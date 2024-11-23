package truong.vx.wheyshop;

public class Category {
    private String name;
    private int imageId;
    private int id;

    public Category(String name, int imageId, int id) {
        this.name = name;
        this.imageId = imageId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
