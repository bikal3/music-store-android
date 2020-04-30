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
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.R;
import com.robinson.anyrentalapp.UI.DetailAcitivity;

import java.util.List;

public class ExploreProductAdapter extends RecyclerView.Adapter<ExploreProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;

    public ExploreProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bindImage(product);
    }

    @Override
    public int getItemCount() {
        return Math.min(productList.size(), 5);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prodimage;
        TextView prodname, prodprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodimage = itemView.findViewById(R.id.img_exp_prod_image);
            prodname = itemView.findViewById(R.id.txt_exp_prod_name);
            prodprice = itemView.findViewById(R.id.txt_exp_prod_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailAcitivity.class);
                    intent.putExtra("prodid", productList.get(getAdapterPosition()).get_id());
                    context.startActivity(intent);
                }
            });
        }

        public void bindImage(Product product) {
            SetImage.setImage(product.getImage(), prodimage);
            if (product.getProductname().length() > 20) {
                prodname.setText(product.getProductname().substring(0, 20) + "...");

            } else {
                prodname.setText(product.getProductname());
            }
            prodprice.setText("$" + product.getPrice());
        }
    }
}
