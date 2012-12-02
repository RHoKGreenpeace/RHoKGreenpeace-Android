package org.greenpeace.rhokgreenpeace;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

/*
 * All overwritten methods simply dump a lot of data in the LogCat to verify
 * if the app is able to receive notifications from GCM. At this time it has
 * not worked. When it does, useful code can be inserted instead.
 */
public class GCMIntentService extends GCMBaseIntentService {
	
	public static String TAG = "GCMIntentService";
	public static String SENDER_ID = "365490956433";
	
	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; ++i) {
			System.out.println("onError: derp");
		}

	}

	@Override
	protected void onMessage(Context arg0, Intent arg1) {
		for (int i = 0; i < 50; ++i) {
			System.out.println("onMessage: lala");
		}
		Log.d("GCM", "Received a message");
	}

	@Override
	protected void onRegistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; ++i) {
			System.out.println("onRegistered: Herp");
		}
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; ++i) {
			System.out.println("onUnregistered: merp");
		}
	}

}
