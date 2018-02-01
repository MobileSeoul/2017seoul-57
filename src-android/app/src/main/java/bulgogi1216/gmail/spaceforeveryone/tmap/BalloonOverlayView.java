package bulgogi1216.gmail.spaceforeveryone.tmap;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class BalloonOverlayView extends FrameLayout {

	private LinearLayout layout;
	private TextView allPath;
	private TextView sectionPath;
	private ImageView clickImage;
	
	public BalloonOverlayView(Context context, int balloonBottomOffset) {

		super(context);

		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new LinearLayout(context); //���� ���̾ƿ�
		layout.setVisibility(VISIBLE);


		
		LayoutParams params = new LayoutParams(
								LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;

		addView(layout, params);
	}
	

	public ImageView getClickImage() {
		
		return clickImage;
	}
	
	

}
