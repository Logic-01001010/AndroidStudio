package com.example.FourButton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.helloandroid.R;


public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setBackgroundColor(Color.GRAY);

        button1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.nate.com"));
                startActivity(mlntent);

            }
        } );


        button2 = (Button) findViewById(R.id.button2);
        button2.setBackgroundColor(Color.GREEN);

        button2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:/911"));
                startActivity(mlntent);

            }
        } );


        button3 = (Button) findViewById(R.id.button3);
        button3.setBackgroundColor(Color.RED);

        button3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent mlntent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"));
                startActivity(mlntent);

            }
        } );


        button4 = (Button) findViewById(R.id.button4);
        button4.setBackgroundColor(Color.RED);

        button4.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){

                finish();

            }
        } );


    }
}