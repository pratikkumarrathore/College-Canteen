package my.collegecanteen.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import my.collegecanteen.app.OwnerFoodPanel.OwnerHomeFragment;
import my.collegecanteen.app.OwnerFoodPanel.OwnerPendingOrderFragment;
import my.collegecanteen.app.OwnerFoodPanel.OwnerProfileFragment;
import my.collegecanteen.app.OwnerFoodPanel.OwnerOrderFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class OwnerFoodPanel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_food_panel_bottom_navigation);
        BottomNavigationView navigationView = findViewById(R.id.chef_bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
        UpdateToken();
        String name = getIntent().getStringExtra("PAGE");
        if (name != null) {
            if (name.equalsIgnoreCase("Orderpage")) {
                loadownerfragment(new OwnerPendingOrderFragment());
            } else if (name.equalsIgnoreCase("Confirmpage")) {
                loadownerfragment(new OwnerOrderFragment());
            } else if (name.equalsIgnoreCase("AcceptOrderpage")) {
                loadownerfragment(new OwnerHomeFragment());
            } else if (name.equalsIgnoreCase("Deliveredpage")) {
                loadownerfragment(new OwnerHomeFragment());
            }
        } else {
            loadownerfragment(new OwnerHomeFragment());
        }

    }
    private void UpdateToken() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isComplete()){
                    String token = task.getResult();
                    FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);



                }
            }
        });
//        String refreshToken = FirebaseInstanceId.getInstance().getToken();
//        Token token = new Token(refreshToken);
//        FirebaseDatabase.getInstance().getReference("Tokens").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(token);
    }

    private boolean loadownerfragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.OwnerHome:
                fragment = new OwnerHomeFragment();
                break;

            case R.id.PendingOrders:
                fragment = new OwnerPendingOrderFragment();
                break;

            case R.id.Orders:
                fragment = new OwnerOrderFragment();
                break;
            case R.id.OwnerProfile:
                fragment = new OwnerProfileFragment();
                break;
        }
        return loadownerfragment(fragment);
    }

}