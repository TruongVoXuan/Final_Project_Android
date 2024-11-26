package truong.vx.wheyshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    TextView titleTxt, decriptionTxt, priceTxt , starTxt , totalTxt , numTxt;
    RatingBar ratingBar;
    int num = 1;
    BestDeal bestDeal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        bestDeal = intent.getParcelableExtra("bestDeal");


        imageView = findViewById(R.id.image);
        titleTxt = findViewById(R.id.titleText);
        decriptionTxt = findViewById(R.id.DecripText);
        priceTxt = findViewById(R.id.priceText);
        starTxt = findViewById(R.id.rateText);
        ratingBar = findViewById(R.id.ratingBar);
        totalTxt = findViewById(R.id.totalTxt);
        numTxt = findViewById(R.id.numText);


        imageView.setImageResource(bestDeal.getImageId());
        titleTxt.setText(bestDeal.getTitle());
        decriptionTxt.setText(bestDeal.getDescription());
        starTxt.setText("" + bestDeal.getStar());
        ratingBar.setRating((float) bestDeal.getStar());
        totalTxt.setText(num * bestDeal.getPrice() + " $");
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            priceTxt.setText(bestDeal.getPrice() + " $");
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 3){
            priceTxt.setText(bestDeal.getPrice() + " $");
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 4){
            priceTxt.setText(bestDeal.getPrice() + " $/Can");
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 5){
            priceTxt.setText(bestDeal.getPrice() + " $");
            numTxt.setText(num + " ");
        }




        recyclerView = findViewById(R.id.BestDealView1);
        List<BestDeal> bestDealList = new ArrayList<BestDeal>();
        if (bestDeal.getCategoryId() == 1) {
            bestDealList.add(new BestDeal("Red Whey", R.drawable.whey1, "good for practice", 80, 4.4, 1, 1, 1));
            bestDealList.add(new BestDeal("Whey Pro", R.drawable.whey2, "gain weight", 87, 3.5, 2, 1, 1));
            bestDealList.add(new BestDeal("Whey Gainer", R.drawable.whey3, "muscle gain", 79, 4, 3, 1, 1));
            bestDealList.add(new BestDeal("Muscal Whey", R.drawable.whey4, "good for practice", 97, 4.3, 4, 1, 1));
            bestDealList.add(new BestDeal("Blue Whey", R.drawable.whey5, "muscle gain", 120, 4, 5, 1, 1));
            bestDealList.add(new BestDeal("GomBat Whey", R.drawable.whey6, "gain weight", 97, 5, 6, 1, 1));
            bestDealList.add(new BestDeal("Gr Whey", R.drawable.whey7, "gain weight", 95, 5, 7, 1, 1));
            bestDealList.add(new BestDeal("Max Whey", R.drawable.whey8, "muscle gain", 84, 4.2, 8, 1, 1));
            bestDealList.add(new BestDeal("Hydro Whey", R.drawable.whey9, "good for practice", 123, 4.3, 9, 1, 1));
        }
        if (bestDeal.getCategoryId() == 2) {
            bestDealList.add(new BestDeal("Ice Cre", R.drawable.cre1, "Additional strength", 96, 4.4, 1, 2, 1));
            bestDealList.add(new BestDeal("ImDoc Cre", R.drawable.cre2, "Additional durability", 49, 3.5, 2, 2, 1));
            bestDealList.add(new BestDeal("Hyper Cre", R.drawable.cre3, "Additional strength", 35, 3.3, 3, 2, 1));
            bestDealList.add(new BestDeal("Siver Cre", R.drawable.cre4, "Additional durability", 35, 3.7, 4, 2, 1));
            bestDealList.add(new BestDeal("Cre Muscel", R.drawable.cre5, "Additional strength", 75, 4.7, 5, 2, 1));
            bestDealList.add(new BestDeal("New Cre", R.drawable.cre6, "Additional strength", 35, 3.8, 6, 2, 1));
            bestDealList.add(new BestDeal("White Cre", R.drawable.cre7, "Additional durability", 25, 3.9, 7, 2, 1));
            bestDealList.add(new BestDeal("Power Cre", R.drawable.cre8, "Additional durability", 79, 5, 8, 2, 1));
            bestDealList.add(new BestDeal("Cretiner", R.drawable.cre9, "Additional strength", 29, 3.4, 9, 2, 1));
        }
        if (bestDeal.getCategoryId() == 3) {
            bestDealList.add(new BestDeal("BCAA Gren", R.drawable.bc1, "Additional strength", 36, 4.4, 1, 3, 1));
            bestDealList.add(new BestDeal("BCAA PM", R.drawable.bc2, "Additional strength", 49, 3.5, 2, 3, 1));
            bestDealList.add(new BestDeal("BCAA MD", R.drawable.bc3, "Additional durability", 35, 2.3, 3, 3, 1));
            bestDealList.add(new BestDeal("Ice BCAA", R.drawable.bc4, "Additional strength", 35, 3.7, 4, 3, 1));
            bestDealList.add(new BestDeal("Pow BCAA", R.drawable.bc5, "Additional durability", 35, 4.7, 5, 3, 1));
            bestDealList.add(new BestDeal("BCAA Black", R.drawable.bc6, "Additional strength", 25, 2.8, 6, 3, 1));
            bestDealList.add(new BestDeal("Red BCAA", R.drawable.bc7, "Additional durability", 25, 3.9, 7, 3, 1));
            bestDealList.add(new BestDeal("Mus BCAA", R.drawable.bc8, "Additional strength", 79, 5, 8, 3, 1));
        }
        if (bestDeal.getCategoryId() == 4) {
            bestDealList.add(new BestDeal("Pre no1", R.drawable.pre1, "Additional strength", 96, 4.4, 1, 4, 1));
            bestDealList.add(new BestDeal("Pre no2", R.drawable.pre2, "Additional durability", 49, 3.5, 2, 4, 1));
            bestDealList.add(new BestDeal("Pre De", R.drawable.pre3, "Additional strength", 35, 2.3, 3, 4, 1));
            bestDealList.add(new BestDeal("Pre Pow", R.drawable.pre4, "Additional durability", 57, 3.7, 4, 4, 1));
            bestDealList.add(new BestDeal("Pre Ice", R.drawable.pre5, "Additional strength", 75, 4.7, 5, 4, 1));
            bestDealList.add(new BestDeal("Pre Mn", R.drawable.pre6, "Additional durability", 25, 2.8, 6, 4, 1));
            bestDealList.add(new BestDeal("Pre PM", R.drawable.pre7, "Additional strength", 25, 3.9, 7, 4, 1));
            bestDealList.add(new BestDeal("Pre God", R.drawable.pre3, "Additional strength", 79, 5, 8, 4, 1));
        }
        if (bestDeal.getCategoryId() == 5) {
            bestDealList.add(new BestDeal("Vitamin Gre", R.drawable.vi1, "Mineral supplements", 36, 4.4, 1, 5, 1));
            bestDealList.add(new BestDeal("Vitamin Fredd", R.drawable.vi3, "Absorbs quickly", 49, 3.5, 2, 5, 1));
            bestDealList.add(new BestDeal("Vitamin Organ", R.drawable.vi2, "Mineral supplements", 35, 2.3, 3, 5, 1));
            bestDealList.add(new BestDeal("Vitamin Gre", R.drawable.vi4, "Absorbs quickly", 35, 3.7, 4, 5, 1));
            bestDealList.add(new BestDeal("Vitamin no1", R.drawable.vi2, "Absorbs quickly", 75, 4.7, 5, 5, 1));
            bestDealList.add(new BestDeal("Vitamin Gre New", R.drawable.vi3, "Mineral supplements", 25, 2.8, 6, 5, 1));
            bestDealList.add(new BestDeal("Vitamin Fede", R.drawable.vi4, "Mineral supplements", 25, 3.9, 7, 5, 1));
        }
        SimilarAdapter similarAdapter = new SimilarAdapter(bestDealList);
        recyclerView.setAdapter(similarAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false));

        similarAdapter.setItemCickListener(new SimilarAdapter.OnMyItemCickListener() {
            @Override
            public void DoSomeThing(int position) {

                bestDeal = bestDealList.get(position);
                num = 1;

                imageView.setImageResource(bestDeal.getImageId());
                titleTxt.setText(bestDeal.getTitle());
                decriptionTxt.setText(bestDeal.getDescription());
                starTxt.setText("" + bestDeal.getStar());
                ratingBar.setRating((float) bestDeal.getStar());
                totalTxt.setText(num * bestDeal.getPrice() + " $");
                if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
                    priceTxt.setText(bestDeal.getPrice() + " $");
                    numTxt.setText(num + " ");
                }
                if (bestDeal.getCategoryId() == 3){
                    priceTxt.setText(bestDeal.getPrice() + " $");
                    numTxt.setText(num + " ");
                }
                if (bestDeal.getCategoryId() == 4){
                    priceTxt.setText(bestDeal.getPrice() + " $");
                    numTxt.setText(num + " ");
                }
                if (bestDeal.getCategoryId() == 5){
                    priceTxt.setText(bestDeal.getPrice() + " $");
                    numTxt.setText(num + " ");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void BtnBack (View view){
        Intent intent = new Intent(this , MainActivity.class);
        intent.putExtra("idCategory" , bestDeal.getCategoryId());
        startActivity(intent);
    }

    public void DecBtn_Onclick (View view){
        if (num > 1) num--;
        totalTxt.setText(num * bestDeal.getPrice() + " $");
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 3){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 4){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 5){
            numTxt.setText(num + " ");
        }
    }
    public void IncBtn_Onclick (View view){
        num++;
        totalTxt.setText(num * bestDeal.getPrice() + " $");
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 3){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 4){
            numTxt.setText(num + " ");
        }
        if (bestDeal.getCategoryId() == 5){
            numTxt.setText(num + " ");
        }
    }
    public void AddBtn_Onclick (View view){
        //Do sonmething
        Intent intent = new Intent(this , Cart.class);
        intent.putExtra("bestDeal" , bestDeal);
        intent.putExtra("num" , num);
        startActivity(intent);

    }
}