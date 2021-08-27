/****************************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																				*
* Date: 01-Jan-2014; Modified: 14-Jul-2014																					*
* Comment: Shows necessary information over the software, like- weakness, developer's feeling, background etc				*									
*****************************************************************************************************************************/

package help.about.gui;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.*;

import run.operation.ManipuladorArquivo;

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
	private String text[];
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##
	 * @throws IOException ***/
	public About() throws IOException {
		//write your message here
		text = ManipuladorArquivo.getTextAbout();
		
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
		
		jTextAreaNotification.setText(text[0]);
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
		setTitle(text[1]);
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

	/********* Main Method 
	 * @throws IOException *********/
	public static void main(String args[]) throws IOException {
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
