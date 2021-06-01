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
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapView mMapView =null;
    public BaiduMap mBaiduMap = null;
    private LocationClient mLocationClient = null;
    private LatLng currentLocation = null;
    private UiSettings mUiSettings = null;
    HeatMap mCustomHeatMap = null;


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
        menulist.add(R.id.polygon);
        menulist.add(R.id.show);
        menulist.add(R.id.adde);
        menulist.add(R.id.overlay);
        menulist.add(R.id.poiSerch);
        menulist.add(R.id.transForm);

        if (id == R.id.point ||id == R.id.line){
            setMyPosition();
            return true;
        }
        if (menulist.contains(id)){
            MapTools.setZoom(mBaiduMap,currentLocation);
            return true;
        }

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
        if (id == R.id.addFont) {
            MapTools.addFont(mBaiduMap);
            return true;
        }
        if (id == R.id.addInfoWindow) {
            MapTools.addInfoWindow(mBaiduMap,MainActivity.this);
            return true;
        }
        if (id == R.id.ddh) {
            MapTools.ddh(mBaiduMap);
            return true;
        }
        if (id == R.id.panAnimate) {
            MapTools.panAnimate(mBaiduMap);
            return true;
        }
        if (id == R.id.panJump) {
            MapTools.panJump(mBaiduMap);
            return true;
        }
        if (id == R.id.groundOvelay) {
            MapTools.groundOvelay(mBaiduMap);
            return true;
        }
        if (id == R.id.customHeatMap) {
            MapTools.customHeatMap(mBaiduMap,mCustomHeatMap);
            return true;
        }
        if (id == R.id.batchAdd) {
            MapTools.batchAdd(mBaiduMap);
            return true;
        }else if (id == R.id.batchDelete) {
            MapTools.batchDelete(mBaiduMap);
            return true;
        }
        if (id == R.id.citySearch) {
            MapTools.citySearch(mBaiduMap);
            return true;
        }
        if (id == R.id.nearSearch) {
            MapTools.nearSearch(mBaiduMap);
            return true;
        }
        if (id == R.id.squareSearch) {
            MapTools.squareSearch(mBaiduMap);
            return true;
        }
        if (id == R.id.locationToCoor) {
            MapTools.locationToCoor(mBaiduMap, MainActivity.this);
            return true;
        }else if (id == R.id.coorToLocation) {
            MapTools.coorToLocation(mBaiduMap, MainActivity.this);
            return true;
        }




        return super.onOptionsItemSelected(item);
    }
//    private void citySerchTest(){
//       PoiSearch mPoiSerch= PoiSearch.newInstance();
//        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
//            @Override
//            public void onGetPoiResult(PoiResult poiResult) {
////                if (poiResult.error== SearchResult.ERRORNO.NO_ERROR){
////                    mBaiduMap.clear();
////                    PoiOverlay poiOverlay= new PoiOverlay(mBaiduMap);
////                    poiOverlay.setData(poiResult);
////                    poiOverlay.addToMap();
////                    poiOverlay.zoomToSpan();
////
////                }
//                LatLng llText = new LatLng(30.526779, 114.405241);
//
//                //构建TextOptions对象
//                OverlayOptions mTextOptions = new TextOptions()
//                        .text(String.valueOf(poiResult.error)) //文字内容
//                        .bgColor(0xAAFFFF00) //背景色
//                        .fontSize(24) //字号
//                        .fontColor(0xFFFF00FF) //文字颜色
//                        .rotate(-30) //旋转角度
//                        .position(llText);
//                //在地图上显示文字覆盖物
//                Overlay mText = mBaiduMap.addOverlay(mTextOptions);
//            }
//
//            @Override
//            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//
//            }
//
//            @Override
//            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
//
//            }
//
//            @Override
//            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//            }
//        };
//
//        mPoiSerch.setOnGetPoiSearchResultListener(listener);
//        PoiCitySearchOption poiCitySearchOption = new PoiCitySearchOption();
//        mPoiSerch.searchInCity(new PoiCitySearchOption().city("北京").keyword("超市").pageNum(100));
//        mPoiSerch.destroy();
//
//    }

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