package my.collegecanteen.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.HashMap;

public class Delivery_registration extends AppCompatActivity {

    String[] Chhattisgarh = {"Bilaspur", "Raigarh", "Bhilai","Raipur"};
    String[] MadhyaPradesh = {"Bhopal", "Indore", "Ujjain"};

    TextInputLayout Fname, Lname, Email, Pass, cfpass, mobileno, houseno, area, pincode;

    Spinner statespin, cityspin;

    Button Register, Emaill, Phone;

    CountryCodePicker Ccp;

    FirebaseAuth fAuth;

    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;

    String fname;
    String lname;
    String emailid;
    String password;
    String confirmpassword;
    String mobile;
    String house;
    String Area;

    String Pincode;
    String role = "DeliveryPerson";
    String states;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_registration);

        getSupportActionBar().setTitle("Delivery Person Registration");

        Fname = (TextInputLayout) findViewById(R.id.DFirstname);
        Lname = (TextInputLayout) findViewById(R.id.DLastname);
        Email = (TextInputLayout) findViewById(R.id.DEmail);
        Pass = (TextInputLayout) findViewById(R.id.DPwd);
        cfpass = (TextInputLayout) findViewById(R.id.DCpass);
        mobileno = (TextInputLayout) findViewById(R.id.DMobileno);
        houseno = (TextInputLayout) findViewById(R.id.DhouseNo);
        area = (TextInputLayout) findViewById(R.id.DArea);
        pincode = (TextInputLayout) findViewById(R.id.DPincode);
        statespin = (Spinner) findViewById(R.id.DState);
        cityspin = (Spinner) findViewById(R.id.DCity);
        Register = (Button) findViewById(R.id.DRegister);
        Emaill = (Button) findViewById(R.id.Demaill);
        Phone = (Button) findViewById(R.id.Dphone);
        Ccp = (CountryCodePicker) findViewById(R.id.DCountryCode);

        statespin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                states = value.toString().trim();
                if (states.equals("Chhattisgarh")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : Chhattisgarh) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Delivery_registration.this, android.R.layout.simple_spinner_item, list);

                    cityspin.setAdapter(arrayAdapter);
                }
                if (states.equals("MadhyaPradesh")) {
                    ArrayList<String> list = new ArrayList<>();
                    for (String text : MadhyaPradesh) {
                        list.add(text);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Delivery_registration.this, android.R.layout.simple_spinner_item, list);

                    cityspin.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cityspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object value = parent.getItemAtPosition(position);
                city = value.toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        databaseReference = firebaseDatabase.getInstance().getReference("DeliveryPerson");
        fAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confirmpassword = cfpass.getEditText().getText().toString().trim();
                Area = area.getEditText().getText().toString().trim();
                house = houseno.getEditText().getText().toString().trim();
                Pincode = pincode.getEditText().getText().toString().trim();


                if (isValid()) {

                    final ProgressDialog mDialog = new ProgressDialog(Delivery_registration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registering please wait...");
                    mDialog.show();

                    fAuth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("Role", role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {

                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        HashMap<String, String> hashMappp = new HashMap<>();
                                        hashMappp.put("Fname", fname);
                                        hashMappp.put("Lname", lname);
                                        hashMappp.put("Mobile", mobile);
                                        hashMappp.put("EmailID", emailid);
                                        hashMappp.put("Password", password);
                                        hashMappp.put("ConfirmPassword", confirmpassword);
                                        hashMappp.put("House", house);
                                        hashMappp.put("Area", Area);
                                        hashMappp.put("Pincode", Pincode);
                                        hashMappp.put("City", city);
                                        hashMappp.put("State", states);
                                        firebaseDatabase.getInstance().getReference("DeliveryPerson")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(Delivery_registration.this);
                                                                    builder.setMessage("Registered Successfully,Please Verify your Email");
                                                                    Toast.makeText(Delivery_registration.this, "Hey!! Use the link sent to your Email Address to verify your Email", Toast.LENGTH_SHORT).show();
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                            dialog.dismiss();

                                                                            String phonenumber = Ccp.getSelectedCountryCodeWithPlus() + mobile;
                                                                            Intent b = new Intent(Delivery_registration.this,DeliveryVerifyPhone.class);
                                                                            b.putExtra("phonenumberd", phonenumber);
                                                                            startActivity(b);

                                                                        }
                                                                    });
                                                                    AlertDialog alert = builder.create();
                                                                    alert.show();

                                                                } else {
                                                                    mDialog.dismiss();
                                                                    ReusableCodeForAll.ShowAlert(Delivery_registration.this, "Error", task.getException().getMessage());

                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                    }
                                });


                            } else {
                                mDialog.dismiss();
                                ReusableCodeForAll.ShowAlert(Delivery_registration.this, "Error", task.getException().getMessage());
                            }

                        }
                    });

                }

            }

        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Delivery_registration.this, Delivery_Login.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(Delivery_registration.this, Delivery_LoginPhone.class);
                startActivity(e);
                finish();
            }
        });
    }
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public boolean isValid() {
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cfpass.setErrorEnabled(false);
        cfpass.setError("");
        area.setErrorEnabled(false);
        area.setError("");
        houseno.setErrorEnabled(false);
        houseno.setError("");
        pincode.setErrorEnabled(false);
        pincode.setError("");

        boolean isValidname = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false, isvalidlname = false, isvalidhousestreetno = false, isvalidarea = false, isvalidpostcode = false;
        if (TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("Firstname is required");
        } else {
            isValidname = true;
        }
        if (TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("Lastname is required");
        } else {
            isvalidlname = true;
        }
        if (TextUtils.isEmpty(emailid)) {
            Email.setErrorEnabled(true);
            Email.setError("Email is required");
        } else {
            if (emailid.matches(emailpattern)) {
                isValidemail = true;
            } else {
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email Address");
            }

        }
        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                Pass.setErrorEnabled(true);
                Pass.setError("password too weak");
            } else {
                isvalidpassword = true;
            }
        }
        if (TextUtils.isEmpty(confirmpassword)) {
            cfpass.setErrorEnabled(true);
            cfpass.setError("Confirm Password is required");
        } else {
            if (!password.equals(confirmpassword)) {
                Pass.setErrorEnabled(true);
                Pass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(mobile)) {
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile number is required");
        } else {
            if (mobile.length() < 10) {
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid mobile number");
            } else {
                isvalidmobileno = true;
            }
        }
        if (TextUtils.isEmpty(house)) {
            houseno.setErrorEnabled(true);
            houseno.setError("Field cannot be empty");
        } else {
            isvalidhousestreetno = true;
        }
        if (TextUtils.isEmpty(Area)) {
            area.setErrorEnabled(true);
            area.setError("Field cannot be empty");
        } else {
            isvalidarea = true;
        }
        if (TextUtils.isEmpty(Pincode)) {
            pincode.setErrorEnabled(true);
            pincode.setError("Field cannot be empty");
        } else {
            isvalidpostcode = true;
        }

        isvalid = (isValidname && isvalidpostcode && isvalidlname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isvalidarea && isvalidhousestreetno) ? true : false;
        return isvalid;
    }
}