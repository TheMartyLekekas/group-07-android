package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import se.chalmers.cse.dit341.group07.model.Product;

public class ProductScreen extends AppCompatActivity {
    public static final String HTTP_PARAM = "httpResponse";
    String url = "https://webshop-gu-backend.herokuapp.com/api/products";
    RequestQueue MyRequestQueue;
    JsonObjectRequest MyJsonRequest;
    String id;
    String nameForUpdate;
    String descriptionForUpdate;
    String priceForUpdate;
    String sellerForUpdate;
    String categoryForUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final TextView Tname = findViewById(R.id.name_placeholder);
        final TextView Tdescription = findViewById(R.id.description_placeholder);
        final TextView Tprice = findViewById(R.id.price_placeholder);
        final TextView Tseller = findViewById(R.id.seller_placeholder);
        final TextView Tcategory = findViewById(R.id.category_placeholder);
        savedInstanceState = getIntent().getExtras();
        String id = savedInstanceState.getString("ID");
        this.id = id;

        MyRequestQueue = Volley.newRequestQueue(this);
        MyJsonRequest = new JsonObjectRequest(Request.Method.GET, url+"/"+id , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject product = response.getJSONObject("product");

                    String name = product.getString("name");
                    Tname.setText(name);
                    nameForUpdate = name;

                    String description = product.getString("description");
                    Tdescription.setText(description);
                    descriptionForUpdate = description;

                    String price = product.getString("price");
                    Tprice.setText(price);
                    priceForUpdate = price;

                    String seller = product.getString("seller");
                    Tseller.setText(seller);
                    sellerForUpdate = seller;

                    String category = product.getString("category");
                    Tcategory.setText(category);
                    categoryForUpdate = category;
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
        intent.putExtra("id", id);
        intent.putExtra("name", nameForUpdate);
        intent.putExtra("description", descriptionForUpdate);
        intent.putExtra("price", priceForUpdate);
        intent.putExtra("seller", sellerForUpdate);
        intent.putExtra("category", categoryForUpdate);

        final int request_code = 1;
        startActivityForResult(intent, request_code);
    }

    public void onClickDeleteProduct (View view) {
        Intent intent = new Intent(this, DeleteProduct.class);


    }

    public void onClickReviews (View view) {
        Intent intent = new Intent(this, ReviewsScreen.class);
        intent.putExtra("ID", this.id);

        startActivity(intent);
    }

    public void onClickSeller(View view) {
        Intent intent = new Intent(this, SellerScreen.class);
        intent.putExtra("ID", this.id);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent newProduct) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Log.d("EMPTY", "result");
            }
        }
    }
}
