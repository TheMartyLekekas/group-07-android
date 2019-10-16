package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import android.view.View;
import android.widget.TextView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import se.chalmers.cse.dit341.group07.model.Product;
import se.chalmers.cse.dit341.group07.model.User;
import se.chalmers.cse.dit341.group07.model.Seller;
import se.chalmers.cse.dit341.group07.model.Category;
import se.chalmers.cse.dit341.group07.model.Review;
import se.chalmers.cse.dit341.group07.model.PaymentData;


public class StartScreen extends AppCompatActivity {

    // Field for parameter name
    public static final String HTTP_PARAM = "httpResponse";
    ArrayList<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        products.add(new Product("Young pup", 200, R.drawable.puppy));
        products.add(new Product("Good wheel", 33, R.drawable.flat_tire));
        products.add(new Product("Human Resources", 10000, R.drawable.kid));
        products.add(new Product("My Soul", 8, R.drawable.soul));

        // Create an ProductAdapter, whose data source is a list of Products
        ProductAdapter newAdapter = new ProductAdapter(this, products);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_product);
        listView.setAdapter(newAdapter);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
//                String result = data.getStringExtra("result");
                products.add(new Product("Young pup", 200, R.drawable.puppy));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }



    public void addProduct(String name, int price, int image) {
        products.add(new Product( name, price, R.drawable.puppy));
    }

}
