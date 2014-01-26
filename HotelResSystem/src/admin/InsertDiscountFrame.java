package admin;

import java.util.Vector;

import internal.Hotel;
import internal.Room;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class InsertDiscountFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Hotel hotel;
	private Vector<Room> rooms;
	private Room disRoom;

	public InsertDiscountFrame() {
		hotel = new Hotel();
		initComponents();
	}

	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<String>();
		selectButton = new javax.swing.JButton();
		discountLabel = new javax.swing.JLabel();
		discountLabel.setVisible(false);
		discountTextField = new javax.swing.JTextField();
		discountTextField.setVisible(false);
		discountTextField.setText("0");
		submitButton = new javax.swing.JButton();
		submitButton.setVisible(false);
		returnButton = new javax.swing.JButton();
		currentDiscountLabel = new javax.swing.JLabel();
		currentDiscountLabel.setVisible(false);
		curDiscTextField = new javax.swing.JTextField();
		curDiscTextField.setVisible(false);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Insert Discount Monitor");

		jLabel2.setText("Select Room:");
		rooms = hotel.getRooms();
		DefaultListModel<String> lm = new DefaultListModel<String>();
		int i = 0;
		for (Room room : rooms) {
			lm.add(i, String.valueOf(room.getNumber()));
		}

		jList1.setModel(lm);

		jScrollPane1.setViewportView(jList1);

		selectButton.setText("Select");
		selectButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				selectButtonActionPerformed(evt);
			}
		});

		discountLabel.setText("Discount");

		submitButton.setText("Submit");
		submitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				submitButtonActionPerformed(evt);
			}
		});

		returnButton.setText("Return");
		returnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});

		currentDiscountLabel.setText("Current Discount:");

		curDiscTextField.setEditable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jLabel1)
								.addGap(117, 117, 117))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		discountLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		76,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		discountTextField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		117,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		submitButton)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		currentDiscountLabel)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		curDiscTextField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		126,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap(
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jScrollPane1,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						220,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel2))
																.addGap(90, 90,
																		90)
																.addComponent(
																		selectButton))
												.addComponent(returnButton))
								.addContainerGap(34, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jScrollPane1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		87,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(48, 48,
																		48)
																.addComponent(
																		selectButton)))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														currentDiscountLabel)
												.addComponent(
														curDiscTextField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														discountLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														32,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														discountTextField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(submitButton))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										87, Short.MAX_VALUE)
								.addComponent(returnButton)));

		pack();
	}// </editor-fold>

	private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (!jList1.isSelectionEmpty()) {
			rooms = hotel.getRooms();
			discountLabel.setVisible(true);
			discountTextField.setVisible(true);
			submitButton.setVisible(true);
			curDiscTextField.setVisible(true);
			currentDiscountLabel.setVisible(true);
			for (Room room : rooms) {
				if (room.getNumber() == Integer.parseInt(jList1
						.getSelectedValue())) {
					disRoom = room;
				}
			}
			curDiscTextField.setText("-"
					+ String.valueOf(disRoom.getOffer() * 100) + " %");
		} else {
			JOptionPane.showMessageDialog(null, "You must select a room");
		}
	}

	private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		int val = JOptionPane.showConfirmDialog(
				null,
				"Do you like to change the discount of the room "
						+ disRoom.getNumber() + " ?", "Discount",
				JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (val == JOptionPane.OK_OPTION) {
			hotel.setOffer(Double.parseDouble(discountTextField.getText()),
					disRoom);
			discountLabel.setVisible(false);
			discountTextField.setVisible(false);
			curDiscTextField.setVisible(false);
			currentDiscountLabel.setVisible(false);
			submitButton.setVisible(false);
			discountTextField.setText("0");
		}
	}

	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AdminBasicFrame().setVisible(true);
		this.dispose();
	}

	private javax.swing.JTextField curDiscTextField;
	private javax.swing.JLabel currentDiscountLabel;
	private javax.swing.JLabel discountLabel;
	private javax.swing.JTextField discountTextField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton returnButton;
	private javax.swing.JButton selectButton;
	private javax.swing.JButton submitButton;
	// End of variables declaration
}
