package com.example.phoneled;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class LedOnOff extends Activity {
	ToggleButton tb;
	final int ID_LED = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_led_on_off);

		tb = (ToggleButton) findViewById(R.id.toggleButton1);
		tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				Notification notification = new Notification();
				// notification.icon = R.drawable.someicon;  // there should be spec for the icon
				if (isChecked) {
					// 这里是颜色，我们可以尝试改变，理论上0xFF0000是红色，0x00FF00是绿色
					notification.ledARGB = 0xFFFFFFFF;
					notification.ledOnMS = 1000;
					notification.ledOffMS = 100;
					notification.flags = Notification.FLAG_SHOW_LIGHTS;
					nm.notify(ID_LED, notification);
					// nm.cancel(ID_LED);
				} else {
					notification.ledARGB = 0x00FF;
					notification.ledOnMS = 2000;
					notification.ledOffMS = 100;
					notification.flags = Notification.FLAG_SHOW_LIGHTS;
					nm.notify(ID_LED, notification);
					// nm.cancel(ID_LED);
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_led_on_off, menu);
		return true;
	}
}
