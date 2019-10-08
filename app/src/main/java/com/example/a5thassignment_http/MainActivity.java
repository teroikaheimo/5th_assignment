package com.example.a5thassignment_http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

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
    // Views
    Button buttonGet;
    ListView listView;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    // Request HTTP
    RequestQueue requestQueue;
    JsonArrayRequest jsonArrayRequest;
    String url;

    // Internet connection status
    ConnectivityManager connMan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        listView = findViewById(R.id.listView);
        buttonGet = findViewById(R.id.button);

        // Button functionality
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGetRequestToQueue();
            }
        });

        // Get connection manager for internet connection status check.
        connMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Prepare listView
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        //// Request
        // The endpoint returns JSON array
        url = "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";
        requestQueue = Volley.newRequestQueue(this);

        jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    // TODO checkout Gson. Googles own tool.
                    @Override
                    public void onResponse(JSONArray response) {
                        // Clear the list before new values are fetched.
                        arrayList.clear();

                        // Loop through the array and get a substring from the pvm attribute that
                        // contains only the time part of the timestamp.
                        for (Integer i = 0; i < response.length(); i++) {
                            try {
                                JSONObject row = response.getJSONObject(i);
                                arrayAdapter.add(row.getString("pvm").substring(11));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        arrayAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Query failed...", Toast.LENGTH_LONG).show();

                    }
                });
    }

    private void addGetRequestToQueue() {
        // IF there is no internet connection. Prompt user and don't add query to queue.
        if (!this.checkInternetConnection()) {
            Toast.makeText(getApplicationContext(), "Internet connection not available!", Toast.LENGTH_LONG).show();
            return;
        }
        requestQueue.add(jsonArrayRequest);
    }

    private boolean checkInternetConnection() {
        // Return true IF there is a active network with internet connection.
        NetworkInfo network = connMan.getActiveNetworkInfo();
        return network != null && network.isConnectedOrConnecting();
    }
}
