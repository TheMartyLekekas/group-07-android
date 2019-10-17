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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import se.chalmers.cse.dit341.group07.model.Product;

public class CreateProduct extends AppCompatActivity {
    DatabaseHelper myDb;
    RequestQueue MyRequestQueue = Volley.newRequestQueue(this);

    String url = "http://localhost:3000/api/products";

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
        String name;
        String description = "";
        String value;
        int price;
        String category = "";

        if( TextUtils.isEmpty(nameField.getText())){
            nameField.setError( "Please fill in the name" );

        }else if( TextUtils.isEmpty(descriptionField.getText())){
            descriptionField.setError( "Please write the description" );

        }else if( TextUtils.isEmpty(priceField.getText())){
            priceField.setError( "Please specify the price" );

        }else{
            name = nameField.getText().toString();
            description = descriptionField.getText().toString();
            value = priceField.getText().toString();
            price = Integer.parseInt(value);
            category = categorySpinner.getSelectedItem().toString();

            int fakeImageId = 0;

            Product newProduct = new Product(name, description);

            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    addProduct(response);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("name", newProduct.getName()); //Add the data you'd like to send to the server.
                    MyData.put("description", newProduct.getDescription());
                    return MyData;
                }
            };

            MyRequestQueue.add(MyStringRequest);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("passedProduct", newProduct);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}