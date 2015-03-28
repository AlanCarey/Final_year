
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.leapmotion.leap.*;

public class LeapController {

	static LeapListener listener = null;
	static Controller controller = null;
	static EstablishConnection establishCon = null;
	static DataBase database = null;
	//static CreateAndShowGui showGUI = null;
	
	public static void main(String[] args) {
		
		final CreateAndShowGui showGUI = new CreateAndShowGui();		
		
		JFrame frame = new JFrame ("Leap Controller User GUI");
		
		frame.setSize(1390, 600);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		frame.add(showGUI.leapControlPanelNorth(), BorderLayout.NORTH);
		frame.add(showGUI.leapDataPanelWest(), BorderLayout.WEST);
		frame.add(showGUI.leapStatusPanelEast(), BorderLayout.EAST);
		frame.add(showGUI.leapControlPanelSouth(), BorderLayout.SOUTH);
		frame.add(showGUI.liveVideoPanelCenter(), BorderLayout.CENTER);
		
		frame.setVisible (true);
		//frame.pack();
		
		showGUI.jButtActivate.setEnabled(true);
		showGUI.jButtDeactivate.setEnabled(false);
		showGUI.jButtSendData.setEnabled(false);
		showGUI.jButtCloseData.setEnabled(false);
		showGUI.jButtStartRecording.setEnabled(false);
		showGUI.jButtStopRecording.setEnabled(false);
		
		showGUI.jButtStartSystemStats.setEnabled(false);
		showGUI.jButtStopSystemStats.setEnabled(true);
		showGUI.jButtStartLeapData.setEnabled(false);
		showGUI.jButtPlayRecord.setEnabled(false);
		showGUI.jButtStopLeapData.setEnabled(true);
		
		showGUI.jButtStopArm.setEnabled(false);
		showGUI.jButtResetArm.setEnabled(false);
		
		showGUI.currentUserData.setVisible(false);
		showGUI.newUserData.setVisible(false);
		
		establishCon = new EstablishConnection(showGUI);
		
		database = new DataBase(showGUI);
		
		listener = new LeapListener(showGUI, establishCon, database); //Listener class
		controller = new Controller(); //leap motion lib
		
		showGUI.lblLED3.setForeground(Color.GREEN);
		showGUI.lblLED4.setForeground(Color.GREEN);
		
		/**********************************************************************************************************************
		 * 
		 * JButtActivate ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtActivate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showGUI.setAllLeapDataFields("0");
				
				controller.addListener(listener);
				showGUI.jButtActivate.setEnabled(false);
				
				showGUI.jButtSendData.setEnabled(true);
				showGUI.jButtDeactivate.setEnabled(true);
				//showGUI.jButtStartRecording.setEnabled(true);
				
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Leap Activated");
				}
				showGUI.lblLED1.setForeground(Color.GREEN);
				
			}
		}); 
		
		/**********************************************************************************************************************
		 * 
		 * JButtDeacivate ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtDeactivate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				controller.removeListener(listener);
				showGUI.jButtSendData.setEnabled(false);
				showGUI.jButtCloseData.setEnabled(false);
				showGUI.jButtDeactivate.setEnabled(false);
				showGUI.jButtStartRecording.setEnabled(false);
				showGUI.jButtStopRecording.setEnabled(false);
				showGUI.jButtResetArm.setEnabled(false);
				showGUI.jButtStopArm.setEnabled(false);
				showGUI.jButtActivate.setEnabled(true);
			
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Leap Deactivated");				
				}

				showGUI.lblLED1.setForeground(Color.RED);
											
				showGUI.setAllLeapDataFields("0");
				
				establishCon.setInService(false);
				
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtSendData ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtSendData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Opening Connection ... ");	
				}
				
				if(establishCon.makeConnection() == true) {
					
					establishCon.setInService(true);
					
					showGUI.jButtSendData.setEnabled(false);
					showGUI.jButtCloseData.setEnabled(true);
					showGUI.jButtStartRecording.setEnabled(true);
					showGUI.jButtStopArm.setEnabled(true);
					showGUI.jButtResetArm.setEnabled(true);
					
					
					showGUI.lblLED2.setForeground(Color.GREEN);
					
					if(listener.isSystemStatsFlag() == true){
						showGUI.printStatus("OK");
					}
				}
				else {
					if(listener.isSystemStatsFlag() == true){
						showGUI.printStatus("Failed to make the connection");
					}
				}
				
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtCloseData ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtCloseData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Closing Connection ... ");
				}
				
				if(establishCon.closeConnection() == true) {
					
					establishCon.setInService(false);
					
					showGUI.jButtSendData.setEnabled(true);
					showGUI.jButtCloseData.setEnabled(false);	
					showGUI.jButtStartRecording.setEnabled(false);
					showGUI.jButtStopArm.setEnabled(false);
					showGUI.jButtPlayRecord.setEnabled(false);
					showGUI.jButtResetArm.setEnabled(false);
					showGUI.jButtStopArm.setEnabled(false);
					
					showGUI.lblLED2.setForeground(Color.RED);
					
					if(listener.isSystemStatsFlag() == true){
						showGUI.printStatus("Ok");
					}
				}
				else { 
					
					if(listener.isSystemStatsFlag() == true){
						showGUI.printStatus("Failed to close the connection");
					}
					
				}
								
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStopSystemStats ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtStopSystemStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showGUI.lblLED3.setForeground(Color.RED);
				listener.setSystemStatsFlag(false);
				showGUI.jButtStartSystemStats.setEnabled(true);
				showGUI.jButtStopSystemStats.setEnabled(false);
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStartSystemStats ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtStartSystemStats.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showGUI.lblLED3.setForeground(Color.GREEN);
				listener.setSystemStatsFlag(true);
				showGUI.jButtStopSystemStats.setEnabled(true);
				showGUI.jButtStartSystemStats.setEnabled(false);
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStopLeapData ActionListener
		 * 
		 **********************************************************************************************************************/		
		
		showGUI.jButtStopLeapData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showGUI.lblLED4.setForeground(Color.RED);
				listener.setLeapDataFlag(false);
				showGUI.jButtStartLeapData.setEnabled(true);
				showGUI.jButtStopLeapData.setEnabled(false);
				showGUI.setAllLeapDataFields("0");
			}
		});

		/**********************************************************************************************************************
		 * 
		 * JButtStartLeapData ActionListener
		 * 
		 **********************************************************************************************************************/		
		
		showGUI.jButtStartLeapData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showGUI.lblLED4.setForeground(Color.GREEN);
				listener.setLeapDataFlag(true);
				showGUI.jButtStopLeapData.setEnabled(true);
				showGUI.jButtStartLeapData.setEnabled(false);
				showGUI.setAllLeapDataFields("0");
				
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStartRecording ActionListener
		 * 
		 **********************************************************************************************************************/		
		
		showGUI.jButtStartRecording.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				showGUI.jButtStopRecording.setEnabled(true);
				showGUI.jButtStartRecording.setEnabled(false);
				
				listener.recordedData.clear();
				
				establishCon.setInService(false);
				establishCon.setInRecordService(true);
				
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Recording ... ");
				}	
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStopRecording ActionListener
		 * 
		 **********************************************************************************************************************/		
		
		showGUI.jButtStopRecording.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				showGUI.jButtStopRecording.setEnabled(false);
				showGUI.jButtStartRecording.setEnabled(true);			
				
				establishCon.setInRecordService(false);
				establishCon.setInService(true);
				
				if(listener.isSystemStatsFlag() == true){
					showGUI.printStatus("Recording Stopped");
				}
				showGUI.jButtPlayRecord.setEnabled(true);
			}
		});
		
		
		/**********************************************************************************************************************
		 * 
		 * JButtPlayRecord ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtPlayRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(listener.isSystemStatsFlag() == true) {
					
					showGUI.printStatus("Playing ...");
					
				}
				listener.sendRecordedData();
				establishCon.setInService(true);
				establishCon.setInRecordService(false);
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtResetArm ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtResetArm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//send the reset signal to the pi
				listener.sendResetSignal();
				showGUI.jButtStopArm.setEnabled(true);
				establishCon.setInService(true);
				
				if(listener.isSystemStatsFlag() == true) {
					
					showGUI.printStatus("Arm In Default Position");
					
				}
				
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStopArm ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtStopArm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//send the stop signal to the arm
				listener.sendPowerOffSignal();
				showGUI.jButtStopArm.setEnabled(false);
				showGUI.jButtResetArm.setEnabled(true);
				
				establishCon.setInService(false);
				
				if(listener.isSystemStatsFlag() == true) {
					
					showGUI.printStatus("Arm Powered Off");
					
				}
			}
		});
		
		/**********************************************************************************************************************
		 * 
		 * JButtStopArm ActionListener
		 * 
		 **********************************************************************************************************************/
		
		showGUI.jButtLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					database.connect();
					
					if(database.login() == true) {
						showGUI.currentUserData.setVisible(true);
						showGUI.login.setVisible(false);
						showGUI.jButtCurUpdate.setEnabled(true);

						database.updateLoginTime();
						database.currentUserReadData();
						
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		
		showGUI.jButtCurSignOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					database.signout();
					showGUI.login.setVisible(true);
					showGUI.currentUserData.setVisible(false);
					showGUI.newUserData.setVisible(false);
					showGUI.jButtCurUpdate.setEnabled(true);
					showGUI.jTextFieldUserName.setText("");
					showGUI.jPasswordFieldPassword.setText("");
					
					System.out.println("User = " + database.getCurrentUsername());
					System.out.println("P Hand = " + database.getPreferred());
					
					
					database.setCurrentUsername("");
					database.setPreferred("");
					
					System.out.println("After Set");
					
					System.out.println("User = " + database.getCurrentUsername());
					System.out.println("P Hand = " + database.getPreferred());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		showGUI.jButtCurUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				showGUI.newUserData.setVisible(true);
				showGUI.jButtCurUpdate.setEnabled(false);
			}
		});
		
		showGUI.jButtNewUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					database.updateData();
					showGUI.newUserData.setVisible(false);
					showGUI.jButtCurUpdate.setEnabled(true);
					database.currentUserReadData();
					showGUI.jTextAreaNewNotes.setText("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
	}
}
