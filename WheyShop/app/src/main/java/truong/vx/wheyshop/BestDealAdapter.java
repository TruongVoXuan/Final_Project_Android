package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BestDealAdapter extends RecyclerView.Adapter<BestDealAdapter.MyViewHolder> {

    private List<BestDeal> bestDealList;
    private Context context; // Biến để lưu context
    private WishlistManager wishlistManager; // Biến để quản lý Wishlist


    public  interface OnMyItemCickListener{
        void DoSomeThing (int position);
    }
    private OnMyItemCickListener itemCickListener;
    public void setItemCickListener(OnMyItemCickListener itemCickListener) {
        this.itemCickListener = itemCickListener;
    }
    public BestDealAdapter(List<BestDeal> bestDealList) {
        this.bestDealList = bestDealList;
    }
    public BestDealAdapter(Context context, List<BestDeal> bestDealList) {
        this.context = context;
        this.bestDealList = bestDealList;
        this.wishlistManager = WishlistManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bestdeal,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCickListener.DoSomeThing(position);
            }
        });
        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wishlistManager.addToWishlist(bestDeal); // Thêm sản phẩm vào Wishlist
                Toast.makeText(context, bestDeal.getTitle() + " đã thêm vào Wishlist", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bestDealList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout cardView;
        private ImageView imageView;
        private ImageView favoriteIcon; // Icon yêu thích
        private TextView titleView;
        private TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.constraint);
            imageView = itemView.findViewById(R.id.imageBestdeal);
            favoriteIcon = itemView.findViewById(R.id.favoriteIcon); // Ánh xạ icon
            titleView = itemView.findViewById(R.id.Title);
            price = itemView.findViewById(R.id.Price);
        }
    }

}