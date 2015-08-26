package com.xd.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.imagemanager6.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

public class ImageLoader {

	private LruCache<String, Bitmap> mMemoryCache;  

	public ImageLoader(){
		int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);  
	    // ʹ���������ڴ�ֵ��1/8��Ϊ����Ĵ�С��   
	    int cacheSize = maxMemory / 16;  
	    mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {  
	        @Override  
	        protected int sizeOf(String key, Bitmap bitmap) {  
	            // ��д�˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ������   
	            return bitmap.getByteCount() / 1024;  
	        }  
	    };  
	}
	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {  
	    if (getBitmapFromMemCache(key) == null) {  
	        mMemoryCache.put(key, bitmap);  
	    }  
	}  
	  
	public Bitmap getBitmapFromMemCache(String key) {  
	    return mMemoryCache.get(key);  
	}  

	public void loadBitmap(String url, ImageView imageView) {  
	    
	    final Bitmap bitmap = getBitmapFromMemCache(url);  
	    if (bitmap != null) {  
	        imageView.setImageBitmap(bitmap);  
	    } else {  
	        imageView.setImageResource(R.drawable.ic_launcher);  
	        BitmapWorkerTask task = new BitmapWorkerTask(imageView);  
	        task.execute(url);  
	    }  
	}  

	//��̨����ͼƬ
	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {  
	    private ImageView imageView;
	    public BitmapWorkerTask(ImageView imageView){
	    	this.imageView=imageView;
	    }
	    @Override  
	    protected Bitmap doInBackground(String... params) {  
	        
	    	Options options=new Options();
	    	Bitmap bitmap=null;
			try {
				bitmap = BitmapFactory.decodeStream(new URL(params[0]).openStream(), null, options);
				addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);  
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return bitmap;  
	    }
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			if(result==null){
				Log.i("liuhaoxian","��ȡͼƬʧ��");
			}
			else
			{
				imageView.setImageBitmap(result);
			}
		}  
	    
	}  

}

