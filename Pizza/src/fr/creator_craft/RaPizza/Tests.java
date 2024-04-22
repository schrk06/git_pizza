package fr.creator_craft.RaPizza;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Tests {
	public static void init() {
		JFrame frame = new JFrame("Hotel");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Font font = new Font("Arial", Font.PLAIN, 16);

        Color backgroundColor = new Color(225, 225, 225);
        Color textColor = Color.BLACK;
        Color buttonColor = new Color(0, 153, 153);

        JTabbedPane onglet = new JTabbedPane();
        onglet.setBackground(backgroundColor);
        
        JLabel clientTitle = new JLabel("Talbeau des clients : ");
        JLabel reservationTitle = new JLabel("Tableau des reservations: ");
        JLabel sejourTitle = new JLabel("Tableau des sejours");
        
        String[] clientColumns = {"Nom", "Prenom", "Date de naissance", "Adresse Mail", "Numero de telephone"};
        DefaultTableModel clientModel = new DefaultTableModel(clientColumns,0) {
        	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;}
        };
        JTable clientTable = new JTable(clientModel);
        
        String[] reservationColumns = {"Type d'habitacle", "Numéro de chambres", "Numero d'etage", "Date debut", "Date fin"};
        DefaultTableModel reservationModel = new DefaultTableModel(reservationColumns,0) {
        	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;}
        };
        JTable reservationTable = new JTable(reservationModel);
        
        String[] sejourColumns = {"Type d'habitacle","numero de Chambre","Numero d'Etage", "Date debut", "Date Fin"};
        DefaultTableModel sejourModel = new DefaultTableModel(sejourColumns,0) {
        	public boolean isCellEditable(int rowIndex, int colIndex) {
        		return false;}
        };
        JTable sejourTable = new JTable(sejourModel);
        JPanel p1 = new JPanel();
        p1.setBackground(backgroundColor);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        p1.add(clientTitle,gbc);
        
        JScrollPane clientScrollPane = new JScrollPane(clientTable);
        clientScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        clientScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        clientScrollPane.setPreferredSize(new Dimension(600, 300));
        p1.add(clientScrollPane,gbc);
        
        p1.add(reservationTitle,gbc);
        
        JScrollPane reservationScrollPane = new JScrollPane(reservationTable);
        reservationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        reservationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        reservationScrollPane.setPreferredSize(new Dimension(600, 300));
        p1.add(reservationScrollPane,gbc);
        
        p1.add(sejourTitle);
        JScrollPane factureScrollPane = new JScrollPane(sejourTable);
        factureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        factureScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        factureScrollPane.setPreferredSize(new Dimension(600, 300));
        p1.add(factureScrollPane);
        
        JPanel p2 = new JPanel();
        p2.setBackground(backgroundColor);
        p2.setLayout(new GridBagLayout());

        JPanel p3 = new JPanel();
        p3.setBackground(backgroundColor);
        p3.setLayout(new GridBagLayout());

        onglet.addTab("Accueil",p1);
        onglet.addTab("Client",p2);
        onglet.addTab("Reservation", p3);
        frame.add(onglet);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 20, 20, 20);
        
        JLabel label1 = new JLabel("Nom");
        label1.setFont(font);
        label1.setForeground(textColor);
        p2.add(label1, gbc);

        gbc.gridx++;
        JTextField text1 = new JTextField(20);
        text1.setFont(font);
        p2.add(text1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label2 = new JLabel("Prénom");
        label2.setFont(font);
        label2.setForeground(textColor);
        p2.add(label2, gbc);

        gbc.gridx++;
        JTextField text2 = new JTextField(20);
        text2.setFont(font);
        p2.add(text2, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label3 = new JLabel("Date de naissance (JJ/MM/AAAA) ");
        label3.setFont(font);
        label3.setForeground(textColor);
        p2.add(label3, gbc);

        gbc.gridx++;
        JFormattedTextField text3 = new JFormattedTextField(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT));
        text3.setColumns(20);
        text3.setFont(font);
        p2.add(text3, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label4 = new JLabel("Adresse mail");
        label4.setFont(font);
        label4.setForeground(textColor);
        p2.add(label4, gbc);

        gbc.gridx++;
        JTextField text4 = new JTextField(20);
        text4.setFont(font);
        p2.add(text4, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label5 = new JLabel("Numéro de téléphone");
        label5.setFont(font);
        label5.setForeground(textColor);
        p2.add(label5, gbc);

        gbc.gridx++;
        JTextField text5 = new JTextField(20);
        text5.setFont(font);
        p2.add(text5, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton annuler = new JButton("Annuler");
        annuler.setFont(font);
        annuler.setBackground(buttonColor);
        annuler.setForeground(textColor);
        p2.add(annuler, gbc);

        gbc.gridx++;
        JButton ajouterClient = new JButton("Ajouter Client");
        ajouterClient.setFont(font);
        ajouterClient.setBackground(buttonColor);
        ajouterClient.setForeground(textColor);
        p2.add(ajouterClient,gbc);
        
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel label6 = new JLabel("Type d'habitacle");
        label6.setFont(font);
        label6.setForeground(textColor);
        p3.add(label6, gbc);

        gbc.gridx++;
        JComboBox<String> comboBox1 = new JComboBox<String>();
        comboBox1.setFont(font);
        comboBox1.addItem("Chambre simple");
        comboBox1.addItem("Chambre double");
        comboBox1.addItem("Suite");
        comboBox1.addItem("Présidentielle");
        p3.add(comboBox1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label7 = new JLabel("Numéro de chambres");
        label7.setFont(font);
        label7.setForeground(textColor);
        p3.add(label7, gbc);

        gbc.gridx++;
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 499, 1));
        spinner1.setFont(font);
        p3.add(spinner1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label8 = new JLabel("Numero d'etage");
        label8.setFont(font);
        label8.setForeground(textColor);
        p3.add(label8, gbc);

        gbc.gridx++;
        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
        spinner2.setFont(font);
        p3.add(spinner2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label9 = new JLabel("Date debut (JJ/MM/AAAA)");
        label9.setFont(font);
        label9.setForeground(textColor);
        p3.add(label9,gbc);
        
        gbc.gridx++;
        JFormattedTextField text6 = new JFormattedTextField(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT));
        text6.setColumns(20);
        text6.setFont(font);
        p3.add(text6, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel label10 = new JLabel("Date Fin (JJ/MM/AAAA)");
        label10.setFont(font);
        label10.setForeground(textColor);
        p3.add(label10,gbc);
        
        gbc.gridx++;
        JFormattedTextField text7 = new JFormattedTextField(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT));
        text7.setColumns(20);
        text7.setFont(font);
        p3.add(text7, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton annuler2 = new JButton("Annuler");
        annuler2.setFont(font);
        annuler2.setBackground(buttonColor);
        annuler2.setForeground(textColor);
        p3.add(annuler2, gbc);

        gbc.gridx++;
        JButton reserver = new JButton("Ajouter Réservation");
        reserver.setFont(font);
        reserver.setBackground(buttonColor);
        reserver.setForeground(textColor);
        p3.add(reserver,gbc);
        
        ajouterClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nom = text1.getText();
                String prenom = text2.getText();
                String dateNaissance = text3.getText();
                String adresseMail = text4.getText();
                String numTelephone = text5.getText();
                Object[] clientData = {nom, prenom, dateNaissance, adresseMail, numTelephone};
                DefaultTableModel clientModel = (DefaultTableModel) clientTable.getModel();
                clientModel.addRow(clientData);
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                JOptionPane.showMessageDialog(p2, "Le client a été ajouté avec succès !");
            }
        });
        
        reserver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String type = comboBox1.getSelectedItem().toString();
        		int numChambre = (int) spinner1.getValue();
        		int numEtage = (int) spinner2.getValue();
        		String dateDebut = text6.getText();
        		String dateFin = text7.getText();
        		Object[] reservationData = {type,numChambre, numEtage, dateDebut, dateFin};
        		DefaultTableModel reservationModel = (DefaultTableModel) reservationTable.getModel();
        		reservationModel.addRow(reservationData);
        		comboBox1.setSelectedIndex(0);
        		spinner1.setValue(1);
        		spinner2.setValue(1);
        		text6.setText("");
        		text7.setText("");
        		JOptionPane.showMessageDialog(p3, "La reservation a été ajouté avec succès !");
        	}
        });
//Pour valider la reservation
        reservationTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    //Creation du pop up
                    JPanel popup1 = new JPanel();
                    JLabel label11 = new JLabel("Voulez-vous valider la reservation ? :");
                    popup1.add(label11);
                    
                    int option = JOptionPane.showOptionDialog(null,popup1,"Validation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Valider","Annuler"},"Valider");
                    
                    //si valider est clique
                    if (option == JOptionPane.OK_OPTION) {
                    	
                    	String[] reservation = new String[reservationModel.getColumnCount()+1];
                    	for (int i = 0; i< reservationModel.getColumnCount();i++) {
                    		reservation[i] = reservationModel.getValueAt(row, i).toString();	
                    	}
                    	sejourModel.addRow(reservation);
                    	
                    	reservationModel.removeRow(row);
                    }
                    else if (option == JOptionPane.CANCEL_OPTION) {
                    	reservationModel.removeRow(row);
                    }
                }
            }
        });
        sejourTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    //Creation du pop up
                    JPanel popup2 = new JPanel();
                    JLabel label12 = new JLabel("Voulez-vous rajouter extra ? :");
                    JTextField textField = new JTextField(10);
                    popup2.add(label12);
                    popup2.add(textField);
                    
                    int option = JOptionPane.showOptionDialog(null,popup2,"Ajouter extra", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"Valider","Annuler"},"Valider");
                    
                    //si valider est clique
                    if (option == JOptionPane.OK_OPTION) {
                    	String extra = textField.getText();
                    	sejourModel.removeRow(row);
                    }
                }
            }
        });

        ajouterClient.addActionListener(e-> {
        	p3.setVisible(true);
        });
        
        p3.setVisible(false);
        frame.setVisible(true);
	}
}

class Application__ extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static Pizzeria pizzeria = new Pizzeria();

	Application__() {
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