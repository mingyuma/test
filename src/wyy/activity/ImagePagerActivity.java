/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package wyy.activity;

import java.util.List;

import wyy.bean.TimeImage;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imagemanager6.R;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * ViewPager页面显示Activity
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerActivity extends BaseActivity {

	private static final String STATE_POSITION = "STATE_POSITION";

	ViewPager pager;

	// PhotoViewAttacher mAttacher;

	private List<TimeImage> timeimages;

	private List<String> filesPath;
	private List<String> filesPath_names;

	// protected ImageLoader imageLoader = ImageLoader.getInstance();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_image_pager);
		Log.i("wyy", 3.1 + "");
		Bundle bundle = getIntent().getExtras();
		Log.i("wyy", 4 + "");

		// String[] imageUrls = bundle.getStringArray("image");
		filesPath = (List<String>) bundle.getSerializable("image");
		filesPath_names = (List<String>) bundle.getSerializable("name");
		// Log.i("wyy", imageUrls.toString());
		// Log.i("wyy", "pagerSize:" + timeimages.size());
		Log.i("wyy", 5 + "");
		// 当前显示View的位置
		int pagerPosition = bundle.getInt("loc", 0);
		Log.i("wyy", 6 + "");
		// 如果之前有保存用户数据
		if (savedInstanceState != null) {
			pagerPosition = savedInstanceState.getInt(STATE_POSITION);
		}
		Log.i("wyy", 7 + "");
		pager = (ViewPager) findViewById(R.id.pager);
		Log.i("wyy", 8 + "");
		pager.setAdapter(new ImagePagerAdapter());
		Log.i("wyy", 9 + "");
		pager.setCurrentItem(pagerPosition); // 显示当前位置的View
		Log.i("wyy", 10 + "");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// 保存用户数据
		outState.putInt(STATE_POSITION, pager.getCurrentItem());
	}

	private class ImagePagerAdapter extends PagerAdapter {

		private LayoutInflater inflater;

		public ImagePagerAdapter() {
			inflater = LayoutInflater.from(ImagePagerActivity.this);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return filesPath.size();
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			Log.i("wyy", "nonon1");
			View imageLayout = inflater.inflate(R.layout.item_pager_image,
					view, false);

			Log.i("wyy", "nonon2");
			ImageView imageView = (ImageView) imageLayout
					.findViewById(R.id.image);
			Log.i("wyy", "nonon3");

			TextView textview = (TextView) imageLayout
					.findViewById(R.id.item_pager_name);
			final ProgressBar spinner = (ProgressBar) imageLayout
					.findViewById(R.id.loading);
			textview.setText(filesPath_names.get(position));
			Log.i("wyy", "file://" + filesPath.get(position));
			imageLoader.displayImage("file://" + filesPath.get(position),
					imageView, options, new SimpleImageLoadingListener() {
						@Override
						public void onLoadingStarted(String imageUri, View view) {
							spinner.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							String message = null;
							switch (failReason.getType()) { // 获取图片失败类型
							case IO_ERROR: // 文件I/O错误
								message = "Input/Output error";
								break;
							case DECODING_ERROR: // 解码错误
								message = "Image can't be decoded";
								break;
							case NETWORK_DENIED: // 网络延迟
								message = "Downloads are denied";
								break;
							case OUT_OF_MEMORY: // 内存不足
								message = "Out Of Memory error";
								break;
							case UNKNOWN: // 原因不明
								message = "Unknown error";
								break;
							}
							Toast.makeText(ImagePagerActivity.this, message,
									Toast.LENGTH_SHORT).show();

							spinner.setVisibility(View.GONE);

						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							spinner.setVisibility(View.GONE); // 不显示圆形进度条
						}
					});

			((ViewPager) view).addView(imageLayout, 0); // 将图片增加到ViewPager

			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
}