import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DataBase {

	private String dbURL = "jdbc:mysql://localhost:3306/robotarmlogin";
	private String username = "root";
	private String password = "";

	private String currentUsername = "";
	private String preferred = "";
	
	private String login = "";
	private String logout = "";
	private String notes = "";
	
	private int result;

	// reference to database connection
	private Connection connection;
	private PreparedStatement sqlInsert;

	private CreateAndShowGui showGUI;
	
	public DataBase(CreateAndShowGui showGUI) {
		
		this.showGUI = showGUI;
		
	}
	
	public void addNewUser() throws Exception {
		
		String newUser = "";
		char[] newPasswordTemp;
		String newPassword = "";
		
		newUser = showGUI.jTextFieldCreateUserName.getText();
		newPasswordTemp = showGUI.jPasswordFieldCreatePassword.getPassword();
		
		newPassword = new String(newPasswordTemp);
		
		if(newUser.equals("") || newPassword.equals("")) {
			
			//dont add to dbJPass
			JOptionPane.showMessageDialog(null, "One or More Fileds is Empty");
			
		}
		else if(!newUser.equals("") && !newPassword.equals("")) {
			
			sqlInsert = connection.prepareStatement("INSERT INTO `robotarmlogin`.`loginusers` (`Name`, `Password`, `LoginTIme`, `LogoutTime`, `Preferredhand`, `Notes`)"
					+ " VALUES ('"+newUser+"', '"+newPassword+"', '', '', '', '')");
			
			connection.createStatement();

			result = sqlInsert.executeUpdate();

			if ( result == 0 ) {
				connection.rollback(); // rollback update

			}      
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "You Are Now Added: " + newUser);
			
		}        
	}
	
	public void currentUserReadData() throws Exception{

		String query = "SELECT LoginTime,LogoutTime,PreferredHand,Notes FROM loginusers " +
				"WHERE Name = '" + currentUsername + "'";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		if(rs.next())
		{
			login =  rs.getString("LoginTime");
			logout =  rs.getString("LogoutTime");
			notes =  rs.getString("Notes");
			preferred = rs.getString("PreferredHand");
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					
					showGUI.jTextFieldCurUserName.setText(currentUsername);
					showGUI.jTextFieldCurLoginTime.setText(login);
					showGUI.jTextFieldCurlogoutTime.setText(logout);
					showGUI.jTextAreaCurNotes.setText(notes);
					showGUI.jTextFieldCurPreferredhand.setText(preferred);
					
				}
			});
		}

	}

	public void closeConnection() throws Exception {
		
		connection.close();	
		
	}
	
	public void signout() throws Exception{
		
		updateLogoutTime();
		connection.close();	
		showGUI.printStatus("Bye " + currentUsername);

	}
	
	public void updateLoginTime() throws Exception { 
		
		Date d = new Date(System.currentTimeMillis());
		//System.out.print(d);

		String time = d.toString();

		sqlInsert = connection.prepareStatement(
				"UPDATE loginusers SET LoginTime = '"+time+"' " + 
				"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsert.executeUpdate();

		if ( result == 0 ) {
			connection.rollback(); // rollback update

		}      

		sqlInsert.close();
		
		connection.commit();        
		
	}

	public void updateLogoutTime() throws Exception{
		
		Date d = new Date(System.currentTimeMillis());
		//System.out.print(d);

		String time = d.toString();

		sqlInsert = connection.prepareStatement(
				"UPDATE loginusers SET LogoutTime = '"+time+"' " + 
				"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsert.executeUpdate();

		if ( result == 0 ) {
			connection.rollback(); // rollback update

		}      

		sqlInsert.close();
		
		connection.commit();        
		
	}
	
	public void updateData() throws Exception{

		String notes = showGUI.jTextAreaNewNotes.getText();
		String preferred = showGUI.jComboPreferredHand.getSelectedItem().toString();	
		//System.out.println(notes + preferred);
		
		sqlInsert = connection.prepareStatement(
				"UPDATE loginusers SET PreferredHand = '"+ preferred +"', Notes = '"+ notes +"' " + 
						"WHERE Name = '"+currentUsername+"' ");


		result = sqlInsert.executeUpdate();

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
			
			JOptionPane.showMessageDialog(null, "No Data Entered in Either Field, Try Again");
		}
		else {

			//get query and compare password & name
			String query = "SELECT Password FROM loginusers " +
					"WHERE Name = '"+currentUsername+"'";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			if(rs.next()) {
				
				//System.out.println("In here");
				//String dbUsername = rs.getString("Name");
				String dbPassword = rs.getString("Password");
				
				//System.out.println(password + dbPassword);
				
				if(password.equals(dbPassword)) {
						
					showGUI.printStatus("Current User: " + currentUsername);
						
					JOptionPane.showMessageDialog(null, "Hello " + currentUsername);
					
					return true;
					
				}
				else {
		
					JOptionPane.showMessageDialog(null, "Invalid Password");
						
					return false;
				}
			}
			else {
								
				JOptionPane.showMessageDialog(null, "Invalid Username");
					
				return false;
			}
		}
		return false;
	}

	public void connect() throws Exception {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(dbURL, username, password);
			//System.out.println("Connected to database....\n");
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