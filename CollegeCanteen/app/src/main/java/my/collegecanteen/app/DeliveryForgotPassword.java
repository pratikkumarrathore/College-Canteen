package my.collegecanteen.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class DeliveryForgotPassword extends AppCompatActivity {

    TextInputLayout forgetpasswordd;
    Button Reset;
    Button goback;
    FirebaseAuth FAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_forgot_password);

        getSupportActionBar().setTitle("Forgot Password");

        forgetpasswordd = (TextInputLayout) findViewById(R.id.Emailid);
        Reset = (Button) findViewById(R.id.Resetbtn);
        goback = (Button) findViewById(R.id.gobackd);

        FAuth = FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(DeliveryForgotPassword.this);
                mDialog.setCancelable(false);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setMessage("Logging in...");
                mDialog.show();

                FAuth.sendPasswordResetEmail(forgetpasswordd.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            ReusableCodeForAll.ShowAlert(DeliveryForgotPassword.this, "", "Password has been sent to your Email");
                        } else {
                            mDialog.dismiss();
                            ReusableCodeForAll.ShowAlert(DeliveryForgotPassword.this, "Error", task.getException().getMessage());
                        }
                    }
                });
            }
        });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeliveryForgotPassword.this,Delivery_Login.class));
            }
        });
    }
}