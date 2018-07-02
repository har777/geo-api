package api;

public interface Constants {
    String PING_INSERT = "INSERT INTO pings(id, point, ts) VALUES(?, ST_SetSRID(ST_MakePoint(?, ?), 4326)::geography, ?);";
    String USERS_GET = "SELECT DISTINCT(id) FROM pings WHERE ts >= ? AND ts <= ? AND ST_DWithin(point, ST_MakePoint(?, ?)::geography, ?); ";
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
