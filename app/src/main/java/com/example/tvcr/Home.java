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

//import androidx.appcompat.app.ActionBar;

//import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    // creation method upon initiation of the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this); // attach listener to list items

        adapter = new ArrayAdapter<>(this, R.layout.list_row, R.id.textView, listItems); // use custom layout for ListView items
        list.setAdapter(adapter);    //connect ArrayAdapter to <ListView>

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

            // write each contact name to the file
            // TODO the following is a demo of contact names - remove later to use real contact info
            out.write("Abby Bates" + "\n");
            out.write("Carol Porter" + "\n");
            out.write("Darren Hult" + "\n");
            out.write("Fred Wu" + "\n");
            out.write("John Smith" + "\n");
            out.write("Karen Lutz" + "\n");
            out.write("Laura Tate" + "\n");
            out.write("Mandy Gold" + "\n");
            out.write("Paula Wright" + "\n");
            out.write("Sarah Abbott" + "\n");
            out.write("Tim Adams" + "\n");

            // will need to change implementation when we import contact info
//            for (int i = 0; i < 1; i++) {
//                out.write("Abby" + "\n");
//                listItems.add("Abby"); // combine list item number with input
//            }

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
            // TODO create add and search functions
            // will need to add implementation when we import contact info
//            case R.id.add: // save list items to file
//
//                //write();
//
//                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
