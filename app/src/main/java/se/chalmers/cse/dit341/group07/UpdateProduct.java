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
//        oldSeller = getIntent().getStringExtra("seller");
    }

    public void onClickUpdateProduct(View Button) {

        final EditText nameField = findViewById(R.id.input_name);
        final EditText descriptionField = findViewById(R.id.input_description);
        final EditText priceField = findViewById(R.id.input_price);
        final Spinner categorySpinner = findViewById(R.id.spinner_category_type);
        final EditText sellerField = findViewById(R.id.input_seller);

        String name;
        String description;
        String value;
        String seller;
        boolean patch = false;

        if( TextUtils.isEmpty(nameField.getText())){
            name = oldName;
            patch = true;
        } else name = nameField.getText().toString();

        if( TextUtils.isEmpty(descriptionField.getText())){
            description = oldDescription;
            patch = true;
        } else description = descriptionField.getText().toString();

        if( TextUtils.isEmpty(priceField.getText())){
            value = oldPrice;
            patch = true;
        } else value = priceField.getText().toString();

        if( TextUtils.isEmpty(sellerField.getText())){
//            seller = oldSeller;
            patch = true;
        } else seller = sellerField.getText().toString();


            Intent resultIntent = new Intent();
            //resultIntent.putExtra("updatedProduct", newProduct);
            setResult(RESULT_OK, resultIntent);


            int price = Integer.parseInt(value);
            String category = categorySpinner.getSelectedItem().toString();
//            String seller = sellerField.getText().toString();

            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

            JSONObject parameters = new JSONObject();
            try {
                JSONObject categoryParameter = new JSONObject();
                categoryParameter.put("name", "Ele");
                categoryParameter.put("name", category);
                JSONObject sellerJSON = new JSONObject();
//                sellerJSON.put("name", seller);
                JSONArray sellerParameter = new JSONArray();
                sellerParameter.put(sellerJSON);

                parameters.put("name", name);
                parameters.put("description", description);
                parameters.put("price", price);
                parameters.put("category", categoryParameter);
                parameters.put("sellers", sellerParameter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("PARAMS", parameters.toString());

            if (patch){
                System.out.println("**********************");
                System.out.println("PATCH PATCH PACK");
                System.out.println("******************************");

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
                System.out.println("**********************");
                System.out.println("PUT PUT PUTPELE");
                System.out.println("******************************");
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