package com.example.multitouch_imageview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
	LinearLayout linearLayout1;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		linearLayout1 = (LinearLayout )findViewById(R.id.linearLayout1);
		linearLayout1.setOnTouchListener(new ZoomListenter());
	}
}
