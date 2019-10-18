package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import se.chalmers.cse.dit341.group07.model.Product;

import static java.lang.Math.pow;
import static se.chalmers.cse.dit341.group07.MainActivity.HTTP_PARAM;

public class ProductScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HTTP_PARAM);

    }

    public void onClickUpdateProduct (View view) {
        TextView productView = findViewById(R.id.update_product_btn);

        // Starts a new activity, providing the text from my HTTP text field as an input
        Intent intent = new Intent(this, UpdateProduct.class);
        intent.putExtra(HTTP_PARAM, productView.getText().toString());

        final int request_code = 1;
        startActivityForResult(intent, request_code);
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
