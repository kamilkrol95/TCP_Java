package udp;

import java.io.Serializable;

enum StationState {
    WORK, PASS, DEFAULT
}

public class Station implements Serializable {

    private Integer stationID;
    private String description;
    private StationState stationState = StationState.DEFAULT;

    @Override
    public String toString(){
        return "[STATION ID: " + stationID + " (" + description + ")], State: [" + stationState.toString() + "]";
    }

    public String stationIDToString() {
        return "[STATION ID: " + stationID + "]";
    }

    public Station() {
        this.stationID = 2;
        this.description = "Drilling";
    }

    public Station(Integer stationID) {
        this.stationID = stationID;
    }

    public Station(Integer stationID, String description) {
        this.stationID = stationID;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStationID() {
        return stationID;
    }

    public StationState getState() {
        return stationState;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public void setState(StationState stationState) {
        this.stationState = stationState;
    }
}
