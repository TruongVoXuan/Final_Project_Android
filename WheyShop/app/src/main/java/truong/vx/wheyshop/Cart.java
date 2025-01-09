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

import java.lang.reflect.Type;
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
    private double total1 = 0;
    private List<BestDeal> bestDealList1;
    private List<Integer> numList1;
    DecimalFormat df = new DecimalFormat("#.##");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalTxt = findViewById(R.id.totalTxt);
        recyclerView = findViewById(R.id.Cartview);

        loadData();
        updateUI();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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
                numList.set(position,i);
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
        // Load data from the first SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CartData", MODE_PRIVATE);
        Gson gson = new Gson();
        String bestDealListJson = sharedPreferences.getString("bestDealList", null);
        String numListJson = sharedPreferences.getString("numList", null);
        Type bestDealListType = new TypeToken<ArrayList<BestDeal>>() {}.getType();
        Type numListType = new TypeToken<ArrayList<Integer>>() {}.getType();
        bestDealList = gson.fromJson(bestDealListJson, bestDealListType);
        numList = gson.fromJson(numListJson, numListType);
        total = sharedPreferences.getFloat("total", 0); // Khôi phục giá trị total

        if (bestDealList == null) {
            bestDealList = new ArrayList<>();
        }
        if (numList == null) {
            numList = new ArrayList<>();
        }

        // Load data from the second SharedPreferences
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyorderData", MODE_PRIVATE);
        String bestDealListJson1 = sharedPreferences1.getString("bestDealList", null);
        String numListJson1 = sharedPreferences1.getString("numList", null);
        Type bestDealListType1 = new TypeToken<ArrayList<BestDeal>>() {}.getType();
        Type numListType1 = new TypeToken<ArrayList<Integer>>() {}.getType();
        bestDealList1 = gson.fromJson(bestDealListJson1, bestDealListType1);
        numList1 = gson.fromJson(numListJson1, numListType1);
        total1 = sharedPreferences1.getFloat("total", 0); // Khôi phục giá trị total

        if (bestDealList1 == null) {
            bestDealList1 = new ArrayList<>();
        }
        if (numList1 == null) {
            numList1 = new ArrayList<>();
        }
    }
    public void payBtn (View view){

        for (int i = 0; i < bestDealList.size(); i++) {
            boolean found = false;
            if (bestDealList1.size() > 0) {
                for (int j = 0; j < bestDealList1.size(); j++) {
                    if (bestDealList.get(i).getCategoryId() == bestDealList1.get(j).getCategoryId() && bestDealList.get(i).getId() == bestDealList1.get(j).getId()) {
                        numList1.set(j, numList1.get(j) + numList.get(i));
                        total1 += bestDealList.get(i).getPrice() * numList.get(i);
                        found = true;
                        break; // Thoát khỏi vòng lặp khi tìm thấy mục
                    }
                }
            }
            if (!found) {
                bestDealList1.add(bestDealList.get(i));
                numList1.add(numList.get(i));
                total1 += bestDealList.get(i).getPrice() * numList.get(i);
            }
        }

        // Save Data
        SharedPreferences sharedPreferences = getSharedPreferences("MyorderData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String bestDealListJson = gson.toJson(bestDealList1);
        String numListJson = gson.toJson(numList1);
        editor.putString("bestDealList", bestDealListJson);
        editor.putString("numList", numListJson);
        editor.putFloat("total", (float) total1); // Lưu giá trị total
        editor.apply();
        Toast.makeText(Cart.this, "Add your Myorder success !", Toast.LENGTH_LONG).show();
    }
    public void BtnBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void BtnMyorder(View view) {
        Intent intent = new Intent(this, MyOrder.class);
        startActivity(intent);
    }
    public void BtnWishList (View view){
        Intent intent = new Intent(this , WishList.class);
        startActivity(intent);
    }
    public void Btnexplore(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



public void orderBtn(View view){
        Intent intent = new Intent(this , MyOrder.class);
        startActivity(intent);
    }

    public void BtnProfile(View view){
        Intent intent = new Intent(this , Profile.class);
        startActivity(intent);
    }
}