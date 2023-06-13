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
import java.util.concurrent.locks.ReentrantLock;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(null);
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
	public Login(Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		username = new JTextField();
		username.setBounds(196, 87, 181, 39);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(90, 87, 141, 39);
		contentPane.add(lblNewLabel);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblParola.setBounds(118, 136, 141, 39);
		contentPane.add(lblParola);

		JButton register = new JButton("Register");
		register.setFont(new Font("Tahoma", Font.BOLD, 18));
		register.setBounds(211, 271, 154, 39);
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setRegister(new Register(c));
				c.getRegister().setVisible(true);

			}
		});
		contentPane.add(register);

		pass = new JPasswordField();
		pass.setBounds(196, 136, 181, 39);
		contentPane.add(pass);

		JButton login = new JButton("Login");
		login.setFont(new Font("Tahoma", Font.BOLD, 18));
		login.setBounds(211, 212, 154, 39);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Username =  username.getText();
				String Parola = String.valueOf(pass.getPassword());
				User U = c.getDeliveryService().findUser(Username);
				if(U == null){
					JOptionPane.showMessageDialog(c.getLogin(), "Incorrect username or password !", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if (!U.getPass().equals(Parola))
						JOptionPane.showMessageDialog(c.getLogin(), "Incorrect username or password !", "Error", JOptionPane.ERROR_MESSAGE);
					else{
					c.getDeliveryService().setCurrentUser(U);
					if(U.getRank() == Rank.ADMIN){
						c.setAdmin(new Admin(c));
						c.getAdmin().setVisible(true);
					}
					else if(U.getRank() == Rank.EMPLOYEE){
						c.setAngajat(new Employee());
						c.getAngajat().setVisible(true);
						c.getDeliveryService().getEmployees().add(c.getAngajat());
					}
					else {
						c.setClient(new Client(c));
						c.getClient().setVisible(true);
					}}
				}
			}
		});
		contentPane.add(login);
	}
}
