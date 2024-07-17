package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class cardController implements Initializable {

    @FXML
    private Label company_name;

    @FXML
    private Label companyAddress;

    @FXML
    private Label companyEmail;

    @FXML
    private Label jobpositionCount;

    @FXML
    private ImageView company_image;

    private CompanyDetails companyDetails;
    private String currentUsername;

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }


    void setData(CompanyDetails companyDetails) {

        this.companyDetails = companyDetails;
        company_name.setText(companyDetails.getCompany());
        companyAddress.setText(companyDetails.getCompanyAddress()); 
        companyEmail.setText(companyDetails.getEmail()); 

        int jobPositionCount = getJobPositionCount(companyDetails.getId());
        jobpositionCount.setText(Integer.toString(jobPositionCount));

        String imagePath = companyDetails.getImage();

        if (imagePath != null && !imagePath.isEmpty()) {
        Image image = new Image(imagePath, 1000, 1000, false, true);
            company_image.setImage(image);
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
    
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getJobPositionCount(String companyId) {
        
        int count = 0;

        String sql = "SELECT COUNT(*) FROM HiringPosition WHERE company_id = ?";
    
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, companyId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @FXML
    void ItsClicked(MouseEvent event) {

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CompanyDetailsPage.fxml"));
        Parent root = loader.load();
        CompanyDetailsPage controller = loader.getController();

        String companyId = companyDetails.getId(); // Retrieve the company ID
        System.out.println("Company ID: " + companyId);

        controller.setCurrentUsername(currentUsername);

        controller.setCompanyDetails(companyDetails); 

        controller.initData(companyId); 
        controller.fetchJobPositionTypesFromDatabase(companyId);
        controller.populateJobPositionTypeComboBox(companyId); 
        controller.fetchJobPositionDetails(companyId);


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
    Connect();
}



}
