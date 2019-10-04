package com.example.a5thassignment_http;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/*
* Create a user interface which consists of a ListView which covers the whole screen and button at
* lower right-corner of the screen with a text "GET" and import the Volley library somehow into
* your project.

If internet connection is not available, pressing the GET-button produces only a notification
* text made with a Toast noting the user that connection is not available.

When GET-button is pressed and internet connection is available a http get request is made to
*  https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1 and the response is
* written into the ListView. Pressing GET-button again overwrites the text with the new response.

In the ListView where one row corresponds to one object in the list, so that in "pvm"-attribute no
* time is displayed, only date.

Try to find a way to deserialize, the response (parse/convert) into a list of your own custom
* object class corresponding to the data in the response, and link that list to the ListView.
* For example ArrayList<MyObject> and not ArrayList<String>.
* */

public class MainActivity extends AppCompatActivity {
RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = new RequestQueue();
    }
}
