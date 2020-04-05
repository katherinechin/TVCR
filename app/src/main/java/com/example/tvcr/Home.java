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

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextClock;
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

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String file = "names.txt";
    private ListView list;

    ArrayAdapter<String> adapter; // defining a string adapter that handles ListView data
    ArrayList<String> listItems = new ArrayList<>(); // list of array strings as list items
    private OutputStreamWriter out;
    private int storePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this); // attach listener to list items

        adapter = new ArrayAdapter<>(this, R.layout.list_row, R.id.textView, listItems); // use custom layout for ListView items
        list.setAdapter(adapter);    //connect ArrayAdapter to <ListView>

        if (file.equals("names.txt")) { //initialize names.txt contents if it exists

        write();

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

    // writes to file
    public void write() {

        try {
            //open output stream
            out = new OutputStreamWriter(openFileOutput(file, MODE_PRIVATE));

            // write each task item to the file
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

//            for (int i = 0; i < 1; i++) {
//                out.write("Abby" + "\n");
//                listItems.add("Abby"); // combine list item number with input
//            }

            out.close();

        } catch (IOException e) {
            Log.e("WRITE", e.getMessage());
        }

    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        storePosition = position; // variable to store position number so it can still be accessed outside the class
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
//            case R.id.save: // save list items to file
//
//                //write();
//
//                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
