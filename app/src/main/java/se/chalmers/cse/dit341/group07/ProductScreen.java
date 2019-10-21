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

}
