/************************************************************************************************************
* Developer: Minhas Kamal(BSSE-0509, IIT, DU)																*
* Date: 14-Apr-2014																							*
* Comment: This is my second game using graphics. I have started & finished the core work in my second 		*
* 	semester final exam(Nov-2013). I acquired a brand new experience developing this project. Now it has 	*
* 	grown a lot and has become very complex. It was really fun, surely more than playing it!				*
*************************************************************************************************************/


package mainFrame.gui;

import help.about.gui.About;
import help.developer.gui.Profile;
import help.highScore.gui.HighScore;
import help.highScore.gui.ResetMemory;
import help.instruction.gui.Instruction;
import help.notifications.message.gui.Message;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

import robot.operation.PuzzleSolver;
import run.operation.ManipuladorArquivo;
import run.operation.Sessao;
import sound.operation.SoundPlayer;

@SuppressWarnings("serial")
public class Frame extends JFrame  implements Runnable, KeyListener{
	//**
	// Variable Declaration																					#*******D*******#
	//**
	
	//main label
	private JLabel jLabelMain;	
	
	//main menu bar
	private JMenuBar jMenubar;
	//menus
    private JMenu jMenuOption, jMenuPicture, jMenuHelp;
    //menu items
    private JCheckBoxMenuItem jCBItemPic[] ;
    private JMenuItem jMenuItemSolvePuzzle, jMenuItemRestart, jMenuItemHighScore, jMenuItemMuteUnmute;
    private JMenuItem jMenuItemAbout, jMenuItemDeveloper, jMenuItemInstruction, jMenuItemResetMemory, jMenuItemExit;
	
    private JLabel jLabelPrompt;
	private JLabel jLabelFrame, jLabelPic;
	private JLabel jLabelPicPart[][];	//five rows & four columns
	
	private JLabel jLabelLeft, jLabelRight, jLabelUp, jLabelDown;	//boarders
	private JLabel jLabelTime;
	
	private ImageIcon iconPic;
	private ImageIcon icon[][];	//five rows & four columns
	
	private ImageIcon icon0;	//empty icon
	private ImageIcon iconLeft, iconRight, iconUp, iconDown;	//boarder of picture
	
	JLabel jLabelTemp;	//needed to swap images
	
	//other variables
	private String minute, second, milSecond;
	private Boolean start, pause, end;	//game status
	private boolean robot;
	//main thread
	private Thread thread;
	//time in miliSecond
	private int timeMili;	
	//number of pictures
	private int numOfPictures;
	//picture number
	private int pictureNumber;	
	//sound player
	private SoundPlayer soundPlayer;
	//End of Variable Declaration																			#_______D_______#
    
	private Sessao sessao = Sessao.getInstance();
	
	private String[] textGame;
	
    
    //**Constructor**//
    public Frame(int pictureNumber) throws IOException{
    	timeMili=0;
    	minute="00";
		second="00";
		milSecond="00";
		start=false;
		pause=false;
		end=false;
		robot=false;
		
    	numOfPictures = 8;
    	
    	this.pictureNumber=pictureNumber;
    	InitialComponent();
    	//soundPlayer.playSound("sound/sounds/NewWindowPopup.wav");
    }
    
    
    /**
	 * Method for Initializing all the GUI variables, placing them all to specific space on the frame and adding action
	 * listener to them. Also specifies criteria of the main frame.
     * @throws IOException 
	 */
    private void InitialComponent() throws IOException{
		//**
		// Initialization 																					#*******I*******#
		//**
    	//main label
		jLabelMain=new JLabel(); 
        
        //main menu bar
		jMenubar=new JMenuBar();
		//menues
		jMenuOption=new JMenu(); jMenuPicture=new JMenu(); jMenuHelp=new JMenu();
		//menu items
		jCBItemPic = new JCheckBoxMenuItem[numOfPictures];
		jMenuItemSolvePuzzle=new JMenuItem(); jMenuItemRestart=new JMenuItem(); jMenuItemHighScore=new JMenuItem();
		jMenuItemAbout=new JMenuItem(); jMenuItemDeveloper=new JMenuItem(); jMenuItemInstruction=new JMenuItem();
		jMenuItemResetMemory=new JMenuItem(); jMenuItemExit=new JMenuItem(); jMenuItemMuteUnmute = new JMenuItem();
		
		
		jLabelPrompt=new JLabel(); jLabelFrame=new JLabel(); jLabelPic=new JLabel();
		jLabelPicPart = new JLabel[5][4];
		
		jLabelLeft=new JLabel(); jLabelRight=new JLabel(); jLabelUp=new JLabel(); jLabelDown=new JLabel();
		jLabelTime=new JLabel();
				
		iconPic=new ImageIcon(getClass().getResource("/mainFrame/pictures/pic"+pictureNumber+"/MiniPic"+pictureNumber+".jpg"));
		icon = new ImageIcon[5][4];
		 	
		icon0=new ImageIcon();

			
		//*Thread*//
        thread = new Thread(this);
        
        //sound player
        soundPlayer = new SoundPlayer();
    	//End of Initialization 																			#_______I_______#
		
		//**
		// Setting Bounds and Attributes of the Elements													#*******S*******#
		//**
		//main label
		jLabelMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/BackGround1.png")));
        jLabelMain.setBounds(0, 0, 760, 570);
        jLabelMain.setLayout(null);
        
        //Get The Text game Language
        textGame = ManipuladorArquivo.getTextFrame();
       
    	jMenuOption.setText(textGame[0]);
        jMenuOption.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconOption.png")));
        jMenuOption.setFont(new java.awt.Font("Lucida Bright", 1, 13));
        jMenuPicture.setText(textGame[1]);
        jMenuPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconPicture.png")));
        jMenuPicture.setFont(new java.awt.Font("Lucida Bright", 1, 13));
        jMenuHelp.setText(textGame[2]);
        jMenuHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconHelp.png")));
        jMenuHelp.setFont(new java.awt.Font("Lucida Bright", 1, 13));
        
	       
        //check box picture menu items
        for(int i=0; i<numOfPictures; i++){
        	jCBItemPic[i]=new JCheckBoxMenuItem();
        	jCBItemPic[i].setText(textGame[3]+(i+1));
        	jCBItemPic[i].setFont(new java.awt.Font("Lucida Bright", 2, 13));
        	jCBItemPic[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/pic" +
        													(i+1)+"/MicroPic"+(i+1)+".jpg")));
        }
        jCBItemPic[0].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[1].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[2].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[3].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(3);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[4].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(4);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[5].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(5);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[6].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(6);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        jCBItemPic[7].addActionListener(new java.awt.event.ActionListener() {
    		public void actionPerformed(java.awt.event.ActionEvent evt) {
    			try {
					jCBItemPicActionPerformed(7);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	});
        
        
        //menu items
        jMenuItemSolvePuzzle.setText(textGame[4]);
        jMenuItemSolvePuzzle.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemSolvePuzzle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconSolvePuzzle.png")));
        jMenuItemSolvePuzzle.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jMenuItemSolvePuzzleActionPerformed(evt);
        	}
        });
        jMenuItemRestart.setText(textGame[5]);
        jMenuItemRestart.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconRestart.png")));
        jMenuItemRestart.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		try {
					jMenuItemRestartActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        jMenuItemHighScore.setText(textGame[6]);
        jMenuItemHighScore.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemHighScore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconHighScore.png")));
        jMenuItemHighScore.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jMenuItemHighScoreActionPerformed(evt);
        	}
        });
        jMenuItemMuteUnmute.setText(textGame[18]);
        jMenuItemMuteUnmute.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemMuteUnmute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconSound.png")));
        jMenuItemMuteUnmute.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jMenuItemMuteUnmute(evt);
        	}
        });
        jMenuItemAbout.setText(textGame[7]);
        jMenuItemAbout.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconAbout.png")));	
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					jMenuItemAboutActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jMenuItemDeveloper.setText(textGame[8]);
        jMenuItemDeveloper.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemDeveloper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconDeveloper.png")));	
        jMenuItemDeveloper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try {
					jMenuItemDeveloperActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        jMenuItemInstruction.setText(textGame[9]);
        jMenuItemInstruction.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemInstruction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconInstruction.png")));
        jMenuItemInstruction.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		jMenuItemInstructionActionPerformed(evt);
        	}
        });
        jMenuItemResetMemory.setText(textGame[10]);
        jMenuItemResetMemory.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemResetMemory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconResetMemory.png")));
        jMenuItemResetMemory.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		try {
					jMenuItemResetMemoryActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        jMenuItemExit.setText(textGame[11]);
        jMenuItemExit.setFont(new java.awt.Font("Lucida Bright", 2, 13));
        jMenuItemExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/IconExit.png")));
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jMenuItemExitActionPerformed(evt);
            }
        });
        
        //labels
        jLabelPrompt.setBounds(22, 50, 240, 35);
        jLabelPrompt.setFont(new java.awt.Font("Lucida Bright", 12, 20));
        jLabelPrompt.setToolTipText("Prompt"); 
        jLabelPrompt.setForeground(new java.awt.Color(236, 236, 236));
        jLabelPrompt.setText(textGame[12]);
        jLabelPrompt.setHorizontalAlignment(0);

        jLabelFrame.setBounds(72, 142, 142, 176);
        jLabelFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/others/Frame.png")));
        
        jLabelPic.setBounds(75, 145, 136, 170);
        jLabelPic.setIcon(iconPic);
        
        for(int i=0; i<5; i++){
        	for(int j=0; j<4; j++){
        		icon[i][j] = new ImageIcon(getClass().getResource("/mainFrame/pictures/pic"+pictureNumber+"/SubPic"+pictureNumber+
        				"-"+((i*4)+(j+1))+".jpg"));
        		
        		jLabelPicPart[i][j] = new JLabel();
        		jLabelPicPart[i][j].setBounds(360+j*85, 35+i*85, 85, 85);
                jLabelPicPart[i][j].setIcon(icon[i][j]);
        	}
        }
        
        
        jLabelLeft.setBounds(345, 20, 15, 455);//boarder
        jLabelLeft.setIcon(iconLeft);
        jLabelRight.setBounds(700, 20, 15, 455);
        jLabelRight.setIcon(iconRight);
        jLabelUp.setBounds(360, 20, 340, 15);
        jLabelUp.setIcon(iconUp);
        jLabelDown.setBounds(360, 460, 340, 15);
        jLabelDown.setIcon(iconDown);
        
        
        jLabelTime.setBounds(33, 330, 220, 20);//time
        jLabelTime.setText(minute+ "." +second+ "." +milSecond);
        jLabelTime.setForeground(new java.awt.Color(236, 236, 236));
        jLabelTime.setFont(new java.awt.Font("Lucida Bright", 10, 20)); //another font "DS Crystal" 
        jLabelTime.setHorizontalAlignment(0);
       
        jLabelTemp=jLabelPicPart[0][3];//used in operation
        jLabelTemp.setIcon(icon0);
    	//End of Setting Bounds and Attributes 																#_______S_______#
		
		
		
		
		//**
		// Adding Components																				#*******A*******#
		//**
        jLabelMain.add(jLabelPrompt);
        jLabelMain.add(jLabelFrame);
        jLabelMain.add(jLabelPic);
        for(int i=0; i<5; i++){
        	for(int j=0; j<4; j++){
        		jLabelMain.add(jLabelPicPart[i][j]);        	        		
        	}
        }
        
        jLabelMain.add(jLabelLeft); jLabelMain.add(jLabelRight); jLabelMain.add(jLabelUp); jLabelMain.add(jLabelDown);
        jLabelMain.add(jLabelTime);
        
        //menu bar
        jMenubar.add(jMenuPicture); jMenubar.add(jMenuOption); jMenubar.add(jMenuHelp);
        //menu item
        jMenuOption.add(jMenuItemSolvePuzzle); jMenuOption.add(jMenuItemRestart); jMenuOption.add(jMenuItemHighScore); jMenuOption.add(jMenuItemMuteUnmute);
        for(int i=0; i<numOfPictures; i++){
        	jMenuPicture.add(jCBItemPic[i]);
        }
    	jMenuHelp.add(jMenuItemInstruction); jMenuHelp.add(jMenuItemAbout);
    		jMenuHelp.add(jMenuItemDeveloper); jMenuHelp.add(jMenuItemResetMemory); 
    		jMenuHelp.add(jMenuItemExit);
        
    	//End of Adding Components																			#_______A_______#
		
		
		
    	//Setting Criterion of the Frame//
    	setIconImage(new ImageIcon(getClass().getResource("/mainFrame/pictures/others/Icon.png")).getImage());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Picture Puzzle");
        setResizable(false);
        setBounds(140, 70, 760, 570);
        setLayout(null);
        setJMenuBar(jMenubar);
        add(jLabelMain);
        addKeyListener(this); 
    }
    
    
    //**
	// Action Events																						#*******AE******#
	//**
    //menu bar actions
  	private void jCBItemPicActionPerformed(int n) throws IOException {                                         
      	Frame frame = new Frame(n+1);
      	frame.setVisible(true);
      	frame.jCBItemPic[n].setSelected(true);
      	
      	end=true;
      	//setEnabled(false);
      	dispose();
      	
  	} 
  	
  	
	private void jMenuItemRestartActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                         
  		Frame frame = new Frame(pictureNumber);
      	frame.setVisible(true);
      	if(pictureNumber==1) frame.jCBItemPic[0].setSelected(true);
      	else if(pictureNumber==2) frame.jCBItemPic[1].setSelected(true);
      	else if(pictureNumber==3) frame.jCBItemPic[2].setSelected(true);
      	else if(pictureNumber==4) frame.jCBItemPic[3].setSelected(true);
      	else if(pictureNumber==5) frame.jCBItemPic[4].setSelected(true);
      	else if(pictureNumber==6) frame.jCBItemPic[5].setSelected(true);
      	else if(pictureNumber==7) frame.jCBItemPic[6].setSelected(true);
      	else if(pictureNumber==8) frame.jCBItemPic[7].setSelected(true);
      		
      	
      	
      	end=true;
      	//setEnabled(false);
      	dispose();
    } 
  	private void jMenuItemSolvePuzzleActionPerformed(java.awt.event.ActionEvent evt){
  		if(start && !end && !robot){
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/RobotSolve.wav");
  			
  	        jLabelPrompt.setForeground(new java.awt.Color(99, 50, 50));
  			jLabelPrompt.setText("IA resolvendo!");
  			
  			timeMili=0; 
  			pause=false;
  			robot=true;
  			
  			new PuzzleSolver(icon, jLabelPicPart, jLabelTemp, end).solvePuzzle();
  		}
  		if(!start){
  			new Message(textGame[14], 210);
  		}
  	}
  	
  	private void jMenuItemHighScoreActionPerformed(java.awt.event.ActionEvent evt){
  		new HighScore(new help.highScore.operation.FileIOOperation().FileReader()).setVisible(true);	//calls a function to get info & shows that by gui
  		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/HighScorePopup.wav");
  	}
  	private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) throws IOException{
  		new About().setVisible(true);
  		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/GeneralPopup.wav");
  	}
  	private void jMenuItemDeveloperActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                         
		new Profile("07-Jun-2014").setVisible(true);
		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/GeneralPopup.wav");
  	} 
  	private void jMenuItemInstructionActionPerformed(java.awt.event.ActionEvent evt){
		new Instruction().setVisible(true);
		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/GeneralPopup.wav");
  	}
  	private void jMenuItemResetMemoryActionPerformed(java.awt.event.ActionEvent evt) throws IOException{
  		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/ResetMemory.wav");
  		new ResetMemory().setVisible(true);
  	}

  	private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {   
  		end=true;
  		
  		System.exit(1);
    } 
  	private void jMenuItemMuteUnmute(java.awt.event.ActionEvent evt){
  		sessao.setSounds(!(sessao.getSounds()));
  	}
    //End of Action Events																					#_______AE______#

    
    //**
	// Run Method																							#*******R*******#
	//**
	@SuppressWarnings("static-access")
	public void run() {
		int time=0, a=0;
		boolean solved=false;

		try{
			while(!end) {
				time=timeMili;
				
				a=time%100;
				if(a<10)milSecond="0"+a;
				else milSecond=""+a;
				time=time/100;
				
				a=time%60;
				if(a<10)second="0"+a;
				else second=""+a;
				time=time/60;
				
				a=time%60;
				if(a<10)minute="0"+a;
				else minute=""+a;
				time=time/60;
				
				
		        jLabelTime.setText(minute + "." +second+ "." +milSecond);
				
				thread.sleep(10);
				
				if(!pause) timeMili++;	//when pause 'timeMili' won't increase
				
				if(verify()){
	  				end=true;
	  				solved=true;
	  			}
			}
			
			if(solved){
				afterSolved();
				thread.sleep(950);//wait
				
				//send time
				if(!robot){
					new help.highScore.operation.FileIOOperation().FileOpener(timeMili-1, minute + "-" +second+ "-" +milSecond);	//function call to save high score
				}
			}
		}catch(Exception e){
			//Do nothing
		}
	}
	//End of Run Method																						#_______R_______#

	
    //**
    // key listener starts																					#*******KL******#
    //**
    /**
     * unimplemented methods of KeyListener    ####//360---615//35|||375//####
     */
  	public void keyPressed(KeyEvent key) {
  		JLabel selectedLabel;
  		
  		if(!start){
  			if(key.getKeyCode() == 10) {
  				start=true;
  		        jLabelPrompt.setFont(new java.awt.Font("Lucida Bright", 0, 28));
  				jLabelPrompt.setText(textGame[15]);
  				
  				mixUp();	//method call
  				
  				thread.start();
  				if (pictureNumber == 8)  if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/Naruto.wav");
  				else if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/Start.wav");
  				
  			}
  		}
  		
  		else if (key.getKeyCode() == 37 && end==false && pause==false && robot==false) {
              //System.out.println("Left");///test
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/PlayPause.wav");
  			selectedLabel=FindSelectedLabel(jLabelTemp, 37);
  			labelSwap(selectedLabel);
        }
  		else if (key.getKeyCode() == 39 && end==false && pause==false && robot==false) {
  			//System.out.println("Right");///test
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/PlayPause.wav");
  			selectedLabel=FindSelectedLabel(jLabelTemp, 39);	
  			labelSwap(selectedLabel);
  		}
  		else if (key.getKeyCode()==38 && end==false && pause==false && robot==false) {
              //System.out.println("Up");///test
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/PlayPause.wav");
  			selectedLabel=FindSelectedLabel(jLabelTemp, 38);			
  			labelSwap(selectedLabel);
  		}
  		else if (key.getKeyCode() == 40 && end==false && pause==false && robot==false) {
  			//System.out.println("Down");///test
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/PlayPause.wav");
  			selectedLabel=FindSelectedLabel(jLabelTemp, 40);	
  			labelSwap(selectedLabel);
        }
  		else if (key.getKeyCode() == 80 && end==false && robot==false) {	//when p is pressed 
  			if(pause==false){
  				pause=true;
  				jLabelPrompt.setText(textGame[16]);
  			}else{
  				pause=false;
  				jLabelPrompt.setText(textGame[15]);
  			}
  			if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/GeneralPopup.wav");
  		}
  		/*///only for test//shows the code
  		else{
  			System.out.println(key.getKeyCode());
  		}/**/
    }
  	

  	
  	
    public void keyReleased(KeyEvent key) {
    }

    public void keyTyped(KeyEvent key) {
    }
  	//key listener ends																						#_______KL_______#
  	
    
    //**  Main Method  **//
    public static void main(String args[]) throws IOException {
    	/* Set the NIMBUS look and feel */
    	try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");					
		} catch (Exception ex) {
			//do nothing if operation is unsuccessful
		}


        /* Create and display the form */
        Frame gui = new Frame(1);
        gui.setVisible(true);
        gui.jCBItemPic[0].setSelected(true);
    }
    
    
    //**
    // Auxiliary Methods																					#*******AM******#
    //**

    /**
     * Mix-ups icons among labels randomly.
     */
  	private void mixUp(){
  		Icon icon = new ImageIcon();
  		JLabel labelRand = new JLabel();
  		/////////////////////////////////////////////////////////////////////////////////////////////////
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[1][3].getIcon()); jLabelPicPart[1][3].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[0][2].getIcon()); jLabelPicPart[0][2].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[0][0].getIcon()); jLabelPicPart[0][0].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[1][1].getIcon()); jLabelPicPart[1][1].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[2][2].getIcon()); jLabelPicPart[2][2].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[2][3].getIcon()); jLabelPicPart[2][3].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[3][3].getIcon()); jLabelPicPart[3][3].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[4][2].getIcon()); jLabelPicPart[4][2].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[3][1].getIcon()); jLabelPicPart[3][1].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[2][0].getIcon()); jLabelPicPart[2][0].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[4][0].getIcon()); jLabelPicPart[4][0].setIcon(icon); 
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[4][1].getIcon()); jLabelPicPart[4][1].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[3][2].getIcon()); jLabelPicPart[3][2].setIcon(icon);   
  		labelRand=randomLabel(); icon=labelRand.getIcon();
  			labelRand.setIcon(jLabelPicPart[3][0].getIcon()); jLabelPicPart[3][0].setIcon(icon);  
  		//////////////////////////////////////////////////////////////////////////////////////////////////
  	}
  	
  	
  	/**
  	 * returns a Random label.
  	 * @return	random label.
  	 */
  	private JLabel randomLabel(){
  		JLabel labelRand=new JLabel();
  		int random= new Random().nextInt();
  		 
  		if(random%5==0) labelRand=jLabelPicPart[2][1];
  		else if(random%4==0) labelRand=jLabelPicPart[1][2];
  		else if(random%3==0) labelRand=jLabelPicPart[1][0];
  		else if(random%2==0) labelRand=jLabelPicPart[0][1];
  		else labelRand=jLabelPicPart[4][3];
  				
  		return labelRand;
  	}
  	
  	
  	/**
	 * Takes a <code>jLabelTemp</code> & an <code>int</code>(pressed arrow key) and returns the neighboring label.
	 * @param jLabelTemp label containing empty pic-part
	 * @param key pressed arrow key
	 * @return label by the side of <code>jLabelTemp</code> depending on <code>int</code>(pressed arrow key)
	 */
  	private JLabel FindSelectedLabel(JLabel jLabelTemp, int key){
  		JLabel selectedLabel=jLabelTemp;
  		
  		if(key==39){			/////right/////
  			if(jLabelTemp==jLabelPicPart[0][0] || jLabelTemp==jLabelPicPart[1][0] || jLabelTemp==jLabelPicPart[2][0] || 
  					jLabelTemp==jLabelPicPart[3][0] || jLabelTemp==jLabelPicPart[4][0]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<5; i++){
  					for(int j=1; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i][j-1];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==37){			/////left/////
  			if(jLabelTemp==jLabelPicPart[0][3] || jLabelTemp==jLabelPicPart[1][3] || jLabelTemp==jLabelPicPart[2][3] || 
  					jLabelTemp==jLabelPicPart[3][3] || jLabelTemp==jLabelPicPart[4][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<5; i++){
  					for(int j=0; j<3; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i][j+1];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==38){			/////up/////
  			if(jLabelTemp==jLabelPicPart[4][0] || jLabelTemp==jLabelPicPart[4][1] || jLabelTemp==jLabelPicPart[4][2] || 
  					jLabelTemp==jLabelPicPart[4][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=0; i<4; i++){
  					for(int j=0; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i+1][j];
  							break;
  						}
  					}
  				}
  			}
  		}
  		else if(key==40){		/////down/////
  			if(jLabelTemp==jLabelPicPart[0][0] || jLabelTemp==jLabelPicPart[0][1] || jLabelTemp==jLabelPicPart[0][2] || 
  					jLabelTemp==jLabelPicPart[0][3]){
  				
  				selectedLabel=jLabelTemp;
  			}
  			else{
  				for(int i=1; i<5; i++){
  					for(int j=0; j<4; j++){
  						if(jLabelTemp==jLabelPicPart[i][j]){
  							selectedLabel=jLabelPicPart[i-1][j];
  							break;
  						}
  					}
  				}
  			}
  		}
  		
  		return selectedLabel;
  	}
  	
  	/**
   	 * Swaps icons of <code>selectedLabel</code> and <code>jLabelTemp</code>. Doesn't swap labels directly.
   	 * @param selectedLabel label containing swap-icon
   	 */
  	private void labelSwap(JLabel selectedLabel){
//  		soundPlayer.playSound("sound/sounds/Drop.wav");
  		
  		Icon icon = selectedLabel.getIcon();
  		selectedLabel.setIcon(jLabelTemp.getIcon());
  		jLabelTemp.setIcon(icon);
  		
  		jLabelTemp=selectedLabel;
  	}
  	
  	
  	/**
  	 * Verifies if all icons are in right position. Returns true if icons are in position.
  	 * @return true if icons are in position other wise return false.
  	 */
  	private boolean verify(){
  		
  		if(jLabelPicPart[0][0].getIcon()!=icon[0][0]) return false;
  		else if(jLabelPicPart[0][1].getIcon()!=icon[0][1]) return false;
  		else if(jLabelPicPart[0][2].getIcon()!=icon[0][2]) return false;
  		else if(jLabelPicPart[0][3].getIcon()!=icon0) return false;//0icon//
  		else if(jLabelPicPart[1][0].getIcon()!=icon[1][0]) return false;
  		else if(jLabelPicPart[1][1].getIcon()!=icon[1][1]) return false;
  		else if(jLabelPicPart[1][2].getIcon()!=icon[1][2]) return false;
  		else if(jLabelPicPart[1][3].getIcon()!=icon[1][3]) return false;
  		else if(jLabelPicPart[2][0].getIcon()!=icon[2][0]) return false;
  		else if(jLabelPicPart[2][1].getIcon()!=icon[2][1]) return false;
  		else if(jLabelPicPart[2][2].getIcon()!=icon[2][2]) return false;
  		else if(jLabelPicPart[2][3].getIcon()!=icon[2][3]) return false;
  		else if(jLabelPicPart[3][0].getIcon()!=icon[3][0]) return false;
  		else if(jLabelPicPart[3][1].getIcon()!=icon[3][1]) return false;
  		else if(jLabelPicPart[3][2].getIcon()!=icon[3][2]) return false;
  		else if(jLabelPicPart[3][3].getIcon()!=icon[3][3]) return false;
  		else if(jLabelPicPart[4][0].getIcon()!=icon[4][0]) return false;
  		else if(jLabelPicPart[4][1].getIcon()!=icon[4][1]) return false;
  		else if(jLabelPicPart[4][2].getIcon()!=icon[4][2]) return false;
  		else if(jLabelPicPart[4][3].getIcon()!=icon[4][3]) return false;
  		else return true;

  	}
  	

  	/**
  	 * Operation executed after puzzle solved.
  	 */
  	private void afterSolved(){
  		if(sessao.getSounds()) soundPlayer.playSound("sound/sounds/Solved.wav");
  		jLabelPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainFrame/pictures/pic"+pictureNumber	+
  				"/SolvedPic"+pictureNumber+".jpg")));
  		jLabelPicPart[0][3].setIcon(icon[0][3]);
  		jLabelPrompt.setText(textGame[17]);
  	}
  	

  	/**
  	 * this one is needed to access to <code>jCBItemPic1</code> from outside and set it selected.
  	 */
  	public void setJCBItemPic1Selected(){
  		jCBItemPic[0].setSelected(true);
  	}
  	
    //End of Auxiliary Methods																				#_______AM______#
}


