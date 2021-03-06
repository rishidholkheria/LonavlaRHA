package com.example.lonavlarha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class first_screen_si extends AppCompatActivity {

    FirebaseUser currentuser;
    DatabaseReference fbd;
    private static final int PERMISSION_REQUEST_CODE = 1;
    User user;


    Spinner spinner1, spinner2, spinner3, spinner4;
    EditText e1, e2, e3, e4, name_e5,e5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen_si);

        fbd = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        user = new User();


        FloatingActionButton fab = findViewById(R.id.button1);

        final String[] Food = {"Rice", "Daal", "Sabzi", "Roti"};
        spinner1 = findViewById(R.id.spinner1);

        ArrayAdapter a = new ArrayAdapter(this, R.layout.spinner_layout, Food);
        a.setDropDownViewResource(R.layout.spinner_layout);
        spinner1.setAdapter(a);
        final String[] food2 = {"", "", ""};
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lonavla RHA");

        spinner2 = findViewById(R.id.spinner2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int j = 0;

                for (int counter = 0; counter < 4; counter++) {

                    if (!(counter == i)) {
                        food2[j] = Food[counter];
                        j++;
                    }
                }

                ArrayAdapter qq = new ArrayAdapter(first_screen_si.this, R.layout.spinner_layout, food2);
                qq.setDropDownViewResource(R.layout.spinner_layout);
                spinner2.setAdapter(qq);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3 = findViewById(R.id.spinner3);
        final String[] food3 = {"", ""};
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int j = 0;

                for (int counter = 0; counter < 3; counter++) {

                    if (!(counter == i)) {
                        food3[j] = food2[counter];
                        j++;
                    }
                }

                ArrayAdapter qq = new ArrayAdapter(first_screen_si.this, R.layout.spinner_layout, food3);
                qq.setDropDownViewResource(R.layout.spinner_layout);
                spinner3.setAdapter(qq);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner4 = findViewById(R.id.spinner4);
        final String[] food4 = {""};
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                int j = 0;

                for (int counter = 0; counter < 2; counter++) {

                    if (!(counter == i)) {
                        food4[j] = food3[counter];
                        j++;
                    }
                }

                ArrayAdapter qq = new ArrayAdapter(first_screen_si.this, R.layout.spinner_layout, food4);
                qq.setDropDownViewResource(R.layout.spinner_layout);
                spinner4.setAdapter(qq);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        name_e5 = findViewById(R.id.name_e5);
        e5 = findViewById(R.id.e6);


        e1.setText("");
        e2.setText("");
        name_e5.setText("");
        e3.setText("");
        e4.setText("");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String sms = smsText.getText().toString();
                //String phoneNum = phoneNumber.getText().toString();
                //if (!TextUtils.isEmpty(sms) && !TextUtils.isEmpty(phoneNum)) {
                if (checkPermission()) {

//Get the default SmsManager//

                    String message_sent = "";
                    if (!(name_e5.getText().toString().equals("")) && !e1.getText().toString().equals("")) {
                        SmsManager smsManager = SmsManager.getDefault();
                        message_sent = "From " + name_e5.getText().toString()  + ":";
                        message_sent = message_sent + "\nWe have " + spinner1.getSelectedItem().toString() + " " + e1.getText().toString() + " (kg/no./ltr)";
                        if (!e2.getText().toString().equals(""))
                            message_sent = message_sent + "\n" + spinner2.getSelectedItem().toString() + " " + e2.getText().toString() + " (kg/no./ltr)";
                        if (!e3.getText().toString().equals(""))
                            message_sent = message_sent + "\n" + spinner3.getSelectedItem().toString() + " " + e3.getText().toString() + " (kg/no./ltr)";
                        if (!e4.getText().toString().equals(""))
                            message_sent = message_sent + "\n" + spinner4.getSelectedItem().toString() + " " + e4.getText().toString() + " (kg/no./ltr)";
                        if (!e5.getText().toString().equals(""))
                            message_sent = message_sent + "\nAnd " + e5.getText().toString();

                        smsManager.sendTextMessage("+918377972338", null, message_sent, null, null);
                        smsManager.sendTextMessage("+919643959973", null, message_sent, null, null);
                        e1.setText("");
                        e2.setText("");
                        name_e5.setText("");
                        e3.setText("");
                        e4.setText("");
                        Toast.makeText(first_screen_si.this, "Information Sent Successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        if((name_e5.getText().toString().equals(""))){
                            name_e5.setError("Required!");
                        }
                        if (e1.getText().toString().equals("")){
                            e1.setError("At least One Item Required!");
                        }

                    }


//Send the SMS//

                } else {
                    Toast.makeText(first_screen_si.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }


            }
        });


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Log.e("permission", "Permission already granted.");
            } else {
                requestPermission();
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////


        //here

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_first_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case (R.id.signout):

                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getApplicationContext(), first_screen_wsi.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(first_screen_si.this, "Signed Out", Toast.LENGTH_LONG).show();
                startActivity(i);

                return true;


            case (R.id.settings):

                startActivity(new Intent(getApplicationContext(), settings_page.class));
                break;

            case (R.id.about):
                startActivity(new Intent(getApplicationContext(), about_us_2.class));
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(first_screen_si.this, Manifest.permission.SEND_SMS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(first_screen_si.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(first_screen_si.this,
                            "Permission denied", Toast.LENGTH_LONG).show();
                    //Button sendSMS = (Button) findViewById(R.id.sendSMS);
                    //sendSMS.setEnabled(false);

                }
                break;
        }
    }
}
