package com.example.obar.simon_kristine_labexer2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button sharedPreferences, internalStorage, next;
    SharedPreferences preferences;
    FileOutputStream fos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        sharedPreferences = (Button)findViewById(R.id.btn_SharedPreferences);
        internalStorage = (Button)findViewById(R.id.btn_InternalStorage);

        preferences = getSharedPreferences("preference", Context.MODE_WORLD_READABLE);
    }

    public void Next(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();

    }

    public void SharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", username.getText().toString());
        editor.putString("pass", password.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    public void InternalStorage(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String output = "Internal Storage - The Username: " + user + ", Password: " + pass;

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(output.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }
}
