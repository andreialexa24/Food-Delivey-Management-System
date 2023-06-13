package View;

import Controller.Controller;
import Model.BaseProduct;
import Model.CompositeProduct;
import Model.FileWriter;
import Model.MenuItem;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Admin extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin(null);
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
	public Admin(Controller c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 848, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 532, 686);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton import_products = new JButton("Import Products");
		import_products.setFont(new Font("Tahoma", Font.BOLD, 18));
		import_products.setBounds(588, 28, 204, 49);
		import_products.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Set<MenuItem> produs = FileWriter.products();
				c.getDeliveryService().setProducts(produs);
				DefaultTableModel model  = c.tabelProduse(produs);
				table.setModel(model);
			}
		});
		contentPane.add(import_products);
		
		JButton create_product = new JButton("Create Product");
		create_product.setFont(new Font("Tahoma", Font.BOLD, 18));
		create_product.setBounds(588, 127, 204, 49);
		create_product.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setCreateProduct(new CreateProduct(c));
				c.getCreateProduct().setVisible(true);
			}
		});
		contentPane.add(create_product);
		
		JButton delete = new JButton("Delete Product");
		delete.setFont(new Font("Tahoma", Font.BOLD, 18));
		delete.setBounds(588, 242, 204, 49);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String title = (String) table.getModel().getValueAt(row,0);
				MenuItem m = c.getDeliveryService().findProduct(title);
				c.getDeliveryService().removeProdus(m);
				table.setModel(c.tabelProduse(c.getDeliveryService().getProducts()));
			}
		});
		contentPane.add(delete);
		
		JButton edit = new JButton("Edit Product");
		edit.setFont(new Font("Tahoma", Font.BOLD, 18));
		edit.setBounds(588, 353, 204, 49);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selected_row = table.getSelectedRow();
				String nume = String.valueOf(table.getValueAt(selected_row, 0));
				System.out.println(nume);
				MenuItem m = c.getDeliveryService().findProduct(nume);
				System.out.println(m);
				if(m instanceof BaseProduct){
					c.setEditProduct(new EditProduct(c, (BaseProduct) m));
					c.getEditProduct().setVisible(true);

				}
				else if(m instanceof CompositeProduct){
					c.setEditMenu(new EditMenu(c, (CompositeProduct) m));
					c.getEditMenu().setVisible(true);

				}

			}
		});
		contentPane.add(edit);
		
		JButton create_menu = new JButton("Create Menu");
		create_menu.setFont(new Font("Tahoma", Font.BOLD, 18));
		create_menu.setBounds(588, 465, 204, 49);
		create_menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.setCreateMenu(new CreateMenu(c));
				c.getCreateMenu().setVisible(true);

			}
		});
		contentPane.add(create_menu);
		
		JButton reports = new JButton("Reports");
		reports.setFont(new Font("Tahoma", Font.BOLD, 18));
		reports.setBounds(588, 572, 204, 49);
		table.setAutoCreateRowSorter(true);
		contentPane.add(reports);
		table.setModel(c.tabelProduse(c.getDeliveryService().getProducts()));
	}

	public JTable getTable() {
		return table;
	}
}
