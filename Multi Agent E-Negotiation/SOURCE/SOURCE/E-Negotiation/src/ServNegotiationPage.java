import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ServNegotiationPage extends JPanel {

	JTextField creatPriceField;
	JTextField creatTimeReqField;
	JTextField usr1PriceField;
	JTextField usr1TimeReqField;
	JTextField usr2PriceField;
	JTextField usr2TimeReqField;
	JTextField usr3PriceField;
	JTextField usr3TimeReqField;
	JTextField usr4PriceField;
	JTextField usr4TimeReqField;
	JTextField lowCurrPriceField;
	JTextField lowCurrTimeReqField;
	JTextArea  chatArea;
	ListSelectionModel lsm;
	JList chatList;
	JLabel lblUseridCreator;
	JLabel lblServName;
	JLabel lblUserID1;
	JLabel lblUserID2;
	JLabel lblUserID3;
	JLabel lblUserID4;
	JButton joinButton;
	JButton creatSendBtn;
	JButton usr1SendBtn;
	JButton usr2SendBtn;
	JButton usr3SendBtn;
	JButton usr4SendBtn;
	JButton usr1CommitBtn;
	JButton usr2CommitBtn;
	JButton usr3CommitBtn;
	JButton usr4CommitBtn;
	JButton withdrawBtn;
	JButton backToMainBtn = new JButton("Back To Main Page");
	JTextField chatField;
	JButton dataBtn;
	static JButton sendChatBtn;

	/**
	 * Create the panel.
	 */
	public ServNegotiationPage() {
		setLayout(null);
		
		lblUseridCreator = new JLabel("userID");
		lblUseridCreator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUseridCreator.setBounds(89, 89, 102, 14);
		add(lblUseridCreator);
		
		lblServName = new JLabel("Service Name");
		lblServName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServName.setBounds(320, 27, 173, 31);
		add(lblServName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(60, 114, 46, 14);
		add(lblPrice);
		
		creatPriceField = new JTextField();
		creatPriceField.setBounds(37, 132, 80, 20);
		add(creatPriceField);
		creatPriceField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Time Required");
		lblQuantity.setBounds(145, 114, 88, 14);
		add(lblQuantity);
		
		creatTimeReqField = new JTextField();
		creatTimeReqField.setBounds(127, 132, 106, 20);
		add(creatTimeReqField);
		creatTimeReqField.setColumns(10);
		
		lblUserID1 = new JLabel("userID");
		lblUserID1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID1.setBounds(529, 89, 102, 14);
		add(lblUserID1);
		
		JLabel label_1 = new JLabel("Price");
		label_1.setBounds(639, 114, 46, 14);
		add(label_1);
		
		usr1PriceField = new JTextField();
		usr1PriceField.setColumns(10);
		usr1PriceField.setBounds(623, 139, 80, 20);
		add(usr1PriceField);
		
		JLabel lblTimeRequired = new JLabel("Time Required");
		lblTimeRequired.setBounds(724, 114, 88, 14);
		add(lblTimeRequired);
		
		usr1TimeReqField = new JTextField();
		usr1TimeReqField.setColumns(10);
		usr1TimeReqField.setBounds(713, 139, 86, 20);
		add(usr1TimeReqField);
		
		lblUserID2 = new JLabel("userID");
		lblUserID2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID2.setBounds(529, 192, 102, 14);
		add(lblUserID2);
		
		JLabel label_6 = new JLabel("Price");
		label_6.setBounds(639, 217, 46, 14);
		add(label_6);
		
		usr2PriceField = new JTextField();
		usr2PriceField.setColumns(10);
		usr2PriceField.setBounds(623, 242, 80, 20);
		add(usr2PriceField);
		
		JLabel lblTimeRequired_1 = new JLabel("Time Required");
		lblTimeRequired_1.setBounds(724, 217, 88, 14);
		add(lblTimeRequired_1);
		
		usr2TimeReqField = new JTextField();
		usr2TimeReqField.setColumns(10);
		usr2TimeReqField.setBounds(713, 242, 86, 20);
		add(usr2TimeReqField);
		
		lblUserID3 = new JLabel("userID");
		lblUserID3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID3.setBounds(529, 293, 102, 14);
		add(lblUserID3);
		
		JLabel label_11 = new JLabel("Price");
		label_11.setBounds(639, 318, 46, 14);
		add(label_11);
		
		usr3PriceField = new JTextField();
		usr3PriceField.setColumns(10);
		usr3PriceField.setBounds(623, 343, 80, 20);
		add(usr3PriceField);
		
		JLabel lblTimeRequired_2 = new JLabel("Time Required");
		lblTimeRequired_2.setBounds(724, 318, 88, 14);
		add(lblTimeRequired_2);
		
		usr3TimeReqField = new JTextField();
		usr3TimeReqField.setColumns(10);
		usr3TimeReqField.setBounds(713, 343, 86, 20);
		add(usr3TimeReqField);
		
		lblUserID4 = new JLabel("userID");
		lblUserID4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserID4.setBounds(529, 396, 102, 14);
		add(lblUserID4);
		
		JLabel label_16 = new JLabel("Price");
		label_16.setBounds(639, 421, 46, 14);
		add(label_16);
		
		usr4PriceField = new JTextField();
		usr4PriceField.setColumns(10);
		usr4PriceField.setBounds(623, 446, 80, 20);
		add(usr4PriceField);
		
		JLabel lblTimeRequired_3 = new JLabel("Time Required");
		lblTimeRequired_3.setBounds(724, 420, 88, 14);
		add(lblTimeRequired_3);
		
		usr4TimeReqField = new JTextField();
		usr4TimeReqField.setColumns(10);
		usr4TimeReqField.setBounds(713, 446, 86, 20);
		add(usr4TimeReqField);
		
		usr1CommitBtn = new JButton("Commit");
		usr1CommitBtn.setBounds(599, 85, 89, 23);
		add(usr1CommitBtn);
		
		usr2CommitBtn = new JButton("Commit");
		usr2CommitBtn.setBounds(599, 188, 89, 23);
		add(usr2CommitBtn);
		
		usr3CommitBtn = new JButton("Commit");
		usr3CommitBtn.setBounds(599, 289, 89, 23);
		add(usr3CommitBtn);
		
		usr4CommitBtn = new JButton("Commit");
		usr4CommitBtn.setBounds(599, 387, 89, 23);
		add(usr4CommitBtn);
		
		JLabel lblLowestCurrentPrice = new JLabel("Lowest Current Price");
		lblLowestCurrentPrice.setBounds(37, 217, 126, 14);
		add(lblLowestCurrentPrice);
		
		JLabel lblLowestCurrentShipping = new JLabel("Lowest Current Time Req");
		lblLowestCurrentShipping.setBounds(22, 256, 169, 14);
		add(lblLowestCurrentShipping);
		
		lowCurrPriceField = new JTextField();
		lowCurrPriceField.setBounds(189, 214, 102, 20);
		add(lowCurrPriceField);
		lowCurrPriceField.setColumns(10);
		
		lowCurrTimeReqField = new JTextField();
		lowCurrTimeReqField.setBounds(189, 253, 107, 20);
		add(lowCurrTimeReqField);
		lowCurrTimeReqField.setColumns(10);
		
		withdrawBtn = new JButton("Withdraw From Negotiation");
		withdrawBtn.setBounds(60, 352, 191, 23);
		add(withdrawBtn);
		
		
		backToMainBtn.setBounds(60, 392, 191, 23);
		add(backToMainBtn);
		
		usr1SendBtn = new JButton("Send");
		usr1SendBtn.setBounds(713, 85, 89, 23);
		add(usr1SendBtn);
		
		usr2SendBtn = new JButton("Send");
		usr2SendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		usr2SendBtn.setBounds(713, 188, 89, 23);
		add(usr2SendBtn);
		
		usr3SendBtn = new JButton("Send");
		usr3SendBtn.setBounds(713, 289, 89, 23);
		add(usr3SendBtn);
		
		usr4SendBtn = new JButton("Send");
		usr4SendBtn.setBounds(710, 387, 89, 23);
		add(usr4SendBtn);
		
		creatSendBtn = new JButton("Send");
		creatSendBtn.setBounds(89, 165, 89, 23);
		add(creatSendBtn);
		
		JButton creatChatBtn = new JButton("Chat");
		creatChatBtn.setBounds(202, 165, 89, 23);
		add(creatChatBtn);
		
		joinButton = new JButton("Join Negotiation");
		joinButton.setBounds(60, 318, 191, 23);
		add(joinButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 483, 590, 120);
		add(scrollPane);
		
		chatArea = new JTextArea();
		scrollPane.setViewportView(chatArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(684, 477, 146, 130);
		add(scrollPane_1);
		
		chatList = new JList();
		scrollPane_1.setViewportView(chatList);
		
		chatField = new JTextField();
		chatField.setBounds(60, 626, 588, 20);
		add(chatField);
		chatField.setColumns(10);
		
		sendChatBtn = new JButton("Send Chat");
		sendChatBtn.setBounds(698, 625, 114, 23);
		add(sendChatBtn);
		
		dataBtn = new JButton("Data Mine");
		dataBtn.setBounds(60, 426, 191, 23);
		add(dataBtn);

	}

	public void disableFields(NegotiationInstance i,
			int participants, User mainUser) {
		 if(mainUser.getUserID() != i.creatorID){
			 creatPriceField.setEnabled(false);
			 creatTimeReqField.setEnabled(false);
			 usr1CommitBtn.setEnabled(false);
			 usr2CommitBtn.setEnabled(false);
			 usr3CommitBtn.setEnabled(false);
			 usr4CommitBtn.setEnabled(false);
			
		 }
	     if((mainUser.getUserID() == i.creatorID || !(i.User1.user.getUserID() == mainUser.getUserID()))){
	    	 usr1PriceField.setEnabled(false);
			 usr1TimeReqField.setEnabled(false);
			 usr1SendBtn.setEnabled(false);
			 if(mainUser.getUserID() != i.creatorID){
				 usr1PriceField.setText("");
			     usr1TimeReqField.setText("");
			 }
	     }
	     if((mainUser.getUserID() == i.creatorID || !(i.User2.user.getUserID() == mainUser.getUserID()))){
	    	 usr2PriceField.setEnabled(false);
			 usr2TimeReqField.setEnabled(false);
			 usr2SendBtn.setEnabled(false);
			
			 if(mainUser.getUserID() != i.creatorID){
				 usr2PriceField.setText("");
			     usr2TimeReqField.setText(""); 
			 }
	     }
	     if((mainUser.getUserID() == i.creatorID || !(i.User3.user.getUserID() == mainUser.getUserID()))){
	    	 usr3PriceField.setEnabled(false);
			 usr3TimeReqField.setEnabled(false);
			 usr3SendBtn.setEnabled(false);
			
			 if(mainUser.getUserID() != i.creatorID){
				 usr3PriceField.setText("");
			     usr3TimeReqField.setText("");
			 }
	     }
	     if((mainUser.getUserID() == i.creatorID || !(i.User4.user.getUserID() == mainUser.getUserID()))){
	    	 usr4PriceField.setEnabled(false);
			 usr4TimeReqField.setEnabled(false);
			 usr4SendBtn.setEnabled(false);
			 
			 if(mainUser.getUserID() != i.creatorID){
				 usr4PriceField.setText("");
			     usr4TimeReqField.setText("");
			 }
			 
	     }
	     if(mainUser.getUserID() == i.creatorID || mainUser.getUserID() == i.User1.user.getUserID() || mainUser.getUserID() == i.User2.user.getUserID()  
	    		 || mainUser.getUserID() == i.User3.user.getUserID() || mainUser.getUserID() == i.User4.user.getUserID() || participants > 4){
	    	 joinButton.setEnabled(false);
	     }
	}
	public void purgeAllFields() {
		 usr1PriceField.setEnabled(true);
		 usr1TimeReqField.setEnabled(true);
		 usr1SendBtn.setEnabled(true);
		 
		 usr2PriceField.setEnabled(true);
		 usr2TimeReqField.setEnabled(true);
		 usr2SendBtn.setEnabled(true);
		 
		 usr3PriceField.setEnabled(true);
		 usr3TimeReqField.setEnabled(true);
		 usr3SendBtn.setEnabled(true);
		 
		 usr4PriceField.setEnabled(true);
		 usr4TimeReqField.setEnabled(true);		
		 usr4SendBtn.setEnabled(true);
		 
		 lowCurrPriceField.setText("");
		 lowCurrTimeReqField.setText("");
		 
		 lblUserID1.setText("");
		 usr1PriceField.setText("");
		 usr1TimeReqField.setText("");
		 lblUserID2.setText("");
		 usr2PriceField.setText("");
		 usr2TimeReqField.setText("");
		 lblUserID3.setText("");
		 usr3PriceField.setText("");
		 usr3TimeReqField.setText("");
		 lblUserID4.setText("");
		 usr4PriceField.setText("");
		 usr4TimeReqField.setText("");
		 joinButton.setEnabled(true);
		 
		 usr1CommitBtn.setEnabled(true);
		 usr2CommitBtn.setEnabled(true);
		 usr3CommitBtn.setEnabled(true);
		 usr4CommitBtn.setEnabled(true);
		
	}
	public void setUserList(ArrayList<String> userNames){
		DefaultListModel<String> myListModel = new DefaultListModel<String>();
		myListModel.clear();
		if(userNames.size() != 0){
		for(int i = 0; i < userNames.size(); i++){
		    
			myListModel.addElement(new String(userNames.get(i)));
			
		}
		}
		chatList.setModel(myListModel);
		lsm = chatList.getSelectionModel();
		
		lsm.addListSelectionListener(new SharedListSelectionHandler());
		
		
	}
	public static void enableChat(){
		sendChatBtn.setEnabled(true);
	}
	public static void disableChat(){
		sendChatBtn.setEnabled(false);
	}
}
