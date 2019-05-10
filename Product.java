package udp;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer orderID;
    private Integer productID;
    private String description;
    private Integer[] operation = new Integer[3];
    private Integer currentOperation;

    @Override
    public String toString(){
        return "[PRODUCT ID: " + productID + "]-> [" + description + "], [Current Operation: " + currentOperation + "] [OrderID: " + orderID + "]";
    }

    public Product() {
        this.productID = 5;
        this.description = "iPhone 11";
        this.currentOperation = 0;
    }
    public Product(Integer orderID){
        this.orderID = orderID;
    }

    public Product(Integer productID, String description) {
        this.productID = productID;
        this.description = description;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public String getDescription() {
        return description;
    }

    public int getProductID() {
        return productID;
    }

    public Integer getCurrentOperation() {
        return currentOperation;
    }

    public Integer[] getOperation() {
        return operation;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setCurrentOperation(Integer currentOperation) {
        this.currentOperation = currentOperation;
    }

    public void setOperation(Integer[] operation) {
        this.operation = operation;
    }
}
