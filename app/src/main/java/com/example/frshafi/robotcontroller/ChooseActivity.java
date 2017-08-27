package com.example.frshafi.robotcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {


    Button button_chooseLight;
    Button button_chooseCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        button_chooseLight = (Button)findViewById(R.id.button_chooseLight);
        button_chooseCar = (Button) findViewById(R.id.button_chooseCar);


        button_chooseLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this,LedTestingActivity.class));
            }
        });

        button_chooseCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this,RCControlActivity.class));
            }
        });
    }
}
