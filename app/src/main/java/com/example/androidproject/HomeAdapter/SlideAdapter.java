package com.example.androidproject.HomeAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproject.Handlers.MovieHandler;
import com.example.androidproject.R;
import com.example.api.API_Factory;
import com.example.beans.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends PagerAdapter{

    private Context context;
    private List<Movie> list_Slide;
    private Handler handler;
    public static final int MSG_SLIDE_CLICK = 4;

    public SlideAdapter(Context context, List<Movie> list_Slide) {
        this.context = context;
        this.list_Slide = list_Slide;
        handler = new MovieHandler(context);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item, null);


        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        TextView slideText = slideLayout.findViewById(R.id.title_slide);
        slideImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked(position);
            }
        });


        //slideImg.setImageResource(list_Slide.get(position).getImage());

        String linkImage = "";
        try {
            linkImage = API_Factory.getInstance(slideImg.getContext()).getImage_source() + "w1280" + list_Slide.get(position).getBackdrop_path();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Picasso.get().load(linkImage).into(slideImg);
        slideText.setText(list_Slide.get(position).getTitle());

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return list_Slide.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void setData(ArrayList<Movie> listMovies)
    {
        list_Slide = listMovies;
        this.notifyDataSetChanged();
    }

    public void onItemClicked(int position){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String key = API_Factory.getInstance(context).getAPI_Movie().getTrailerKey(list_Slide.get(position).getId());
                    Message message = new Message();
                    message.arg1 = MSG_SLIDE_CLICK;
                    message.obj = key;
                    handler.sendMessage(message);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
