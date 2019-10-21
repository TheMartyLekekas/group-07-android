package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import se.chalmers.cse.dit341.group07.model.DeleteProduct;
import se.chalmers.cse.dit341.group07.model.Product;

import static java.lang.Math.pow;
import static se.chalmers.cse.dit341.group07.MainActivity.HTTP_PARAM;

public class ProductScreen extends AppCompatActivity {
    public static final String HTTP_PARAM = "httpResponse";
    String url = "https://webshop-gu-backend.herokuapp.com/api/products";
    RequestQueue MyRequestQueue;
    JsonObjectRequest MyJsonRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final ImageView icon = findViewById(R.id.list_item_icon);
        final TextView Tname = findViewById(R.id.name_placeholder);
        final TextView Tdescription = findViewById(R.id.description_placeholder);
        final TextView Tprice = findViewById(R.id.price_placeholder);
        savedInstanceState=getIntent().getExtras();
        String id=savedInstanceState.getString("ID");

        MyRequestQueue=Volley.newRequestQueue(this);
        MyJsonRequest = new JsonObjectRequest(Request.Method.GET, url+"/"+id , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject product = response.getJSONObject("product");

                    String name= product.getString("name");
                    Tname.setText(name);
                    String description= product.getString("description");
                    Tdescription.setText(description);
                    String price= product.getString("price");
                    Tprice.setText(price);
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
    }

    public void onClickUpdateProduct (View view) {
        TextView productView = findViewById(R.id.update_product_btn);

        // Starts a new activity, providing the text from my HTTP text field as an input
        Intent intent = new Intent(this, UpdateProduct.class);
        intent.putExtra(HTTP_PARAM, productView.getText().toString());

        final int request_code = 1;
        startActivityForResult(intent, request_code);
    }

    public void onClickDeleteProduct (View view) {
        Intent intent = new Intent(this, DeleteProduct.class);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent newProduct) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Product neProduct = (Product) newProduct.getSerializableExtra("updatedProduct");
//                products.add(neProduct);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //code if there's no result
            }
        }
    }

}
