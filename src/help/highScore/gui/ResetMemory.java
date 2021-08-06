/**
 * Developer: Minhas Kamal (IIT.DU, BSSE-0509)
 * Date: 19-Dec-2013
 * Attribute: Clears a file after clicking OK 3 times
**/

package help.highScore.gui;

import help.highScore.operation.FileInputOutput;

import java.awt.event.ActionEvent;

import javax.swing.*;

import sound.operation.SoundPlayer;


@SuppressWarnings("serial")
public class ResetMemory extends JDialog {
	/**
	 * Declaration///////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	private JLabel jLabelMessage;
	private JButton jButtonOK;
	private JButton jButtonCancel;
	private JPanel jPanelMain;
	int i;
	
	
	/**
	 * Constructor////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	public ResetMemory(){
		i=1;
		
		initComponent();
	}
	
	//doing initial work
	private void initComponent(){
		/**
		 * Initialization////////////////////////////////////////////////////////////////////////////////////////////
		**/
		jLabelMessage=new JLabel();
		jButtonOK=new JButton(); 
		jButtonCancel=new JButton();
		
		jPanelMain=new JPanel();
		
		
		
		/**
		 * Setting position & size
		**/
		jLabelMessage.setText("Deseja reiniciar todos os registros?");
		jLabelMessage.setFont(new java.awt.Font("Lucida Bright", 0, 15));
		jLabelMessage.setForeground(new java.awt.Color(1,1,1));
		jLabelMessage.setBounds(15, 40, 270, 35);
		
		jButtonOK.setText("Sim");
		jButtonOK.setBounds(190, 125, 65, 25);
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
		
		jButtonCancel.setText("N�o");
		jButtonCancel.setBounds(260, 125, 65, 25);
		jButtonCancel.setBorder(null);
		jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		dispose();
        		//jButtonCancelActionPerformed(evt);
        	}
        });
		
		/**
		 * criteria of the frame
		**/
        setIconImage(new ImageIcon(getClass().getResource("/help/highScore/pictures/IconResetMemory.png")).getImage());
		setLocation(300, 200);
		setSize(350, 200);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		add(jPanelMain);
		setModal(true);
		setResizable(false);
		setTitle("Reiniciar Mem�ria");
        /**
         * The main panel declaration and adding characteristics//////////////////////////////////////////////////////////////
        **/
        jPanelMain.setBackground(new java.awt.Color(240,240,240));
        jPanelMain.setBounds(0, 0, 350, 200);
        jPanelMain.setLayout(null);
        
        //ADD//
        jPanelMain.add(jLabelMessage); jPanelMain.add(jButtonOK); jPanelMain.add(jButtonCancel);
        /**
         * The main panel ends
        **/
	}
	
	/**
	 * Actions//////////////////////////////////////////////////////////////
	**/
	private void jButtonOKActionPerformed(ActionEvent evt){
		if (i==1) {
			new FileInputOutput().fileWriter("PicturePuzzle/HighScore.png", new help.highScore.operation.FileIOOperation().defaultInfo());
			
			jPanelMain.setBackground(new java.awt.Color(240,240,240));
			jLabelMessage.setForeground(new java.awt.Color(0, 0, 0));
			jLabelMessage.setFont(new java.awt.Font("Lucida Bright", 0, 15));
			jLabelMessage.setText("Registros Reiniciados!");	
			
			//new SoundPlayer().playSound("sound/sounds/MemoryCleared.wav");
			
			jButtonCancel.setVisible(false);
		}else {
			dispose();
		}
		i++;
	}
	
	private void jButtonCancelActionPerformed(ActionEvent evt){
		dispose();
	}
	
	
	
	/**
	 * The main method////////////////////////////////////////////////////////////////////////////////////////////////////////
	**/
	public static void main(String args[]) {
		/**/
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ResetMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ResetMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ResetMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ResetMemory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		/**/
		
		ResetMemory memoryClear = new ResetMemory();
		memoryClear.setVisible(true);
	}

}