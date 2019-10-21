package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.dit341.group07.model.Product;

public class CreateProduct extends AppCompatActivity {
    DatabaseHelper myDb;

    String url = "https://webshop-gu-backend.herokuapp.com/api/products";

    private void addProduct(String product){
        Log.i("WATCH HERE", "HERE!");
        Log.i("PRODUKT", product);
        //myDb.addProduct(product);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
    }

    public void onClickPublishProduct(View Button) {
//        TextView productView = findViewById(R.id.publish_product_btn);
        final EditText nameField = findViewById(R.id.input_name);
        final EditText descriptionField = findViewById(R.id.input_description);
        final EditText priceField = findViewById(R.id.input_price);
        final Spinner categorySpinner = findViewById(R.id.spinner_category_type);
        final EditText sellerField = findViewById(R.id.input_seller);

        if( TextUtils.isEmpty(nameField.getText())){
            nameField.setError( "Please fill in the name" );

        }else if( TextUtils.isEmpty(descriptionField.getText())){
            descriptionField.setError( "Please write the description" );

        }else if( TextUtils.isEmpty(priceField.getText())){
            priceField.setError( "Please specify the price" );

        }else{
            String name = nameField.getText().toString();
            String description = descriptionField.getText().toString();
            String value = priceField.getText().toString();
            int price = Integer.parseInt(value);
            String category = categorySpinner.getSelectedItem().toString();
            String seller = sellerField.getText().toString();

            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

            JSONObject parameters = new JSONObject();
            try {
                JSONObject categoryParameter = new JSONObject();
                categoryParameter.put("name",category);

                JSONObject sellerJSON = new JSONObject();
                sellerJSON.put("name",seller);
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
            JsonObjectRequest MyJsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    addProduct(response.toString());
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });

            MyRequestQueue.add(MyJsonRequest);

            /*Intent resultIntent = new Intent();
            resultIntent.putExtra("passedProduct", newProduct);
            setResult(RESULT_OK, resultIntent);*/
            finish();
        }
    }
}