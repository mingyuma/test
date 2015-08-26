package wyy.activity;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import wyy.bean.Folder;
import wyy.bean.TimeImage;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.imagemanager6.R;

public class GridDetailActivity extends BaseActivity {

	private GridView gridview;
	private List<String> filesPath = new ArrayList<String>(); // 获取全部路径
	private List<String> filesPath_name = new ArrayList<String>(); // 获取图片的所有者

	File dir;
	private String path;

	private List<Folder> timeimages = new ArrayList<Folder>();

	private View noDateLayout;

	private ImageAdapter imageadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_griddetail);
		initViews();
		initDates();
		initEvents();

	}

	private void initEvents() {
		// TODO Auto-generated method stub
		imageadapter = new ImageAdapter();
		gridview.setAdapter(imageadapter);
		// Log.i("wyy", "4");
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GridDetailActivity.this,
						ImagePagerActivity.class);
				// intent.putExtra("image", (Serializable) timeimages);
				intent.putExtra("image", (Serializable) filesPath);
				intent.putExtra("name", (Serializable)filesPath_name);
				intent.putExtra("loc", arg2);
				startActivity(intent);
			}
		});
	}

	private void initDates() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		String type = (String) extras.get("type");
		// Toast.makeText(this, "::" + type, Toast.LENGTH_LONG).show();
		// String str = CharactersUtil.swtichChiToEng(type);
		timeimages = DataSupport.where("bodyLoction = ?", type.substring(0, 2))
				.find(Folder.class, true);
		// Log.i("wyy", "detail::::" + timeimages.size());
		// Log.i("wyy", "1");
		findAllFiles();
		// Log.i("wyy", "2");
		if (filesPath.size() != 0) {
			noDateLayout.setVisibility(View.GONE);
		}
	}

	private void initViews() {
		// TODO Auto-generated method stub

		gridview = (GridView) this.findViewById(R.id.gridviewdetail);
		noDateLayout = this.findViewById(R.id.no_data_layout);
		// Log.i("wyy", "3");

		// Log.i("wyy", "5");
	}

	private void findAllFiles() {
		// TODO Auto-generated method stub
		// File file = new File(dir.getAbsoluteFile() + File.separator + path);
		// Log.i("wyy", dir.getAbsoluteFile() + path);
		// if (!file.exists()) {
		// Log.i("wyy", "!exists");
		// return;
		// }
		// File[] Files = file.listFiles();
		// for (File file2 : Files) {
		// filesPath.add(file2.getAbsolutePath());
		// }
		// Log.i("wyy", "timeimages.size():" + timeimages.size() + "");

		for (Folder time : timeimages) {

			// DataSupport.where("timeimage=?",time.get);
			filesPath.add(time.getImagePath());
			filesPath_name.add(time.getFolderDescripe());
			// Log.i("wyy", "folders.size():" + folders.size() + "");
			// for (Folder folder : folders) {
			// if (folder.getImagePath() != null)
			// filesPath.add(folder.getImagePath());
			// }
		}

	}

	final static class Holder {
		ImageView imageView;
	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder = null;
			if (convertView == null) {
				holder = new Holder();
				LayoutInflater layout = LayoutInflater
						.from(GridDetailActivity.this);
				convertView = layout.inflate(R.layout.item_imagegridview, null);
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.image);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			// 将图片显示任务增加到执行池，图片将被显示到ImageView当轮到此ImageView
			// Log.i("wyy", "6");
			// Log.i("wyy", filesPath.get(position));
			imageLoader.displayImage("file://" + filesPath.get(position),
					holder.imageView);
			Log.i("wyy", "7");
			return convertView;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return filesPath.size();
		}
	};
}
