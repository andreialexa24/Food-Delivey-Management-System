package View;

import Controller.Controller;
import Model.Rank;
import Model.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pass;
	private JPasswordField pass_confirm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(10, 10, 538, 390);
		contentPane.add(contentPane_1);

		username = new JTextField();
		username.setColumns(10);
		username.setBounds(196, 87, 181, 39);
		contentPane_1.add(username);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(90, 87, 141, 39);
		contentPane_1.add(lblNewLabel);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblParola.setBounds(118, 136, 141, 39);
		contentPane_1.add(lblParola);

		JButton register = new JButton("Register");
		register.setFont(new Font("Tahoma", Font.BOLD, 18));
		register.setBounds(196, 277, 154, 39);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Username = username.getText();
				String pass1 = pass.getText();
				String pass2 = pass_confirm.getText();
				if(!pass1.equals(pass2)){
					JOptionPane.showMessageDialog(c.getRegister(), "Parolele nu coincid !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					User u = new User(Username, pass1, Rank.CLIENT);
					c.getDeliveryService().addUser(u);
					dispose();
				}


			}
		});
		contentPane_1.add(register);

		pass = new JPasswordField();
		pass.setBounds(196, 136, 181, 39);
		contentPane_1.add(pass);

		pass_confirm = new JPasswordField();
		pass_confirm.setBounds(196, 196, 181, 39);
		contentPane_1.add(pass_confirm);

		JLabel lblConfirmareParola = new JLabel("Confirmare parola");
		lblConfirmareParola.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConfirmareParola.setBounds(19, 193, 212, 39);
		contentPane_1.add(lblConfirmareParola);
	}

}
