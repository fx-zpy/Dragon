package com.zpy.dragonsdk.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


/**
 * 设置一些系统的函数
 */
public class SystemUtil {
    private static final String TAG = "SystemUtil";

    /**
     * 获取当前屏幕的高度和宽度
     *
     * @param context
     * @return pair.first 高度，pair.second 宽度
     */
    public static Pair<Integer, Integer> getScreenHW(Context context) {
        // 获取系统服务
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取显示参数保存在dm中
        wm.getDefaultDisplay().getMetrics(dm);
        Pair<Integer, Integer> pair = new Pair<>(dm.heightPixels, dm.widthPixels);
        return pair;
    }

    /**
     * 获取当前屏幕的像素密度
     *
     * @param context
     * @return
     */
    public static float getScreenDensity(Context context) {
        // 获取系统服务
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        // 获取显示参数保存在dm中
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.density * 160;
    }

    /**
     * 获取当前可用运行内存的大小
     *
     * @param context
     * @return
     */
    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     * 获取总运行内存大小
     *
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {
        // 系统内存信息文件
        String systemInfoFile = "/proc/meminfo";
        String str;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader fileReader = new FileReader(systemInfoFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader, 8192);
            // 读取meminfo第一行，系统总内存大小
            str = bufferedReader.readLine();
            arrayOfString = str.split("\\s+");
            int i = Integer.valueOf(arrayOfString[1]);
            initial_memory = (long) i * 1024;
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Formatter.formatFileSize(context, initial_memory);
    }

    /**
     * 获取手机内部可用的存储空间
     *
     * @param context
     * @return
     */
    public static String getAvailableInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long availableBlocks = statFs.getAvailableBlocksLong();
        long blockSize = statFs.getBlockSizeLong();
        return Formatter.formatFileSize(context, availableBlocks * blockSize);
    }

    /**
     * 获取手机内部存储空间
     *
     * @param context
     * @return
     */
    public static String getInternalMemorySize(Context context) {
        File file = Environment.getDataDirectory();
        StatFs statFs = new StatFs(file.getPath());
        long blockCount = statFs.getBlockCountLong();
        long blockSize = statFs.getBlockSizeLong();
        return Formatter.formatFileSize(context, blockSize * blockCount);
    }

    @SuppressLint("MissingPermission")
    public static String getWifiScanInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();
        List<ScanResult> scanResult = wifiManager.getScanResults();
        Log.i(TAG, "wifi scan result's size is " + scanResult.size());
        StringBuilder stringBuilder = new StringBuilder();
        int returnSize = 5;
        while (returnSize > 0) {
            for (ScanResult result : scanResult) {
                stringBuilder.append("SSID: ").append(result.SSID).append("\n")
                        .append("BSSID: ").append(result.BSSID).append("\n")
                        .append("RSSI: ").append(result.level).append(" dBm\n")
                        .append("\n");
                returnSize--;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 判断字符串是否为blank
     *
     * @param s
     * @return
     */
    public boolean isBlank(String s) {
        return s == null || s.length() == 0;
    }


}
