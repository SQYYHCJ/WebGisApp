package top.sqyy.webgis;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.baidu.mapapi.map.ArcOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

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
        MapStatus mMapStatus = new MapStatus.Builder().target(currentLocation).zoom(19).build();
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
}