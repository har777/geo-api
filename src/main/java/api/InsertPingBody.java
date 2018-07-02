package api;

public class InsertPingBody {
    public InsertPingBody(String id, long epoch, double lon, double lat) {
        this.id = id;
        this.epoch = epoch;
        this.lon = lon;
        this.lat = lat;
    }

    String id;
    long epoch;
    double lon;
    double lat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "InsertPingBody{" +
                "id='" + id + '\'' +
                ", epoch=" + epoch +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
