
package manager;

import internal.Analysis;


import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
public class AnnualAnalysisFrame extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private Analysis analysis;
	
    public AnnualAnalysisFrame() {
        analysis= new Analysis();
        initComponents();
        Vector<String> year = analysis.getYears();
        DefaultListModel<String> model = new DefaultListModel<String>();
        for (String string : year) {
			model.addElement(string);
		}
        
        annualList.setModel(model); 
    }

                     
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        annualList = new javax.swing.JList<String>();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        incomeTxtField = new javax.swing.JTextField();
        outcomeTxField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        showAnalsisBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ετήσια Οικονομικά Στοιχεία");

        jScrollPane1.setViewportView(annualList);

        jLabel1.setText("Επλογή Έτους");

        incomeTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incomeTxtFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Έσοδα");

        jLabel3.setText("Έξοδα");

        showAnalsisBtn.setText("Εμφάνιση");
        showAnalsisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showAnalsisBtnActionPerformed(evt);
            }
        });

        closeBtn.setText("Κλείσιμο");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(showAnalsisBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                            .addComponent(incomeTxtField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(outcomeTxField)
                            .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 126, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outcomeTxField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incomeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showAnalsisBtn)
                    .addComponent(closeBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void incomeTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void showAnalsisBtnActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	if(annualList.isSelectionEmpty()){
    		JOptionPane.showMessageDialog(null,"Please select a Year first");
    	}else{
    		incomeTxtField.setText(String.valueOf(analysis.getYearProfit(annualList.getSelectedValue())));
    		outcomeTxField.setText(String.valueOf(analysis.getYearCost(annualList.getSelectedValue())));
    		
    	}
        
       
    }                                              

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        new ChooseAnalysisFrame().setVisible(true);
    	this.dispose();
    }                                        

    

    // Variables declaration - do not modify                     
    private javax.swing.JList<String> annualList;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField incomeTxtField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField outcomeTxField;
    private javax.swing.JButton showAnalsisBtn;
    // End of variables declaration                   
 
}
