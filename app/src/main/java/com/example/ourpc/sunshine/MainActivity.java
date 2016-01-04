package com.example.ourpc.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(LOG_TAG, "onCreate()" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(LOG_TAG, "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(LOG_TAG, "onResume()");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.v(LOG_TAG, "onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG, "onDestroy()");
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
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }if (id == R.id.action_mapLocation){
            openPreferredLocationOnMap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationOnMap() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPref.getString(getString(R.string.pref_location_key),
                "Moscow");
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().
                appendQueryParameter("q", location)
                .build();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager())!= null)
            startActivity(intent);
        else
            Log.d (LOG_TAG, "Couldn't open " + location + ", no app found.");

    }
}
