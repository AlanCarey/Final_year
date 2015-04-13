/**
 * 
 * The EstablishConnection class will be used to bridge a connection
 * between to the raspberry Pi and send some data over a socket connection  
 * 
 * @author (G00269534)Alan Carey
 * @version 1.0
 * @since 2014-1-11
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EstablishConnection {
	
	private boolean inService = false;
	private boolean inRecordService = false;
	private Socket socket;
	private PrintWriter pw;
	private OutputStream outStream;
	private BufferedReader reader;

	/**
	 * 
	 * Default Constructor
	 * 
	 */
	
	public EstablishConnection(){
		
	}
		
	/**
	 * 
	 * Attempts to make a connection with desired client
	 * 
	 * @exception throws UnknownHostExecption, IOException
	 * 
	 * @return true Connection made | false Connection failed
	 */
	
	public boolean makeConnection() {
		
		try {
			
			socket = new Socket("192.168.1.122", 4014);
			outStream = socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(outStream);
			
			return true;
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			return false;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
			
		}		
	}
	
	/**
	 * Attempt to close connection
	 * 
	 * @exception throws IOException
	 * 
	 * @return true Connection closed | false Connection failed to close
	 */
	
	public boolean closeConnection() {
		
		try {
			
			pw.close();
			reader.close();
			outStream.close();
			socket.close();
			
			return true;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	/**
	 * 
	 * Send the data you want in a string using PrintWriter and waits for ack
	 * 
	 * @param theData The data you want to send
	 */
	
	void sendTheData(String theData){
		
		if(socket != null && outStream != null && reader != null && pw != null) {
		
			String line;

			try {	
				
				pw.println(theData);	
				pw.flush();
				
				System.out.println("Message Sent");
				
				line = reader.readLine();
			    System.out.println(line);
				
				//sent to raspberry pi 
				
			} catch (Exception e) {
				
				e.printStackTrace();
			
			} 
		}
		else
			System.out.println("Not sending that message");
		
		//HZYSEUJW James extender
	}
	
	/**
	 * 
	 * Check to see if there a connection is in service
	 * 
	 * @return Current status of the connection
	 */
	
	public boolean isInService() {
		return inService;
	}

	/**
	 * 
	 * Set the status of the connection
	 * 
	 * @param inService The status of the connection
	 */
	
	public void setInService(boolean inService) {
		this.inService = inService;
	}
	
	/**
	 * 
	 * Check to see status of recording 
	 * 
	 * @return Current status of record
	 */
	
	public boolean isInRecordService() {
		return inRecordService;
	}

	/**
	 * 
	 * Set the status of the record
	 * 
	 * @param inRecordService The status of the record
	 */
	
	public void setInRecordService(boolean inRecordService) {
		this.inRecordService = inRecordService;
	}	
}
