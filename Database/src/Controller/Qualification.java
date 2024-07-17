package Controller;

    import javafx.beans.property.SimpleStringProperty;
    import javafx.beans.property.StringProperty;

    public class Qualification {

    private final StringProperty qualificationId = new SimpleStringProperty();
    private final StringProperty degree = new SimpleStringProperty();

    public Qualification(String qualificationId, String degree) {
        this.qualificationId.set(qualificationId);
        this.degree.set(degree);
    }

    public Qualification() {
    }


    public String getQualificationId() {
        return qualificationId.get();
    }

    public StringProperty qualificationIdProperty() {
        return qualificationId;
    }

    public void setQualificationId(String qualificationId) {
        this.qualificationId.set(qualificationId);
    }

    public String getDegree() {
        return degree.get();
    }

    public StringProperty degreeProperty() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree.set(degree);
    }

    @Override
    public String toString() {
        return degree.get();
    }
}
