package com.example.simplecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1;
    EditText text2;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText) findViewById( R.id.text1 );
        text2 = (EditText) findViewById( R.id.text2 );
        btn1 = (Button) findViewById( R.id.btn1 ); // 더하기
        btn2 = (Button) findViewById( R.id.btn2 ); // 빼기
        btn3 = (Button) findViewById( R.id.btn3 ); // 곱하기
        btn4 = (Button) findViewById( R.id.btn4 ); // 나누기
        btn5 = (Button) findViewById( R.id.btn5 ); // 나머지
        txtView = (TextView) findViewById( R.id.txtView );


        // 더하기
        btn1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String num1 = text1.getText().toString();
                String num2 = text2.getText().toString();

                if( num1.equals("") || num2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill the Edit Text!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Float result = Float.parseFloat(num1) + Float.parseFloat(num2);
                    txtView.setText(result.toString());
                }

            }
        } );


        // 빼기
        btn2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String num1 = text1.getText().toString();
                String num2 = text2.getText().toString();

                if( num1.equals("") || num2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill the Edit Text!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Float result = Float.parseFloat(num1) - Float.parseFloat(num2);
                    txtView.setText(result.toString());
                }

            }
        } );


        // 곱하기
        btn3.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String num1 = text1.getText().toString();
                String num2 = text2.getText().toString();

                if( num1.equals("") || num2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill the Edit Text!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Float result = Float.parseFloat(num1) * Float.parseFloat(num2);
                    txtView.setText(result.toString());
                }

            }
        } );

        // 나누기
        btn4.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String num1 = text1.getText().toString();
                String num2 = text2.getText().toString();

                if( num1.equals("") || num2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill the Edit Text!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Float result = Float.parseFloat(num1) / Float.parseFloat(num2);
                    txtView.setText(result.toString());
                }

            }
        } );



        // 나머지
        btn5.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View V){
                String num1 = text1.getText().toString();
                String num2 = text2.getText().toString();

                if( num1.equals("") || num2.equals("") ){
                    Toast.makeText(getApplicationContext(), "Fill the Edit Text!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Float result = Float.parseFloat(num1) % Float.parseFloat(num2);
                    txtView.setText(result.toString());
                }

            }
        } );


    }
}