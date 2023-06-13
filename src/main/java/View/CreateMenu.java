package View;

import Controller.Controller;
import Model.CompositeProduct;
import Model.FileWriter;
import Model.MenuItem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CreateMenu extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField rating;
	private JTextField calories;
	private JTextField proteins;
	private JTextField fats;
	private JTextField sodium;
	private JTextField price;
	private JTable products;
	private JTable menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateMenu frame = new CreateMenu(null);
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
	public CreateMenu(Controller c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1192, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(462, -17, 311, 377);
		contentPane.add(contentPane_1);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(36, 28, 137, 38);
		contentPane_1.add(lblNewLabel);

		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRating.setBounds(36, 75, 137, 38);
		contentPane_1.add(lblRating);

		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCalories.setBounds(36, 123, 137, 38);
		contentPane_1.add(lblCalories);

		JLabel lblProteins = new JLabel("Proteins:");
		lblProteins.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProteins.setBounds(36, 175, 137, 38);
		contentPane_1.add(lblProteins);

		JLabel lblFats = new JLabel("Fats:");
		lblFats.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFats.setBounds(36, 223, 137, 38);
		contentPane_1.add(lblFats);

		JLabel lblSodium = new JLabel("Sodium:");
		lblSodium.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSodium.setBounds(36, 275, 137, 38);
		contentPane_1.add(lblSodium);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(36, 328, 137, 38);
		contentPane_1.add(lblPrice);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(147, 28, 158, 38);
		contentPane_1.add(name);

		rating = new JTextField();
		rating.setColumns(10);
		rating.setBounds(147, 76, 158, 38);
		contentPane_1.add(rating);

		calories = new JTextField();
		calories.setColumns(10);
		calories.setBounds(147, 123, 158, 38);
		contentPane_1.add(calories);

		proteins = new JTextField();
		proteins.setColumns(10);
		proteins.setBounds(147, 171, 158, 38);
		contentPane_1.add(proteins);

		fats = new JTextField();
		fats.setColumns(10);
		fats.setBounds(147, 219, 158, 38);
		contentPane_1.add(fats);

		sodium = new JTextField();
		sodium.setColumns(10);
		sodium.setBounds(147, 271, 158, 38);
		contentPane_1.add(sodium);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(147, 323, 158, 38);
		contentPane_1.add(price);

		JButton add = new JButton("Add to List");
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.setBounds(558, 370, 154, 46);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = products.getSelectedRow();
				List<Object> row = new ArrayList<>();
				for(int i = 0; i < products.getColumnCount(); i++){
					row.add(products.getValueAt(selected_row, i));
				}
				DefaultTableModel model= (DefaultTableModel) menu.getModel();
				model.addRow(row.toArray());
			}
		});
		contentPane.add(add);

		JButton remove = new JButton("Remove from list");
		remove.setFont(new Font("Tahoma", Font.BOLD, 18));
		remove.setBounds(529, 426, 203, 46);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = menu.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) menu.getModel();
				model.removeRow(selected_row);
			}
		});
		contentPane.add(remove);

		JButton create = new JButton("Create");
		create.setFont(new Font("Tahoma", Font.BOLD, 18));
		create.setBounds(558, 486, 154, 46);
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
				CompositeProduct item = new CompositeProduct(title, Rating, Callories, Proteins, Fats, Sodium, Price);
				for(int i = 0; i < menu.getRowCount(); i++){
					String name = (String) menu.getValueAt(i, 0);
					MenuItem m = c.getDeliveryService().findProduct(name);
					item.addProduct(m);

				}
				c.getDeliveryService().addProduct(item);
				c.getAdmin().getTable().setModel(c.tabelProduse(c.getDeliveryService().getProducts()));
				dispose();
			}

		});
		contentPane.add(create);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 442, 605);
		contentPane.add(scrollPane);

		products = new JTable();
		scrollPane.setViewportView(products);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(783, 10, 385, 624);
		contentPane.add(scrollPane_1);

		menu = new JTable();
		scrollPane_1.setViewportView(menu);
		products.setModel(c.tabelProduse(c.getDeliveryService().getProducts()));

	DefaultTableModel defaultTableModel = new DefaultTableModel();
		Field[] declaredFields = MenuItem.class.getDeclaredFields();
		for (Field f : declaredFields) {
			f.setAccessible(true);
			defaultTableModel.addColumn(f.getName());
		}
		menu.setModel(defaultTableModel);

	}



}
