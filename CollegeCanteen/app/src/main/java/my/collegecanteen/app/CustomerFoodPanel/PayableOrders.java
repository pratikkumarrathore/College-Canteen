package my.collegecanteen.app.CustomerFoodPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.collegecanteen.app.R;
import my.collegecanteen.app.UPIPayment;

public class PayableOrders extends AppCompatActivity {
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payable_orders);


        button1=(Button)findViewById(R.id.pay);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayableOrders.this, UPIPayment.class);
                startActivity(intent);
            }
        });

    }
}