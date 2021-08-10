package run.operation;

//Classe Singleton para ser usada globalmente
public class Sessao {

	private static Sessao instance; //cont�m a instacia da classe
	private String language = "EN"; //PT(Portugu�s) - EN(Ingl�s)
	private boolean sounds = true; //Define se o som do jogo est� ativado ou desativado
	
	//Contrutor privado para substituir o padr�o
	private Sessao() {}
	
	public static Sessao getInstance() {
		if (instance == null) {
			instance  = new Sessao();
		}
		
		return instance;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isSounds() {
		return sounds;
	}

	public void setSounds(boolean sounds) {
		this.sounds = sounds;
	}

}
