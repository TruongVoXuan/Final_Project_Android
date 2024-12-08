package truong.vx.wheyshop;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import static truong.vx.wheyshop.R.*;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WishList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BestDeal bestDeal;


    private List<BestDeal> bestDealList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wish_list);

        recyclerView = findViewById(id.WishListView);

        loadData();
        updateUI();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.wishList_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void updateUI() {
        WishlistAdapter wishlistAdapter = new WishlistAdapter(bestDealList);
        recyclerView.setAdapter(wishlistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        wishlistAdapter.setItemCickListener(new WishlistAdapter.OnMyItemCickListener() {
            @Override
            public void DosomeThing(int position) {

            }

            @Override
            public void BtnClearItem(int position) {
                BestDeal item = bestDealList.get(position); // Lấy đối tượng BestDeal tại vị trí position
                new AlertDialog.Builder(WishList.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc chắn muốn xoá " + item.getTitle() + " trong wistlist không?")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                bestDealList.remove(position);
                                updateUI();
                                Toast.makeText(WishList.this, "Đã xóa " + item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });

    }

    private void clearData() {
        bestDealList.clear();
        saveData(); // Lưu lại trạng thái mới
        updateUI(); // Cập nhật giao diện người dùng
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String bestDealListJson = gson.toJson(bestDealList);
        editor.putString("bestDealList", bestDealListJson);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("WishlistData", MODE_PRIVATE);
        Gson gson = new Gson();
        String bestDealListJson = sharedPreferences.getString("bestDealList", null);
        Type bestDealListType = new TypeToken<ArrayList<BestDeal>>() {}.getType();
        bestDealList = gson.fromJson(bestDealListJson, bestDealListType);


        if (bestDealList == null) {
            bestDealList = new ArrayList<>();
        }
    }
    public void BtnClearWishList(View view) {
        if (bestDealList != null && !bestDealList.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa tất cả các mục trong giỏ hàng không?")
                    .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearData();
                            Toast.makeText(WishList.this, "Đã xóa tất cả các mục", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        } else {
            Toast.makeText(WishList.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
        }
    }
    public void Btncart (View view){
        Intent intent = new Intent(this , Cart.class);
        startActivity(intent);
    }
    public void BtnBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Btnexplore(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}