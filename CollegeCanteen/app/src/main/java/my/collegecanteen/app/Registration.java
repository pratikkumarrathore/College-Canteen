package my.collegecanteen.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Registration extends AppCompatActivity {

    String[] BCA = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] BTBC = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] BTZC = {"1st", "2nd", "3rd", "4th","5th", "6th"};
    String[] BZC = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] GZC = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] MBBC = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] MBZC = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] PCM = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] PCSM = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] PGM = {"1st", "2nd", "3rd", "4th", "5th", "6th"};
    String[] PITM = {"1st", "2nd", "3rd", "4th", "5th", "6th"};

    TextInputLayout Fname, Lname, Email, Pass, cfpass, mobileno, Enrollno;

    Spinner Dept, Sems;

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
    String enroll;
    String role = "Customer";
    String depts;
    String sem;
    ProgressDialog mDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setTitle("Customer Registration");
        try {
            mDialog = new ProgressDialog(Registration.this);
            mDialog.setMessage("Registering please wait...");
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
            Fname = (TextInputLayout) findViewById(R.id.UFirstname);
            Lname = (TextInputLayout) findViewById(R.id.ULastname);
            Email = (TextInputLayout) findViewById(R.id.UEmail);
            Pass = (TextInputLayout) findViewById(R.id.UPwd);
            cfpass = (TextInputLayout) findViewById(R.id.UCpass);
            mobileno = (TextInputLayout) findViewById(R.id.UMobileno);
            Enrollno = (TextInputLayout) findViewById(R.id.Enrollno);
            Dept = (Spinner) findViewById(R.id.deptspin);
            Sems = (Spinner) findViewById(R.id.semesterspin);
            Register = (Button) findViewById(R.id.URegister);
            Emaill = (Button) findViewById(R.id.emaill);
            Phone = (Button) findViewById(R.id.phone);
            Ccp = (CountryCodePicker) findViewById(R.id.UCountryCode);

            Dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    depts = value.toString().trim();
                    if (depts.equals("BCA")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : BCA) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("BTBC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : BTBC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("BTZC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : BTZC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("BZC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : BZC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("GZC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : GZC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("MBBC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : MBBC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("MBZC")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : MBZC) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }if (depts.equals("PCM")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : PCM) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }if (depts.equals("PCSM")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : PCSM) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("PGM")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : PGM) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }
                    if (depts.equals("PITM")) {
                        ArrayList<String> list = new ArrayList<>();
                        for (String text : PITM) {
                            list.add(text);
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Registration.this, android.R.layout.simple_spinner_item, list);

                        Sems.setAdapter(arrayAdapter);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            Sems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Object value = parent.getItemAtPosition(position);
                    sem = value.toString().trim();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });

            databaseReference = firebaseDatabase.getInstance().getReference("Customer");
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
                    enroll = Enrollno.getEditText().getText().toString().trim();

                    if (isValid()) {

                        final ProgressDialog mDialog = new ProgressDialog(Registration.this);
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
                                    final HashMap<String, String> hashMap = new HashMap<>();
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
                                            hashMappp.put("Department", depts);
                                            hashMappp.put("Semester", sem);
                                            hashMappp.put("EnrollmentNo", enroll);
                                            firebaseDatabase.getInstance().getReference("Customer")
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(hashMappp).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            mDialog.dismiss();

                                                            fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                                                                        builder.setMessage("Registered Successfully,Please Verify your Email");
                                                                        Toast.makeText(Registration.this, "Hey!! Use the link sent to your Email Address to verify your Email", Toast.LENGTH_SHORT).show();
                                                                        builder.setCancelable(false);
                                                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {

                                                                                dialog.dismiss();

                                                                                String phonenumber = Ccp.getSelectedCountryCodeWithPlus() + mobile;
                                                                                Intent b = new Intent(Registration.this, VerifyPhone.class);
                                                                                b.putExtra("phonenumberu", phonenumber);
                                                                                startActivity(b);

                                                                            }
                                                                        });
                                                                        AlertDialog alert = builder.create();
                                                                        alert.show();

                                                                    } else {
                                                                        mDialog.dismiss();
                                                                        ReusableCodeForAll.ShowAlert(Registration.this, "Error", task.getException().getMessage());

                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                        }
                                    });


                                } else {
                                    mDialog.dismiss();
                                    ReusableCodeForAll.ShowAlert(Registration.this, "Error", task.getException().getMessage());
                                }

                            }
                        });

                    }

                }

            });
        } catch (Exception e) {
            mDialog.dismiss();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
                finish();
            }
        });

        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = new Intent(Registration.this, LoginPhone.class);
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
        cfpass.setErrorEnabled(false);
        cfpass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        Enrollno.setErrorEnabled(false);
        Enrollno.setError("");


        boolean isValidfirstname = false, isValidlastname = false, isValidaddress = false, isValidemail = false, isvalidpassword = false, isvalidconfirmpassword = false, isvalid = false, isvalidmobileno = false;
        if (TextUtils.isEmpty(fname)) {
            Fname.setErrorEnabled(true);
            Fname.setError("FirstName is required");
        } else {
            isValidfirstname = true;
        }
        if (TextUtils.isEmpty(lname)) {
            Lname.setErrorEnabled(true);
            Lname.setError("LastName is required");
        } else {
            isValidlastname = true;
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
        if (TextUtils.isEmpty(password)) {
            Pass.setErrorEnabled(true);
            Pass.setError("Password is required");
        } else {
            if (password.length() < 6) {
                Pass.setErrorEnabled(true);
                Pass.setError("Password too weak");
                cfpass.setError("password too weak");
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
                cfpass.setError("Password doesn't match");
            } else {
                isvalidconfirmpassword = true;
            }
        }
        if (TextUtils.isEmpty(enroll)) {
            Enrollno.setErrorEnabled(true);
            Enrollno.setError("Enrollment Number is required");
        } else {
            isValidaddress = true;
        }
        isvalid = (isValidfirstname && isValidlastname && isValidemail && isvalidconfirmpassword && isvalidpassword && isvalidmobileno && isValidaddress) ? true : false;
        return isvalid;
    }
}