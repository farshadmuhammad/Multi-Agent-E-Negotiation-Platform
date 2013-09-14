import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


import java.io.StringWriter;
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
	
	
public MessageBuilder() throws ParserConfigurationException, TransformerConfigurationException{
	docFac = DocumentBuilderFactory.newInstance();
	docBuild = docFac.newDocumentBuilder();
    
    transFac = TransformerFactory.newInstance();
    transform = transFac.newTransformer();
}

public String loginRequestMessage(String userID){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("loginrequest");
	Element userEle = xmlDoc.createElement("user");
	
	
	userEle.setAttribute("userID", userID);
	
	
	rootElement.appendChild(userEle);
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
public String createProdNMessage(String price, String ship, String shipTime, String BS, String quant, String userID, String prodName, String userName){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("createProdNegRequest");
	Element prodNEle = xmlDoc.createElement("ProdNegotiation");
	
	
	prodNEle.setAttribute("price", price);
	prodNEle.setAttribute("shipping", ship);
	prodNEle.setAttribute("shippingTime", shipTime);
	prodNEle.setAttribute("BS", BS);
	prodNEle.setAttribute("quantity", quant);
	prodNEle.setAttribute("creatorID", userID);
	prodNEle.setAttribute("prodName", prodName);
	prodNEle.setAttribute("userName", userName);
	rootElement.appendChild(prodNEle);
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
public String createServNMessage(String price,  String timeReq, String BS, String userID, String prodName, String userName){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("createServNegRequest");
	Element servNEle = xmlDoc.createElement("ServNegotiation");
	
	
	servNEle.setAttribute("price", price);
	
	
	servNEle.setAttribute("BS", BS);
	servNEle.setAttribute("timeReq", timeReq);
	servNEle.setAttribute("creatorID", userID);
	servNEle.setAttribute("prodName", prodName);
	servNEle.setAttribute("userName", userName);
	rootElement.appendChild(servNEle);
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
public String createNegotiationByPNIDReq(int PNID){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("NegotiationRequestByPNID");
	Element negoNEle = xmlDoc.createElement("Negotiation");
	negoNEle.setAttribute("PNID", Integer.toString(PNID));
	
	rootElement.appendChild(negoNEle);
	
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

public String retNegotiationInstanceByPNIDReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("NegotiationInstanceRequestByPNID");
	Element negoNEle = xmlDoc.createElement("NegotiationInstance");
	negoNEle.setAttribute("PNID", iD);
	
	rootElement.appendChild(negoNEle);
	
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

public String retNegotiationInstanceBySNIDReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("NegotiationInstanceRequestBySNID");
	Element negoNEle = xmlDoc.createElement("NegotiationInstance");
	negoNEle.setAttribute("SNID", iD);
	
	rootElement.appendChild(negoNEle);
	
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
public String createNegotiationBySNIDReq(int sNID){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	try{
	Document xmlDoc; 
	xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("NegotiationRequestBySNID");
	Element negoNEle = xmlDoc.createElement("Negotiation");
	negoNEle.setAttribute("SNID", Integer.toString(sNID));
	
	rootElement.appendChild(negoNEle);
	
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

public String createProdUpdateMessage(String price, String shipping,
		String shippTime, String quantity, int userID, ArrayList<Integer> IDs, int PNID) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	
	
	Element rootElement = xmlDoc.createElement("UpdateProdNegotiation");
	Element negoNEle = xmlDoc.createElement("Negotiation");
	negoNEle.setAttribute("PNID", Integer.toString(PNID));
	negoNEle.setAttribute("Price", price);
	negoNEle.setAttribute("Shipping", shipping);
	negoNEle.setAttribute("ShipTime", shippTime);
	negoNEle.setAttribute("Quantity", quantity);
	negoNEle.setAttribute("userID", Integer.toString(userID));
	
	Element IDListEle = xmlDoc.createElement("IDList");
	for(int i = 0; i < IDs.size(); i++){
		IDListEle.setAttribute("User" + i, Integer.toString(IDs.get(i)));
	}
	  rootElement.appendChild(IDListEle);
	  rootElement.appendChild(negoNEle);
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
public String createServUpdateMessage(String price,  String timeReq, int userID, ArrayList<Integer> IDs, int SNID) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	
	System.out.println("CLIENT MESSAGE BUILDER SNID: " + SNID);
	Element rootElement = xmlDoc.createElement("UpdateServNegotiation");
	Element negoNEle = xmlDoc.createElement("Negotiation");
	negoNEle.setAttribute("SNID", Integer.toString(SNID));
	negoNEle.setAttribute("Price", price);
	negoNEle.setAttribute("timeReq", timeReq);
	negoNEle.setAttribute("userID", Integer.toString(userID));
	
	Element IDListEle = xmlDoc.createElement("IDList");
	for(int i = 0; i < IDs.size(); i++){
		IDListEle.setAttribute("User" + i, Integer.toString(IDs.get(i)));
	}
	  rootElement.appendChild(IDListEle);
	  rootElement.appendChild(negoNEle);
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
public String createSearchProducts(String search, String userID, String type) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("SearchProducts");
	Element Search = xmlDoc.createElement("ProductSearch");
	Search.setAttribute("SearchString", search);
	Search.setAttribute("UserID", userID);
	Search.setAttribute("Type", type);
	root.appendChild(Search);
	xmlDoc.appendChild(root);
	
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
public String createSearchServices(String search, String userID, String type) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("SearchServices");
	Element Search = xmlDoc.createElement("ServiceSearch");
	Search.setAttribute("SearchString", search);
	Search.setAttribute("UserID", userID);
	Search.setAttribute("Type", type);
	root.appendChild(Search);
	xmlDoc.appendChild(root);
	
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

public String createProdJoinReq(String userName, String userID, String pNID, ArrayList<Integer> IDList) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("JoinProdNegotiationRequest");
	Element join = xmlDoc.createElement("JoinPReq");
	join.setAttribute("UserName", userName);
	join.setAttribute("UserID", userID);
	join.setAttribute("PNID", pNID);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < IDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(IDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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
public String createServJoinReq(String userName, String userID, String sNID, ArrayList<Integer> iDList) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("JoinServNegotiationRequest");
	Element join = xmlDoc.createElement("JoinSReq");
	join.setAttribute("UserName", userName);
	join.setAttribute("UserID", userID);
	join.setAttribute("SNID", sNID);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < iDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(iDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createProdCommitedNotification(NegotiationInstance mainNegInst, int i) {
	String PNID = null;
	String userID = null;
	String userName = null;
	String Quantity = null;
	String Price = null;
	String Shipping = null;
	String ShipTime = null;
	String prodName = mainNegInst.prodOrServName;
	String creatorName = mainNegInst.creatorName;

	if(i == 1){
		PNID = Integer.toString(mainNegInst.PNID);
		userID = Integer.toString(mainNegInst.User1.user.getUserID());
		userName = mainNegInst.User1.user.getUserName();
		Quantity = Integer.toString(mainNegInst.User1.Quantity);
		ShipTime = Integer.toString(mainNegInst.User1.shipTime);
		Price = Double.toString(mainNegInst.User1.Price);
		Shipping = Double.toString(mainNegInst.User1.Shipping);
				
	}
	if(i == 2){
		PNID = Integer.toString(mainNegInst.PNID);
		userID = Integer.toString(mainNegInst.User2.user.getUserID());
		userName = mainNegInst.User2.user.getUserName();
		Quantity = Integer.toString(mainNegInst.User2.Quantity);
		ShipTime = Integer.toString(mainNegInst.User2.shipTime);
		Price = Double.toString(mainNegInst.User2.Price);
		Shipping = Double.toString(mainNegInst.User2.Shipping);
		
		
	}
	if(i == 3){
		PNID = Integer.toString(mainNegInst.PNID);
		userID = Integer.toString(mainNegInst.User3.user.getUserID());
		userName = mainNegInst.User3.user.getUserName();
		Quantity = Integer.toString(mainNegInst.User3.Quantity);
		ShipTime = Integer.toString(mainNegInst.User3.shipTime);
		Price = Double.toString(mainNegInst.User3.Price);
		Shipping = Double.toString(mainNegInst.User3.Shipping);
		
	}
	if(i == 4){
		PNID = Integer.toString(mainNegInst.PNID);
		userID = Integer.toString(mainNegInst.User4.user.getUserID());
		userName = mainNegInst.User4.user.getUserName();
		Quantity = Integer.toString(mainNegInst.User4.Quantity);
		ShipTime = Integer.toString(mainNegInst.User4.shipTime);
		Price = Double.toString(mainNegInst.User4.Price);
		Shipping = Double.toString(mainNegInst.User4.Shipping);
		
	}
    ArrayList<Integer> IDList = new ArrayList<Integer>();
    IDList.add(mainNegInst.creatorID);
	if(mainNegInst.User1.user.getUserID() != 0){
		IDList.add(mainNegInst.User1.user.getUserID());
	}
	if(mainNegInst.User2.user.getUserID() != 0){
		IDList.add(mainNegInst.User2.user.getUserID());
	}
	if(mainNegInst.User3.user.getUserID() != 0){
		IDList.add(mainNegInst.User3.user.getUserID());
	}
	if(mainNegInst.User4.user.getUserID() != 0){
		IDList.add(mainNegInst.User4.user.getUserID());
	}
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	MainPage.addMyCProdNegotiation(new ProdNegInstList(prodName, mainNegInst.creatorID, mainNegInst.creatorName, mainNegInst.PNID, mainNegInst.type));
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("CommitProdNegotiationNotify");
	Element commit = xmlDoc.createElement("ProdNegotiation");
	commit.setAttribute("UserName", userName);
	commit.setAttribute("UserID", userID);
	commit.setAttribute("PNID", PNID);
	commit.setAttribute("Price", Price);
	commit.setAttribute("Shipping", Shipping);
	commit.setAttribute("ShipTime", ShipTime);
	commit.setAttribute("Quantity", Quantity);
	commit.setAttribute("prodName", prodName);
	commit.setAttribute("creatorName", creatorName);
	commit.setAttribute("CreatorID", Integer.toString(mainNegInst.creatorID));
	Element list = xmlDoc.createElement("userList");
	for(int i1 = 0; i1 < IDList.size(); i1++){
		list.setAttribute("User" + i1, Integer.toString(IDList.get(i1)));
	}
	commit.appendChild(list);
	root.appendChild(commit);
	
	xmlDoc.appendChild(root);
	
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

public String createServCommitedNotification(NegotiationInstance mainNegInst,
		int i) {
	String SNID = null;
	String userID = null;
	String userName = null;
	String Price = null;
	String timeReq = null;
	String servName = mainNegInst.prodOrServName;
	String creatorName = mainNegInst.creatorName;

	if(i == 1){
		SNID = Integer.toString(mainNegInst.SNID);
		userID = Integer.toString(mainNegInst.User1.user.getUserID());
		userName = mainNegInst.User1.user.getUserName();
		Price = Double.toString(mainNegInst.User1.Price);
		timeReq = Integer.toString(mainNegInst.User1.timeReq);
				
	}
	if(i == 2){
		SNID = Integer.toString(mainNegInst.SNID);
		userID = Integer.toString(mainNegInst.User2.user.getUserID());
		userName = mainNegInst.User2.user.getUserName();
		Price = Double.toString(mainNegInst.User2.Price);
		timeReq = Integer.toString(mainNegInst.User2.timeReq);
		
		
	}
	if(i == 3){
		SNID = Integer.toString(mainNegInst.SNID);
		userID = Integer.toString(mainNegInst.User3.user.getUserID());
		userName = mainNegInst.User3.user.getUserName();
		Price = Double.toString(mainNegInst.User3.Price);
		timeReq = Integer.toString(mainNegInst.User3.timeReq);
		
	}
	if(i == 4){
		SNID = Integer.toString(mainNegInst.SNID);
		userID = Integer.toString(mainNegInst.User4.user.getUserID());
		userName = mainNegInst.User4.user.getUserName();
		Price = Double.toString(mainNegInst.User4.Price);
		timeReq = Integer.toString(mainNegInst.User4.timeReq);
		
	}
	MainPage.addMyCServNegotiation(new ServNegInstList(servName, mainNegInst.creatorID, mainNegInst.creatorName, mainNegInst.PNID, mainNegInst.type));
    ArrayList<Integer> IDList = new ArrayList<Integer>();
   
	if(mainNegInst.User1.user.getUserID() != 0){
		IDList.add(mainNegInst.User1.user.getUserID());
	}
	if(mainNegInst.User2.user.getUserID() != 0){
		IDList.add(mainNegInst.User2.user.getUserID());
	}
	if(mainNegInst.User3.user.getUserID() != 0){
		IDList.add(mainNegInst.User3.user.getUserID());
	}
	if(mainNegInst.User4.user.getUserID() != 0){
		IDList.add(mainNegInst.User4.user.getUserID());
	}
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("CommitServNegotiationNotify");
	Element commit = xmlDoc.createElement("ProdNegotiation");
	commit.setAttribute("UserName", userName);
	commit.setAttribute("UserID", userID);
	commit.setAttribute("SNID", SNID);
	commit.setAttribute("Price", Price);
	commit.setAttribute("timeReq", timeReq);
	commit.setAttribute("prodName", servName);
	commit.setAttribute("creatorName", creatorName);
	commit.setAttribute("CreatorID", Integer.toString(mainNegInst.creatorID));
	Element list = xmlDoc.createElement("userList");
	for(int i1 = 0; i1 < IDList.size(); i1++){
		list.setAttribute("User" + i1, Integer.toString(IDList.get(i1)));
	}
	commit.appendChild(list);
	root.appendChild(commit);
	
	xmlDoc.appendChild(root);
	
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
public String createCreatorProdWithdrawMessage(String PNID, String prodName, String creatorName, ArrayList<Integer> IDList){
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("CreatorWithdrawProdNegotiation");
	Element join = xmlDoc.createElement("NWithdraw");
	
	join.setAttribute("PNID", PNID);
	join.setAttribute("prodName", prodName);
	join.setAttribute("creatorName", creatorName);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < IDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(IDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createCreatorServWithdrawMessage(String sNID, String prodName,
		String creatorName, ArrayList<Integer> iDList) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("CreatorWithdrawServNegotiation");
	Element join = xmlDoc.createElement("NWithdraw");
	
	join.setAttribute("SNID", sNID);
	join.setAttribute("prodName", prodName);
	join.setAttribute("creatorName", creatorName);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < iDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(iDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createUserWithdraProdNegotiation(String pNID, String userID,
		ArrayList<Integer> iDList) {
	
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("UserWithdrawProdNegotiation");
	Element join = xmlDoc.createElement("NWithdraw");
	
	join.setAttribute("PNID", pNID);
	
	join.setAttribute("userID", userID);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < iDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(iDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createUserWithdrawServNegotiation(String sNID, String userID,
		ArrayList<Integer> iDList) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("UserWithdrawServNegotiation");
	Element join = xmlDoc.createElement("NWithdraw");
	
	join.setAttribute("SNID", sNID);
	
	join.setAttribute("userID", userID);
	Element list = xmlDoc.createElement("userList");
	for(int i = 0; i < iDList.size(); i++){
		list.setAttribute("User" + i, Integer.toString(iDList.get(i)));
	}
	join.appendChild(list);
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createProdDataMineReq(String prodOrServName) {
	// TODO Auto-generated method stub
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("ProdDataMine");
	Element join = xmlDoc.createElement("Product");
	
	join.setAttribute("prodName", prodOrServName);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createServDataMineReq(String prodOrServName) {
	// TODO Auto-generated method stub
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("ServDataMine");
	Element join = xmlDoc.createElement("Service");
	
	join.setAttribute("servName", prodOrServName);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createProdSendChat(String pNID, String senderID,
		String receiverID, String chat) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("ProdChat");
	Element join = xmlDoc.createElement("Chat");
	
	join.setAttribute("PNID", pNID);
	join.setAttribute("receiverID", receiverID);
	join.setAttribute("senderID", senderID);
	join.setAttribute("chat", chat);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String createServSendChat(String sNID, String senderID,
		String receiverID, String chat) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("ServChat");
	Element join = xmlDoc.createElement("Chat");
	
	join.setAttribute("SNID", sNID);
	join.setAttribute("receiverID", receiverID);
	join.setAttribute("senderID", senderID);
	join.setAttribute("chat", chat);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String retCComProdNegotiationReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("RetCComProd");
	Element join = xmlDoc.createElement("PRODPNID");
	
	join.setAttribute("PNID", iD);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String retCComServNegotiationReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("RetCComServ");
	Element join = xmlDoc.createElement("ServSNID");
	
	join.setAttribute("SNID", iD);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String retJComProdNegotiationReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("RetJComProd");
	Element join = xmlDoc.createElement("ProdPNID");
	
	join.setAttribute("PNID", iD);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

public String retJComServNegotiationReq(String iD) {
	StringWriter stringWriter = new StringWriter();
	String returner = " ";
	
	try{
	Document xmlDoc; 
    xmlDoc = docBuild.newDocument();
	Element root = xmlDoc.createElement("RetJComServ");
	Element join = xmlDoc.createElement("ServSNID");
	
	join.setAttribute("SNID", iD);
	
	
	
	root.appendChild(join);
	xmlDoc.appendChild(root);
	
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

}


