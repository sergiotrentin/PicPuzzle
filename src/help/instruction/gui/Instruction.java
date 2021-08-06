/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 21-Dec-2013; Modified: 14-Jul-2014																					*
* Comment: This will make a window that will show a manual of how the software should be used.							 	*									
****************************************************************************************************************************/

package help.instruction.gui;


import java.awt.event.ActionEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class Instruction extends JFrame {
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JPanel jPanelMain;
	private JScrollPane jScrollPane;
	private JTextArea jTextAreaNotification;
	private JButton jButtonOK;
	
	//operational variables
	private String notification;
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
	public Instruction() {
		//write your message here
		this.notification="É muito fácil jogar o game-\n\n" +
			"#  Pressione 'ENTER' para iniciar o jogo.\n" +
			"#  Use as setas do teclado para mover as peças para cima, baixo, direita e esquerda.\n" +
			"#  A tecla 'P' faz com que o jogo pause.\n" +
			"#  Também existe uma lista dos 5 melhores tempos registrados. " +
			"Você pode adicionar seu nome lá quando derrotar uma das 5 melhores marcas.\n" +
			"#  Você também tem a opção de recomeçar as marcas (confimando no 'OK' 3 vezes).\n" +
			"#  Existem diversas imagens para jogar, basta selecionar no menu qual lhe agrada mais.\n" +
			"#  Existe opções de reiniciar o jogo, ver o perfil do desenvolvedor do jogo, entre outros.\n" +
			"#  Existe também a opção de resolver o quebra cabeça, onde uma IA irá resolver para você" +
			" a figura passo a passo. Podendo te ajudar e ensinar como resolver quebra-cabeças. \n" +
			"\n\n\n@  Espero que goste do jogo. Boa Sorte e divirta-se!";
		
		initialComponent();
	}
	
	public Instruction(String notification) {
		this.notification = notification;
		
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
		jPanelMain=new JPanel();
		jScrollPane=new JScrollPane();
		jTextAreaNotification=new JTextArea();
		jButtonOK = new JButton();
		// End of Initialization																			#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
		jPanelMain.setBackground(new java.awt.Color(240, 240, 240));
        jPanelMain.setBounds(0, 0, 350, 350);
        jPanelMain.setLayout(null);
		
		jScrollPane.setViewportView(jTextAreaNotification);
		jScrollPane.setBounds(10, 10, 330, 330);
		jScrollPane.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		jTextAreaNotification.setText(notification);
		jTextAreaNotification.setForeground(new java.awt.Color(1, 1, 1));
		jTextAreaNotification.setFont(new java.awt.Font("Lucida Bright", 0, 14)); // NOI18N
		jTextAreaNotification.setEditable(false);
		jTextAreaNotification.setCaretPosition(0);
		jTextAreaNotification.setLineWrap(true);
		jTextAreaNotification.setWrapStyleWord(true);
        
        jButtonOK.setText("OK");
        jButtonOK.setBounds(280, 360, 60, 30);
        jButtonOK.setBackground(new java.awt.Color(240, 240, 240));
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
        jPanelMain.add(jScrollPane); 
		// End of Adding Components 																		#_______A_______#

        
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/help/instruction/pictures/IconInstruction.png")).getImage());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Instruções");
		setBounds(200, 150, 355, 430);
		setLayout(null);
		setResizable(false);
		add(jPanelMain); add(jButtonOK);
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
		Instruction gui = new Instruction();
		gui.setVisible(true);
	}

	//**
	// Auxiliary Methods 																					#********AM*******#
	//**

	// End of Auxiliary Methods 																			#________AM_______#
}
