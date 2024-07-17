package Controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;


public class RegistrationSuccessful {

    @FXML
    private Button gotoHome;

    @FXML
    private Button gotoRegisPage;

    @FXML
    void GotoHomePage(ActionEvent event) {
    try {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Homepage.fxml"));
        Parent root = loader.load();
        
        // Create a new scene with the loaded FXML file
        Scene scene = new Scene(root);
        
        // Get the stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Set the scene to the stage
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    void goBacktoRegis(ActionEvent event) {

    try {
        Parent root = FXMLLoader.load(getClass().getResource("/View/SignUpPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}


