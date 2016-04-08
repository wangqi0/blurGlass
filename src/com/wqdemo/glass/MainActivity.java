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

	private int scaleRatio = 10;// 数值越大 图片越模糊，耗时越短 此为毛玻璃化前压缩图片比例
	private int blurRadius = 1; // 数值越小 图片越清晰，耗时越短

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView img = (ImageView) findViewById(R.id.iv_img);
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);

		//获取手机屏幕宽度
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		//int height = wm.getDefaultDisplay().getHeight();

		long start = System.currentTimeMillis();

		Bitmap bitmap = new BitmapFactory().decodeResource(getResources(), R.drawable.abc);
		Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / scaleRatio, bitmap.getHeight() / scaleRatio, false);
		Bitmap blurBitmap = FastBlur.doBlur(scaleBitmap, blurRadius, true);

		//设置成屏幕的宽和高，设置imageview为原图压缩的效果
		LayoutParams layoutParams = img.getLayoutParams();
		layoutParams.height = width-30;
		layoutParams.width = width-30;
		img.setScaleType(ImageView.ScaleType.FIT_XY);
		img.setImageBitmap(bitmap);

		//设置背景为毛玻璃效果
		Drawable drawable = new BitmapDrawable(blurBitmap);
		rl.setBackgroundDrawable(drawable);

		long end = System.currentTimeMillis();
		Log.e("耗时：", (end - start) + "");
	}

}
