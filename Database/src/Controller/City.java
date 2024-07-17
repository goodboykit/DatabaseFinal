package Controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class City {
    private final IntegerProperty cityId;
    private final StringProperty cityName;

    public City(int cityId, String cityName) {
        this.cityId = new SimpleIntegerProperty(cityId);
        this.cityName = new SimpleStringProperty(cityName);
    }

    public int getCityId() {
        return cityId.get();
    }

    public IntegerProperty cityIdProperty() {
        return cityId;
    }

    public String getCityName() {
        return cityName.get();
    }

    public StringProperty cityNameProperty() {
        return cityName;
    }

    @Override
    public String toString() {
        return cityName.get(); 
    }
}
