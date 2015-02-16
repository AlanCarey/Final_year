
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
	//static CreateAndShowGui showGUI = null;
	
	public static void main(String[] args) {
		
		final CreateAndShowGui showGUI = new CreateAndShowGui();		
		
		JFrame frame = new JFrame ("Leap Controller User GUI");
		
		frame.setSize(1390, 600);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
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
		
		establishCon = new EstablishConnection(showGUI);
		
		listener = new LeapListener(showGUI, establishCon); //Listener class
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
				showGUI.jButtStartRecording.setEnabled(true);
				
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
					showGUI.printStatus("OK");
				}
				
				establishCon.setInService(true);
				showGUI.jButtSendData.setEnabled(false);
				showGUI.jButtCloseData.setEnabled(true);
				
				
				
				showGUI.lblLED2.setForeground(Color.GREEN);
				
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
					showGUI.printStatus("Ok");
				}
				establishCon.setInService(false);
				showGUI.jButtSendData.setEnabled(true);
				showGUI.jButtCloseData.setEnabled(false);
				
				showGUI.lblLED2.setForeground(Color.RED);
				
				
				
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
				showGUI.printStatus("Recording ... ");
				
				listener.setRecordDataFlag(true);	
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
				
				showGUI.printStatus("Recording Stopped");
				listener.setRecordDataFlag(false);
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
				listener.setSendRecording(true);
			}
		});
	}
}
