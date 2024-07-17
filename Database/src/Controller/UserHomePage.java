package Controller;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class UserHomePage {
     @SuppressWarnings("unused")
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
    private String currentUsername;
    @FXML
    private ImageView imageView;

    @FXML
    private ImageView icon;

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
    private AnchorPane profile_mainform1,UserhomeAnchorpane;

    @FXML
    private AnchorPane company_mainform;

    @FXML
    private Label usernameLabel;

    @FXML
    void CompaniesTextOnclick(MouseEvent event) {
    try {
        // Load UserInterface.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserInterface.fxml"));
        Parent root = loader.load();

        UserInterface controller = loader.getController();
        controller.setCurrentUsername(currentUsername);

        // Get the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the scene with the loaded root
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // if (UserhomeAnchorpane != company_mainform) {
        //     if (UserhomeAnchorpane != null) {
        //         UserhomeAnchorpane.setStyle("");
        //     }
        //     company_mainform.setStyle("-fx-background-color: linear-gradient(to bottom, #FDF1BA, #FFDB58);");
        // } else {
        //     company_mainform.setStyle("");
        // }

        // // Set visibility of UI elements
        // company_mainform.setVisible(true);
        // profile_mainform1.setVisible(false);

        // Show the stage
        stage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
}


@FXML
    void profile(ActionEvent event) {
    try {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/UserInterface.fxml"));
        Parent root = loader.load();
        
        UserInterface controller = loader.getController();
        controller.setCurrentUsername(currentUsername);

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

public void setCurrentUsername(String username) {
    this.currentUsername = username;
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

}
