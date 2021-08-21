package com.example.shell;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        editText = (EditText) findViewById(R.id.editText);




        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) ){


                    String cmd = editText.getText().toString();
                    RunCommand( cmd );
                    editText.setText(null);


                }
                else
                    return false;

                return true;

            }
        });






    }

    public void RunCommand(String cmd){

        java.lang.Process process = null;
        InputStreamReader inputReader = null;
        BufferedReader bufferedReader = null;

        try {


            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();


            inputReader = new InputStreamReader(process.getInputStream(), "MS949");
            bufferedReader = new BufferedReader(inputReader);

            String content = "";
            String line = "";
            while( (line = bufferedReader.readLine()) != null ){
                if(line.trim().length() == 0)
                    continue;
                content += line + "\n";
            }

            txtView.setText(content);

            int resultValue = process.exitValue();

            inputReader.close();
            bufferedReader.close();
            //process.destroy();

        } catch (Exception e){
            txtView.setText("Exception: "+e.toString());
        }

    }



}

