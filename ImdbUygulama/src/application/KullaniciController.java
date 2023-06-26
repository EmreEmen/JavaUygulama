package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.İsteMySQL.Util.VeriTabaniUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class KullaniciController {
	public KullaniciController() {
		baglanti = VeriTabaniUtil.Baglan();
	}
	    Connection baglanti = null;
	    PreparedStatement sorguIfadesi=null;
	    ResultSet getirilen = null;
	    String sql;
	  
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TableColumn<Kayitlar_Filmler, String> FilmAdColumn;

	    @FXML
	    private TableColumn<Kayitlar_Filmler, Integer> FilmPuanColumn;

	    @FXML
	    private AnchorPane KullaniciAnchor;

	    @FXML
	    private TableColumn<Kayitlar_Filmler, Integer> filmID;

	    @FXML
	    private TableColumn<Kayitlar_Filmler, String> filmYorumColumn;

	    @FXML
	    private TableView<Kayitlar_Filmler> tablewiew1;

	    @FXML
	    private TextField txtFilmAdi;     

    @FXML
    void txt_Arama_Keypressed(KeyEvent event) {
    	if(txtFilmAdi.getText().equals("")) {
    		sql="select * from filmler";
    	}else {
    			 sql = "select * from filmler where filmAd like '%"+txtFilmAdi.getText()+"%'";  
    	}
    	
    	   	
    	DegerlerGetir(tablewiew1,sql);
    }   
public void DegerlerGetir(TableView tablo,String sql) {
    	
    
    	ObservableList<Kayitlar_Filmler> kayitlarliste= FXCollections.observableArrayList();    	     
    	     try {
				sorguIfadesi = baglanti.prepareStatement(sql);
			    ResultSet getirilen = sorguIfadesi.executeQuery();
			    while(getirilen.next()) {
			    	kayitlarliste.add(new Kayitlar_Filmler(getirilen.getInt("FilmID"),getirilen.getString("filmAd"),getirilen.getString("FilmYorum"),getirilen.getString("FilmPuan")));
			    }
			
			    filmID.setCellValueFactory(new PropertyValueFactory<>("id"));
			    FilmAdColumn.setCellValueFactory(new PropertyValueFactory<>("filmAd"));
			    filmYorumColumn.setCellValueFactory(new PropertyValueFactory<>("filmYorum"));
			    FilmPuanColumn.setCellValueFactory(new PropertyValueFactory<>("filmPuan"));		
			    tablewiew1.setItems(kayitlarliste);
    	     } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage().toString());
			}
        	 
         }
    @FXML
    void initialize() {
           sql="select * from filmler";
    	DegerlerGetir(tablewiew1,sql);

    }

}


