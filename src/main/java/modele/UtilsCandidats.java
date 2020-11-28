package modele;

import java.util.List;

public class UtilsCandidats {
	private List<Couple> listeCouples;
	public UtilsCandidats(Candidats candidats) {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public List<Couple> getListeCouples() {
		return listeCouples;
	}





	class Couple {
		private int c1;
		private int c2;
		public void setC1(int c1) {this.c1=c1;}
		public void setC2(int c2) {this.c2=c2;}
		public int getC1() {return this.c1;}
		public int getC2() {return this.c2;}
		
	}
	
	
	
}
