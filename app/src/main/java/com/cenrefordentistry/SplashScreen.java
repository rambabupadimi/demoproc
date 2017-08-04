package com.cenrefordentistry;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;


/**
 * Created by Dineshgadu on 17-01-2017.
 */

public class SplashScreen extends AppCompatActivity {
    // Splash screen timer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        AppPreferences appPreferences = new AppPreferences(this);
        if(appPreferences.getToken()!=null && appPreferences.getToken().length()>0)
        {
         Intent intent = new Intent(this,HomeScreen.class);
         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
         startActivity(intent);
        }
        else {
            VideoView videoView = (VideoView) findViewById(R.id.videoview);
            getWindow().setFormat(PixelFormat.UNKNOWN);
            String uripath = "android.resource://com.cenrefordentistry/" + R.raw.splashvideo;
            Uri uri = Uri.parse(uripath);
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
        }
    }

    private void jump() {
        startActivity(new Intent(this, RegisterDob.class));
        finish();
    }
}
