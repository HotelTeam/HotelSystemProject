package admin;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import internal.Hotel;
import internal.Room;

public class DeleteRoomFrame extends javax.swing.JFrame {
	private Hotel hotel;
	private Vector<Room> rooms;
	public DeleteRoomFrame() {
		hotel = new Hotel();
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		returnButton = new javax.swing.JButton();
		deleteButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<String>();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Delete Room Monitor");

		returnButton.setText("Return");
		returnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});

		deleteButton.setText("Delete");
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});
		rooms = hotel.getRooms();
		DefaultListModel<String> lm = new DefaultListModel<String>();
		int i = 0;
		for (Room room : rooms) {
			lm.add(i, String.valueOf(room.getNumber()));
		}
		jList1.setModel(lm);
		jScrollPane1.setViewportView(jList1);

		jLabel2.setText("Select Room :");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap(
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		159,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(24, 24,
																		24)
																.addComponent(
																		returnButton)
																.addGap(0,
																		0,
																		Short.MAX_VALUE)))
								.addGap(5, 5, 5).addComponent(deleteButton)
								.addGap(39, 39, 39))
				.addGroup(
						layout.createSequentialGroup()
								.addGap(24, 24, 24)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														326,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														102,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(50, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										35, Short.MAX_VALUE)
								.addComponent(jLabel2)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										178,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(returnButton)
												.addComponent(deleteButton))
								.addContainerGap()));

		pack();
	}

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
		rooms = hotel.getRooms();
		Room delRoom = null;
		for (Room room : rooms) {
			if (room.getNumber() == Integer.parseInt(jList1.getSelectedValue())) {
				delRoom = room;
			}
		}
		if (delRoom != null) {
			int val = JOptionPane.showConfirmDialog(null,
					"Do you like to delete " + delRoom.getNumber() + " ?",
					"Delete", JOptionPane.OK_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.OK_OPTION) {
				hotel.deleteRoom(delRoom);
				new DeleteRoomFrame().setVisible(true);
				this.dispose();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Severe System Problem");
		}
	}

	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AdminBasicFrame().setVisible(true);
		this.dispose();
	}

	// Variables declaration - do not modify
	private javax.swing.JButton deleteButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton returnButton;
	// End of variables declaration
}
