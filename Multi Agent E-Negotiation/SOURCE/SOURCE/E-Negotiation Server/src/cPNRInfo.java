
public class cPNRInfo {
    int userID;
    double price;
    int BS;
    double ship;
    int shipTime;
    int quant;
    int isActive;
    int PNID;
    int timeReq;
    boolean isCommited;
    String prodName;
    int currPar;
    String userName;
    public cPNRInfo(){
    	userID = 0;
    	price = 0;
    	BS = 0;
    	ship = 0;
    	shipTime = 0;
    	quant = 0;
    	isActive = 1;
    	isCommited = false;
    	prodName = " ";
    	currPar = 0;
    	PNID = 0;
    	timeReq = 0;
    	userName = " ";
    }
    public cPNRInfo(int cID, double pR, int BsR, double sR, int sTR, int qR, String pN, String uN){
    	userID = cID;
    	price = pR;
    	BS = BsR;
    	ship = sR;
    	shipTime = sTR;
    	quant = qR;
    	isActive = 1;
    	isCommited = false;
    	prodName = pN;
    	currPar = 1;
    	PNID = 0;
    	userName = uN;
    	
    }
    public cPNRInfo(int cID, double pR, int BsR, String pN, int tR, String uN){
    	userID = cID;
    	price = pR;
    	BS = 0;
    	ship = 0;
    	shipTime = 0;
    	quant = 0;
    	isActive = 1;
    	isCommited = false;
    	prodName = pN;
    	currPar = 1;
    	PNID = 0;
    	timeReq = tR;
    	userName = uN;
    	
    }

}
