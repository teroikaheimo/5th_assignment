package com.example.a5thassignment_http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    StringRequest stringRequest;
    String url;
    TextView textView;
    Button buttonGet;
    ConnectivityManager connMan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Views
        textView = findViewById(R.id.textView);
        buttonGet = findViewById(R.id.button);
        buttonGet.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                addGetRequestToQueue();
            }
        });

        connMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        url = "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";
        requestQueue = Volley.newRequestQueue(this);
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Query failed...\n\r" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addGetRequestToQueue() {
        requestQueue.add(stringRequest);
    }
}
