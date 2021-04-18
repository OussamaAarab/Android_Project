package com.example.workers;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.androidproject.MainActivity;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.api.API_Movie;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Context.NOTIFICATION_SERVICE;

public class TrendingWorker extends Worker {
    public static final String TrendingWorker_Tag = "com.example.workers.TrendingWorker.tags.global";
    private static final int LoadedPages = 5;
    private static final String TRENDING_MOVIES_DAY = "TrendingMovies.json";
    private static final String Tag = TrendingWorker.class.getName();
    NotificationManager mNotificationManager;

    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    public TrendingWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            String movies = LoadTrending("day","movie");
            try (FileOutputStream fileOutputStream = getApplicationContext().openFileOutput(TRENDING_MOVIES_DAY,Context.MODE_PRIVATE)) {
                fileOutputStream.write(movies.getBytes());
            }catch (IOException e ){
                e.printStackTrace();
                return Result.failure();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
        if(Looper.myLooper() == null){
            Looper.prepare();
        }
        if(getInputData().getBoolean("notification",true))  ShowNotification();
        return Result.success();
    }


    public String LoadTrending(String time_window,String type) throws Exception {

        API_Movie api_movie = API_Factory.getInstance(getApplicationContext()).getAPI_Movie();
        StringBuilder movies = new StringBuilder("[");
        for( int i=1;i<=LoadedPages;i++ ){
            movies.append(api_movie.findTrendingMoviesJson(time_window, i).toString());
            if(i!=LoadedPages) movies.append(",");
        }
        movies.append("]");
        return movies.toString();
    }

    public void ShowNotification(){
        createNotificationChannel();

//Set up the notification content intent to launch the app when clicked
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder
                (getApplicationContext(), PRIMARY_CHANNEL_ID)
                .setContentTitle(getApplicationContext().getResources().getString(R.string.day_trending_notif_title))
                .setContentText(getApplicationContext().getResources().getString(R.string.day_trending_notif_dsc))
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.app_icone)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        mNotificationManager.notify(0, builder.build());
    }
    public void createNotificationChannel() {

        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,  getApplicationContext().getResources().getString(R.string.day_trending_notif_title), NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getApplicationContext().getResources().getString(R.string.day_trending_notif_dsc));
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
