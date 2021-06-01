package top.sqyy.webgis;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.ArcOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Gradient;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapTools {


    //    点事件
    public static void drawPoint(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
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
    }
    public static void drawCustomPoint(BaiduMap mBaiduMap) {
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
    }


//    线事件
    public static void drawLine(BaiduMap mBaiduMap) {
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
//            ((Polyline)mPolyline).setDottedLine(true);
    }
    public static void drawDottedLine(BaiduMap mBaiduMap) {
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
                .points(points)
                .dottedLine(true); //设置折线显示为虚线
        //mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
        //设置折线显示为虚线
        ((Polyline) mPolyline).setDottedLine(true);
    }
    public static void drawColorLine(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //构建折线点坐标
        List<LatLng> points = new ArrayList<LatLng>();
        LatLng p1 = new LatLng(60.527123, 80.405671);
        LatLng p2 = new LatLng(60.526779, 160.405241);
        LatLng p3 = new LatLng(40.526149, 100.40724);
        LatLng p4 = new LatLng(80.527123, 120.405671);
        LatLng p5 = new LatLng(40.526779, 140.405241);
        LatLng p6 = new LatLng(60.526149, 80.40724);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.RED);

//设置折线的属性
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xAAFF0000)
                .points(points)
                .colorsValues(colors);//设置每段折线的颜色

//在地图上绘制折线
//mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
    }
    public static void drawClickLine(BaiduMap mBaiduMap,final Context context) {
        BaiduMap.OnPolylineClickListener listener = new BaiduMap.OnPolylineClickListener() {
            //处理Polyline点击逻辑
            @Override
            public boolean onPolylineClick(Polyline polyline) {
                Toast.makeText(context, "Click on polyline", Toast.LENGTH_LONG).show();

                return true;
            }
        };
        //设置Polyline点击监听器
        mBaiduMap.setOnPolylineClickListener(listener);
    }
    public static void drawHuLine(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        // 添加弧线坐标数据
        LatLng p1 = new LatLng(20.527123, 100.405671);//起点
        LatLng p2 = new LatLng(40.526779, 120.405241);//中间点
        LatLng p3 = new LatLng(20.526149, 80.40724);//终点
        //构造ArcOptions对象
        OverlayOptions mArcOptions = new ArcOptions()
                .color(Color.RED)
                .width(10)
                .points(p1, p2, p3);
        //在地图上显示弧线
        Overlay mArc = mBaiduMap.addOverlay(mArcOptions);
    }

    public static void drowPolygon(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //多边形顶点位置
        List<LatLng> points = new ArrayList<>();
        points.add(new LatLng(30.527123, 114.405671));
        points.add(new LatLng(30.526779, 114.405241));
        points.add(new LatLng(30.526149, 114.40724));
        points.add(new LatLng(30.525762,114.408533));
        points.add(new LatLng(30.52632,114.407898));

        //构造PolygonOptions
        PolygonOptions mPolygonOptions = new PolygonOptions()
                .points(points)
                .fillColor(0xAAFFFF00) //填充颜色
                .stroke(new Stroke(5, 0xAA00FF00)); //边框宽度和颜色

        //在地图上显示多边形
        mBaiduMap.addOverlay(mPolygonOptions);
    }
    public static void setZoom(BaiduMap mBaiduMap ,LatLng currentLocation) {
        mBaiduMap.clear();
        currentLocation = new LatLng(30.52632, 114.407898);
        MapStatus mMapStatus = new MapStatus.Builder().target(currentLocation).zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);

    }
    public static void drawCircle(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //圆心位置
        LatLng center = new LatLng(30.526779, 114.405241);

        //构造CircleOptions对象
        CircleOptions mCircleOptions = new CircleOptions().center(center)
                .radius(50)
                .fillColor(0xAA0000FF) //填充颜色
                .stroke(new Stroke(5, 0xAA00ff00)); //边框宽和边框颜色

        //在地图上显示圆
        Overlay mCircle = mBaiduMap.addOverlay(mCircleOptions);
    }

    public static void ptType(BaiduMap mBaiduMap) {
        mBaiduMap.setTrafficEnabled(false);
        mBaiduMap.setBaiduHeatMapEnabled(false);
        //普通地图 ,mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    public static void addFont(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //文字覆盖物位置坐标
        LatLng llText = new LatLng(30.526779, 114.405241);

        //构建TextOptions对象
        OverlayOptions mTextOptions = new TextOptions()
                .text("百度地图SDK") //文字内容
                .bgColor(0xAAFFFF00) //背景色
                .fontSize(24) //字号
                .fontColor(0xFFFF00FF) //文字颜色
                .rotate(-30) //旋转角度
                .position(llText);
        //在地图上显示文字覆盖物
        Overlay mText = mBaiduMap.addOverlay(mTextOptions);
    }
    @SuppressLint("SetTextI18n")
    public static void addInfoWindow(BaiduMap mBaiduMap, final Context context) {
        mBaiduMap.clear();
        //文字覆盖物位置坐标
        LatLng point = new LatLng(30.526779, 114.405241);
        //用来构造InfoWindow的Button
        Button button = new Button(context.getApplicationContext());
        button.setBackgroundResource(R.drawable.popup);
        button.setText("InfoWindow");
        //构造InfoWindow
        //point 描述的位置点
        //-100 InfoWindow相对于point在y轴的偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, point, -100);
        //使InfoWindow生效
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    //点标记动画
    public static void ddh(BaiduMap mBaiduMap){
        mBaiduMap.clear();
        //构造Icon列表
        // 初始化bitmap 信息，不用时及时 recycle
        BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        BitmapDescriptor bdB = BitmapDescriptorFactory.fromResource(R.drawable.icon_markb);
        BitmapDescriptor bdC = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);
        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
        giflist.add(bdA);
        giflist.add(bdB);
        giflist.add(bdC);
        //Marker位置坐标
        LatLng llD = new LatLng(30.526779, 114.405241);

        //构造MarkerOptions对象
        MarkerOptions ooD = new MarkerOptions()
                .position(llD)
                .icons(giflist)
                .zIndex(0)
                .period(20);//定义刷新的帧间隔
        //在地图上展示包含帧动画的Marker
        Overlay mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));
    }
    //  平移下落动画
    public static void panAnimate(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        LatLng llC = new LatLng(30.526779, 114.405241);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        MarkerOptions ooA = new MarkerOptions()
                .position(llC)
                .icon(bitmap);
        //设置掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        /* 在地图上添加Marker，并显示 */
        mBaiduMap.addOverlay(ooA);
    }
    //跳跃动画
    public static void panJump(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        LatLng llC = new LatLng(30.526779, 114.405241);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        MarkerOptions ooA = new MarkerOptions()
                .position(llC)
                .icon(bitmap);
        //设置掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.jump);
        /* 在地图上添加Marker，并显示 */
        mBaiduMap.addOverlay(ooA);
    }

    public static void groundOvelay(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //定义Ground的显示地理范围
        LatLng southwest = new LatLng(30.527123, 114.405671);
        LatLng northeast = new LatLng(30.526779, 114.405241);
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(northeast)
                .include(southwest)
                .build();
        //定义Ground显示的图片
        BitmapDescriptor bdGround = BitmapDescriptorFactory.fromResource(R.drawable.ground_overlay);
        //定义GroundOverlayOptions对象
        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds)
                .image(bdGround)
                .transparency(0.8f); //覆盖物透明度
        //在地图中添加Ground覆盖物
        mBaiduMap.addOverlay(ooGround);
    }
    //自定义热力图
    public static void customHeatMap(BaiduMap mBaiduMap,HeatMap mCustomHeatMap) {
        mBaiduMap.clear();
        //设置渐变颜色值
        int[] DEFAULT_GRADIENT_COLORS = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
        //设置渐变颜色起始值
        float[] DEFAULT_GRADIENT_START_POINTS = {0.2f, 1f};
        //构造颜色渐变对象
        Gradient gradient = new Gradient(DEFAULT_GRADIENT_COLORS, DEFAULT_GRADIENT_START_POINTS);
        //以下数据为随机生成地理位置点，开发者根据自己的实际业务，传入自有位置数据即可
        List<LatLng> randomList = new ArrayList<LatLng>();
        Random r = new Random();
        for (int i = 0; i < 500; i++) {
            // 116.220000,39.780000 116.570000,40.150000 114.404544,30.524177
            int rlat = r.nextInt(3700);
            int rlng = r.nextInt(3700);
            int lat = 30524177 + rlat;
            int lng = 114404544 + rlng;
            LatLng ll = new LatLng(lat / 1E6, lng / 1E6);
            randomList.add(ll);
        }
        //构造HeatMap
        //在大量热力图数据情况下，build过程相对较慢，建议放在新建线程实现
        mCustomHeatMap = new HeatMap.Builder()
                .data(randomList)
                .gradient(gradient)
                .build();
        //在地图上添加自定义热力图
        mBaiduMap.addHeatMap(mCustomHeatMap);

    }

    //批量添加OverLay
    public static void batchAdd(BaiduMap mBaiduMap) {
        mBaiduMap.clear();
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        //构造大量坐标数据
        LatLng point1 = new LatLng(30.527123, 114.405671);
        LatLng point2 = new LatLng(30.526779, 114.405241);
        LatLng point3 = new LatLng(30.526149, 114.40724);
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //创建OverlayOptions属性
        OverlayOptions option1 =  new MarkerOptions()
                .position(point1)
                .icon(bitmap);
        OverlayOptions option2 =  new MarkerOptions()
                .position(point2)
                .icon(bitmap);
        OverlayOptions option3 =  new MarkerOptions()
                .position(point3)
                .icon(bitmap);
        //将OverlayOptions添加到list
        options.add(option1);
        options.add(option2);
        options.add(option3);
        //在地图上批量添加
        mBaiduMap.addOverlays(options);
    }
    //批量删除OverLay
    public static void batchDelete(BaiduMap mBaiduMap) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
    }

    //c城市内搜索
    public static void citySearch(final BaiduMap mBaiduMap) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();
                    //创建PoiOverlay对象

                    PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);
                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);
                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        mPoiSearch.searchInCity(new PoiCitySearchOption()
                .city("武汉") //必填
                .keyword("超市") //必填
                .pageNum(10));
        mPoiSearch.destroy();
    }
    //周边搜索
    public static void nearSearch(final BaiduMap mBaiduMap) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();
                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);
                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);
                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);

        mPoiSearch.searchNearby(new PoiNearbySearchOption()
                .location(new LatLng(30.526779, 114.405241))
                .radius(10000).keyword("超市").pageNum(10));
        mPoiSearch.destroy();
    }
    //矩形搜索
    public static void squareSearch(final BaiduMap mBaiduMap) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        PoiSearch mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();
                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);
                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);
                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }
            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng( 30.530515, 114.400124))
                .include(new LatLng( 30.521742, 114.414515))
                .build();

        mPoiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword("美食"));
        mPoiSearch.destroy();
    }


    //地址转坐标
    public static void locationToCoor(BaiduMap mBaiduMap,final Context context) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        GeoCoder mCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                    if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        //没有检索到结果
                        return;
                    } else {
                        double latitude = geoCodeResult.getLocation().latitude;
                        double longitude = geoCodeResult.getLocation().longitude;
                        AlertDialog.Builder ab=new AlertDialog.Builder(context);  //(普通消息框)
                        ab.setTitle("地理编码（转坐标）");  //设置标题
                        ab.setMessage(latitude+","+longitude);//设置消息内容
                        ab.show();//显示弹出框

                    }
                }
            }
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        };
        mCoder.setOnGetGeoCodeResultListener(listener);
        //city 和 address是必填项
        mCoder.geocode(new GeoCodeOption()
                .city("武汉")
                .address("武汉市洪山区鲁磨路388号中国地质大学"));
        mCoder.destroy();
    }
    //坐标转地址
    public static void coorToLocation(BaiduMap mBaiduMap,final Context context) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        GeoCoder mCoder = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                    return;
                } else {
                    //详细地址
                    String address = reverseGeoCodeResult.getAddress();
                    //行政区号
                    int adCode = reverseGeoCodeResult. getCityCode();
                    AlertDialog.Builder ab=new AlertDialog.Builder(context);  //(普通消息框)
                    ab.setTitle("地理编码（转地址）");  //设置标题
                    ab.setMessage(address);//设置消息内容
                    ab.show();//显示弹出框
                }
            }
        };
        mCoder.setOnGetGeoCodeResultListener(listener);
        LatLng point = new LatLng(30.52632,114.407898);
        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
                .location(point)
                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
                .radius(500));
        mCoder.destroy();
    }

    //不行路径规划
    public static void warkRoutePlan(final BaiduMap mBaiduMap,final Context context) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        RoutePlanSearch mSearch = RoutePlanSearch.newInstance();

        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                if(walkingRouteResult.error==SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR){
                    walkingRouteResult.getSuggestAddrInfo();
                    String aa="";
                    return;

                }
                //创建WalkingRouteOverlay实例
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
                if (walkingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条数据为例)
                    //为WalkingRouteOverlay实例设置路径数据
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    //在地图上绘制WalkingRouteOverlay
                    overlay.addToMap();
                }
            }
            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }
            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }
            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }
            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }
            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };

        mSearch.setOnGetRoutePlanResultListener(listener);
        PlanNode stNode = PlanNode.withLocation(new LatLng(30.526716,114.405536));
        PlanNode enNode = PlanNode.withLocation(new LatLng(30.52441,114.409808));


        mSearch.walkingSearch((new WalkingRoutePlanOption())
                .from(stNode)
                .to(enNode));
        mSearch.destroy();
    }
    //汽车路径规划
    public static void carRoutePlan(final BaiduMap mBaiduMap,final Context context) {
        //清除地图上的所有覆盖物
        mBaiduMap.clear();
        RoutePlanSearch mSearch = RoutePlanSearch.newInstance();

        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
                //创建DrivingRouteOverlay实例
                DrivingRouteOverlay overlay = new DrivingRouteOverlay(mBaiduMap);
                if (drivingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条路线为例）
                    //为DrivingRouteOverlay实例设置数据
                    overlay.setData(drivingRouteResult.getRouteLines().get(0));
                    //在地图上绘制DrivingRouteOverlay
                    overlay.addToMap();
                }
            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };

        mSearch.setOnGetRoutePlanResultListener(listener);
        PlanNode stNode = PlanNode.withLocation(new LatLng(30.526716,114.405536));
        PlanNode enNode = PlanNode.withLocation(new LatLng(30.52441,114.409808));

        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode)
                .to(enNode));
        mSearch.destroy();
    }
}
