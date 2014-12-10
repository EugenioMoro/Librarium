package it.unisalento.Model;

public class CasaEd {
		
		private int id;
		private String nome;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		//da usare per caricare da db
		public CasaEd(int id, String nome){
			this.id=id;
			this.nome=nome;
		}
		
		
		//Da usare per definire nuova da interfaccia
		public CasaEd(String nome) {
			this.nome = nome;
		}
	
}
