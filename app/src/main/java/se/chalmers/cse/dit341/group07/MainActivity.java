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
import android.view.Menu;
import android.view.Display;
import android.view.WindowManager;

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



public class MainActivity extends AppCompatActivity implements Serializable {

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent selectedProduct = new Intent(MainActivity.this, ProductScreen.class);
                startActivity(selectedProduct);
            }
        });

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
