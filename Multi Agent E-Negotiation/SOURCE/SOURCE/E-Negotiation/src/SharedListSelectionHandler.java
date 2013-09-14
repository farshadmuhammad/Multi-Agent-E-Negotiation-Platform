import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class SharedListSelectionHandler implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        if (lsm.isSelectionEmpty()) {
        	
        } else if (lsm.getValueIsAdjusting()) {
            ProdNegotiationPage.enableChat();
            ServNegotiationPage.enableChat();
        	MainPage.setChatArea(0);
             }
            }
       
       
    
}