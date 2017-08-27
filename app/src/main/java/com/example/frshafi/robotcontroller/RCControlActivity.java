package com.example.frshafi.robotcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static com.example.frshafi.robotcontroller.DeviceListActivity.btSocket;

public class RCControlActivity extends AppCompatActivity {

    Button button_right,button_left,button_forward,button_backward,button_dis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rccontrol);

        button_forward = (Button)findViewById(R.id.button_forward);
        button_backward = (Button)findViewById(R.id.button_backward);
        button_left = (Button)findViewById(R.id.button_left);
        button_right = (Button)findViewById(R.id.button_right);
        button_dis = (Button)findViewById(R.id.button_dis);



        button_forward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        goForward();
                        v.setBackgroundResource(R.drawable.button_calc);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        garbageSending();
                        v.setBackgroundResource(R.drawable.button_back);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        button_backward.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        goBackward();
                        v.setBackgroundResource(R.drawable.button_calc);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        garbageSending();
                        v.setBackgroundResource(R.drawable.button_back);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        button_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        goRight();
                        v.setBackgroundResource(R.drawable.button_calc);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        garbageSending();
                        v.setBackgroundResource(R.drawable.button_back);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });


        button_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        goLeft();
                        v.setBackgroundResource(R.drawable.button_calc);
                        return true; // if you want to handle the touch event
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        garbageSending();
                        v.setBackgroundResource(R.drawable.button_back);
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        // action for disconnect button
        button_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Disconnect();
                startActivity(new Intent(RCControlActivity.this,MainActivity.class));
                finish();

            }
        });
    }


    // method for moving robot forward
    private void goForward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("FRD".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // method for moving robot backward
    private void goBackward()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("BRD".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // method for turning robot right
    private void goRight()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("RHT".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // method for turning robot left
    private void goLeft()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("LFT".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // this method is for sending a string which indicate we are
    // not pressing anyone of our desired button
    private void garbageSending(){

        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("OFFALL".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // method for disconnecting bt connection
    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { showToast("Error");}
        }
    }

    // universal method for all toasts

    public void showToast(String toastMesg){

        Toast.makeText(getBaseContext(),toastMesg, Toast.LENGTH_SHORT).show();
    }
}
