
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MessageBuilder  {
	DocumentBuilderFactory docFac;
	DocumentBuilder docBuild;
	TransformerFactory transFac;
	Transformer transform;
	int userID2;
	Emailer sender = new Emailer();
	DatabaseHandler dbMan;
public MessageBuilder() throws ParserConfigurationException, TransformerConfigurationException{
	docFac = DocumentBuilderFactory.newInstance();
	docBuild = docFac.newDocumentBuilder();
   
    transFac = TransformerFactory.newInstance();
    transform = transFac.newTransformer();
    dbMan = new DatabaseHandler();
}

public String loginReplyMessage(String userID) throws NumberFormatException, IOException{
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	String ID;
	try {
		if( (ID = DatabaseHandler.userExists(userID)) != null){
			
			rootElement = xmlDoc.createElement("loginaccepted");
			Element userIDNum = xmlDoc.createElement("userID");
			userIDNum.setAttribute("userID", ID);
		    rootElement.appendChild(userIDNum);
		    userID2 = Integer.parseInt(ID);
		    createListUserProdNegs(ID);
		    createListUserServNegs(ID);
		    createListUserNotCreatorProdNegs(ID);
		    createListUserNotCreatorServNegs(ID);
		    createListUserCComProdNegs(ID);
		    createListUserCComServNegs(ID);
		    createListUserJComProdNegs(ID);
		    createListUserJComServNegs(ID);
		    Server.printSocketInfo();
		    		    
		}
		else{
			rootElement = xmlDoc.createElement("loginrejected");
		}
	} catch (DOMException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	xmlDoc.appendChild(rootElement);
    
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
   } catch (TransformerConfigurationException e) {
   e.printStackTrace();
   } catch (TransformerException e) {
   e.printStackTrace();
   }
	return returner;
	
    }
  public String createProdNegReply(cPNRInfo info) throws SQLException, TransformerException{
	  int PNID = dbMan.insertProdNegInst(info);
	  boolean check = false;
	 
	  
	  if(PNID != 0){
		info.PNID = PNID;
		 check = dbMan.insertProdNeg(info);
	  }
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("createProdNegotiationReply");
				Element servNeg = xmlDoc.createElement("ProdNegotiation");
				
				servNeg.setAttribute("PNID", Integer.toString(PNID));
				
			   rootElement.appendChild(servNeg);
			   xmlDoc.appendChild(rootElement);
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
			return returner;
  }
  public String createServNegReply(cPNRInfo info) throws SQLException, TransformerException{
	  int PNID = dbMan.insertServNegInst(info);
	  boolean check = false;
	  if(PNID != 0){
		  
		info.PNID = PNID;
		 check = dbMan.insertServNeg(info);
	  }
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("createServNegotiationReply");
				Element servNeg = xmlDoc.createElement("ServNegotiation");
				
				servNeg.setAttribute("SNID", Integer.toString(PNID));
				
			   rootElement.appendChild(servNeg);
			   xmlDoc.appendChild(rootElement);
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
			return returner;
  }
  public void createListUserProdNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserPNIDEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myCProdNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ProdName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.get(i).PNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				System.out.println("THIS IS CURRENT USERID " + userID2);
				Server.sendToSingle(userID2, returner);
				//return returner;
  }
  public void createListUserServNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserSNIDEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myCServNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ServName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(prodNegs.get(i).SNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				Server.sendToSingle(userID2, returner);
				//return returner;
  }
  public void createListUserNotCreatorProdNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserWhenNotCreatorPNIDEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myJProdNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ProdName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.get(i).PNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				System.out.println("THIS IS CURRENT USERID " + userID2);
				Server.sendToSingle(userID2, returner);
				//return returner;
  }
  public void createListUserJComProdNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserJComProdEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myJComProdNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ProdName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.get(i).PNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				System.out.println("THIS IS CURRENT USERID " + userID2);
				Server.sendToSingle(userID2, returner);
				//return returner;
  }
  public void createListUserNotCreatorServNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserWhenNotCreatorSNIDEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myJServNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ServName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(prodNegs.get(i).SNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				Server.sendToSingle(userID2, returner);
				
  }
  public void createListUserJComServNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserJComServEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myJComServNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ServName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(prodNegs.get(i).SNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				Server.sendToSingle(userID2, returner);
				
  }
  public String createProdNegotiationsReply(String PNID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<Negotiations> prodNegs = dbMan.getNegotiationsByPNID(Integer.parseInt(PNID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("ProdNegotiationsByPNID");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("User" + i);
					Negotiation.setAttribute("Price", Double.toString(prodNegs.get(i).Price));
					Negotiation.setAttribute("Shipping", Double.toString(prodNegs.get(i).Shipping));
					Negotiation.setAttribute("ShippingTime", Integer.toString(prodNegs.get(i).shipTime));
					Negotiation.setAttribute("Quantity", Integer.toString(prodNegs.get(i).Quantity));
					Negotiation.setAttribute("PNID", PNID);
					Negotiation.setAttribute("userName", prodNegs.get(i).user.getUserName());
					Negotiation.setAttribute("UID", Integer.toString(prodNegs.get(i).user.getUserID()));
								
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
			    
				return returner;
  }

public String createProdNegotiationsInstanceReply(String PNID) throws TransformerException, SQLException, IOException {
	NegInst prodNegs = dbMan.getNegotiationInstanceByPNID(PNID);
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("RetrievedProductNegotiationInstance");
				
				 
					Element Negotiation = xmlDoc.createElement("Negotiation");
					if(prodNegs != null){
					Negotiation.setAttribute("ProdName", prodNegs.prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.PNID));
					Negotiation.setAttribute("creatorName", prodNegs.userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.Type));			
					}
				
				rootElement.appendChild(Negotiation);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				System.out.println("HERE IS THE USERID");
				Server.sendToSingle(userID2, returner);
			   	if(prodNegs != null)
				return createProdNegotiationsReply(PNID);
			   	else
			   	return "";
}

public String createServNegotiationsInstanceReply(String sNID) throws NumberFormatException, TransformerException, SQLException, IOException {
	// TODO Auto-generated method stub
	NegInst servNegs = dbMan.getNegotiationInstanceBySNID(sNID);
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("RetrievedServiceNegotiationInstance");
				
				
					Element Negotiation = xmlDoc.createElement("Negotiation");
					if(servNegs != null){
					Negotiation.setAttribute("ProdName", servNegs.prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(servNegs.SNID));
					Negotiation.setAttribute("creatorName", servNegs.userName);
					Negotiation.setAttribute("creatorID", Integer.toString(servNegs.creatorID));
					Negotiation.setAttribute("Type", Integer.toString(servNegs.Type));			
					}
				
				rootElement.appendChild(Negotiation);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
			   	Server.sendToSingle(userID2, returner);
			   	if(servNegs != null)
			   	return createServNegotiationsReply(sNID);
			   	else
			   		return "";
}
public String createServNegotiationsReply(String sNID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<Negotiations> servNegs = dbMan.getNegotiationsBySNID(Integer.parseInt(sNID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("ServNegotiationsBySNID");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < servNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("User" + i);
					Negotiation.setAttribute("Price", Double.toString(servNegs.get(i).Price));
					Negotiation.setAttribute("timeReq", Integer.toString(servNegs.get(i).timeReq));
					Negotiation.setAttribute("SNID", sNID);
					Negotiation.setAttribute("userName", servNegs.get(i).user.getUserName());
					Negotiation.setAttribute("UID", Integer.toString(servNegs.get(i).user.getUserID()));
								
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
			    
				return returner;
}

public String createUpdateProdNegotiationRep(int pNID, double price,int shipTime, double shipp, int userID, int Quant) throws SQLException, TransformerException {
	// TODO Auto-generated method stub
	dbMan.updateProdNegotiation(pNID,price, shipTime, shipp, userID, Quant);
	  StringWriter stringWriter = new StringWriter();
			String returner = " ";
		
			Document xmlDoc; 
			xmlDoc = docBuild.newDocument();
			Element rootElement = null;
			String ID;
			
					rootElement = xmlDoc.createElement("UpdateProdNegotiation");
					
					
						Element Negotiation = xmlDoc.createElement("Negotiation");
						
						Negotiation.setAttribute("PNID", Integer.toString(pNID));
						Negotiation.setAttribute("Price", Double.toString(price));
						Negotiation.setAttribute("Quant", Integer.toString(Quant));	
						Negotiation.setAttribute("Shipping", Double.toString(shipp));
						Negotiation.setAttribute("ShipTime", Integer.toString(shipTime));
						Negotiation.setAttribute("userID", Integer.toString(userID));
						
					    
					
					rootElement.appendChild(Negotiation);
					xmlDoc.appendChild(rootElement);
					
					
				    
					Source source = new DOMSource(xmlDoc);
					Result result = new StreamResult(stringWriter);
					transform.transform(source, result);
					return returner = stringWriter.getBuffer().toString();
}

public String createUpdateServNegotiationRep(int sNID, double price, int userID, int timeReq) throws SQLException, TransformerException {
	// TODO Auto-generated method stub
	dbMan.updateServNegotiation(sNID,price, userID, timeReq);
	  StringWriter stringWriter = new StringWriter();
			String returner = " ";
		
			Document xmlDoc; 
			xmlDoc = docBuild.newDocument();
			Element rootElement = null;
			String ID;
			
					rootElement = xmlDoc.createElement("UpdateServNegotiation");
					
					
						Element Negotiation = xmlDoc.createElement("Negotiation");
						
						Negotiation.setAttribute("SNID", Integer.toString(sNID));
						Negotiation.setAttribute("Price", Double.toString(price));
						Negotiation.setAttribute("TimeReq", Integer.toString(timeReq));	
						Negotiation.setAttribute("userID", Integer.toString(userID));
						
					    
					
					rootElement.appendChild(Negotiation);
					xmlDoc.appendChild(rootElement);
					
					
				    
					Source source = new DOMSource(xmlDoc);
					Result result = new StreamResult(stringWriter);
					transform.transform(source, result);
					return returner = stringWriter.getBuffer().toString();
}

public String createProductSearchReply(String search, String userID) throws SQLException, TransformerException {
	  ArrayList<NegInst> prodNegs = dbMan.searchProdNegotiations(search, userID);
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("mySProdNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ProdName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.get(i).PNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				
				
				return returner;
				
}
public String createServiceSearchReply(String search, String userID) throws SQLException, TransformerException {
	  ArrayList<NegInst> prodNegs = dbMan.searchServNegotiation(search, userID);
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("mySServNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("servName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(prodNegs.get(i).SNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				
				
				return returner;
				
}

public String createJoinProdNegRep(String pNID, String userID, String userName, ArrayList<Integer> IDList) throws SQLException, TransformerException, IOException {
	boolean joinCheck = false;
	 StringWriter stringWriter = new StringWriter();
		String returner = " ";
	   
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		rootElement = xmlDoc.createElement("joinProdNegotiationReply");
		Element joinRep = xmlDoc.createElement("joinRep");
	if(dbMan.checkProdParticipants(pNID)){
		joinCheck = dbMan.joinProdNegotiation(pNID, userID, userName);
		if(joinCheck){
			dbMan.incrementProdParticipants(pNID);
			joinRep.setAttribute("reply", "accepted");
			joinProdReplyToRest(pNID, userID, userName, IDList);
		}
		else{
			joinRep.setAttribute("reply", "rejected");
		}
	}
	else{
		joinRep.setAttribute("reply", "rejected");
	}
	rootElement.appendChild(joinRep);
	xmlDoc.appendChild(rootElement);
	
	
    
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	return returner;
}

public void joinProdReplyToRest(String pNID, String userID, String userName,
		ArrayList<Integer> iDList) throws TransformerException, IOException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
   
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardProdNegotiationReply");
	Element joinRep = xmlDoc.createElement("joinRep");
	joinRep.setAttribute("PNID", pNID);
	joinRep.setAttribute("userID", userID);
	joinRep.setAttribute("userName", userName);
	rootElement.appendChild(joinRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToAll(returner);
	
}

public String createJoinServNegRep(String sNID, String userID, String userName, ArrayList<Integer> IDList) throws NumberFormatException, SQLException, TransformerException, IOException {
	boolean joinCheck = false;
	 StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		rootElement = xmlDoc.createElement("joinServNegotiationReply");
		Element joinRep = xmlDoc.createElement("joinRep");
	if(dbMan.checkServParticipants(sNID)){
		joinCheck = dbMan.joinServNegotiation(sNID, userID, userName);
		if(joinCheck){
			dbMan.incrementServParticipants(sNID);
			joinServReplyToRest(sNID, userID, userName, IDList);
			joinRep.setAttribute("reply", "accepted");
		}
		else{
			joinRep.setAttribute("reply", "rejected");
		}
	}
	else{
		joinRep.setAttribute("reply", "rejected");
	}
	rootElement.appendChild(joinRep);
	xmlDoc.appendChild(rootElement);
	
	
   
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	return returner;
}
public void joinServReplyToRest(String sNID, String userID, String userName,
	ArrayList<Integer> iDList) throws TransformerException, IOException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
   
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardServNegotiationReply");
	Element joinRep = xmlDoc.createElement("joinRep");
	joinRep.setAttribute("SNID", sNID);
	joinRep.setAttribute("userID", userID);
	joinRep.setAttribute("userName", userName);
	rootElement.appendChild(joinRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToAll(returner);
	
}

public void createCommitProdNegotiationReply(String pNID, String userID,
		String userName, String price, String quantity, String shipTime,
		String shipping, String prodName, String creatorName, String creatorID, ArrayList<Integer> iDList) throws TransformerException, IOException, SQLException {
	
		StringWriter stringWriter = new StringWriter();
		String returner = " ";
	    dbMan.commitProdNegotiationInstance(pNID);
	    dbMan.commitProdNegotiations(pNID, userID);
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		rootElement = xmlDoc.createElement("forwardCommitProdNegotiationReply");
		Element commRep = xmlDoc.createElement("joinRep");
		
		commRep.setAttribute("PNID", pNID);
		commRep.setAttribute("userID", userID);
		commRep.setAttribute("Price", price);
		commRep.setAttribute("prodName", prodName);
		commRep.setAttribute("Quantity", quantity);
		commRep.setAttribute("ShipTime", shipTime);
		commRep.setAttribute("Shipping", shipping);
		commRep.setAttribute("creatorName", creatorName);
		
		rootElement.appendChild(commRep);
		xmlDoc.appendChild(rootElement);
		Source source = new DOMSource(xmlDoc);
		Result result = new StreamResult(stringWriter);
		transform.transform(source, result);
		returner = stringWriter.getBuffer().toString();
		Server.sendToSeveral(iDList, returner);
		sendCommitProdEmails(pNID, userID, userName, price, quantity, shipTime, shipping, prodName, creatorName,
				            creatorID, iDList); 
		
		
	
}

private void sendCommitProdEmails(String pNID, String userID,
		String userName, String price, String quantity, String shipTime,
		String shipping, String prodName, String creatorName, String creatorID, ArrayList<Integer> iDList) throws SQLException {
	
	sender.sendProdCommitEmailToCreator(pNID, userID, userName, price, quantity, shipTime, 
			shipping, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(creatorID), 1);
	
	for(int i = 0; i <iDList.size(); i++){
		if(iDList.get(i) == Integer.parseInt(userID))
			sender.sendProdCommitEmailToCreator(pNID, userID, userName, price, quantity, shipTime, 
					shipping, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(iDList.get(i).toString()), 2);
		else
			sender.sendProdCommitEmailToCreator(pNID, userID, userName, price, quantity, shipTime, 
					shipping, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(iDList.get(i).toString()), 3);
	}
	
}

public void createCommitServNegotiationReply(String sNID, String userID,
		String userName, String price, String timeReq, String prodName,
		String creatorName, String creatorID, ArrayList<Integer> iDList) throws IOException, TransformerException, SQLException {
	

	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    dbMan.commitServNegotiationInstance(sNID);
    dbMan.commitServNegotiations(sNID, userID);
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardCommitServNegotiationReply");
	Element commRep = xmlDoc.createElement("joinRep");
	
	commRep.setAttribute("SNID", sNID);
	commRep.setAttribute("userID", userID);
	commRep.setAttribute("Price", price);
	commRep.setAttribute("prodName", prodName);
	commRep.setAttribute("timeReq", timeReq);
	commRep.setAttribute("creatorName", creatorName);
	
	rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSeveral(iDList, returner);
	sendCommitServEmails(sNID, userID, userName, price, timeReq, prodName, creatorName,
			            creatorID, iDList);

	
	
}

private void sendCommitServEmails(String sNID, String userID, String userName,
		String price, String timeReq, String prodName, String creatorName,
		String creatorID, ArrayList<Integer> iDList) throws SQLException {
	sender.sendServCommitEmailToCreator(sNID, userID, userName, price, timeReq, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(creatorID), 1);
	
	for(int i = 0; i <iDList.size(); i++){
		if(iDList.get(i) == Integer.parseInt(userID))
			sender.sendServCommitEmailToCreator(sNID, userID, userName, price, timeReq, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(iDList.get(i).toString()), 2);
		else
			sender.sendServCommitEmailToCreator(sNID, userID, userName, price, timeReq, prodName, creatorName, creatorID, dbMan.retrieveUserEmail(iDList.get(i).toString()), 3);
	}
	
}

public void createCreatorProdWithdrawNotifications(String pNID, String prodName, String creatorName,
		ArrayList<Integer> iDList) throws TransformerException, IOException, SQLException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    dbMan.withDrawCreatorProdNegotiation(pNID);
    dbMan.withDrawCreatorProdNegotiationInstance(pNID);
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardCreatorWithdrawProdNegotiation");
	Element commRep = xmlDoc.createElement("joinRep");
	
	commRep.setAttribute("PNID", pNID);
	commRep.setAttribute("prodName", prodName);
	commRep.setAttribute("creatorName", creatorName);
	
	rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSeveral(iDList, returner);
	sendCreatorWithdrawProdNegotiationsNotify(pNID, prodName, creatorName, iDList);

	
}

private void sendCreatorWithdrawProdNegotiationsNotify(String pNID, String prodName, String creatorName,
		ArrayList<Integer> iDList) throws SQLException {
	for(int i = 0; i <iDList.size(); i++){
	sender.createCreatorProdWithdrawNegotiationsNotify(pNID, prodName, creatorName, dbMan.retrieveUserEmail(Integer.toString(iDList.get(i))));
	}
	}

public void createCreatorServWithdrawNotifications(String sNID,
		String prodName, String creatorName, ArrayList<Integer> iDList) throws TransformerException, IOException, SQLException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    dbMan.withDrawCreatorServNegotiationInstance(sNID);
    dbMan.withDrawCreatorServNegotiation(sNID);
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardCreatorWithdrawServNegotiation");
	Element commRep = xmlDoc.createElement("joinRep");
	
	commRep.setAttribute("SNID", sNID);
	commRep.setAttribute("prodName", prodName);
	commRep.setAttribute("creatorName", creatorName);
	
	rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSeveral(iDList, returner);
	sendCreatorWithdrawServNegotiationsNotify(sNID, prodName, creatorName, iDList);
	
	
}

private void sendCreatorWithdrawServNegotiationsNotify(String sNID,
		String prodName, String creatorName, ArrayList<Integer> iDList) throws SQLException {
	
		for(int i = 0; i <iDList.size(); i++){
		sender.createCreatorServWithdrawNegotiationsNotify(sNID, prodName, creatorName, dbMan.retrieveUserEmail(Integer.toString(iDList.get(i))));
		}
	
}

public void createUserProdWithdrawNotifications(String pNID, String userID,
		ArrayList<Integer> iDList) throws TransformerException, IOException, SQLException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    dbMan.withDrawUserProdNegotiation(userID, pNID);
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardUserWithdrawProdNegotiation");
	Element commRep = xmlDoc.createElement("joinRep");
	
	commRep.setAttribute("PNID", pNID);

	commRep.setAttribute("userID", userID);
	
	rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSeveral(iDList, returner);
	
	
}

public void createUserServWithdrawNotifications(String sNID, String userID,
		ArrayList<Integer> iDList) throws TransformerException, IOException, SQLException {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    dbMan.withDrawUserServNegotiation(userID, sNID);
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("forwardUserWithdrawServNegotiation");
	Element commRep = xmlDoc.createElement("joinRep");
	
	commRep.setAttribute("SNID", sNID);

	commRep.setAttribute("userID", userID);
	
	rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSeveral(iDList, returner);
	
	
}

public String createProdDataMineRep(String prodName) throws TransformerException, SQLException {
	Negotiations newer = dbMan.dataMineProdNegotiations(prodName);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
   
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("ProdDataMineRep");
	Element commRep = xmlDoc.createElement("Product");
	
	commRep.setAttribute("Price", Double.toString(newer.Price));
    commRep.setAttribute("Shipping", Double.toString(newer.Shipping));
    commRep.setAttribute("ShipTime", Integer.toString(newer.shipTime));
    commRep.setAttribute("Quantity", Integer.toString(newer.Quantity));
    rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	return returner;
}

public String createServDataMineRep(String servName) throws SQLException, TransformerException {
	Negotiations newer = dbMan.dataMineServNegotiations(servName);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
   
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	rootElement = xmlDoc.createElement("ServDataMineRep");
	Element commRep = xmlDoc.createElement("Service");
	
	commRep.setAttribute("Price", Double.toString(newer.Price));
    commRep.setAttribute("timeReq", Integer.toString(newer.timeReq));
    rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	return returner;
}

public void createProdChatRep(String pNID, String chat, String receiverID,
		String senderID) throws TransformerException, IOException {
	int sendTo = Integer.parseInt(receiverID);
	int sentBy = Integer.parseInt(senderID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	if(Server.isOnline(sendTo)){
	rootElement = xmlDoc.createElement("ProdChat");
	Element commRep = xmlDoc.createElement("Chat");
	
	commRep.setAttribute("PNID", pNID);
    commRep.setAttribute("chat", chat);
    commRep.setAttribute("receiverID", receiverID);
    commRep.setAttribute("senderID", senderID);
    rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSingle(sendTo, returner);}
	else{
		rootElement = xmlDoc.createElement("ProdChatNotOnline");
		xmlDoc.appendChild(rootElement);
		Source source = new DOMSource(xmlDoc);
		Result result = new StreamResult(stringWriter);
		transform.transform(source, result);
		returner = stringWriter.getBuffer().toString();
		Server.sendToSingle(sentBy, returner);
	}
	
}

public void createServChatRep(String sNID, String chat, String receiverID,
		String senderID) throws IOException, TransformerException {
	int sendTo = Integer.parseInt(receiverID);
	int sentBy = Integer.parseInt(senderID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
    
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	if(Server.isOnline(sendTo)){
	rootElement = xmlDoc.createElement("ServChat");
	Element commRep = xmlDoc.createElement("Chat");
	
	commRep.setAttribute("SNID", sNID);
    commRep.setAttribute("chat", chat);
    commRep.setAttribute("receiverID", receiverID);
    commRep.setAttribute("senderID", senderID);
    rootElement.appendChild(commRep);
	xmlDoc.appendChild(rootElement);
	Source source = new DOMSource(xmlDoc);
	Result result = new StreamResult(stringWriter);
	transform.transform(source, result);
	returner = stringWriter.getBuffer().toString();
	Server.sendToSingle(sendTo, returner);}
	else{
		rootElement = xmlDoc.createElement("ServChatNotOnline");
		xmlDoc.appendChild(rootElement);
		Source source = new DOMSource(xmlDoc);
		Result result = new StreamResult(stringWriter);
		transform.transform(source, result);
		returner = stringWriter.getBuffer().toString();
		Server.sendToSingle(sentBy, returner);
	}
	
}
public void createListUserCComProdNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserCComProdEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myCComProdNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ProdName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("PNID", Integer.toString(prodNegs.get(i).PNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				System.out.println("THIS IS CURRENT USERID " + userID2);
				Server.sendToSingle(userID2, returner);
				//return returner;
}
public void createListUserCComServNegs(String userID) throws TransformerException, NumberFormatException, SQLException, IOException{
	  ArrayList<NegInst> prodNegs = dbMan.getUserCComServEntries(Integer.parseInt(userID));
	  StringWriter stringWriter = new StringWriter();
		String returner = " ";
	
		Document xmlDoc; 
		xmlDoc = docBuild.newDocument();
		Element rootElement = null;
		String ID;
		
				rootElement = xmlDoc.createElement("myCComServNegotiationInstances");
				Element list = xmlDoc.createElement("NegotiationsList");
				for(int i = 0 ; i < prodNegs.size(); i++){
					Element Negotiation = xmlDoc.createElement("Negotiation" + i);
					Negotiation.setAttribute("ServName", prodNegs.get(i).prodServName);
					Negotiation.setAttribute("SNID", Integer.toString(prodNegs.get(i).SNID));
					Negotiation.setAttribute("creatorName", prodNegs.get(i).userName);
					Negotiation.setAttribute("creatorID", Integer.toString(prodNegs.get(i).creatorID));
					Negotiation.setAttribute("Type", Integer.toString(prodNegs.get(i).Type));			
				    list.appendChild(Negotiation);
				}
				rootElement.appendChild(list);
				xmlDoc.appendChild(rootElement);
				
				
			    
				Source source = new DOMSource(xmlDoc);
				Result result = new StreamResult(stringWriter);
				transform.transform(source, result);
				returner = stringWriter.getBuffer().toString();
				Server.sendToSingle(userID2, returner);
				//return returner;
}

public String createCComProdRep(String pNID) throws SQLException, TransformerException {
	String prodName = dbMan.getProdName(pNID);
	Negotiations prod = dbMan.getCComProd(pNID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";

	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	String ID;
	
			rootElement = xmlDoc.createElement("myCComProdRep");
			Element list = xmlDoc.createElement("Negotiation");
			list.setAttribute("PNID", pNID);
			list.setAttribute("prodName", prodName);
			list.setAttribute("userName", prod.user.getUserName());
			list.setAttribute("Price", Double.toString(prod.Price));
			list.setAttribute("Shipping", Double.toString(prod.Shipping));
			list.setAttribute("ShipTime", Integer.toString(prod.shipTime));
			list.setAttribute("Quantity", Integer.toString(prod.Quantity));
			rootElement.appendChild(list);
			xmlDoc.appendChild(rootElement);
			
			
		    
			Source source = new DOMSource(xmlDoc);
			Result result = new StreamResult(stringWriter);
			transform.transform(source, result);
			returner = stringWriter.getBuffer().toString();
			
	
	return returner;
}
public String createJComProdRep(String pNID) throws SQLException, TransformerException {
	String prodName = dbMan.getProdName(pNID);
	Negotiations prod = dbMan.getCComProd(pNID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";

	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	String ID;
	String creatorName = dbMan.getProdCreateName(pNID);
	
			rootElement = xmlDoc.createElement("myJComProdRep");
			Element list = xmlDoc.createElement("Negotiation");
			list.setAttribute("PNID", pNID);
			list.setAttribute("prodName", prodName);
			list.setAttribute("userName", prod.user.getUserName());
			list.setAttribute("Price", Double.toString(prod.Price));
			list.setAttribute("Shipping", Double.toString(prod.Shipping));
			list.setAttribute("ShipTime", Integer.toString(prod.shipTime));
			list.setAttribute("Quantity", Integer.toString(prod.Quantity));
			list.setAttribute("creatorName", creatorName);
			rootElement.appendChild(list);
			xmlDoc.appendChild(rootElement);
			
			
		    
			Source source = new DOMSource(xmlDoc);
			Result result = new StreamResult(stringWriter);
			transform.transform(source, result);
			returner = stringWriter.getBuffer().toString();
			
	
	return returner;
}
public String createCComServRep(String sNID) throws SQLException, TransformerException {
	String prodName = dbMan.getServName(sNID);
	Negotiations prod = dbMan.getCComServ(sNID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";

	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	String ID;
	
			rootElement = xmlDoc.createElement("myCComServRep");
			Element list = xmlDoc.createElement("Negotiation");
			list.setAttribute("SNID", sNID);
			list.setAttribute("servName", prodName);
			list.setAttribute("userName", prod.user.getUserName());
			list.setAttribute("Price", Double.toString(prod.Price));
			list.setAttribute("timeReq", Integer.toString(prod.timeReq));
			rootElement.appendChild(list);
			xmlDoc.appendChild(rootElement);
			
			
		    
			Source source = new DOMSource(xmlDoc);
			Result result = new StreamResult(stringWriter);
			transform.transform(source, result);
			returner = stringWriter.getBuffer().toString();
			
	
	return returner;
}
public String createJComServRep(String sNID) throws SQLException, TransformerException {
	String prodName = dbMan.getServName(sNID);
	Negotiations prod = dbMan.getCComServ(sNID);
	StringWriter stringWriter = new StringWriter();
	String returner = " ";

	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	Element rootElement = null;
	String ID;
	String creatorName = dbMan.getServCreateName(sNID);
	
			rootElement = xmlDoc.createElement("myJComServRep");
			Element list = xmlDoc.createElement("Negotiation");
			list.setAttribute("SNID", sNID);
			list.setAttribute("servName", prodName);
			list.setAttribute("userName", prod.user.getUserName());
			list.setAttribute("Price", Double.toString(prod.Price));
			list.setAttribute("timeReq", Integer.toString(prod.timeReq));
			list.setAttribute("creatorName", creatorName);
			rootElement.appendChild(list);
			xmlDoc.appendChild(rootElement);
			
			
		    
			Source source = new DOMSource(xmlDoc);
			Result result = new StreamResult(stringWriter);
			transform.transform(source, result);
			returner = stringWriter.getBuffer().toString();
			
	
	return returner;
}	
}
