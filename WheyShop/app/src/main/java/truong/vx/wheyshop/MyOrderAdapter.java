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

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyViewHolder> {

    private List<BestDeal> bestDealList;
    private List<Integer> numList;
    public  interface OnMyItemCickListener{

    }
    private MyOrderAdapter.OnMyItemCickListener itemCickListener;
    public void setItemCickListener(MyOrderAdapter.OnMyItemCickListener itemCickListener) {
        this.itemCickListener = itemCickListener;
    }
    public MyOrderAdapter(List<BestDeal> bestDealList , List<Integer> numList) {
        this.bestDealList = bestDealList;
        this.numList = numList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_my_order,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BestDeal bestDeal = bestDealList.get(position);
        holder.imageView.setImageResource(bestDeal.getImageId());
        holder.titleView.setText(bestDeal.getTitle());
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            holder.price.setText("" + bestDeal.getPrice() + " $");
            holder.num.setText(this.numList.get(position) + " ");
        }
        if (bestDeal.getCategoryId() == 3 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
            holder.num.setText(this.numList.get(position) + " ");
        }
        if (bestDeal.getCategoryId() == 4 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
            holder.num.setText(this.numList.get(position) + " ");
        }
        if (bestDeal.getCategoryId() == 5 ){
            holder.price.setText("" + bestDeal.getPrice() + " $");
            holder.num.setText(this.numList.get(position) + " ");
        }


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
        private TextView num;

        @SuppressLint("WrongViewCast")
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.Cart);
            imageView = itemView.findViewById(R.id.imageCart);
            titleView = itemView.findViewById(R.id.titleTxt);
            price = itemView.findViewById(R.id.price);
            num = itemView.findViewById(R.id.numTxt);

        }
    }
}
