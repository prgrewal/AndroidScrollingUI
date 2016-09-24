package com.example.root.simpleviewpager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;
import com.viewpagerindicator.CirclePageIndicator;
import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    VideoView video_player_view;
    DisplayMetrics dm;
    private String vSource;

    ImageView imageview;
    Drawable drawable;
    Bitmap bitmap1, bitmap2;
    ByteArrayOutputStream bytearrayoutputstream;
    byte[] BYTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getInit();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        /** set the adapter for ViewPager */
        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));

        //Bind the title indicator to the adapter
        CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
        titleIndicator.setViewPager(mViewPager);

    }

    /** Defining a FragmentPagerAdapter class for controlling the fragments to be shown when user swipes on the screen. */
    public class SamplePagerAdapter extends FragmentPagerAdapter {
        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position==0) {
                return new SampleFragment();
            } else {
                return new SampleFragmentTwo();
            }
        }

        @Override
        public int getCount() {
            //Show 2 total pages.
            return 2;
        }
    }

    public void handleLogin(View view) {startLogin();}

    public void handleSignup(View view) {startSignup();}

    private void startSignup() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivityForResult(intent, SignUpActivity.EXTRA_REQUEST);
    }

    private void startLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, LoginActivity.EXTRA_REQUEST);
    }


    public void getInit() {
        vSource="android.resource://"+getPackageName()+"/"+R.raw.grunge;
        Uri uri= Uri.parse(vSource);

        video_player_view = (VideoView) findViewById(R.id.video_player_view);
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


    @Override
    public void onResume() {
        super.onResume();

        getInit();
    }



}
