package udp;

public class Product {

    private Integer productID;
    private String description;

    public String getDescription() {
        return description;
    }

    public int getProductID() {
        return productID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Product() {
        this.productID = 5;
        this.description = "iPhone 11";
    }

    public Product(Integer productID, String description) {
        this.productID = productID;
        this.description = description;
    }

}
