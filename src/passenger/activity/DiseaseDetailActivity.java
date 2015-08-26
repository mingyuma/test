package passenger.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class DiseaseDetailActivity extends Activity {

	private ExpandableListView ex_listview;
	private List<String> groupData;
	private List<String> childData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease_detail);
		initdata();
		initviews();
	}

	private void initdata() {
		// TODO Auto-generated method stub
		groupData = new ArrayList<String>();
		groupData.add("����");
		groupData.add("����");
		groupData.add("���֢״");
		groupData.add("����");
		groupData.add("��ʳ����");
		groupData.add("Ԥ������");

		childData = new ArrayList<String>();
		childData.add("���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���"
				+ "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���"
				+ "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���"
				+ "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���"
				+ "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���" + "���������Ͼ��Ŀ�ѻ�ʿ�ڿ���");
		childData.add("����");
		childData.add("���֢״");
		childData.add("����");
		childData.add("��ʳ����");
		childData.add("Ԥ������");
	}

	private void initviews() {
		// TODO Auto-generated method stub
		ex_listview = (ExpandableListView) this.findViewById(R.id.ex_listview);
		ex_listview.setAdapter(new BaseExpandableListAdapter() {

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = null;
				if (convertView != null) {
					tv = (TextView) convertView;
					tv.setText(groupData.get(groupPosition));
				} else {
					tv = createGroupView(groupData.get(groupPosition));
				}
				return tv;
			}

			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return groupData.size();
			}

			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return groupData.get(groupPosition);
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return 1;
			}

			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				TextView tv = null;
				if (convertView != null) {
					tv = (TextView) convertView;
					tv.setText(childData.get(groupPosition));
				} else {
					tv = createView(childData.get(groupPosition));
				}

				return tv;
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childData.get(groupPosition);
			}
		});
	}

	protected TextView createView(String string) {
		// TODO Auto-generated method stub
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		TextView tv = new TextView(DiseaseDetailActivity.this);
		tv.setLayoutParams(layoutParams);
		tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		tv.setPadding(20, 20, 20, 20);
		tv.setText(string);
		return tv;
	}

	protected TextView createGroupView(String string) {
		// TODO Auto-generated method stub
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 80);
		TextView tv = new TextView(DiseaseDetailActivity.this);
		tv.setLayoutParams(layoutParams);
		tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		tv.setPadding(80, 0, 0, 0);
		tv.setText(string);
		return tv;
	}
}
