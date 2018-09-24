package com.shp.video_recorder;


import android.content.Context;
import android.view.KeyEvent;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.os.Bundle;
import android.content.BroadcastReceiver;


public class VolumeBroadcast extends BroadcastReceiver{
    private static final String TAG = "VolumeBroadcast";


    @Override
    public void onReceive(Context context, Intent intent) {
        //check the intent something like:
        Log.d("onBroadcast","onReceive");
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent event = (KeyEvent)intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            VideoRecorder.mTakePicture=true;
     
        }
    }
  }