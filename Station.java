package udp;

import java.io.Serializable;

enum State {
    WORK, PASS, FAILURE, DEFAULT
}

public class Station implements Serializable {

    private Integer stationID;
    private String description;
    private State state = State.DEFAULT;

    @Override
    public String toString(){
        return "[STATION ID: " + stationID + "]-> [" + description + "], State: [" + state.toString() + "]";
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

    public State getState() {
        return state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public void setState(State state) {
        this.state = state;
    }
}
