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

public class Delivery_LoginPhone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView txtsignup;
    CountryCodePicker cpp;
    FirebaseAuth FAuth;
    String numberr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login_phone);

        num=(EditText)findViewById(R.id.DLPnumber);
        sendotp=(Button)findViewById(R.id.DLPotp);
        cpp=(CountryCodePicker)findViewById(R.id.DLPcountryCode);
        signinemail=(Button)findViewById(R.id.DLPbtnEmail);
        txtsignup=(TextView)findViewById(R.id.DLPsignup);


        FAuth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numberr=num.getText().toString().trim();
                String phonenumber= cpp.getSelectedCountryCodeWithPlus() + numberr;
                Intent b=new Intent(Delivery_LoginPhone.this,Deliverysendotp.class);
                b.putExtra("phonenumberd",phonenumber);
                startActivity(b);
                finish();

            }
        });

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Delivery_LoginPhone.this,Delivery_registration.class);
                startActivity(a);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent em=new Intent(Delivery_LoginPhone.this, Delivery_Login.class);
                startActivity(em);
                finish();
            }
        });
    }
}