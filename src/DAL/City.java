package DAL;

/**
 * Created by Nguyễn Đăng Dũng on 3/6/2017 9:21 PM
 * Project: BaiTapLon
 */
public class City {
    private int cityId;
    private String cityName;

    public City() {
    }

    public City(int cityId, String cityName) {

        this.cityId = cityId;
        this.cityName = cityName;
    }

    public int getCityId() {

        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName +
                '}';
    }
}
