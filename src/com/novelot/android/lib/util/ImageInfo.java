package com.novelot.android.lib.util;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageInfo implements Parcelable
{

	private static final String KEY_IMAGE_NAME = "ImageName";
	private static final String KEY_IMAGE_LATITUDE = "ImageLatitude";
	private static final String KEY_IMAGE_LONGITUDE = "ImageLongitude";
	private static final String KEY_IMAGE_PATH = "ImagePath";
	
	public String mImageName;
	public int mImageLatitude;
	public String mImageLongitude;
	public String mImagePath;//图片路径

	public ImageInfo()
	{
		super();
		this.mImageName = "";
		this.mImageLatitude = 0;
		this.mImageLongitude = "";
		this.mImagePath = "";
	}

	@Override
	public int describeContents()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		// TODO Auto-generated method stub
		Bundle mBundle = new Bundle();

		mBundle.putString(KEY_IMAGE_NAME, mImageName);
		mBundle.putInt(KEY_IMAGE_LATITUDE, mImageLatitude);
		mBundle.putString(KEY_IMAGE_LONGITUDE, mImageLongitude);
		mBundle.putString(KEY_IMAGE_PATH, mImagePath);
		dest.writeBundle(mBundle);
	}

}
