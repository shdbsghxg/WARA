package com.example.wara;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skt.Tmap.TMapView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_user:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.category:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.meet_place:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.link_messenger:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameTmap = (FrameLayout)findViewById(R.id.frameMap);
        TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey( "9419da98-d84c-436d-97c8-a5216f6b0922" );
        frameTmap.addView( tMapView );

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
