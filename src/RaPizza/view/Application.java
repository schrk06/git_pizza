package RaPizza.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import RaPizza.model.CataloguedPizza;
import RaPizza.model.Ingredient;
import RaPizza.model.Order;
import RaPizza.model.Pizza;
import RaPizza.model.Pizzeria;
import RaPizza.model.User;

// @SuppressWarnings("serial")
public class Application extends JFrame{
  // Color buttonColor = new Color(0, 153, 153);

	DefaultTableModel preparationTableModel, sendingTableModel, clientsTableModel, pizzasTableModel, ingredientsTableModel, driversTableModel, historyTableModel;

	public Pizzeria pizzeria = new Pizzeria();

	JTabbedPane tabs;

	Application(String theme) {
		super();
    this.setTitle(pizzeria.getName());
		pizzeria.loadAll();


		if (theme != null)
			try {
				UIManager.setLookAndFeel(theme);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}

		tabs = new JTabbedPane();
		tabs.setBackground(new Color(225, 225, 225));
		tabs.setForeground(Color.BLACK);
		tabs.setFont(new Font("Arial", Font.BOLD, 14));

    // ImageIcon icon = new ImageIcon("icon.png", ".");
		tabs.addTab("Overview", null, makeGeneralView(), "Current orders"); // Current orders
		tabs.addTab("Clients" , makeClientsView());
		tabs.addTab("Catalog", makeCatalogsView());
		tabs.addTab("Drivers", makeDriversView());
		tabs.addTab("Order history", makeHistoryView());
		tabs.setIconAt(0, new ImageIcon());

    add(tabs);

    // SwingUtilities.updateComponentTreeUI(this); pack();

		setSize(720, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

    addWindowListener(new WindowListener() {
      public void windowOpened(WindowEvent e) {}
      public void windowClosing(WindowEvent e) {
        pizzeria.saveAll();
      }
      public void windowDeiconified(WindowEvent e) {}
      public void windowActivated(WindowEvent e) {}
      public void windowDeactivated(WindowEvent e) {}
      public void windowClosed(WindowEvent e) {}
      public void windowIconified(WindowEvent e) {}
    });

    ArrayList<Pizza> pizzas_test = new ArrayList<Pizza>();
    pizzas_test.add(new Pizza(pizzeria.getCatalog()[0], 0, 3));
    System.out.println(pizzeria.createOrder(pizzeria.getClients()[0], pizzas_test, new ArrayList<>(), false));;
    System.out.println(pizzeria.validateOrder(pizzeria.getOrders()[pizzeria.getOrders().length - 1]));;
    addPreparingOrderRow(pizzeria.getOrders()[0]);
	}

	private DefaultTableModel addTable(JPanel panel, Object contraints, String[] columns, int width, int height) {
		DefaultTableModel model = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int rowIndex, int colIndex) { return false; }
		};
		JTable table = new JTable(model);
		table.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		if (width != 0)
			scrollPane.setPreferredSize(new Dimension(width, height));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    // clientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		panel.add(scrollPane, contraints);
		return model;
	}

  public void addClientRow(User client) {
    clientsTableModel.addRow(new String[] { ""+client.getPhone(), client.getName(), client.getMail(), ""+client.getBalance() });
  }
  public void removeClientRow(int phone) {} // TODO
  public void addPreparingOrderRow(Order order) {
      String pizzas_str = "";
      for (Pizza pizza : order.pizzas)
        pizzas_str += pizza.count + "*" + pizza.pizza.name + "[" + pizza.size + "]; ";
      preparationTableModel.addRow(new String[] { ""+order.ID, pizzas_str, String.join(", ", order.drinks), ""+order.with_hot_sauce });
  }
  public void removePreparingOrderRow(long id) {
    for (int i = 0; i < preparationTableModel.getRowCount(); i++)
      if (preparationTableModel.getValueAt(i, 0).equals(""+id)) {
        preparationTableModel.removeRow(i);
        return;
      }
  }
  public void addSendingOrderRow(Order order) {
    sendingTableModel.addRow(new String[] { ""+order.ID, order.client.getAddress(), ""+order.client.getPhone(), order.driver.name });
  }
  public void removeSendingOrderRow(long id) {
    for (int i = 0; i < sendingTableModel.getRowCount(); i++)
      if (sendingTableModel.getValueAt(i, 0).equals(""+id)) {
        sendingTableModel.removeRow(i);
        return;
      }
  }

  public void addPizzaRow(CataloguedPizza pizza) {
    pizzasTableModel.addRow(new String[] { pizza.name, "", ""+pizza.base_price, ""+pizza.id });
  }
  public void addIngredientRow(Ingredient ing) {
    ingredientsTableModel.addRow(new String[] { ing.name, ""+ing.id });
  }


	private Component makeGeneralView() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
  // panel.setFont(font);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

    JPanel button_panel = new JPanel();

    JButton button_add, button_manage;
    button_panel.add(button_add = new JButton("New order"));
    button_panel.add(button_manage = new JButton("Manage order"));
    button_add.addActionListener(new NewOrderAction(this));
    button_manage.addActionListener(new SelectOrderAction(this));

		c.gridx = 0;
    c.weightx = 1; c.weighty = 0.05; c.gridy = 0;
		panel.add(button_panel, c);

		c.weightx = 1; c.weighty = 0.05; c.gridy = 1;
		panel.add(new JLabel("Preparation"), c);

		c.weightx = 1; c.weighty = 0.4; c.gridy = 2;
		preparationTableModel = addTable(panel, c, new String[]{"ID", "Pizzas", "Drinks", "Hot sauce"}, 0, 0);

		c.weightx = 1; c.weighty = 0.1; c.gridy = 3;
		panel.add(new JLabel("Sending"), c);

		c.weightx = 1; c.weighty = 0.4; c.gridy = 4;
		sendingTableModel = addTable(panel, c, new String[]{"ID", "Destination", "Client", "Driver"}, 0, 0);

		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16)); // Padding
		return panel;
	}

	private Component makeClientsView() {
		JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel p = new JPanel();
    JButton button = new JButton("Add client");
    button.addActionListener(new AddClientAction(this));
    p.add(button);
		panel.add(p);

		clientsTableModel = addTable(panel, null, new String[]{"Phone", "Name", "Mail", "Balance"}, 0, 0);
		for (User client : pizzeria.getClients())
			clientsTableModel.addRow(new String[]{""+client.getPhone(), client.getName(), client.getMail(), ""+client.getBalance()});

		panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		return panel;
	}

	private Component makeCatalogsView() {
		JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel buttons_pane = new JPanel();

    JButton button_pizza, button_drink, button_ingredient;
    buttons_pane.add(button_pizza = new JButton("Add pizza"));
    buttons_pane.add(button_drink = new JButton("Add drink"));
    buttons_pane.add(button_ingredient = new JButton("Add Ingredient"));
    panel.add(buttons_pane);

		pizzasTableModel = addTable(panel, null, new String[]{"Pizza name", "Ingredients", "Base price", "ID"}, 0, 0);
    ingredientsTableModel = addTable(panel, null, new String[]{"Ingredient name", "ID"}, 0, 0);

    for (CataloguedPizza pizza : pizzeria.getCatalog())
      addPizzaRow(pizza);
    for (Ingredient ing : pizzeria.getIngredients())
      addIngredientRow(ing);

    panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		return panel;
	}
	private Component makeDriversView() {
		JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JPanel button_pane = new JPanel();
    JButton button = new JButton("Add driver");
    button_pane.add(button);
    panel.add(button_pane);

		driversTableModel = addTable(panel, null, new String[]{"Name", "Average rate"}, 0, 0);

    panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		return panel;
	}
	private Component makeHistoryView() {
		JPanel panel = new JPanel(new GridLayout());
		historyTableModel = addTable(panel, null, new String[]{"ID", "Pizzas count * ID", "Drinks", "Client ID", "Driver ID", "Price", "Date"}, 0, 0);

    panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		return panel;
	}
}
