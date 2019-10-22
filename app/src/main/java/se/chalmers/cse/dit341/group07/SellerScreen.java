package se.chalmers.cse.dit341.group07;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import se.chalmers.cse.dit341.group07.model.Review;
import se.chalmers.cse.dit341.group07.model.Seller;

public class SellerScreen extends AppCompatActivity {
    String url = "https://webshop-gu-backend.herokuapp.com/api/products/";
    RequestQueue MyRequestQueue;
    JsonObjectRequest MyJsonRequest;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_seller);

        final TextView Sname = findViewById(R.id.seller_name);
        //final TextView PID = findViewById(R.id.product_id);
        savedInstanceState=getIntent().getExtras();
        this.id=savedInstanceState.getString("ID");

       MyRequestQueue = Volley.newRequestQueue(this);
        Log.d("URI", url + this.id + "/seller");
        MyJsonRequest = new JsonObjectRequest(Request.Method.GET, url + this.id + "/sellers" , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray sellersArray = response.getJSONArray("sellers");
                    JSONObject seller = sellersArray.getJSONObject(0);

                    String name= seller.getString("name");
                    Sname.setText(name);
                }
                catch(Exception e) {

                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MyRequestQueue.add(MyJsonRequest);

        /*Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getOrientation();
        switch(orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }*/

    }
}
