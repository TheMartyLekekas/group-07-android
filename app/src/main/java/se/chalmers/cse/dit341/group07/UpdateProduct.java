package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import se.chalmers.cse.dit341.group07.model.Product;

public class UpdateProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
    }

    public void onClickUpdateProduct(View Button) {
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
            Product newProduct = new Product(name, price, R.drawable.puppy);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("updatedProduct", newProduct);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}