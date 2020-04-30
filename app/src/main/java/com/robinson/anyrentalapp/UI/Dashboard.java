package com.robinson.anyrentalapp.UI;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.robinson.anyrentalapp.Fragments.CardFragment;
import com.robinson.anyrentalapp.Fragments.ExploreFragment;
import com.robinson.anyrentalapp.Fragments.MyFeedFragment;
import com.robinson.anyrentalapp.Fragments.ProfileFragment;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.MainActivity;
import com.robinson.anyrentalapp.R;

public class Dashboard extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {
    TabLayout tabLayout;
    UserSession userSession;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    TextView ProximitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        tabLayout = findViewById(R.id.dash_tabLayout);
        ProximitySensor = findViewById(R.id.proximityvalue);
        tabLayout.addOnTabSelectedListener(this);
        loadFragment(MyFeedFragment.newInstance());
        initToolbar();
        userSession = new UserSession(this);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            mySensorManager.registerListener(sensorEventListener, myProximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            WindowManager.LayoutParams params = Dashboard.this.getWindow().getAttributes();
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

                if (event.values[0] == 0) {
                    params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    params.screenBrightness = 0;
                    getWindow().setAttributes(params);
                } else {
                    params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    params.screenBrightness = -1f;
                    getWindow().setAttributes(params);
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void initToolbar() {
        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);
    }

    private void loadFragment(Fragment activeFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, activeFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) ;
        userSession.endSession();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = null;
        switch (tab.getPosition()) {
            case 0:
                fragment = MyFeedFragment.newInstance();
                getSupportActionBar().setTitle("My Feed");
                break;
            case 1:
                fragment = ExploreFragment.newInstance();
                getSupportActionBar().setTitle("Explore");
                break;
            case 2:
                fragment = CardFragment.newInstance();
                getSupportActionBar().setTitle("Cart");
                break;
            case 3:
                fragment = ProfileFragment.newInstance();
                getSupportActionBar().setTitle("Profile");
                break;

        }
        loadFragment(fragment);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
