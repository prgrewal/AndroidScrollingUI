package com.example.root.simpleviewpager;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.VideoView;
import android.support.v8.renderscript.*;

/**
 * Created by root on 8/4/16.
 */
public class LoginActivity extends AppCompatActivity {
    public static final int EXTRA_REQUEST = 0;
    VideoView video_player_view;
    DisplayMetrics dm;
    private String vSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
       getInit();
    }

    public void getInit() {
        vSource="android.resource://"+getPackageName()+"/"+R.raw.portal;
        Uri uri= Uri.parse(vSource);

        video_player_view = (VideoView) findViewById(R.id.video_player_view_login);
        video_player_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        video_player_view.setMinimumWidth(width);
        video_player_view.setMinimumHeight(height);
        video_player_view.setVideoURI(uri);
        video_player_view.start();
    }


}
