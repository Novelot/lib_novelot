package com.novelot.android.lib.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class MediaUtils
{
	
	private static final String TAG = "MediaUtils";
	private static ArrayList<MusicData> mAllMusicList;
	private static ArrayList<ImageInfo> mAllImageList;
	private static Map<String,List<MusicData>> mSingerMap;
	private static Map<String,List<MusicData>> mAlbumMap;

	private MediaUtils(){}
	
	/**
	 * 获取手机上所有的音乐文件
	 * @param context
	 * @return
	 */
	public static ArrayList<MusicData> getAllMusicList(Context context)
	{
		
		if(mAllMusicList != null) return mAllMusicList;
		
		mAllMusicList = new ArrayList<MusicData>();

		String[] projection = new String[] { MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM };

		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null,
				null, null);
		
		if(cursor != null)
		{
			cursor.moveToFirst();

			int colNameIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.TITLE);
			int colTimeIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.DURATION);
			int colPathIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.DATA);
			int colArtistIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST);
			int colAlbumIndex = cursor
					.getColumnIndex(MediaStore.Audio.Media.ALBUM);

			int fileNum = cursor.getCount();
			for (int counter = 0; counter < fileNum; counter++)
			{

				MusicData data = new MusicData();
				data.mMusicName = cursor.getString(colNameIndex);
				data.mMusicTime = cursor.getInt(colTimeIndex);
				data.mMusicPath = cursor.getString(colPathIndex);
				data.mMusicAritst = cursor.getString(colArtistIndex);
				data.mMusicAlbum = cursor.getString(colAlbumIndex);

				mAllMusicList.add(data);
				cursor.moveToNext();
			}

			cursor.close();
		}
		Logger.i(TAG, "getAllMusicList");
		return mAllMusicList;
	}
	
	/**
	 * 获得歌手名称为key的map集合
	 * @return
	 */
	public static Map<String,List<MusicData>> getSingerMap(Context context)
	{
		if(mSingerMap != null) return mSingerMap;
		mSingerMap = new HashMap<String, List<MusicData>>();
		
		if(mAllMusicList == null)
			if(getAllMusicList(context)==null) return null;
		
		
		Set<String> singerSet = new HashSet<String>();
		
		for (MusicData musicData : mAllMusicList)
		{
			String singer = musicData.mMusicAritst;
			singerSet.add(singer);
		}
		
		for (String singerName : singerSet)
		{
			List<MusicData> musicSingerList = new ArrayList<MusicData>();
			mSingerMap.put(singerName, musicSingerList);
		}
		
		for (MusicData musicData: mAllMusicList)
		{
			String singer = musicData.mMusicAritst;
			mSingerMap.get(singer).add(musicData);
		}
		
		return mSingerMap;
	}
	
	/**
	 * 获得专辑名称为key的map集合
	 * @return
	 */
	public static Map<String,List<MusicData>> getAlbumMap(Context context)
	{
		
		if(mAlbumMap != null) return mAlbumMap;
		mAlbumMap = new HashMap<String, List<MusicData>>();
		
		if(mAllMusicList == null)
			if(getAllMusicList(context)==null) return null;
		
		
		Set<String> albumSet = new HashSet<String>();
		
		for (MusicData musicData : mAllMusicList)
		{
			String album = musicData.mMusicAlbum;
			albumSet.add(album);
		}
		
		for (String album : albumSet)
		{
			List<MusicData> musicAlbumList = new ArrayList<MusicData>();
			mAlbumMap.put(album, musicAlbumList);
		}
		
		for (MusicData musicData: mAllMusicList)
		{
			String album = musicData.mMusicAlbum;
			mAlbumMap.get(album).add(musicData);
		}
		
		return mAlbumMap;
	}
	
	

	/**
	 * 获取手机上所有的图片
	 * @param context
	 * @return
	 */
	public static ArrayList<ImageInfo> getAllImageList(Context context)
	{
		if(mAllImageList != null) return mAllImageList;
		
		mAllImageList = new ArrayList<ImageInfo>();
		String[] STORE_IMAGES = {
				MediaStore.Images.Media._ID,
		        MediaStore.Images.Media.DISPLAY_NAME,
		        MediaStore.Images.Media.LATITUDE,
		        MediaStore.Images.Media.DATA,
		        MediaStore.Images.Media.LONGITUDE
		    };
		
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES, null,
				null, null);
		
		if(cursor != null)
		{
			cursor.moveToFirst();

			int colNameIndex = cursor
					.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
			int colLatitudeIndex = cursor
					.getColumnIndex(MediaStore.Images.Media.LATITUDE);
			int colLongitudeIndex = cursor
					.getColumnIndex(MediaStore.Images.Media.LONGITUDE);
			int colPathIndex = cursor
					.getColumnIndex(MediaStore.Images.Media.DATA);
			
			int fileNum = cursor.getCount();
			
			for (int counter = 0; counter < fileNum; counter++)
			{

				ImageInfo data = new ImageInfo();
				data.mImageName = cursor.getString(colNameIndex);
				data.mImageLatitude = cursor.getInt(colLatitudeIndex);
				data.mImageLongitude = cursor.getString(colLongitudeIndex);
				data.mImagePath = cursor.getString(colPathIndex);

				mAllImageList.add(data);
				cursor.moveToNext();
			}

			cursor.close();
		}
		return mAllImageList;
	}
	
	
}



