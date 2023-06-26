package application;


import java.net.URL;
import java.util.ResourceBundle;
import com.İsteMySQL.Util.VeriTabaniUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import com.İsteMySQL.Util.VeriTabaniUtil;

public class AdminGirisController {
	public AdminGirisController() {
		baglanti = VeriTabaniUtil.Baglan();
	}
     
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label KullaniciGirisLabel;

    @FXML
    private AnchorPane adminGirisAnchor;

    @FXML
    private Button girisButton;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtSifre;
     
      Connection baglanti = null;
      PreparedStatement sorguIfadesi=null;
      ResultSet getirilen = null;
      String sql;
  
      @FXML
    void girisButton_OnClick(ActionEvent event)  {
    	  
            sql="select * from login where kul_ad=? and sifre=?";  
        	try {
           sorguIfadesi= baglanti.prepareStatement(sql);
            sorguIfadesi.setString(1,txtID.getText().trim());
            sorguIfadesi.setString(2,VeriTabaniUtil.MD5Sifrele(txtSifre.getText().trim()));  	   	
        	ResultSet getirilen=sorguIfadesi.executeQuery();     	
        
        	if(getirilen.next()) {	try {
        		Stage stage1 = new Stage();
    			AnchorPane anchor = (AnchorPane)FXMLLoader.load(getClass().getResource("AdminAnaForm.fxml"));
    			Scene scene = new Scene(anchor,800,600);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			stage1.setScene(scene);
    			stage1.show();
    			stage1.setTitle("Admin");
    			stage1.centerOnScreen();
    		} catch(Exception e) {
    			e.printStackTrace();
    		
    		}
        		
        	} else {
        	
        		KullaniciGirisLabel.setText("Kullanıcı adı veya Sifre Hatalı");
        	}
            	      	
			} catch (SQLException e) {			
				e.printStackTrace();
		
        	}	        	
    }

    @FXML
    void initialize() {
       

    }

}


