package RaPizza.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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

        pane.add(out = new JLabel(".."));
        out.setForeground(Color.RED);
        return pane;
      }
      protected boolean quit(boolean canceled) {
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
            app.updateClientTable();
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
      JTextField client_phone, drinks;
      JSpinner pizzas;
      JLabel out;
      protected JPanel init() {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        pane.add(new JLabel("client ID (phone)"));
        pane.add(client_phone = new JTextField());
        pane.add(new JPanel()); // Empty object for space

        pane.add(new JLabel("Pizzas"));

        JPanel spane = new JPanel(new GridLayout(7, 2));

        spane.add(new JLabel("Pizza A"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza B"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza C"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza D"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza E"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza F"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));
        spane.add(new JLabel("Pizza G"));
        spane.add(pizzas = new JSpinner(new SpinnerNumberModel()));

        JScrollPane scroller = new JScrollPane(spane);
        scroller.setPreferredSize(new Dimension(120, 200));
        pane.add(scroller);
        pane.add(new JPanel());

        pane.add(new JLabel("Drinks"));
        pane.add(drinks = new JTextField());

        pane.add(out = new JLabel(".."));
        out.setForeground(Color.RED);
        return pane;
      }
      protected boolean quit(boolean canceled) {
        System.out.println((int)pizzas.getValue() + 1);
        if (client_phone.getText().length() != 10 || !Controller.isInteger(client_phone.getText()))
          out.setText("Invalid phone number");
        else {
          User client = app.pizzeria.getClient(Integer.parseInt(client_phone.getText().charAt(0) == '0' ? client_phone.getText().substring(1, 10) : client_phone.getText()));
          if (client == null) {
            out.setText("Client unknow");
            return false;
          }
          if (app.pizzeria.createOrder(client, null, null)) {
            app.updateClientTable();
            return true;
          }
          out.setText("Invalid Order");
        }
        return false;
      }
    };
  }
}
