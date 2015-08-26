package passenger.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import com.example.imagemanager6.R;
import com.xd.Vos.Doctor;
import com.xd.util.ImageLoader;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorAdapter extends BaseAdapter{
	private List<Doctor> items = null;
	private LayoutInflater inflater;
	private ImageLoader imageLoader;
	
	

	public DoctorAdapter(Context context,List<Doctor> items) {
		this.items = items;
		this.inflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader();
		 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public void updateListView(List<Doctor> filterDateList) {
		this.items = filterDateList;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Doctor doctor = (Doctor)items.get(position);
		ViewHolder viewholder = null;
		if (convertView == null) {
			viewholder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_finddoctot, null);
			viewholder.image = (ImageView) convertView.findViewById(R.id.doc_image);
			viewholder.name = (TextView) convertView.findViewById(R.id.doc_name);
			viewholder.clasz = (TextView) convertView.findViewById(R.id.doc_class);
			viewholder.grade = (TextView) convertView.findViewById(R.id.doc_grade);
			viewholder.hospital = (TextView) convertView.findViewById(R.id.doc_hosipital);
			viewholder.brief = (TextView) convertView.findViewById(R.id.doctor_info);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		Log.i("wyy1", "URL:" + doctor.getPhotoURL());
		viewholder.name.setText(doctor.getName());
		viewholder.grade.setText("90");
		viewholder.hospital.setText(doctor.getHospital()+ "     " +doctor.getDepartment());
		viewholder.brief.setText("擅长:" + doctor.getGoodat());
		imageLoader.loadBitmap(doctor.getPhotoURL(), viewholder.image);
		String type = doctor.getType();
		if(type.equalsIgnoreCase("0")){
			viewholder.clasz.setText("实习医师");
		}else if(type.equalsIgnoreCase("1")){
			viewholder.clasz.setText("主任医师");
		}else{
			viewholder.clasz.setText("管理医师");
		}
		return convertView;

	}
	
//	public static InputStream  getImageViewInputStream() throws IOException {
//		InputStream  InputStream = null;
//		URL url= new URL();
//		if(url!= null){
//			HttpURLConnection http = (HttpURLConnection)
//		}
//		
//	}
	
	public class ViewHolder{
		public ImageView image;
		public TextView  name;
		public TextView  grade;
		public TextView  clasz;
		public TextView  hospital;
		public TextView  brief;
	}
	
	

}
