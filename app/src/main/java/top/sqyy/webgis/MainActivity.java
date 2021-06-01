package top.sqyy.webgis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView =null;
    public BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private LatLng currentLocation = null;
    private UiSettings mUiSettings = null;
    private boolean hasLocated = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        SDKInitializer.setCoordType(CoordType.BD09LL);

        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.bmapView);

        mMapView.showZoomControls(true);
        mLocationClient = new LocationClient(this);
        initMap();
        setMyPosition();


////        显示比例尺
//        mMapView.showScaleControl(false);
//
////        缩放按钮设置
//        mMapView.showZoomControls(false);

//        logo位置设置
//        mMapView.setLogoPosition(LogoPosition.logoPostionleftTop);
//
//        mMapView.setPadding(50,100,50,100);

//        mBaiduMap = mMapView.getMap();
////        卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        标准地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(5));

////        交通地图
//        mBaiduMap.setTrafficEnabled(true);


////        热力图
//        mBaiduMap.setBaiduHeatMapEnabled(true);
////        空白地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);

//        设置指南针
//        mUiSettings = mBaiduMap.getUiSettings();
//        mUiSettings.setCompassEnabled(false);
//
//
//        mBaiduMap.setMyLocationEnabled(true);
//
//        mLocationClient = new LocationClient(this);
//
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);
//        option.setCoorType("bd09ll");
//        option.setScanSpan(1000);
//        mLocationClient.setLocOption(option);
//
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
//
//        mLocationClient.start();

    }
    private void setMyPosition() {
        currentLocation = new LatLng(30.52632, 114.407898);
        MapStatus mMapStatus = new MapStatus.Builder().target(currentLocation).zoom(5).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }
    private void initMap() {

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);

        initLocation();
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        mLocationClient.start();
        mLocationClient.requestLocation();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );
        option.setCoorType("bd09ll");
        int span = 100;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);

        option.setIsNeedLocationPoiList(true);
        option.setIgnoreKillProcess(false);
        option.setOpenGps(true); // ��gps

        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        ArrayList<Integer> menulist = new ArrayList<>();
        menulist.add(R.id.line);
        menulist.add(R.id.point);

        if (id == R.id.showLocation) {
            showMylocation();
            return true;
        }
        if (id == R.id.ptType) {
            MapTools.ptType(mBaiduMap);
            return true;
        }
        if (id == R.id.wxType) {
            //卫星地图
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
            return true;
        }
        if (id == R.id.kbType) {
            //空白地图
            mBaiduMap.setBaiduHeatMapEnabled(false);
            mBaiduMap.setTrafficEnabled(false);
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
            return true;
        }
        if (id == R.id.lkType) {
            //开启交通图
            if (mBaiduMap.getMapType()!=BaiduMap.MAP_TYPE_NONE){
                MapTools.ptType(mBaiduMap);
            }
            mBaiduMap.setTrafficEnabled(true);
            return true;
        }
        if (id == R.id.rlType) {
            //开启热力图
            if (mBaiduMap.getMapType()!=BaiduMap.MAP_TYPE_NONE){
                MapTools.ptType(mBaiduMap);
            }
            mBaiduMap.setBaiduHeatMapEnabled(true);
            return true;
        }



        if (menulist.contains(id)){
            setMyPosition();
            return true;
        }

        if (id == R.id.polygon ||id == R.id.show){
            MapTools.setZoom(mBaiduMap,currentLocation);
            return true;
        }
        if (id == R.id.drawPoint){
            MapTools.drawPoint(mBaiduMap);
            return true;
        }
        if (id == R.id.drawCustomPoint) {
            MapTools.drawCustomPoint(mBaiduMap);
            return true;
        }
        if (id == R.id.drawLine) {
            MapTools.drawLine(mBaiduMap);
            return true;
        }
        if (id == R.id.drawDottedLine) {
            MapTools.drawDottedLine(mBaiduMap);
            return true;
        }
        if (id == R.id.drawColorLine) {
            MapTools.drawColorLine(mBaiduMap);
            return true;
        }
        if (id == R.id.drawClickLine) {
            MapTools.drawClickLine(mBaiduMap,MainActivity.this);
            return true;
        }
        if (id == R.id.drawHuLine) {
            MapTools.drawHuLine(mBaiduMap);
            return true;
        }
        if (id == R.id.drowPolygon) {
            MapTools.drowPolygon(mBaiduMap);
            return true;
        }
        if (id == R.id.drawCircle) {
            MapTools.drawCircle(mBaiduMap);
            return true;
        }





        return super.onOptionsItemSelected(item);
    }

    private void showMylocation() {

//        mLocationClient = new LocationClient(this);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);
//        option.setCoorType("bd09ll");
//        option.setScanSpan(1000);
//        mLocationClient.setLocOption(option);
//
//        MyLocationListener myLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(myLocationListener);
//        mLocationClient.start();

        MapTools.setZoom(mBaiduMap,currentLocation);

        MapStatus mMapStatus = new MapStatus.Builder().target(currentLocation).zoom(15).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

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