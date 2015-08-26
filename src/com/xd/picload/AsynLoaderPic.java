package com.xd.picload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.imagemanager6.R;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import com.xd.util.ImageSettingUtil;
public class AsynLoaderPic{
	private String path;
	private Context context;
	/**
	 * create liuhaoixan
	 * 2013/8/22
	 */
	//鍥剧墖缂撳啿鍖�
	public Map<String,SoftReference<Drawable>> cache=new HashMap<String,SoftReference<Drawable>>();
	public AsynLoaderPic(Context context)
	{
//		path = new TakePhotoUtil(context,"pictrues").getImgPathParent()+"/";
//		//鍒涘缓鍥剧墖鏂囦欢澶�?
//		File file=new File(path);
//		if(!file.exists())
//			file.mkdir();
		this.context=context;
	}
	//鍔犺浇鍥剧墖
    public Drawable getDrawable(final String url,final ImageView view)
    {
    	
    	if(cache.containsKey(url))
    	{
    		SoftReference<Drawable> sRef=cache.get(url);
    		Drawable drawable=sRef.get();
    		if(drawable!=null)
    			return drawable;
    		else
    			cache.remove(url);
    	}
    
    	final Handler myHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what)
				{
				    case 0:
					   if(msg.obj!=null)
					   {
						   view.setImageDrawable((Drawable)msg.obj);
						  
					   }
					   else{
//						   Toast.makeText(context,"澶村儚鍔犺浇澶辫�?", Toast.LENGTH_SHORT).show();
						   Drawable drawable = context.getResources().getDrawable(R.drawable.image_default);//鐢ㄥ亣鍥剧墖鏉ユ楠�?
						   view.setImageDrawable(drawable);
					   }
					break;
					default:;
				}
			}
    	
    	};
    	
    	new Thread()//涓嬭浇鍥剧墖
    	{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				    System.out.println("URL--------->"+url);
				    Drawable drawable;
//				    InputStream in=ConnectUtil.httpRequest(url,null,ConnectUtil.GET);
				    try{
				    	InputStream in=null;
				    	//
				    	if(url.startsWith("http:")){
				    		in=new URL(url).openStream();
				    		Log.i("liuhaoxian","pic size="+in.available());
				    		BitmapFactory.Options bitmapOptions=new BitmapFactory.Options();
				    		if(in.available()>=2*1024*1024)
				        	   bitmapOptions.inSampleSize=4;
				    		else
				    		    bitmapOptions.inSampleSize=2;
				        	Bitmap bitmap=BitmapFactory.decodeStream(new URL(url).openStream(),null,bitmapOptions);
				        	drawable=new BitmapDrawable(bitmap);
				    	}
				    	else
				    	{
				    		//in=new FileInputStream(new File(url));
				    		in=ImageSettingUtil.compressJPG(url);
				    		
				    		Bitmap bitmap=BitmapFactory.decodeStream(in);
				        	drawable=new BitmapDrawable(bitmap);
				    		
				    	}
				    	

				    }catch(Exception e){
				    	e.printStackTrace();
				    	drawable=null;
				    }
					Message msg=new Message();
					msg.what=0;
					msg.obj=drawable;
					myHandler.sendMessage(msg);
					if(drawable!=null)
					    cache.put(url, new SoftReference<Drawable>(drawable));
					//瀛樺埌鏈�?�?
//					if(1==head_photo&&drawable!=null)
//				     writeToSDcard(drawable,"head_photo.png");
			}
    	}.start();
    	return null;
    }
//    //灏咲rawable鍥剧墖鍐欏叆SDcard
//    public void writeToSDcard(Drawable drawable,String fileName)
//    {   
//    	System.out.println("fileName = "+fileName);
//    	System.out.println("drawable = "+drawable);
//    	
//    	File file=new File(path+fileName);
//    	if (!file.exists()) {
//			File fileParent = file.getParentFile();
//			fileParent.mkdirs();
//		}
//    	
//    	System.out.println("file = "+file);
//    	
//    	OutputStream out=null;
//    	try {
//			out=new FileOutputStream(file);
//			BitmapDrawable bd=(BitmapDrawable)drawable;
//			Bitmap x=bd.getBitmap();
//			out.write(bitMapToByte(x));
//			out.flush();
//			System.out.println("鍥剧墖鍐欏叆鎴愬�?");
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	finally{
//    		try {
//				out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	}
//    		
//    }
    //灏哹itmap杞寲涓篵yte[]
    private byte[] bitMapToByte(Bitmap bm)
    {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
    	return baos.toByteArray();
    }
    //浠嶴Dcard涓緱鍒板浘鐗�
    public Drawable getDrawableFromLocal(String fileName)
    {
    	Drawable drawable=null;
    	File file=new File(path+fileName);
    	if(file.exists())
    	{
    	    BitmapFactory.Options opts = new BitmapFactory.Options();
    	    opts.inSampleSize = 4;
    		Bitmap bm=BitmapFactory.decodeFile(path+fileName,opts);
        	drawable= new BitmapDrawable(bm);
        	System.out.println("---------->In local");
    	}
    	return drawable;
    }  

}
