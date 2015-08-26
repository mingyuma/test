package com.zhy.imageloader;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.imagemanager6.R;
import com.zhy.utils.CommonAdapter;

public class MyAdapter extends CommonAdapter<String> {

	/**
	 * 鐢ㄦ埛閫夋嫨鐨勫浘鐗囷紝瀛樺偍涓哄浘鐗囩殑�?�屾暣璺緞
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();

	/**
	 * 鏂囦欢澶硅矾寰�
	 */
	private String mDirPath;
	private Activity mActivity;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId,
			String dirPath, Activity activity) {
		super(context, mDatas, itemLayoutId);
		mActivity = activity;
		this.mDirPath = dirPath;
	}

	@Override
	public void convert(final com.zhy.utils.ViewHolder helper, final String item) {
		// 璁剧疆no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		// 璁剧疆no_selected
		helper.setImageResource(R.id.id_item_select,
				R.drawable.picture_unselected);
		// 璁剧疆鍥剧墖
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		// 璁剧疆ImageView鐨勭偣鍑讳簨浠�
		mImageView.setOnClickListener(new OnClickListener() {
			// 閫夋嫨锛屽垯灏嗗浘鐗囧彉鏆楋紝鍙嶄箣鍒欏弽涔�?
			@Override
			public void onClick(View v) {
				/**
				 * wyy begin
				 */
				mSelectedImage.add(mDirPath + "/" + item);
				Intent intent = new Intent();
				intent.putExtra("image", mSelectedImage.get(0));
				mActivity.setResult(16, intent);

				

				mActivity.finish();
				/**
				 * wyy finish
				 */
				// 宸茬粡閫夋嫨杩囪鍥剧墖
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
				} else
				// 鏈�夋嫨璇ュ浘鐗�
				{
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}

			}
		});

		/**
		 * 宸茬粡閫夋嫨杩囩殑鍥剧墖锛屾樉绀哄嚭閫夋嫨杩囩殑鏁堟�?
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
}
