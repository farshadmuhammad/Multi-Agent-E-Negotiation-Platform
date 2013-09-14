import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class ChoicePage extends JPanel {
	public JTextField searchField;
	JButton searchBtn = new JButton("Search");
	JButton cProdNegotBtn = new JButton("Create Product Negotiation");
	JButton cServNegotBtn = new JButton("Create Service Negotiation");
	JButton myNegotBtn = new JButton("My Negotiations");
	JButton joinNegotBtn = new JButton("Join Negotiation");
	JButton retrieveBtn = new JButton("Retrieve with ID");
	JList searchResultList;
	JRadioButton buyRadBtn;
	JRadioButton sellRadBtn;
	JComboBox chooserBox;
	JButton myCBtn;
	DefaultListModel<String> myListModel;
	String currState;
	private final JScrollPane scrollPane = new JScrollPane();
	/**
	 * Create the panel.
	 */
	public ChoicePage() {
		setLayout(null);
		
		searchField = new JTextField();
		searchField.setBounds(119, 24, 286, 20);
		add(searchField);
		searchField.setColumns(10);
		myListModel = new DefaultListModel<String>();
		
		searchBtn.setBounds(434, 23, 89, 23);
		add(searchBtn);
		
		
		cProdNegotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cProdNegotBtn.setBounds(23, 71, 197, 23);
		add(cProdNegotBtn);
		
	
		cServNegotBtn.setBounds(23, 116, 197, 23);
		add(cServNegotBtn);
		
		
		myNegotBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		myNegotBtn.setBounds(23, 161, 197, 23);
		add(myNegotBtn);
		
		
		joinNegotBtn.setBounds(339, 269, 134, 23);
		add(joinNegotBtn);
		retrieveBtn.setBounds(23, 243, 197, 23);
		
		add(retrieveBtn);
		
		chooserBox = new JComboBox();
		chooserBox.setBounds(477, 51, 122, 20);
		add(chooserBox);
		chooserBox.addItem("Products");
		chooserBox.addItem("Services");
		
		buyRadBtn = new JRadioButton("Buying");
		
		buyRadBtn.setBounds(260, 51, 89, 23);
		add(buyRadBtn);
		
		sellRadBtn = new JRadioButton("Selling");
		sellRadBtn.setBounds(362, 50, 89, 23);
		add(sellRadBtn);
		scrollPane.setBounds(270, 82, 316, 164);
		
		add(scrollPane);
		searchResultList = new JList(myListModel);
		scrollPane.setViewportView(searchResultList);
		
		myCBtn = new JButton("My Commits");
		myCBtn.setBounds(23, 203, 197, 23);
		add(myCBtn);
		
		
		
		
		
		
		buyRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			sellRadBtn.setSelected(false);
			}
		});

		sellRadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			buyRadBtn.setSelected(false);
			}
		});
	}
	 public void setMyProdNegList(ArrayList<ProdNegInstList> prodNegotiations){
	    	DefaultListModel<String> newListModel = new DefaultListModel<String>();
	    	
	    	if(prodNegotiations.size() != 0){
	    	
	    		for(int i = 0; i < prodNegotiations.size(); i++){
	    	    if(prodNegotiations.get(i).Type == 1)
	    		newListModel.addElement(new String(prodNegotiations.get(i).prodName +", Buying, " + prodNegotiations.get(i).PNID));
	    	    else
	    	    newListModel.addElement(new String(prodNegotiations.get(i).prodName +", Selling, " + prodNegotiations.get(i).PNID));	
	    	}
	    	
	    	}
	    	searchResultList.setModel(newListModel);
	    	
	    }
	 public void setMyServNegList(ArrayList<ServNegInstList> servNegotiations){
		     DefaultListModel<String> newListModel = new DefaultListModel<String>();
	    	if(servNegotiations.size() != 0){
	    	for(int i = 0; i < servNegotiations.size(); i++){
	    	    if(servNegotiations.get(i).Type == 1)
	    		newListModel.addElement(new String(servNegotiations.get(i).servName +", Buying, " + servNegotiations.get(i).SNID));
	    	    else
	    	    newListModel.addElement(new String(servNegotiations.get(i).servName +", Selling, " + servNegotiations.get(i).SNID));	
	    		currState = "SNID";
	    	}
	    	}
	    searchResultList.setModel(newListModel);
	   
	 }
}
