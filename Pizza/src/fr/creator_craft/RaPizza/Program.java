package fr.creator_craft.RaPizza;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Program {
	
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		new Application(); // ..............................
	}

}

class Application extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static Pizzeria pizzeria = new Pizzeria();

	Application() {
		pizzeria.loadAll();
		
		setTitle(pizzeria.name);
		
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
//		SwingUtilities.updateComponentTreeUI(this);
//		pack();
		
		JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton clientsButton = new JButton("Clients");
        JButton catalogButton = new JButton("Catalog");
        JButton ingredientsButton = new JButton("Ingredients");
        
        buttonPanel.add(clientsButton);
        buttonPanel.add(catalogButton);
        buttonPanel.add(ingredientsButton);
        

//        buyButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(Application.this, "Pizza bought successfully!");
//            }
//        });


        JPanel ingredientsPanel = new JPanel(new GridLayout(3, 2));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Ingredients"));

        JCheckBox cheeseCheckbox = new JCheckBox("Cheese");
        JCheckBox pepperoniCheckbox = new JCheckBox("Pepperoni");
        JCheckBox mushroomsCheckbox = new JCheckBox("Mushrooms");

        ingredientsPanel.add(cheeseCheckbox);
        ingredientsPanel.add(pepperoniCheckbox);
        ingredientsPanel.add(mushroomsCheckbox);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField("0", 5);
        

        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.Y_AXIS));
        quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityPanel.add(quantityLabel);
//        quantityField.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantityPanel.add(quantityField);
        
//        quantityLabel.setSize(100, quantityLabel.getHeight());

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(ingredientsPanel, BorderLayout.CENTER);
        mainPanel.add(quantityPanel, BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        // setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
	}
}
