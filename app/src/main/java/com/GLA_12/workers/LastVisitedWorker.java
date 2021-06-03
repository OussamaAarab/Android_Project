package com.GLA_12.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.GLA_12.api.API_Factory;
import com.GLA_12.api.API_Movie;
import com.GLA_12.beans.Movie;
import com.GLA_12.dao.DaoMovie;

import java.io.IOException;

public class LastVisitedWorker extends Worker {

    public static final  String LastVisitedWorkerTag = "last_visited_worker_tag";


    public LastVisitedWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        int id = getInputData().getInt("id",0);

        Movie m = null;
        try {
            API_Movie api_movie = API_Factory.getInstance(getApplicationContext()).getAPI_Movie();
            m = api_movie.findMovie(id,null);
            DaoMovie daoMovie = new DaoMovie(super.getApplicationContext());
            daoMovie.add_to_visited(m);
            return Result.success();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Result.failure();
    }
}
