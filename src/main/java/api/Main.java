package api;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;

import com.google.gson.Gson;
import org.postgis.Point;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;


public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));

        Gson gson = new Gson();

        post("/insert/ping", (req, res) -> {
            InsertPingBody insertPingBody = gson.fromJson(req.body(), InsertPingBody.class);
            System.out.println(insertPingBody.toString());
            try(Connection connection = DatabaseUrl.extract().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(Constants.PING_INSERT);
                preparedStatement.setString(1, insertPingBody.getId());
    //            Point point = new Point(insertPingBody.getLon(), insertPingBody.getLat());
    //            preparedStatement.setObject(2, point.toString(), Types.OTHER);
                preparedStatement.setDouble(2, insertPingBody.getLon());
                preparedStatement.setDouble(3, insertPingBody.getLat());
                preparedStatement.setTimestamp(4, Helper.getPostgresTimestampFromEpoch(insertPingBody.getEpoch()));
                preparedStatement.executeUpdate();
                preparedStatement.close();
                return gson.toJson(new StatusBody(true, "none"));
            } catch (Exception e) {
                e.printStackTrace();
                return gson.toJson(new StatusBody(false, e.getMessage()));
            }
        });

        get("/get/users/:lon/:lat/:startEpoch/:endEpoch/:radius", (req, res) -> {
            double lon = Double.parseDouble(req.params("lon"));
            double lat = Double.parseDouble(req.params("lat"));
            long startEpoch = Long.parseLong(req.params("startEpoch"));
            long endEpoch = Long.parseLong(req.params("endEpoch"));
            long radius = Long.parseLong(req.params("radius"));
            try(Connection connection = DatabaseUrl.extract().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(Constants.USERS_GET);
                preparedStatement.setTimestamp(1, Helper.getPostgresTimestampFromEpoch(startEpoch));
                preparedStatement.setTimestamp(2, Helper.getPostgresTimestampFromEpoch(endEpoch));
                preparedStatement.setDouble(3, lon);
                preparedStatement.setDouble(4, lat);
                preparedStatement.setLong(5, radius);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<String> ids = new ArrayList<>();
                while(resultSet.next()) {
                    String id = resultSet.getString(1);
                    ids.add(id);
                }
                resultSet.close();
                preparedStatement.close();
                return gson.toJson(ids);
            } catch (Exception e) {
                res.status(404);
                e.printStackTrace();
                return gson.toJson(new StatusBody(false, e.getMessage()));
            }
        });


    }

}
