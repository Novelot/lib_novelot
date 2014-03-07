package com.novelot.android.lib.util;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

/**
 * 网络类型判断
 * 
 * @author Administrator
 * 
 */
public class NetUtil {
	private static final String TAG = "NetUtil";

	/**
	 * 检查网络
	 * @param context
	 * @return
	 */
	public static boolean isNetConnected(Context context) {
		// 区分ＷＩＦＩ和ＭＯＢＩＬＥ

		boolean isWifi = isWIFIConnected(context);
		boolean isApn = isAPNConnected(context);

		if (isApn == false && isWifi == false) {
			// 无网络
			return false;
		}

//		if (isApn) {
//			// 区分Wap和Net
//			// 获取apn配置信息：代理的ip和端口，如果非空wap，空net
//			readApn(context);
//
//		}

		return true;

	}

	private static Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");

	/**
	 * 读取代理ip和端口
	 */
//	private static void readApn(Context context) {
//		// 读取是当前正在处于连接状态的Apn的配置信息
//		// 读取方式：联系人
//		ContentResolver contentResolver = context.getContentResolver();
//
//		Cursor query = contentResolver.query(PREFERRED_APN_URI, null, null, null, null);
//
//		if (query != null && query.moveToFirst()) {
//			GloableParams.PROXY_IP = query.getString(query.getColumnIndex("proxy"));
//			GloableParams.PROXY_PORT = query.getInt(query.getColumnIndex("port"));
//			
//			Log.i(TAG, "ip:"+GloableParams.PROXY_IP+"port:"+GloableParams.PROXY_PORT);
//		}
//
//	}

	// wifi mobile（apn）
	/**
	 * WIFI是否处于连接状态
	 * 
	 * @param context connected
	 * @return
	 */
	public static boolean isWIFIConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// WIFI的描述信息：NetworkInfo
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (networkInfo != null)
			return networkInfo.isConnected();
		return false;
	}

	/**
	 * Mobile(apn)是否处于连接状态
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isAPNConnected(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// WIFI的描述信息：NetworkInfo
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (networkInfo != null)
			return networkInfo.isConnected();
		return false;
	}
}
