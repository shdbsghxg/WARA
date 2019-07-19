package com.example.wara;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AddUser.OnFragmentInteractionListener, Category.OnFragmentInteractionListener,
        Destination.OnFragmentInteractionListener, Messanger.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private AddUser addUser;
    private Category category;
    private Destination destination;
    private Messanger messanger;
    private BottomNavigationView navView;
    private PopupWindow popupWindow;
    private FragmentManager fragmentManager = getSupportFragmentManager();


    public static Rect locateView(View v)
    {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try
        {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe)
        {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.add_user:
                    if(addUser.isVisible()) {
                        fragmentTransaction.remove(addUser);
                        fragmentTransaction.commit();
                    }else{
                        switchFragment(0);
                    }
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.category:
                    View popupView = getLayoutInflater().inflate(R.layout.popup_category, null);
                    popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    popupWindow.setFocusable(true);

                    popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);


//                    ImageButton ok = (ImageButton) popupView.findViewById(R.id.Ok);
//                    ok.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    ImageButton cancel = (ImageButton) popupView.findViewById(R.id.Cancel);
//                    cancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            popupWindow.dismiss();
//                        }
//                    });


//                    switchFragment(1);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.destination:
                    if(destination.isVisible()) {
                        fragmentTransaction.remove(destination);
                        fragmentTransaction.commit();
                    }else{
                        switchFragment(2);
                    }
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.link_messenger:
                    if(messanger.isVisible()) {
                        fragmentTransaction.remove(messanger);
                        fragmentTransaction.commit();
                    }else{
                        switchFragment(3);
                    }
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                fragmentTransaction.remove(addUser);
                fragmentTransaction.remove(category);
                fragmentTransaction.remove(destination);
                fragmentTransaction.remove(messanger);
                fragmentTransaction.commit();
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

        /*Tmap API*/
        TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey( "9419da98-d84c-436d-97c8-a5216f6b0922" );
        tMapView.setLocationPoint(127.121272, 37.385810);
        frameTmap.addView( tMapView );

//        TMapPoint mapPoint1 = tMapView.getLocationPoint();
//        double latitude = mapPoint1.getLatitude();
//        double longitude = mapPoint1.getLongitude();
        double totLatitude = 0;
        double avgLatitude = 0;
        double totLongitude = 0;
        double avgLongitude = 0;
        int personCount = 3;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marker);
        Bitmap bitmapCenter = BitmapFactory.decodeResource(getResources(), R.drawable.marker_center);

        for(int i=0; i<personCount; i++){
            double latitude = Math.random() + 37;
            double longitude = Math.random() + 127;
            totLatitude += latitude;
            totLongitude += longitude;

            TMapPoint mapPoint = new TMapPoint(latitude, longitude);
            TMapMarkerItem marker = new TMapMarkerItem();
            marker.setIcon(bitmap); // 마커 아이콘 지정
            marker.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
            marker.setTMapPoint( mapPoint); // 마커의 좌표 지정
            marker.setName("MARKER" + i); // 마커의 타이틀 지정
            tMapView.addMarkerItem("marker"+i, marker); // 지도에 마커 추가

            if(i==(personCount-1)) {
                avgLatitude = totLatitude / personCount;
                avgLongitude= totLongitude/ personCount;
                TMapPoint mapPointCenter = new TMapPoint(avgLatitude, avgLongitude);
                TMapMarkerItem markerCenter = new TMapMarkerItem();
                markerCenter.setIcon(bitmapCenter); // 마커 아이콘 지정
                markerCenter.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
                markerCenter.setTMapPoint( mapPointCenter); // 마커의 좌표 지정
                markerCenter.setName("CENTER" + i); // 마커의 타이틀 지정
                tMapView.addMarkerItem("markerCenter", markerCenter); // 지도에 마커 추가

                tMapView.setCenterPoint( avgLongitude, avgLatitude );
            }

        }

//        TMapMarkerItem marker1 = new TMapMarkerItem();
//            marker1.setIcon(bitmap); // 마커 아이콘 지정
//            marker1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
//            marker1.setTMapPoint( mapPoint1); // 마커의 좌표 지정
//            marker1.setName("SKT타워"); // 마커의 타이틀 지정

//        tMapView.addMarkerItem("markerItem1", marker1); // 지도에 마커 추가
//        tMapView.setCenterPoint( longitude, latitude );


        /*Daum Map API*/
//        MapView mapView = new MapView(this);
//        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.fragFrame);
//        mapViewContainer.addView(mapView);

        navView = findViewById(R.id.nav_view);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }


    protected void switchFragment(int fragmentNum){
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
