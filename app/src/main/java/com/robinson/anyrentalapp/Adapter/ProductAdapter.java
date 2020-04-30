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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> prodList;

    public ProductAdapter(Context context, List<Product> prodList) {
        this.context = context;
        this.prodList = prodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = prodList.get(position);
        holder.bindImage(product);
    }

    @Override
    public int getItemCount() {
        return prodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView prodimage;
        TextView prodname, prodprice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodimage = itemView.findViewById(R.id.img_cart_prod);
            prodname = itemView.findViewById(R.id.txt_cart_prodname);
            prodprice = itemView.findViewById(R.id.txt_cart_prodprice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailAcitivity.class);
                    intent.putExtra("prodid", prodList.get(getAdapterPosition()).get_id());
                    context.startActivity(intent);
                }
            });
        }

        public void bindImage(Product product) {
            SetImage.setImage(product.getImage(), prodimage);
            prodname.setText(product.getProductname());
            prodprice.setText("$" + product.getPrice());
        }
    }
}
