package RaPizza.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import RaPizza.model.CataloguedPizza;
import RaPizza.model.Order;
import RaPizza.model.Pizza;
import RaPizza.model.User;

public abstract class Controller implements ActionListener {
  Application app;
  public Controller(Application app) { this.app = app; }
  protected static boolean isInteger(String v) {
    for (int i = 0; i < v.length(); i++)
      switch (v.charAt(i)) {
        case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9': break;
        default: return false;
      }
    return true;
  }

  public static void ManageOrderView(Application app, Order order) {
    new FramePopup("Manage order") {
      protected JPanel init() {
        JPanel pane = new JPanel(new GridLayout(12 + (order.driver != null ? 1 : 0) + order.pizzas.length, 1));

        pane.add(new JLabel("Order infos"));
        pane.add(new JLabel("ID: " + order.ID));
        pane.add(new JLabel("Price: " + order.price));
        pane.add(new JLabel("Date: " + order.date));
        pane.add(new JLabel("Client: " + order.client.getPhone()));
        pane.add(new JLabel("Hot sauce: " + order.with_hot_sauce));
        pane.add(new JLabel("State: " + order.state));
        if (order.driver != null)
          pane.add(new JLabel("Driver: " + order.driver.name));
        pane.add(new JLabel("Drinks: " + order.drinks));

        pane.add(new JPanel());
        pane.add(new JSeparator());
        for (Pizza pizza : order.pizzas)
          pane.add(new JLabel("Pizza '" + pizza.pizza.name + "' * " + pizza.count + " [" + pizza.size + "]"));

        pane.add(new JSeparator());

        JLabel label = new JLabel(order.state == Order.OrderState.Choice ? "Do you validate the order ?" : order.state == Order.OrderState.Preparation ? "Would you like to send it ?" : "Is the order received ?");
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        pane.add(label);

        pane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        return pane;
      }
      protected boolean quit(boolean canceled) {
        if (canceled) return true;

        if (order.state == Order.OrderState.Choice) {
          if (!app.pizzeria.validateOrder(order)) return false;
          app.addPreparingOrderRow(order);
        } else if (order.state == Order.OrderState.Preparation) {
          if (!app.pizzeria.orderSend(order)) return false;
          app.removePreparingOrderRow(order.ID);
          app.addSendingOrderRow(order);
        } else {
          if (!app.pizzeria.orderReceive(order)) return false;
          app.removeSendingOrderRow(order.ID);
        }
        return true;
      }
    };
  }
}

class AddClientAction extends Controller {
  public AddClientAction(Application app) { super(app); }
  public void actionPerformed(ActionEvent e) {
    new FramePopup("Add client") {
      JTextField client_name , client_mail, client_phone, client_address;
      JLabel out;
      protected JPanel init() {
        JPanel pane = new JPanel(new GridLayout(12, 1));
        pane.add(new JLabel("Client name"));
        pane.add(client_name = new JTextField());
        pane.add(new JPanel()); // Empty object for space

        pane.add(new JLabel("Client mail"));
        pane.add(client_mail = new JTextField());
        pane.add(new JPanel());

        pane.add(new JLabel("Client phone"));
        pane.add(client_phone = new JTextField());
        pane.add(new JPanel());

        pane.add(new JLabel("Client address"));
        pane.add(client_address = new JTextField());

        pane.add(out = new JLabel(""));
        out.setForeground(Color.RED);
        return pane;
      }
      protected boolean quit(boolean canceled) {
        if (canceled) return true;
        if (client_name.getText().length() < 3)
          out.setText("Invalid name's length");
        else if (!client_mail.getText().contains("@") || client_mail.getText().indexOf(".", client_mail.getText().indexOf("@") + 2) == -1)
          out.setText("Invalid nmail format (string@string.string)");
        else if (client_phone.getText().length() != 10 || !Controller.isInteger(client_phone.getText()))
          out.setText("Invalid phone number");
        else if (client_address.getText().length() < 3)
          out.setText("Invalid address's length");
        else {
          if (app.pizzeria.createUser(client_phone.getText(), client_name.getText(), client_address.getText(), client_mail.getText())) {
            app.addClientRow(app.pizzeria.getClients()[app.pizzeria.getClients().length - 1]);
            return true;
          }
          out.setText("Invalid user");
        }
        return false;
      }
    };
  }
}

class NewOrderAction extends Controller {
  public NewOrderAction(Application app) { super(app); }
  public void actionPerformed(ActionEvent e) {
    new FramePopup("New order") {
      JTextField client_phone, drinks_field;
      JSpinner[] pizzas_count, pizzas_size;
      JCheckBox hot_sauce;
      CataloguedPizza[] catalog;
      JLabel out;
      protected JPanel init() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(new JLabel("client ID (phone)"));
        pane.add(client_phone = new JTextField());
        pane.add(new JPanel()); // Empty object for space

        pane.add(new JLabel("Pizzas"));

        catalog = app.pizzeria.getCatalog();
        JPanel spane = new JPanel(new GridLayout(catalog.length + 1, 3));

        spane.add(new JLabel("Type"));
        spane.add(new JLabel("Count"));
        spane.add(new JLabel("Size"));

        pizzas_count = new JSpinner[catalog.length];
        pizzas_size = new JSpinner[catalog.length];
        for (int i = 0; i < catalog.length; i++) {
          spane.add(new JLabel(catalog[i].name));
          spane.add(pizzas_count[i] = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1)));
          spane.add(pizzas_size[i] = new JSpinner(new SpinnerNumberModel(1, 0, 2, 1)));
        }

        JScrollPane scroller = new JScrollPane(spane);
        scroller.setPreferredSize(new Dimension(500, 250));
        pane.add(scroller);
        pane.add(new JPanel());

        pane.add(new JLabel("Drinks"));
        pane.add(drinks_field = new JTextField());

        pane.add(hot_sauce = new JCheckBox("Hot sauce"));

        pane.add(out = new JLabel(""));
        out.setForeground(Color.RED);
        return pane;
      }
      protected boolean quit(boolean canceled) {
        if (canceled) return true;
        if (client_phone.getText().length() != 10 || !Controller.isInteger(client_phone.getText()))
          out.setText("Invalid phone number");
        else {
          User client = app.pizzeria.getClient(Integer.parseInt(client_phone.getText().charAt(0) == '0' ? client_phone.getText().substring(1, 10) : client_phone.getText()));
          if (client == null) {
            out.setText("Client unknow");
            return false;
          }
          ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
          ArrayList<String> drinks = new ArrayList<String>();
          for (String s : drinks_field.getText().split(","))
            drinks.add(s);
          for (int i = 0; i < catalog.length; i++)
            if ((int)pizzas_count[i].getValue() > 0)
              pizzas.add(new Pizza(catalog[i], (int)pizzas_size[i].getValue(), (int)pizzas_count[i].getValue()));
          if (app.pizzeria.createOrder(client, pizzas, drinks, hot_sauce.isSelected())) {
            Controller.ManageOrderView(app, app.pizzeria.getOrders()[app.pizzeria.getOrders().length - 1]);
            return true;
          }
          out.setText("Invalid Order");
        }
        return false;
      }
    };
  }
}

class SelectOrderAction extends Controller {
  JComboBox<Long> selector;
  public SelectOrderAction(Application app) { super(app); }
  public void actionPerformed(ActionEvent e) {
    new FramePopup("Select order") {
      protected JPanel init() {
        JPanel pane = new JPanel();

        Order[] orders = app.pizzeria.getOrders();
        if (orders.length == 0) return null;
        Long[] IDs = new Long[orders.length];
        for (int i = 0; i < IDs.length; i++)
          IDs[i] = orders[i].ID;

        pane.add(selector = new JComboBox<Long>(IDs));

        return pane;
      }
      protected boolean quit(boolean canceled) {
        if (!canceled)
          ManageOrderView(app, app.pizzeria.getOrder((long)selector.getSelectedItem()));
        return true;
      }
    };
  }
}
