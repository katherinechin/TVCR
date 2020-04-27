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
    private ContentValues values;
    private Cursor cursor;
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

        text = (TextView) findViewById(R.id.TextTest);

        nameField = (EditText) findViewById(R.id.editName);
        numberField = (EditText) findViewById(R.id.editNum);
//        emailField = (EditText) findViewById(R.id.editEmail);
        urlField = (EditText) findViewById(R.id.editLI);
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


        submitButton = (Button)findViewById(R.id.submit);
        submitButton.setOnClickListener(this);

        //insert records
//        helper.addAnimal(new ContactInfo("tiger", "8888888888", "kchin96@gmail.com", "https://google.com", "1 Main St."));
//        helper.addAnimal(new ContactInfo("tiger", number, url, address));
//        helper.addAnimal(new ContactInfo("zebra", 23));
//        helper.addAnimal(new ContactInfo("buffalo", 13));
//        helper.addAnimal(new ContactInfo("lion", 37));
//        helper.addAnimal(new ContactInfo("yak", 18));


//        //update buffalo to gorilla
//        helper.updateAnimal(new ContactInfo("buffalo"), new ContactInfo("gorilla"));
//
//        //delete record
//        helper.deleteAnimal(new ContactInfo("tiger"));

        //query database
//        ArrayList<ContactInfo> animalList = helper.getContactList();
//
//        //write contents of list to screen
//        for (ContactInfo item : animalList) {
//            text.append(item.getName() + " " + item.getNumber() + " " + item.getUrl() + " " + item.getAddress() + "\n" );
//            text.append(item.getName() + " " + item.getNumber() + " " + item.getEmail() + " " + item.getUrl() + " " + item.getAddress() + "\n" );
//        }

    }

    // Perform action on click
    public void onClick(View v) {
        // create a switch for the four button functionalities
        switch(v.getId()) {

            // activates web functionality upon WEB button click
            case R.id.submit:
                helper.addAnimal(new ContactInfo(nameField.getText().toString(), numberField.getText().toString(), urlField.getText().toString(), addressField.getText().toString()));
//                helper.addAnimal(new ContactInfo(nameField.getText().toString(), numberField.getText().toString(), emailField.getText().toString(), urlField.getText().toString(), addressField.getText().toString()));
                ArrayList<ContactInfo> animalList = helper.getContactList();

                for (ContactInfo item : animalList) {
                    text.append(item.getName() + " " + item.getNumber() + " " + item.getUrl() + " " + item.getAddress() + "\n" );
//            text.append(item.getName() + " " + item.getNumber() + " " + item.getEmail() + " " + item.getUrl() + " " + item.getAddress() + "\n" );
                }

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
