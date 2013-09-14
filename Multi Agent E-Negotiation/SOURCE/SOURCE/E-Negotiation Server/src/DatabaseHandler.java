import java.sql.*;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;


public class DatabaseHandler {
static Connection conn = null;
static Statement statement = null;
static ResultSet resultSet = null;
String sDriver = "org.sqlite.JDBC";
static String Database = "E-Negotiationdb";
static String sJdbc = "jdbc:sqlite";
static String sDbUrl = sJdbc + ":" + Database;

	public DatabaseHandler() {
		try {
			Class.forName(sDriver);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		
		
		
	}
  
	 public static String userExists(String userName) throws SQLException{
		 conn = DriverManager.getConnection(sDbUrl);  
		 statement = conn.createStatement();
		 resultSet = statement.executeQuery("SELECT UID FROM Users WHERE userName ='" + userName + "'");
		  String reply = " ";
		 if(!resultSet.next()){
			 return null;}
		 else{
		     
			 reply = resultSet.getString(1);
			 resultSet.close();
			 conn.close();
			 return reply;
		 }
	 }
     public static int insertProdNegInst(cPNRInfo info) throws SQLException{
    	 
    	 conn = DriverManager.getConnection(sDbUrl);  
    	 String stateString = ("INSERT INTO ProductNegotiationInstance (Type, prodName, creatorID, isActive, currParticipants, CreatorName) Values (?,?,?,?,?,?)");
    	     
    	 
    	 PreparedStatement prepState = conn.prepareStatement(stateString);
    	 prepState.setInt(1, info.BS);
    	 prepState.setString(2, info.prodName);
    	 prepState.setInt(3, info.userID);
    	 prepState.setInt(4, info.isActive);
    	 prepState.setString(5, info.prodName);
    	 prepState.setInt(5, 1);
    	 prepState.setString(6, info.userName);
    	 
         int rows = prepState.executeUpdate();
         
         int key = 0;
    	  
    	   ResultSet now = prepState.getGeneratedKeys();
    	   if(now.next()){
    	   key = now.getInt(1);
    	   }
    	   prepState.close();
    	   conn.close();
    	   return key;
      
    	
     }
     public static boolean insertProdNeg(cPNRInfo info) throws SQLException{
    	 conn = DriverManager.getConnection(sDbUrl);  
    	 String stateString = ("INSERT INTO ProductNegotiations (isCommited, Quantity, ShipTime, Shipping, Price, UID, PNID, userName) Values (?,?,?,?,?, ?,?,?)");
    	     
    	 
    	 PreparedStatement prepState = conn.prepareStatement(stateString);
    	 prepState.setBoolean(1, info.isCommited);
    	 prepState.setInt(2, info.quant);
    	 prepState.setInt(3, info.shipTime);
    	 prepState.setDouble(4, info.ship);
    	 prepState.setDouble(5, info.price);
    	 prepState.setInt(6, info.userID);
    	 prepState.setInt(7, info.PNID);
    	 prepState.setString(8, info.userName);
    	
    	 int rows = prepState.executeUpdate();
    	 prepState.close();
    	 conn.close();
    	 
    		 return true;
    	 
    	 
 
     }
public static int insertServNegInst(cPNRInfo info) throws SQLException{
    	 
    	 conn = DriverManager.getConnection(sDbUrl);  
    	 String stateString = ("INSERT INTO ServiceNegotiationInstance (Type, servName, creatorID, isActive, currParticipants, CreatorName) Values (?,?,?,?,?,?)");
    	     
    	 
    	 PreparedStatement prepState = conn.prepareStatement(stateString);
    	 prepState.setInt(1, info.BS);
    	 prepState.setString(2, info.prodName);
    	 prepState.setInt(3, info.userID);
    	 prepState.setInt(4, info.isActive);
    	 prepState.setInt(5, 1);
    	 prepState.setString(6, info.userName);
         int rows = prepState.executeUpdate();
         
         int key = 0;
    	  
    	   ResultSet now = prepState.getGeneratedKeys();
    	   if(now.next()){
    	   key = now.getInt(1);
    	   }
    	   prepState.close();
    	   conn.close();
    	   return key;
      
    	
     } 
public static boolean insertServNeg(cPNRInfo info) throws SQLException{
	 conn = DriverManager.getConnection(sDbUrl);  
	 String stateString = ("INSERT INTO ServiceNegotiations (isCommited, timeReq, Price, UID, userName, SNID) Values (?,?,?,?,?,?)");
	     
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 prepState.setBoolean(1, info.isCommited);
	 prepState.setInt(2, info.timeReq);
	 prepState.setDouble(3, info.price);
	 prepState.setInt(4, info.userID);
	 prepState.setString(5, info.userName);
	 prepState.setInt(6, info.PNID);
	
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	 
		 return true;
	 
	 

}
public static boolean insertUsers(User user) throws SQLException{
	 conn = DriverManager.getConnection(sDbUrl);  
	 String stateString = ("INSERT INTO Users (UID, userName ) Values (?,?)");
	     
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	
	 prepState.setString(2, user.getUserName());
	 prepState.setInt(1, user.getUserID());
	
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	 
		 return true;
	 
	 

}
public ArrayList<NegInst> getLastManyPNIDEntries() throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, userName, PNID, Type, prodName FROM ProductNegotiationInstance" +
	 		               " WHERE PNID BETWEEN ((Select Max(PNID) from ProductNegotiationInstance) - 1000) AND (Select Max(PNID) from ProductNegotiationInstance)  AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + prodNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstances;
}

public ArrayList<NegInst> getUserPNIDEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, creatorName, PNID, Type, prodName FROM ProductNegotiationInstance" +
	 		               " WHERE creatorID = " + userID + "  AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + prodNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstances;
}

public ArrayList<NegInst> getUserCComProdEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = (" SELECT creatorID, creatorName, PNID, Type, prodName FROM ProductNegotiationInstance " +
			              " WHERE creatorID = " + userID + "  AND IsActive = 2");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + prodNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstances;
}

public ArrayList<NegInst> getUserCComServEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = (" SELECT creatorID, creatorName, SNID, Type, servName FROM ServiceNegotiationInstance " +
			              " WHERE creatorID = " + userID + "  AND IsActive = 2");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3)));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + prodNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstances;
}
public ArrayList<NegInst> getUserSNIDEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> servNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, CreatorName, SNID, Type, servName FROM ServiceNegotiationInstance" +
	 		               " WHERE creatorID = " + userID + "  AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 servNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3)));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + servNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return servNegInstances;
}
public ArrayList<Negotiations> getNegotiationsByPNID(int PNID) throws SQLException{
	ArrayList<Negotiations> negotiations = new ArrayList<Negotiations>();
	conn = DriverManager.getConnection(sDbUrl);  
	String stateString = ("SELECT userName, Quantity, Price, Shipping, ShipTime, PNID, UID FROM ProductNegotiations" +
             " WHERE PNID =" + PNID);
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 while(results.next()){
		 negotiations.add(new Negotiations(new User(Integer.parseInt(results.getString(7)), results.getString(1)), Double.parseDouble(results.getString(3)),Double.parseDouble(results.getString(4)), 
				 							Integer.parseInt(results.getString(2)),0, false, Integer.parseInt(results.getString(5))));
	 }
	 results.close();
	 prepState.close();
	 conn.close();
	return negotiations;
	
}
public ArrayList<NegInst> getUserWhenNotCreatorPNIDEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, creatorName, ProductNegotiationInstance.PNID, Type, prodName FROM ProductNegotiationInstance " +
	                      "JOIN ProductNegotiations ON ProductNegotiationInstance.PNID = ProductNegotiations.PNID" +
	 		               " WHERE ProductNegotiations.UID = " + userID + " AND ProductNegotiationInstance.creatorID != ProductNegotiations.UID  AND IsActive = 1");
	 		               		
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 int counter = 0;
	
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	return prodNegInstances;
	
}
public ArrayList<NegInst> getUserJComProdEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, creatorName, ProductNegotiationInstance.PNID, Type, prodName FROM ProductNegotiationInstance " +
	                      "JOIN ProductNegotiations ON ProductNegotiationInstance.PNID = ProductNegotiations.PNID" +
	 		               " WHERE ProductNegotiationInstance.creatorID != ProductNegotiations.UID  AND ProductNegotiations.isCommited = 1");
	 		               		
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 int counter = 0;
	
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	return prodNegInstances;
	
}
public ArrayList<NegInst> getUserWhenNotCreatorSNIDEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> servNegInstances = new ArrayList<NegInst>();
	String stateString = ("SELECT creatorID, CreatorName, ServiceNegotiationInstance.SNID, Type, servName FROM ServiceNegotiationInstance " +
	                      "JOIN ServiceNegotiations ON ServiceNegotiationInstance.SNID = ServiceNegotiations.SNID" +
	 		               " WHERE ServiceNegotiations.UID = " + userID + " AND ServiceNegotiationInstance.creatorID != ServiceNegotiations.UID  AND IsActive = 1");
	 		               		
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 servNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3)));
	 }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	return servNegInstances;
	
}
public ArrayList<NegInst> getUserJComServEntries(int userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> servNegInstances = new ArrayList<NegInst>();
	String stateString = ("SELECT creatorID, CreatorName, ServiceNegotiationInstance.SNID, Type, servName FROM ServiceNegotiationInstance " +
	                      "JOIN ServiceNegotiations ON ServiceNegotiationInstance.SNID = ServiceNegotiations.SNID" +
	 		               " WHERE ServiceNegotiationInstance.creatorID != ServiceNegotiations.UID  AND ServiceNegotiations.isCommited = 1");
	 		               		
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 servNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3)));
	 }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	return servNegInstances;
	
}
public NegInst getNegotiationInstanceByPNID(String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	 NegInst prodNegInstance = null;
	String stateString = ("SELECT creatorID, creatorName, PNID, Type, prodName FROM ProductNegotiationInstance" +
	 		               " WHERE PNID = " + PNID + " AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	     while(results.next()){
		 prodNegInstance = new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0);
	     }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstance;
}

public NegInst getNegotiationInstanceBySNID(String sNID) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	NegInst servNegInstance = null;
	String stateString = ("SELECT creatorID, creatorName, SNID, Type, servName FROM ServiceNegotiationInstance" +
	 		               " WHERE SNID = " + sNID + " AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	   while(results.next()){
		servNegInstance = new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3));
	   }
	 
	
	 prepState.close();
	 results.close();
	 conn.close();
	
	return servNegInstance;
}

public ArrayList<Negotiations> getNegotiationsBySNID(int sNID) throws SQLException {
	ArrayList<Negotiations> negotiations = new ArrayList<Negotiations>();
	conn = DriverManager.getConnection(sDbUrl);  
	String stateString = ("SELECT userName, Price, timeReq, SNID, UID FROM ServiceNegotiations" +
             " WHERE SNID =" + sNID);
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 while(results.next()){
		 negotiations.add(new Negotiations(new User(Integer.parseInt(results.getString(5)), results.getString(1)), Double.parseDouble(results.getString(2)),
				 					0,0, Integer.parseInt(results.getString(3)), false, 0));
	 }
	 results.close();
	 prepState.close();
	 conn.close();
	return negotiations;
	
}

public void updateProdNegotiation(int pNID, double price, int shipTime, double shipp, int userID, int Quant) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	String stateString = ("UPDATE ProductNegotiations " +
						  "SET Price = ?, Quantity = ?, ShipTime = ?, Shipping = ? " +
			              "WHERE PNID =" + pNID +" AND UID =" + userID);
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 prepState.setDouble(1, price);
	 prepState.setInt(2, Quant);
	 prepState.setInt(3, shipTime);
	 prepState.setDouble(4, shipp);
	 
	 int i = prepState.executeUpdate();
	 System.out.println("UPDATED " + i);
	 prepState.close();
	 conn.close();
	
}
public void updateServNegotiation(int sNID, double price, int userID, int timeReq) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	System.out.println("usrID: " + userID +" SNID: " + sNID);
	String stateString = ("UPDATE ServiceNegotiations " +
						  "SET Price = ?, timeReq = ? " +
			              "WHERE SNID =" + sNID +" AND UID =" + userID);
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 prepState.setDouble(1, price);
	 prepState.setInt(2, timeReq);
	
	 
	 int i = prepState.executeUpdate();
	 System.out.println("UPDATED " + i);
	 prepState.close();
	 conn.close();
	
}
public ArrayList<NegInst> searchServNegotiation(String sF, String userID) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> servNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, creatorName, SNID, Type, servName FROM ServiceNegotiationInstance" +
                           " WHERE servName LIKE '%" + sF + "%'  AND IsActive = 1 AND currParticipants < 5 AND creatorID != " + userID );
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 servNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), 0, results.getInt(4), results.getInt(3)));
	 }
	 
	 prepState.close();
	 results.close();
	 conn.close();
	
	return servNegInstances;
}
public ArrayList<NegInst> searchProdNegotiations( String sF,String userID) throws SQLException{
	System.out.println(sF+" " + userID);
	conn = DriverManager.getConnection(sDbUrl);  
	ArrayList<NegInst> prodNegInstances = new ArrayList<NegInst>(); 
	String stateString = ("SELECT creatorID, creatorName, PNID, Type, prodName FROM ProductNegotiationInstance" +
	 		               " WHERE prodName LIKE '%"+ sF + "%' AND currParticipants < 5 AND creatorID != "+ userID +" AND IsActive = 1");
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 ResultSet results = prepState.executeQuery();
	 
	 while(results.next()){
		 prodNegInstances.add(new NegInst(results.getString(5), results.getInt(1), results.getString(2), results.getInt(3), results.getInt(4), 0));
	 }
	 
	 System.out.println("THIS WAS RETURNED " + prodNegInstances.size());
	 prepState.close();
	 results.close();
	 conn.close();
	
	return prodNegInstances;
}
public boolean checkProdParticipants(String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	String stateString = ("SELECT currParticipants FROM ProductNegotiationInstance " + 
			              "WHERE PNID =" + PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet results = prepState.executeQuery();
	int resulter = results.getInt(1);
	prepState.close();
	conn.close();
	if(resulter < 5)
		return true;
	else
		return false;
	
}
public boolean checkServParticipants(String SNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl);  
	String stateString = ("SELECT currParticipants FROM ServiceNegotiationInstance " + 
			              "WHERE SNID =" + SNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet results = prepState.executeQuery();
	int resulter = results.getInt(1);
	prepState.close();
	conn.close();
	if(resulter < 5)
		return true;
	else
		return false;
	
}
public boolean joinProdNegotiation(String pNID, String userID, String userName) throws NumberFormatException, SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	 String stateString = ("INSERT INTO ProductNegotiations (isCommited, Quantity, ShipTime, Shipping, Price, UID, PNID, userName) Values (?,?,?,?,?, ?,?,?)");
	     
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 prepState.setBoolean(1, false);
	 prepState.setInt(2, 0);
	 prepState.setInt(3, 0);
	 prepState.setDouble(4, 0);
	 prepState.setDouble(5, 0);
	 prepState.setInt(6, Integer.parseInt(userID));
	 prepState.setInt(7, Integer.parseInt(pNID));
	 prepState.setString(8, userName);
	
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	    if(rows > 0)
		 return true;
	    else
	     return false;
}
public boolean joinServNegotiation(String SNID, String userID, String userName) throws NumberFormatException, SQLException {
	conn = DriverManager.getConnection(sDbUrl);  
	 String stateString = ("INSERT INTO ServiceNegotiations (isCommited, Price, TimeReq, UID, SNID, userName) Values (?,?,?,?,?, ?)");
	     
	 
	 PreparedStatement prepState = conn.prepareStatement(stateString);
	 prepState.setBoolean(1, false);
	  prepState.setDouble(2, 0);
	 prepState.setInt(3, 0);
	 prepState.setInt(4, Integer.parseInt(userID));
	 prepState.setInt(5, Integer.parseInt(SNID));
	 prepState.setString(6, userName);
	
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	    if(rows > 0)
		 return true;
	    else
	     return false;
}

public void incrementProdParticipants(String pNID) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ProductNegotiationInstance " +
			  "SET currParticipants = currParticipants + 1 " +
            "WHERE PNID =" + pNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	 
}
public String retrieveUserEmail(String userID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT Email FROM Users WHERE UID =" + userID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet results = prepState.executeQuery();
	String Email = results.getString(1);
	prepState.close();
	conn.close();
	return Email;
	
}
public void incrementServParticipants(String sNID) throws SQLException {
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ServiceNegotiationInstance " +
			  "SET currParticipants = currParticipants + 1 " +
            "WHERE SNID =" + sNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	 int rows = prepState.executeUpdate();
	 prepState.close();
	 conn.close();
	 
}
public Negotiations dataMineProdNegotiations(String prodName) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT AVG(Price), AVG(Shipping), AVG(ShipTime), AVG(Quantity) FROM ProductNegotiations " +
            "JOIN ProductNegotiationInstance ON ProductNegotiations.PNID = ProductNegotiationInstance.PNID" +
              " WHERE ProductNegotiations.isCommited = 1 AND ProductNegotiationInstance.prodName = \""+ prodName + "\"");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet results = prepState.executeQuery();
	Negotiations sendNeg = new Negotiations();
	while(results.next()){
	sendNeg.Price = results.getDouble(1);
	sendNeg.Shipping = results.getDouble(2);
	sendNeg.shipTime = results.getInt(3);
	sendNeg.Quantity = results.getInt(4);
	}
	prepState.close();
	conn.close();
	return sendNeg;
}
public Negotiations dataMineServNegotiations(String servName) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT AVG(Price), AVG(timeReq) FROM ServiceNegotiations " +
            "JOIN ServiceNegotiationInstance ON ServiceNegotiations.SNID = ServiceNegotiationInstance.SNID" +
              " WHERE ServiceNegotiations.isCommited = 1 AND ServiceNegotiationInstance.servName = \""+ servName + "\"");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet results = prepState.executeQuery();
	Negotiations sendNeg = new Negotiations();
	while(results.next()){
	sendNeg.Price = results.getDouble(1);
	sendNeg.timeReq = results.getInt(2);
	}
	prepState.close();
	conn.close();
	return sendNeg;
}
public void commitProdNegotiationInstance(String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ProductNegotiationInstance SET isActive = 2 WHERE PNID =" + PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
	
}
public void commitServNegotiationInstance(String SNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ServiceNegotiationInstance SET isActive = 2 WHERE SNID =" + SNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
	
}
public void commitProdNegotiations(String PNID, String UID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ProductNegotiations SET isCommited = 1 WHERE PNID =" + PNID + " AND UID =" + UID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void commitServNegotiations(String SNID, String UID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("UPDATE ServiceNegotiations SET isCommited = 1 WHERE SNID =" + SNID + " AND UID =" + UID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawUserProdNegotiation(String UID, String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ProductNegotiations WHERE PNID ="+ PNID + " AND UID=" + UID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawCreatorProdNegotiation(String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ProductNegotiations WHERE PNID ="+ PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawCreatorProdNegotiationInstance(String PNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ProductNegotiationInstance WHERE PNID ="+ PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawUserServNegotiation(String UID, String SNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ServiceNegotiations WHERE SNID ="+ SNID + " AND UID=" + UID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawCreatorServNegotiation(String SNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ServiceNegotiations WHERE SNID ="+ SNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void withDrawCreatorServNegotiationInstance(String SNID) throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ServiceNegotiationInstance WHERE SNID ="+ SNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int rows = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public String getProdName(String PNID) throws SQLException{
	String prodName = null;
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT prodName FROM ProductNegotiationInstance WHERE PNID =" + PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		prodName = result.getString(1);
	}
	
	prepState.close();
	conn.close();
	return prodName;
}
public String getProdCreateName(String PNID) throws SQLException{
	String prodName = null;
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT CreatorName FROM ProductNegotiationInstance WHERE PNID =" + PNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		prodName = result.getString(1);
	}
	
	prepState.close();
	conn.close();
	return prodName;
}
public String getServName(String sNID) throws SQLException{
	String prodName = null;
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT servName FROM ServiceNegotiationInstance WHERE sNID =" + sNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		prodName = result.getString(1);
	}
	
	prepState.close();
	conn.close();
	return prodName;
}
public String getServCreateName(String sNID) throws SQLException{
	String prodName = null;
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT CreatorName FROM ServiceNegotiationInstance WHERE sNID =" + sNID);
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		prodName = result.getString(1);
	}
	
	prepState.close();
	conn.close();
	return prodName;
}
public Negotiations getCComProd(String PNID) throws SQLException{
	Negotiations prod = new Negotiations();
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT userName, Price, Shipping, shipTime, Quantity FROM ProductNegotiations WHERE PNID =" + PNID + " AND isCommited = 1");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		prod.user.setUserName(result.getString(1));
		prod.Shipping = result.getDouble(3);
		prod.Price = result.getDouble(2);
		prod.shipTime = result.getInt(4);
		prod.Quantity = result.getInt(5);
	}
	
	prepState.close();
	conn.close();
	return prod;
}

public Negotiations getCComServ(String sNID) throws SQLException {
	Negotiations serv = new Negotiations();
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("SELECT userName, Price, timeReq FROM ServiceNegotiations WHERE SNID =" + sNID + " AND isCommited = 1");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	ResultSet result = prepState.executeQuery();
	while(result.next()){
		serv.user.setUserName(result.getString(1));
		serv.timeReq = result.getInt(3);
		serv.Price = result.getDouble(2);
		
	}
	
	prepState.close();
	conn.close();
	return serv;
}
public void clearProdNegotiationInstances() throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ProductNegotiationInstance");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int i = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void clearProdNegotiations() throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ProductNegotiations");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int i = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void clearServNegotiations() throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ServiceNegotiations");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int i = prepState.executeUpdate();
	prepState.close();
	conn.close();
}
public void clearServNegotiationInstances() throws SQLException{
	conn = DriverManager.getConnection(sDbUrl); 
	String stateString = ("DELETE FROM ServiceNegotiationInstance");
	PreparedStatement prepState = conn.prepareStatement(stateString);
	int i = prepState.executeUpdate();
	prepState.close();
	conn.close();
}

}
