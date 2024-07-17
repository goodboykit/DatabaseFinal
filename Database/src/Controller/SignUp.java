package Controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.Node;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SignUp implements Initializable {

    @FXML
    private Button Import;

    @FXML
    private PasswordField RetypeTextBox;


    @FXML
    private DatePicker Birthdate;

    @FXML
    private ImageView iconRetype;

    @FXML
    private ImageView iconRtype;

     @FXML
    private Label errorLabel;

    @FXML
    private TextField MiddleITxtBox;

    @FXML
    private PasswordField notshowretype;

    @FXML
    private PasswordField showretype;


    @FXML
    private ImageView NOTSHOWICON;

    @FXML
    private ImageView NOTSHOWICON1;

    @FXML
    private ImageView ShowIcon;

    @FXML
    private ImageView ShowIcon1;

    @FXML
    private TextField addresstextbox;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<City> cityChoice;

    @FXML
    private ComboBox<String> countrychoice;

    @FXML
    private ComboBox<String> genderChoice;

    @FXML
    private TextField emailtxtBox;

    @FXML
    private TextField firstnameTxtBox;

    @FXML
    private TextField lastnametxtBox;

    @FXML
    private TextField mobileTextbox;

    @FXML
    private CheckBox termsandconditions;

    @FXML
    private PasswordField showPasstxtBox;

    @FXML
    private PasswordField notshowPasstxtBox;

    @FXML
    private AnchorPane regis_form;

    @FXML
    private ImageView regis_imageview;

    @FXML
    private Button savedDatabtn;

    @FXML
    private TextField usernametxtBox;


    @FXML
    void showIcon(MouseEvent event) {
    NOTSHOWICON.setVisible(true);
    ShowIcon.setVisible(false);
    showretype.setVisible(false);
    notshowretype.setVisible(true);
    notshowretype.setText(showretype.getText());
    }

    @FXML
    void DontshowIcon(MouseEvent event) {
    showretype.setVisible(true);
    notshowretype.setVisible(false);

    ShowIcon.setVisible(true);
    NOTSHOWICON.setVisible(false);
    showretype.setText(showretype.getText());
    }


    @FXML
    void showIcon1(MouseEvent event) {

        showPasstxtBox.setVisible(false);
        notshowPasstxtBox.setVisible(true);
        NOTSHOWICON1.setVisible(true);
        ShowIcon1.setVisible(false);
        notshowPasstxtBox.setText(showPasstxtBox.getText());
    }

    @FXML
    void DontshowIcon1(MouseEvent event) {

        showPasstxtBox.setVisible(true);
        notshowPasstxtBox.setVisible(false);
        ShowIcon1.setVisible(true);
        NOTSHOWICON1.setVisible(false);
        showPasstxtBox.setText(notshowPasstxtBox.getText());
    }
    

    @FXML
    void showPasstxtBoxKeyReleased(KeyEvent event) {
        notshowPasstxtBox.setText(showPasstxtBox.getText());
    }

    @FXML
    void notshowPasstxtBoxKeyReleased(KeyEvent event) {
        showPasstxtBox.setText(notshowPasstxtBox.getText());
    }


   @FXML
    void CancelNow(ActionEvent event) {
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
    void displayTermsAndConditions(ActionEvent event) {
    String message = "By creating a user account, you agree to the following terms and conditions:\n\n"
            + "1. You must provide accurate and complete information during the registration process,\nincluding your full name, username, and any other information that may be required.\n\n"
            + "2. You must choose a strong and secure password and keep it confidential. You are\nsolely responsible for maintaining the confidentiality of your password and\naccount information.\n\n"
            + "3. You are solely responsible for all activities that occur under your account.\nYou agree to notify us immediately of any unauthorized use of your account\nor any other breach of security.\n\n"
            + "4. You may not use the user account to engage in any illegal or unauthorized activity,\nincluding but not limited to violating any applicable laws or regulations, infringing\non any intellectual property rights, or accessing any restricted areas\nor confidential information.\n\n"
            + "5. You may not share your user account or password with anyone else,\nor allow anyone else to access your account.\n\n"
            + "6. We reserve the right to suspend or terminate your user account at any time,\nfor any reason, without notice or liability to you.\n\n"
            + "7. You agree to indemnify and hold us harmless from any claims, damages,\nor losses arising from your use of the user account or any breach of\nthese terms and conditions.\n\n";

    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Terms and Conditions");
    dialog.setHeaderText(null);
    
    dialog.setContentText(message);
    
    ButtonType agreeButton = new ButtonType("Agree", ButtonBar.ButtonData.OK_DONE);
    ButtonType disagreeButton = new ButtonType("Disagree", ButtonBar.ButtonData.CANCEL_CLOSE);
    
    dialog.getDialogPane().getButtonTypes().addAll(agreeButton, disagreeButton);
    
    Optional<ButtonType> result = dialog.showAndWait();
    
    if (result.isPresent() && result.get() == agreeButton) {
        termsandconditions.setSelected(true);
    } else {
        termsandconditions.setSelected(false);
    }
    }

    String image;
    String imagePath;
    @FXML
    void SavedData(ActionEvent event) {

        if (connection == null) {
            System.out.println("Database connection is not established.");
            return;
        }
        
        String firstName = firstnameTxtBox.getText();
        String middleName = MiddleITxtBox.getText();
        String lastName = lastnametxtBox.getText();
        String username = usernametxtBox.getText();
        String password = notshowPasstxtBox.getText();
        String retypepass = notshowretype.getText();

        String mobile = mobileTextbox.getText();
        String email = emailtxtBox.getText();
        String address = addresstextbox.getText();
        String gender = (String) genderChoice.getSelectionModel().getSelectedItem();

        LocalDate birthday = Birthdate.getValue();

        City selectedCity = cityChoice.getValue();
        int city_id = selectedCity.getCityId();

        image = regis_imageview.getImage() != null ? regis_imageview.getImage().getUrl() : null;
        image = imagePath; 
        
        try {
            // Check if the username already exists in the database
            String checkUsernameQuery = "SELECT COUNT(*) FROM Registration WHERE username = ?";
            PreparedStatement checkUsernameStmt = connection.prepareStatement(checkUsernameQuery);
            checkUsernameStmt.setString(1, username);
            ResultSet checkUsernameResult = checkUsernameStmt.executeQuery();
    
            if (checkUsernameResult.next()) {
                int count = checkUsernameResult.getInt(1);
                if (count > 0) {
                    showAlert("Username Taken", "The username is already taken. Please choose another one.", Alert.AlertType.ERROR);
                    return;
                }
            }

            if (image == null || image.isEmpty() ) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Please select an image.");
                alert.showAndWait();
                return;
            }
            

        if (firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || retypepass.isEmpty() || mobile.isEmpty() || email.isEmpty() || address.isEmpty() || image == null || image.isEmpty() || birthday == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            if (image == null || image.isEmpty()) {
                alert.setContentText("Please select an image.");
            } else {
                alert.setContentText("Please fill in all the fields.");
            }
            alert.showAndWait();
            return;
        }

        if (!isValidName(firstName)) {
        showAlert("Invalid Input", "First name should contain only alphabetic characters.", Alert.AlertType.ERROR);
        return;
        }

        if (!isValidName(middleName)) {
            showAlert("Invalid Input", "Middle name should contain only alphabetic characters.", Alert.AlertType.ERROR);
            return;
        }

        if (!isValidName(lastName)) {
            showAlert("Invalid Input", "Last name should contain only alphabetic characters.", Alert.AlertType.ERROR);
            return;
        }

        if (!isValidPassword(password)) {
            showAlert("Invalid Password", "Password should be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit.", Alert.AlertType.ERROR);
            return;
        }

        if (!password.equals(retypepass)) {
            showAlert("Password Mismatch", "Password and Retype Password do not match.", Alert.AlertType.ERROR);
            return;
        }

        if (mobile.length() != 11 || !mobile.matches("\\d+")) {
            showAlert("Invalid Mobile Number", "Mobile number should contain exactly 11 digits.", Alert.AlertType.ERROR);
            return;
        }

        if (cityChoice.getSelectionModel().isEmpty()) {
            showAlert("Error", "Please select a city.", Alert.AlertType.ERROR);

            return;
        }

        if (!termsandconditions.isSelected()) {
            showAlert("Terms and Conditions", "Please read and accept the terms and conditions before proceeding.", Alert.AlertType.CONFIRMATION);
            displayTermsAndConditions(event);
            return;
        }

        if (!isValidEmail(email)) {
            showAlert("Invalid Email", "Please enter a valid email address.", Alert.AlertType.ERROR);
            return;
        }
       
            String sql = "INSERT INTO Registration (firstname, middlename, lastname, username, password, mobile, email, address, image, birthday, gender, RegistrationID, city_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, firstName);
            pst.setString(2, middleName);
            pst.setString(3, lastName);
            pst.setString(4, username);
            pst.setString(5, password);
            pst.setString(6, mobile);
            pst.setString(7, email);
            pst.setString(8, address);
            pst.setString(9, image);
            pst.setObject(10, birthday);
            pst.setObject(11, gender);

            pst.setString(12, null);
            pst.setInt(13, city_id);
            pst.executeUpdate();

            showAlert("Success", "Data saved successfully.", Alert.AlertType.INFORMATION);


        } catch (SQLException e) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);

            showAlert("Error", "Failed to save data to the database.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistrationSuccessful.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load RegistrationSuccessful.fxml", Alert.AlertType.ERROR);
        }
    
    }

        private boolean isValidName(String name) {
            return name.matches("^[a-zA-Z]+(?:[ ,.;]?[a-zA-Z]+)*$");
        }

    
        private boolean isValidPassword(String password) {
            // Password should be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, and one digit
            return password.length() >= 8 && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*") && password.matches(".*\\d.*");
        }

        private boolean isValidEmail(String email) {
            // Regular expression for basic email validation
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            return email.matches(emailRegex);
        }

        private void showAlert(String title, String message, Alert.AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private void initializeGenderComboBox() {
        ObservableList<String> genderOptions = FXCollections.observableArrayList("Female", "Male", "Other/s");
        genderChoice.setItems(genderOptions);
        
    }
    @FXML
    void gotoLoginarrow(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginPage.fxml"));
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
    void gotoLogin(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginPage.fxml"));
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
        void ImportBtn(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        String initialDirectoryPath = "/Users/goodboykit/FinalProject_Database/src/REGISTRATION/Pictures";
        File initialDirectory = new File(initialDirectoryPath);
        fileChooser.setInitialDirectory(initialDirectory);

        File file = fileChooser.showOpenDialog(regis_form.getScene().getWindow());

        if (file != null) {

            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                if (bufferedImage != null) {
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    regis_imageview.setImage(image);
                    regis_imageview.setFitWidth(regis_imageview.getFitWidth());
                    regis_imageview.setFitHeight(regis_imageview.getFitHeight());

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


    Connection connection;
    PreparedStatement pst; 
    int myIndex; 
    int id;

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


    private void populateCityComboBox() {

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DatabaseProject", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM City");

        List<City> cities = new ArrayList<>();
        while (rs.next()) {
            int cityId = rs.getInt("city_id");
            String cityName = rs.getString("city_name");
            cities.add(new City(cityId, cityName));
        }
        cityChoice.getItems().addAll(cities);
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Connect();

        firstnameTxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches(".*\\d.*")) {
                firstnameTxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");

            } else {
                firstnameTxtBox.setStyle("-fx-border-color: green;"); 
            }
        });
       
        MiddleITxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.matches(".*\\d.*")) 
        {
            MiddleITxtBox.setStyle("-fx-border-color: red;");
            MiddleITxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");

        } else {
            MiddleITxtBox.setStyle("-fx-border-color: green;"); 
        }
    });

        lastnametxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.matches(".*\\d.*")) {
            lastnametxtBox.setStyle("-fx-border-color: red;");
            lastnametxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");

        } else {
            lastnametxtBox.setStyle("-fx-border-color: green;"); 
        }
    });

        notshowPasstxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(notshowretype.getText())) {
                notshowPasstxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
                notshowretype.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");

            } else {
                notshowPasstxtBox.setStyle("-fx-border-color: green;");
                 notshowretype.setStyle("-fx-border-color: green;");
            }
        });

        notshowretype.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(notshowPasstxtBox.getText())) {
                notshowPasstxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
                notshowretype.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
            } else {
                notshowPasstxtBox.setStyle("-fx-border-color: green;");
                notshowretype.setStyle("-fx-border-color: green;");
            }
        });

   
        populateCityComboBox();

        cityChoice.setCellFactory(new Callback<ListView<City>, ListCell<City>>() {
            @Override
            public ListCell<City> call(ListView<City> param) {
                return new ListCell<City>() {
                    @Override
                    protected void updateItem(City item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null && !empty) {
                            setText(item.getCityName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        termsandconditions.setOnAction(event -> {
                showAlert("Terms and Conditions", "Please read and accept the terms and conditions before proceeding.", Alert.AlertType.CONFIRMATION);
                displayTermsAndConditions(event);
        });
        
        usernametxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
            
            try {
                String checkUsernameQuery = "SELECT COUNT(*) FROM Registration WHERE username = ?";
                PreparedStatement checkUsernameStmt = connection.prepareStatement(checkUsernameQuery);
                checkUsernameStmt.setString(1, newValue);
                ResultSet checkUsernameResult = checkUsernameStmt.executeQuery();
        
                if (checkUsernameResult.next()) {
                    int count = checkUsernameResult.getInt(1);

                    if (count > 0) {
                        usernametxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");

                    } else {
                        usernametxtBox.setStyle("-fx-border-color: green;");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });


        emailtxtBox.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!isValidEmail(newValue)) {
                    emailtxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
                    return;
                }
        
                String checkEmailQuery = "SELECT COUNT(*) FROM Registration WHERE email = ?";
                try (PreparedStatement checkEmailStmt = connection.prepareStatement(checkEmailQuery)) {
                    checkEmailStmt.setString(1, newValue);
                    try (ResultSet checkEmailResult = checkEmailStmt.executeQuery()) {
                        if (checkEmailResult.next()) {
                            int count = checkEmailResult.getInt(1);
                            if (count > 0) {
                                // Email already exists in the database, show alert
                                showAlert("Error", "Email already exists in the database.", Alert.AlertType.ERROR);
                                emailtxtBox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
                            } else {
                                // Email is valid and does not exist in the database
                                emailtxtBox.setStyle("-fx-border-color: green;");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);
                showAlert("Error", "Failed to check email in the database.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });
        

        mobileTextbox.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("^\\d{11}$")) {
                mobileTextbox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: green;");
            } else {
                mobileTextbox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
            }
        });
        
        

        mobileTextbox.textProperty().addListener((observable, oldValue, newValue) -> {

            try {
                String checkMobileQuery = "SELECT COUNT(*) FROM Registration WHERE mobile = ?";
                PreparedStatement checkMobileStmt = connection.prepareStatement(checkMobileQuery);
                checkMobileStmt.setString(1, newValue);
                ResultSet checkMobileResult = checkMobileStmt.executeQuery();

                if (checkMobileResult.next()) {
                    int count = checkMobileResult.getInt(1);
                    if (count > 0) {
                        mobileTextbox.setStyle("-fx-border-width: 0 0 2px 0; -fx-border-color: red;");
                    } 
                }

            } catch (SQLException e) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, e);
                showAlert("Error", "Failed to check mobile number in the database.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        });

        initializeGenderComboBox();
    }
}


