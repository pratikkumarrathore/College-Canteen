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

public class Ownerloginphone extends AppCompatActivity {

    EditText num;
    Button sendotp,signinemail;
    TextView txtsignup;
    CountryCodePicker cpp;
    FirebaseAuth FAuth;
    String numberr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerloginphone);

        num=(EditText)findViewById(R.id.OLPnumber);
        sendotp=(Button)findViewById(R.id.OLPotp);
        cpp=(CountryCodePicker)findViewById(R.id.OLPcountryCode);
        signinemail=(Button)findViewById(R.id.OLPbtnEmail);
        txtsignup=(TextView)findViewById(R.id.OLPsignup);


        FAuth=FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                numberr=num.getText().toString().trim();
                String phonenumber= cpp.getSelectedCountryCodeWithPlus() + numberr;
                Intent b=new Intent(Ownerloginphone.this,Ownersendotp.class);
                b.putExtra("phonenumber",phonenumber);
                startActivity(b);
                finish();

            }
        });

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Ownerloginphone.this,OwnerRegistration.class);
                startActivity(a);
                finish();
            }
        });

        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent em=new Intent(Ownerloginphone.this, OwnerLogin.class);
                startActivity(em);
                finish();
            }
        });
    }
}