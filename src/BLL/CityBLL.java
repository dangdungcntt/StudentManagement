package BLL;

import DAL.City;
import DAL.Database;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Nguyá»…n Ä�Äƒng DÅ©ng on 3/6/2017 9:24 PM
 * Project: BaiTapLon
 */
public class CityBLL {
    private HashMap<Integer, City> mapCity;
    private Database database;

    public CityBLL() {
        mapCity = new HashMap<Integer, City>();
        database = new Database();
    }

    public HashMap<Integer, City> getMapCity() {
        return mapCity;
    }

    public void setMapCity(HashMap<Integer, City> mapCity) {
        this.mapCity = mapCity;
    }

    @Override
    public String toString() {
        return "CityBLL{" +
                "mapCity=" + mapCity +
                '}';
    }

    public boolean readData() throws ConnectException {
        try {
            Connection connection = database.connect();
            String sql = "SELECT * FROM tinhthanh";
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                int maTinh = resultSet.getInt(1);
                String tenTinh = resultSet.getString(2);
                mapCity.put(maTinh, new City(maTinh, tenTinh));
            }
            resultSet.close();
            pStatement.close();
            database.close();
        } catch (SQLException e) {
            throw new ConnectException();
        }
        return mapCity.size() != 0;
    }

    public String getNameById(int cityId) {
        return mapCity.get(cityId).getCityName();
    }

    public int getIdByName(String cityName) {
        for (City city : mapCity.values()) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                return city.getCityId();
            }
        }
        return -1;
    }
}
