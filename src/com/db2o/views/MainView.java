package com.db2o.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.db2o.connections.MySQLConnection;

@SuppressWarnings("serial")
public class MainView extends JFrame {

	// Connection
	private MySQLConnection mySQLConnection = null;

	private JPanel contentPane;
	private Dimension goldRatioDimension = new Dimension(800, 495);
	private JTextField txtHost;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JTextField txtPort;
	private JTextField txtSchema;
	private JPanel pnlConnectionInfo;
	private JComboBox<String> cbbSchemas = null;
	private JLabel lblResult = null;
	private static final String[] CONNCETION_TYPE = new String[] { "MySQL", "Orale", "MS SQL Server", "SQLite",
			"MariaDB" };

	private void renderSchemas(LinkedList<String> schemas) {
		for (String item : schemas) {
			cbbSchemas.addItem(item);
		}
	}

	private void switchToConnectionType(String connectionType) {

		cbbSchemas.removeAllItems();
	}

	private void connectTo(String connectionType, boolean isTesting) {
		String host = txtHost.getText().trim();
		String port = txtPort.getText().trim();
		String username = txtUserName.getText().trim();
		String password = txtPassword.getPassword() != null ? new String(txtPassword.getPassword()) : "";
		String dbName = txtSchema.getText().trim();
		cbbSchemas.removeAllItems();
		switch (connectionType) {
		case "MySQL":
			connectToMySQL(host, port, username, password, dbName, isTesting);
			break;
		}
	}

	private void connectToMySQL(String host, String port, String username, String password, String defaultDb,
			boolean isTesting) {
		if (mySQLConnection == null) {
			mySQLConnection = new MySQLConnection(host, username, password, port, defaultDb);
		} else {
			mySQLConnection.changeTo(host, username, password, port, defaultDb);
		}
		if (mySQLConnection.isClosed()) {
			lblResult.setForeground(Color.RED);
			lblResult.setText("Connect failure!");
		} else {
			lblResult.setForeground(Color.BLUE);
			lblResult.setText("Connect successfull!");
		}
		if (isTesting) {
			System.out.println("Testing...");
		} else {
			LinkedList<String> schemas = mySQLConnection.getAllSchemas();
			renderSchemas(schemas);
		}
		mySQLConnection.closeConnection();
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("DB2O Generator - by lvtuan3689");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 629, 452);
		setLocation(100, 100);
		setSize(goldRatioDimension);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Connection infomation");
		lblNewLabel_1.setBounds(10, 81, 309, 14);
		contentPane.add(lblNewLabel_1);

		pnlConnectionInfo = new JPanel();
		pnlConnectionInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlConnectionInfo.setBounds(10, 98, 329, 165);
		contentPane.add(pnlConnectionInfo);
		pnlConnectionInfo.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Host *:");
		lblNewLabel_2.setBounds(10, 14, 105, 14);
		pnlConnectionInfo.add(lblNewLabel_2);

		txtHost = new JTextField();
		txtHost.setText("221.133.7.253");
		txtHost.setBounds(125, 11, 194, 20);
		pnlConnectionInfo.add(txtHost);
		txtHost.setColumns(10);

		txtUserName = new JTextField();
		txtUserName.setText("lvtuan");
		txtUserName.setColumns(10);
		txtUserName.setBounds(125, 70, 194, 20);
		pnlConnectionInfo.add(txtUserName);

		txtPort = new JTextField();
		txtPort.setText("3306");
		txtPort.setColumns(10);
		txtPort.setBounds(125, 39, 194, 20);
		pnlConnectionInfo.add(txtPort);

		JLabel lblPort = new JLabel("Port *:");
		lblPort.setBounds(10, 42, 105, 14);
		pnlConnectionInfo.add(lblPort);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(125, 101, 194, 20);
		pnlConnectionInfo.add(txtPassword);

		JLabel lblUsername = new JLabel("Username *:");
		lblUsername.setBounds(10, 73, 105, 14);
		pnlConnectionInfo.add(lblUsername);

		JLabel lblPassword = new JLabel("Password *:");
		lblPassword.setBounds(10, 104, 105, 14);
		pnlConnectionInfo.add(lblPassword);

		JLabel lblDefaultDb = new JLabel("Default schema:");
		lblDefaultDb.setBounds(10, 135, 105, 14);
		pnlConnectionInfo.add(lblDefaultDb);

		txtSchema = new JTextField();
		txtSchema.setText("sysdb");
		txtSchema.setColumns(10);
		txtSchema.setBounds(125, 132, 194, 20);
		pnlConnectionInfo.add(txtSchema);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 28, 329, 42);
		contentPane.add(panel);
		panel.setLayout(null);

		JComboBox<String> cbbConnectionType = new JComboBox<String>();
		cbbConnectionType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToConnectionType(cbbConnectionType.getSelectedItem().toString());
			}
		});
		cbbConnectionType.setBounds(10, 11, 309, 20);
		panel.add(cbbConnectionType);

		JLabel lblNewLabel = new JLabel("Select your source database");
		lblNewLabel.setBounds(10, 11, 289, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSelectYourSchema = new JLabel("Select your schema");
		lblSelectYourSchema.setBounds(10, 338, 289, 14);
		contentPane.add(lblSelectYourSchema);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 355, 329, 42);
		contentPane.add(panel_1);

		cbbSchemas = new JComboBox<String>();
		cbbSchemas.setBounds(10, 11, 309, 20);
		panel_1.add(cbbSchemas);

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_2.setBounds(10, 274, 329, 37);
		contentPane.add(panel_2);

		JButton btnReset = new JButton("Reset");
		panel_2.add(btnReset);

		JButton btnTestConnection = new JButton("Test connection");
		btnTestConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectTo(cbbConnectionType.getSelectedItem().toString(), true);
			}
		});
		panel_2.add(btnTestConnection);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectTo(cbbConnectionType.getSelectedItem().toString(), false);
			}
		});
		panel_2.add(btnConnect);

		lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setForeground(new Color(0, 0, 255));
		lblResult.setBounds(10, 313, 329, 14);
		contentPane.add(lblResult);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(349, 355, 425, 42);
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JCheckBox cbJava = new JCheckBox("Java");
		panel_3.add(cbJava);

		JCheckBox cbPHP5 = new JCheckBox("PHP5");
		panel_3.add(cbPHP5);

		JCheckBox cbPhp7 = new JCheckBox("PHP7");
		panel_3.add(cbPhp7);

		JCheckBox chckbxNewCheckBox = new JCheckBox("C#");
		panel_3.add(chckbxNewCheckBox);

		JLabel lblSelectProgrammingLanguages = new JLabel("Select programming languages");
		lblSelectProgrammingLanguages.setBounds(349, 338, 425, 14);
		contentPane.add(lblSelectProgrammingLanguages);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.setBounds(10, 408, 764, 38);
		contentPane.add(panel_4);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainView.this.setVisible(false);
				System.exit(EXIT_ON_CLOSE);
			}
		});
		panel_4.add(btnCancel);

		JButton btnGenerate = new JButton("Generate");
		panel_4.add(btnGenerate);

		JLabel lblSelectColumns = new JLabel("Select tables");
		lblSelectColumns.setBounds(349, 11, 289, 14);
		contentPane.add(lblSelectColumns);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(349, 28, 425, 299);
		contentPane.add(panel_5);

		JList<String> list = new JList<String>();
		panel_5.add(list);
		for (String type : CONNCETION_TYPE) {
			cbbConnectionType.addItem(type);
		}
	}
}
