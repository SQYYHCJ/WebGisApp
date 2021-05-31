package top.sqyy.webgis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        SDKInitializer.setCoordType(CoordType.BD09LL);

        setContentView(R.layout.activity_main);

        mMapView = findViewById(R.id.bmapView);

////        显示比例尺
//        mMapView.showScaleControl(false);
//
////        缩放按钮设置
//        mMapView.showZoomControls(false);

//        logo位置设置
//        mMapView.setLogoPosition(LogoPosition.logoPostionleftTop);
//
//        mMapView.setPadding(50,100,50,100);

        mBaiduMap = mMapView.getMap();
////        卫星地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//        标准地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

////        交通地图
//        mBaiduMap.setTrafficEnabled(true);


////        热力图
//        mBaiduMap.setBaiduHeatMapEnabled(true);
////        空白地图
//        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);

//        设置指南针
        mUiSettings = mBaiduMap.getUiSettings();
        mUiSettings.setCompassEnabled(false);


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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.drawLine) {
            mBaiduMap.clear();
            //构建折线点坐标,
            LatLng p1 = new LatLng(60.527123, 80.405671);
            LatLng p2 = new LatLng(60.526779, 160.405241);
            LatLng p3 = new LatLng(40.526149, 100.40724);
            LatLng p4 = new LatLng(80.527123, 120.405671);
            LatLng p5 = new LatLng(40.526779, 140.405241);
            LatLng p6 = new LatLng(60.526149, 80.40724);
            List<LatLng> points = new ArrayList<LatLng>();
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);
            points.add(p5);
            points.add(p6);

            //设置折线的属性
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(10)
                    .color(0xAAFF0000)
                    .points(points);
            //在地图上绘制折线
            //mPloyline 折线对象
            Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
            return true;
        }

        if (id == R.id.drawPoint){
            LatLng point = new LatLng(30.527123, 114.405671);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.marker1);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            return true;
        }
        if (id == R.id.drawCustomPoint) {
            mBaiduMap.clear();
            //定义Maker坐标点
            LatLng point = new LatLng(30.526149, 114.40724);
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.circle);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point) //必传参数
                    .icon(bitmap) //必传参数
                    .draggable(true)
                    //设置平贴地图，在地图中双指下拉查看效果
                    .flat(true)
                    .alpha(0.5f);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
            return true;
        }

        return super.onOptionsItemSelected(item);
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