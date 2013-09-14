import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;


public class RetrievePage extends JPanel {

    JTextField IDField;
	JRadioButton prodNegRadBtn;
	JRadioButton servNegRadBtn;
	JButton retrieveBtn;
	JButton backBtn;
	/**
	 * Create the panel.
	 */
	public RetrievePage() {
		setLayout(null);
		
		IDField = new JTextField();
		IDField.setBounds(103, 90, 139, 20);
		add(IDField);
		IDField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(58, 93, 35, 14);
		add(lblId);
		
		prodNegRadBtn = new JRadioButton("Product Negotiation");
		prodNegRadBtn.setBounds(24, 42, 139, 23);
		add(prodNegRadBtn);
		
		JLabel lblRetrieveNegotiation = new JLabel("Retrieve Negotiation");
		lblRetrieveNegotiation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRetrieveNegotiation.setBounds(103, 11, 165, 24);
		add(lblRetrieveNegotiation);
		
		servNegRadBtn = new JRadioButton("Service Negotiation");
		servNegRadBtn.setBounds(184, 42, 132, 23);
		add(servNegRadBtn);
		
		retrieveBtn = new JButton("Retrieve");
		retrieveBtn.setBounds(184, 132, 89, 23);
		add(retrieveBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(58, 132, 89, 23);
		add(backBtn);

	}
}
