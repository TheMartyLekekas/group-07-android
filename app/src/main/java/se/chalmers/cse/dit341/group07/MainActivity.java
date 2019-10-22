package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.view.Display;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import se.chalmers.cse.dit341.group07.model.Seller;
import se.chalmers.cse.dit341.group07.model.Product;
import se.chalmers.cse.dit341.group07.model.Review;


public class MainActivity extends AppCompatActivity implements Serializable {

    public static final String name="name";
    // Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";
    public static final ArrayList<Product> products = new ArrayList<>();
    String url = "https://webshop-gu-backend.herokuapp.com/api/products";
    RequestQueue MyRequestQueue;
    JsonObjectRequest MyJsonRequest;

    @Override
    protected void onResume() {
        super.onResume();
        products.removeAll(products);
        MyRequestQueue = Volley.newRequestQueue(this);

        MyJsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray productsArray = response.getJSONArray("products");
                    Log.d("ARARY", productsArray.toString());

                    for(int i = 0; i < productsArray.length(); i++) {
                        JSONObject product = productsArray.getJSONObject(i);

                        String id= product.getString("_id");
                        String name= product.getString("name");
                        String description= product.getString("description");
                        int price= product.getInt("price");
                        String category= product.getJSONObject("category").getString("name");
                        //String seller= product.getJSONObject("seller").getString("name");

                        Product p = new Product (id, name, description, price, category, "");

                        products.add(p);
                    }

                }
                catch(Exception e) {

                }
                setupAdapter(products);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MyRequestQueue.add(MyJsonRequest);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void setupAdapter(ArrayList<Product> products) {
        // Create an ProductAdapter, whose data source is a list of Products
        ProductAdapter newAdapter = new ProductAdapter(this, products);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_product);
        listView.setAdapter(newAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(getApplicationContext(),"You clicked"+i,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ProductScreen.class);
                Product product= products.get(i);
                Toast.makeText(getApplicationContext(),"ID: "+product.getId(),Toast.LENGTH_LONG).show();
                intent.putExtra("ID", product.getId());
                startActivity(intent);

                /*Product product= products.get(i);
                product.getId();
                Toast.makeText(getApplicationContext(),"ID: "+product.getId(),Toast.LENGTH_LONG).show();
                Intent selectedProduct = new Intent(MainActivity.this, ProductScreen.class);
                selectedProduct.putExtra("ID", product.getId());
                //selectedProduct.putExtra("photo", product.getImageResourceId());
                selectedProduct.putExtra("name", product.getName());
                selectedProduct.putExtra("description", product.getDescription());
                selectedProduct.putExtra("price", product.getPrice());
                startActivity(selectedProduct);
                 */
            }
        });
    }

    public void onClickCreateProduct (View view) {
        TextView productView = findViewById(R.id.create_product_btn);

        // Starts a new activity, providing the text from my HTTP text field as an input
        Intent intent = new Intent(this, CreateProduct.class);
        intent.putExtra(HTTP_PARAM, productView.getText().toString());

        final int request_code = 1;
        startActivityForResult(intent, request_code);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent newProduct) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Product neProduct = (Product) newProduct.getSerializableExtra("passedProduct");
                products.add(neProduct);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //code if there's no result
            }

        } else if(requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                Product updatedProduct = (Product) newProduct.getSerializableExtra("passedProduct");
                products.add(updatedProduct);
            }
        }
    }


    // Check screen orientation or screen rotate event here
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
