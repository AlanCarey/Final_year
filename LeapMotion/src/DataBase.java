import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

public class DataBase {

	private String dbURL = "jdbc:mysql://localhost:3306/robotarmlogin";
	private String username = "root";
	private String password = "";

	private String currentUsername = "";
	private String preferred = "";
	
	private int result;

	// reference to database connection
	private Connection connection;
	private PreparedStatement sqlInsertTime;
	private PreparedStatement sqlMaxUserid;

	private CreateAndShowGui showGUI;
	
	public DataBase(CreateAndShowGui showGUI) {
		
		this.showGUI = showGUI;
		
	}
	
	//	public static void main(String[] args) throws Exception {
	//
	//		DataBase database = new DataBase();
	//		database.connect();
	//		database.readData();
	//
	//
	//	}

//	public void readDat1a() throws SQLException {
//		// sqlMaxUserid = connection.prepareStatement( "SELECT (StartTime,BreakIn,BreakOut,FinishTime) FROM staffhours WHERE NAME = Ian" );
//		//  ResultSet maxuserid = sqlMaxUserid.executeQuery();
//
//		//	   if(maxuserid.next())
//		//	   {
//		//		   String st =  maxuserid.getString(1);
//		//		   String bi =  maxuserid.getString(2);
//		//		   String bo =  maxuserid.getString(3);
//		//		String ft = null;
//		//			String name = "Alan";
//		//		   
//		//		   
//		//		   
//		//		   System.out.print("Data Inserted" + st);
//		//		   System.out.print("\nData Inserted" + bi);
//		//		   System.out.print("\nData Inserted" + bo);
//		//		   System.out.print("Data Inserted" + ft);
//		//		String userName = "Ian";
//		//
//		String query = "SELECT Password,LoginTime,LogoutTime,notes FROM login " +
//				"WHERE Name = '"+name+"'";
//		Statement stmt = connection.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
//
//		if(rs.next())
//		{
//			String st =  rs.getString("Password");
//			String bi =  rs.getString("LoginTime");
//			String bo =  rs.getString("LogoutTime");
//			ft =  rs.getString("Notes");
//
//
//
//
//
//
//			System.out.print("st" + st);
//			System.out.print("\nbi\t" + bi);
//			if(ft!=null)
//			{
//				System.out.print("SHE IS EMPTY");
//			}
//			System.out.print("\nbo\t" + bo);
//			System.out.print("\nft\t" + ft);
//
//		}
//		if(ft==null)
//		{
//			System.out.print("SHE IS EMPTY");
//		}
//
//		connection.close();
//
//		//		int i = 0;
//		//
//		//		while(i < 5) {
//		/*
//			try{	 
//
//				String user = "" ;
//				String password = "";
//				String time = "";
//				String notes = "";
//
//				Scanner sc = new Scanner(System.in);
//				System.out.println("Enter Your user name: ");
//				user = sc.next();
//
//				System.out.println("Enter Your password: ");
//				password = sc.next();
//
//				System.out.println("Notes?: ");
//				notes = sc.next();
//
//				Date d = new Date(System.currentTimeMillis());
//				System.out.print(d);
//
//				time = d.toString();
//
//				sqlInsertTime = connection.prepareStatement(
//						"UPDATE login SET LoginTime = '"+time+"', Password = '"+password+"', Notes = '"+notes+"' " + 
//						"WHERE Name = '"+user+"' ");
//
//
//				result = sqlInsertTime.executeUpdate();
//
//				if ( result == 0 ) {
//					connection.rollback(); // rollback update
//
//				}      
//
//				connection.commit();        
//				//i++;
//			}
//			catch ( SQLException sqlException ) {
//				// rollback transaction
//				System.out.println("SQL exception = " + sqlException);
//			}
//
//			System.out.print("Data Inserted");
//
//			sqlInsertTime.close();
//			// sqlMaxUserid.close();
//			//connection.close();
//
//
////		}
//
//		connection.close();
//		 */
//	}

	public void currentUserReadData() throws Exception{

		String query = "SELECT LoginTime,LogoutTime,PreferredHand,Notes FROM loginusers " +
				"WHERE Name = '" + currentUsername + "'";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		if(rs.next())
		{
			String login =  rs.getString("LoginTime");
			String logout =  rs.getString("LogoutTime");
			String notes =  rs.getString("Notes");
			preferred = rs.getString("PreferredHand");
			
			showGUI.jTextFieldCurUserName.setText(currentUsername);
			showGUI.jTextFieldCurLoginTime.setText(login);
			showGUI.jTextFieldCurlogoutTime.setText(logout);
			showGUI.jTextAreaCurNotes.setText(notes);
			showGUI.jTextFieldCurPreferredhand.setText(preferred);
		}

	}

	public void signout() throws Exception{
		
		updateLogoutTime();
		connection.close();	
		showGUI.printStatus("Bye " + currentUsername);

	}
	
	public void updateLoginTime() throws Exception { 
		
		Date d = new Date(System.currentTimeMillis());
		System.out.print(d);

		String time = d.toString();

		sqlInsertTime = connection.prepareStatement(
				"UPDATE loginusers SET LoginTime = '"+time+"' " + 
				"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsertTime.executeUpdate();

		if ( result == 0 ) {
			connection.rollback(); // rollback update

		}      

		sqlInsertTime.close();
		
		connection.commit();        
		
	}

	public void updateLogoutTime() throws Exception{
		
		Date d = new Date(System.currentTimeMillis());
		System.out.print(d);

		String time = d.toString();

		sqlInsertTime = connection.prepareStatement(
				"UPDATE loginusers SET LogoutTime = '"+time+"' " + 
				"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsertTime.executeUpdate();

		if ( result == 0 ) {
			connection.rollback(); // rollback update

		}      

		sqlInsertTime.close();
		
		connection.commit();        
		
	}
	
	public void updateData() throws Exception{

		String notes = showGUI.jTextAreaNewNotes.getText();
		String preferred = showGUI.jComboPreferredHand.getSelectedItem().toString();	
		System.out.println(notes + preferred);
		
		sqlInsertTime = connection.prepareStatement(
				"UPDATE loginusers SET PreferredHand = '"+ preferred +"', Notes = '"+ notes +"' " + 
						"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsertTime.executeUpdate();

		if ( result == 0 ) {
			connection.rollback(); // rollback update

		}      

		connection.commit();        
		
	}

	public boolean login() throws Exception{

		currentUsername = showGUI.jTextFieldUserName.getText();
		char[] passwordtemp = showGUI.jPasswordFieldPassword.getPassword();
		
		String password = new String(passwordtemp);
		
		
		
		if(currentUsername.isEmpty() || password.isEmpty()) {
			
			showGUI.printStatus("No Data In either Field, Try Again");
		
		}
		else {

			//get query and compare password & name
			String query = "SELECT Password FROM loginusers " +
					"WHERE Name = '"+currentUsername+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			if(rs.next()) {
				
				System.out.println("In here");
				//String dbUsername = rs.getString("Name");
				String dbPassword = rs.getString("Password");
				
				System.out.println(password + dbPassword);
				
				if(password.equals(dbPassword)) {
						
					showGUI.printStatus("Current User: " + currentUsername);
						
					JOptionPane.showMessageDialog(null, "Hello " + currentUsername);
					
					return true;
					
				}
				else {
		
					showGUI.printStatus("Invalid Password");
						
					return false;
				}
			}
			else {
								
				showGUI.printStatus("Invalid Username");
					
				return false;
			}
		}
		return false;
	}

	public void connect() throws Exception {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connected to database....\n");
			connection.setAutoCommit( false );
	
	}
	
	public String getCurrentUsername() {
		return currentUsername;
	}

	public void setCurrentUsername(String currentUsername) {
		this.currentUsername = currentUsername;
	}

	public String getPreferred() {
		return preferred;
	}

	public void setPreferred(String preferred) {
		this.preferred = preferred;
	}
} 