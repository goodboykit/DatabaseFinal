package Controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistrationDetails {
    
    private final StringProperty registrationID = new SimpleStringProperty(this, "RegistrationID");
    private final StringProperty name = new SimpleStringProperty(this, "firstname");
    private final StringProperty middleename = new SimpleStringProperty(this, "middlename");
    private final StringProperty lastname = new SimpleStringProperty(this, "lastname");

    private final StringProperty username = new SimpleStringProperty(this, "username");
    private final StringProperty password = new SimpleStringProperty(this, "password");
    private final StringProperty mobile = new SimpleStringProperty(this, "mobile");

    private final StringProperty email = new SimpleStringProperty(this, "email");

    private final StringProperty image = new SimpleStringProperty(this, "image");
    private final StringProperty gender = new SimpleStringProperty(this, "gender");

    private final StringProperty address = new SimpleStringProperty(this, "address");


    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty<>(this, "birthday");


    private final StringProperty country = new SimpleStringProperty(this, "country");

    private final StringProperty city_id = new SimpleStringProperty(this, "city_id");


    public StringProperty cityIdProperty() {
        return city_id;
    }

    public String getCityId() {
        return city_id.get();
    }

    public void setCityId(String newCityId) {
        city_id.set(newCityId);
    }

    public StringProperty countryProperty() {
        return country;
    }

    public String getCountryProperty() {
        return country.get();
    }

    public void setCountryProperty (String newCountry) {
        country.set(newCountry);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String newAddress) {
        address.set(newAddress);
    }


    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(Date newDateApplied) {
        LocalDate newBirthdate = newDateApplied.toLocalDate();
        birthday.set(newBirthdate);
    }

    public StringProperty idProperty() {
        return registrationID;
    }

    public String getId() {
        return registrationID.get();
    }

    public void setId(String newRegisID) {
        registrationID.set(newRegisID);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        name.set(newName);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public String getGender() {
        return gender.get();
    }

    public void setGender(String newGender) {
        gender.set(newGender);
    }

    public StringProperty middlenameProperty() {
        return middleename;
    }

    public String getmiddleName() {
        return middleename.get();
    }

    public void setMiddleName(String newMiddleName) {
        middleename.set(newMiddleName);
    }

   
    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getLastName() {
        return lastname.get();
    }

    public void setLastName(String newlastname) {
        lastname.set(newlastname);
    }

    public StringProperty userNameProperty() {
        return username;
    }

    public String getUserName() {
        return username.get();
    }

    public void setUserName(String newUserName) {
        username.set(newUserName);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword (String newPassword) {
        password.set(newPassword);
    }

    public StringProperty mobileProperty() {
        return mobile;
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile (String newMobile) {
        mobile.set(newMobile);
    }


    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail (String newEmail) {
        email.set(newEmail);
    }


    public StringProperty imageProperty() {
        return image;
    }

    public String getImage() {
        return image.get();
    }

    public void setImage(String newImage) {
        image.set(newImage);
    }
}
