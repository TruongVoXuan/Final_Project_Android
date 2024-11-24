package truong.vx.wheyshop;

import android.os.Parcel;
import android.os.Parcelable;

public class BestDeal implements Parcelable {
    private String title;
    private int imageId;
    private String description;
    private double price;
    private double star;
    private int id;
    private int categoryId;
    private int locationId;

    public BestDeal(String title, int imageId, String description, double price, double star, int id, int categoryId, int locationId) {
        this.title = title;
        this.imageId = imageId;
        this.description = description;
        this.price = price;
        this.star = star;
        this.id = id;
        this.categoryId = categoryId;
        this.locationId = locationId;
    }

    protected BestDeal(Parcel in) {
        title = in.readString();
        imageId = in.readInt();
        description = in.readString();
        price = in.readDouble();
        star = in.readDouble();
        id = in.readInt();
        categoryId = in.readInt();
        locationId = in.readInt();
    }

    public static final Creator<BestDeal> CREATOR = new Creator<BestDeal>() {
        @Override
        public BestDeal createFromParcel(Parcel in) {
            return new BestDeal(in);
        }

        @Override
        public BestDeal[] newArray(int size) {
            return new BestDeal[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(imageId);
        dest.writeString(description);
        dest.writeDouble(price);
        dest.writeDouble(star);
        dest.writeInt(id);
        dest.writeInt(categoryId);
        dest.writeInt(locationId);
    }
    public boolean matchesCriteria(Double minPrice, Double maxPrice, Double minStar, Integer categoryId) {
        if (minPrice != null && price < minPrice) return false;
        if (maxPrice != null && price > maxPrice) return false;
        if (minStar != null && star < minStar) return false;
        if (categoryId != null && this.categoryId != categoryId) return false;
        return true;
    }

    // Getters and setters...
    public String getTitle() {
        return title;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getStar() {
        return star;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}