package com.GLA_12.androidproject.Fragment;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import com.GLA_12.androidproject.R;
import com.GLA_12.workers.TrendingWorker;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ImageView logo;
    TextView slogan,text_bottom;
    Animation logo_anim,slogan_anim;
    MediaPlayer sound;
    private static int SPLASH_SCREEN = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        // Animation
        logo_anim = AnimationUtils.loadAnimation(this,R.anim.logo_animation);
        slogan_anim = AnimationUtils.loadAnimation(this,R.anim.slogan_animation);

        logo = (ImageView) findViewById(R.id.logo);
        slogan = (TextView) findViewById(R.id.slogan);
        text_bottom = (TextView) findViewById(R.id.tickets_app);

        logo.setAnimation(logo_anim);
        slogan.setAnimation(slogan_anim);
        text_bottom.setAnimation(slogan_anim);

        // Sound
        sound = MediaPlayer.create(this, R.raw.audio);
        sound.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(MainActivity.this,Home.class);
                startActivity(home);
                finish();
            }
        },SPLASH_SCREEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        StartServices();

    }

    public void StartServices(){
        Log.d(MainActivity.class.getName(),"Remaining Time to process : "+getRemainingTime());
        WorkManager manager = WorkManager.getInstance(this);
        //manager.cancelAllWork();
        if(!scheduled()){
            Constraints c = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest OTworkRequest = new OneTimeWorkRequest.Builder(TrendingWorker.class)
                    .addTag(TrendingWorker.TrendingWorker_Tag)
                    .setConstraints(c)
                    .setInputData(new Data.Builder().putBoolean("notification",false).build())
                    .build();
            manager.enqueueUniqueWork(TrendingWorker.TrendingWorker_Tag+".unique", ExistingWorkPolicy.KEEP,OTworkRequest);
            int remainingTime = getRemainingTime();
            PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TrendingWorker.class,24, TimeUnit.HOURS)
                    .setInitialDelay(getRemainingTime(),TimeUnit.SECONDS)
                    .addTag(TrendingWorker.TrendingWorker_Tag)
                    .setConstraints(c)
                    .setInputData(new Data.Builder().putBoolean("notification",true).build())
                    .build();
            manager.enqueueUniquePeriodicWork(TrendingWorker.TrendingWorker_Tag, ExistingPeriodicWorkPolicy.KEEP,periodicWorkRequest);
        }
    }

    public int getRemainingTime(){

        Date date = new Date();
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(date);
        Log.d(MainActivity.class.getName(),currentTime);

        String currentH = currentTime.split(":")[0];
        String currentM = currentTime.split(":")[1];
        String currentS = currentTime.split(":")[2];
        int remainingS = 60 - Integer.parseInt(currentS);
        int remainingM = 59 - Integer.parseInt(currentM);
        int hour = 7;
        if(Integer.parseInt(currentH) > 7 ){
            hour += 24;
        }
        int remainingH = hour - Integer.parseInt(currentH);

        return remainingS + remainingM*60 +remainingH*60*60;
    }
    public boolean scheduled(){
        WorkManager instance = WorkManager.getInstance(this.getApplicationContext());
        ListenableFuture<List<WorkInfo>> statuses = instance.getWorkInfosByTag(TrendingWorker.TrendingWorker_Tag);
        try {
            boolean running = false;
            List<WorkInfo> workInfoList = statuses.get();
            for (WorkInfo workInfo : workInfoList) {
                WorkInfo.State state = workInfo.getState();
                Log.d(MainActivity.class.getName(),"Work id : " + workInfo.getId() + " Work state : " + state );
                running = state == WorkInfo.State.RUNNING || state == WorkInfo.State.ENQUEUED;
                if(running) return true;
            }
            return running;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}