package admin;

import javax.swing.JOptionPane;

import internal.Hotel;
import internal.Simple;
import internal.Suite;

public class InsertRoomFrame extends javax.swing.JFrame {
	
	private static final long serialVersionUID = 1L;
	private Suite suite;
	private Simple simple;
	private Hotel hotel;

	public InsertRoomFrame() {
		initComponents();
	}
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		returnButton = new javax.swing.JButton();
		submitButton = new javax.swing.JButton();
		simpleRadioButton = new javax.swing.JRadioButton();
		suiteRadioButton = new javax.swing.JRadioButton();
		simplePanel = new javax.swing.JPanel();
		multimediaCheckBox = new javax.swing.JCheckBox();
		wifiCheckBox = new javax.swing.JCheckBox();
		tvCheckBox = new javax.swing.JCheckBox();
		refrigeratorCheckBox = new javax.swing.JCheckBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<String>();
		jLabel2 = new javax.swing.JLabel();
		airconCheckBox = new javax.swing.JCheckBox();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		numberTextField = new javax.swing.JTextField();
		priceTextField = new javax.swing.JTextField();
		discountTextField = new javax.swing.JTextField();
		jacuzziCheckBox = new javax.swing.JCheckBox();
		jacuzziCheckBox.setVisible(false);
		breakfastCheckBox = new javax.swing.JCheckBox();
		breakfastCheckBox.setVisible(false);
		mealCheckBox = new javax.swing.JCheckBox();
		mealCheckBox.setVisible(false);
		dinnerCheckBox = new javax.swing.JCheckBox();
		dinnerCheckBox.setVisible(false);
		buttonGroup1.add(simpleRadioButton);
		buttonGroup1.add(suiteRadioButton);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setText("Insert Room Monitor");
		returnButton.setText("Return");
		returnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});
		submitButton.setText("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});
		simpleRadioButton.setSelected(true);
		simpleRadioButton.setText("Simple");
		simpleRadioButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						simpleRadioButtonActionPerformed(evt);
					}
				});
		suiteRadioButton.setText("Suite");
		suiteRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				suiteRadioButtonActionPerformed(evt);
			}
		});
		multimediaCheckBox.setText("Multimedia");
		wifiCheckBox.setText("Wi-Fi");
		tvCheckBox.setText("TV");
		refrigeratorCheckBox.setText("Refrigerator");
		jList1.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "1", "2", "3", "4", "5" };

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(jList1);
		jLabel2.setText("Beds");
		airconCheckBox.setText("Air Con");
		jLabel3.setText("Number");
		jLabel4.setText("Price");
		jLabel5.setText("Discount");
		priceTextField.setText("0");
		discountTextField.setText("0");
		numberTextField.setText("0");
		javax.swing.GroupLayout simplePanelLayout = new javax.swing.GroupLayout(
				simplePanel);
		simplePanel.setLayout(simplePanelLayout);
		simplePanelLayout
				.setHorizontalGroup(simplePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								simplePanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																simplePanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				simplePanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								multimediaCheckBox)
																						.addComponent(
																								wifiCheckBox)
																						.addComponent(
																								tvCheckBox)
																						.addComponent(
																								airconCheckBox))
																		.addGap(26,
																				26,
																				26)
																		.addGroup(
																				simplePanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabel2,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addGroup(
																								simplePanelLayout
																										.createSequentialGroup()
																										.addComponent(
																												jScrollPane1,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												44,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												Short.MAX_VALUE))))
														.addGroup(
																simplePanelLayout
																		.createSequentialGroup()
																		.addGroup(
																				simplePanelLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								simplePanelLayout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addGroup(
																												simplePanelLayout
																														.createSequentialGroup()
																														.addGroup(
																																simplePanelLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.TRAILING,
																																				false)
																																		.addComponent(
																																				jLabel4,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE)
																																		.addComponent(
																																				jLabel3,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				javax.swing.GroupLayout.DEFAULT_SIZE,
																																				Short.MAX_VALUE))
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addGroup(
																																simplePanelLayout
																																		.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																		.addComponent(
																																				priceTextField)
																																		.addComponent(
																																				numberTextField)))
																										.addGroup(
																												simplePanelLayout
																														.createSequentialGroup()
																														.addComponent(
																																jLabel5)
																														.addPreferredGap(
																																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																														.addComponent(
																																discountTextField,
																																javax.swing.GroupLayout.PREFERRED_SIZE,
																																100,
																																javax.swing.GroupLayout.PREFERRED_SIZE)))
																						.addComponent(
																								refrigeratorCheckBox))
																		.addGap(0,
																				13,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		simplePanelLayout
				.setVerticalGroup(simplePanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								simplePanelLayout
										.createSequentialGroup()
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel3)
														.addComponent(
																numberTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel4)
														.addComponent(
																priceTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel5)
														.addComponent(
																discountTextField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																airconCheckBox)
														.addComponent(jLabel2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												simplePanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																63,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																simplePanelLayout
																		.createSequentialGroup()
																		.addComponent(
																				multimediaCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				wifiCheckBox)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				tvCheckBox)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(refrigeratorCheckBox)
										.addGap(21, 21, 21)));
		jacuzziCheckBox.setText("Jacuzzi");
		breakfastCheckBox.setText("Breakfast");
		mealCheckBox.setText("Meal");
		dinnerCheckBox.setText("Dinner");
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(returnButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(submitButton).addGap(69, 69, 69))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(simpleRadioButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(suiteRadioButton)
								.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(130,
																		130,
																		130)
																.addComponent(
																		jLabel1))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		simplePanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(30, 30,
																		30)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						breakfastCheckBox)
																				.addComponent(
																						mealCheckBox,
																						javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						dinnerCheckBox,
																						javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jacuzziCheckBox,
																						javax.swing.GroupLayout.Alignment.LEADING))))
								.addContainerGap(65, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(simpleRadioButton)
												.addComponent(suiteRadioButton))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		simplePanel,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						returnButton)
																				.addComponent(
																						submitButton))
																.addContainerGap())
												.addGroup(
														layout.createSequentialGroup()
																.addGap(122,
																		122,
																		122)
																.addComponent(
																		jacuzziCheckBox)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		breakfastCheckBox)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		mealCheckBox)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		dinnerCheckBox)
																.addContainerGap(
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)))));
		pack();
	}

	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		hotel = new Hotel();
		if (simpleRadioButton.isSelected()) {
			simple = new Simple();
			simple.setAir_con(airconCheckBox.isSelected());
			simple.setMultimedia(multimediaCheckBox.isSelected());
			simple.setNumber(Integer.parseInt(numberTextField.getText()));
			simple.setOffer(Double.parseDouble(discountTextField.getText()));
			simple.setPrice(Double.parseDouble(priceTextField.getText()));
			simple.setRefrigerator(refrigeratorCheckBox.isSelected());
			simple.setTv(tvCheckBox.isSelected());
			simple.setWi_fi(wifiCheckBox.isSelected());
			simple.setNumberOfBeds(jList1.getSelectedIndex() + 1);
			if (!hotel.hasRoom(simple)) {
				hotel.createSimpleRoom(simple);
				JOptionPane.showMessageDialog(null, "The room is created");
			} else {
				JOptionPane.showMessageDialog(null, "The room already exists");
			}
		} else if (suiteRadioButton.isSelected()) {
			suite = new Suite();
			suite.setAir_con(airconCheckBox.isSelected());
			suite.setMultimedia(multimediaCheckBox.isSelected());
			suite.setNumber(Integer.parseInt(numberTextField.getText()));
			suite.setOffer(Double.parseDouble(discountTextField.getText()));
			suite.setPrice(Double.parseDouble(priceTextField.getText()));
			suite.setRefrigerator(refrigeratorCheckBox.isSelected());
			suite.setTv(tvCheckBox.isSelected());
			suite.setWi_fi(wifiCheckBox.isSelected());
			suite.setNumberOfBeds(jList1.getSelectedIndex() + 1);
			suite.setJacuzzi(jacuzziCheckBox.isSelected());
			suite.setMeal(mealCheckBox.isSelected());
			suite.setBreakfast(breakfastCheckBox.isSelected());
			suite.setDinner(dinnerCheckBox.isSelected());
			if (!hotel.hasRoom(suite)) {
				hotel.createSuiteRoom(suite);
				JOptionPane.showMessageDialog(null, "The room is created");
			} else {
				JOptionPane.showMessageDialog(null, "The room already exists");
			}
		}
		airconCheckBox.setSelected(false);
		multimediaCheckBox.setSelected(false);
		numberTextField.setText("");
		discountTextField.setText("");
		priceTextField.setText("");
		refrigeratorCheckBox.setSelected(false);
		tvCheckBox.setSelected(false);
		wifiCheckBox.setSelected(false);
		jList1.setSelectedIndex(0);
		jacuzziCheckBox.setSelected(false);
		mealCheckBox.setSelected(false);
		breakfastCheckBox.setSelected(false);
		dinnerCheckBox.setSelected(false);
	}

	private void suiteRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		jacuzziCheckBox.setVisible(true);
		breakfastCheckBox.setVisible(true);
		mealCheckBox.setVisible(true);
		dinnerCheckBox.setVisible(true);
	}

	private void simpleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		jacuzziCheckBox.setVisible(false);
		breakfastCheckBox.setVisible(false);
		mealCheckBox.setVisible(false);
		dinnerCheckBox.setVisible(false);
	}

	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AdminBasicFrame().setVisible(true);
		this.dispose();
	}

	// Variables declaration - do not modify
	private javax.swing.JCheckBox airconCheckBox;
	private javax.swing.JCheckBox breakfastCheckBox;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JCheckBox dinnerCheckBox;
	private javax.swing.JTextField discountTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JCheckBox jacuzziCheckBox;
	private javax.swing.JCheckBox mealCheckBox;
	private javax.swing.JCheckBox multimediaCheckBox;
	private javax.swing.JTextField numberTextField;
	private javax.swing.JTextField priceTextField;
	private javax.swing.JCheckBox refrigeratorCheckBox;
	private javax.swing.JButton returnButton;
	private javax.swing.JPanel simplePanel;
	private javax.swing.JRadioButton simpleRadioButton;
	private javax.swing.JButton submitButton;
	private javax.swing.JRadioButton suiteRadioButton;
	private javax.swing.JCheckBox tvCheckBox;
	private javax.swing.JCheckBox wifiCheckBox;
	// End of variables declaration
}
