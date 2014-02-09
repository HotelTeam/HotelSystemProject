package main;

import client.ClientValidationFrame;
import manager.ManagerValidationFrame;
import admin.AdminValidationFrame;

public class MainFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		initComponents();
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		insertClientButton = new javax.swing.JButton();
		insertManagerButton = new javax.swing.JButton();
		insertAdministratorButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Hotel Reservation System");

		insertClientButton.setText("Insert as Client");
		insertClientButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						insertClientButtonActionPerformed(evt);
					}
				});

		insertManagerButton.setText("Insert as Manager");
		insertManagerButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						insertManagerButtonActionPerformed(evt);
					}
				});

		insertAdministratorButton.setText("Insert as Administrator");
		insertAdministratorButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						insertAdministratorButtonActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(100, 100, 100)
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										186,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(114, Short.MAX_VALUE))
				.addComponent(insertClientButton,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(insertManagerButton,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(insertAdministratorButton,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLabel1)
								.addGap(40, 40, 40)
								.addComponent(insertClientButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										48, Short.MAX_VALUE)
								.addComponent(insertManagerButton)
								.addGap(45, 45, 45)
								.addComponent(insertAdministratorButton)
								.addGap(63, 63, 63)));

		pack();
	}

	private void insertClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new ClientValidationFrame().setVisible(true);
		this.dispose();
	}

	private void insertManagerButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		new ManagerValidationFrame().setVisible(true);
		this.dispose();
	}

	private void insertAdministratorButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		new AdminValidationFrame().setVisible(true);
		this.dispose();
	}

	private javax.swing.JButton insertAdministratorButton;
	private javax.swing.JButton insertClientButton;
	private javax.swing.JButton insertManagerButton;
	private javax.swing.JLabel jLabel1;
}
