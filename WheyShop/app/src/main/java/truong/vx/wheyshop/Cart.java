package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            bestDealList = new ArrayList<>();
            numList = new ArrayList<>();
        }

        Intent intent = getIntent();
        num = intent.getIntExtra("num", 0);
        bestDeal = intent.getParcelableExtra("bestDeal");

        if (bestDeal != null) {
            bestDealList.add(bestDeal);
            numList.add(num);
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
}