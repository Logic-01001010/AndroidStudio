package com.example.shell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    TextView txtView;
    EditText editText;
    ScrollView scrollView;

    private static String cmd_result;
    private static Handler mHandler;
    private static Process p = null;


    private void checkStoragePermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(permissionCheck == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = (TextView) findViewById(R.id.txtView);
        editText = (EditText) findViewById(R.id.editText);
        scrollView = (ScrollView) findViewById(R.id.scrollView) ;


        // 저장소 권한 확인
        checkStoragePermission();



        // 화면 출력 역할 핸들러
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){

                // Clear
                if( cmd_result.equals(new String("\u001B[2J\u001B[H")) )
                    txtView.setText("");
                else
                    txtView.setText(txtView.getText() + cmd_result);

                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                editText.requestFocus();

            }

        };
        
        

        ExampleThread thread = new ExampleThread();
        thread.start();




        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if( (event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) ){


                    String cmd = editText.getText().toString();


                    // 입력 신호를 쉘에게 리다이렉트
                    PrintWriter writer = new PrintWriter(  p.getOutputStream() );
                    writer.println( cmd );
                    writer.flush();


                    editText.setText(null);
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    editText.requestFocus();

                }
                else
                    return false;

                return true;

            }
        });






    }


    // 스레드
    private class ExampleThread extends Thread{
        public ExampleThread(){

        }
        public void run(){


            ProcessBuilder pb = new ProcessBuilder("/system/bin/sh");

            // 시작 경로
            pb.directory( new File("/storage/emulated/0/") );


            // 프로세스 시작
            try {
                p = pb.start();
            } catch (IOException e) {
                e.printStackTrace();
            }





            // 표준 출력 리다이렉션
            InputStream stdOut = p.getInputStream();

            new Thread(new Runnable(){
                public void run(){
                    byte[] buffer = new byte[8192];
                    int len = -1;
                    try {
                        while((len = stdOut.read(buffer)) > 0){


                            String str = new String();

                            str += new String(buffer, 0, len, "UTF-8");


                            cmd_result = str;
                            mHandler.sendEmptyMessage(0);


                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();


                    }
                }
            }).start();



            // 표준 에러 리다이렉션
            InputStream stdError = p.getErrorStream();

            new Thread(new Runnable(){
                public void run(){
                    byte[] buffer = new byte[8192];
                    int len = -1;
                    try {

                        while((len = stdError.read(buffer)) > 0){


                            String str = new String();

                            str+= new String(buffer, 0, len, "UTF-8");

                            cmd_result = str;
                            mHandler.sendEmptyMessage(0);





                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();


                    }
                }
            }).start();





            // 프로세스가 끝날때 까지 기다림.
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 앱 종료
            finish();



        }
    }


}

