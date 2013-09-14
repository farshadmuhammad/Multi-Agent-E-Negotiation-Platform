import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Client {
int port = 80;
Socket sock;
String userID;
PrintWriter sockWriter;
BufferedReader buffR;
PrintWriter output;
BufferedReader input;
 
 
 public Client(String IP) throws UnknownHostException, IOException{
	 sock = new Socket(IP, port);	 
     sockWriter = new PrintWriter(sock.getOutputStream(), true);
     buffR = new BufferedReader(new InputStreamReader(sock.getInputStream()));
     System.out.println("Connected to Server" + sock.getInetAddress() + " " + sock.getPort());
	 
	
    // openListenerThread();
     
 }

 
      
      public void sendMessage(String message){
    	  
    	  sockWriter.println(message);
    	  sockWriter.flush();
      }
}
