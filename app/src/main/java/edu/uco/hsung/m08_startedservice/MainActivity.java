package edu.uco.hsung.m08_startedservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

    Intent service;
    Intent intentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        service = new Intent(this, DownloadService.class);
        service.putExtra("FILENAME", "A huge image.jpg");

        Switch s1 = (Switch) findViewById(R.id.service_switch);
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(service);
                } else {
                    stopService(service);
                }
            }
        });

        intentService = new Intent(this, DownloadIntentService.class);
        intentService.putExtra("FILENAME", "a big movie.mpg");

        ToggleButton s2 = (ToggleButton) findViewById(R.id.intentservice_switch);
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(intentService);
                } else {
                    stopService(intentService);
                }
            }
        });

        final TextView timeView = (TextView) findViewById(R.id.get_time_tv);

        Button timeButton = (Button) findViewById(R.id.time_button);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeView.setText("" + new java.util.Date());
            }
        });

    }
}
