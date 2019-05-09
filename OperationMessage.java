package udp;

import java.io.Serializable;

public class OperationMessage implements Serializable {

    private Integer stationID;
    private String description;
    private Integer productID;

    public OperationMessage() {
        this.stationID = -1;
        this.description = "NULL";
    }

    public OperationMessage(Integer stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }

    public Integer getProductID() {
        return productID;
    }

    public Integer getStationID() {
        return stationID;
    }

    public String getDescription() {
        return description;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
