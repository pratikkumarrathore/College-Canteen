package my.collegecanteen.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.hbb20.CountryCodePicker;

public class LoginPhone extends AppCompatActivity {
    EditText num;
    Button sendotp,signinemail;
    TextView txtsignup;
    CountryCodePicker cpp;
    FirebaseAuth FAuth;
    String numberr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone);

        num=(EditText)findViewById(R.id.ULPnumber);
        sendotp=(Button)findViewById(R.id.ULPotp);
        cpp=(CountryCodePicker)findViewById(R.id.ULPcountryCode);
        signinemail=(Button)findViewById(R.id.ULPbtnEmail);
        txtsignup=(TextView)findViewById(R.id.ULPsignup);


        FAuth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numberr=num.getText().toString().trim();
                String phonenumber= cpp.getSelectedCountryCodeWithPlus() + numberr;
                Intent b=new Intent(LoginPhone.this,sendotp.class);
                b.putExtra("phonenumberu",phonenumber);
                startActivity(b);
                finish();

            }
        });

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(LoginPhone.this,Registration.class);
                startActivity(a);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent em=new Intent(LoginPhone.this, Login.class);
                startActivity(em);
                finish();
            }
        });
    }
}