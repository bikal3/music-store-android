package com.robinson.anyrentalapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.Helper.SetImage;
import com.robinson.anyrentalapp.Model.Feed;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.UI.ProductList;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    private Context context;
    private List<Feed> feedList;

    public ExploreAdapter(Context context, List<Feed> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.explore_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feedList.get(position);
        holder.bindImage(feed);
    }

    @Override
    public int getItemCount() {
//        return Math.min(feedList.size(), 5);
        return feedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView feedimage;
        private TextView feedname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feedimage = itemView.findViewById(R.id.img_exp_image);
            feedname = itemView.findViewById(R.id.txt_exp_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductList.class);
                    intent.putExtra("feedid", feedList.get(getAdapterPosition()).get_id());
                    context.startActivity(intent);

                }
            });
        }

        public void bindImage(Feed feed) {
            SetImage.setImage(feed.getFeedimage(), feedimage);
            feedname.setText(feed.getFeedname());
        }
    }
}
