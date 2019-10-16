package se.chalmers.cse.dit341.group07;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import se.chalmers.cse.dit341.group07.model.Product;

public class CreateProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

//        Intent intent = getIntent();
//        String message = intent.getStringExtra(StartScreen.HTTP_PARAM);

//        TextView text = findViewById(R.id.displayTextView);
//        text.setText("Text from my main activity: " + message);
    }

    public void onClickPublishProduct(View Button) {
//        TextView productView = findViewById(R.id.publish_product_btn);

        final EditText nameField = findViewById(R.id.input_name);
        String name = nameField.getText().toString();

        final EditText descriptionField = findViewById(R.id.input_description);
        String description = descriptionField.getText().toString();

        final EditText priceField = findViewById(R.id.input_price);
//        priceField.setInputType(InputType.TYPE_CLASS_NUMBER);
        String value = priceField.getText().toString();
        int price = Integer.parseInt(value);

        final Spinner categorySpinner = findViewById(R.id.spinner_category_type);
        String category = categorySpinner.getSelectedItem().toString();

        final int request_code = 1;
//        finishActivity(request_code);


        Product newProduct = new Product(name, price, price);
//        StartScreen startScreen = new StartScreen();
//        startScreen.addProduct(name, price, price);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", newProduct);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}