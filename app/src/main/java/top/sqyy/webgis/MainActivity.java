package top.sqyy.webgis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView =null;
    public BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private LatLng currentLocation = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        SDKInitializer.setCoordType(CoordType.BD09LL);

        setContentView(R.layout.activity_main);

        mMapView = findViewById(R.id.bmapView);

        mBaiduMap = mMapView.getMap();
////        卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        标准地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

////        交通地图
//        mBaiduMap.setTrafficEnabled(true);

        mBaiduMap.setBaiduHeatMapEnabled(true);
////        空白地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        mBaiduMap.setMyLocationEnabled(true);

        mLocationClient = new LocationClient(this);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        mLocationClient.start();





    }



    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView=null;
        super.onDestroy();

    }





    public class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null) {
                return;
            }
            int code = location.getLocType();

            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())

                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

//            currentLocation = new LatLng(30.526779, 114.405241);
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            //currentLocation=p;
        }
    }
}