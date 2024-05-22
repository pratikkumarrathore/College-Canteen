package my.collegecanteen.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class ReusableCodeForAll {

    public static void ShowAlert(Context context, String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Verify your Email Id then Login", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).setTitle(title).setMessage(message).show();
    }
}
