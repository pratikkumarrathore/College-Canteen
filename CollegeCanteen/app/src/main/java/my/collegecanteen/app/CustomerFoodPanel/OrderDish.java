package my.collegecanteen.app.CustomerFoodPanel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import my.collegecanteen.app.Customer;
import my.collegecanteen.app.CustomerFoodPanel_BottomNavigation;
import my.collegecanteen.app.OwnerFoodPanel.Owner;
import my.collegecanteen.app.OwnerFoodPanel.UpdateDishModel;
import my.collegecanteen.app.R;

public class OrderDish extends AppCompatActivity {

    String RandomId, OwnerID;
    ImageView imageView;
    ElegantNumberButton additem;
    TextView Foodname, OwnerName, FoodQuantity, FoodPrice, FoodDescription;
    DatabaseReference databaseReference, dataaa, ownerdata, reference, data, dataref;
    String State, City, dishname;
    String Name;
    int dishprice;
    String custID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_dish);

        Foodname = (TextView) findViewById(R.id.food_name);
        OwnerName = (TextView) findViewById(R.id.chef_name);
        FoodQuantity = (TextView) findViewById(R.id.food_quantity);
        FoodPrice = (TextView) findViewById(R.id.food_price);
        FoodDescription = (TextView) findViewById(R.id.food_description);
        imageView = (ImageView) findViewById(R.id.image);
        additem = (ElegantNumberButton) findViewById(R.id.number_btn);

        final String userid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        dataaa = FirebaseDatabase.getInstance().getReference("Customer").child(userid);
        dataaa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Customer cust = dataSnapshot.getValue(Customer.class);
                assert cust != null;
                Name = cust.getFname();


                RandomId = getIntent().getStringExtra("FoodMenu");
                OwnerID = getIntent().getStringExtra("OwnerId");

                databaseReference = FirebaseDatabase.getInstance().getReference("FoodSupplyDetails");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UpdateDishModel updateDishModel = dataSnapshot.getValue(UpdateDishModel.class);
                        assert updateDishModel != null;
                        Foodname.setText(updateDishModel.getDishes());
                        String qua = "<b>" + "Quantity: " + "</b>" + updateDishModel.getQuantity();
                        FoodQuantity.setText(Html.fromHtml(qua));
                        String ss = "<b>" + "Description: " + "</b>" + updateDishModel.getDescription();
                        FoodDescription.setText(Html.fromHtml(ss));
                        String pri = "<b>" + "Price: ₹ " + "</b>" + updateDishModel.getPrice();
                        FoodPrice.setText(Html.fromHtml(pri));
                        //                      Glide.with(OrderDish.this).load(updateDishModel.getImageURL()).into(imageView);

                        ownerdata = FirebaseDatabase.getInstance().getReference("Owner");
                        ownerdata.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Owner owner = dataSnapshot.getValue(Owner.class);

                                String name = "<b>" + "Owner Name: " + "</b>" + Owner.getFname() + " " + Owner.getLname();
                                OwnerName.setText(Html.fromHtml(name));
                                custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                                databaseReference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Cart cart = dataSnapshot.getValue(Cart.class);
                                        if (dataSnapshot.exists()) {
                                            assert cart != null;
                                            additem.setNumber(cart.getDishQuantity());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                additem.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dataref = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        dataref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Cart cart1=null;
                                if (dataSnapshot.exists()) {
                                    int totalcount=0;
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        totalcount++;
                                    }
                                    int i=0;
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        i++;
                                        if(i==totalcount){
                                            cart1= snapshot.getValue(Cart.class);
                                        }
                                    }

                                    assert cart1 != null;
                                    if (OwnerID.equals(cart1.getOwnerId())) {
                                        data = FirebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(OwnerID).child(RandomId);
                                        data.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
                                                assert update != null;
                                                dishname = update.getDishes();
                                                dishprice = Integer.parseInt(update.getPrice());

                                                int num = Integer.parseInt(additem.getNumber());
                                                int totalprice = num * dishprice;
                                                if (num != 0) {
                                                    HashMap<String, String> hashMap = new HashMap<>();
                                                    hashMap.put("DishName", dishname);
                                                    hashMap.put("DishID", RandomId);
                                                    hashMap.put("DishQuantity", String.valueOf(num));
                                                    hashMap.put("Price", String.valueOf(dishprice));
                                                    hashMap.put("Totalprice", String.valueOf(totalprice));
                                                    hashMap.put("OwnerId", OwnerID);
                                                    custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                    reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                                    reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                } else {

                                                    FirebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                    else
                                    {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDish.this);
                                        builder.setMessage("You can't add food items of multiple Owner at a time. Try to add items of same Owner");
                                        builder.setCancelable(false);
                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();
                                                Intent intent = new Intent(OrderDish.this, CustomerFoodPanel_BottomNavigation.class);
                                                startActivity(intent);
                                                finish();

                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }
                                } else {
                                    data = FirebaseDatabase.getInstance().getReference("FoodSupplyDetails").child(OwnerID).child(RandomId);
                                    data.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            UpdateDishModel update = dataSnapshot.getValue(UpdateDishModel.class);
                                            dishname = update.getDishes();
                                            dishprice = Integer.parseInt(update.getPrice());
                                            int num = Integer.parseInt(additem.getNumber());
                                            int totalprice = num * dishprice;
                                            if (num != 0) {
                                                HashMap<String, String> hashMap = new HashMap<>();
                                                hashMap.put("DishName", dishname);
                                                hashMap.put("DishID", RandomId);
                                                hashMap.put("DishQuantity", String.valueOf(num));
                                                hashMap.put("Price", String.valueOf(dishprice));
                                                hashMap.put("Totalprice", String.valueOf(totalprice));
                                                hashMap.put("OwnerId", OwnerID);
                                                custID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                                reference = FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(custID).child(RandomId);
                                                reference.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        Toast.makeText(OrderDish.this, "Added to cart", Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            } else {

                                                FirebaseDatabase.getInstance().getReference("Cart").child(custID).child(RandomId).removeValue();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}