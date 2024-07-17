package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.scene.Node;


public class LoginPage implements Initializable{

    @FXML
    private RadioButton adminChoiceBtn;

    @FXML
    private RadioButton adminChoiceBtn1;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private TextField adminUsername;

    @FXML
    private AnchorPane admin_mainform;

    @FXML
    private Button loginBtn;

    @FXML
    private Button adminLoginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private Slider slider;

    @FXML
    private RadioButton userChoceBtn;

    @FXML
    private RadioButton userChoceBtn1;

    @FXML
    private TextField userName;

    @FXML
    private AnchorPane user_mainform;

    @FXML
    private Slider loginslider;


    
    @FXML
    void adminPasswordtxtbox(ActionEvent event) {

    }

    @FXML
    void adminusernametxtBox(ActionEvent event) {

    }

    
    @FXML
    void Loginslider(MouseEvent event) {

    }



    
    @FXML
    void loginNow(ActionEvent event) {
        Connect(); 
    
        String enteredUsername = userName.getText(); 
        String enteredPassword = password.getText(); 
    
        try {
            String query = "SELECT username, password, firstName, lastName FROM Registration WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, enteredPassword);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                showAlert("Login Successful", "Welcome, " + firstName + " " + lastName + "!", Alert.AlertType.INFORMATION);
                
                // Load the new FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserInterface.fxml"));
                Parent root = loader.load();
                UserInterface controller = loader.getController();
    
                controller.setCurrentUsername(enteredUsername);

               
                // Set the new scene
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                showAlert("Login Failed", "Invalid username or password.", Alert.AlertType.ERROR);
            }
        } catch (SQLException | IOException e) {
            System.err.println("ERROR: Failed to execute SQL query or load next page.");
            e.printStackTrace();
        }
    }
    


    // @FXML
    // void adminLogin(ActionEvent event) {

    //     String enteredUsername = adminUsername.getText();
    //     String enteredPassword = adminPassword.getText();

    //     String adminUsername = "123";
    //     String adminPassword = "123";

    //     if (enteredUsername.equals(adminUsername) && enteredPassword.equals(adminPassword)) {
    //         try {
    //             FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AdminInterface.fxml"));
    //             Parent root = loader.load();
    //             Stage stage = new Stage();
    //             stage.setScene(new Scene(root));
    //             stage.show();

    //             ((Node) (event.getSource())).getScene().getWindow().hide();

    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     } else {
    //         showAlert("Login Failed", "Invalid username or password.", Alert.AlertType.ERROR);
    //     }
    // }



    @FXML
    void adminLogin(ActionEvent event) {
    String enteredUsername = adminUsername.getText();
    String enteredPassword = adminPassword.getText();

    try {
        String sql = "SELECT username, password FROM Admin WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, enteredUsername);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String adminUsername = resultSet.getString("username");
            String adminPassword = resultSet.getString("password");

            if (enteredPassword.equals(adminPassword)) {
                showAlert("Login Successful", "Welcome, " + adminUsername + "!", Alert.AlertType.INFORMATION);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AdminInterface.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                ((Node) (event.getSource())).getScene().getWindow().hide();

            } else {
                showAlert("Login Failed", "Invalid password.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Login Failed", "Invalid username and password", Alert.AlertType.ERROR);
        }

        resultSet.close();
        statement.close();
    } catch (SQLException | IOException e) {
        e.printStackTrace();
        showAlert("Error", "An error occurred during login.", Alert.AlertType.ERROR);
    }
}


    @FXML
    void gotoHomepage(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    @FXML
    void gotoRegister(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUpPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    @FXML
    void gotologinarrowabout(MouseEvent event) {
        
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
    void gotologinarrowHowApply(MouseEvent event) {
        
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
    void gotoHome(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
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
    void passwordTxtBox(ActionEvent event) {

    }

    @FXML
    void userNametxtbox(ActionEvent event) {

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
    }
    @FXML
    void gotoAdmin(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Admin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void gotologin(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginPage.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

