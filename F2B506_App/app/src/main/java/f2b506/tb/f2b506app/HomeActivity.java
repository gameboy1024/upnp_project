package f2b506.tb.f2b506app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

    public void startRestingActivity(View view) {
        Intent intent = new Intent(this, RestingActivity.class);
        startActivity(intent);
    }

    public void startSportsActivity(View view) {
        Intent intent = new Intent(this, SportsActivity.class);
        startActivity(intent);
    }

    public void startMonitorActivity(View view) {
        Intent intent = new Intent(this, MonitorActivity.class);
        startActivity(intent);
    }

    public void startAlarmActivity(View view) {
        Intent intent = new Intent(this, AlarmActivity.class);
        startActivity(intent);
    }
}
