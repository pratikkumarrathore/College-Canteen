package my.collegecanteen.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseOne extends AppCompatActivity {
    private Button OwnerBtn,CustomerBtn,DeliveryBtn;
    String type;
    Intent intent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_one);

        OwnerBtn=findViewById(R.id.OwnerBtn);
        CustomerBtn=findViewById(R.id.CustomerBtn);
        DeliveryBtn=findViewById(R.id.DeliveryBtn);

        intent = getIntent();
        type = intent.getStringExtra("Home").toString().trim();

        OwnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")) {
                    Intent loginemail = new Intent(ChooseOne.this, OwnerLogin.class);
                    startActivity(loginemail);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphone = new Intent(ChooseOne.this, Ownerloginphone.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent Register = new Intent(ChooseOne.this, OwnerRegistration.class);
                    startActivity(Register);
                }
            }
        });

        CustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("Email")) {
                    Intent loginemailcust = new Intent(ChooseOne.this, Login.class);
                    startActivity(loginemailcust);
                    finish();
                }
                if (type.equals("Phone")) {
                    Intent loginphonecust = new Intent(ChooseOne.this, LoginPhone.class);
                    startActivity(loginphonecust);
                    finish();
                }
                if (type.equals("SignUp")) {
                    Intent Registercust = new Intent(ChooseOne.this, Registration.class);
                    startActivity(Registercust);
                }
            }
        });

        DeliveryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("SignUp")) {
                    Intent Registerdelivery = new Intent(ChooseOne.this, Delivery_registration.class);
                    startActivity(Registerdelivery);
                }
                if (type.equals("Phone")) {
                    Intent loginphone = new Intent(ChooseOne.this, Delivery_LoginPhone.class);
                    startActivity(loginphone);
                    finish();
                }
                if (type.equals("Email")) {
                    Intent loginemail = new Intent(ChooseOne.this, Delivery_Login.class);
                    startActivity(loginemail);
                    finish();
                }
            }
        });
    }
}