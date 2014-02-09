
package manager;

import internal.Analysis;


import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class MonthlyAnalysisFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Analysis analysis;

	public MonthlyAnalysisFrame() {
		analysis = new Analysis();
		initComponents();
		Vector<String> year = analysis.getYears();
		Vector<String> months = analysis.getMonths();
		DefaultListModel<String> model = new DefaultListModel<String>();
		DefaultListModel<String> model1 = new DefaultListModel<String>();
		for (String string : year) {
			model.addElement(string);
		}
		for (String string1 : months) {
			model1.addElement(string1);
		}
		annualList.setModel(model);
		monthlyList.setModel(model1);
	}

	private void initComponents() {

		javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
		annualList = new javax.swing.JList<String>();
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
		javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
		monthlyList = new javax.swing.JList<String>();
		javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
		showAnalysisBtn = new javax.swing.JButton();
		javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
		incomeTxtField = new javax.swing.JTextField();
		javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
		outcomeTxtField = new javax.swing.JTextField();
		closeBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Οικονομικά Στοιχεία");

		jScrollPane1.setViewportView(annualList);

		jLabel1.setText("Επιλογή Έτους");

		jScrollPane2.setViewportView(monthlyList);

		jLabel2.setText("Επιλογή Μήνα");

		showAnalysisBtn.setText("Εμφάνιση");
		showAnalysisBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showAnalysisBtnActionPerformed(evt);
			}
		});

		jLabel3.setText("Έσοδα");

		incomeTxtField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				incomeTxtFieldActionPerformed(evt);
			}
		});

		jLabel4.setText("Έξοδα");

		closeBtn.setText("Κλείσιμο");
		closeBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane2,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						145,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel2,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						123,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel3)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel4)
																.addGap(121,
																		121,
																		121))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						showAnalysisBtn,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						140,
																						Short.MAX_VALUE)
																				.addComponent(
																						incomeTxtField,
																						javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						0,
																						Short.MAX_VALUE)
																				.addComponent(
																						jLabel1,
																						javax.swing.GroupLayout.Alignment.LEADING,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addGap(89, 89,
																		89)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						outcomeTxtField)
																				.addComponent(
																						closeBtn,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						145,
																						Short.MAX_VALUE))
																.addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(26, 26, 26)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(jLabel2))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3)
												.addComponent(jLabel4))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														incomeTxtField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														outcomeTxtField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										32, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(showAnalysisBtn)
												.addComponent(closeBtn))
								.addContainerGap()));

		pack();
	}// </editor-fold>

	private void showAnalysisBtnActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if(annualList.isSelectionEmpty()||monthlyList.isSelectionEmpty()){
        	JOptionPane.showMessageDialog(null, "Please select year and month");
        }else{
        	Map<String,Double> data=analysis.getMonthlyPrCo(annualList.getSelectedValue(), monthlyList.getSelectedValue());
        	if(data!=null){
        	incomeTxtField.setText(String.valueOf(data.get("profit")));
        	outcomeTxtField.setText(String.valueOf(data.get("cost")));
        	}else{	
        		incomeTxtField.setText("0");
            	outcomeTxtField.setText("0");	
        		
        	}
        }
        
        
    }	private void incomeTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {
		new ChooseAnalysisFrame().setVisible(true);
		this.dispose();
	}

	private javax.swing.JList<String> annualList;
	private javax.swing.JButton closeBtn;
	private javax.swing.JTextField incomeTxtField;
	private javax.swing.JList<String> monthlyList;
	private javax.swing.JTextField outcomeTxtField;
	private javax.swing.JButton showAnalysisBtn;
	// End of variables declaration

}
