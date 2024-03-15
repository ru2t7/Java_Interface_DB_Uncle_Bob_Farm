package view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import controller.FarmController;
import controller.VisitorController;

import model.AnimalModel;
import model.PackageModel;
import model.ReservationModel;
import model.VisitorModel;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.ParagraphView;


public class VisitorView {

    JFrame frame = new JFrame("Uncle Bob's Farm");
    JMenuBar menuBar = new JMenuBar();
    JMenu accountMenu = new JMenu("Account");
    JMenuItem accountInfoItem = new JMenuItem("Account Info");
    JMenuItem logOutItem = new JMenuItem("Log Out");
    JMenuItem deleteItem = new JMenuItem("Delete Account");

    JLabel labelName = new JLabel("Package Name");
    JLabel labelDescription = new JLabel("Description:");
    JTextArea textAreaDescritpion = new JTextArea(5, 20); // Rows and columns
    JLabel labelDate = new JLabel("Date:");
    JDateChooser dateChooser = new JDateChooser();
    JLabel labelDiscount = new JLabel("Discount:");

    JComboBox comboBox = new JComboBox();

    JLabel labelPrice = new JLabel("Final Price:");
    JTextField textFieldPrice = new JTextField("");

    JButton makeReservationButton = new JButton("Make Reservation");

    JLabel labelTable1 = new JLabel("Packages");
    JTable table1 = new JTable();
    JScrollPane scrollPane1 = new JScrollPane(table1);

    JLabel labelTable2 = new JLabel("Reservations");
    JTable table2 = new JTable();
    JScrollPane scrollPane2 = new JScrollPane(table2);
    VisitorController visitorController;

    public VisitorView() {

        JLabel labelPane = new JLabel();
        labelPane.add(comboBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.getContentPane().setLayout(null);

        Color myGrey =new Color(43,45,48);
        frame.setBackground(myGrey);
        labelTable1.setForeground(Color.WHITE);
        labelDate.setForeground(Color.WHITE);
        labelName.setForeground(Color.WHITE);
        labelDiscount.setForeground(Color.WHITE);
        labelDescription.setForeground(Color.WHITE);
        labelPrice.setForeground(Color.WHITE);
        labelTable2.setForeground(Color.WHITE);


        accountInfoItem.addActionListener(e -> visitorController.accountInfoItemClicked());
        logOutItem.addActionListener(e -> visitorController.logOutItemClicked());
        deleteItem.addActionListener(e -> visitorController.deleteItemClicked());
        makeReservationButton.addActionListener(e -> visitorController.makeReservationButtonClicked());


        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table1.getModel();
                String name = (String) model.getValueAt(table1.getSelectedRow(), 0);
                Integer price = (Integer) model.getValueAt(table1.getSelectedRow(), 1);
                PackageModel packageModel = visitorController.getPackage(name);
                labelName.setText(packageModel.getName());
                textAreaDescritpion.setText(packageModel.getDescription());
                textFieldPrice.setText(String.valueOf(packageModel.getPrice()));

                labelPane.remove(comboBox);
                comboBox = new JComboBox(packageModel.getDiscountOptions());
                comboBox.setBounds(350, 215, 150, 20);
                comboBox.addActionListener(e -> updatePrice());
                labelPane.add(comboBox);

            }
        });

        menuBar.add(accountMenu);
        menuBar.setBounds(0, 0, frame.getWidth(), 30);

        accountMenu.add(accountInfoItem);
        accountMenu.add(logOutItem);
        accountMenu.add(deleteItem);

        labelTable1.setBounds(10, 10, 300, 20);
        scrollPane1.setBounds(10, 35, 300, 200);
        labelTable2.setBounds(10, 240, 300, 20);
        scrollPane2.setBounds(10, 265, 300, 200);
        labelName.setBounds(350, 10, 150, 20);
        labelDescription.setBounds(350, 35, 150, 20);
        textAreaDescritpion.setBounds(350, 60, 150, 100);
        labelDate.setBounds(350, 165, 45, 20);
        dateChooser.setBounds(400, 165, 100, 20);
        labelDiscount.setBounds(350, 190, 150, 20);
        labelPrice.setBounds(350, 240, 70, 20);
        textFieldPrice.setBounds(420, 240, 80, 20);
        makeReservationButton.setBounds(350, 265, 150, 20);

        textAreaDescritpion.setLineWrap(true);
        textAreaDescritpion.setEditable(false);
        textAreaDescritpion.setText("Package description");
        textFieldPrice.setEditable(false);

        labelPane.setBounds(0, 0, 300, 300);
        labelPane.add(labelTable1);
        labelPane.add(scrollPane1);
        labelPane.add(labelTable2);
        labelPane.add(scrollPane2);
        labelPane.add(labelName);
        labelPane.add(labelDescription);
        labelPane.add(textAreaDescritpion);
        labelPane.add(labelDate);
        labelPane.add(dateChooser);
        labelPane.add(labelDiscount);
        labelPane.add(labelPrice);
        labelPane.add(textFieldPrice);
        labelPane.add(makeReservationButton);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(labelPane);

    }

    public String getName() {
        return labelName.getText();
    }

    public String convertMonth(String month) {
        switch (month) {
            case ("Jan"):
                return "01";
            case ("Feb"):
                return "02";
            case ("Mar"):
                return "03";
            case ("Apr"):
                return "04";
            case ("May"):
                return "05";
            case ("Jun"):
                return "06";
            case ("Jul"):
                return "07";
            case ("Aug"):
                return "08";
            case ("Sep"):
                return "09";
            case ("Oct"):
                return "10";
            case ("Nov"):
                return "11";
            default:
                return "12";
        }

    }

    public String convertDateFormat(String date) {

        String[] param = date.split(" ", -2);
        String day = param[2].trim();
        String year = param[5].trim();
        String month = param[1].trim();
        month = convertMonth(month);
        String newDate = year + "-" + month + "-" + day;
        return newDate;
    }

    public String getDate() {
        try {
            return convertDateFormat(dateChooser.getDate().toString());
        } catch (NullPointerException e) {
            showMessage("Choose a Date First", 0);
            return null;
        }
    }

    public String getDiscount() {
        System.out.println(comboBox.getSelectedItem().toString());
        return comboBox.getSelectedItem().toString();
    }

    public String getPrice() {

        return textFieldPrice.getText();
    }

    public void updatePrice() {
        textFieldPrice.setText(String.valueOf(visitorController.getPackage(getName()).getPrice()));
        PackageModel packageModel = new PackageModel();
        int percentage = packageModel.convertOptionToPercentage(getDiscount());
        System.out.println("percentage" + percentage);
        int currentPrice = Integer.parseInt(getPrice());
        System.out.println("currentprice" + currentPrice);
        int newPrice = currentPrice - ((percentage * currentPrice) / 100);
        System.out.println("newprice" + newPrice);
        textFieldPrice.setText(String.valueOf(newPrice));
    }

    public void displayPackagesData(List<PackageModel> packages) {
        String[] coll = {"name", "price"};


        DefaultTableModel model = new DefaultTableModel(coll, 0);
        for (PackageModel package1 : packages) {
            Object[] row = {package1.getName(), package1.getPrice()};
            model.addRow(row);
        }

        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(1, Comparator.comparingInt((Integer price) -> price).reversed());

        table1.setModel(model);
        table1.setRowSorter(sorter);
    }

    public void displayReservationsData(List<ReservationModel> reservations) {
        String[] coll = {"date", "package", "final price"};


        DefaultTableModel model = new DefaultTableModel(coll, 0);
        for (ReservationModel reservation : reservations) {
            Object[] row = {reservation.getDate(), reservation.getPackageName(), reservation.getFinalPrice()};
            model.addRow(row);
        }

        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(2, Comparator.comparingInt((Integer price) -> price).reversed());

        table2.setModel(model);
        table2.setRowSorter(sorter);
    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public VisitorController getVisitorController() {
        return visitorController;
    }

    public void setVisitorController(VisitorController visitorController) {

        this.visitorController = visitorController;
    }


}
