package com.example.obar.simon_kristine_labexer2;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    Button loadSharedPreferences, loadInternalStorage, clear, back;
    TextView display;
    SharedPreferences preferences;
    FileInputStream fis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        loadSharedPreferences = (Button)findViewById(R.id.btn_loadSharedPreferences);
        loadInternalStorage = (Button)findViewById(R.id.btn_InternalStorage);
        clear = (Button)findViewById(R.id.btn_clear);
        back = (Button)findViewById(R.id.btn_back);
        display = (TextView)findViewById(R.id.display);

        preferences = getSharedPreferences("preference", Context.MODE_WORLD_READABLE);
    }

    public void LoadSharedPreferences(View view){
        String user = preferences.getString("user", "");
        String pass = preferences.getString("pass", "");
        display.setText("Shared Preferences - The Username: " + user + ", Password: " + pass);
    }

    public void LoadInternalStorage(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        display.setText(buffer.toString());
    }

    public void Clear(View view){
        display.setText("");
    }

    public void Back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}