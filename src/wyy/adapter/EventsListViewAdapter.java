package wyy.adapter;

import java.util.List;

import wyy.bean.CalendarEvents;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class EventsListViewAdapter extends BaseAdapter {

	private List<CalendarEvents> events;
	private Context mContext;

	public EventsListViewAdapter(List<CalendarEvents> events, Context mContext) {
		this.events = events;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return events.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return events.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewholder = null;
		if (convertView == null) {
			viewholder = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.item_events, null);
			viewholder.eventName = (TextView) convertView
					.findViewById(R.id.event_name);
			viewholder.eventTime = (TextView) convertView
					.findViewById(R.id.event_time);
			convertView.setTag(viewholder);
		} else {
			viewholder = (ViewHolder) convertView.getTag();
		}
		viewholder.eventName.setText(events.get(position).getTitle());
		viewholder.eventTime.setText(events.get(position).getHour() + ":"
				+ events.get(position).getMinute());
		return convertView;
	}

	final static class ViewHolder {
		TextView eventName;
		TextView eventTime;
	}

	public void update(List<CalendarEvents> events) {
		this.events = events;
		this.notifyDataSetChanged();

	}

}
