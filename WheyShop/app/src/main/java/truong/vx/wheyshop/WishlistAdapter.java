package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder> {

    private List<BestDeal> bestDealList;
    public  interface OnMyItemCickListener{

        void BtnClearItem (int position);

        void DoSomeThing(int position);
    }
    private WishlistAdapter.OnMyItemCickListener itemCickListener;
    public void setItemCickListener(WishlistAdapter.OnMyItemCickListener itemCickListener) {
        this.itemCickListener = itemCickListener;
    }
    public WishlistAdapter(List<BestDeal> bestDealList ) {
        this.bestDealList = bestDealList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_wishlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BestDeal bestDeal = bestDealList.get(position);
        holder.imageView.setImageResource(bestDeal.getImageId());
        holder.titleView.setText(bestDeal.getTitle());
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            holder.price.setText("" + bestDeal.getPrice() + " $");
        }
        if (bestDeal.getCategoryId() == 3 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
        }
        if (bestDeal.getCategoryId() == 4 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
        }
        if (bestDeal.getCategoryId() == 5 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
        }
        holder.BtnClearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCickListener.BtnClearItem(position);
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCickListener.DoSomeThing(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestDealList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout cardView;
        private ImageView imageView;
        private TextView titleView;
        private TextView price;
        private ImageView BtnClearItem;
        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.Cart);
            imageView = itemView.findViewById(R.id.imageCart);
            titleView = itemView.findViewById(R.id.titleTxt);
            price = itemView.findViewById(R.id.price);
            BtnClearItem = itemView.findViewById(R.id.BtnClearItem);
        }
    }
}



