package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

public class CompanyDetailsPage implements Initializable {

    private String currentUsername;

    @FXML
    private Button CancelBtn;

    @FXML
    private ComboBox<String> DegreeType;

    @FXML
    private Button ImportBtn;

    @FXML
    private ComboBox<String> jobPositionType;

    @FXML
    private Button SaveBtn;

    @FXML
    private Label QualificationLabel;

    @FXML
    private Label experienceRequired;

    @FXML
    private Label JobTypeLabel;

    @FXML
    private Label JobDescriptionLabel;

    @FXML
    private Label filePath;

    @FXML
    private Label postalLabel;

    @FXML
    private ImageView company_image;

    @FXML
    private Label companyadressTxtBox;

    @FXML
    private Label companyemailtxtBox;

    @FXML
    private Label companytxBox;

    @FXML
    private Label contacttxtbox;

    @FXML
    private DatePicker datewantedtoApply;

   
    @FXML
    private TableView<CompanyDetails> hiringpositions_table;

    @FXML
    private TableColumn<CompanyDetails, String> jobpositionColumn;

    @FXML
    private TableColumn<CompanyDetails, String> salaryLabel;


    @FXML
    private TableColumn<CompanyDetails, LocalDate> lastdateappliedCol;   
    private CompanyDetails companyDetails;


    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public void setCompanyDetails(CompanyDetails companyDetails) {
        this.companyDetails = companyDetails;
    }
    

    @FXML
    void Cancel(ActionEvent event) {    
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserInterface.fxml"));
            Parent root = loader.load();
            
            UserInterface controller = loader.getController();
            controller.setCurrentUsername(currentUsername); 
            
            @SuppressWarnings("unused")
            AnchorPane companyMainFormAnchorPane = (AnchorPane) root.lookup("#company_mainform");

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    @FXML   
    void ImportResume(ActionEvent event) {

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Document File");
    fileChooser.setInitialDirectory(new File("/Users/goodboykit/FinalProject_Database/src/REGISTRATION/Resumes"));
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("PDF Documents", "*.pdf"));
    File file = fileChooser.showOpenDialog(null);

    if (file != null) {
        try {
            byte[] fileData = Files.readAllBytes(file.toPath());
            filePath.setText(file.getAbsolutePath());

            showAlert("Success", "Document Saved!: " + file.getName(), AlertType.INFORMATION);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to read or save document: " + file.getName(), AlertType.ERROR);
        }
        }
    }


    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void SaveIt(ActionEvent event) throws SQLException {

        String companyId = companyDetails.getId(); 
        String degreeType = DegreeType.getValue(); 
        String jobPosition = jobPositionType.getValue();
        LocalDate dateWanted = datewantedtoApply.getValue(); 
        File documentFile = new File(filePath.getText()); 

        if (companyId == null || degreeType == null || jobPositionType == null || dateWanted == null || documentFile == null) {
            showAlert("Error", "Please fill in all required fields.", AlertType.ERROR);
            return;
        }

        try {
            int qualificationId = getQualificationIdFromDatabase(degreeType);
            int hiringPositionId = getHiringPositionIdFromDatabase(companyId, jobPosition);

            LocalDate lastDateApplied = getLastDateAppliedFromDatabase(hiringPositionId);
        
            if (lastDateApplied != null && dateWanted.isAfter(lastDateApplied)) {
                showAlert("Error", "Date wanted cannot be beyond for the last date application.", AlertType.ERROR);
                return;
            }

            // Read the document file content as a byte array
            byte[] documentData = Files.readAllBytes(documentFile.toPath());
    
            insertDataIntoDatabase(companyId, qualificationId, hiringPositionId, dateWanted, documentData);
    
            showAlert("Success", "Data saved successfully.", AlertType.INFORMATION);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserInterface.fxml"));
            Parent root = loader.load();
            
            UserInterface controller = loader.getController();
            controller.setCurrentUsername(currentUsername); 

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to read or save document: " + documentFile.getName(), AlertType.ERROR);
        }
    }

    private int getQualificationIdFromDatabase(String degreeType) throws SQLException {

        String query = "SELECT qualification_id FROM Qualification WHERE degree = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, degreeType);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("qualification_id");
                } else {
                    throw new SQLException("Qualification not found for degree: " + degreeType);
                }
            }
        }
    }

    private LocalDate getLastDateAppliedFromDatabase(int hiringPositionId) throws SQLException {
        String sql = "SELECT lastdateapplied FROM HiringPosition WHERE hiringposition_id = ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setInt(1, hiringPositionId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getDate("lastdateapplied").toLocalDate();
                }
            }
        }
        return null; // Return null if no last date applied found
    }

    private int getHiringPositionIdFromDatabase(String companyId, String jobPosition) throws SQLException {
        String query = "SELECT hiringposition_id FROM HiringPosition WHERE company_id = ? AND jobposition = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, companyId);
            statement.setString(2, jobPosition); 
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("hiringposition_id");
                } else {
                    throw new SQLException("Hiring position not found for job position: " + jobPosition);
                }
            }
        }
    }

    private void insertDataIntoDatabase(String companyId, int qualificationId, int hiringPositionId, LocalDate dateWanted, byte[] documentData) throws SQLException {

        String query = "INSERT INTO AppliedUsers (company_id, hiringposition_id, qualification_id, registration_id, datewanted, document) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {


            int registrationId = getRegistrationIdFromDatabase(currentUsername);

            statement.setString(1, companyId);
            statement.setInt(2, hiringPositionId);
            statement.setInt(3, qualificationId);
            statement.setInt(4, registrationId); 
            statement.setDate(5, Date.valueOf(dateWanted));
            statement.setBytes(6, documentData);
            statement.executeUpdate();
        }
    }


    private int getRegistrationIdFromDatabase(String username) throws SQLException {
        String query = "SELECT RegistrationID FROM Registration WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("RegistrationID");
                } else {
                    throw new SQLException("Registration ID not found for username: " + username);
                }
            }
        }
    }


    //-------------------------------------------------------------------------------------------------
    
    public void fetchJobPositionDetails(String companyId) {
        ObservableList<CompanyDetails> data = FXCollections.observableArrayList();
    
        try {
            String sql = "SELECT hp.jobposition, hp.jobdescription, hp.lastdateapplied, cd.salary " +
                         "FROM HiringPosition hp " +
                         "JOIN CompanyDetails cd ON hp.company_id = cd.company_id " +
                         "WHERE cd.company_id = ?";
    
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, companyId);
            ResultSet rs = pst.executeQuery();
    
            while (rs.next()) {
                String jobPosition = rs.getString("jobposition");
                String jobDescription = rs.getString("jobdescription");
                LocalDate lastDateApplied = rs.getDate("lastdateapplied").toLocalDate();
                String salary = rs.getString("salary");
    
                CompanyDetails company = new CompanyDetails();
                company.setJobPosition(jobPosition);
                company.setJobDescription(jobDescription);
                company.setLastdateProperty(lastDateApplied);
                company.setSalary(salary); 
                data.add(company);
    
                JobDescriptionLabel.setText(jobDescription);
            }
    
            rs.close();
            pst.close();
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        jobpositionColumn.setCellValueFactory(new PropertyValueFactory<>("jobPosition"));
        lastdateappliedCol.setCellValueFactory(cellData -> cellData.getValue().lastdateProperty());        
        salaryLabel.setCellValueFactory(new PropertyValueFactory<>("salary")); 
        hiringpositions_table.setItems(data);   

        hiringpositions_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Update the JobDescriptionLabel with the description of the selected hiring position
                JobDescriptionLabel.setText(newSelection.getjobDescriptionProperty());
            } else {
                JobDescriptionLabel.setText("");
            }
        });
    }
    

    public void initData(String companyId) {

        ObservableList<CompanyDetails> data = FXCollections.observableArrayList();
    
        try {

            String sql = "SELECT cd.companyname, cd.companyaddress, cd.companyemail, cd.companycontact, cd.postalcode, cd.experiencerequired, cd.jobtype, cd.image, q.degree " +
                        "FROM CompanyDetails cd " +
                        "JOIN Qualification q ON cd.qualification_id = q.qualification_id " +
                        "WHERE cd.company_id = ?";

        
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, companyId);
            ResultSet rs = pst.executeQuery();
    
            while (rs.next()) {
                
                String companyName = rs.getString("companyname");
                String companyAddress = rs.getString("companyaddress");
                String companycontact = rs.getString("companycontact");
                String companyemail = rs.getString("companyemail");
                String postalLabell = rs.getString("postalcode");
                String experiencerequired = rs.getString("experiencerequired");
                String jobType = rs.getString("jobtype");
                String degreeRequired = rs.getString("degree");

                //---IMAGE
                String imagePath = rs.getString("image"); 
                Image image = new Image(imagePath, 1000, 1000, false, true);
                company_image.setImage(image);
                //IMAGE--- 
                
                CompanyDetails company = new CompanyDetails();
                company.setCompany(companyName);
                company.setCompanyAddress(companyAddress);
                company.setDegree(degreeRequired);
                company.setExperience(experiencerequired);
                company.setJobType(jobType);
                company.setContact(companycontact);
                company.setPostal(postalLabell);
                company.setEmail(companyemail);

                data.add(company);
                companytxBox.setText(companyName);
                companyemailtxtBox.setText(companyemail);
                companyadressTxtBox.setText(companyAddress);
                contacttxtbox.setText(companycontact);
                postalLabel.setText(postalLabell);
                JobTypeLabel.setText(jobType);
                experienceRequired.setText(experiencerequired);
                QualificationLabel.setText(degreeRequired);

            }

            rs.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Pair<String, String>> fetchDegreeTypesFromDatabase() {

        List<Pair<String, String>> degreeTypes = new ArrayList<>();
        try {
            String query = "SELECT qualification_id, degree FROM Qualification";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String degreeId = resultSet.getString("qualification_id");
                String degreeType = resultSet.getString("degree");
                Pair<String, String> degreePair = new Pair<>(degreeId, degreeType);
                degreeTypes.add(degreePair);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return degreeTypes;
    }

    private void populateDegreeTypeComboBox() {
        List<Pair<String, String>> degreeTypes = fetchDegreeTypesFromDatabase();
        ObservableList<String> degreeTypeNames = FXCollections.observableArrayList();
        for (Pair<String, String> degreePair : degreeTypes) {
            degreeTypeNames.add(degreePair.getValue());
        }
        DegreeType.setItems(degreeTypeNames);
    }



    public List<String> fetchJobPositionTypesFromDatabase(String companyId) {
        List<String> jobPositionTypes = new ArrayList<>();
        
        try {
            String query = "SELECT DISTINCT jobposition FROM HiringPosition WHERE company_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, companyId);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String jobPositionType = resultSet.getString("jobposition");
                jobPositionTypes.add(jobPositionType);
                System.out.println("Retrieved job position type: " + jobPositionType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobPositionTypes;
    }

    public void populateJobPositionTypeComboBox(String companyId) {
        List<String> jobPositionTypes = fetchJobPositionTypesFromDatabase(companyId);
        ObservableList<String> jobPositionTypeNames = FXCollections.observableArrayList(jobPositionTypes);
        jobPositionType.setItems(jobPositionTypeNames);
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
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Connect();
        populateDegreeTypeComboBox();
    }
}



