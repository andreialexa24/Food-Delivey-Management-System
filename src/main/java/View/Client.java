package View;

import Controller.Controller;
import Model.BaseProduct;
import Model.FileWriter;
import Model.MenuItem;
import Model.Order;

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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField rating;
	private JTextField calories;
	private JTextField proteins;
	private JTextField fats;
	private JTextField sodium;
	private JTextField price;
	private JTable products;
	private JTable cart;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client(null);
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
	public Client(Controller c){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1192, 749);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(10, 10, 1178, 644);
		contentPane.add(contentPane_1);

		JPanel contentPane_1_1 = new JPanel();
		contentPane_1_1.setLayout(null);
		contentPane_1_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1_1.setBounds(462, -17, 311, 377);
		contentPane_1.add(contentPane_1_1);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(36, 28, 137, 38);
		contentPane_1_1.add(lblNewLabel);

		JLabel lblRating = new JLabel("Rating:");
		lblRating.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRating.setBounds(36, 75, 137, 38);
		contentPane_1_1.add(lblRating);

		JLabel lblCalories = new JLabel("Calories:");
		lblCalories.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCalories.setBounds(36, 123, 137, 38);
		contentPane_1_1.add(lblCalories);

		JLabel lblProteins = new JLabel("Proteins:");
		lblProteins.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProteins.setBounds(36, 175, 137, 38);
		contentPane_1_1.add(lblProteins);

		JLabel lblFats = new JLabel("Fats:");
		lblFats.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFats.setBounds(36, 223, 137, 38);
		contentPane_1_1.add(lblFats);

		JLabel lblSodium = new JLabel("Sodium:");
		lblSodium.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSodium.setBounds(36, 275, 137, 38);
		contentPane_1_1.add(lblSodium);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(36, 328, 137, 38);
		contentPane_1_1.add(lblPrice);

		name = new JTextField();
		name.setColumns(10);
		name.setBounds(147, 28, 158, 38);
		contentPane_1_1.add(name);

		rating = new JTextField();
		rating.setColumns(10);
		rating.setBounds(147, 76, 158, 38);
		contentPane_1_1.add(rating);

		calories = new JTextField();
		calories.setColumns(10);
		calories.setBounds(147, 123, 158, 38);
		contentPane_1_1.add(calories);

		proteins = new JTextField();
		proteins.setColumns(10);
		proteins.setBounds(147, 171, 158, 38);
		contentPane_1_1.add(proteins);

		fats = new JTextField();
		fats.setColumns(10);
		fats.setBounds(147, 219, 158, 38);
		contentPane_1_1.add(fats);

		sodium = new JTextField();
		sodium.setColumns(10);
		sodium.setBounds(147, 271, 158, 38);
		contentPane_1_1.add(sodium);

		price = new JTextField();
		price.setColumns(10);
		price.setBounds(147, 323, 158, 38);
		contentPane_1_1.add(price);

		JButton add = new JButton("Add to Cart");
		add.setFont(new Font("Tahoma", Font.BOLD, 18));
		add.setBounds(547, 370, 154, 46);
		contentPane_1.add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = products.getSelectedRow();
				List<Object> row = new ArrayList<>();
				for(int i = 0; i < products.getColumnCount(); i++){
					row.add(products.getValueAt(selected_row, i));
				}
				DefaultTableModel model= (DefaultTableModel) cart.getModel();
				model.addRow(row.toArray());
			}
		});

		JButton remove = new JButton("Remove from cart");
		remove.setFont(new Font("Tahoma", Font.BOLD, 18));
		remove.setBounds(522, 426, 209, 46);
		contentPane_1.add(remove);
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = cart.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) cart.getModel();
				model.removeRow(selected_row);
			}
		});

		JButton filter = new JButton("Filter");
		filter.setFont(new Font("Tahoma", Font.BOLD, 18));
		filter.setBounds(547, 481, 154, 46);
		contentPane_1.add(filter);
		filter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String title;
				double Rating;
				int Callories, Proteins,Fats,Sodium,Price;
				if (!name.getText().isEmpty())
					 title= name.getText();
				else
					title="";
				if (!rating.getText().isEmpty())
				 	Rating= Double.parseDouble(rating.getText());
				else
					Rating=-1;
				if (!calories.getText().isEmpty())
					Callories= Integer.parseInt(calories.getText());
				else
					Callories=-1;
				if(!proteins.getText().isEmpty())
					Proteins= Integer.parseInt(proteins.getText());
				else
					Proteins=-1;
				if(!fats.getText().isEmpty())
					Fats= Integer.parseInt(fats.getText());
				else
					Fats=-1;
				if(!sodium.getText().isEmpty())
					Sodium= Integer.parseInt(sodium.getText());
				else
					Sodium=-1;
				if(!price.getText().isEmpty())
					Price = Integer.parseInt(price.getText());
				else
					Price=-1;
				BaseProduct produs = new BaseProduct(title, Rating, Callories, Proteins, Fats, Sodium, Price);
				//c.getDeliveryService().setProducts(c.filtrare(produs));
				Set<MenuItem> produsefiltrare=c.filtrare(produs);
				products.setModel(c.tabelProduse(produsefiltrare));
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 442, 605);
		contentPane_1.add(scrollPane);

		products = new JTable();
		scrollPane.setViewportView(products);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(783, 10, 385, 624);
		contentPane_1.add(scrollPane_1);

		cart = new JTable();
		scrollPane_1.setViewportView(cart);

		JButton order = new JButton("Order");
		order.setFont(new Font("Tahoma", Font.BOLD, 18));
		order.setBounds(547, 537, 154, 46);
		contentPane_1.add(order);
		order.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<MenuItem> carucior=new ArrayList<>();
				for(int i=0;i<cart.getRowCount();i++)
				{
					String nume= (String) cart.getValueAt(i,0);
					MenuItem m=c.getDeliveryService().findProduct(nume);
					carucior.add(m);
				}
				int idClient=c.getDeliveryService().getCurrentUser().getId();
				Order o=new Order(idClient, new Date());
				c.getDeliveryService().createOrder(o,carucior);
				System.out.println(c.getDeliveryService().getOrders());
				c.getDeliveryService().notifyObservers(o.toString());
				c.getDeliveryService().getEmployees().get(0).update(c.getDeliveryService(),o.toString());
				FileWriter.bill(o,carucior);
			}
		});

		products.setModel(c.tabelProduse(c.getDeliveryService().getProducts()));

		DefaultTableModel defaultTableModel = new DefaultTableModel();
		Field[] declaredFields = MenuItem.class.getDeclaredFields();
		for (Field f : declaredFields) {
			f.setAccessible(true);
			defaultTableModel.addColumn(f.getName());
		}
		cart.setModel(defaultTableModel);
	}

}
