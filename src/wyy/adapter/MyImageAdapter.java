package wyy.adapter;

import java.util.ArrayList;
import java.util.List;

import wyy.bean.Condition;

import com.example.imagemanager6.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xd.picload.AsynLoaderPic;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyImageAdapter extends BaseAdapter {
	private List<Condition> conditions;
	private Context mContext;
	private AsynLoaderPic loader;// Í¼Æ¬¼ÓÔØÆ÷
	Holder holder = null;
	DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	List<ImageView> list ;
	List<TextView> tlist ;
	public MyImageAdapter(List<Condition> conditions, Context mContext) {
		this.conditions = conditions;
		this.mContext = mContext;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		// return imageUrls.length;
		return conditions.size();
		// return timeimages.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return conditions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if (convertView == null) {
			LayoutInflater layout = LayoutInflater
					.from(mContext);
			convertView = layout.inflate(R.layout.item_medicalrecord, null);
			holder = new Holder();
			holder.date = (TextView) convertView
					.findViewById(R.id.item_date);
			holder.dignose = (TextView) convertView
					.findViewById(R.id.item_dignose);
			holder.remarks = (TextView) convertView
					.findViewById(R.id.item_remarks);
			holder.dis1 = (TextView)convertView.findViewById(R.id.remarks_1);
			holder.dis2 = (TextView)convertView.findViewById(R.id.remarks_2);
			holder.dis3 = (TextView)convertView.findViewById(R.id.remarks_3);
			holder.image1 = (ImageView) convertView.findViewById(R.id.item_medicalrecord_image1);
			holder.image2 = (ImageView) convertView.findViewById(R.id.item_medicalrecord_image2);
			holder.image3 = (ImageView) convertView.findViewById(R.id.item_medicalrecord_image3);
			
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.date.setText(conditions.get(position).getDate());
		if(conditions.get(position).getDignose().equals("")){
			holder.dignose.setVisibility(View.GONE);
		}
		else{
			holder.dignose.setText("Ö¢×´£º"+ conditions.get(position).getDignose());
		}
		if(conditions.get(position).getRemarks().equals("")){
			holder.remarks.setVisibility(View.GONE);
		}
		else{
			holder.remarks.setText("±¸×¢£º"+ conditions.get(position).getRemarks());
		}
		list = new ArrayList<ImageView>();
		list.add(holder.image1);
		list.add(holder.image2);
		list.add(holder.image3);
		tlist = new ArrayList<TextView>();
		tlist.add(holder.dis1);
		tlist.add(holder.dis2);
		tlist.add(holder.dis3);
		for (ImageView imageView : list) {
			imageView.setVisibility(View.INVISIBLE);
		}
		for (TextView textView : tlist) {
			textView.setVisibility(View.INVISIBLE);
		}
		//conditions = people.getConditions();
		Condition condition = conditions.get(position);
		Log.i("wyy1", "conditions.size():" + conditions.size());
		for(int i=0;i<condition.getImageNum();i++){
			//Log.i("mmm", "---"+conditions.get(position).getImageList().get(i).getImagePath());
			//getPicFromRemote(conditions.get(position).getImageList().get(i).getImagePath(),i);
			
			//imageLoader.displayImage(condition.getImageList().get(i).getImagePath(),list.get(i),options);
			imageLoader.displayImage(condition.getImageList().get(i).getImagePath(),list.get(i),options);
			tlist.get(i).setText(condition.getImageList().get(i).getRemarks());
			list.get(i).setVisibility(View.VISIBLE);
			tlist.get(i).setVisibility(View.VISIBLE);
			
		}
		return convertView;
	}
//	private void getPicFromLocal(String url){
//        Drawable drawable = loader.getDrawableFromLocal("head_photo.png");
//        if (drawable == null)
//            getPicFromRemote(url);
//        else
//        	holder.ItemPhoto.setImageDrawable(drawable);
//        
//    }
    private void getPicFromRemote(String url,int i) {
        // ´Ó·þÎñÆ÷¶ËµÃµ½Í¼Æ¬
    	Drawable drawable = loader.getDrawable(url, list.get(i));
        if(url==null||url.equals("")){
        	drawable = mContext.getResources().getDrawable(R.drawable.image_default);
        	list.get(i).setImageDrawable(drawable);
        }
        else if (drawable != null)
        	list.get(i).setImageDrawable(drawable);
        else{
        	drawable = mContext.getResources().getDrawable(R.drawable.image_default);
        	list.get(i).setImageDrawable(drawable);
        }
    	
    	

        
    }
	final static class Holder  {
		TextView date;
		TextView dignose;
		TextView remarks;
		TextView dis1;
		TextView dis2;
		TextView dis3;
		ImageView image1;
		ImageView image2;
		ImageView image3;
	}
}
