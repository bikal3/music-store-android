package com.robinson.anyrentalapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.BLL.CartBLL;
import com.robinson.anyrentalapp.Helper.SetImage;
import com.robinson.anyrentalapp.Helper.UserSession;
import com.robinson.anyrentalapp.Model.Product;
import com.robinson.anyrentalapp.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context context;
    private List<Product> cartlist;
    public static int total;
    private static String prodId;

    public CardAdapter(Context context, List<Product> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        Product product = cartlist.get(position);
        holder.bindImage(product);
    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodname, prodprice;
        ImageView prodimage, deleteimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodimage = itemView.findViewById(R.id.img_cart_prod);
            deleteimg = itemView.findViewById(R.id.img_cart_delete);
            prodprice = itemView.findViewById(R.id.txt_cart_prodprice);
            prodname = itemView.findViewById(R.id.txt_cart_prodname);
            deleteimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UserSession userSession = new UserSession(context);
                    CartBLL cartBLL = new CartBLL();
                    String userId = userSession.getUser().get_id();
                    if (cartBLL.deleteCart(userId, prodId) != null) {
                        Toast.makeText(context, "Cart Deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        public void bindImage(Product product) {
            for (int i = 0; i < cartlist.size(); i++) {
                Integer integer = Integer.parseInt(cartlist.get(i).getPrice());
                total = total + integer;
            }
            SetImage.setImage(product.getImage(), prodimage);
            if (product.getProductname().length() > 40) {
                prodname.setText(product.getProductname().substring(0, 20) + "...");
            } else {
                prodname.setText(product.getProductname());
            }
            prodprice.setText("$" + product.getPrice());
            prodId = product.get_id();
        }
    }
}
