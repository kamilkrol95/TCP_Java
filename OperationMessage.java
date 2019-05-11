package udp;

import java.io.Serializable;

public class OperationMessage implements Serializable {

    private Product product;
    private Station station;

    public OperationMessage() {
        this.product = new Product();
        this.station = new Station();
        this.station.setStationID(-1);
        this.station.setDescription("Not specified");
        this.station.setState(StationState.DEFAULT);
    }

    public OperationMessage(Station newStation, Product newProduct) {
        this.station = newStation;
        this.product = newProduct;
    }

    public Product getProduct() {
        return product;
    }

    public Station getStation() {
        return station;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
