package com.example.phoneled;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LEDService extends Service {
	private boolean TimerStarted;
	private Timer timer;
	private long LastColor;
	final int ID_LED = 1;

	public void TurnLedOn() {
		Notification notification = new Notification();
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification.flags |= Notification.FLAG_SHOW_LIGHTS;
		notification.ledOnMS = 100;
		notification.ledOffMS = 100;

		if (LastColor == 0x00FF00)
			notification.ledARGB = 0xFF0000;
		else
			notification.ledARGB = 0x00FF00;
		LastColor = notification.ledARGB;

		nm.notify(ID_LED, notification);
	}

	private class MyTimerTask extends TimerTask {
		public void run() {
			TurnLedOn();
		}
	}

	public void OnCreate() {
		super.onCreate();
		TimerStarted = false;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (TimerStarted == false) {
			timer = new Timer();
			timer.schedule(new MyTimerTask(), 1000, 200);
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		timer.cancel();
	}

	// public IBinder onBind() {
	// return null;
	// }

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
