package com.example.frshafi.robotcontroller;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import static com.example.frshafi.robotcontroller.DeviceListActivity.btSocket;


public class LedTestingActivity extends AppCompatActivity {


    // declaring necessary variables (objects)
    SwitchCompat onOffSwitch;
    String address = null;


    Button onButton;
    Button offButton;
    Button disconnectButton;
    SeekBar brightness;
    TextView lumn;
    TextView brightPercent;

    public static String STRING_EXTRA;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    //BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_test);

        onButton = (Button)findViewById(R.id.buttonOn);
        offButton =(Button)findViewById(R.id.buttonOff);
        disconnectButton = (Button)findViewById(R.id.disconnectButton);
        brightness = (SeekBar)findViewById(R.id.seekBar);
        lumn = (TextView)findViewById(R.id.lumn);
        brightPercent = (TextView)findViewById(R.id.brightPercent);
        brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser==true)
                {
                    brightPercent.setVisibility(View.VISIBLE);
                    int progressInhundred = (progress*100)/255;

                    lumn.setVisibility(View.VISIBLE);
                    lumn.setText(String.valueOf(progressInhundred));
                    try
                    {
                        btSocket.getOutputStream().write(String.valueOf(progress).getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    onButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            turnOnLed();
        }
    });

    offButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            lumn.setVisibility(View.INVISIBLE);
            brightPercent.setVisibility(View.INVISIBLE);
            turnOffLed();
        }
    });

   disconnectButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Disconnect();
           startActivity(new Intent(LedTestingActivity.this,MainActivity.class));
           finish();
       }
   });

    }

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
        finish(); //return to the first layout
    }


    private void turnOffLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TF".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }


    private void turnOnLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("TO".toString().getBytes());
            }
            catch (IOException e)
            {
                showToast("Error");
            }
        }
    }

    // universal method for all toasts

    public void showToast(String toastMesg){

        Toast.makeText(getBaseContext(),toastMesg, Toast.LENGTH_SHORT).show();
    }
}
