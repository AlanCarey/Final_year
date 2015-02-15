import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class EstablishConnection {
	
	private boolean inService = false;

	Socket socket = null;
	PrintWriter pw = null;
	OutputStream outStream = null;
	BufferedReader reader = null;
	
	CreateAndShowGui showGUI = null;
	
	StringBuilder sb;
	
	public EstablishConnection(CreateAndShowGui showGUI){
		
		this.showGUI = showGUI;
		
	}
	
	
	void sendTheData(String theData){
		
		sb = new StringBuilder();
		
		String line;
		try {	
			
			socket = new Socket("192.168.0.103", 4014);
			
			outStream = socket.getOutputStream();
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			pw = new PrintWriter(outStream);
			
			pw.print(theData);
			
			pw.flush();
		    
			while ((line = reader.readLine()) != null)
		        sb.append(line).append("\n");
			
		    System.out.println(sb.toString());
		
			//sent to raspberry pi 
			
		} catch (Exception e) {
			
			e.printStackTrace();
		
		} finally{

			
			try {
				pw.close();
				outStream.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		
	}
	
	public boolean isInService() {
		return inService;
	}


	public void setInService(boolean inService) {
		this.inService = inService;
	}
	
}