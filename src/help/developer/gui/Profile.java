/************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)							*
* Date: 17-Dec-2013, modified: 25-Dec-2013, modified: 13-May-2014		*
* Comment: This GUI make a window that shows a simple developer profile *
************************************************************************/

package help.developer.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import run.operation.Sessao;

@SuppressWarnings("serial")
public class Profile extends JFrame implements Runnable{
	//**
	// Variable Declaration 																				#*******D*******#
	//**
	private JLabel jLabelMain;
	private JButton jButtonPhoto;
	private JLabel jLabelName, jLabelInstitute, jLabelDate, jLabelEmail;
    private JLabel jLabelPhoto[];
    private JTextArea jTextAreaInformation;
    private Sessao sessao = Sessao.getInstance();
    
    //other variables
    private String date;			//Change it accordingly
    private int numberOfPhotos;		//number of profile photos
    private int position;			//position determiner 
    private boolean isStanding;		//picture show is standing or not
    private Thread thread;			//main thread
    private String textGame[]; //text Profile
	// End of Variable Declaration 																			#_______D_______#

	/***##Constructor##***/
    public Profile() {
    	this.date="07-Jun-2014";
    	numberOfPhotos=7;
    	isStanding=false;
    	position=0;
    	
    	initialComponent();
    }
    /***##Constructor##***/
    public Profile(String date) {
    	this.date=date;
    	numberOfPhotos=7;
    	isStanding=false;
    	position=0;

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
		jLabelMain = new JLabel();
    	jButtonPhoto = new JButton();
        jLabelName = new JLabel();
        jLabelInstitute = new JLabel();
        jLabelDate = new JLabel();
        jLabelEmail = new JLabel();
        jLabelPhoto = new JLabel[numberOfPhotos+1];	//we need one extra photo
     
        textGame = languageTextSelection(sessao.getLanguage());
        
        jTextAreaInformation = new JTextArea();
		// End of Initialization																			#_______I_______#

        //**
		// Setting Bounds and Attributes of the Elements 													#*******S*******#
		//**
        jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/help/developer/pictures/BackGround.jpg")));
        jLabelMain.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelMain.setBounds(0, 0, 525, 340);
        jLabelMain.setLayout(null);

        jLabelName = configLabelComponent(jLabelName,"Minhas Kamal ",textGame[1],20, 60, 120, 20);
        jLabelInstitute = configLabelComponent(jLabelInstitute,"Institute of Information Technology, DU ",textGame[2], 20, 90, 290, 20);
        jLabelDate = configLabelComponent(jLabelDate,textGame[3]+": " + date,textGame[4], 20, 120, 200, 20);
        jLabelEmail = configLabelComponent(jLabelEmail,"Email: minhaskamal024@gmail.com", null, 20, 150, 260, 20);

        jButtonPhoto.setToolTipText(textGame[5]);
        jButtonPhoto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPhoto.setLayout(null);
        jButtonPhoto.setBounds(328, 15, 180, 195);
        jButtonPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jPanelPhotoActionPerformed(evt);
            }
        });
        
        //photos
        for(int i=0; i<numberOfPhotos; i++){
        	jLabelPhoto[i]=new JLabel();
        	jLabelPhoto[i].setBounds(i*181, 0, 180, 195);
        	jLabelPhoto[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/help/developer/pictures/" +
        			"MinhasKamal" + (i+1) + ".png")));
        }
        jLabelPhoto[numberOfPhotos]=new JLabel();
    	jLabelPhoto[numberOfPhotos].setBounds(numberOfPhotos*181, 0, 180, 195);
    	jLabelPhoto[numberOfPhotos].setIcon(new javax.swing.ImageIcon(getClass().getResource("/help/developer/pictures/" +
    			"MinhasKamal" + 1 + ".png")));
        
        //text area
        jTextAreaInformation.setEditable(false);
        jTextAreaInformation.setBackground(new Color(0,0,0,0));
        jTextAreaInformation.setColumns(20);
        jTextAreaInformation.setFont(new Font("Lucida Bright", 0, 10)); // NOI18N
        jTextAreaInformation.setRows(5);
        jTextAreaInformation.setText(textGame[6]);
       //jTextAreaInformation.setBorder((new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        jTextAreaInformation.setBounds(10, 225, 500, 75);
        
		// End of Setting Bounds and Attributes 															#_______S_______#

     	//**
  		// Adding Components 																				#*******A*******#
  		//**
        //adding photos to the photo panel
        for(int i=0; i<numberOfPhotos+1; i++){
        	jButtonPhoto.add(jLabelPhoto[i]);
        }
       
        //adding features to the main panel
        jLabelMain.add(jLabelName); jLabelMain.add(jLabelInstitute); jLabelMain.add(jLabelDate); jLabelMain.add(jLabelEmail);
        jLabelMain.add(jButtonPhoto); jLabelMain.add(jTextAreaInformation);
		// End of Adding Components 																		#_______A_______#

		//Setting criteria of the Frame//
        setIconImage(new ImageIcon(getClass().getResource("/help/developer/pictures/IconDeveloper.png")).getImage());
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); 
        setTitle(textGame[7]);
        setLayout(null);
        setSize(525, 340);
        setLocation(250, 200);
        setResizable(false);
        add(jLabelMain);
        setAlwaysOnTop(true);
        
        
        //*Thread*//
        thread = new Thread(this);
        thread.start();
	}
	
	/**
	 * Run Method 
	 */
	@SuppressWarnings("static-access")
	public void run() {
		int criticalPoint=numberOfPhotos*(-180) - numberOfPhotos - 1;
		
		while(true){
			if(!isStanding){
				position--;
			}
			
			for(int i=0; i<numberOfPhotos+1; i++){
				jLabelPhoto[i].setLocation(position + (i*181), 0);			
			}
			
			try {
				thread.sleep(4);
				for(int i=0; i<numberOfPhotos; i++){
					if(position == i*(-180) - i - 1){
						thread.sleep(900);
						break;
					}
				}
			}catch(Exception e) {
				//do nothing
			}
			
			if(position == criticalPoint){
				position=0;
			}
		}
	}
	
	
	//**
	// Action Events 																						#********AE*******#
	//**
	private void jPanelPhotoActionPerformed(java.awt.event.ActionEvent evt) {  
		if(!isStanding){
			isStanding=true;
		}else{
			isStanding=false;
		}
    }
	// End of Action Events 																				#________AE_______#

	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		Profile gui = new Profile();
		gui.setVisible(true);
	}

	/*Funcitons Aux*/
	public JLabel configLabelComponent(JLabel label, String text, String ToolText, 
									   int x, int y, int width, int height) {
		
		label.setFont(new java.awt.Font("Lucida Bright", 2, 15)); //Font Padrão
		
		if(text != null) label.setText(text);
		if(ToolText != null) label.setToolTipText(ToolText);
		if((x != 0) && (y != 0) && (width != 0) && (height != 0)) label.setBounds(x, y, width, height);
		
		return label;
	}
	
	
	public String[] languageTextSelection(String language) {
  		String[] text = new String[8];
  		
  		if (language == "PT") {
  			text[0] = "07-Jun-2014";
  			text[1] = "Nome";
  			text[2] = "Graduação";
  			text[3] = "Data";
  			text[4] = "Data de Conclusão";
  			text[5] = "Click para pausar";
  			text[6] = "   Olá, é Minhas Kamal, minha cidade natal é Jhenaidah. Comecei minha vida de estudo na " +
  	        		"\n   Arbi-Medium School (como Madrasa) chamada Al-Faruq Academy e continuei lá até " +
  	        		"\n   a classe 7. Concluí meu SSC na Kanchan Nagar Model High School & College e HSC " +
  	        		"\n   da Jhenaidah Govt. K.C. College. Agora estou no curso BSSE no IIT, DU. ";
  			text[7] = "Perfil do Desenvolvedor";
  		}else{
  			text[0] = "__-__-20__";
  			text[1] = "Name";
  			text[2] = "Educational Institute";
  			text[3] = "Date";
  			text[4] = "Date of Completion";
  			text[5] = "Click to pause";
  			text[6] = "   Hi, its Minhas Kamal, my home town is Jhenaidah. I started my study life by attending an " +
  	        		"\n   Arbi-Medium  School (like Madrasa) named  Al-Faruq  Academy &  continued there upto " +
  	        		"\n   class 7. I completed my SSC from Kanchan Nagar Model High School & College and HSC " +
  	        		"\n   from  Jhenaidah Govt. K.C. College. Now I am in  BSSE  course at  IIT, DU. ";
  			text[7] = "Developer's Profile";
  		}
  		
		return text;
  	}
	
}
