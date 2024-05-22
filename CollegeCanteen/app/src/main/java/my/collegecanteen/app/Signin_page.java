package my.collegecanteen.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Signin_page extends AppCompatActivity {
    private Button signInwithMail,signInwithPhone,signUp;
    ImageView bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_page);

        getSupportActionBar().setTitle("SignIn");
        final Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        final Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        bgimage = findViewById(R.id.BackG);
        bgimage.setAnimation(zoomin);
        bgimage.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bgimage.startAnimation(zoomin);
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
                bgimage.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        signInwithMail = findViewById(R.id.signInwithMail);
        signInwithPhone = findViewById(R.id.signInwithPhone);
        signUp = findViewById(R.id.signUp);

        signInwithMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signEmail = new Intent(Signin_page.this, ChooseOne.class);
                signEmail.putExtra("Home", "Email");
                startActivity(signEmail);
                finish();
            }
        });

        signInwithPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signPhone = new Intent(Signin_page.this, ChooseOne.class);
                signPhone.putExtra("Home", "Phone");
                startActivity(signPhone);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(Signin_page.this, ChooseOne.class);
                signUp.putExtra("Home", "SignUp");
                startActivity(signUp);
                finish();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }
}