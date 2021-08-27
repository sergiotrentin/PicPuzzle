/************************************************************************************************************
 * Developer: Minhas Kamal (IIT, DU, BSSE-0509)																*
 * Date: 03-Dec-2013 																						*
 * Total Lines: almost 2700																					*
 * Comment: This is where the game starts running.															*
*************************************************************************************************************/

package run.operation;

import java.io.IOException;

import javax.swing.JOptionPane;

import mainFrame.gui.*;


public class MainApp {
	public static void main(String args[]) throws IOException {
		
		Sessao sessao = Sessao.getInstance();
		
		/**///##using NIMBUS##///**/
		try{	
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Exception ex){
			//do nothing
		}
		
		Object[] options = { "Português", "English" };
	    int opt =  JOptionPane.showOptionDialog(null, "Bem vindo! Selecione a língua do jogo\n   Welcome! Select game language", "Language / Língua",
	          JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
	              null, options, options[0]);
	    
	    //seleciona a linguagem do jogo
	    if (opt == 0) {
	    	sessao.setLanguage("PT");
	    }else {
	    	sessao.setLanguage("EN");
	    }
		
		Frame frame = new Frame(1);
    	frame.setVisible(true);
    	frame.setJCBItemPic1Selected();
	}
}


