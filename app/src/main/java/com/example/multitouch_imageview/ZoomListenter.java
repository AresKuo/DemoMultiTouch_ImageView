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
		case MotionEvent.ACTION_DOWN:					//�Ĥ@�ڤ��Ĳ�I��ù��ɡA��Ĳ�o���ƥ�
			System.out.println("ACTION_DOWN");
			break;
		case MotionEvent.ACTION_UP:
			System.out.println("ACTION_UP");
			break;
		case MotionEvent.ACTION_POINTER_DOWN:			//�ĤG�ڥH�W���Ĳ�I��ù���(�Y�h�IĲ�I)�A��Ĳ�o���ƥ�
			System.out.println("ACTION_POINTER_DOWN");
			oldDist = getHypotenuse(event);				//����X��ڤ���Y�����Z��
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

	// �Q�Ϊ����T���ΨD���䪺�[���Ӻ�X����������Z��
	// ���O���Ĥ@�ڤ���Y����m�A���O���ĤG�ڤ���Y����m
	private float getHypotenuse(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

}
