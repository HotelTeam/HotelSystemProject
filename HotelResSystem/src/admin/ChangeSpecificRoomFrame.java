package admin;

import javax.swing.JOptionPane;

import internal.Hotel;
import internal.Room;
import internal.Suite;
import internal.Simple;

public class ChangeSpecificRoomFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Hotel hotel;
	private Suite suiteRoom;
	private Simple simpleRoom;
	private boolean roomtypeSuite;

	public ChangeSpecificRoomFrame(Room room) {
		hotel = new Hotel();
		initComponents();
		numberTextField.setText(String.valueOf(room.getNumber()));
		simpleRadioButton.setSelected(true);
		roomtypeSuite = hotel.isSuite(room);
		if (roomtypeSuite) {
			suiteRadioButton.setSelected(true);
			jacuzziCheckBox.setVisible(true);
			mealCheckBox.setVisible(true);
			breakfastCheckBox.setVisible(true);
			dinnerCheckBox.setVisible(true);
			suiteRoom = new Suite();
			suiteRoom.setId_room(room.getId_room());
			suiteRoom.setNumber(room.getNumber());
		} else {
			simpleRoom = new Simple();
			simpleRoom.setId_room(room.getId_room());
			simpleRoom.setNumber(room.getNumber());
		}
	}

	private void initComponents() {
		buttonGroup1 = new javax.swing.ButtonGroup();
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
		returnButton = new javax.swing.JButton();
		submitButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		simpleRadioButton = new javax.swing.JRadioButton();
		buttonGroup1.add(simpleRadioButton);
		buttonGroup1.add(suiteRadioButton);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

		numberTextField.setEditable(false);
		priceTextField.setText("0");
		discountTextField.setText("0");
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
																		.addGap(9,
																				9,
																				9)
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

		jLabel1.setText("Change the Room Monitor");

		simpleRadioButton.setText("Simple");
		simpleRadioButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						simpleRadioButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(46, 46,
																		46)
																.addComponent(
																		simpleRadioButton)
																.addGap(18, 18,
																		18)
																.addComponent(
																		suiteRadioButton))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						simplePanel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(12,
																										12,
																										12)
																								.addComponent(
																										returnButton)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										184,
																										Short.MAX_VALUE)
																								.addComponent(
																										submitButton)))))
								.addGap(73, 73, 73))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(0, 0, Short.MAX_VALUE)
								.addComponent(jLabel1).addGap(95, 95, 95))
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addGap(273, 273, 273)
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
																		javax.swing.GroupLayout.Alignment.LEADING))
												.addContainerGap(33,
														Short.MAX_VALUE))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										17,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(2, 2, 2)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(simpleRadioButton)
												.addComponent(suiteRadioButton))
								.addGap(18, 18, 18)
								.addComponent(simplePanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										257,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(returnButton)
												.addComponent(submitButton))
								.addContainerGap(26, Short.MAX_VALUE))
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addGap(181, 181, 181)
												.addComponent(jacuzziCheckBox)
												.addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(breakfastCheckBox)
												.addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(mealCheckBox)
												.addPreferredGap(
														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(dinnerCheckBox)
												.addContainerGap(96,
														Short.MAX_VALUE))));

		pack();
	}// </editor-fold>

	private void suiteRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		jacuzziCheckBox.setVisible(true);
		breakfastCheckBox.setVisible(true);
		mealCheckBox.setVisible(true);
		dinnerCheckBox.setVisible(true);
	}

	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new ChangeRoomFrame().setVisible(true);
		this.dispose();
	}

	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		boolean flag = false;
		if (roomtypeSuite) {
			suiteRoom.setAir_con(airconCheckBox.isSelected());
			suiteRoom.setMultimedia(multimediaCheckBox.isSelected());
			suiteRoom.setOffer(Double.parseDouble(discountTextField.getText()));
			suiteRoom.setPrice(Double.parseDouble(priceTextField.getText()));
			suiteRoom.setRefrigerator(refrigeratorCheckBox.isSelected());
			suiteRoom.setTv(tvCheckBox.isSelected());
			suiteRoom.setWi_fi(wifiCheckBox.isSelected());
			suiteRoom.setNumberOfBeds(jList1.getSelectedIndex() + 1);
			suiteRoom.setJacuzzi(jacuzziCheckBox.isSelected());
			suiteRoom.setMeal(mealCheckBox.isSelected());
			suiteRoom.setBreakfast(breakfastCheckBox.isSelected());
			suiteRoom.setDinner(dinnerCheckBox.isSelected());
			flag = hotel.updateSuiteRoom(suiteRoom);

		} else {
			simpleRoom.setPrice(Double.parseDouble(priceTextField.getText()));
			simpleRoom
					.setOffer(Double.parseDouble(discountTextField.getText()));
			simpleRoom.setAir_con(airconCheckBox.isSelected());
			simpleRoom.setMultimedia(multimediaCheckBox.isSelected());
			simpleRoom.setRefrigerator(refrigeratorCheckBox.isSelected());
			simpleRoom.setTv(tvCheckBox.isSelected());
			simpleRoom.setWi_fi(wifiCheckBox.isSelected());
			simpleRoom.setNumberOfBeds(jList1.getSelectedIndex() + 1);
			flag = hotel.updateSimpleRoom(simpleRoom);
		}
		if (flag) {
			JOptionPane.showMessageDialog(null, "The room is changed.");
			new ChangeRoomFrame().setVisible(true);
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					"The room has not been changed due to asystem error.");
		}
	}

	private void simpleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		jacuzziCheckBox.setVisible(false);
		breakfastCheckBox.setVisible(false);
		mealCheckBox.setVisible(false);
		dinnerCheckBox.setVisible(false);
	}

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

}
