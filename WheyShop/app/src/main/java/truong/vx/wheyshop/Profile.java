package truong.vx.wheyshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void Btnexplore(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
    }

    public void Btncart(View view){
        Intent intent = new Intent(this , Cart.class);
        startActivity(intent);
    }

    public void BtnWishList(View view){
        Intent intent = new Intent(this , WishList.class);
        startActivity(intent);
    }

    public void BtnMyOrder(View view){
        Intent intent = new Intent(this , MyOrder.class);
        startActivity(intent);
    }

    public void BtnBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void SignOutBtn(View view) {
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
    }

}