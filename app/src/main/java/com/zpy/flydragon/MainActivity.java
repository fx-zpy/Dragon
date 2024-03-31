package com.zpy.flydragon;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zpy.dragonsdk.handler.ISpHandler;
import com.zpy.dragonsdk.handler.SpHandler;
import com.zpy.dragonsdk.util.HiLog;
import com.zpy.dragonsdk.util.SystemUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static TextView SCREEN_HEIGHT;

    private static TextView SCREEN_WIDTH;

    private static TextView SCREEN_DENSITY;

    private static TextView MEMORY;

    private static TextView INTERNAL_MEMORY;

    private static TextView WIFI;

    private static Button GET_INFO;

    private static Button DESTROY_INFO;

    private static Context context;

    private static Button TAKE_TO_BASIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HiLog.d(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        ISpHandler spHandler=SpHandler.getInstance(context);
        spHandler.setLoginTime(System.currentTimeMillis());
        initView();
        listenClick();
    }

    private void listenClick() {
        GET_INFO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAllInfo();
            }
        });

        DESTROY_INFO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destroyInfo();
            }
        });

    }

    private void destroyInfo() {
        SCREEN_HEIGHT.setText(null);
        SCREEN_WIDTH.setText(null);
        SCREEN_DENSITY.setText(null);
        MEMORY.setText(null);
        INTERNAL_MEMORY.setText(null);
        WIFI.setText(null);
    }

    private void getAllInfo() {
        Pair<Integer, Integer> pair = SystemUtil.getScreenHW(context);
        SCREEN_HEIGHT.setText("屏幕高度是 " + pair.first);
        SCREEN_WIDTH.setText("屏幕宽度是 " + pair.second);
        SCREEN_DENSITY.setText("屏幕的像素密度是 " + SystemUtil.getScreenDensity(context));
        MEMORY.setText("可用运行内存为 " + SystemUtil.getAvailMemory(context) + " / 总的运行内存是 " + SystemUtil.getTotalMemory(context));
        INTERNAL_MEMORY.setText("可用内部内存为 " + SystemUtil.getAvailableInternalMemorySize(context) + " / 总的内部内存是" + SystemUtil.getInternalMemorySize(context));
        //WIFI.setText("wifi采集的信息是" + SystemUtil.getWifiScanInfo(context));
    }

    private void initView() {
        SCREEN_HEIGHT = findViewById(R.id.screenHeight);
        SCREEN_WIDTH = findViewById(R.id.screenWidth);
        SCREEN_DENSITY = findViewById(R.id.screenDensity);
        MEMORY = findViewById(R.id.memory);
        INTERNAL_MEMORY = findViewById(R.id.internalMemory);
        WIFI = findViewById(R.id.wifi);
        GET_INFO = findViewById(R.id.getInfo);
        DESTROY_INFO = findViewById(R.id.destroyInfo);
    }

}