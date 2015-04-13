/**
 * Main of the application that create the object necessary to run the application
 * 
 * @author G00269534(Alan Carey)
 * @version 1.0
 * @since 20-09-2014
 * 
 */

import com.leapmotion.leap.*;

public class LeapController {

	static LeapListener listener = null;
	static Controller controller = null;
	static EstablishConnection establishCon = null;
	static DataBase database = null;
	static ActionListeners actionListener = null;
	static CreateAndShowGui showGUI = null;
	
	/**
	 * Main that starts the application
	 * 
	 * @param args no args needed
	 */
	
	public static void main(String[] args) {
			
		showGUI = new CreateAndShowGui();		
		
		showGUI.initButtons();
			
		establishCon = new EstablishConnection();
		
		database = new DataBase(showGUI);
		
		listener = new LeapListener(showGUI, establishCon, database); //Listener class
		
		controller = new Controller(); //leap motion lib
		
		actionListener = new ActionListeners(showGUI, listener, controller, establishCon, database);
		
		actionListener.setUpActionListeners();

	}
	
}
