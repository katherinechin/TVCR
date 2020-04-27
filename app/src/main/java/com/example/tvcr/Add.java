package com.example.tvcr;

//This Activity uses a helper class to execute the actual SQL code.
import java.util.ArrayList;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ContentValues;
import android.text.method.ScrollingMovementMethod;

import androidx.core.app.NotificationCompat;

public class Add extends Activity implements View.OnClickListener {

    // TODO comment variables, methods and blocks

    private TextView text;
    private SQLiteDatabase db;
    private SQLHelper helper;

    private EditText nameField;
    private EditText numberField;
//    private EditText emailField;
    private EditText urlField;
    private EditText addressField;
    private Button saveButton;


    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder notifyDetails = null;
    private int SIMPLE_NOTFICATION_ID = 1;
    private String contentTitle = "Email Notification";
    private String contentText = "Get to Email by clicking me";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        // TODO eventually remove TextTest in add.xml when done testing
        text = (TextView) findViewById(R.id.TextTest);

        nameField = (EditText) findViewById(R.id.editName);
        numberField = (EditText) findViewById(R.id.editNumber);
//        emailField = (EditText) findViewById(R.id.editEmail);
        urlField = (EditText) findViewById(R.id.editUrl);
        addressField = (EditText) findViewById(R.id.editAddress);

        //let textview widget scroll
        text.setMovementMethod(new ScrollingMovementMethod());

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch(SQLException e) {
            Log.d("SQLiteDemo", "Create database failed");
        }

        saveButton = (Button)findViewById(R.id.addSave);
//        saveButton.setOnClickListener(this);

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        //As of API 26 Notification Channels must be assigned to a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Channel foobar",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Channel description");
            channel.setLightColor(Color.GREEN);
            mNotificationManager.createNotificationChannel(channel);
        }

        //create implicit intent for action when notification selected
        //from expanded notification screen
        //open email when notification clicked
        Intent notifyIntent = new Intent(this, Home.class);

        //the pending intent will outlive this app
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 9, notifyIntent,0 );

        //set icon, text, and time on notification status bar
        notifyDetails = new NotificationCompat.Builder(this, "default")
                .setContentIntent(pendingIntent)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setAutoCancel(true)     //cancel Notification after clicking on it
                //set Android to vibrate when notified
                .setVibrate(new long[] {1000, 1000, 2000, 2000});






//        //update buffalo to gorilla
//        helper.updateContact(new ContactInfo("Abigail"), new ContactInfo("John"));
//
//        //delete record
//        helper.deleteContact(new ContactInfo("Abigail"));

    }

    // Perform action on click
    public void onClick(View v) {
        // create a switch for the four button functionalities
        switch(v.getId()) {

            // activates web functionality upon WEB button click
            case R.id.addSave:

                // insert record
                helper.addContact(new ContactInfo(nameField.getText().toString(), numberField.getText().toString(),
                        //emailField.getText().toString(),
                        urlField.getText().toString(), addressField.getText().toString()));

                //query database
                ArrayList<ContactInfo> contactList = helper.getContactList();

                for (ContactInfo item : contactList) {
                    text.append(item.getName() + " " + item.getNumber() + " "
                            // + item.getEmail() + " "
                            + item.getUrl() + " " + item.getAddress() + "\n" );
                }

                nameField.getText().clear();
                numberField.getText().clear();
                urlField.getText().clear();
                addressField.getText().clear();

                saveButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        //notify() in response to button click.
                        mNotificationManager.notify(SIMPLE_NOTFICATION_ID,
                                notifyDetails.build());

                    }
                });

                break;

        }
    }

    //close database
    @Override
    protected void onPause() {
        super.onPause();
        if(db != null)
            db.close();
    }

}
