package fr.creator_craft.RaPizza;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Application extends JFrame{
	private static final long serialVersionUID = 1L;
	
	Font font = new Font("Arial", Font.BOLD, 14);

    Color backgroundColor = new Color(225, 225, 225);
    Color textColor = Color.BLACK;
    Color buttonColor = new Color(0, 153, 153);
	
	public static Pizzeria pizzeria = new Pizzeria();

	Application() {
		super(pizzeria.name);
//		pizzeria.loadAll();
		
//		try {
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}

        JTabbedPane onglet = new JTabbedPane();
//        onglet.setOpaque(true);
//        onglet.setUI(new MyTabbedPaneUI());
        onglet.setBackground(backgroundColor);
        onglet.setForeground(textColor);
        onglet.setFont(font);
        
//        ImageIcon icon = new ImageIcon("icon.png", ".");
        onglet.addTab("Overview", null, getGeneralView(), "Current orders"); // Current orders
        onglet.addTab("Clients" , getClientsView());
        onglet.addTab("Catalog", null);
        onglet.addTab("Drivers", null);
        onglet.addTab("Order history", null);
        onglet.setIconAt(0, new ImageIcon());

//		SwingUtilities.updateComponentTreeUI(this); pack();
        add(onglet);
        setSize(720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);	
	}
	
	private JScrollPane addTable(String[] columns) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) /*{
        	private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) { return false; }
        }*/;
//        model.addTableModelListener(new TableModelListener() {
//			@Override
//			public void tableChanged(TableModelEvent e) {
//				System.out.println(e.getColumn() + " " + e.getFirstRow() + " " + e.getLastRow());
//			}
//        });
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
        
		c.weightx = 1; 
        c.weighty = 0.1;
        c.gridy = 0;
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
		
		return panel;
	}
}
