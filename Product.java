package udp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

enum ProductState {
    TODO,
    IN_PROGRESS,
    DONE;

    private static Map<Integer, ProductState> CACHE = null;

    private static void fillCACHE(){
        if (CACHE != null){
            return;
        }

        final Map<Integer, ProductState> cache = new HashMap<>();
        for ( ProductState productState : ProductState.values()){
            cache.put(productState.ordinal(), productState);
        }
        CACHE = cache;
    }

    public static ProductState getProductState(int id){
        fillCACHE();
        return CACHE.get(id);
    }
}

public class Product implements Serializable {

    private Integer orderID;
    private Integer productID;
    private String description;
    private Integer[] operation = new Integer[3];
    private Integer currentOperation;
    private ProductState productState = ProductState.TODO;

    @Override
    public String toString() {
        return "[PRODUCT ID: " + productID + " (" + description + ")], [Current Operation: " + currentOperation + "] [OrderID: " + orderID + "] [State: " + productState + "]";
    }

    public String orderIDToString() {
        return "[ORDER ID: " + orderID + "]";
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

    public ProductState getState() {
        return productState;
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

    public void setState(ProductState productState) {
        this.productState = productState;
    }
}
