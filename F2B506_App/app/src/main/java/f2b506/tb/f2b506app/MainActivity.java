package f2b506.tb.f2b506app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private static final String PREF_IP = "ip";
    private static final String PREF_PORT = "port";

    private TextView serverIpTextView;
    private TextView serverPortTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverIpTextView = (TextView) findViewById(R.id.ip);
        serverPortTextView = (TextView) findViewById(R.id.port);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        serverIpTextView.setText(pref.getString(PREF_IP, "192.168.1.100"));
        serverPortTextView.setText(pref.getString(PREF_PORT, "4445"));
    }

    public void connectToServer(View view) {
        Intent intent = new Intent(this, MonitorActivity.class);
        String ip = serverIpTextView.getText().toString();
        String port = serverPortTextView.getText().toString();
        intent.putExtra("ip", ip);
        intent.putExtra("port", port);
        saveConnectionInfo(ip, port);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
