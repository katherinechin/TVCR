package com.example.tvcr;

//This Activity uses a helper class to execute the actual SQL code.
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

public class Add extends Activity implements View.OnClickListener {

    private TextView text;
    private SQLiteDatabase db;
    private SQLHelper helper;

    private EditText nameField;
    private EditText numberField;
//    private EditText emailField;
    private EditText urlField;
    private EditText addressField;
    private Button submitButton;

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

        submitButton = (Button)findViewById(R.id.addSave);
        submitButton.setOnClickListener(this);


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
