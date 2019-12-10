package com.balala.appbootstrap;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.balala.bootstrap.core.BootStrap;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BootStrap.invokeGroupMethod("exit", MainActivity.this);
            }
        });
//        BootStrap.invokeGroupMethod("sayHello", this);
//        BootStrap.findListForGroup("")
//        BootStrap.invokeGroupMethod("list", getApplication());
    }
}
