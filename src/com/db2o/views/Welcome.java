package com.db2o.views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Welcome extends JFrame {

	private JPanel contentPane;
	private Dimension goldRatioDimension = new Dimension(700, 433);

	/**
	 * Create the frame.
	 */
	public Welcome() {
		setTitle("DB2O Generator - by lvtuan3689");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 629, 452);
		setLocation(100, 100);
		setSize(goldRatioDimension);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
