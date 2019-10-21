package se.chalmers.cse.dit341.group07.model;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.callback.Callback;

import se.chalmers.cse.dit341.group07.R;
import se.chalmers.cse.dit341.group07.model.Product;


public class DeleteProduct extends AppCompatActivity {

    String url = "https://webshop-gu-backend.herokuapp.com/api/products/id";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void onClickDeleteProduct(View Button){

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        final JSONObject product = new JSONObject();

        JsonObjectRequest MyJsonRequest = new JsonObjectRequest(Request.Method.DELETE, url,product, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("onResponse", response.toString());
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MyRequestQueue.add(MyJsonRequest);
        finish();

        }











}
