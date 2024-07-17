package Controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;


public class EveryJuanaHome implements Initializable{

    @FXML
    private Button SignUpBtn;

    @FXML
    private Button loginBtn;

    @FXML
    void HomepageLoginbutton(MouseEvent event) {
        
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
    void SignUpPage(ActionEvent event) {

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/SignUpPage.fxml"));
        Parent signUpParent = loader.load();
        Scene signUpScene = new Scene(signUpParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(signUpScene);
        window.show();

    } catch (IOException e) {
        e.printStackTrace(); 
    }
}
@FXML
    void AboutPage(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/About.fxml"));
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
    void HowtoApply(MouseEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HowToApply.fxml"));
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       
}
}