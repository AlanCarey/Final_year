import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;

public class CreateAndShowGui{
	
	protected JTextArea jTextStatus;

	protected JTextField textFrame;
	protected JTextField textHands;
	protected JTextField textFingers;
	protected JTextField textTools;
	protected JTextField textGestures;
	protected JTextField textPamlPos;
	protected JTextField textX;
	protected JTextField textY;
	protected JTextField textZ;
	protected JTextField textLeftRight;
	protected JTextField textYaw;
	protected JTextField textRoll;
	
	protected JScrollPane jScrollStatus;

	protected JButton jButtActivate;
	protected JButton jButtDeactivate;
	protected JButton jButtSendData;
	protected JButton jButtCloseData;
	protected JButton jButtStartRecording;
	protected JButton jButtStopRecording;
	protected JButton jButtStartSystemStats;
	protected JButton jButtStopSystemStats;
	protected JButton jButtStartLeapData;
	protected JButton jButtStopLeapData;
	protected JButton jButtPlayRecord;
	
	protected JButton jButtResetArm;
	protected JButton jButtStopArm;
	
	protected JLabel lblLED1;
	protected JLabel lblLED2;
	
	protected JLabel lblLED3;
	protected JLabel lblLED4;
	
	/*
	 * dataBase varibles
	 */
	
	protected JPanel login;
	protected JTextField jTextFieldUserName;
	protected JTextField jTextFieldPassword;
	protected JButton jButtLogin;
	
	/*
	 * 
	 */
	
	protected JPanel currentUserData;	
	protected JTextField jTextFieldCurUserName = new JTextField(20);
	protected JTextField jTextFieldCurLoginTime;
	protected JTextField jTextFieldCurlogoutTime;
	protected JTextField jTextFieldCurPreferredhand;	
	protected JTextArea jTextAreaCurNotes;
	protected JButton jButtCurSignOut;
	protected JButton jButtCurUpdate;

	/*
	 * 
	 */
	
	protected JPanel newUserData;
	protected JTextField jTextFieldNewPreferredhand;
	protected JTextArea jTextAreaNewNotes;
	protected JButton jButtNewUpdate;
	
	BufferedImage myPicture = null;;
	
	public CreateAndShowGui(){
				
		
	}

	public Component liveVideoPanelCenter(){
		
//		JPanel videoPanel = new JPanel(new MigLayout());
//		videoPanel.setBorder(BorderFactory.createTitledBorder("Live Video Stream"));
//	
//		try {
//			myPicture = ImageIO.read(new File("C:/Users/Alan/Documents/FinalYearGitHubNew/Final_year/yes.JPG"));
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//		videoPanel.add(picLabel);
//		
//		return videoPanel;
		
		JPanel dataBasePanel = new JPanel(new BorderLayout());
		dataBasePanel.setBorder(BorderFactory.createTitledBorder("DataBase"));
		
		
		
		login = new JPanel(new MigLayout());
		login.setBorder(BorderFactory.createTitledBorder("Login"));
		
		JLabel lblUserName = new JLabel("Username");
		jTextFieldUserName = new JTextField(20);
		
		JLabel lblPassword = new JLabel("Password");
		jTextFieldPassword = new JTextField(20);
		
		jButtLogin = new JButton("Login");
		
		login.add(lblUserName);
		login.add(jTextFieldUserName, "wrap");
		login.add(lblPassword);
		login.add(jTextFieldPassword);
		login.add(jButtLogin);
		
		/*
		 * 
		 */
		
		currentUserData = new JPanel(new MigLayout());
		currentUserData.setBorder(BorderFactory.createTitledBorder("Current User Data"));
		
		JLabel lblCurUserName = new JLabel("Username");
		jTextFieldCurUserName = new JTextField(20);
		
		JLabel lblCurLoginTime = new JLabel("Login Time");
		jTextFieldCurLoginTime = new JTextField(20);
		
		JLabel lblCurLogoutTime = new JLabel("Logout Time");
		jTextFieldCurlogoutTime = new JTextField(20);
		
		JLabel lblCurPreferredHand = new JLabel("Preferred Hand");
		jTextFieldCurPreferredhand = new JTextField(20);
		
		JLabel lblCurNotes = new JLabel("Notes");
		jTextAreaCurNotes = new JTextArea(10,10);
		JScrollPane jScrollPaneCurNotes = new JScrollPane(jTextAreaCurNotes);
		
		jScrollPaneCurNotes.setPreferredSize(new Dimension(150, 200));
		jTextAreaCurNotes.setWrapStyleWord(true);
		jTextAreaCurNotes.setLineWrap(true);
		
		jButtCurSignOut = new JButton("Sign Out");
		jButtCurUpdate = new JButton("Update Info?");
		
		currentUserData.add(lblCurUserName);
		currentUserData.add(jTextFieldCurUserName, "wrap");
		
		currentUserData.add(lblCurLoginTime);
		currentUserData.add(jTextFieldCurLoginTime, "wrap");
		
		currentUserData.add(lblCurLogoutTime);
		currentUserData.add(jTextFieldCurlogoutTime, "wrap");
		
		currentUserData.add(lblCurPreferredHand);
		currentUserData.add(jTextFieldCurPreferredhand, "wrap");
		
		currentUserData.add(lblCurNotes);
		currentUserData.add(jScrollPaneCurNotes, "wrap");
		
		currentUserData.add(jButtCurSignOut);
		currentUserData.add(jButtCurUpdate);
		/*
		 * 
		 */
		
		newUserData = new JPanel(new MigLayout());
		newUserData.setBorder(BorderFactory.createTitledBorder("New User Data"));

		JLabel lblNewPreferredHand = new JLabel("Preferred Hand");
		jTextFieldNewPreferredhand = new JTextField(20);
		
		JLabel lblNewNotes = new JLabel("Notes (50)");
		jTextAreaNewNotes = new JTextArea(10,10);
		JScrollPane jScrollPaneNewNotes = new JScrollPane(jTextAreaNewNotes);
		
		jScrollPaneNewNotes.setPreferredSize(new Dimension(150, 200));
		jTextAreaNewNotes.setWrapStyleWord(true);
		jTextAreaNewNotes.setLineWrap(true);
		
		jButtNewUpdate = new JButton("Update");
				
		newUserData.add(lblNewPreferredHand);
		newUserData.add(jTextFieldNewPreferredhand, "wrap");
		newUserData.add(lblNewNotes);
		newUserData.add(jScrollPaneNewNotes, "wrap");
		
		newUserData.add(jButtNewUpdate);
		
		/*
		 * 
		 */
		
		dataBasePanel.add(login, BorderLayout.NORTH);
		dataBasePanel.add(currentUserData, BorderLayout.EAST);
		dataBasePanel.add(newUserData, BorderLayout.WEST);
		
		return dataBasePanel;
	}
	
	public Component leapControlPanelNorth(){
		
		JPanel controlPanel = new JPanel(new MigLayout());
		controlPanel.setBorder(BorderFactory.createTitledBorder("System Control Buttons"));
		jButtActivate = new JButton("Leap On");
		jButtDeactivate = new JButton("Leap Off");
	
		jButtStartSystemStats = new JButton("System Stats On");
		jButtStopSystemStats = new JButton("System Stats Off");
		jButtStartLeapData = new JButton("Leap Data On");
		jButtStopLeapData = new JButton("Leap Data Off");
		
		jButtSendData = new JButton("Open Connection");
		jButtCloseData = new JButton("Close Connection");		
				
		controlPanel.add(jButtActivate);
		controlPanel.add(jButtDeactivate);
		controlPanel.add(jButtSendData);
		controlPanel.add(jButtCloseData);
		controlPanel.add(jButtStartSystemStats);
		controlPanel.add(jButtStopSystemStats);
		controlPanel.add(jButtStartLeapData);
		controlPanel.add(jButtStopLeapData);
		
		return controlPanel;
	}
	
    public Component leapControlPanelSouth(){
		
		JPanel controlPanel = new JPanel(new MigLayout());
		controlPanel.setBorder(BorderFactory.createTitledBorder("Robotic Arm Control Buttons"));
				
		jButtStartRecording = new JButton("Start Recording");
		jButtStopRecording = new JButton("Stop Recording");
		jButtPlayRecord = new JButton("Play Record");
		jButtResetArm = new JButton("Reset Arm");
		jButtStopArm = new JButton("Stop Arm");
		
		controlPanel.add(jButtStartRecording);
		controlPanel.add(jButtStopRecording);
		controlPanel.add(jButtPlayRecord);
		
		controlPanel.add(jButtResetArm);
		controlPanel.add(jButtStopArm);
		
		return controlPanel;
	}
	
	public Component leapStatusPanelEast(){
		
		jTextStatus = new JTextArea(10, 10);
		jTextStatus.setPreferredSize(new Dimension(400,400));
		//textStatus.setBorder(BorderFactory.createTitledBorder("System Status"));
		
		jScrollStatus = new JScrollPane(jTextStatus);
		
		JLabel lblLeapActive1 = new JLabel("Leap Motion Status");
		JLabel lblLeapActive2 = new JLabel("Connection Status");		
		JLabel lblLeapActive3 = new JLabel("System Status");
		JLabel lblLeapActive4 = new JLabel("Leap Data");
				
		lblLED1 = new JLabel("�");
		lblLED1.setForeground(Color.red);
		
		lblLED2 = new JLabel("�");
		lblLED2.setForeground(Color.red);
		
		lblLED3 = new JLabel("�");
		lblLED3.setForeground(Color.red);
		
		lblLED4 = new JLabel("�");
		lblLED4.setForeground(Color.red);
		
		JPanel statusPanel = new JPanel(new MigLayout());
		statusPanel.setBorder(BorderFactory.createTitledBorder("System Status"));

		jScrollStatus.setPreferredSize(new Dimension(290, 300));
		jTextStatus.setWrapStyleWord(true);
		jTextStatus.setLineWrap(true);
	
		statusPanel.add(jScrollStatus, "wrap");
		statusPanel.add(lblLeapActive1);
		statusPanel.add(lblLED1, "wrap");
		statusPanel.add(lblLeapActive2);
		statusPanel.add(lblLED2, "wrap");
		statusPanel.add(lblLeapActive3);
		statusPanel.add(lblLED3, "wrap");
		statusPanel.add(lblLeapActive4);
		statusPanel.add(lblLED4, "wrap");
		
		return statusPanel; 
	}
	
	
	public Component leapDataPanelWest(){
		
		JLabel labFrame = new JLabel("Frame ID");
		JLabel labHands = new JLabel("Hands");
		JLabel labFingers = new JLabel("Fingers");
		JLabel labTools = new JLabel("Tools");
		JLabel labGuestures = new JLabel("Guestures");
		JLabel labPalmPos  = new JLabel("Palm Position");
		JLabel labX = new JLabel("X Position");
		JLabel labY = new JLabel("Y Position");
		JLabel labZ = new JLabel("Z Position");
		JLabel labLeftRight = new JLabel("Left / Right");
		JLabel labYaw = new JLabel("Yaw");
		JLabel labRoll = new JLabel("Roll");
		
		textFrame = new JTextField(20);
		textHands = new JTextField(20);
		textFingers = new JTextField(20);
		textTools = new JTextField(20);
		textGestures = new JTextField(20);
		textPamlPos = new JTextField(20);
		textX = new JTextField(20);
		textY = new JTextField(20);
		textZ = new JTextField(20);
		textLeftRight = new JTextField(20);
		textYaw = new JTextField(20);
		textRoll = new JTextField(20);
		
		JPanel dataPanel = new JPanel(new MigLayout());
		dataPanel.setBorder(BorderFactory.createTitledBorder("Leap Motion Data"));
		
		dataPanel.add(labFrame);
		dataPanel.add(textFrame, "wrap");
		
		dataPanel.add(labHands);
		dataPanel.add(textHands, "wrap");
		
		dataPanel.add(labFingers);
		dataPanel.add(textFingers, "wrap");
		
		dataPanel.add(labTools);
		dataPanel.add(textTools, "wrap");
		
		dataPanel.add(labGuestures);
		dataPanel.add(textGestures, "wrap");
		
		dataPanel.add(labPalmPos);
		dataPanel.add(textPamlPos, "wrap");
		
		dataPanel.add(labX);
		dataPanel.add(textX, "wrap");
		
		dataPanel.add(labY);
		dataPanel.add(textY, "wrap");
		
		dataPanel.add(labZ);
		dataPanel.add(textZ, "wrap");
		
		dataPanel.add(labLeftRight);
		dataPanel.add(textLeftRight, "wrap");
		
		dataPanel.add(labYaw);
		dataPanel.add(textYaw, "wrap");
		
		dataPanel.add(labRoll);
		dataPanel.add(textRoll, "wrap");
		
		return dataPanel;
		
	}

	public void printFrameID(final String frameID){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textFrame.setText(frameID);
				
			}
		});
		
	}
	
	public void printHands(final String hands){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textHands.setText(hands);
				
			}
		});
		
	}
	
	public void printFingers(final String fingers){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textFingers.setText(fingers);
				
			}
		});
		
	}
	
	public void printTools(final String tools){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textTools.setText(tools);
				
			}
		});
		
	}
	
	public void printGestures(final String gestures){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textGestures.setText(gestures);
				
			}
		});
		
	}
	
	public void printPalmPosition(final String palmPos){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textPamlPos.setText(palmPos);
				
			}
		});
		
	}
	
	public void printX(final String x){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textX.setText(x);
				
			}
		});
		
	}
	
	public void printY(final String y){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textY.setText(y);
				
			}
		});
		
	}
	
	public void printZ(final String z){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textZ.setText(z);
				
			}
		});
		
	}
	
	public void printLeftRight(final String leftRight){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textLeftRight.setText(leftRight);
				
			}
		});
		
	}
	
	public void printYaw(final String yaw){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textYaw.setText(yaw);
				
			}
		});
		
	}
	
	public void printRoll(final String roll){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				textRoll.setText(roll);
				
			}
		});
		
	}
	
	public void printStatus(final String status){
		
    	SwingUtilities.invokeLater(new Runnable() {
    		
			public void run() {
				jTextStatus.append(status + "\n");		
			}
		});
	}
	
	public void setAllLeapDataFields(final String toDo){
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				
				textFrame.setText(toDo);
				textHands.setText(toDo);
				textFingers.setText(toDo);
				textTools.setText(toDo);
				textGestures.setText(toDo);
				textPamlPos.setText(toDo);
				textX.setText(toDo);
				textY.setText(toDo);
				textZ.setText(toDo);
				textLeftRight.setText(toDo);
				textYaw.setText(toDo);
				textRoll.setText(toDo);
				
			}
		});
		
	}
	
	
}
