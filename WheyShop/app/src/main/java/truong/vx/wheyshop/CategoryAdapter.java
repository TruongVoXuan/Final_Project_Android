package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private int selectedPosition = RecyclerView.NO_POSITION;
    private boolean SetFull;
    private List<Category> categoryList;
    public  interface OnMyItemCickListener{
        void DoSomeThing (int position);
    }
    private OnMyItemCickListener itemCickListener;

    public void setItemCickListener(OnMyItemCickListener itemCickListener) {
        this.itemCickListener = itemCickListener;
    }

    public CategoryAdapter(List<Category> categoryList , boolean SetFull) {
        this.categoryList = categoryList;
        this.SetFull = SetFull;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = categoryList.get(position);
        holder.imageView.setImageResource(category.getImageId());
        holder.textView.setText(category.getName());

        // Kiểm tra vị trí được chọn
        if (SetFull){
            holder.cardView1.setBackgroundResource(R.drawable.oval_black_yel);
        }
        else {
            if (position == selectedPosition) {
                holder.cardView1.setBackgroundResource(R.drawable.oval_black_yel);
            } else {
                holder.cardView1.setBackgroundResource(R.drawable.oval_black); // Đặt lại nền mặc định
            }
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCickListener.DoSomeThing(position);
                // Cập nhật vị trí được chọn
                selectedPosition = position;
                SetFull =  false;
                // Thông báo cho Adapter cập nhật lại dữ liệu
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout cardView;
        private ConstraintLayout cardView1;
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.constraintLayout);
            cardView1 = itemView.findViewById(R.id.constraintLayout2);
            imageView = itemView.findViewById(R.id.imageCategory);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
