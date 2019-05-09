package udp;

public class Station {

    private Integer stationID;
    private String description;

    public String getDescription() {
        return description;
    }

    public Integer getStationID() {
        return stationID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public Station() {
        this.stationID = 2;
        this.description = "Drilling";
    }

    public Station(Integer stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }
}
