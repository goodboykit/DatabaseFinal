package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class UserInterface implements Initializable {

  
    private int id;

    @FXML
    public GridPane menu_gridPane;

    @FXML
    private Button ChangeBtn;

    @FXML
    private Button UpdateBtn;

    @FXML
    private TextField addresstxtBox;

    @FXML
    private DatePicker birthdaytxtBox;

    @FXML
    private TextField contatctxtBox;

    @FXML
    private TextField emailtxtBox;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private ImageView imageView;

    @FXML
    private Pane PaneProfile;

    @FXML
    private Pane HomePane;

    @FXML
    private Pane CompanyPane;

    @FXML
    private Pane ViewApplicationPane;

    @FXML
    private TextField lastnametxtbox;

    @FXML
    private TextField middlenametxtbox;

    @FXML
    private TextField nameBox;

    @FXML
    private AnchorPane profile_mainform;

    @FXML
    private AnchorPane company_mainform;

    @FXML
    private Label usernameLabel;

    private String currentUsername;


    //CARDDISPLAY COMPANY -----------

    private ObservableList<CompanyDetails> menuGetData() {

        ObservableList<CompanyDetails> listData = FXCollections.observableArrayList();
        String sql = "SELECT CD.*, HP.jobposition " +
        "FROM CompanyDetails CD " +
        "INNER JOIN HiringPosition HP ON CD.company_id = HP.company_id";    
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
    
            while (rs.next()) {
                CompanyDetails company = new CompanyDetails();

                company.setId(rs.getString("company_id")); 
                company.setCompany(rs.getString("companyname"));
                company.setCompanyAddress(rs.getString("companyaddress"));
                company.setEmail(rs.getString("companyemail"));
                company.setImage(rs.getString("image"));
                company.setJobPosition(rs.getString("jobposition")); 
                listData.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return listData;
    }
    

    private ObservableList<CompanyDetails> cardListData = FXCollections.observableArrayList();

    public void menuDisplayCard(String username) {
        try {
            cardListData.clear();
            cardListData.addAll(menuGetData());
    
            int row = 0;
            int column = 0;
    
            menu_gridPane.getChildren().clear();
            menu_gridPane.getRowConstraints().clear();
            menu_gridPane.getColumnConstraints().clear();
    
            for (int q = 0; q < cardListData.size(); q++) {
                try {
                    FXMLLoader load = new FXMLLoader(getClass().getResource("/View/cardCompany.fxml"));
                    AnchorPane pane = load.load();
                    cardController controller = load.getController();

                    controller.setCurrentUsername(currentUsername);
                    controller.setData(cardListData.get(q));
    

                    if (column == 1) {
                        column = 0;
                        row += 1;
                    }
    
                    menu_gridPane.add(pane, column++, row);
                    Insets insets = new Insets(20, 45, 10, 45);

                    GridPane.setMargin(pane, insets);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompanyCardImage(int companyId, String imagePath) {
        for (CompanyDetails company : cardListData) {
            if (company.getId().equals(String.valueOf(companyId))) {
                company.setImage(imagePath);
                break;
            }
        }
    }
    
    // PROFILE LIST --------------------------------------------------------------------------------------------------------

    // Method to show an alert
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void ChangeDetails(ActionEvent event) {

        nameBox.setEditable(true);
        middlenametxtbox.setEditable(true);
        lastnametxtbox.setEditable(true);
        emailtxtBox.setEditable(false);
        contatctxtBox.setEditable(false);
        addresstxtBox.setEditable(true);
        genderComboBox.setDisable(false);
        birthdaytxtBox.setDisable(false);
        cityComboBox.setDisable(false);
    }

    @FXML
    void updateDetails(ActionEvent event) {

        String firstName = nameBox.getText();
        String middleName = middlenametxtbox.getText();
        String lastName = lastnametxtbox.getText();
        String address = addresstxtBox.getText();
        String gender = genderComboBox.getValue();
        LocalDate birthday = birthdaytxtBox.getValue();
        String cityId = cityComboBox.getValue(); 


        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || address.isEmpty() || gender.isEmpty() || birthday == null || cityId == null) {
            showAlert("Validation Error", "Please fill in all the fields.", Alert.AlertType.ERROR);
            return;
        }
    
        if (!firstName.matches("[a-zA-Z\\s]+") || !middleName.matches("[a-zA-Z\\s]+") || !lastName.matches("[a-zA-Z\\s]+")) {
            showAlert("Validation Error", "First, middle, and last name should contain letters and spaces only.", Alert.AlertType.ERROR);
            setRedBorderIfInvalid(nameBox);
            setRedBorderIfInvalid(middlenametxtbox);
            setRedBorderIfInvalid(lastnametxtbox);
            return;
        }
        
    
        String query = "UPDATE Registration SET "
        + "firstname = ?, "
        + "middlename = ?, "
        + "lastname = ?, "
        + "address = ?, "
        + "birthday = ?, "
        + "gender = ?, "
        + "city_id = (SELECT city_id FROM City WHERE city_name = ? LIMIT 1) "
        + "WHERE RegistrationID = ?";

       
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            
            pst.setString(1, firstName);
            pst.setString(2, middleName);
            pst.setString(3, lastName);
            pst.setString(4, address);
            pst.setDate(5, java.sql.Date.valueOf(birthday));
            pst.setString(6, gender);
            pst.setString(7, cityId);
            pst.setInt(8, id);
    
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                showAlert("Update Successful", "Registration details updated successfully.", Alert.AlertType.INFORMATION);
                setGreenBorderIfInvalid(lastnametxtbox);
                setGreenBorderIfInvalid(middlenametxtbox);
                setGreenBorderIfInvalid(nameBox);

            } else {
                showAlert("Update Failed", "Failed to update registration details.", Alert.AlertType.ERROR);
            }

            nameBox.setEditable(false);
            middlenametxtbox.setEditable(false);
            lastnametxtbox.setEditable(false);
            emailtxtBox.setEditable(false);
            contatctxtBox.setEditable(false);
            addresstxtBox.setEditable(false);
            
            birthdaytxtBox.setDisable(true);
            genderComboBox.setDisable(true);
            cityComboBox.setDisable(true);
    
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to execute SQL update query.");
            e.printStackTrace();
         }
        }
        

        private void setRedBorderIfInvalid(TextField textField) {
            textField.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
        }

        private void setGreenBorderIfInvalid(TextField textField) {
            textField.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: green;");
        }

       
    private void initializeGenderComboBox() {

        ObservableList<String> genderOptions = FXCollections.observableArrayList("Female", "Male");
        genderComboBox.setItems(genderOptions);
        
    }

    void populateUserDetails(String currentUsername) {

        String sql = "SELECT * FROM Registration WHERE username = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, currentUsername);
            ResultSet resultSet = statement.executeQuery();
        
            if (resultSet.next()) {
    
                id = resultSet.getInt("RegistrationID");
    
                RegistrationDetails userDetails = new RegistrationDetails();
    
                userDetails.setName(resultSet.getString("firstname"));
                userDetails.setMiddleName(resultSet.getString("middlename"));
                userDetails.setLastName(resultSet.getString("lastname"));
                userDetails.setEmail(resultSet.getString("email"));
                userDetails.setMobile(resultSet.getString("mobile"));
                userDetails.setAddress(resultSet.getString("address"));
                userDetails.setGender(resultSet.getString("gender"));
                userDetails.setBirthday(resultSet.getDate("birthday"));
                userDetails.setImage(resultSet.getString("image"));
                userDetails.setUserName(resultSet.getString("username"));
                userDetails.setCityId(resultSet.getString("city_id")); 
    
                String imagePath = userDetails.getImage();
             
                if (imagePath != null && !imagePath.isEmpty()) {
    
                    Image image = new Image(imagePath, 1500, 1500, false, true);
                        imageView.setImage(image);
                }
            
                nameBox.setText(userDetails.getName());
                middlenametxtbox.setText(userDetails.getmiddleName());
                lastnametxtbox.setText(userDetails.getLastName());
                emailtxtBox.setText(userDetails.getEmail());
                contatctxtBox.setText(userDetails.getMobile());
                addresstxtBox.setText(userDetails.getAddress());
                usernameLabel.setText(userDetails.getUserName());
                birthdaytxtBox.setValue(userDetails.getBirthday());
                genderComboBox.setValue(userDetails.getGender());
                String cityName = getCityNameById(userDetails.getCityId());
                if (cityName != null) {
                    cityComboBox.setValue(cityName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String getCityNameById(String cityId) {

        try {

            String query = "SELECT city_name FROM City WHERE city_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("city_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    private List<Pair<String, String>> fetchCitiesFromDatabase() {

        List<Pair<String, String>> cities = new ArrayList<>();
    
        try {
            String query = "SELECT city_id, city_name FROM City";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                String cityId = resultSet.getString("city_id");
                String cityName = resultSet.getString("city_name");
                Pair<String, String> cityPair = new Pair<>(cityId, cityName);
                cities.add(cityPair);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    
    private void populateCityComboBox() {

        List<Pair<String, String>> cities = fetchCitiesFromDatabase();
        ObservableList<String> cityNames = FXCollections.observableArrayList();
        for (Pair<String, String> city : cities) {
            cityNames.add(city.getValue());
        }
        cityComboBox.setItems(cityNames);
    }
    
    
   public void setCurrentUsername(String username) {

        this.currentUsername = username;

        if (username != null && !username.isEmpty()) {
            populateUserDetails(username);
            menuDisplayCard(username);
        } 
        
        else {
            System.out.println("Current username is not set.");
        }
    }

   
    //HOMEE CLICKED ------------------------------------------------------------------------------------------

    @FXML
    void HomeClicked(MouseEvent event) {

    }

    @FXML
    void LogoutNow(MouseEvent event) {
        

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
            Parent root = loader.load();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to logout?");
            alert.setContentText("Now exiting. Do you want to logout now?");


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
    
        } catch (IOException e) {
             e.printStackTrace();
        }

    }

    private Pane currentPane = null;


    @FXML
    void profileClick(MouseEvent event) {

        if (currentPane != PaneProfile) {
            if (currentPane != null) {
                currentPane.setStyle(""); 
            }
            PaneProfile.setStyle("-fx-background-color: linear-gradient(to bottom , #FDF1BA, #FFDB58);");
            currentPane = PaneProfile; 
        } else {
            PaneProfile.setStyle(""); 
            currentPane = null; 
        }
        company_mainform.setVisible(false);
        profile_mainform.setVisible(true);
    }


    // COMPANY LISTS ---------------------------------------------------------------------------------------------------------

    @FXML
    void CompanyClicked(MouseEvent event) {
    if (currentPane != CompanyPane) {
        if (currentPane != null) {
            currentPane.setStyle(""); 
        }
        CompanyPane.setStyle("-fx-background-color: linear-gradient(to bottom , #FFE8AC, #FFE8AC);");

        currentPane = CompanyPane; 
    } else {
        CompanyPane.setStyle(""); 
        currentPane = null; 
    }
    company_mainform.setVisible(true);
    profile_mainform.setVisible(false);
}

@FXML
    void HomePane(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserHomePage.fxml"));
            Parent root = loader.load();

            UserHomePage controller = loader.getController();
            controller.setCurrentUsername(currentUsername);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
    
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
    Connection connection;
    PreparedStatement pst; 
    int myIndex; 

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
        if (currentUsername != null && !currentUsername.isEmpty()) {
            populateUserDetails(currentUsername);
        }        initializeGenderComboBox();
        populateCityComboBox();
        menuDisplayCard(currentUsername);
        
    }

    
@FXML
    void LogoutPane(MouseEvent event) {
        
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



