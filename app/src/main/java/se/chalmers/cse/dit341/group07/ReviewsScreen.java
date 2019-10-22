package se.chalmers.cse.dit341.group07;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import se.chalmers.cse.dit341.group07.model.Review;


public class ReviewsScreen extends AppCompatActivity {

    // Field for parameter name
    private ArrayList<Review> reviews;
    String url = "https://webshop-gu-backend.herokuapp.com/api/products/";
    RequestQueue MyRequestQueue;
    JsonObjectRequest MyJsonRequest;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.reviews = new ArrayList<>();
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_review);

        savedInstanceState=getIntent().getExtras();
        this.id=savedInstanceState.getString("ID");

        MyRequestQueue = Volley.newRequestQueue(this);
        Log.d("URI", url + this.id + "/reviews");
        MyJsonRequest = new JsonObjectRequest(Request.Method.GET, url + this.id + "/reviews", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray reviewsArray = response.getJSONArray("reviews");
                    Log.d("REVIEWSARRAY", reviewsArray.toString());



                    for(int i = 0; i < reviewsArray.length(); i++) {
                        JSONObject review = reviewsArray.getJSONObject(i);

                        String id = review.getString("_id");
                        String text= review.getString("text");
                        String date= review.getString("date");
                        int rating= review.getInt("rating");

                        Log.d("VALUES", id + text + date + rating);

                        Review r = new Review (id, text, date, rating);

                        reviews.add(r);
                    }

                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                setupAdapter(reviews);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MyRequestQueue.add(MyJsonRequest);

        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getOrientation();
        switch(orientation) {
        case Configuration.ORIENTATION_PORTRAIT:
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        break;
        case Configuration.ORIENTATION_LANDSCAPE:
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        break;
        }

    }

    private void setupAdapter(ArrayList<Review> reviews) {
        // Create an ProductAdapter, whose data source is a list of Products
        ReviewAdapter newAdapter = new ReviewAdapter(this, reviews);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_reviews);
        listView.setAdapter(newAdapter);
    }
}
