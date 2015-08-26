package wyy.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imagemanager6.R;

public class DeleteDialog extends Dialog {

	View.OnClickListener onClickListener;

	private ImageView imageView;
	private TextView title;
	Context context;

	public DeleteDialog(Context context, View.OnClickListener onClickListener) {
		super(context, R.style.DeleteDialog);
		this.context = context;
		this.onClickListener = onClickListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.delete_dialog);
		this.setCancelable(false);
		// 实例化控件
		Button btn_confir = (Button) findViewById(R.id.delete_confirm);
		Button btn_cancel = (Button) findViewById(R.id.cancel);
		title = (TextView) findViewById(R.id.dialog_text_delete);
		imageView = (ImageView) findViewById(R.id.delete_image);
		// 给确认键设置监听
		btn_confir.setOnClickListener(onClickListener);
		// 点击取消关闭dialog
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DeleteDialog.this.dismiss();
			}
		});
	}

	public void setTitle(String str) {
		title.setText(str);
	}

	public void startAnimation() {
		Animation animation = AnimationUtils
				.loadAnimation(context, R.anim.rock);

		imageView.startAnimation(animation);
	}
}