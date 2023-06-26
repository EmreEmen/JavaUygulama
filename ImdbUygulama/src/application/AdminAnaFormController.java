package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.İsteMySQL.Util.VeriTabaniUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import com.İsteMySQL.Util.VeriTabaniUtil;

public class AdminAnaFormController {
	public AdminAnaFormController() {
		baglanti = VeriTabaniUtil.Baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonEkle;

    @FXML
    private Button ButtonGüncelle;

    @FXML
    private Button ButtonSil;

    @FXML
    private TableColumn<Kayitlar_Filmler, Integer> FilmPuan;

    @FXML
    private TableColumn<Kayitlar_Filmler, String> FilmYorum;

    @FXML
    private AnchorPane KullaniciAnchor;

    @FXML
    private TableView<Kayitlar_Filmler> anatablo;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private TableColumn<Kayitlar_Filmler, String> filmAd;

    @FXML
    private TableColumn<Kayitlar_Filmler, Integer> id;

    @FXML
    private TextField txtFilmAd;

    @FXML
    private TextField txtFilmPuan;

    @FXML
    private TextField txtfilmYorum;
    Connection baglanti = null;
    PreparedStatement sorguIfadesi=null;
    ResultSet getirilen = null;
    String sql;

 public void DegerlerGetir(TableView tablo,String sql) {
    	
    	 sql = "SELECT * FROM filmler"; 
    	 ObservableList<Kayitlar_Filmler> kayitlarliste= FXCollections.observableArrayList();    	     
    	     try {
				sorguIfadesi = baglanti.prepareStatement(sql);
			    ResultSet getirilen = sorguIfadesi.executeQuery();
			    while(getirilen.next()) {
			    	kayitlarliste.add(new Kayitlar_Filmler(getirilen.getInt("FilmID"),getirilen.getString("filmAd"),getirilen.getString("FilmYorum"),getirilen.getString("FilmPuan")));
			    	
			    }
			    id.setCellValueFactory(new PropertyValueFactory<>("id"));
			    filmAd.setCellValueFactory(new PropertyValueFactory<>("filmAd"));
			    FilmYorum.setCellValueFactory(new PropertyValueFactory<>("filmYorum"));
			    FilmPuan.setCellValueFactory(new PropertyValueFactory<>("filmPuan"));		
			    anatablo.setItems(kayitlarliste);}
			    catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage().toString());
			}
        	 
         }
    	     
 
    
    
    @FXML
    void ButtonEkle_OnClick(ActionEvent event) {              	   	    	       	    	
    	 sql = "select * from filmler where filmAd like '%"+txtFilmAd.getText()+"%'";  
          try { 
        		
        	sorguIfadesi = baglanti.prepareStatement(sql); 
        	ResultSet getirilen = sorguIfadesi.executeQuery();
           if(getirilen.next()) { 
        	   Alert alert =new Alert(AlertType.ERROR);
         	  alert.setTitle("Iste Otomasyon");
         	  alert.setHeaderText("Kayıt Var");
         	  alert.setContentText("Bu Film Listede var ");   	
         	 ButtonType btn2 = new ButtonType ("IPTAL",ButtonData.CANCEL_CLOSE);
         	  alert.getButtonTypes().setAll(btn2);
         	  alert.showAndWait();
        	 
         }else {
        	  sql = "insert into filmler(filmAd,FilmYorum,FilmPuan) values(?,?,?)";
        	  sorguIfadesi = baglanti.prepareStatement(sql);
              sorguIfadesi.setString(1,txtFilmAd.getText().trim());
              sorguIfadesi.setString(2,txtfilmYorum.getText().trim());
              sorguIfadesi.setString(3, txtFilmPuan.getText().trim());
              sorguIfadesi.executeUpdate();
         }
          } catch (Exception e) {
        	  
          }
          DegerlerGetir(anatablo,sql);
        	
    }

    @FXML
    void ButtonGüncelle_OnClick(ActionEvent event) {
    	  Alert alert =new Alert(AlertType.CONFIRMATION);
    	  alert.setTitle("Iste Otomasyon");
    	  alert.setHeaderText("Güncelle");
    	  alert.setContentText("Güncellemek istediğinize eminmisiniz");   	
    	  ButtonType btn1 = new ButtonType("GÜNCELLE");
    	  ButtonType btn2 = new ButtonType ("IPTAL",ButtonData.CANCEL_CLOSE);
    	  alert.getButtonTypes().setAll(btn1, btn2);
    	  Optional<ButtonType> sonuc= alert.showAndWait();
    	  if(sonuc.get()==btn1) {
    	        sql="update filmler set  FilmPuan=? where filmAd=? ";	
           try {
        	  sorguIfadesi = baglanti.prepareStatement(sql); 
              sorguIfadesi.setString(2,txtFilmAd.getText().trim());            
              sorguIfadesi.setString(1,txtFilmPuan.getText().trim());    
               sorguIfadesi.executeUpdate();
            } catch (Exception e) {
           	
           }
           sql="select * from filmler";
           DegerlerGetir(anatablo,sql);
    	  }
    }

    @FXML
    void ButtonSil_OnClick(ActionEvent event) {
    	  Alert alert =new Alert(AlertType.ERROR);
    	  alert.setTitle("Iste Otomasyon");
    	  alert.setHeaderText("SIL");
    	  alert.setContentText("Silmek istediğinize eminmisiniz");   	
    	  ButtonType btn1 = new ButtonType("SIL");
    	  ButtonType btn2 = new ButtonType ("IPTAL",ButtonData.CANCEL_CLOSE);
    	  alert.getButtonTypes().setAll(btn1, btn2);
    	  Optional<ButtonType> sonuc= alert.showAndWait();
    	  if(sonuc.get()==btn1) {
    	        sql="delete from filmler where filmAd=? and FilmYorum=? and FilmPuan=?";	
           try {
        	   sorguIfadesi = baglanti.prepareStatement(sql); 
              sorguIfadesi.setString(1,txtFilmAd.getText().trim());
              sorguIfadesi.setString(2,txtfilmYorum.getText().trim());  
              sorguIfadesi.setString(3,txtFilmPuan.getText().trim());    
               sorguIfadesi.executeUpdate();
            } catch (Exception e) {
           	
           }
           sql="select * from filmler";
           DegerlerGetir(anatablo,sql);
    	  }
    }

    @FXML
    void tablewiew_MouseClick(MouseEvent event) {
            Kayitlar_Filmler kayit = new Kayitlar_Filmler();
            kayit = (Kayitlar_Filmler) anatablo.getItems().get(anatablo.getSelectionModel().getSelectedIndex());
            txtFilmAd.setText(kayit.getFilmAd());
            txtfilmYorum.setText(kayit.getFilmYorum());
            txtFilmPuan.setText(kayit.getFilmPuan());
            
    }

    @FXML
    void initialize() {
    	  sql="select * from filmler";
    	DegerlerGetir(anatablo,sql);
    }

}

	

