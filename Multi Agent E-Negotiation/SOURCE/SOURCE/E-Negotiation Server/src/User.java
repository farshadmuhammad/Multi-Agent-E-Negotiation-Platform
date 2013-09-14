
public class User {
	private int userID;
	private String userName;
	
	public User(){
		
		userID = 0;
		userName = " ";
		
	}
     public User(int id, String name){
		
		userID = id;
		userName = name;
		
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
