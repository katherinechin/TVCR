/*
 * TVCR • HOME PAGE
 * By Boxin Cao, Katherine Chin, & Shahzeb Khurshid
 * Created in April 2020
 * CS680 Pepe • Final Project
 *
 * Utilizing smartphones’ NFC readers, The Virtual Card Reader (TVCR) collects data
 * from identification cards embedded with NFC tags, and the data is populated into
 * a contacts list. The goal is to provide a simple, efficient way for professionals
 * to exchange contact information.
 *
 * The home page displays a ListView  of all contact names. The menu bar has options
 * for searching contacts and adding contacts.
 *
 */

package com.example.tvcr;

// TODO remove unused imports

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import java.util.Locale;

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener, OnInitListener {

    private String file = "names.txt"; // file that contains the names of each contact
    private ListView list; // list of all contact names
    // TODO list the names in alphabetical order

    ArrayAdapter<String> adapter; // defining a string adapter that handles ListView data
    ArrayList<String> listItems = new ArrayList<>(); // list of array strings as list items
    private OutputStreamWriter out; // writes stream of data

    private TextToSpeech speaker; // speaker for Text to Speech
    private static final String tag = "Speech"; // tag for debugging Text to Speech

    private int thisPosition; // global variable to hold the position of the clicked list item

    private SQLHelper helper;

//    private NotificationManager mNotificationManager;
//    private NotificationCompat.Builder notifyDetails = null;
//    private int SIMPLE_NOTFICATION_ID = 1;
//    private String contentTitle = "Email Notification";
//    private String contentText = "Get to Email by clicking me";

    // creation method upon initiation of the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this); // attach listener to list items

        adapter = new ArrayAdapter<>(this, R.layout.list_row, R.id.textView, listItems); // use custom layout for ListView items
        list.setAdapter(adapter);    //connect ArrayAdapter to <ListView>

        helper = new SQLHelper(this);

        //Initialize Text to Speech engine
        speaker = new TextToSpeech(this, this);

        if (file.equals("names.txt")) { //initialize names.txt contents if it exists

        write(); // writes to file

            try {
                //open stream for reading from file
                InputStream in = openFileInput(file);
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isr);
                String str;

                while ((str = reader.readLine()) != null) {
                    listItems.add(str); // combine list item number with input
                    adapter.notifyDataSetChanged();
                }

                reader.close();

            } catch (IOException e) {
                Log.e("FILE", e.getMessage());
            }

        } else { // if file does not exist, do not open stream

            listItems.clear();
            adapter.notifyDataSetChanged();

        }
//
//        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        //As of API 26 Notification Channels must be assigned to a channel
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel("default",
//                    "Channel foobar",
//                    NotificationManager.IMPORTANCE_HIGH);
//            channel.setDescription("Channel description");
//            channel.setLightColor(Color.GREEN);
//            mNotificationManager.createNotificationChannel(channel);
//        }
//
//        //create implicit intent for action when notification selected
//        //from expanded notification screen
//        //open email when notification clicked
//        Intent notifyIntent = new Intent(this, Home.class);
//
//        //the pending intent will outlive this app
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 9, notifyIntent,0 );
//
//        //set icon, text, and time on notification status bar
//        notifyDetails = new NotificationCompat.Builder(this, "default")
//                .setContentIntent(pendingIntent)
//                .setContentTitle(contentTitle)
//                .setContentText(contentText)
//                .setAutoCancel(true)     //cancel Notification after clicking on it
//                //set Android to vibrate when notified
//                .setVibrate(new long[] {1000, 1000, 2000, 2000});
//
//        list.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                //notify() in response to button click.
//                mNotificationManager.notify(SIMPLE_NOTFICATION_ID,
//                        notifyDetails.build());
//
//            }
//        });

    }

    // speak methods will send text to be spoken
    public void speak(String output){
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }

    // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // If a language is not be available, the result will indicate it.
            int result = speaker.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
                Log.e(tag, "Language is not available.");
            } else {
                // The TTS engine has been successfully initialized
                Log.i(tag, "TTS Initialization successful.");
            }
        } else {
            // Initialization failed.
            Log.e(tag, "Could not initialize TextToSpeech.");
        }
    }

    // writes to file
    public void write() {

        try {
            //open output stream
            out = new OutputStreamWriter(openFileOutput(file, MODE_PRIVATE));

            // demo of contact names - remove later to use real contact info
//            out.write("Abby Bates" + "\n");
//            out.write("Carol Porter" + "\n");
//            out.write("Darren Hult" + "\n");
//            out.write("Fred Wu" + "\n");
//            out.write("John Smith" + "\n");
//            out.write("Karen Lutz" + "\n");
//            out.write("Laura Tate" + "\n");
//            out.write("Mandy Gold" + "\n");
//            out.write("Paula Wright" + "\n");
//            out.write("Sarah Abbott" + "\n");
//            out.write("Tim Adams" + "\n");

            //query database
            ArrayList<ContactInfo> contactList = helper.getContactList();

            // write each contact name as a list item
            for (ContactInfo item : contactList) {
                String name = item.getName();
                out.write(name + "\n");
            }


            out.close();

        } catch (IOException e) {
            Log.e("WRITE", e.getMessage());
        }

    }

    // on destroy for speaker
    public void onDestroy(){

        // shut down TTS engine
        if(speaker != null){
            speaker.stop();
            speaker.shutdown();
        }
        super.onDestroy();
    }

    // when list item is clicked
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        thisPosition = position; // variable to store position number so it can still be accessed outside the class
        try { // speaker when adding
            Log.i(tag, "Add - TTS invoked.");

            // if speaker is talking, stop it
            if(speaker.isSpeaking()){
                Log.i(tag, "Speaker Speaking");
                speaker.stop();
                // else start speech
            } else {
                Log.i(tag, "Speaker Not Already Speaking");
                speak(listItems.get(thisPosition));
            }

        } catch (Exception e) {
            Log.e(tag, "Speaker failure" + e.getMessage());
        }

    }

    // initialize options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // add contact or search for contact when clicked in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            // TODO create search function (Boxin)
            // activates add contact information functionality upon ADD menu button click
            case R.id.add:
                Intent i1 = new Intent(this, Add.class);
                startActivity(i1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
