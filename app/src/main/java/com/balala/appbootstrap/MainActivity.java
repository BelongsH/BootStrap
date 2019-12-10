package com.balala.appbootstrap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BootStrap.invokeGroupMethod("sayHello", this);
//        BootStrap.findListForGroup("")
//        BootStrap.invokeGroupMethod("list", getApplication());
    }
}
