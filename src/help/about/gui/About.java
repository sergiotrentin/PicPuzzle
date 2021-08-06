/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 01-Jan-2014; Modified: 14-Jul-2014																					*
* Comment: Shows necessary information over the software, like- weakness, developer's feeling, background etc				*									
*****************************************************************************************************************************/

package help.about.gui;

import java.awt.event.ActionEvent;
import javax.swing.*;


@SuppressWarnings("serial")
public class About extends JFrame {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMain;
	private JScrollPane jScrollPane;
	private JTextArea jTextAreaNotification;
	private JButton jButtonOK;

	//operational variable
	private String data;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public About() {
		//write your message here
		data="@ Esse programa est� aberto a todos. Podendo ser modificado e alterado. \n\n" +
				"Demorou cerca de um m�s para desenvolver a parte central do jogo. As extens�es " +
				"foram criadas separadamente em fases. J� passaram quase 6 meses que comecei este projeto. " +
				"estou trabalhando continuamente para melhorar suas funcionalidades. Como resultado " +
				"eu tinha come�ado com um pequeno projeto e finalmente cresceu como " +
				"um programa complexo. O programa cont�m 13 classes, " +
				"cerca de 2.800 linhas de c�digo, 204 imagens e 12 sons. Agora, esse programa est� em seu sexto est�gio " +
				"de desenvolvimento. Agora estou trabalhando com tr�s outros projetos maiores." +
				"\n\nAgrade�o muito aos meus professores, amigos e tamb�m a minha prima por encorajar e " +
				"aconselhar-me." +
				"\n\nAlgu�m pode n�o acha o programa muito bom, mas como um estudante de Java e um programador ing�nuo " +
				"pode ser que isso tenha sido o melhor que pude fazer com meu conhecimento limitado. \n\n**Obrigado**\n\n" +
				"                                                                                   -Desenvolvedor\n" +
				"                                                                                   7-Jun-2014";

		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																					#*******I*******#
		//**
		jLabelMain=new JLabel();
		jScrollPane = new JScrollPane();
		jTextAreaNotification = new JTextArea();
		jButtonOK = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help/about/pictures/AboutBackGround.jpg")));
        jLabelMain.setBounds(0, 0, 370, 425);
        jLabelMain.setLayout(null);
        
		
		jScrollPane.setViewportView(jTextAreaNotification);
		jScrollPane.setBounds(10, 10, 348, 356);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		jTextAreaNotification.setText(data);
        jTextAreaNotification.setForeground(new java.awt.Color(1, 1, 1));
        jTextAreaNotification.setSelectedTextColor(new java.awt.Color(1, 1, 1));
        jTextAreaNotification.setSelectionColor(new java.awt.Color(80, 0, 80));
        jTextAreaNotification.setFont(new java.awt.Font("Lucida Bright", 0, 13)); // NOI18N
//        jTextAreaNotification.setEditable(false);
        jTextAreaNotification.setCaretPosition(0);
        jTextAreaNotification.setLineWrap(true);
        jTextAreaNotification.setWrapStyleWord(true);
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(297, 380, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(212, 227, 250));
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });
        jButtonOK.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
	    	put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "ENTER_pressed");
	    jButtonOK.getActionMap().put("ENTER_pressed", new AbstractAction() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	jButtonOKActionPerformed(evt);
	        }
	    });
		// End of Setting Bounds and Attributes 															#_______S_______#

		//**
		// Adding Components 																				#*******A*******#
		//**
	    jLabelMain.add(jScrollPane); 
        jLabelMain.add(jButtonOK); 
		// End of Adding Components 																		#_______A_______#

		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/help/about/pictures/IconAbout.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(200, 150, 375, 450);
		setTitle("Sobre");
		setLayout(null);
		add(jLabelMain);
		setResizable(false);
		setFocusable(true);
		setAlwaysOnTop(true);
	}

	//**
	// Action Events 																						#********AE*******#
	//**
	private void jButtonOKActionPerformed(ActionEvent evt){	
		dispose();
	}
	
	// End of Action Events 																				#________AE_______#

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		About gui = new About();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**

	// End of Auxiliary Methods 																			#________AM_______#
}