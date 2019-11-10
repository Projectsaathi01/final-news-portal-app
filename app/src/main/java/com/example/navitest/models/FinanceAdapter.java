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

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.MyViewHolder> {

    private List<Finance> finances;
    private Context context;

    public FinanceAdapter(Context context, List<Finance> finances){
        this.finances = finances;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final Finance finance = finances.get(position);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);



        Glide.with(context)
                .load(finance.getField_image())
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
        holder.title.setText(finance.getTitle());
        holder.created.setText(finance.getCreated());
        holder.body.setText(finance.getBody());
        holder.uid.setText(finance.getUid());
        holder.field_category.setText(finance.getField_category());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra("link", finance.getView_node());
                view.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return finances.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView title, body, created, uid, field_category;
        CardView cardView;
        ImageView imageView;
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
