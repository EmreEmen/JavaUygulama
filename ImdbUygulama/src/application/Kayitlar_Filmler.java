package application;



public class Kayitlar_Filmler {
	private int id;
	private String filmAd;
	private String filmYorum;
	private String filmPuan;	
     Kayitlar_Filmler(){
		
	      }
	
	Kayitlar_Filmler(int id,String filmAd ,String filmYorum,String filmPuan ){
		this.id = id;
		this.filmAd=filmAd;
		this.filmYorum=filmYorum;
		this.filmPuan=filmPuan;		
	
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilmAd() {
		return filmAd;
	}
	public void setFilmAd(String filmAd) {
		this.filmAd = filmAd;
	}
	public String getFilmYorum() {
		return filmYorum;
	}
	public void setFilmYorum(String filmYorum) {
		this.filmYorum = filmYorum;
	}
	public String getFilmPuan() {
		return filmPuan;
	}
	public void setFilmPuan(String filmPuan) {
		this.filmPuan = filmPuan;
	}			
}
