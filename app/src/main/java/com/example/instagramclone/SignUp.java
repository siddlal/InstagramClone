package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;


public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    private TextView txtGetData;
    private Button btnGet;
    private String allKickBoxers;
    private Button btnNextActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        ParseInstallation.getCurrentInstallation().saveInBackground();

        btnSave = findViewById(R.id.btnSave);
        btnGet = findViewById(R.id.btnGet);
        btnNextActivity = findViewById(R.id.btnNextActivity);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtKickPower);

        txtGetData = findViewById(R.id.txtGetData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Kicker");
                parseQuery.getInBackground("Bmc3GHDcET", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("name") + "-" + "Punch Power: " + object.get("punch_power") );
                        }
                    }
                });
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Kicker");
                queryAll.whereGreaterThanOrEqualTo("punch_speed",100);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e ==null) {
                            if (objects.size() > 0){
                                for (ParseObject kickBoxer : objects) {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this,allKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                            }
//                            else {
//                                FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();
//                            }
                        }

                    }
                });

            }
        });

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {

        final ParseObject kickBoxer = new ParseObject("Kicker");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punch_speed",Integer.parseInt(edtPunchSpeed.getText().toString()));
        kickBoxer.put("punch_power",Integer.parseInt(edtPunchPower.getText().toString()));
        kickBoxer.put("kick_speed",Integer.parseInt(edtKickSpeed.getText().toString()));
        kickBoxer.put("kick_power",Integer.parseInt(edtKickPower.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Toast.makeText(SignUp.this,kickBoxer.get("name") + " is saved to server", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(SignUp.this,e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


//    public void helloWorldTapped(View view){
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punch_speed",200);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e==null){
//                    Toast.makeText(SignUp.this,"boxer object is saved successfully",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        final ParseObject kickBoxer = new ParseObject("Kicker");
//        kickBoxer.put("name","Aladin");
//        kickBoxer.put("punch_speed",1000);
//        kickBoxer.put("punch_power",100);
//        kickBoxer.put("kick_speed",2000);
//        kickBoxer.put("kick_power",300);
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null) {
//                    Toast.makeText(SignUp.this,kickBoxer.get("name") + " is saved to server", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });




}
