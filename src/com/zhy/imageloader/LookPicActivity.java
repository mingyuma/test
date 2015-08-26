package com.zhy.imageloader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imagemanager6.R;
import com.zhy.bean.ImageFloder;
import com.zhy.imageloader.ListImageDirPopupWindow.OnImageDirSelected;

public class LookPicActivity extends Activity implements OnImageDirSelected {
	private ProgressDialog mProgressDialog;

	/**
	 * 瀛樺偍鏂囦欢澶�?�腑鐨勫浘鐗囨暟閲�
	 */
	private int mPicsSize;
	/**
	 * 鍥剧墖鏁伴噺鏈�澶氱殑鏂囦欢澶�
	 */
	private File mImgDir;
	/** 
	 * 鎵�鏈夌殑鍥剧墖
	 */
	private List<String> mImgs;

	private GridView mGirdView;
	private MyAdapter mAdapter;
	/**
	 * 涓存椂鐨勮緟鍔╃被锛�?敤浜庨槻姝㈠悓涓�涓枃浠跺す鐨勫娆℃壂鎻�?
	 */
	private HashSet<String> mDirPaths = new HashSet<String>();

	/**
	 * 鎵弿鎷垮埌鎵�鏈夌殑鍥剧墖鏂囦欢澶�?
	 */
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

	private RelativeLayout mBottomLy;

	private TextView mChooseDir;
	private TextView mImageCount;
	int totalCount = 0;

	private int mScreenHeight;

	private ListImageDirPopupWindow mListImageDirPopupWindow;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressDialog.dismiss();
			// 涓篤iew缁戝畾鏁版嵁
			data2View();
			// 鍒濆鍖栧睍�?烘枃浠跺す鐨刾opupWindw
			initListDirPopupWindw();
		}
	};

	/**
	 * 涓篤iew缁戝畾鏁版嵁
	 */
	private void data2View() {
		if (mImgDir == null) {
			Toast.makeText(getApplicationContext(), "鎿︼紝涓�寮犲浘鐗囨病鎵弿鍒�",
					Toast.LENGTH_SHORT).show();
			return;
		}

		mImgs = Arrays.asList(mImgDir.list());
		/**
		 * 鍙互鐪嬪埌鏂囦欢澶圭殑璺緞鍜屽浘鐗囩殑璺緞鍒嗗紑淇濆瓨锛屾瀬澶х殑鍑忓皯浜嗗唴�?�樼殑娑堣�楋紱
		 */
		mAdapter = new MyAdapter(getApplicationContext(), mImgs,
				R.layout.grid_item, mImgDir.getAbsolutePath(), this);
		mGirdView.setAdapter(mAdapter);
		mImageCount.setText(totalCount + "寮�");
	};

	/**
	 * 鍒濆鍖栧睍�?烘枃浠跺す鐨刾opupWindw
	 */
	private void initListDirPopupWindw() {
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
				mImageFloders, LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.list_dir, null));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				// 璁剧疆鑳屾櫙棰滆壊鍙樻殫
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
			}
		});
		// 璁剧疆閫夋嫨鏂囦欢澶圭殑鍥炶�?
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookpic);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;

		initView();
		getImages();
		initEvent();

	}

	/**
	 * 鍒╃敤ContentProvider鎵弿鎵嬫満涓殑鍥剧墖锛屾鏂规硶鍦ㄨ繍琛屽湪瀛愮嚎绋嬩腑 瀹屾垚鍥剧墖鐨勬壂鎻忥紝鏈�缁堣幏寰梛pg鏈�澶氱殑閭ｄ釜鏂囦欢澶�?
	 */
	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "鏆傛棤澶栭儴瀛樺�?", Toast.LENGTH_SHORT).show();
			return;
		}
		// 鏄剧ず杩涘害鏉�
		mProgressDialog = ProgressDialog.show(this, null, "姝ｅ湪鍔犺浇...");

		new Thread(new Runnable() {
			@Override
			public void run() {

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = LookPicActivity.this
						.getContentResolver();

				// 鍙煡璇peg鍜宲ng鐨勫浘鐗�?
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext()) {
					// 鑾峰彇鍥剧墖鐨勮矾寰�?
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					Log.e("TAG", path);
					// 鎷垮埌绗竴寮犲浘鐗囩殑璺�?
					if (firstImage == null)
						firstImage = path;
					// 鑾峰彇璇ュ浘鐗囩殑鐖惰矾寰勫�?
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					// 鍒╃敤涓�涓狧ashSet闃叉澶氭鎵弿鍚屼竴涓枃浠跺す锛堜笉鍔犺繖涓垽鏂紝鍥剧墖澶氳捣鏉ヨ繕鏄浉褰撴亹鎬栫殑~~锛�
					if (mDirPaths.contains(dirPath)) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						// 鍒濆鍖杋mageFloder
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImagePath(path);
					}

					int picSize = parentFile.list(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String filename) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg")||filename.endsWith(".bmp"))
								return true;
							return false;
						}
					}).length;
					totalCount += picSize;

					imageFloder.setCount(picSize);
					mImageFloders.add(imageFloder);

					if (picSize > mPicsSize) {
						mPicsSize = picSize;
						mImgDir = parentFile;
					}
				}
				mCursor.close();

				// 鎵弿�?�屾垚锛岃緟鍔╃殑HashSet涔熷氨鍙互閲婃斁鍐呭瓨浜�
				mDirPaths = null;

				// 閫氱煡Handler鎵弿鍥剧墖瀹屾�?
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();

	}

	/**
	 * 鍒濆鍖朧iew
	 */
	private void initView() {
		mGirdView = (GridView) findViewById(R.id.id_gridView);
		mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
		mImageCount = (TextView) findViewById(R.id.id_total_count);

		mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);

	}

	private void initEvent() {
		/**
		 * 涓哄簳閮ㄧ殑甯冨眬璁剧疆鐐瑰嚮浜嬩欢锛屽脊鍑簆opupWindow
		 */
		mBottomLy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListImageDirPopupWindow
						.setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

				// 璁剧疆鑳屾櫙棰滆壊鍙樻殫
				WindowManager.LayoutParams lp = getWindow().getAttributes();
				lp.alpha = .3f;
				getWindow().setAttributes(lp);
			}
		});
	}

	@Override
	public void selected(ImageFloder floder) {

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		/**
		 * 鍙互鐪嬪埌鏂囦欢澶圭殑璺緞鍜屽浘鐗囩殑璺緞鍒嗗紑淇濆瓨锛屾瀬澶х殑鍑忓皯浜嗗唴�?�樼殑娑堣�楋紱
		 */
		mAdapter = new MyAdapter(getApplicationContext(), mImgs,
				R.layout.grid_item, mImgDir.getAbsolutePath(), this);
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		mImageCount.setText(floder.getCount() + "寮�");
		mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();

	}

}
