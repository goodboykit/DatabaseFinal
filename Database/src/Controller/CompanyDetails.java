package Controller;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CompanyDetails {
    
    private final StringProperty company_id = new SimpleStringProperty(this, "company_id");
    private final StringProperty companyname = new SimpleStringProperty(this, "companyname");
    private final StringProperty companyaddress = new SimpleStringProperty(this, "companyaddress");
    private final StringProperty companycontact = new SimpleStringProperty(this, "companycontact");


    private final StringProperty companyemail = new SimpleStringProperty(this, "companyemail");
    private final StringProperty postalcode = new SimpleStringProperty(this, "postalcode");
    private final StringProperty salary = new SimpleStringProperty(this, "salary");

    //-------
    private final StringProperty qualification = new SimpleStringProperty(this, "qualification");
    private final StringProperty qualification_id = new SimpleStringProperty(this, "qualification_id");
    private final StringProperty qualificationDegree = new SimpleStringProperty(this, "qualificationDegree");
    private final StringProperty degree = new SimpleStringProperty(this, "degree");

    //-------

    private final StringProperty experiencerequired = new SimpleStringProperty(this, "experiencerequired");
    private final StringProperty jobtype = new SimpleStringProperty(this, "jobtype");
    private final ObjectProperty<LocalDate> lastdateapply = new SimpleObjectProperty<>(this, "lastdateapply");

    private final StringProperty jobPosition = new SimpleStringProperty(this, "jobPosition");
    private final StringProperty jobDescription = new SimpleStringProperty(this, "jobDescription");
    private final StringProperty hiringposition_id = new SimpleStringProperty(this, "hiringposition_id");


    private final StringProperty image = new SimpleStringProperty(this, "image");



    public StringProperty qualificationProperty() {
        return qualification;
    }

    public String getQualification() {
        return qualification.get();
    }

    public void setQualification(String newQualification) {
        qualification.set(newQualification);
    }

    public StringProperty degreeProperty() {
        return degree;
    }

    public String getDegree() {
        return degree.get();
    }

    public void setDegree(String newDegree) {
        degree.set(newDegree);
    }


    public StringProperty qualificationIdProperty() {
        return qualification_id;
    }

    public int getQualificationId() {
        if (qualification_id == null || qualification_id.get() == null) {
            return 0; 
        } else {
            return Integer.parseInt(qualification_id.get());
        }
    }
    
    public void setQualificationID(String newQualificationID) {
        qualification_id.set(newQualificationID);
    }

  
    public StringProperty qualificationDegreeProperty() {
        return qualificationDegree;
    }

    public String getQualificationDegree() {
        return qualificationDegree.get();
    }

    public void setQualificationDegree(String newQualificationDegree) {
        qualificationDegree.set(newQualificationDegree);
    }

    public StringProperty hiringPositionProperty() {
        return hiringposition_id;
    }

    public String getHiringPosition() {
        return hiringposition_id.get();
    }

    public void setHiringPosition(String newhiringposition_id) {
        hiringposition_id.set(newhiringposition_id);
    }
   

    public StringProperty idProperty() {
        return company_id;
    }

    public String getId() {
        return company_id.get();
    }

    public void setId(String newcompanyID) {
        company_id.set(newcompanyID);
    }

    public StringProperty companyProperty() {
        return companyname;
    }

    public String getCompany() {
        return companyname.get();
    }

    public void setCompany(String newCompany) {
        companyname.set(newCompany);
    }


    public StringProperty jobPositionProperty() {
        return jobPosition;
    }

    public String getJobPosition() {
        return jobPosition.get();
    }

    public void setJobPosition(String newjobPosition) {
        jobPosition.set(newjobPosition);
    }


    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }

    public String getjobDescriptionProperty() {
        return jobDescription.get();
    }

    public void setJobDescription(String newJobDescription) {
        jobDescription.set(newJobDescription);
    }



    public StringProperty emailProperty() {
        return companyemail;
    }

    public String getEmail() {
        return companyemail.get();
    }

    public void setEmail(String newcompanyEmail) {
        companyemail.set(newcompanyEmail);
    }



    public StringProperty companyAddress() {
        return companyaddress;
    }

    public String getCompanyAddress() {
        return companyaddress.get();
    }

    public void setCompanyAddress(String newcompanyAddress) {
        companyaddress.set(newcompanyAddress);
    }


    public StringProperty contactProperty() {
        return companycontact;
    }

    public String getContact() {
        return companycontact.get();
    }

    public void setContact(String newContact) {
        companycontact.set(newContact);
    }


    public StringProperty postalProperty() {
        return postalcode;
    }

    public String getPostal() {
        return postalcode.get();
    }

    public void setPostal(String newPostal) {
        postalcode.set(newPostal);
    }



    public StringProperty salaryProperty() {
        return salary;
    }

    public String getSalary() {
        return salary.get();
    }

    public void setSalary(String newSalary) {
        salary.set(newSalary);
    }


    


    public StringProperty experienceProperty() {
        return experiencerequired;
    }

    public String getExperience() {
        return experiencerequired.get();
    }

    public void setExperience(String newExperience) {
        experiencerequired.set(newExperience);
    }


    public StringProperty jobtypeProperty() {
        return jobtype;
    }

    public String getJobType() {
        return jobtype.get();
    }

    public void setJobType(String newjobType) {
        jobtype.set(newjobType);
    }


    public ObjectProperty<LocalDate> lastdateProperty() {
        return lastdateapply;
    }

    public LocalDate getLastdateApply() {
        return lastdateapply.get();
    }

    public void setLastdateProperty(LocalDate newLastDate) {
        lastdateapply.set(newLastDate);
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

