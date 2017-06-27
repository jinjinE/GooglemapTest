package emirim0223.kr.hs.emirim.googlemaptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap googleMap;
    SupportMapFragment mapFragment;
    GroundOverlayOptions loc_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap  = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4663251,126.9323608),17));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                loc_mark = new GroundOverlayOptions();
                loc_mark.image(BitmapDescriptorFactory.fromResource(R.drawable.location)).position(latLng,100f,100f);
                googleMap.addGroundOverlay(loc_mark);
            }
        });
        
    }

    public static final int ITEM_SATELLITE = 1;
    public static final int ITEM_NOMAL = 2;
    public static final int ITEM_jeju = 3;
    public static final int ITEM_hongdae = 4;
    public static final int ITEM_Mark_Clear = 5;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, ITEM_SATELLITE, 0, "위성 지도");
        menu.add(0, ITEM_NOMAL, 0, "일반 지도");
        SubMenu hotMenu = menu.addSubMenu("Hot Place");
        hotMenu.add(0, ITEM_jeju, 0, "제주도");
        hotMenu.add(0, ITEM_hongdae, 0, "홍대");
        menu.add(0, ITEM_Mark_Clear, 0, "위치 아이콘 제거");

       // menu.add(0, ITEM_jeju, 0, "추천 장소");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case ITEM_SATELLITE:
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case ITEM_NOMAL:
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case ITEM_jeju:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.3357579,126.2888158),10));
                return true;
            case ITEM_hongdae:
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.558084,126.9233449),17));
                return true;
            case ITEM_Mark_Clear:
                googleMap.clear();
                return true;
        }
        return false;
    }
}
