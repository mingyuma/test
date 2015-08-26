package wyy.application;

import java.io.File;
import java.util.LinkedList;

import org.litepal.LitePalApplication;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.xd.Vos.UserVo;


public class MyApplication extends LitePalApplication {


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		super.onCreate();
		initImageLoader(getApplicationContext());
		Log.i("wyy", "initImageLoader");
	}


	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		File cache = StorageUtils.getOwnCacheDirectory(context,
				"imagemanager5/image/cache/");
		Log.i("wyy", "cache path::" + cache.getAbsolutePath());
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				// default = device screen dimensions
				.diskCacheExtraOptions(480, 800, null)
				.threadPoolSize(3)
				// default
				.threadPriority(Thread.NORM_PRIORITY - 2)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13)
				// default // default
				.diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(100)
				.diskCache(new UnlimitedDiscCache(cache))
				.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(context)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}


	// 用户信息 add by Bryan 2013-8-22
	private UserVo userInfo = null;

	public UserVo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserVo userInfo) {
		this.userInfo = userInfo;
	}
	
	//cookie验证成功与否的标志 add by wangjun 2013-10-15
	private boolean isCookieValidation=false;
	
	public boolean isCookieValidation() {
		return isCookieValidation;
	}

	public void setCookieValidation(boolean isCookieValidation) {
		this.isCookieValidation = isCookieValidation;
	}

	// 相机拍照图片路径
	private String imgPath = "";
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}


	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	//---lhx-----添加删除图片的标志
	private boolean isDeletePic;

	/**
	 * @return the isDeletePic
	 */
	public boolean isDeletePic() {
		return isDeletePic;
	}

	/**
	 * @param isDeletePic the isDeletePic to set
	 */
	public void setDeletePic(boolean isDeletePic) {
		this.isDeletePic = isDeletePic;
	}
    //添加选择图片的uir
	public Uri uri;
	public Uri getUri() {
		return uri;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

}
