package se.chalmers.cse.dit341.group07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UpdateProduct extends AppCompatActivity {

    public static final String name="name";
    String url = "https://webshop-gu-backend.herokuapp.com/api/products";
    String id;
    String oldName;
    String oldDescription;
    String oldPrice;
    String oldSeller;

    private void renewProduct(String product) {
        Log.i("WATCH HERE", "HERE!");
        Log.i("PRODUCT", product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        id = getIntent().getStringExtra("id");
        oldName = getIntent().getStringExtra("name");
        oldDescription = getIntent().getStringExtra("description");
        oldPrice = getIntent().getStringExtra("price");
        oldSeller = getIntent().getStringExtra("seller");
    }

    public void onClickUpdateProduct(View Button) {

        final EditText nameField = findViewById(R.id.input_name);
        final EditText descriptionField = findViewById(R.id.input_description);
        final EditText priceField = findViewById(R.id.input_price);
        final EditText sellerField = findViewById(R.id.input_seller);
        final Spinner categorySpinner = findViewById(R.id.spinner_category_type);

        String name;
        String description;
        String value;
        String seller;
        String category;
        boolean patch = false;
        JSONObject parameters = new JSONObject();

        try {
            if( TextUtils.isEmpty(nameField.getText())){
//                name = oldName;
                patch = true;
            } else {
                name = nameField.getText().toString();
                parameters.put("name", name); }

            if( TextUtils.isEmpty(descriptionField.getText())){
//                description = oldDescription;
                patch = true;
            } else {
                description = descriptionField.getText().toString();
                parameters.put("description", description); }

            if( TextUtils.isEmpty(priceField.getText())){
//                value = oldPrice;
                patch = true;
            } else {
                value = priceField.getText().toString();
                int price = Integer.parseInt(value);
                parameters.put("price", price);
            }

            category = categorySpinner.getSelectedItem().toString();
            if(category.equals("Select a category")){
                patch = true;
            }
            else {
                JSONObject categoryParameter = new JSONObject();
//            categoryParameter.put("name", "Ele");
                categoryParameter.put("name", category);
                parameters.put("category", categoryParameter);
            }

            if( TextUtils.isEmpty(sellerField.getText())){
//                seller = oldSeller;
                patch = true;
            } else {
                seller = sellerField.getText().toString();
                JSONObject sellerJSON = new JSONObject();
                sellerJSON.put("name", seller);
                JSONArray sellerParameter = new JSONArray();
                sellerParameter.put(sellerJSON);
                parameters.put("sellers", sellerParameter);
            }

            Intent resultIntent = new Intent();
            //resultIntent.putExtra("updatedProduct", newProduct);
            setResult(RESULT_OK, resultIntent);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

            Log.d("PARAMS", parameters.toString());

            if (patch){

                JsonObjectRequest MyJsonRequest = new JsonObjectRequest(Request.Method.PATCH, url + "/" + id, parameters, new Response.Listener<JSONObject>() {

            @Override
                public void onResponse(JSONObject response) {
                    renewProduct(response.toString());
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            MyRequestQueue.add(MyJsonRequest);

            } else{
                JsonObjectRequest MyJsonRequest = new JsonObjectRequest(Request.Method.PUT, url + "/" + id, parameters, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        renewProduct(response.toString());
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                MyRequestQueue.add(MyJsonRequest);
            }
        finish();

    }
}