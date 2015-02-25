package com.sunbotu.f2b506controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sunbotu.f2b506controller.util.ControlPoint;
import com.sunbotu.f2b506controller.util.DeviceDiscoverTarget;
import com.sunbotu.f2b506controller.util.Parameter;


public class HomeActivity extends Activity implements DeviceDiscoverTarget{

    private ControlPoint controlPoint;
    private TextView debugDevices;
    private TextView stateChair;
    private TextView stateBed;
    private TextView stateBand;
    private TextView stateDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controlPoint = new ControlPoint(this);
        setContentView(R.layout.activity_home);
        debugDevices = (TextView) findViewById(R.id.text_devices);
        stateChair = (TextView) findViewById(R.id.state_chair);
        stateBed = (TextView) findViewById(R.id.state_bed);
        stateBand = (TextView) findViewById(R.id.state_wristband);
        stateDoor = (TextView) findViewById(R.id.state_door);
    }

    public void deviceDiscovered() {
        // Debugging message
        debugDevices.setText(controlPoint.printAllDevices());
        if(controlPoint.getDevice(Parameter.DEV_CHAIR) != null) {
            stateChair.setText("Connected");
        }
        if(controlPoint.getDevice(Parameter.DEV_BED) != null) {
            stateBed.setText("Connected");
        }
        if(controlPoint.getDevice(Parameter.DEV_BAND) != null) {
            stateBand.setText("Connected");
        }
        if(controlPoint.getDevice(Parameter.DEV_DOOR) != null) {
            stateDoor.setText("Connected");
        }
    }

    public void startControlPoint() {
        controlPoint.search();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
