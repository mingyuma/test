package wyy.adapter;

import java.util.List;

import mmy.tools.MyTools;

import wyy.bean.People;
import wyy.utils.RoundCorner;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.imagemanager6.R;
import com.xd.Vos.Patient;

public class PatientSortAdapter extends BaseAdapter implements SectionIndexer {
	private List<Patient> list = null;
	private Context mContext;

	public PatientSortAdapter(Context mContext, List<Patient> list) {
		this.mContext = mContext;
		this.list = list;
	}

	/**
	 * ��ListView���ݷ����仯ʱ,���ô˷���������ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<Patient> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final Patient mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(
					R.layout.activity_main_item, null);
			viewHolder.tvName = (TextView) view.findViewById(R.id.patient_name);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.tvAge = (TextView) view.findViewById(R.id.patient_age);
			viewHolder.tvSexy = (TextView) view.findViewById(R.id.patient_sexy);
			viewHolder.tvNumOfImage = (TextView) view
					.findViewById(R.id.numofimage);
			viewHolder.tvNumOfCheck = (TextView) view
					.findViewById(R.id.numofcheck);
			viewHolder.image = (ImageView) view.findViewById(R.id.head_photo);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		// ����position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);

		// �����ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		} else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		int id;
		String sex="��";
		if (this.list.get(position).getSex().equals("0")) {
			id = R.drawable.user_image;
			sex="��";
		} else {
			id = R.drawable.user_image_female;
			sex="Ů";
		}
		Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
				id);
		Bitmap output = RoundCorner.toRoundCorner(bitmap, 200);
		viewHolder.image.setImageBitmap(output);
		viewHolder.tvName.setText(this.list.get(position).getName());
		String birth = this.list.get(position).getAge();
		//String age =MyTools.getAge(birth);
		//String age = this.list.get(position).getAge();
		viewHolder.tvAge.setText(MyTools.getAge(birth) + "��");
		//viewHolder.tvAge.setText(this.list.get(position).getAge() + "��");
		viewHolder.tvSexy.setText(sex);//this.list.get(position).getSex()
		String num = this.list.get(position).getImageCount();
		viewHolder.tvNumOfImage.setText("��Ƭ����" + num + "��");
		num = this.list.get(position).getMedicalCount();
		viewHolder.tvNumOfCheck.setText("������¼����" + num);
		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvName;
		TextView tvAge;
		TextView tvSexy;
		TextView tvNumOfImage;
		TextView tvNumOfCheck;
		ImageView image;
	}

	/**
	 * ����ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	/**
	 * ���ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}

}