package com.example.plausibleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    EditText editText;
    RadioButton radioBtn1;
    RadioButton radioBtn2;
    ImageView imgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        editText = (EditText) findViewById(R.id.editText);
        radioBtn1 = (RadioButton) findViewById(R.id.radioBtn1);
        radioBtn2 = (RadioButton) findViewById(R.id.radioBtn2);
        imgView = (ImageView) findViewById(R.id.imgView);

        btn1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Editable str = editText.getText();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Editable str = editText.getText();
                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse(str.toString()));
                startActivity(mlntent);
            }
        });


        radioBtn1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                imgView.setImageResource( R.drawable.calamus );


            }
        });

        radioBtn2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                imgView.setImageResource( R.drawable.client );
            }
        });

    }
}