package tuoi;

import java.io.Serializable;

public class Tuoi implements Serializable{
	String id;
	String Tuoi;
	String NoiDung;
	Integer TT;

	public Tuoi() {
	}

	public Tuoi(String id, String tuoi, String noiDung, Integer tT) {
		super();
		this.id = id;
		Tuoi = tuoi;
		NoiDung = noiDung;
		TT = tT;
	}
	
	public void setTuoi(String id, String tuoi, String noiDung, Integer tT) {
		this.id = id;
		Tuoi = tuoi;
		NoiDung = noiDung;
		TT = tT;
	}


	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTuoi() {
		return Tuoi;
	}



	public void setTuoi(String tuoi) {
		Tuoi = tuoi;
	}



	public String getNoiDung() {
		return NoiDung;
	}



	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}



	public Integer getTT() {
		return TT;
	}



	public void setTT(Integer tT) {
		TT = tT;
	}



	public void show() {
		System.out.println("-----TestShow----- ");
		System.out.println("id: "+ this.id);
		System.out.println("Tuoi: "+ this.Tuoi);
		System.out.println("NoiDung: "+ this.NoiDung);
		System.out.println("TT: "+ this.TT);
		System.out.println("----------------- ");
		
	}
}
