package com.example.linking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView sensorDataTextView;

    Button CONN,DCONN,RC,LC,STARTSTOP;
    EditText IPAD;
    SeekBar SENS;

    public int SENSV=1,ICE=0;

    TextView Senstivity;
    String ipad;



    public Boolean CONN_STAT=false,MM=false;

    public int SSC=0,CONNC=0;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CONN = findViewById(R.id.Connect);
        DCONN = findViewById(R.id.Disconnect);
        STARTSTOP = findViewById(R.id.SS);

        RC = findViewById(R.id.RC);
        LC = findViewById(R.id.LC);
        IPAD = findViewById(R.id.IpadText);
        SENS = findViewById(R.id.SensControl);
        Senstivity = findViewById(R.id.Senstivity);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Context context = MainActivity.this;





        CONN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Thread t = new Thread() {


                    @Override
                    public void run() {


                        try {
                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_CONNECTED");

                                CONN_STAT=true;
                                ICE=1;






                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                if(ICE==1)
                {
                    Toast.makeText(context, "Device Connected", Toast.LENGTH_SHORT).show();
                }
            }

        });


        DCONN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Thread t = new Thread() {


                    @Override
                    public void run() {


                        try {
                            if(ICE==0)
                            {

                            }

                            if(ICE==1)
                            {
                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_DICONNECTED");

                                CONN_STAT=false;
                                ICE=0;

                            }










                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();

            if(ICE==0)
            {
                Toast.makeText(context, "Device Disconnected", Toast.LENGTH_SHORT).show();
            }
            }
        });


        STARTSTOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SSC = SSC + 1;
                        MM=true;
                STARTSTOP.setText("STOP");
                Toast.makeText(context, "Mouse Activity Stopped", Toast.LENGTH_SHORT).show();
                        if (SSC % 2 != 0) {
                            MM=false;
                            STARTSTOP.setText("START");
                            Toast.makeText(context, "Mouse Activity Started", Toast.LENGTH_SHORT).show();

                        }
            }
        });



        RC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(CONN_STAT==true && MM==true)
                {
                    Thread t = new Thread() {


                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            try {
                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_BR");


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
            }
        });

        RC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(CONN_STAT==true && MM==true)
                {
                    Thread t = new Thread() {


                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            try {

                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_BRL");


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
                return true;
            }
        });



        LC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if(CONN_STAT==true && MM==true)
                {
                    Thread t = new Thread() {


                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            try {

                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_BL");


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
            }
        });


        LC.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(CONN_STAT==true && MM==true)
                {
                    Thread t = new Thread() {


                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            try {

                                ipad= String.valueOf(IPAD.getText());
                                Socket s = new Socket(ipad, 7000);
                                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                                dos.writeUTF("_BLL");


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    t.start();
                }
                return true;
            }
        });

        SENS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int sens,
                                          boolean fromUser) {

                SENSV = sens;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {





            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int xAxis = (int) event.values[0];
            int yAxis = (int) event.values[1];

            if(CONN_STAT==true && MM==true)
            {
                Thread t = new Thread() {


                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        try {

                            ipad= String.valueOf(IPAD.getText());
                            Socket s = new Socket(ipad, 7000);
                            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                            dos.writeUTF("_" + xAxis*-1*SENSV+":"+yAxis*-1*SENSV);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



}
