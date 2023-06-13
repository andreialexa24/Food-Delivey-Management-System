package View;

import Controller.Controller;
import Model.BaseProduct;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreateProduct extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField rating;
	private JTextField calories;
	private JTextField proteins;
	private JTextField fats;
	private JTextField sodium;
	private JTextField price;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProduct frame = new CreateProduct(null);
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
	public CreateProduct(Controller c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(36, 28, 137, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRating.setBounds(36, 75, 137, 38);
		contentPane.add(lblRating);
		
		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCalories.setBounds(36, 123, 137, 38);
		contentPane.add(lblCalories);
		
		JLabel lblProteins = new JLabel("Proteins:");
		lblProteins.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProteins.setBounds(36, 175, 137, 38);
		contentPane.add(lblProteins);
		
		JLabel lblFats = new JLabel("Fats:");
		lblFats.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFats.setBounds(36, 223, 137, 38);
		contentPane.add(lblFats);
		
		JLabel lblSodium = new JLabel("Sodium:");
		lblSodium.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSodium.setBounds(36, 275, 137, 38);
		contentPane.add(lblSodium);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(36, 328, 137, 38);
		contentPane.add(lblPrice);
		
		name = new JTextField();
		name.setBounds(147, 28, 158, 38);
		contentPane.add(name);
		name.setColumns(10);
		
		rating = new JTextField();
		rating.setColumns(10);
		rating.setBounds(147, 76, 158, 38);
		contentPane.add(rating);
		
		calories = new JTextField();
		calories.setColumns(10);
		calories.setBounds(147, 123, 158, 38);
		contentPane.add(calories);
		
		proteins = new JTextField();
		proteins.setColumns(10);
		proteins.setBounds(147, 171, 158, 38);
		contentPane.add(proteins);
		
		fats = new JTextField();
		fats.setColumns(10);
		fats.setBounds(147, 219, 158, 38);
		contentPane.add(fats);
		
		sodium = new JTextField();
		sodium.setColumns(10);
		sodium.setBounds(147, 271, 158, 38);
		contentPane.add(sodium);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBounds(147, 323, 158, 38);
		contentPane.add(price);
		
		JButton create = new JButton("Create");

		create.setFont(new Font("Tahoma", Font.BOLD, 18));
		create.setBounds(351, 143, 158, 92);
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title = name.getText();
				double Rating = Double.parseDouble(rating.getText());
				int Callories = Integer.parseInt(calories.getText());
				int Proteins = Integer.parseInt(proteins.getText());
				int Fats = Integer.parseInt(fats.getText());
				int Sodium = Integer.parseInt(sodium.getText());
				int Price= Integer.parseInt(price.getText());
				BaseProduct produs = new BaseProduct(title, Rating, Callories, Proteins, Fats, Sodium, Price);
				c.getDeliveryService().addProduct(produs);
				c.getAdmin().getTable().setModel(c.tabelProduse(c.getDeliveryService().getProducts()));
				dispose();
			}
		});
		contentPane.add(create);
	}

}
