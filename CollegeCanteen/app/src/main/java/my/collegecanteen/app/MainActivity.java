package my.collegecanteen.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth Fauth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView bgimage1;
    ImageView imageVieww;
    TextView textView;
    TextView textView1;
    TextView textView2;

    String[] permissions={"android.permission.SEND_SMS","android.permission.READ_SMS","android.permission.WRITE_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageVieww=(ImageView)findViewById(R.id.imageView3);
        textView=(TextView)findViewById(R.id.textView2);
        textView1=(TextView)findViewById(R.id.textView5);
        textView2=(TextView)findViewById(R.id.textView6);
        imageVieww.animate().alpha(0f).setDuration(0);
        textView.animate().alpha(0f).setDuration(0);
        textView1.animate().alpha(0f).setDuration(0);
        textView2.animate().alpha(0f).setDuration(0);
        imageVieww.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textView.animate().alpha(1f).setDuration(1000);
                textView1.animate().alpha(1f).setDuration(1000);
                textView2.animate().alpha(1f).setDuration(1000);
            }
        });

        final Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        bgimage1 = findViewById(R.id.WelcomeBkg);
        bgimage1.setAnimation(zoomin);
        bgimage1.setAnimation(zoomout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions,80);
        }

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage1.startAnimation(zoomin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage1.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Redirecting to Home Page Please wait...", Toast.LENGTH_SHORT).show();
                Fauth = FirebaseAuth.getInstance();
                if (Fauth.getCurrentUser() != null) {
                    if (Fauth.getCurrentUser().isEmailVerified()) {
                        Fauth = FirebaseAuth.getInstance();
                        databaseReference = FirebaseDatabase.getInstance().getReference("User").child(FirebaseAuth.getInstance().getUid() + "/Role");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String role = dataSnapshot.getValue(String.class);
                                if (role.equals("Owner")) {
                                    Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                                    Intent a = new Intent(MainActivity.this, OwnerFoodPanel_BottomNavigation.class);
                                    startActivity(a);
                                    finish();
                                }
                                if (role.equals("Customer")) {
                                    Intent n = new Intent(MainActivity.this, CustomerFoodPanel_BottomNavigation.class);
                                    startActivity(n);
                                    finish();
                                }
                                if (role.equals("DeliveryPerson")) {
                                    Intent n = new Intent(MainActivity.this, DeliveryFoodPanel_BottomNavigation.class);
                                    startActivity(n);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                    else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Check whether you have verified your details, Otherwise please verify");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                Intent intent = new Intent(MainActivity.this, Signin_page.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                        Fauth.signOut();
                    }
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Signin_page.class);
                    startActivity(intent);
                    finish();

                }
            }
        }, 2500);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==80){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }else {
                Toast.makeText(this, "Permission required for email and mobile verification", Toast.LENGTH_SHORT).show();
            }
        }
    }
}