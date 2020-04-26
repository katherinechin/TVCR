/*
 * TVCR • CONTACT PAGE
 * By Boxin Cao, Katherine Chin, & Shahzeb Khurshid
 * Created in April 2020
 * CS680 Pepe • Final Project
 *
 * Utilizing smartphones’ NFC readers, The Virtual Card Reader (TVCR) collects data
 * from identification cards embedded with NFC tags, and the data is populated into
 * a contacts list. The goal is to provide a simple, efficient way for professionals
 * to exchange contact information.
 *
 *
 *
 */

package com.example.tvcr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class Contact extends AppCompatActivity {
    Uri info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);


        ActionBar actionBar = getSupportActionBar();          //create ActionBar object
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        ImageView imageView = (ImageView)findViewById(R.id.image);
        //TODO
//        imageView.setImageResource(R.drawable.person);

        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);
        Button button3 = findViewById(R.id.button4);
        Button button4 = findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = Uri.parse("tel:7812668279");
                startActivity(new Intent(Intent.ACTION_VIEW, info));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = Uri.parse("mailto:cao_boxi@bentley.edu");
                startActivity(new Intent(Intent.ACTION_VIEW, info));

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = Uri.parse("https://www.linkedin.com/in/boxincao/");
                startActivity(new Intent(Intent.ACTION_VIEW, info));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = Uri.parse("geo:0,0?q=175 Forest Street waltham ma");
                Intent intent = new Intent(Intent.ACTION_VIEW, info);
                startActivity(intent);
            }
        });
    }
}

