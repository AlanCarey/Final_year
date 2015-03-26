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

	protected Socket socket;
	protected PrintWriter pw;
	protected OutputStream outStream;
	protected BufferedReader reader;
	
	protected CreateAndShowGui showGUI;
	
	public EstablishConnection(CreateAndShowGui showGUI){
		
		this.showGUI = showGUI;
		
	}
		
	public boolean makeConnection() {
		
		try {
			socket = new Socket("192.168.1.100", 4014);
			outStream = socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(outStream);
			
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	public boolean closeConnection() {
		
		try {
			pw.close();
			reader.close();
			outStream.close();
			socket.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
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
	
	public boolean isInService() {
		return inService;
	}

	public void setInService(boolean inService) {
		this.inService = inService;
	}
	
	public boolean isInRecordService() {
		return inRecordService;
	}


	public void setInRecordService(boolean inRecordService) {
		this.inRecordService = inRecordService;
	}	
}
