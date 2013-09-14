
public class ProdNegInstList {
    String prodName;
    int creatorID;
    String userName;
    int PNID;
    int Type;
    
    public ProdNegInstList(){
    	prodName = " ";
    	creatorID = 0;
        userName = " ";
        PNID = 0;
        Type = 0;
    }
    public ProdNegInstList(String pN, int CID, String uN, int PID, int tY){
    	prodName = pN;
    	creatorID = CID;
    	 userName = uN;
    	PNID = PID;
    	Type = tY;
    }
}
