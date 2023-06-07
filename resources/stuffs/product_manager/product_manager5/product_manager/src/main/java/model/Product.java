package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private String img;
    private int category_id;
    private int brand_id;
    private String category;
    private String brand;

    public Product(int id, String name, String description, int price, String img, String category, String brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.category = category;
        this.brand = brand;
    }

    public Product(String name, String description, int price, String img, int category_id, int brand_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.category_id = category_id;
        this.brand_id = brand_id;
    }

    public Product(int id, String name, String description, int price, String img, int category_id, int brand_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.category_id = category_id;
        this.brand_id = brand_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
