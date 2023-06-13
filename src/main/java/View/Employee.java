package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Employee extends JFrame implements Observer {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private static DefaultListModel<String> model=new DefaultListModel<>();
	private JList notificari;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
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
	public Employee() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(10, 10, 443, 214);
		contentPane.add(scrollPane);
		notificari=new JList();
		notificari.setModel(model);
		scrollPane.setViewportView(notificari);

		JButton btnclear = new JButton("CLEAR");
		btnclear.setBounds(125, 234, 174, 52);
		contentPane.add(btnclear);
		btnclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<String> model= (DefaultListModel<String>)notificari.getModel();
				if(!model.isEmpty())
					model.removeAllElements();
				}

		});
	}

	@Override
	public void update(Observable o, Object arg) {
		String notif= (String) arg;
		DefaultListModel<String> model= (DefaultListModel<String>) notificari.getModel();
		model.addElement(notif);
	}
}
