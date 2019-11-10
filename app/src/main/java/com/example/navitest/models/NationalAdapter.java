package com.example.navitest.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.navitest.NewsDetailActivity;
import com.example.navitest.R;
import com.example.navitest.Utils;

import java.util.List;

public class NationalAdapter extends RecyclerView.Adapter<NationalAdapter.MyViewHolder> {

    private List<National> nationals;
    private Context context;

    public NationalAdapter(List<National> nationals, Context context) {
        this.nationals = nationals;
        this.context = context;
    }


    @NonNull
    @Override
    public NationalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new NationalAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NationalAdapter.MyViewHolder holder, int position) {

        final National national = nationals.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(national.getField_image())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);
        Log.d("testing", "onResponse");
        holder.title.setText(national.getTitle());
        holder.created.setText(national.getCreated());
        holder.body.setText(national.getBody());
        holder.uid.setText(national.getUid());
        holder.field_category.setText(national.getField_category());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra("link", national.getView_node());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nationals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, body, created, uid, field_category;
        ImageView imageView;
        CardView cardView;
        ProgressBar progressBar;
        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            created = v.findViewById(R.id.created);
            body = v.findViewById(R.id.body);
            uid = v.findViewById(R.id.uid);
            field_category = v.findViewById(R.id.field_category);
            imageView = itemView.findViewById(R.id.field_image);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
            cardView = v.findViewById(R.id.newsCard);

        }
    }
}
