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
            priceTxt.setText(bestDeal.getPrice() + " $/Kg");
            numTxt.setText(num + " Kg");
        }
        if (bestDeal.getCategoryId() == 3){
            priceTxt.setText(bestDeal.getPrice() + " $/Box");
            numTxt.setText(num + " Box");
        }
        if (bestDeal.getCategoryId() == 4){
            priceTxt.setText(bestDeal.getPrice() + " $/Can");
            numTxt.setText(num + " Can");
        }
        if (bestDeal.getCategoryId() == 5){
            priceTxt.setText(bestDeal.getPrice() + " $/Pec");
            numTxt.setText(num + " Pec");
        }




        recyclerView = findViewById(R.id.BestDealView1);
        List<BestDeal> bestDealList = new ArrayList<BestDeal>();
        if (bestDeal.getCategoryId() == 1){
            bestDealList.add (new BestDeal("Strawberry" , R.drawable.atrawberry , "abc" ,3.96 , 4.4 , 1, 1, 1));
            bestDealList.add (new BestDeal("Apple" , R.drawable.apple , "abc" ,4.5 , 3.5 , 2, 1, 1));
            bestDealList.add (new BestDeal("Berry" , R.drawable.berry , "abc" ,3.5 , 1 , 3, 1, 1));
            bestDealList.add (new BestDeal("Orange" , R.drawable.orange , "abc" ,5 , 4.3 , 4, 1, 1));
            bestDealList.add (new BestDeal("Pineaple" , R.drawable.pineaplle , "abc" ,3.76 , 2.5 , 5, 1, 1));
            bestDealList.add (new BestDeal("Watermelon" , R.drawable.watermelon , "abc" ,6.49 , 5 , 6, 1, 1));
            bestDealList.add (new BestDeal("Banana" , R.drawable.banana , "abc" ,3.69 , 5 , 7, 1, 1));
            bestDealList.add (new BestDeal("Vargua" , R.drawable.vargua , "abc" ,2.49 , 4.2 , 8, 1, 1));
            bestDealList.add (new BestDeal("Kiwi" , R.drawable.kiwi1 , "abc" ,1.85 , 4.3 , 9, 1, 1));
        }
        if (bestDeal.getCategoryId() == 2){
            bestDealList.add (new BestDeal("Carot" , R.drawable.carot , "abc" ,3.96 , 4.4 , 1, 2, 1));
            bestDealList.add (new BestDeal("Cabbage" , R.drawable.bapcai , "abc" ,4.49 , 3.5 , 2, 2, 1));
            bestDealList.add (new BestDeal("Bitter cabbage" , R.drawable.raucai , "abc" ,3.5 , 2.3 , 3, 2, 1));
            bestDealList.add (new BestDeal("Beetroot" , R.drawable.cuden , "abc" ,3.5 , 3.7 , 4, 2, 1));
            bestDealList.add (new BestDeal("Tomato" , R.drawable.tomato , "abc" ,3.75 , 4.7 , 5, 2, 1));
            bestDealList.add (new BestDeal("Potato" , R.drawable.potato , "abc" ,3.25 , 2.8, 6, 2, 1));
            bestDealList.add (new BestDeal("Spinach" , R.drawable.raumuong , "abc" ,2.5 , 3.9 , 7, 2, 1));
            bestDealList.add (new BestDeal("Broccoli" , R.drawable.suplo , "abc" ,2.79 , 5, 8, 2, 1));
            bestDealList.add (new BestDeal("Eggplant" , R.drawable.catim , "abc" ,1.29 , 1.4 , 9, 2, 1));
        }
        if (bestDeal.getCategoryId() == 3){
            bestDealList.add (new BestDeal("Mix" , R.drawable.all , "abc" ,3.96 , 4.4 , 1, 3, 1));
            bestDealList.add (new BestDeal("Milo" , R.drawable.milo , "abc" ,4.49 , 3.5 , 2, 3, 1));
            bestDealList.add (new BestDeal("TH True milk" , R.drawable.thtrue , "abc" ,3.5 , 2.3 , 3, 3, 1));
            bestDealList.add (new BestDeal("Dau nanh" , R.drawable.daunanh , "abc" ,3.5 , 3.7 , 4, 3, 1));
            bestDealList.add (new BestDeal("Nutri food" , R.drawable.nutri , "abc" ,3.75 , 4.7 , 5, 3, 1));
            bestDealList.add (new BestDeal("Optimum" , R.drawable.optimum , "abc" ,3.25 , 2.8, 6, 3, 1));
            bestDealList.add (new BestDeal("Oc cho" , R.drawable.occho , "abc" ,2.5 , 3.9 , 7, 3, 1));
            bestDealList.add (new BestDeal("Yen mach" , R.drawable.yenmach , "abc" ,2.79 , 5, 8, 3, 1));
        }
        if (bestDeal.getCategoryId() == 4){
            bestDealList.add (new BestDeal("Orange" , R.drawable.cam , "abc" ,3.96 , 4.4 , 1, 4, 1));
            bestDealList.add (new BestDeal("Luu" , R.drawable.luu , "abc" ,4.49 , 3.5 , 2, 4, 1));
            bestDealList.add (new BestDeal("Kiwi" , R.drawable.kiwi , "abc" ,3.5 , 2.3 , 3, 4, 1));
            bestDealList.add (new BestDeal("Coconut" , R.drawable.dua , "abc" ,3.5 , 3.7 , 4, 4, 1));
            bestDealList.add (new BestDeal("Watermelon" , R.drawable.duahau , "abc" ,3.75 , 4.7 , 5, 4, 1));
            bestDealList.add (new BestDeal("Pineaple" , R.drawable.thom , "abc" ,3.25 , 2.8, 6, 4, 1));
            bestDealList.add (new BestDeal("Apple" , R.drawable.tao , "abc" ,2.5 , 3.9 , 7, 4, 1));
            bestDealList.add (new BestDeal("Vargua" , R.drawable.oi , "abc" ,2.79 , 5, 8, 4, 1));
        }
        if (bestDeal.getCategoryId() == 5){
            bestDealList.add (new BestDeal("BreadVN" , R.drawable.banh1 , "abc" ,3.96 , 4.4 , 1, 2, 1));
            bestDealList.add (new BestDeal("Bread" , R.drawable.banh2 , "abc" ,4.49 , 3.5 , 2, 2, 1));
            bestDealList.add (new BestDeal("Bread New" , R.drawable.banh3 , "abc" ,3.5 , 2.3 , 3, 2, 1));
            bestDealList.add (new BestDeal("Bread Franch" , R.drawable.banh4 , "abc" ,3.5 , 3.7 , 4, 2, 1));
            bestDealList.add (new BestDeal("Sanwich" , R.drawable.banh5 , "abc" ,3.75 , 4.7 , 5, 2, 1));
            bestDealList.add (new BestDeal("Sanwich New" , R.drawable.banh6 , "abc" ,3.25 , 2.8, 6, 2, 1));
            bestDealList.add (new BestDeal("Sanwich Berry" , R.drawable.banh7 , "abc" ,2.5 , 3.9 , 7, 2, 1));
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
                    priceTxt.setText(bestDeal.getPrice() + " $/Kg");
                    numTxt.setText(num + " Kg");
                }
                if (bestDeal.getCategoryId() == 3){
                    priceTxt.setText(bestDeal.getPrice() + " $/Box");
                    numTxt.setText(num + " Box");
                }
                if (bestDeal.getCategoryId() == 4){
                    priceTxt.setText(bestDeal.getPrice() + " $/Can");
                    numTxt.setText(num + " Can");
                }
                if (bestDeal.getCategoryId() == 5){
                    priceTxt.setText(bestDeal.getPrice() + " $/Pec");
                    numTxt.setText(num + " Pec");
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
            numTxt.setText(num + " Kg");
        }
        if (bestDeal.getCategoryId() == 3){
            numTxt.setText(num + " Box");
        }
        if (bestDeal.getCategoryId() == 4){
            numTxt.setText(num + " Can");
        }
        if (bestDeal.getCategoryId() == 5){
            numTxt.setText(num + " Pec");
        }
    }
    public void IncBtn_Onclick (View view){
        num++;
        totalTxt.setText(num * bestDeal.getPrice() + " $");
        if (bestDeal.getCategoryId() == 1 || bestDeal.getCategoryId() == 2){
            numTxt.setText(num + " Kg");
        }
        if (bestDeal.getCategoryId() == 3){
            numTxt.setText(num + " Box");
        }
        if (bestDeal.getCategoryId() == 4){
            numTxt.setText(num + " Can");
        }
        if (bestDeal.getCategoryId() == 5){
            numTxt.setText(num + " Pec");
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