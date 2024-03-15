package view;

import controller.FarmController;
import model.AnimalModel;
import model.FarmModel;
import model.AnimalType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FarmView {
    JFrame frame = new JFrame("Farm");

    //-------------------------------------------------
    // SEARCH
    //-------------------------------------------------
    JMenuBar menuBar = new JMenuBar();
    JLabel labelAnimalType = new JLabel("Show All: ");
    JMenu animalType = new JMenu("animals");
    JMenuItem animals = new JMenuItem("animals");
    JMenuItem sheep = new JMenuItem("sheep");
    JMenuItem chickens = new JMenuItem("chickens");
    JMenuItem geese = new JMenuItem("geese");
    JMenuItem pigs = new JMenuItem("pigs");
    JMenuItem goats = new JMenuItem("goats");
    JMenuItem horses = new JMenuItem("horses");
    JMenuItem ducks = new JMenuItem("ducks");
    JMenuItem ponies = new JMenuItem("ponies");
    JMenuItem cows = new JMenuItem("cows");

    JLabel labelSearchText = new JLabel("Find: ");
    JTextField textFieldSearchText = new JTextField();

    JButton searchButton = new JButton("Search");

    //-------------------------------------------------
    // ADD
    //-------------------------------------------------

    JLabel labelAddName = new JLabel("Name: ");
    JTextField textFieldAddName = new JTextField();

    JButton popupButton = new JButton("Animal type:");
    JLabel labelAddAnimalType = new JLabel("      ");
    JPopupMenu popupFieldAddAnimalType = new JPopupMenu("Animal type");
    JMenuItem sheepItem = new JMenuItem("sheep");
    JMenuItem chickenItem = new JMenuItem("chicken");
    JMenuItem gooseItem = new JMenuItem("goose");
    JMenuItem pigItem = new JMenuItem("pig");
    JMenuItem goatItem = new JMenuItem("goat");
    JMenuItem horseItem = new JMenuItem("horse");
    JMenuItem duckItem = new JMenuItem("duck");
    JMenuItem ponyItem = new JMenuItem("pony");
    JMenuItem cowItem = new JMenuItem("cow");
    JLabel labelAddAge = new JLabel("Age:");
    JTextField textFieldAddAge = new JTextField();

    JButton addButton = new JButton("Add");

    //-------------------------------------------------
    // DELETE
    //-------------------------------------------------
    JLabel labelDeleteName = new JLabel("Name: ");
    JTextField textFieldDeleteName = new JTextField();

    JButton deleteButton = new JButton("Delete");

    //-------------------------------------------------
    // UPDATE
    //-------------------------------------------------
    JLabel labelId = new JLabel("Id: ");
    JLabel labelId1 = new JLabel();

    JLabel labelUpdateName = new JLabel("Name: ");
    JTextField textFieldUpdateName = new JTextField();

    JLabel labelUpdateAnimalType = new JLabel("Type: ");
    JLabel labelUpdateAnimalType1 = new JLabel();

    JLabel labelUpdateAge = new JLabel("Age: ");
    JTextField textFieldUpdateAge = new JTextField();

    JLabel labelPen = new JLabel("Pen: ");
    JTextField textFieldPen = new JTextField();

    JButton updateButton = new JButton("Update");

    JButton goBackButton = new JButton("Go Back");
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);


    FarmController farmController;

    public FarmView() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 600);
        frame.getContentPane().setLayout(null);

        Color myGrey =new Color(43,45,48);
        frame.setBackground(myGrey);
        labelSearchText.setForeground(Color.WHITE);
        labelAddAge.setForeground(Color.WHITE);
        labelUpdateAge.setForeground(Color.WHITE);
        labelId.setForeground(Color.WHITE);
        labelUpdateName.setForeground(Color.WHITE);
        labelAddAnimalType.setForeground(Color.WHITE);
        labelDeleteName.setForeground(Color.WHITE);
        labelId1.setForeground(Color.WHITE);
        labelUpdateAnimalType.setForeground(Color.WHITE);
        labelUpdateAnimalType1.setForeground(Color.WHITE);
        labelAddName.setForeground(Color.WHITE);


        //-------------------------------------------------
        // SEARCH
        //-------------------------------------------------
        animalType.add(animals);
        animalType.add(sheep);
        animalType.add(chickens);
        animalType.add(geese);
        animalType.add(pigs);
        animalType.add(goats);
        animalType.add(horses);
        animalType.add(ducks);
        animalType.add(ponies);
        animalType.add(cows);

        animals.addActionListener(e -> animalType.setText("animals"));
        sheep.addActionListener(e -> animalType.setText("sheep"));
        chickens.addActionListener(e -> animalType.setText("chickens"));
        geese.addActionListener(e -> animalType.setText("geese"));
        pigs.addActionListener(e -> animalType.setText("pigs"));
        goats.addActionListener(e -> animalType.setText("goats"));
        horses.addActionListener(e -> animalType.setText("horses"));
        ducks.addActionListener(e -> animalType.setText("ducks"));
        ponies.addActionListener(e -> animalType.setText("ponies"));
        cows.addActionListener(e -> animalType.setText("cows"));

        animals.addActionListener(e -> farmController.animalButtonClicked(AnimalType.ANIMAL));
        sheep.addActionListener(e -> farmController.animalButtonClicked(AnimalType.SHEEP));
        chickens.addActionListener(e -> farmController.animalButtonClicked(AnimalType.CHICKEN));
        geese.addActionListener(e -> farmController.animalButtonClicked(AnimalType.GOOSE));
        pigs.addActionListener(e -> farmController.animalButtonClicked(AnimalType.PIG));
        goats.addActionListener(e -> farmController.animalButtonClicked(AnimalType.GOAT));
        horses.addActionListener(e -> farmController.animalButtonClicked(AnimalType.HORSE));
        ducks.addActionListener(e -> farmController.animalButtonClicked(AnimalType.DUCK));
        ponies.addActionListener(e -> farmController.animalButtonClicked(AnimalType.PONY));
        cows.addActionListener(e -> farmController.animalButtonClicked(AnimalType.COW));

        searchButton.addActionListener(e -> farmController.searchButtonClicked());

        //-------------------------------------------------
        // ADD
        //-------------------------------------------------

        popupFieldAddAnimalType.add(sheepItem);
        popupFieldAddAnimalType.add(chickenItem);
        popupFieldAddAnimalType.add(gooseItem);
        popupFieldAddAnimalType.add(pigItem);
        popupFieldAddAnimalType.add(goatItem);
        popupFieldAddAnimalType.add(horseItem);
        popupFieldAddAnimalType.add(duckItem);
        popupFieldAddAnimalType.add(ponyItem);
        popupFieldAddAnimalType.add(cowItem);


        addButton.addActionListener(e -> farmController.addButtonClicked());

        popupButton.addActionListener(e -> {
            int x = popupButton.getLocation().x;
            int y = popupButton.getLocation().y + popupButton.getHeight();
            popupFieldAddAnimalType.show(frame.getContentPane(), x, y);
        });

        sheepItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("sheep");
            }
        });
        chickenItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("chicken");
            }
        });
        gooseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("goose");
            }
        });
        pigItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("pig");
            }
        });
        goatItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("goat");
            }
        });
        horseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("horse");
            }
        });
        duckItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("duck");
            }
        });
        ponyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("pony");
            }
        });

        cowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelAddAnimalType.setText("cow");
            }
        });

        //-------------------------------------------------
        // DELETE
        //-------------------------------------------------

        deleteButton.addActionListener(e -> farmController.deleteButtonClicked());

        //-------------------------------------------------
        // UPDATE
        //-------------------------------------------------
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Integer id = (Integer) model.getValueAt(table.getSelectedRow(), 0);
                String name = (String) model.getValueAt(table.getSelectedRow(), 1);
                String type = (String) model.getValueAt(table.getSelectedRow(), 2);
                Integer age = (Integer) model.getValueAt(table.getSelectedRow(), 3);
                Integer pen = (Integer) model.getValueAt(table.getSelectedRow(), 4);
                labelId1.setText(String.valueOf(id));
                textFieldUpdateName.setText(name);
                labelUpdateAnimalType1.setText(type);
                textFieldUpdateAge.setText(String.valueOf(age));
                textFieldPen.setText(String.valueOf(pen));
            }
        });

        updateButton.addActionListener(e -> farmController.updateButtonClicked());

        goBackButton.addActionListener(e -> farmController.goBackButtonClicked());

        labelAnimalType.setBounds(320, 60, 100, 20);
        animalType.setBounds(420, 60, 100, 20);

        labelSearchText.setBounds(320, 10, 100, 20);
        textFieldSearchText.setBounds(370, 10, 100, 20);
        searchButton.setBounds(370, 35, 100, 20);

        labelAddName.setBounds(320, 90, 100, 20);
        textFieldAddName.setBounds(370, 90, 100, 20);
        popupButton.setBounds(320, 115, 120, 20);
        labelAddAnimalType.setBounds(445, 115, 100, 20);
        labelAddAnimalType.setBackground(Color.WHITE);
        labelAddAge.setBounds(320, 140, 100, 20);
        textFieldAddAge.setBounds(370, 140, 100, 20);
        addButton.setBounds(370, 165, 100, 20);

        labelDeleteName.setBounds(320, 210, 100, 20);
        textFieldDeleteName.setBounds(370, 210, 100, 20);
        deleteButton.setBounds(370, 235, 100, 20);

        labelId.setBounds(320, 280, 100, 20);
        labelId1.setBounds(370, 280, 100, 20);
        labelId1.setBackground(Color.WHITE);
        labelUpdateName.setBounds(320, 305, 100, 20);
        textFieldUpdateName.setBounds(370, 305, 100, 20);
        labelUpdateAnimalType.setBounds(320, 330, 100, 20);
        labelUpdateAnimalType1.setBounds(370, 330, 100, 20);
        labelUpdateAnimalType1.setBackground(Color.WHITE);
        labelUpdateAge.setBounds(320, 355, 100, 20);
        textFieldUpdateAge.setBounds(370, 355, 100, 20);
        labelPen.setBounds(320, 380, 100, 20);
        textFieldPen.setBounds(370, 380, 100, 20);
        updateButton.setBounds(370, 405, 100, 20);

        goBackButton.setBounds(110, 355, 100, 20);

        scrollPane.setBounds(10, 10, 300, 300);

        JLabel labelPane = new JLabel();
        labelPane.setBounds(0, 0, 300, 300);
        labelPane.add(scrollPane);
        labelPane.add(labelSearchText);
        labelPane.add(textFieldSearchText);
        labelPane.add(searchButton);
        labelPane.add(labelAddName);
        labelPane.add(textFieldAddName);
        labelPane.add(popupButton);
        labelPane.add(labelAddAnimalType);
        labelPane.add(labelAddAge);
        labelPane.add(textFieldAddAge);
        labelPane.add(addButton);
        labelPane.add(labelDeleteName);
        labelPane.add(textFieldDeleteName);
        labelPane.add(deleteButton);
        labelPane.add(labelId);
        labelPane.add(labelId1);
        labelPane.add(labelUpdateName);
        labelPane.add(textFieldUpdateName);
        labelPane.add(labelUpdateAnimalType);
        labelPane.add(labelUpdateAnimalType1);
        labelPane.add(labelUpdateAge);
        labelPane.add(textFieldUpdateAge);
        labelPane.add(labelPen);
        labelPane.add(textFieldPen);
        labelPane.add(updateButton);
        labelPane.add(goBackButton);


        menuBar.setBounds(0, 0, frame.getWidth(), 30);
        menuBar.add(labelAnimalType);
        menuBar.add(animalType);


        frame.setJMenuBar(menuBar);
        frame.setContentPane(labelPane);

    }

    public String getAnimalType() {
        return animalType.getText();
    }

    public String getSearchText() {
        return textFieldSearchText.getText();
    }

    public String getAddName() {
        return textFieldAddName.getText();
    }

    public String getAddAnimalType() {
        return labelAddAnimalType.getText();
    }

    public String getAddAge() {
        return textFieldAddAge.getText();
    }

    public String getDeleteName() {
        return textFieldDeleteName.getText();
    }

    public String getId() {
        return labelId1.getText();
    }

    public String getUpdateName() {
        return textFieldUpdateName.getText();
    }

    public String getUpdateAnimalType() {
        return labelUpdateAnimalType1.getText();
    }

    public String getUpdateAge() {
        return textFieldUpdateAge.getText();
    }

    public String getPen() {
        return textFieldPen.getText();
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

    public void displayData(List<AnimalModel> animals) {
        String[] coll = {"id", "name", "type", "age", "pen"};

        DefaultTableModel model = new DefaultTableModel(coll, 0);
        for (AnimalModel animal : animals) {
            Object[] row = {animal.getAnimalId(), animal.getName(), animal.getAnimalType(), animal.getAge(), animal.getPen()};
            model.addRow(row);

        }

        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(0, Comparator.comparingInt((Integer id) -> id).reversed());
        sorter.setComparator(3, Comparator.comparingInt((Integer age) -> age).reversed());
        sorter.setComparator(4, Comparator.comparingInt((Integer pen) -> pen).reversed());

        table.setModel(model);
        table.setRowSorter(sorter);
    }

    public void displayData(List<AnimalModel> animals, String searchText) {
        String[] coll = {"id", "name", "type", "age", "pen"};

        DefaultTableModel model = new DefaultTableModel(coll, 0);
        for (AnimalModel animal : animals) {
            Object[] row = {animal.getAnimalId(), animal.getName(), animal.getAnimalType(), animal.getAge(), animal.getPen()};
            model.addRow(row);

        }

        TableRowSorter sorter = new TableRowSorter(model);
        sorter.setComparator(0, Comparator.comparingInt((Integer id) -> id).reversed());
        sorter.setComparator(3, Comparator.comparingInt((Integer age) -> age).reversed());
        sorter.setComparator(4, Comparator.comparingInt((Integer pen) -> pen).reversed());


        sorter.setRowFilter(new FarmModel.MyRowFilter(searchText));
        table.setModel(model);
        table.setRowSorter(sorter);
    }

    public void emptyUpdateFields() {
        labelId1.setText("");
        textFieldUpdateName.setText("");
        labelUpdateAnimalType1.setText("");
        textFieldUpdateAge.setText("");
        textFieldPen.setText("");
    }

    public FarmController getFilterController() {
        return farmController;
    }

    public void setFarmController(FarmController farmController) {

        this.farmController = farmController;
    }


}

