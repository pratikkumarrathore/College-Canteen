package my.collegecanteen.app.CustomerFoodPanel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import my.collegecanteen.app.Customer;
import my.collegecanteen.app.R;
import my.collegecanteen.app.Signin_page;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CustomerProfileFragment extends Fragment {


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

    EditText firstname, lastname,semester;
    Spinner Deptspn, Semspn;
    TextView mobileno, Emailid,enrollno;
    Button Update;
    LinearLayout password, LogOut;
    DatabaseReference databaseReference, data;
    String dept, sem, email, passwordd, confirmpass,enroll;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Profile");
        View v = inflater.inflate(R.layout.fragment_customer_profile, null);

        firstname = (EditText) v.findViewById(R.id.fnamee);
        lastname = (EditText) v.findViewById(R.id.lnamee);
        semester = (EditText) v.findViewById(R.id.semester);
  //      Email = (TextView) v.findViewById(R.id.emailID);
        Deptspn = (Spinner) v.findViewById(R.id.deptspin);
        mobileno = (TextView) v.findViewById(R.id.mobilenumber);
        enrollno = (TextView) v.findViewById(R.id.enrollnumber);
   //     Emailid = (TextView) v.findViewById(R.id.Emailid);
        Update = (Button) v.findViewById(R.id.update);
        password = (LinearLayout) v.findViewById(R.id.passwordlayout);
        LogOut = (LinearLayout) v.findViewById(R.id.logout_layout);

        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Customer customer = dataSnapshot.getValue(Customer.class);

                firstname.setText(customer.getFname());
                lastname.setText(customer.getLname());
                mobileno.setText(customer.getMobile());
                Deptspn.setSelection(getIndexByString(Deptspn, customer.getDepartment()));
                semester.setText(customer.getSemester());
                enrollno.setText(customer.getEnrollmentNo());
       //         Emailid.setText(customer.getEmailId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        updateinformation();
        return v;
    }

    private void updateinformation() {


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                data = FirebaseDatabase.getInstance().getReference("Customer").child(useridd);
                data.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Customer customer = dataSnapshot.getValue(Customer.class);


                        confirmpass = customer.getConfirmPassword();
                        email = customer.getEmailId();
                        passwordd = customer.getPassword();
                        long mobilenoo = Long.parseLong(customer.getMobile());
                        enroll = customer.getEnrollmentNo();
                        String Fname = firstname.getText().toString().trim();
                        String Lname = lastname.getText().toString().trim();
                        String sem = semester.getText().toString().trim();
                        String enroll = enrollno.getText().toString().trim();
//                        String email = Emailid.getText().toString().trim();

                        HashMap<String, String> hashMappp = new HashMap<>();

                        hashMappp.put("Fname", Fname);
                        hashMappp.put("Lname", Lname);
                        hashMappp.put("Mobile", String.valueOf(mobilenoo));
                        hashMappp.put("EmailID", email);
                        hashMappp.put("Password", passwordd);
                        hashMappp.put("ConfirmPassword", confirmpass);
                        hashMappp.put("Department", dept);
                        hashMappp.put("Semester", sem);
                        hashMappp.put("EnrollmentNo", enroll);
                        FirebaseDatabase.getInstance().getReference("Customer").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(hashMappp);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CustomerPassword.class);
                startActivity(intent);
            }
        });

        mobileno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), CustomerPhonenumber.class);
                startActivity(i);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Are you sure you want to Logout ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getActivity(), Signin_page.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

    }

    private int getIndexByString(Spinner st, String spist) {
        int index = 0;
        for (int i = 0; i < st.getCount(); i++) {
            if (st.getItemAtPosition(i).toString().equalsIgnoreCase(spist)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
