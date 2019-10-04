package com.tubug.game.one2fifty.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


public class AppUtils {
    /**
     * 获取当前app version code
     */
    public static long getAppVersionCode(Context context) {
        long appVersionCode = 0;
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                appVersionCode = packageInfo.getLongVersionCode();
            } else {
                appVersionCode = packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return appVersionCode;
    }

    /**
     * 获取当前app version name
     */
    public static String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return appVersionName;
    }

    public static String getMetadata(Context context,String metaKey){
        ApplicationInfo appInfo = null;
        String channelValueStr;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object channelId = appInfo.metaData.get(metaKey);
            if(channelId != null){
                channelValueStr = channelId.toString();
            }else{
                channelValueStr =  "google";
            }
            return channelValueStr;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void toShareGooglePlay(Context pContext,String text2Share){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        StringBuilder sb = new StringBuilder();
        sb.append(text2Share);
        sb.append("\n");
        sb.append("https://play.google.com/store/apps/details?id=");
        sb.append(pContext.getPackageName());

        intent.putExtra("android.intent.extra.TEXT", sb.toString());
        pContext.startActivity(Intent.createChooser(intent, "Share"));
    }
    public static void toShare(Context pContext,String text2Share){
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        StringBuilder sb = new StringBuilder();
        sb.append(text2Share);

        intent.putExtra("android.intent.extra.TEXT", sb.toString());
        pContext.startActivity(Intent.createChooser(intent, "Share"));
    }
}