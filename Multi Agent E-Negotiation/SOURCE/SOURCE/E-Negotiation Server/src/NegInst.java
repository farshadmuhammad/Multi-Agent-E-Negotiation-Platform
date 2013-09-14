
public class NegInst {
    String prodServName;
    int creatorID;
    String userName;
    int PNID;
    int Type;
    int SNID;
    
    public NegInst(){
    	prodServName = " ";
    	creatorID = 0;
        userName = " ";
        PNID = 0;
        Type = 0;
        SNID = 0;
    }
    public NegInst(String pN, int CID, String uN, int PID, int tY, int SID){
    	prodServName = pN;
    	creatorID = CID;
    	userName= uN;
    	PNID = PID;
    	Type = tY;
    	SNID = SID;
    }
}
