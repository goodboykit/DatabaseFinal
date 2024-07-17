package Controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;

public class AppliedUsersDetails {
    
    private final StringProperty applicantName = new SimpleStringProperty(this, "applicantName");
    private final StringProperty companyName = new SimpleStringProperty(this, "companyName");
    private final StringProperty jobPosition = new SimpleStringProperty(this, "jobPosition");
    private final StringProperty jobType = new SimpleStringProperty(this, "jobType");
    private final ObjectProperty<LocalDate> lastDateApplied = new SimpleObjectProperty<>(this, "lastDateApplied");
    private final StringProperty imagePath = new SimpleStringProperty(this, "imagePath");

    private final StringProperty address = new SimpleStringProperty(this, "address");
    private final StringProperty mobile = new SimpleStringProperty(this, "mobile");

    private final StringProperty companyAddress = new SimpleStringProperty(this, "companyAddress");
    private final StringProperty companyEmail = new SimpleStringProperty(this, "companyEmail");
    private final StringProperty salary = new SimpleStringProperty(this, "salary");


    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final StringProperty birthday = new SimpleStringProperty(this, "birthday");
    
    // Additional property for Qualification degree
    private final StringProperty degreeFinished = new SimpleStringProperty(this, "degreeFinished");
    private final StringProperty dateApplied = new SimpleStringProperty(this, "dateApplied");
    private final StringProperty experience = new SimpleStringProperty(this, "experience");
    private final StringProperty jobDescription = new SimpleStringProperty(this, "jobDescription");

    private final IntegerProperty id = new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public void setId(int userId) {
        id.set(userId);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getDateApplied() {
        return dateApplied.get();
    }

    public void setDateApplied(String dateApplied) {
        this.dateApplied.set(dateApplied);
    }

    public StringProperty dateAppliedProperty() {
        return dateApplied;
    }

    public String getExperience() {
        return experience.get();
    }

    public void setExperience(String experience) {
        this.experience.set(experience);
    }

    public StringProperty experienceProperty() {
        return experience;
    }

    public String getJobDescription() {
        return jobDescription.get();
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription.set(jobDescription);
    }

    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }

    public String getMobile() {
        return mobile.get();
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public StringProperty mobileProperty() {
        return mobile;
    }
    
    public String getCompanyAddress() {
        return companyAddress.get();
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress.set(companyAddress);
    }

    public StringProperty companyAddressProperty() {
        return companyAddress;
    }

    public String getSalary() {
        return salary.get();
    }

    public void setSalary(String salary) {
        this.salary.set(salary);
    }

    public StringProperty salaryProperty() {
        return salary;
    }

    public String getCompanyEmail() {
        return companyEmail.get();
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail.set(companyEmail);
    }

    public StringProperty companyEmailProperty() {
        return companyEmail;
    }

    public String getDegreeFinished() {
        return degreeFinished.get();
    }

    public void setDegreeFinished(String degreeFinished) {
        this.degreeFinished.set(degreeFinished);
    }

    public StringProperty degreeFinishedProperty() {
        return degreeFinished;
    }

    public String getBirthday() {
        return birthday.get();
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getApplicantName() {
        return applicantName.get();
    }

    public void setApplicantName(String applicantName) {
        this.applicantName.set(applicantName);
    }

    public StringProperty applicantNameProperty() {
        return applicantName;
    }


public String getCompanyName() {
        return companyName.get();
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public String getJobPosition() {
        return jobPosition.get();
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition.set(jobPosition);
    }

    public StringProperty jobPositionProperty() {
        return jobPosition;
    }

    public String getJobType() {
        return jobType.get();
    }

    public void setJobType(String jobType) {
        this.jobType.set(jobType);
    }

    public StringProperty jobTypeProperty() {
        return jobType;
    }

    public LocalDate getLastDateApplied() {
        return lastDateApplied.get();
    }

    public void setLastDateApplied(String lastDateApplied) {
        this.lastDateApplied.set(LocalDate.parse(lastDateApplied));
    }

    public ObjectProperty<LocalDate> lastDateAppliedProperty() {
        return lastDateApplied;
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public void setImagePath(String path) {
        imagePath.set(path);
    }
}
