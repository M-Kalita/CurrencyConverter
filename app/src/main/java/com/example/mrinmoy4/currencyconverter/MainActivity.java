package com.example.mrinmoy4.currencyconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.gravity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ruptodol(View view){
        Intent intent = new Intent(this,RUPEE_TO_DOLLAR.class);
        startActivity(intent);
    }
    public void doltorup(View view){
        Intent intent = new Intent(this,DOLLAR_TO_RUPEE.class);
        startActivity(intent);
    }

}
