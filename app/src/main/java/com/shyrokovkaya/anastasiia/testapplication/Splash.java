package com.shyrokovkaya.anastasiia.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private final int SLEEP = 50;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        bar.getProgressDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);

        Thread background = new Thread (new Runnable() {
            public void run() {
                try {
                    int increment = SLEEP * 100 / SPLASH_DISPLAY_LENGTH;
                    while (bar.getProgress() < 100) {
                        Thread.sleep(SLEEP);
                        bar.incrementProgressBy(increment);
                    }
                    Intent mainIntent = new Intent(Splash.this, UserListActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                } catch (java.lang.InterruptedException e) {
                    // if something fails do something smart
                }
            }
        });
        background.start();

    }
}
