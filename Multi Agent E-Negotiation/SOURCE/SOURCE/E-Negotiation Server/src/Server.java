import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

public class Server {
   int port = 80;
   static ArrayList<SThread> sockList;
	public static void main(String[] args) throws IOException, TransformerConfigurationException{
		
		try {
			new Server().run();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
	public void run() throws IOException, ParserConfigurationException, TransformerConfigurationException{
		sockList = new ArrayList<SThread>(); 
		ServerSocket servSock = new ServerSocket(port);
	    ServerFrame frame = new ServerFrame();
		 System.out.println("Server Ready");
		 System.out.println(InetAddress.getLocalHost().getHostAddress());
		 while(true){
			 
		 Socket sock = servSock.accept();
		 SThread thread = new SThread(sock);
		 thread.start();
		 sockList.add(thread);
		
		 
		 }
		 
		 
	 }
   public static void sendToSeveral(ArrayList<Integer> IDs, String Message) throws IOException{
	   for(int i = 0 ; i < IDs.size(); i++){
		  
		   for(int j = 0 ; j < sockList.size(); j++){
			   
			   if(sockList.get(j).userID1 == IDs.get(i)){
				   sockList.get(j).sendMessageGlobal(Message);
				   
			   }
		   }
	   }
   }
   public static void sendToSingle(int ID, String Message) throws IOException{
	       System.out.println("THIS IS THE ID: " + ID);
		   for(int j = 0 ; j < sockList.size(); j++){
			   if(sockList.get(j).userID1 == ID){
				   sockList.get(j).sendMessageGlobal(Message);
			   }
		   }
	   
   }
   public static void sendToAll(String Message) throws IOException{
       
	   for(int j = 0 ; j < sockList.size(); j++){
		  
			   sockList.get(j).sendMessageGlobal(Message);
		   
	   }
   
}
  public static void printSocketInfo(){
	  for(int i = 0; i < sockList.size(); i++){
		  System.out.println("Socket" + i +" userID " + sockList.get(i).userID1);
	  }
  }

	public static boolean isOnline (int ID){
		 for(int j = 0 ; j < sockList.size(); j++){
			   if(sockList.get(j).userID1 == ID){
				   return true;
			   }
		   }
		 return false;
	}
}