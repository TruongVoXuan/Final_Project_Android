package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BestDeal bestDeal;
    private int num;
    private TextView totalTxt;
    private double total = 0;
    private List<BestDeal> bestDealList;
    private List<Integer> numList;
    DecimalFormat df = new DecimalFormat("#.##");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalTxt = findViewById(R.id.totalTxt); // Đúng id

        recyclerView = findViewById(R.id.Cartview);

        if (savedInstanceState != null) {
            bestDealList = savedInstanceState.getParcelableArrayList("bestDealList");
            numList = savedInstanceState.getIntegerArrayList("numList");
        } else {
            loadData(); // Tải dữ liệu từ SharedPreferences
        }

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 0);
        bestDeal = intent.getParcelableExtra("bestDeal");

        if (bestDeal != null) {
            bestDealList.add(bestDeal);
            numList.add(num);
            saveData(); // Lưu dữ liệu sau khi cập nhật giỏ hàng
        }

        CartAdapter cartAdapter = new CartAdapter(bestDealList, numList);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        calculateAndUpdateTotal(); // Cập nhật tổng ngay khi khởi tạo

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculateAndUpdateTotal() {
        total = 0;
        if (bestDealList != null && numList != null) {
            for (int i = 0; i < bestDealList.size(); i++) {
                BestDeal deal = bestDealList.get(i);
                int quantity = numList.get(i);
                total += deal.getPrice() * quantity; // Giả sử BestDeal có phương thức getPrice()
            }
        }
        totalTxt.setText(String.format("Total: %.2f", total));
    }

    public void BtnBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Btnexplore(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void updateUI() {
        totalTxt.setText(df.format(total) + " $");
        CartAdapter cartAdapter = new CartAdapter(bestDealList, numList);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cartAdapter.setItemCickListener(new CartAdapter.OnMyItemCickListener() {
            @Override
            public void BtnIncNum(int position) {
                int i = numList.get(position) + 1;
                numList.set(position, i);
                updateTotal();
                updateUI();
            }

            @Override
            public void BtnDecNum(int position) {
                if (numList.get(position) == 1) {
                    BestDeal item = bestDealList.get(position); // Lấy đối tượng BestDeal tại vị trí position
                    new AlertDialog.Builder(Cart.this)
                            .setTitle("Xác nhận xóa")
                            .setMessage("Bạn có chắc chắn muốn xoá " + item.getTitle() + " trong giỏ hàng không?")
                            .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    bestDealList.remove(position);
                                    numList.remove(position);
                                    updateTotal();
                                    updateUI();
                                    Toast.makeText(Cart.this, "Đã xóa " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Hủy", null)
                            .show();
                } else {
                    int i = numList.get(position) - 1;
                    numList.set(position, i);
                    updateTotal();
                    updateUI();
                }
            }
        });
    }

    private void updateTotal() {
        total = 0;
        for (int i = 0; i < bestDealList.size(); i++) {
            total += bestDealList.get(i).getPrice() * numList.get(i);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("bestDealList", new ArrayList<Parcelable>(bestDealList));
        outState.putIntegerArrayList("numList", new ArrayList<>(numList));
        outState.putInt("num", num);
    }
    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String bestDealListJson = gson.toJson(bestDealList);
        String numListJson = gson.toJson(numList);
        editor.putString("bestDealList", bestDealListJson);
        editor.putString("numList", numListJson);
        editor.putFloat("total", (float) total); // Lưu giá trị total
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        Gson gson = new Gson();
        String bestDealListJson = sharedPreferences.getString("bestDealList", null);
        String numListJson = sharedPreferences.getString("numList", null);

        if (bestDealListJson != null && numListJson != null) {
            bestDealList = gson.fromJson(bestDealListJson, new TypeToken<List<BestDeal>>() {}.getType());
            numList = gson.fromJson(numListJson, new TypeToken<List<Integer>>() {}.getType());
        } else {
            bestDealList = new ArrayList<>();
            numList = new ArrayList<>();
        }

        total = sharedPreferences.getFloat("total", 0);
    }



    public void BtnClearCart(View view) {
        if (bestDealList != null && !bestDealList.isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn muốn xóa tất cả các mục trong giỏ hàng không?")
                    .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearData();
                            Toast.makeText(Cart.this, "Đã xóa tất cả các mục", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        } else {
            Toast.makeText(Cart.this, "Giỏ hàng rỗng!", Toast.LENGTH_SHORT).show();
        }
    }


    private void clearData() {
        bestDealList.clear();
        numList.clear();
        total = 0; // Đặt lại giá trị total
        saveData(); // Lưu lại trạng thái mới
        updateUI(); // Cập nhật giao diện người dùng
    }

}