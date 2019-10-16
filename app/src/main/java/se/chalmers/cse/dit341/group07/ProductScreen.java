package se.chalmers.cse.dit341.group07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(StartScreen.HTTP_PARAM);

        TextView text = findViewById(R.id.displayTextView);
        text.setText("Text from my main activity: " + message);
    }
}