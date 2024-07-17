package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.FileOutputStream;
import java.awt.Desktop;


public class AdminInterface implements Initializable {

    @FXML
    private Button CancelBtn;

    @FXML
    private Button ImportBtn;

    @FXML
    private Button DeleteBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private AnchorPane company_imageform;

    @FXML
    private AnchorPane companies_mainform;

    @FXML
    private AnchorPane applicants_mainform;

    @FXML
    private TableView<AppliedUsersDetails> AppliedTable;

    @FXML
    private TableColumn<AppliedUsersDetails, String> applicantNameCol;

    @FXML
    private TableColumn<AppliedUsersDetails, String> companyNameCol;

    @FXML
    private TableColumn<AppliedUsersDetails, String> jobPosCol;

    @FXML
    private TableColumn<AppliedUsersDetails, String> jobTypeCol;
    @FXML
    private TableColumn<AppliedUsersDetails, LocalDate> DateCol;

    @FXML
    private ImageView applied_imageview;

    @FXML
    private ComboBox<Qualification> QualificationComboBox;

    @FXML
    private ComboBox<String> experiencecompanyedit;

    @FXML
    private TextField SalarytxtBox;

    @FXML
    private Button addBtn;

    @FXML
    private TextField addresstxtBox;

    @FXML
    private TextField companyEmail;

    @FXML
    private TextField companyName;


    @FXML
    private ImageView company_imageview;

    @FXML
    private TextField contacttxtBox;

    @FXML
    private ComboBox<String> experiencecomboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField jobDescriptiontxtBox;

    @FXML
    private TextField jobPositiontextbox;

    @FXML
    private ComboBox<String> jobType;

    @FXML
    private Button HiringBtn;

    @FXML
    private TextField appAddressTxtfield;

    @FXML
    private TextField appEmailTxtField;

    @FXML
    private TextField degreefinishedTxtfield;

    @FXML
    private TextField birthdaytxtField;

    @FXML
    private TextField companydeetsTxtfield;

    @FXML
    private TextField companyaddresstxtField;
    
    @FXML
    private TextField companyemailTxtField;

    @FXML
    private TextField salaryTxtfield;

    @FXML
    private TextField applicantdateTxtfield;

    @FXML
    private TextField experienceTxtfield;

    @FXML
    private TextField jobDescriptionTxtField;

    @FXML
    private TableView<CompanyDetails> HiringPositionTable;

    @FXML
    private TableColumn<CompanyDetails, String> positionColumn;

    @FXML
    private TableColumn<CompanyDetails, String> JobDescriptionColumn;

    @FXML
    private TableColumn<CompanyDetails, LocalDate> lastdateColumn;


    @FXML
    private TableView<CompanyDetails> company_table;

    @FXML
    private TableColumn<CompanyDetails, String> companyCOl;


    @FXML
    private TableView<CompanyDetails> hiringposition_table;

    @FXML
    private TableColumn<CompanyDetails, String> jobpositionnCOl;

    @FXML
    private TextField namecompanyedit;

    @FXML
    private TextField emailcompantyedit;

    @FXML
    private TextField addresscompanyedit;

    @FXML
    private TextField contactcompanyedit;

    @FXML
    private TextField postalcompanyedit;

    @FXML
    private TextField salarycompanyedit;

    @FXML
    private TextField jobposcompanyedit;

    @FXML
    private TextField jobDescCompanyEdit;


    @FXML
    private DatePicker datecompanyedit;

    @FXML
    private ImageView companyedit_imageview;

    @FXML
    private ComboBox<String> QualificationCompanyEdit;

    @FXML
    private ComboBox<String> jobCompanyEdit;

    @FXML
    private Pane DashPane;

    @FXML
    private Pane CompaniesPane;

    @FXML
    private Pane NewJobPane;

    @FXML
    private Pane ApplicantsPane;

    @FXML
    private DatePicker lastdateapplied;

    @FXML
    private AnchorPane newjob_mainform;

    @FXML
    private AnchorPane dashboard_mainform;

    @FXML
    private TextField postalTxtbox;

    @FXML
    private Label totalusers_label;

    @FXML
    private Label totalcompanieslabel;
    
    @FXML
    private Label totalappliedlabel;

    @FXML
    private Label totalgraduateslabel;

    @FXML
    private Label totalappliedusers;

    @FXML
    private Label totalmaleapplied;

    @FXML
    private Label totalfemaleApplied;

    @FXML
    private ComboBox<String> degree_combobox;

    @FXML
    private ComboBox<String> City_combobox;

    @FXML
    private Label cityLabel;

    @FXML
    private Label degreeLabel;

    @FXML
    private Label jobpositionlabel;

    
    String imagePath;
    String selectedImageUrl;
    String image;
    private int companyId;
    private int hiringPositionId;



    private void populateTotalUsersCount() {

       
            String query = "SELECT * FROM Registration";
    
            try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
            ) {
                int totalUsers = 0;
                while (resultSet.next()) {
                    totalUsers++;
            }

            totalusers_label.setText(Integer.toString(totalUsers));
         } catch (SQLException e) {
             e.printStackTrace();
        }
    }

        private void populateDegreeComboBoxAndLabel() {
        ObservableList<String> degrees = FXCollections.observableArrayList();
        String degreeQuery = "SELECT degree FROM Qualification";

        try (
            PreparedStatement degreeStatement = connection.prepareStatement(degreeQuery);
            ResultSet degreeResultSet = degreeStatement.executeQuery()
        ) {
            while (degreeResultSet.next()) {
                String degree = degreeResultSet.getString("degree");
                degrees.add(degree);
            }

            degree_combobox.setItems(degrees);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private int getCountForDegree(String degree) {
        try {
            String countQuery = "SELECT COUNT(DISTINCT registration_id) FROM AppliedUsers WHERE qualification_id = (SELECT qualification_id FROM Qualification WHERE degree = ?)";
    
            try (PreparedStatement countStatement = connection.prepareStatement(countQuery)) {
                countStatement.setString(1, degree);
                ResultSet countResultSet = countStatement.executeQuery();
                if (countResultSet.next()) {
                    int count = countResultSet.getInt(1);
                    return count;
                }
            } catch (SQLException ex) {
                System.out.println("Error occurred while counting for degree '" + degree + "': " + ex.getMessage()); // Validation message
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    


    private void populateCityComboBoxAndLabels() {
        ObservableList<String> cities = FXCollections.observableArrayList();
        String cityQuery = "SELECT city_name FROM City";
    
        try (
            PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
            ResultSet cityResultSet = cityStatement.executeQuery()
        ) {
            while (cityResultSet.next()) {
                String city = cityResultSet.getString("city_name");
                cities.add(city);
            }
    
            City_combobox.setItems(cities);
    
            String initialCity = City_combobox.getValue();
            if (initialCity != null) {
                cityLabel.setText(initialCity);
                int count = getCountForCity(initialCity);
                totalappliedusers.setText(Integer.toString(count));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  
    private int getCountForCity(String city) {
        try {
            String countQuery = "SELECT COUNT(DISTINCT AU.registration_id) FROM AppliedUsers AU " +
                                "INNER JOIN Registration R ON AU.registration_id = R.RegistrationID " +
                                "WHERE R.city_id IN (SELECT city_id FROM City WHERE city_name = ?)";
    
            try (PreparedStatement countStatement = connection.prepareStatement(countQuery)) {
                countStatement.setString(1, city);
                ResultSet countResultSet = countStatement.executeQuery();
                if (countResultSet.next()) {
                    int count = countResultSet.getInt(1);
                    return count;
                }
            } catch (SQLException ex) {
                System.out.println("Error occurred while counting for city '" + city + "': " + ex.getMessage()); // Validation message
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if an exception occurs or no count is found
    }
    
    
    
    

    private void populateTotalCompaniesCount() {

        String query = "SELECT * FROM CompanyDetails";
    
        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            int totalCompanies = 0;
            while (resultSet.next()) {
                totalCompanies++;
            }

            totalcompanieslabel.setText(Integer.toString(totalCompanies));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateTotalAppliedUsersCount() {
        String query = "SELECT COUNT(DISTINCT registration_id) AS total FROM AppliedUsers";
    
        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            int totalAppliedUsers = 0;
            if (resultSet.next()) {
                totalAppliedUsers = resultSet.getInt("total");
            }
    
            totalappliedlabel.setText(Integer.toString(totalAppliedUsers));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private void populateJobPositionCount() {
        String query = "SELECT * FROM HiringPosition";
    
        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            int jobPositionCount = 0;
            while (resultSet.next()) {
                jobPositionCount++;
            }
    
            jobpositionlabel.setText(Integer.toString(jobPositionCount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateMaleAndFemaleCount() {
        String query = "SELECT R.gender, COUNT(DISTINCT AU.registration_id) AS count " +
        "FROM AppliedUsers AU " +
        "INNER JOIN Registration R ON AU.registration_id = R.RegistrationID " +
        "GROUP BY R.gender";

        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()
        ) {
            int maleCount = 0;
            int femaleCount = 0;
            
            while (resultSet.next()) {
                String gender = resultSet.getString("gender");
                int count = resultSet.getInt("count");
                
                if (gender.equalsIgnoreCase("male")) {
                    maleCount += count;
                } else if (gender.equalsIgnoreCase("female")) {
                    femaleCount += count;
                }
            }
            
            totalmaleapplied.setText(Integer.toString(maleCount));
            totalfemaleApplied.setText(Integer.toString(femaleCount));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

    private void initializeTable() {

        companyCOl.setCellValueFactory(cellData -> cellData.getValue().companyProperty());
        populateTable();
    } 

    private void experiencecomboBox2() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList("None","Fresh Graduate", "1 Year Experience", 
        "2 Years Experience", "3 Years Experience", "4 Years Experience", "5 Years Experience", "6 Years Experience", "More than 6 Years");
        experiencecompanyedit.setItems(genderOptions);
    }

    


    void populateDegrees() {
        try {
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DatabaseProject", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT degree FROM Qualification");

            List<String> degrees = new ArrayList<>();
            while (rs.next()) {
                String degree = rs.getString("degree");
                degrees.add(degree);
            }

            QualificationCompanyEdit.getItems().addAll(degrees);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    private void setupTableListener() {
        company_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                CompanyDetails selectedCompany = company_table.getSelectionModel().getSelectedItem();
                if (selectedCompany != null) {

                    int companyId = Integer.parseInt(selectedCompany.getId());
                    namecompanyedit.setText(selectedCompany.getCompany());
                    emailcompantyedit.setText(selectedCompany.getEmail());
                    addresscompanyedit.setText(selectedCompany.getCompanyAddress());
                    contactcompanyedit.setText(selectedCompany.getContact());
                    jobCompanyEdit.setValue(selectedCompany.getJobType());
                    postalcompanyedit.setText(selectedCompany.getPostal());
                    experiencecompanyedit.setValue(selectedCompany.getExperience());
                    salarycompanyedit.setText(selectedCompany.getSalary());
                    String imagePath = selectedCompany.getImage();

                    if (imagePath != null && !imagePath.isEmpty()) {
                        Image image = new Image(imagePath);
                        companyedit_imageview.setImage(image);
                    } else {
                        companyedit_imageview.setImage(null);
                    }

                    try {
                        String query = "SELECT jobposition, jobdescription, lastdateapplied FROM HiringPosition WHERE company_id = ?";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setInt(1, companyId);
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            String jobPosition = resultSet.getString("jobposition");
                            String jobDescription = resultSet.getString("jobdescription");
                            LocalDate applicationDeadline = resultSet.getDate("lastdateapplied").toLocalDate();
                            
                            jobposcompanyedit.setText(jobPosition);
                            jobDescCompanyEdit.setText(jobDescription);
                            datecompanyedit.setValue(applicationDeadline);
                        } else {
            
                            jobposcompanyedit.setText("");
                            jobDescCompanyEdit.setText("");
                            datecompanyedit.setValue(LocalDate.now());
                        }
                        resultSet.close();
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    
                    try {

                        String query = "SELECT q.degree FROM Qualification q " +
                                       "INNER JOIN CompanyDetails c ON c.qualification_id = q.qualification_id " +
                                       "WHERE c.qualification_id = ?";
                        PreparedStatement statement = connection.prepareStatement(query);

                        statement.setInt(1, selectedCompany.getQualificationId());
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            String degree = resultSet.getString("degree");
                            QualificationCompanyEdit.setValue(degree);
                        } else {
                            System.out.println("No degree found for company with qualification ID: " + selectedCompany.getQualificationId());
                        }
                        resultSet.close();
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    populateHiringPositionTable(companyId);

                } else {
                    System.out.println("Selected company is null.");
                }
            } else {
                System.out.println("New selection is null.");
            }
        });
    }

    
    private void populateTable() {
        try {
            String query = "SELECT c.company_id, c.qualification_id, c.companyname, c.companyaddress, c.companyemail, c.companycontact, " +
               "c.postalcode, c.salary, c.experiencerequired, c.jobtype, c.image, q.degree " +
               "FROM CompanyDetails c " +
               "INNER JOIN Qualification q ON c.qualification_id = q.qualification_id";


            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {

                CompanyDetails companyDetails = new CompanyDetails();

                String companyId = resultSet.getString("company_id");
                String qualificationId = resultSet.getString("qualification_id");
                String companyName = resultSet.getString("companyname");
                String companyAddress = resultSet.getString("companyaddress");
                String companyEmail = resultSet.getString("companyemail");
                String companyContact = resultSet.getString("companycontact");
                String companypostal = resultSet.getString("postalcode");
                String salary = resultSet.getString("salary");
                String experiencerequuired = resultSet.getString("experiencerequired");
                String jobtype = resultSet.getString("jobtype");
                companyDetails.setImage(resultSet.getString("image"));
                String degree = resultSet.getString("degree");

                companyDetails.setId(companyId);
                companyDetails.setCompany(companyName);
                companyDetails.setCompanyAddress(companyAddress);
                companyDetails.setEmail(companyEmail);
                companyDetails.setContact(companyContact);
                companyDetails.setPostal(companypostal);
                companyDetails.setSalary(salary);
                companyDetails.setExperience(experiencerequuired);
                companyDetails.setJobType(jobtype);
                companyDetails.setDegree(degree); 
                companyDetails.setQualificationID(qualificationId);
                company_table.getItems().add(companyDetails);
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void populateHiringPositionTable(int companyId) {
        try {
            String query = "SELECT jobposition, jobdescription, lastdateapplied FROM HiringPosition WHERE company_id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();
            
            hiringposition_table.getItems().clear();
            jobpositionnCOl.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
    
            while (resultSet.next()) {
                String jobPosition = resultSet.getString("jobposition");
                String jobDescription = resultSet.getString("jobdescription");
                LocalDate lastDateApplied = resultSet.getDate("lastdateapplied").toLocalDate();
    
                System.out.println("Job Position: " + jobPosition);
                System.out.println("Job Description: " + jobDescription);
                System.out.println("Last Date Applied: " + lastDateApplied);
    
                if (jobPosition != null && !jobPosition.isEmpty()) {
                    CompanyDetails hiringPosition = new CompanyDetails();
                    hiringPosition.setJobPosition(jobPosition);
                    hiringPosition.setJobDescription(jobDescription);
                    hiringPosition.setLastdateProperty(lastDateApplied);
                    hiringposition_table.getItems().add(hiringPosition);
                } else {
                    System.out.println("Skipping row with missing job position.");
                }
            }
    
            resultSet.close();
            statement.close();
            hiringposition_table.refresh();
            
            // Add a listener to the selection model of the hiring position table
            hiringposition_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    // Populate the text fields with the selected hiring position's details
                    jobposcompanyedit.setText(newSelection.getJobPosition());
                    jobDescCompanyEdit.setText(newSelection.getjobDescriptionProperty());
                    datecompanyedit.setValue(newSelection.getLastdateApply());
                } else {
                    // Clear the text fields if no hiring position is selected
                    jobposcompanyedit.clear();
                    jobDescCompanyEdit.clear();
                    datecompanyedit.setValue(null);
                }
            });
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    void ClearItAgain(ActionEvent event) {
        namecompanyedit.clear();
        addresscompanyedit.clear();
        emailcompantyedit.clear();
        contactcompanyedit.clear();
        postalcompanyedit.clear();
        salarycompanyedit.clear();
        jobposcompanyedit.clear();
        jobDescCompanyEdit.clear();
        experiencecompanyedit.setValue(null);
        companyedit_imageview.setImage(null);
        jobCompanyEdit.setValue(null);
        datecompanyedit.setValue(null);
    }
    
  
    @FXML
    void UpdateItAgain(ActionEvent event) {

        CompanyDetails selectedCompany = company_table.getSelectionModel().getSelectedItem();

        if (selectedCompany == null || selectedCompany.getId() == null || selectedCompany.getId().isEmpty()) {
            showAlert("Error", "No company selected or company ID is missing.", Alert.AlertType.ERROR);
            return;
        }

        if (namecompanyedit.getText().isEmpty() ||
        addresscompanyedit.getText().isEmpty() ||
        emailcompantyedit.getText().isEmpty() ||
        contactcompanyedit.getText().isEmpty() ||
        postalcompanyedit.getText().isEmpty() ||
        salarycompanyedit.getText().isEmpty()) {
        
        showAlert("Error", "All fields must be filled.", Alert.AlertType.ERROR);
        return;
    }
    
        try {
            String companyName = namecompanyedit.getText();
            String companyAddress = addresscompanyedit.getText();
            String companyEmail = emailcompantyedit.getText();
            String companycontact = contactcompanyedit.getText();
            String postal = postalcompanyedit.getText();
            String salary = salarycompanyedit.getText();
            String companyId = selectedCompany.getId(); 
            String experience = experiencecompanyedit.getValue();
            int qualificationId = selectedCompany.getQualificationId();
    
            System.out.println("Retrieved Company ID: " + companyId);

            if (!isValidEmail(emailcompantyedit.getText())) {
                showAlert("Error", "Please enter a valid email address.", Alert.AlertType.ERROR);
                emailcompantyedit.setStyle("-fx-border-color: transparent transparent red transparent; -fx-border-radius: 8px; -fx-border-width: 0 0 2 0;");
                return;
            } else {
                emailcompantyedit.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-radius: 8px;");
            }

            if (!isValidContact(contactcompanyedit.getText())) {
                showAlert("Error", "Please enter a valid contact number.", Alert.AlertType.ERROR);
                contactcompanyedit.setStyle("-fx-border-color: transparent transparent red transparent; -fx-border-radius: 8px; -fx-border-width: 0 0 2 0;");
                return;
            } else {
                contactcompanyedit.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-radius: 8px;");
            }

            if (!isValidPostalCode(postalcompanyedit.getText())) {
                showAlert("Error", "Please enter a valid postal code.", Alert.AlertType.ERROR);
                postalcompanyedit.setStyle("-fx-border-color: transparent transparent red transparent; -fx-border-radius: 8px; -fx-border-width: 0 0 2 0;");
                return;
            } else {
                postalcompanyedit.setStyle("-fx-border-color: transparent transparent green transparent; -fx-border-radius: 8px;");
            }
        
            // Validate salary
            
        
            String updateQuery = "UPDATE CompanyDetails SET companyname = ?, companyaddress = ?, companyemail = ?, companycontact = ?, postalcode = ?, salary = ?, experiencerequired = ? WHERE company_id = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setString(1, companyName);
                statement.setString(2, companyAddress);
                statement.setString(3, companyEmail);
                statement.setString(4, companycontact);
                statement.setString(5, postal);
                statement.setString(6, salary);
                statement.setString(7, experience);
                statement.setString(8, companyId);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {

                    selectedCompany.setCompany(companyName);
                    selectedCompany.setCompanyAddress(companyAddress);
                    selectedCompany.setEmail(companyEmail);
                    selectedCompany.setContact(companycontact);
                    selectedCompany.setPostal(postal);
                    selectedCompany.setSalary(salary);
                    selectedCompany.setExperience(experience);
                    company_table.refresh();
                    showAlert("Success", "Company details updated successfully.", Alert.AlertType.INFORMATION);

                   
                } else {
                    showAlert("Error", "Failed to update company details.", Alert.AlertType.ERROR);
                }
            }

            String updateQualificationsQuery = "UPDATE Qualification SET degree = ? WHERE qualification_id = ?";

            try (PreparedStatement qualificationStatement = connection.prepareStatement(updateQualificationsQuery)) {
                // Retrieve the new degree value from the QualificationCompanyEdit ComboBox
                String newDegree = QualificationCompanyEdit.getValue(); 
            
                // Set the parameters for the prepared statement
                qualificationStatement.setString(1, newDegree); 
                qualificationStatement.setInt(2, qualificationId); // Use the retrieved qualification ID
            
                // Execute the update query
                int rowsAffected = qualificationStatement.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert("Success", "Qualification updated successfully.", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to update qualification.", Alert.AlertType.ERROR);
                }
            } catch (SQLException e) {
                showAlert("Error", "Failed to update qualification: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }

             // Update the selected hiring position
            CompanyDetails selectedPosition = hiringposition_table.getSelectionModel().getSelectedItem();

            if (selectedPosition != null) {
            try {
                String newJobDescription = jobDescCompanyEdit.getText();
                LocalDate newLastDateApplied = datecompanyedit.getValue();
                String newJobPosition = jobposcompanyedit.getText();

                String updateHiringPositionQuery = "UPDATE HiringPosition SET jobdescription = ?, lastdateapplied = ?, jobposition = ? WHERE company_id = ? AND jobposition = ?";
                try (PreparedStatement statement = connection.prepareStatement(updateHiringPositionQuery)) {
                    statement.setString(1, newJobDescription);
                    statement.setDate(2, java.sql.Date.valueOf(newLastDateApplied));
                    statement.setString(3, newJobPosition);
                    statement.setString(4, companyId);
                    statement.setString(5, selectedPosition.getJobPosition()); // Use the job position of the selected item

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        selectedPosition.setJobDescription(newJobDescription);
                        selectedPosition.setLastdateProperty(newLastDateApplied);
                        selectedPosition.setJobPosition(newJobPosition);

                        hiringposition_table.refresh();

                        showAlert("Success", "Hiring position updated successfully.", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Error", "Failed to update hiring position.", Alert.AlertType.ERROR);
                    }
                }
            } catch (SQLException e) {
                showAlert("Error", "Failed to update hiring position: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }


            } catch (SQLException e) {
                showAlert("Error", "Failed to update company: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
            hiringposition_table.refresh();

        }

        private boolean isValidContact(String contact) {
            // Contact regex pattern allowing only digits, symbols, and spaces
            String contactRegex = "^[0-9\\s\\-+()]*$";
            return contact.matches(contactRegex);
        }

        // Method to validate postal code format
        private boolean isValidPostalCode(String postalCode) {
            String postalRegex = "^\\d{0,6}$";
            return postalCode.matches(postalRegex);
        }

        

    @FXML
    void DeleteItAgain2(ActionEvent event) {
        // Get the selected item from the table
        CompanyDetails selectedCompany = company_table.getSelectionModel().getSelectedItem();

        if (selectedCompany == null) {
            showAlert("Error", "No company selected.", Alert.AlertType.ERROR);
            return;
        }

    // Confirm deletion with the user
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setHeaderText("Delete Company");
    alert.setContentText("Are you sure you want to delete the company with the following details?\n\n" +
                         "Company ID: " + selectedCompany.getId() + "\n" +
                         "Company Name: " + selectedCompany.getCompany() + "\n" +
                         "Company Address: " + selectedCompany.getCompanyAddress() + "\n" +
                         "Company Email: " + selectedCompany.getEmail() + "\n" +
                         "Company Contact: " + selectedCompany.getContact() + "\n" +
                         "Postal Code: " + selectedCompany.getPostal() + "\n" +
                         "Salary: " + selectedCompany.getSalary() + "\n" +
                         "Experience Required: " + selectedCompany.getExperience() + "\n" +
                         "Job Type: " + selectedCompany.getJobType() + "\n");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            // Execute the delete query
            String deleteQuery = "DELETE FROM CompanyDetails WHERE company_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, selectedCompany.getId());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Remove the item from the table view
                company_table.getItems().remove(selectedCompany);
                showAlert("Success", "Company deleted successfully.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to delete company.", Alert.AlertType.ERROR);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert("Error", "Failed to delete company: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}



@FXML
void AddAgain(ActionEvent event) {
    String jobPosition = jobposcompanyedit.getText();
    String jobDescription = jobDescCompanyEdit.getText();
    LocalDate lastDateApplied = datecompanyedit.getValue();

    try {
        CompanyDetails selectedCompany = company_table.getSelectionModel().getSelectedItem();
        
        if (selectedCompany == null) {
            showAlert("Error", "No company selected.", Alert.AlertType.ERROR);
            return;
        }
        
        String companyId = selectedCompany.getId(); 
        
        if (jobPosition.isEmpty() || jobDescription.isEmpty()) {
            showAlert("Error", "Please enter both job position and job description.", Alert.AlertType.ERROR);
            return;
        }
        
        String insertQuery = "INSERT INTO HiringPosition (company_id, jobposition, jobdescription, lastdateapplied) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        
        statement.setString(1, companyId);
        statement.setString(2, jobPosition);
        statement.setString(3, jobDescription);
        statement.setDate(4, Date.valueOf(lastDateApplied));
        
        int rowsAffected = statement.executeUpdate();
        
        if (rowsAffected > 0) {
            // Create a new CompanyDetails object for the added job position
            CompanyDetails newHiringPosition = new CompanyDetails();
            newHiringPosition.setJobPosition(jobPosition);
            newHiringPosition.setJobDescription(jobDescription);
            newHiringPosition.setLastdateProperty(lastDateApplied);
            
            hiringposition_table.getItems().add(newHiringPosition);
            
            // Show success message
            showAlert("Success", "New job position added successfully.", Alert.AlertType.INFORMATION);
        } else {
            // Show error message if the insert failed
            showAlert("Error", "Failed to add new job position.", Alert.AlertType.ERROR);
        }
        
        // Close the statement
        statement.close();
    } catch (SQLException e) {
        // Show error message for SQL exception
        showAlert("Error", "Failed to add new job position: " + e.getMessage(), Alert.AlertType.ERROR);
        e.printStackTrace();
    }
}



    @FXML
    void DeleteItAgain(ActionEvent event) {

    CompanyDetails selectedPosition = hiringposition_table.getSelectionModel().getSelectedItem();

    if (selectedPosition == null) {
        showAlert("Error", "No job position selected.", Alert.AlertType.ERROR);
        return;
    }

    // Retrieve the job position, job description, and last date applied
    String jobPosition = selectedPosition.getJobPosition();
    String jobDescription = selectedPosition.getjobDescriptionProperty();
    LocalDate lastDateApplied = selectedPosition.getLastdateApply();

    // Confirm deletion with the user
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setHeaderText("Delete Job Position");
    alert.setContentText("Are you sure you want to delete the job position with the following details?\n\n" +
                         "Job Position: " + jobPosition + "\n" +
                         "Job Description: " + jobDescription + "\n" +
                         "Last Date Applied: " + lastDateApplied + "\n");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            // Execute the delete query
            String deleteQuery = "DELETE FROM HiringPosition WHERE jobposition = ? AND jobdescription = ? AND lastdateapplied = ?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setString(1, jobPosition);
            statement.setString(2, jobDescription);
            statement.setDate(3, Date.valueOf(lastDateApplied));
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Remove the item from the table view
                hiringposition_table.getItems().remove(selectedPosition);
                showAlert("Success", "Job position deleted successfully.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to delete job position.", Alert.AlertType.ERROR);
            }

            statement.close();
        } catch (SQLException e) {
            showAlert("Error", "Failed to delete job position: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}




    @FXML
    void UpdatePhoto(ActionEvent event) {
    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
    
        String initialDirectoryPath = "/Users/goodboykit/FinalProject_Database/src/DATABASE";
        File initialDirectory = new File(initialDirectoryPath);
        fileChooser.setInitialDirectory(initialDirectory);
    
        // Show open file dialog
        File file = fileChooser.showOpenDialog(company_imageform.getScene().getWindow());
    
        if (file != null) {
            try {
                // Set the selected image to the ImageView
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage != null) {
    
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    companyedit_imageview.setImage(image);
                    selectedImageUrl = file.toURI().toString();
                    System.out.println("Image Path: " + selectedImageUrl);
    
                    savePhotoUrlToDatabase(selectedImageUrl);
    
                } else {
                    System.err.println("Failed to read image file: " + file.getName());
                }
            } catch (IOException e) {
                System.err.println("Error loading image file: " + file.getName());
                e.printStackTrace();
            }
        }
    
    }


    private void savePhotoUrlToDatabase(String selectedImageUrl) {

        CompanyDetails selectedCompany = company_table.getSelectionModel().getSelectedItem();
        if (selectedCompany == null) {
            showAlert("Error", "No company selected.", Alert.AlertType.ERROR);
            return;
        }
    
        try {
            Image image = companyedit_imageview.getImage();
            String imageUrl = selectedImageUrl;
    
            if (image == null) {
                showAlert("Error", "No image selected.", Alert.AlertType.ERROR);
                return;
            }
    
            try {
                String updateQuery = "UPDATE CompanyDetails SET image = ? WHERE company_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                    statement.setString(1, imageUrl);
                    statement.setString(2, selectedCompany.getId());
                    int rowsAffected = statement.executeUpdate();
    
                    if (rowsAffected > 0) {

                        selectedCompany.setImage(imageUrl);
                        company_table.refresh();
                        showAlert("Success", "Photo updated successfully.", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Error", "Failed to update photo.", Alert.AlertType.ERROR);
                    }
                }
            } catch (SQLException e) {
                showAlert("Error", "Failed to update photo in the database: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to update photo: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
        
    
    


    @FXML
    void ViewDocument(ActionEvent event) {

        AppliedUsersDetails selectedUser = AppliedTable.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            int userId = selectedUser.getId();
            try {

                String sql = "SELECT document FROM AppliedUsers WHERE applied_id = ?";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setInt(1, userId);
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    java.sql.Blob documentBlob = rs.getBlob("document");
                    
                    if (documentBlob != null) {

                        File tempFilePdf = File.createTempFile("document", ".pdf");
                        File tempFileDocx = File.createTempFile("document", ".docx");

                        try (FileOutputStream fosPdf = new FileOutputStream(tempFilePdf);
                         FileOutputStream fosDocx = new FileOutputStream(tempFileDocx)) {
                        fosPdf.write(documentBlob.getBytes(1, (int) documentBlob.length()));
                        fosDocx.write(documentBlob.getBytes(1, (int) documentBlob.length()));
                    }
                        
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.OPEN)) {
                        try {
                            desktop.open(tempFilePdf);
                            showAlert(AlertType.INFORMATION, "Success", "Document opened successfully.");

                        } catch (IOException e) {
                            desktop.open(tempFileDocx);
                            showAlert(AlertType.INFORMATION, "Success", "Document opened successfully.");

                        }
                    } else {
                        System.out.println("Opening documents is not supported on this platform.");
                    }
                    } else {
                        System.out.println("Document not found for AppliedUser with ID: " + userId);
                    }
                } else {
                    System.out.println("No document found for AppliedUser with ID: " + userId);
                }
                
                rs.close();
                pst.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No AppliedUser selected.");
        }
        }

        private void showAlert(AlertType alertType, String title, String message) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        
    
    public void showTable () {

        try {

            String sql = "SELECT au.applied_id, r.firstname, r.lastname, r.address, r.email, r.birthday, r.mobile, cd.companyname, cd.companyaddress, cd.companyemail, cd.salary, cd.jobtype, cd.experiencerequired, hp.jobposition, hp.jobdescription, hp.lastdateapplied, r.image, q.degree, q.qualification_id, au.datewanted, au.document " +
             "FROM AppliedUsers au " +
             "JOIN Registration r ON au.registration_id = r.RegistrationID " +
             "JOIN HiringPosition hp ON au.hiringposition_id = hp.hiringposition_id " +
             "JOIN CompanyDetails cd ON au.company_id = cd.company_id " +
             "JOIN Qualification q ON au.qualification_id = q.qualification_id";

            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ObservableList<AppliedUsersDetails> data = FXCollections.observableArrayList();

            while (rs.next()) {

                int userId = rs.getInt("applied_id"); // Retrieve the ID of the AppliedUser
                String applicantName = rs.getString("firstname") + " " + rs.getString("lastname");
                String companyName = rs.getString("companyname");
                String jobPosition = rs.getString("jobposition");
                String jobType = rs.getString("jobtype");
                String lastDateApplied = rs.getString("lastdateapplied");
                String imagePath = rs.getString("image"); 
                String address = rs.getString("address");
                String email = rs.getString("email");
                String birthday = rs.getString("birthday");
                String degree = rs.getString("degree");
                String mobile = rs.getString("mobile");

                String companyaddress = rs.getString("companyaddress");
                String companyemail = rs.getString("companyemail");
                String salary = rs.getString("salary");

                String dateapplied = rs.getString("datewanted");
                String experience = rs.getString("experiencerequired");
                String jobdescription = rs.getString("jobdescription");


                AppliedUsersDetails appliedUsers = new AppliedUsersDetails();
                appliedUsers.setId(userId); // Set the ID of the AppliedUser
                appliedUsers.setApplicantName(applicantName);
                appliedUsers.setCompanyName(companyName);
                appliedUsers.setJobPosition(jobPosition);
                appliedUsers.setJobType(jobType);
                appliedUsers.setLastDateApplied(lastDateApplied);
                appliedUsers.setImagePath(imagePath); 

                appliedUsers.setAddress(address);
                appliedUsers.setEmail(email);
                appliedUsers.setBirthday(birthday);
                appliedUsers.setDegreeFinished(degree);

                appliedUsers.setMobile(mobile);
                appliedUsers.setCompanyAddress(companyaddress);
                appliedUsers.setCompanyEmail(companyemail);
                appliedUsers.setSalary(salary);

                appliedUsers.setDateApplied(dateapplied);
                appliedUsers.setExperience(experience);
                appliedUsers.setJobDescription(jobdescription);

                data.add(appliedUsers);
            }

            AppliedTable.setItems(data);
            applicantNameCol.setCellValueFactory(cellData -> cellData.getValue().applicantNameProperty());
            companyNameCol.setCellValueFactory(cellData -> cellData.getValue().companyNameProperty());
            jobPosCol.setCellValueFactory(cellData -> cellData.getValue().jobPositionProperty());
            jobTypeCol.setCellValueFactory(cellData -> cellData.getValue().jobTypeProperty());
            DateCol.setCellValueFactory(cellData -> cellData.getValue().lastDateAppliedProperty());

            AppliedTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

                if (newSelection != null) {
                    String imagePath = newSelection.getImagePath();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        Image image = new Image(imagePath, 1000, 1000, false, true);
                        applied_imageview.setImage(image);
                    }
                    setTextFieldValues(newSelection);
                } else {
                    clearTextFields();
                }
            });

            rs.close();
            pst.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setTextFieldValues(AppliedUsersDetails user) {

        appAddressTxtfield.setText(user.getAddress());
        appEmailTxtField.setText(user.getEmail());
        birthdaytxtField.setText(user.getBirthday());
        degreefinishedTxtfield.setText(user.getDegreeFinished());
        
        companydeetsTxtfield.setText(user.getMobile());
        companyaddresstxtField.setText(user.getCompanyAddress());
        companyemailTxtField.setText(user.getCompanyEmail());
        salaryTxtfield.setText(user.getSalary());
        applicantdateTxtfield.setText(user.getDateApplied());
        experienceTxtfield.setText(user.getExperience());
        jobDescriptionTxtField.setText(user.getJobDescription());
    }
    
    private void clearTextFields() {

        appAddressTxtfield.clear();
        appEmailTxtField.clear();
        birthdaytxtField.clear();
        degreefinishedTxtfield.clear();
        
        companydeetsTxtfield.clear();
        companyaddresstxtField.clear();
        companyemailTxtField.clear();
        salaryTxtfield.clear();
        applicantdateTxtfield.clear();
        experienceTxtfield.clear();
        jobDescriptionTxtField.clear();
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void AddCompany(ActionEvent event) {

        String companyname = companyName.getText();
        String companyaddress = addresstxtBox.getText();
        String companyemail = companyEmail.getText();
        String companycontact = contacttxtBox.getText();
        String postalcode = postalTxtbox.getText();
        String salary = SalarytxtBox.getText();
        String jobPosition = jobPositiontextbox.getText();
        String jobDescription = jobDescriptiontxtBox.getText();
        Qualification selectedQualification = QualificationComboBox.getValue();
        String qualification_id = selectedQualification.getQualificationId();
        String experiencerequired = (String) experiencecomboBox.getSelectionModel().getSelectedItem();
        String jobtype = (String) jobType.getSelectionModel().getSelectedItem();
        LocalDate lastdateapply = lastdateapplied.getValue();

        image = company_imageview.getImage() != null ? company_imageview.getImage().getUrl() : null;
        image = imagePath; 

        if (companyname.isEmpty() || companyaddress.isEmpty() || companyemail.isEmpty() ||
        companycontact.isEmpty() || postalcode.isEmpty() || salary.isEmpty() ||
        jobPosition.isEmpty() || jobDescription.isEmpty() || lastdateapply == null || selectedQualification == null  || experiencerequired == null || image == null) {
        showAlert("Validation Error", "Please fill in all the required fields.", Alert.AlertType.ERROR);
        return;
        }
    
        if (!isValidEmail(companyemail)) {
            showAlert("Validation Error", "Please enter a valid email address.", Alert.AlertType.ERROR);
            return;
        }

        if (!isPhoneNumber(companycontact)) {
            showAlert("Validation Error", "Company contact should contain numbers only.", Alert.AlertType.ERROR);
            return;
        }


        if (postalcode.length() > 6) {
            showAlert("Validation Error", "Postal code should contain less than or equal to 6 numbers.", Alert.AlertType.ERROR);
            return;
        }

       try {

            String sql = "INSERT INTO CompanyDetails (company_id, qualification_id, companyname, companyaddress, companyemail, companycontact, postalcode, salary, experiencerequired, jobtype, image) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, null);
            pst.setString(2, qualification_id);
            pst.setString(3, companyname);
            pst.setString(4, companyaddress);
            pst.setString(5, companyemail);
            pst.setString(6, companycontact);
            pst.setString(7, postalcode);
            pst.setString(8, salary);
            pst.setObject(9, experiencerequired);
            pst.setObject(10, jobtype);
            pst.setString(11, image);

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating CompanyDetails failed, no rows affected.");
            }

            ResultSet generatedKeys = pst.getGeneratedKeys();
             companyId = -1;
            if (generatedKeys.next()) {
                companyId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to insert CompanyDetails, no ID obtained.");
            }

            String hiringPositionSql = "INSERT INTO HiringPosition (company_id, jobposition, jobdescription, lastdateapplied) VALUES (?, ?, ?, ?)";
            PreparedStatement hiringPositionPst = connection.prepareStatement(hiringPositionSql, Statement.RETURN_GENERATED_KEYS);

            hiringPositionPst.setInt(1, companyId); 
            hiringPositionPst.setString(2, jobPosition);
            hiringPositionPst.setString(3, jobDescription);
            hiringPositionPst.setObject(4, lastdateapply);

            int hiringPositionAffectedRows = hiringPositionPst.executeUpdate();
            if (hiringPositionAffectedRows == 0) {
            throw new SQLException("Creating HiringPosition failed, no rows affected.");

            }

            ResultSet hiringPositionKeys = hiringPositionPst.getGeneratedKeys();
             hiringPositionId = -1;
            if (hiringPositionKeys.next()) {
                hiringPositionId = hiringPositionKeys.getInt(1);
            } else {
                throw new SQLException("Failed to insert HiringPosition, no ID obtained.");
            }

            showAlert("Success", "Data saved successfully.", Alert.AlertType.INFORMATION);
            refreshHiringPositionsTableView();


        } catch (SQLException e) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);

            showAlert("Error", "Failed to save data to the database.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private boolean isPhoneNumber(String str) {
        return str.matches("^\\+?[0-9()\\-\\.\\s]*$");
    }

    @SuppressWarnings("unused")
    private boolean isSalary(String str) {
        // Allow positive or negative numbers, optionally with decimal points and digits after decimal
        return str.matches("^-?\\d+(\\.\\d+)?$");
    }
    

    @FXML
    void Delete(ActionEvent event) {
    CompanyDetails selectedHiringPosition = HiringPositionTable.getSelectionModel().getSelectedItem();

    if (selectedHiringPosition == null) {
        showAlert("Error", "Please select a hiring position to delete.", Alert.AlertType.ERROR);
        return;
    }

    String jobPosition = selectedHiringPosition.getJobPosition();
    String jobDescription = selectedHiringPosition.getjobDescriptionProperty();
    LocalDate lastDateApplied = selectedHiringPosition.getLastdateApply();

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setHeaderText("Delete Hiring Position");
    alert.setContentText("Are you sure you want to delete the job position with the following details?\n\n" +
    "Job Position: " + jobPosition + "\n" +
    "Job Description: " + jobDescription + "\n" +
    "Last Date Applied: " + lastDateApplied + "\n");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {

        try {
            String deleteSql = "DELETE FROM HiringPosition WHERE jobposition = ? AND jobdescription = ? AND lastdateapplied = ?";            
            PreparedStatement deletePst = connection.prepareStatement(deleteSql);

            deletePst.setString(1, jobPosition);
            deletePst.setString(2, jobDescription);
            deletePst.setDate(3, Date.valueOf(lastDateApplied));

            int affectedRows = deletePst.executeUpdate();

            if (affectedRows > 0) {

                HiringPositionTable.getItems().remove(selectedHiringPosition);
                showAlert("Success", "Hiring position deleted successfully.", Alert.AlertType.INFORMATION);

                company_table.refresh();
                HiringPositionTable.refresh();
                refreshHiringPositionsTableView();
            } else {
                showAlert("Error", "Failed to delete hiring position.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            Logger.getLogger(AdminInterface.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Error", "Failed to delete hiring position from the database.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
        }
    }




    @FXML
    void UpdateIt(ActionEvent event) {

    CompanyDetails selectedHiringPosition = HiringPositionTable.getSelectionModel().getSelectedItem();

    if (selectedHiringPosition == null) {
        showAlert("Error", "Please select a hiring position to update.", Alert.AlertType.ERROR);
        return;
    }

    String newJobPosition = jobPositiontextbox.getText(); 
    String newJobDescription = jobDescriptiontxtBox.getText(); 
    LocalDate newDateApplied = lastdateapplied.getValue(); 

    try {
        String updateSql = "UPDATE HiringPosition SET jobposition = ?, jobdescription = ?, lastdateapplied = ? WHERE company_id = ? AND hiringposition_id = ?";
        PreparedStatement updatePst = connection.prepareStatement(updateSql);

        updatePst.setString(1, newJobPosition);
        updatePst.setString(2, newJobDescription);
        updatePst.setObject(3, java.sql.Date.valueOf(newDateApplied)); 
        updatePst.setInt(4, companyId); 
        updatePst.setInt(5, hiringPositionId); 

        int affectedRows = updatePst.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Updating hiring position failed, no rows affected.");
        }

        showAlert("Success", "Hiring position updated successfully.", Alert.AlertType.INFORMATION);
        refreshHiringPositionsTableView(); // Refresh the TableView after updating

     } catch (SQLException e) {
        Logger.getLogger(AdminInterface.class.getName()).log(Level.SEVERE, null, e);
        showAlert("Error", "Failed to update hiring position in the database.", Alert.AlertType.ERROR);
        e.printStackTrace();
        }
    }

    @FXML
    void Clear(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Clear Fields for Hiring Fields?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        jobPositiontextbox.setText(""); 
        jobDescriptiontxtBox.setText(""); 
        lastdateapplied.setValue(null);
    }
    }

    public void Clearrr () {
        jobPositiontextbox.setText(""); 
        jobDescriptiontxtBox.setText(""); 
        lastdateapplied.setValue(null);
    }

    
    @FXML
    void ImportLogo(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // Set initial directory to user's home directory
        String initialDirectoryPath = "/Users/goodboykit/FinalProject_Database/src/DATABASE";
        File initialDirectory = new File(initialDirectoryPath);
        fileChooser.setInitialDirectory(initialDirectory);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(company_imageform.getScene().getWindow());

        if (file != null) {
            try {
                // Set the selected image to the ImageView
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage != null) {

                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    company_imageview.setImage(image);
                    imagePath = file.toURI().toString(); 
                    System.out.println("Image Path: " + imagePath);

                } else {
                    System.err.println("Failed to read image file: " + file.getName());
                }
            } catch (IOException e) {
                System.err.println("Error loading image file: " + file.getName());
                e.printStackTrace();
            }
        }

    }

    @FXML
    void Clear2 (ActionEvent event) {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Clear Fields and Table");
    alert.setContentText("Are you sure you want to clear all fields and the table?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {

        SalarytxtBox.setText(""); 
        jobType.setValue(null);
        QualificationComboBox.setValue(null);
        experiencecomboBox.setValue(null);
        companyName.setText("");

        companyEmail.setText("");
        contacttxtBox.setText("");
        addresstxtBox.setText("");
        postalTxtbox.setText("");
        company_imageview.setImage(null); 

        ObservableList<CompanyDetails> emptyList = FXCollections.observableArrayList();
        HiringPositionTable.setItems(emptyList);
    }


    }

    
    public void Clearfield () {
        SalarytxtBox.setText(""); 
        jobType.setValue(null);
        QualificationComboBox.setValue(null);
        experiencecomboBox.setValue(null);
        companyName.setText("");

        companyEmail.setText("");
        contacttxtBox.setText("");
        addresstxtBox.setText("");
        postalTxtbox.setText("");
        company_imageview.setImage(null); 
    }


    @FXML
    void AddHiringPosition(ActionEvent event) {
        String jobPosition = jobPositiontextbox.getText();
        String jobDescription = jobDescriptiontxtBox.getText();
    
        LocalDate lastdateApplied = lastdateapplied.getValue();
    
        try {
            String hiringPositionSQL = "INSERT INTO HiringPosition (company_id, jobposition, jobdescription, lastdateapplied) VALUES (?, ?, ?, ?)";
            
            PreparedStatement hiringPositionPst = connection.prepareStatement(hiringPositionSQL);
            hiringPositionPst.setInt(1, companyId); 
            hiringPositionPst.setString(2, jobPosition);
            hiringPositionPst.setString(3, jobDescription);
            hiringPositionPst.setObject(4, lastdateApplied); 
    
            hiringPositionPst.executeUpdate();
    
            showAlert("Success", "Hiring position added successfully.", Alert.AlertType.INFORMATION);
            refreshHiringPositionsTableView();
    
        } catch (SQLException e) {
            Logger.getLogger(AdminInterface.class.getName()).log(Level.SEVERE, null, e);
            showAlert("Error", "Failed to add hiring position to the database.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    private void refreshHiringPositionsTableView() {

        ObservableList<CompanyDetails> hiringdata = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM HiringPosition WHERE company_id = ?";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, companyId);
            ResultSet rs = pst.executeQuery();
    
            while (rs.next()) {
                String jobposition = rs.getString("jobposition");
                String jobdescription = rs.getString("jobdescription");
                LocalDate lastdateapplied = rs.getDate("lastdateapplied").toLocalDate(); 
    
                CompanyDetails hiringPosition = new CompanyDetails();
                hiringPosition.setJobPosition(jobposition);
                hiringPosition.setJobDescription(jobdescription);
                hiringPosition.setLastdateProperty(lastdateapplied);
                hiringdata.add(hiringPosition);
            }

            positionColumn.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
            JobDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("jobDescription"));
            lastdateColumn.setCellValueFactory(cellData -> cellData.getValue().lastdateProperty());
            // Set the table items to the CompanyDetails list
            HiringPositionTable.setItems(hiringdata);
            rs.close();
            pst.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------

    private Pane currentPane = null;

    @FXML
    void dashboardVisible(MouseEvent event) {

        if (currentPane != DashPane) {
            if (currentPane != null) {
                currentPane.setStyle(""); 
            }

            DashPane.setStyle("-fx-background-color: linear-gradient(to bottom , #75A8E9, #75A8E9);");
            currentPane = DashPane;
        } else {
            DashPane.setStyle(""); 
            currentPane = null; 
        }
        
        dashboard_mainform.setVisible(true);
        newjob_mainform.setVisible(false);
        applicants_mainform.setVisible(false);
        companies_mainform.setVisible(false);

    }


    @FXML
    void companiesVisible(MouseEvent event) {

        if (currentPane != CompaniesPane) {
            if (currentPane != null) {
                currentPane.setStyle(""); 
            }

            CompaniesPane.setStyle("-fx-background-color: linear-gradient(to bottom , #75A8E9, #75A8E9);");
            currentPane = CompaniesPane;
        } else {
            CompaniesPane.setStyle(""); 
            currentPane = null; 
        }
        companies_mainform.setVisible(true);
        dashboard_mainform.setVisible(false);
        newjob_mainform.setVisible(false);
        applicants_mainform.setVisible(false);

    }


    @FXML
    void gotoHomepage(MouseEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void newJob(MouseEvent event) {

        if (currentPane != NewJobPane) {
            if (currentPane != null) {
                currentPane.setStyle(""); 
            }
            NewJobPane.setStyle("-fx-background-color: linear-gradient(to top right , #75A8E9, #75A8E9);");
            currentPane = NewJobPane;
        } else {
            NewJobPane.setStyle(""); 
            currentPane = null; 
        }
        
        dashboard_mainform.setVisible(false);
        applicants_mainform.setVisible(false);
        newjob_mainform.setVisible(true);
        companies_mainform.setVisible(false);

    }

    @FXML
    void homevisible(MouseEvent event) {

    }


    @FXML
    void ApplicantsClick(MouseEvent event) {

        if (currentPane != ApplicantsPane) {
            if (currentPane != null) {
                currentPane.setStyle(""); 
            }
            ApplicantsPane.setStyle("-fx-background-color: linear-gradient(to top right , #75A8E9, #75A8E9);");
            currentPane = ApplicantsPane;
        } else {
            ApplicantsPane.setStyle(""); 
            currentPane = null; 
        }

        applicants_mainform.setVisible(true);
        dashboard_mainform.setVisible(false);
        newjob_mainform.setVisible(false);
        companies_mainform.setVisible(false);
    }

   

    //-----------------------------------------------------------------------------------------------------------------

      private void experiencecomboBox() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList("None","Fresh Graduate", "1 Year Experience", 
        "2 Years Experience", "3 Years Experience", "4 Years Experience", "5 Years Experience", "6 Years Experience", "More than 6 Years");
        experiencecomboBox.setItems(genderOptions);
    }

    private void JobType() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList("Full Time", "Part Time", "Work From Home", 
        "Contract Job", "Temporary Job", "Hybrid Job", "Freelance Job");
        jobType.setItems(genderOptions);
        jobCompanyEdit.setItems(genderOptions);
    }


    //QUALIFICIATIONS -----------------------
    private void populateQualificationComboBox() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DatabaseProject", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Qualification");
    
            List<Qualification> qualifications = new ArrayList<>();
            while (rs.next()) {
                String qualificationId = rs.getString("qualification_id");
                String degree = rs.getString("degree");
                qualifications.add(new Qualification(qualificationId, degree));
            }
            
            QualificationComboBox.getItems().addAll(qualifications);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    Connection connection;
    PreparedStatement pst; 

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = "jdbc:mysql://localhost:3306/DatabaseProject";
            String username = "root";
            String password = "";
    
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
    
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to connect to the database.");
            e.printStackTrace();
        }
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Connect();
        experiencecomboBox();
        JobType();
        populateQualificationComboBox();
        showTable();
        initializeTable();
        setupTableListener();
        populateDegrees();
        experiencecomboBox2();
        populateTotalUsersCount();
        populateTotalCompaniesCount();
        populateTotalAppliedUsersCount();
        populateMaleAndFemaleCount();
        populateJobPositionCount();
        populateDegreeComboBoxAndLabel();
        populateCityComboBoxAndLabels();

        jobpositionnCOl.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));


        degree_combobox.setOnAction(e -> {
            String selectedDegree = degree_combobox.getValue();
            if (selectedDegree != null) {
                degreeLabel.setText(selectedDegree);
                int count = getCountForDegree(selectedDegree);
                totalgraduateslabel.setText(Integer.toString(count));
            }
        });

        City_combobox.setOnAction(e -> {

            String selectedCity = City_combobox.getValue();
            if (selectedCity != null) {
                cityLabel.setText(selectedCity);
                int count = getCountForCity(selectedCity);
                totalappliedusers.setText(Integer.toString(count));
            }
        });

       
        
        companyName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(".*[\",><@#$%)+-].*") && !newValue.matches("\\s+")) {
                companyName.setStyle("-fx-border-color: red; -fx-border-radius: 8px; -fx-border-width: 2px;");
            } else {
                companyName.setStyle("-fx-border-color: green; -fx-border-radius: 8px; -fx-border-width: 2px;");
            }
        });
        

    contacttxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("^[\\d\\s\\p{Punct}]+")) { // Allows digits, spaces, and symbols but not letters, and exactly 11 characters
            contacttxtBox.setStyle("-fx-border-color: red; -fx-border-radius: 8px; -fx-border-width: 2px;");
        } else {
            contacttxtBox.setStyle("-fx-border-color: green; -fx-border-radius: 8px; -fx-border-width: 2px;"); 
        }
    });

    companyEmail.textProperty().addListener((observable, oldValue, newValue) -> {
  
            // Check if the email is valid
            if (!isValidEmail(newValue)) {
                // Set border color to red if email is not valid
                companyEmail.setStyle("-fx-border-color: red; -fx-border-radius: 8px; -fx-border-width: 2px;");
                return;
            }
            else {
                companyEmail.setStyle("-fx-border-color: green; -fx-border-radius: 8px; -fx-border-width: 2px;"); 

            }
    });

    postalTxtbox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("[\\d\\s\\p{Punct}]+") || newValue.matches(".*[a-zA-Z]+.*")) {
            postalTxtbox.setStyle("-fx-border-color: red; -fx-border-radius: 8px; -fx-border-width: 2px;");

        } else if (newValue.length() > 6) {

            postalTxtbox.setStyle("-fx-border-color: red; -fx-border-radius: 8px; -fx-border-width: 2px;");

        } else {

            postalTxtbox.setStyle("-fx-border-color: green; -fx-border-radius: 8px; -fx-border-width: 2px;"); 
        }
    });

    
    HiringPositionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {

            jobPositiontextbox.setText(newSelection.getJobPosition());
            jobDescriptiontxtBox.setText(newSelection.getjobDescriptionProperty());
            lastdateapplied.setValue(newSelection.getLastdateApply());
        }
    });


    }

    @FXML
    void Logoutpane(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
    
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
}
