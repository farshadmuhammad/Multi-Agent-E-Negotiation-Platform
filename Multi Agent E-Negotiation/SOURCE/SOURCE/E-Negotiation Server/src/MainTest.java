import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;


public class MainTest {
	
		 public static void main (String[] args) throws SQLException, ParserConfigurationException, TransformerException{
			 DatabaseHandler dbMan = new DatabaseHandler();
			 
			 ArrayList<String> servNames = new ArrayList<String>();
			 servNames.add(new String("Pool Cleaning"));
			 servNames.add(new String("Lawn Mowing"));
			 servNames.add(new String("Pizza Delivery Man"));
			 servNames.add(new String("Engine Repair"));
			 servNames.add(new String("Tax Audit"));
			 servNames.add(new String("Java Tutorials"));
			 servNames.add(new String("Database Concurrency Checks"));
			 servNames.add(new String("Mandarin Interpretor"));
			 servNames.add(new String("Math Tutoring"));
			 servNames.add(new String("MathLab Statistics Tutorials"));
			 
             ArrayList<User> users = new ArrayList<User>();
			 
			 users.add(new User(100792339, "Farshad Muhammad"));
			 users.add(new User(100825344, "Kenan Thompson"));
			 users.add(new User(559922, "Andrew Hughes"));
			 users.add(new User(559977, "John Hughes"));
			 users.add(new User(8889922, "Mathew Mckinnon"));
			 users.add(new User(10102233, "Lu Bao"));
			 users.add(new User(55223232, "Si Wang Mu"));
			 
			 ArrayList<String> products = new ArrayList<String>();
			 
			 products.add(new String("Hi Res TV"));
			 products.add(new String("Sony Camcorder"));
			 products.add(new String("IPhone 5"));
			 products.add(new String("Samsung S4"));
			 products.add(new String("Linen BedSheets"));
			 products.add(new String("Men's Suits"));
			 products.add(new String("Dual Band Modems"));
			 products.add(new String("Evolutionary Psychology 3rd Edition"));
			 products.add(new String("Sens Jerseys"));
			 products.add(new String("Fender Guitars"));
			 
			 MessageBuilder haha = new MessageBuilder();
			 
			 //insertProdNegs(users, products);
			 // insertServNegs(users, servNames);
			 
			  //insertUsers(users);
			 
	     	dbMan.clearProdNegotiationInstances();
	     	dbMan.clearProdNegotiations();
	     	dbMan.clearServNegotiationInstances();
	     	dbMan.clearServNegotiations();
				
		 }
		public static void insertUsers(ArrayList<User> users) throws SQLException{
			DatabaseHandler dbHand = new DatabaseHandler();
             
			 for(int i = 0; i < users.size(); i++){
				dbHand.insertUsers(users.get(i));
			  }
		  }
        public static void insertProdNegs(ArrayList<User> users, ArrayList<String> prodNames) throws SQLException{
        	DatabaseHandler dbHand = new DatabaseHandler();
        	 cPNRInfo info = new cPNRInfo(100792339, 200.00, 1, 50.00, 14, 2, "Hi-res tvs", " ");
        	 int uI = 0;
        	 int pI = 0;
        	 int PNID = 0;
        	 
        	for(int i = 0; i < 1000; i++){
        		if(uI > 6)
			    	uI = 0;
			    if(pI > 9)
			    	pI = 0;
				info.userID = users.get(uI).getUserID();
				info.prodName = prodNames.get(pI);
				info.userName = users.get(uI).getUserName();
				PNID = dbHand.insertProdNegInst(info);
				info.PNID = PNID;
				dbHand.insertProdNeg(info);
			    uI++;
			    pI++;
			    
			    
        	}
        	
        }
        public static void insertServNegs(ArrayList<User> users, ArrayList<String> servNames) throws SQLException{
        	DatabaseHandler dbHand = new DatabaseHandler();
        	 cPNRInfo info = new cPNRInfo(100792339, 200.00, 1, 50.00, 14, 2, "Hi-res tvs", " ");
        	 int uI = 0;
        	 int pI = 0;
        	 int PNID = 0;
        	 info.timeReq = 15;
        	 
        	for(int i = 0; i < 1000; i++){
        		if(uI > 6)
			    	uI = 0;
			    if(pI > 9)
			    	pI = 0;
				info.userID = users.get(uI).getUserID();
				info.prodName = servNames.get(pI);
				info.userName = users.get(uI).getUserName();
				PNID = dbHand.insertServNegInst(info);
				info.PNID = PNID;
				dbHand.insertServNeg(info);
			    uI++;
			    pI++;
			    
			    
        	}
        	
        }

}
