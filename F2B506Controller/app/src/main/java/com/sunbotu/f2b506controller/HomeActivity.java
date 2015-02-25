package com.sunbotu.f2b506controller;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class HomeActivity extends Activity {

    private static final String PREF_IP = "ip";
    private static final String PREF_PORT = "port";

    private TextView serverIpTextView;
    private TextView serverPortTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        serverIpTextView = (TextView) findViewById(R.id.ip);
        serverPortTextView = (TextView) findViewById(R.id.port);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        serverIpTextView.setText(pref.getString(PREF_IP, "192.168.1.100"));
        serverPortTextView.setText(pref.getString(PREF_PORT, "4445"));
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
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    private void saveConnectionInfo(String ip, String port) {
        PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putString(PREF_IP, ip)
                .putString(PREF_PORT, port)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
