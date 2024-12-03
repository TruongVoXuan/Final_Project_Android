package truong.vx.wheyshop;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WishlistManager {
    private static final String PREFS_NAME = "wishlist_prefs";
    private static final String WISHLIST_KEY = "wishlist";
    private static WishlistManager instance;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private WishlistManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public static WishlistManager getInstance(Context context) {
        if (instance == null) {
            instance = new WishlistManager(context.getApplicationContext());
        }
        return instance;
    }

    // Lấy danh sách yêu thích
    public List<BestDeal> getWishlist() {
        String json = sharedPreferences.getString(WISHLIST_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<BestDeal>>() {}.getType();
        return gson.fromJson(json, type);
    }

    // Lưu danh sách yêu thích
    private void saveWishlist(List<BestDeal> wishlist) {
        String json = gson.toJson(wishlist);
        sharedPreferences.edit().putString(WISHLIST_KEY, json).apply();
    }

    // Thêm sản phẩm vào danh sách yêu thích
    public void addToWishlist(BestDeal bestDeal) {
        List<BestDeal> wishlist = getWishlist();
        if (!wishlist.contains(bestDeal)) { // Tránh trùng lặp
            wishlist.add(bestDeal);
            saveWishlist(wishlist);
        }
    }

    // Xóa sản phẩm khỏi danh sách yêu thích
    public void removeFromWishlist(BestDeal bestDeal) {
        List<BestDeal> wishlist = getWishlist();
        if (wishlist.contains(bestDeal)) {
            wishlist.remove(bestDeal);
            saveWishlist(wishlist);
        }
    }

    // Xóa toàn bộ danh sách yêu thích
    public void clearWishlist() {
        sharedPreferences.edit().remove(WISHLIST_KEY).apply();
    }
}
