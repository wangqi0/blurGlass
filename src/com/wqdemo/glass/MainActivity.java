package com.wqdemo.glass;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private int scaleRatio = 10;// ��ֵԽ�� ͼƬԽģ������ʱԽ�� ��Ϊë������ǰѹ��ͼƬ����
	private int blurRadius = 1; // ��ֵԽС ͼƬԽ��������ʱԽ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView img = (ImageView) findViewById(R.id.iv_img);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

		//��ȡ�ֻ���Ļ���
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		//int height = wm.getDefaultDisplay().getHeight();

		long start = System.currentTimeMillis();

		Bitmap bitmap = new BitmapFactory().decodeResource(getResources(), R.drawable.abc);
		Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / scaleRatio, bitmap.getHeight() / scaleRatio, false);
		Bitmap blurBitmap = FastBlur.doBlur(scaleBitmap, blurRadius, true);

		//���ó���Ļ�Ŀ�͸ߣ�����imageviewΪԭͼѹ����Ч��
		LayoutParams layoutParams = img.getLayoutParams();
		layoutParams.height = width-30;
		layoutParams.width = width-30;
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		img.setImageBitmap(bitmap);

		//���ñ���Ϊë����Ч��
		Drawable drawable = new BitmapDrawable(blurBitmap);
		rl.setBackgroundDrawable(drawable);

		long end = System.currentTimeMillis();
		Log.e("��ʱ��", (end - start) + "");
	}

}
