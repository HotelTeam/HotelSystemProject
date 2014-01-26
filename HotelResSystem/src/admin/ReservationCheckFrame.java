package admin;

import java.util.Vector;

import javax.swing.DefaultListModel;

import internal.Hotel;
import internal.Reservation;

public class ReservationCheckFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private Hotel hotel;
	private Vector<Reservation> res;

	public ReservationCheckFrame() {
		hotel = new Hotel();
		initComponents();
	}
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<String>();
		returnButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Reservation Check Monitor");

		jLabel2.setText("Select Room:");
		DefaultListModel<String> lm = new DefaultListModel<String>();
		res = hotel.getReservations();
		int i = 0;
		for (Reservation reservation : res) {
			lm.add(i,
					"ResNumber:" + reservation.getId() + ".Room: "
							+ reservation.getRoom().getNumber() + ".Value: "
							+ reservation.getPrice() + ".From :"
							+ reservation.getArrivalDate() + " to:"
							+ reservation.getDepartureDate());
			i++;
		}
		jList1.setModel(lm);
		jScrollPane2.setViewportView(jList1);

		returnButton.setText("Return");
		returnButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				returnButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(102, Short.MAX_VALUE)
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										199,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(99, 99, 99))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										105,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 295, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(returnButton)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jScrollPane2).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel2)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										185,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(returnButton)
								.addContainerGap(27, Short.MAX_VALUE)));

		pack();
	}

	private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new AdminBasicFrame().setVisible(true);
		this.dispose();
	}

	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton returnButton;
	// End of variables declaration
}
