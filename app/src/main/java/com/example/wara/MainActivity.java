package com.example.wara;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements AddUser.OnFragmentInteractionListener, Category.OnFragmentInteractionListener,
        Destination.OnFragmentInteractionListener, Messanger.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private AddUser addUser;
    private Category category;
    private Destination destination;
    private Messanger messanger;
    private BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_user:
                    switchFragment(0);
//                    setCheckable(navView, true, 0);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.category:
                    switchFragment(1);
//                    setCheckable(navView, true, 1);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.meet_place:
                    switchFragment(2);
//                    setCheckable(navView, true, 2);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.link_messenger:
                    switchFragment(3);
//                    setCheckable(navView, true, 3);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

//    public static void setCheckable(BottomNavigationView view, boolean checkable, int fragmentNum) {
//        final Menu menu = view.getMenu();
//        menu.getItem(fragmentNum).setCheckable(checkable);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                fragmentTransaction.remove(addUser);
                fragmentTransaction.remove(category);
                fragmentTransaction.remove(destination);
                fragmentTransaction.remove(messanger);
                fragmentTransaction.commit();
//                setCheckable(navView, false, 0);
//                setCheckable(navView, false, 1);
//                setCheckable(navView, false, 2);
//                setCheckable(navView, false, 3);
//                invalidateOptionsMenu();

                break;
            case KeyEvent.KEYCODE_VOLUME_DOWN :
                break;
            case KeyEvent.KEYCODE_VOLUME_UP :
                break;
            case KeyEvent.KEYCODE_HOME :
                break;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUser = new AddUser();
        category = new Category();
        destination = new Destination();
        messanger = new Messanger();

        FrameLayout frameTmap = (FrameLayout)findViewById(R.id.fragFrame);

//        TMapView tMapView = new TMapView(this);
//        tMapView.setSKTMapApiKey( "9419da98-d84c-436d-97c8-a5216f6b0922" );
//        frameTmap.addView( tMapView );

        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.fragFrame);
        mapViewContainer.addView(mapView);

        navView = findViewById(R.id.nav_view);
//        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    protected void switchFragment(int fragmentNum){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (fragmentNum){
            case 0 :
                fragmentTransaction.replace(R.id.fragFrame, addUser);
                fragmentTransaction.commit();
                break;
            case 1 :
                fragmentTransaction.replace(R.id.fragFrame, category);
                fragmentTransaction.commit();
                break;
            case 2 :
                fragmentTransaction.replace(R.id.fragFrame, destination);
                fragmentTransaction.commit();
                break;
            case 3 :
                fragmentTransaction.replace(R.id.fragFrame, messanger);
                fragmentTransaction.commit();
                break;
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
