package fr.creator_craft.RaPizza;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Application extends JFrame{
	private static final long serialVersionUID = 1L;
	
	Font font = new Font("Arial", Font.BOLD, 14);

    Color backgroundColor = new Color(225, 225, 225);
    Color textColor = Color.BLACK;
//    Color buttonColor = new Color(0, 153, 153);
    
    DefaultTableModel preparationTableModel, sendingTableModel, clientsTableModel, pizzasTableModel, drinksTableModel, driversTableModel, historyTableModel;
	
	public static Pizzeria pizzeria = new Pizzeria();

	JTabbedPane tabs;
	
	Application(String theme) {
		super(pizzeria.getName());
//		pizzeria.loadAll();
		
		if (theme != null)
			try {
				UIManager.setLookAndFeel(theme);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

        tabs = new JTabbedPane();
//        tabs.setOpaque(true);
//        tabs.setUI(new MyTabbedPaneUI());
        tabs.setBackground(backgroundColor);
        tabs.setForeground(textColor);
        tabs.setFont(font);
        tabs.setFocusable(false);
        
//        ImageIcon icon = new ImageIcon("icon.png", ".");
        tabs.addTab("Overview", null, makeGeneralView(), "Current orders"); // Current orders
        tabs.addTab("Clients" , makeClientsView());
        tabs.addTab("Catalog", makeCatalogsView());
        tabs.addTab("Drivers", makeDriversView());
        tabs.addTab("Order history", makeHistoryView());
        tabs.setIconAt(0, new ImageIcon());
        
//		SwingUtilities.updateComponentTreeUI(this); pack();
        JLabel text = new JLabel("0", SwingConstants.RIGHT);
        text.setSize(100, 20);
        add(text);
        add(tabs);
		
        setSize(720, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				text.setLocation(getSize().width - text.getWidth() - 8, 0);
			}
			@Override public void componentMoved(ComponentEvent e) {}
			@Override public void componentShown(ComponentEvent e) {}
			@Override public void componentHidden(ComponentEvent e) {}
        });
        
        setFocusable(true);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (tabs.getSelectedIndex() == 0 && e.getKeyChar() >= '0' && e.getKeyChar() <= '9')
					text.setText((text.getText().equals("0") ? "" : text.getText()) + e.getKeyChar());
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ESCAPE -> System.exit(0);
					case KeyEvent.VK_LEFT -> tabs.setSelectedIndex((tabs.getSelectedIndex() > 0 ? tabs.getSelectedIndex() : tabs.getTabCount()) - 1);
					case KeyEvent.VK_RIGHT -> tabs.setSelectedIndex(tabs.getSelectedIndex() < tabs.getTabCount() - 1 ? tabs.getSelectedIndex() + 1 : 0);
					case KeyEvent.VK_BACK_SPACE -> {
						if (tabs.getSelectedIndex() == 0)
							if (text.getText().length() == 1)
								text.setText("0");
							else
								text.setText(text.getText().substring(0, text.getText().length() - 1));
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
	}
	
	private DefaultTableModel addTable(JPanel panel, Object contraints, String[] columns, int width, int height) {
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
        	private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) { return false; }
        };
        JTable table = new JTable(model);
        table.setFocusable(false);
        JScrollPane scrollPane = new JScrollPane(table);
        if (width != 0)
        	scrollPane.setPreferredSize(new Dimension(width, height));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        clientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        panel.add(scrollPane, contraints);
        return model;
	}
	
	private Component makeGeneralView() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
//		panel.setFont(font);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.weightx = 0.8;
        c.weighty = 0.1;
        c.gridy = 0;
        c.gridx = 0;
        panel.add(new JLabel("Preparation"), c);
        c.weightx = 1; 
        c.weighty = 0.4;
        c.gridy = 1;
        preparationTableModel = addTable(panel, c, new String[]{"ID", "Pizzas", "Drinks"}, 0, 0);
        
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridy = 2;
        panel.add(new JLabel("Sending"), c);
        c.weightx = 1;
        c.weighty = 0.4;
        c.gridy = 3;
        sendingTableModel = addTable(panel, c, new String[]{"ID", "Destination", "Client"}, 0, 0);
        
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        
		return panel;
	}
	
	private Component makeClientsView() {
		JPanel panel = new JPanel();
		
//		JButton button = new JButton("Text");
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				tabs.setSelectedIndex(0);
//			}
//		});
		
		clientsTableModel = addTable(panel, null, new String[]{"Phone", "Name", "Mail", "Balance"}, 0, 0);
		
		return panel;
	}
	
	private Component makeCatalogsView() {
		JPanel panel = new JPanel();
		pizzasTableModel = addTable(panel, null, new String[]{"Pizza name", "Ingredients", "Base price"}, 0, 0);
		drinksTableModel = addTable(panel, null, new String[]{"Drink name"}, 0, 0);
		return panel;
	}
	private Component makeDriversView() {
		JPanel panel = new JPanel();
		driversTableModel = addTable(panel, null, new String[]{"Name", "Average rate"}, 0, 0);
		return panel;
	}
	private Component makeHistoryView() {
		JPanel panel = new JPanel();
		historyTableModel = addTable(panel, null, new String[]{"Global ID", "Pizzas", "Drinks", "Client"}, 0, 0);
		return panel;
	}
}
