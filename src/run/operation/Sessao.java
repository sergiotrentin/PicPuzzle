package run.operation;

//Classe Singleton para ser usada globalmente
public class Sessao {

	private static Sessao instance; //contém a instacia da classe
	private String language = "EN"; //PT(Português) - EN(Inglês)
	private boolean sounds = true; //Define se o som do jogo está ativado ou desativado
	
	//Contrutor privado para substituir o padrão
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
