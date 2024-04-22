package fr.creator_craft.RaPizza;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Application extends JFrame{
	private static final long serialVersionUID = 1L;
	
	Font font = new Font("Arial", Font.BOLD, 14);

    Color backgroundColor = new Color(225, 225, 225);
    Color textColor = Color.BLACK;
    Color buttonColor = new Color(0, 153, 153);
	
	public static Pizzeria pizzeria = new Pizzeria();

	JTabbedPane tabs;
	
	Application() {
		super(pizzeria.name);
//		pizzeria.loadAll();
		
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}

        tabs = new JTabbedPane();
//        tabs.setOpaque(true);
//        tabs.setUI(new MyTabbedPaneUI());
        tabs.setBackground(backgroundColor);
        tabs.setForeground(textColor);
        tabs.setFont(font);
        
//        ImageIcon icon = new ImageIcon("icon.png", ".");
        tabs.addTab("Overview", null, getGeneralView(), "Current orders"); // Current orders
        tabs.addTab("Clients" , getClientsView());
        tabs.addTab("Catalog", null);
        tabs.addTab("Drivers", null);
        tabs.addTab("Order history", null);
        tabs.setIconAt(0, new ImageIcon());
        
//		SwingUtilities.updateComponentTreeUI(this); pack();
        add(tabs);
        setSize(720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	private JScrollPane addTable(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
        	private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) { return false; }
        };
        model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				System.out.println(e.getColumn() + " " + e.getFirstRow() + " " + e.getLastRow());
				
			}
			
        });
//        for (int i = 0; i < 50; i++)
        model.addRow(new String[] {"MM", "NN", "OO"});
        JScrollPane scrollPane = new JScrollPane(new JTable(model));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        clientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        return scrollPane;
	}
	
	private Component getGeneralView() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
//		panel.setFont(font);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
        
//		gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.WEST;
//        gbc.insets = new Insets(20, 20, 20, 20);
//        gbc.gridwidth = 1;
//        gbc.gridheight= 4;
        
		c.weightx = 0.2; 
        c.weighty = 0.1;
        c.gridy = 0;
		c.gridx = 1;
		panel.add(new JTextField(), c);
		
		c.weightx = 0.8;
        c.weighty = 0.1;
        c.gridy = 0;
        c.gridx = 0;
        panel.add(new JLabel("Preparation"), c);
        c.weightx = 1; 
        c.weighty = 0.4;
        c.gridy = 1;
        panel.add(addTable(new String[]{"Number", "B", "C"}), c);
        
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridy = 2;
        panel.add(new JLabel("Sending"), c);
        c.weightx = 1;
        c.weighty = 0.4;
        c.gridy = 3;
        panel.add(addTable(new String[]{"Number", "B", "C"}), c);
        
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        
//        panel.add(reservationTitle, gbc);
//        
//        JScrollPane reservationScrollPane = new JScrollPane(reservationTable);
//        reservationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        reservationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        reservationScrollPane.setPreferredSize(new Dimension(600, 300));
//        panel.add(reservationScrollPane, gbc);
//        
//        panel.add(sejourTitle);
//        JScrollPane factureScrollPane = new JScrollPane(sejourTable);
//        factureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        factureScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        factureScrollPane.setPreferredSize(new Dimension(600, 300));
//        panel.add(factureScrollPane);
		
		return panel;
	}
	
	private Component getClientsView() {
		JPanel panel = new JPanel();
		
		JButton button = new JButton("Text");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tabs.setSelectedIndex(0);
			}
		});
		
		panel.add(button);
		
		return panel;
	}
}
