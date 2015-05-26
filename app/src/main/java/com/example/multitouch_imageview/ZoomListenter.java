package com.example.multitouch_imageview;

import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZoomListenter implements OnTouchListener
{
	float oldDist;
	ImageView imageView1;
	int width;
	int height;

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		imageView1 = (ImageView) (v.findViewById(R.id.imageView1));

		if (width == 0 && height == 0)
		{
			width = imageView1.getWidth();
			height = imageView1.getHeight();
		}

		switch (event.getAction() & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN:					//第一根手指觸碰到螢幕時，所觸發的事件
			System.out.println("ACTION_DOWN");
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_DOWN:			//第二根以上手指觸碰到螢幕時(即多點觸碰)，所觸發的事件
			System.out.println("ACTION_POINTER_DOWN");
			oldDist = getHypotenuse(event);				//先算出兩根手指頭間的距離
			break;
		case MotionEvent.ACTION_POINTER_UP:
			System.out.println("ACTION_POINTER_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("ACTION_MOVE");
			try
			{
				float newDist = getHypotenuse(event);
				if (newDist > oldDist + 1)
				{
					zoom(newDist / oldDist);
					oldDist = newDist;
				}
				if (newDist < oldDist - 1)
				{
					zoom(newDist / oldDist);
					oldDist = newDist;
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		}
		return true;
	}

	private void zoom(float f)
	{
		imageView1.setLayoutParams(new LinearLayout.LayoutParams(width *= f, height *= f));
	}

	// 利用直角三角形求斜邊的觀念來算出兩指之間的距離
	// ０是指第一根手指頭的位置，１是指第二根手指頭的位置
	private float getHypotenuse(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

}
