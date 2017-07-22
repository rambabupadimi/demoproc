package com.cenrefordentistry;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;


/**
 * Created by Dineshgadu on 17-01-2017.
 */

public class SplashScreen extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private String TAG = "Splash.java";
    private Animation anim;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        appPreferences = new AppPreferences(this);
        //startAnimations();
      //  normalSplash();
       // newVideo();

        VideoView mVideoView = (VideoView)findViewById(R.id.videoview);
        String uriPath = "android.resource://com.android.AndroidVideoPlayer/"+R.raw.exportnew;
        Uri uri = Uri.parse(uriPath);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            public void onCompletion(MediaPlayer mp) {
                jump();
            }

        });

    }

    private void newVideo()
    {
        try{
            VideoView mVideoView = new VideoView(this);
            setContentView(mVideoView);

        //    String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.exportnew;
       //     Uri uri = Uri.parse(uriPath);
       //     mVideoView.setVideoURI(uri);


       //     Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exportnew);
       //     videoHolder.setVideoURI(video);

       //     VideoView mVideoView = new VideoView(this);
            String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.exportnew;
            Uri uri = Uri.parse(uriPath);
            mVideoView.setVideoURI(uri);
            mVideoView.requestFocus();
            mVideoView.start();

            mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                public void onCompletion(MediaPlayer mp) {
                    jump();
                }

            });
          //  videoHolder.start();
        } catch(Exception ex) {
            jump();
        }
    }
//  Uncomment this function if you want the user to be able to skip this screen
//	@Override
//    public boolean onTouchEvent(MotionEvent event) {
//	  try {
//    	videoHolder.stopPlayback();
//	  } catch(Exception ex) {}
//	  jump();
//    	return true;
//    }

    private void jump() {
        if(isFinishing())
            return;
        startActivity(new Intent(this, LandingPage.class));
        finish();
    }







    private  void normalSplash()
    {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if(appPreferences.getIsNotFirstTime().trim().equals("true"))
                {
                    Intent i = new Intent(SplashScreen.this, HomeScreen.class);
                    startActivity(i);
                }
                else if (appPreferences.getIsNotFirstTime().trim().equals("false") )
                {
                    //the app is being launched for first time, do something
                    Log.i(TAG, "Not First time: " + appPreferences.getIsNotFirstTime());
               //     appPreferences.setIsNotFirstTime(true);
                    // first time task
                    Intent i = new Intent(SplashScreen.this, LandingPage.class);
                    startActivity(i);

                    // record the fact that the app has been started at least once
                }

            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startAnimations();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //startAnimations();
    }



    @Override
    public void onBackPressed() {

    }
}
