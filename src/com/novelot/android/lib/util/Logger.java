package com.novelot.android.lib.util;

import android.util.Log;


public class Logger
{	
	
	
	private static final int I = 1;
	private static final int D = 2;
	private static final int E = 3;
	private static final int V = 4;
	private static final int W = 5;
	
	private static int level = 5;

	public static void i(String tag, String msg)
	{
		if(level >= I)
			Log.i(tag, msg);
		else
			return;
	}
	
	public static void d(String tag, String msg)
	{
		if(level >= D)
			Log.d(tag, msg);
		else
			return;
	}
	
	public static void e(String tag, String msg)
	{
		if(level >= E)
			Log.e(tag, msg);
		else
			return;
	}

	public static void v(String tag, String msg)
	{
		if(level >= V)
			Log.v(tag, msg);
		else
			return;
	}
	public static void w(String tag, String msg)
	{
		if(level >= W)
			Log.w(tag, msg);
		else
			return;
	}
}
