
public class User {
	private int userID;
	private String userName;
	
	public User(){
		
		userID = 0;
		userName = " ";
		
	}
    public User(int ID, String uN){
		
		userID = ID;
		userName = uN;
		
	}
	public void setUserID(int ID){
	  userID = ID;
	}
    public void setUserName(String name){
    	userName = name;
    }
    public String getUserName(){
    	return userName;
    }
    public int getUserID(){
    	return userID;
    }
}
