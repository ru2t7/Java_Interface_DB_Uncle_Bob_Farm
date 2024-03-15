package view;


import controller.ChooseUserController;


import javax.swing.*;
import java.awt.*;

public class ChooseUserView extends JPanel {

    JButton adminButton = new JButton("Admin");
    JButton visitorButton = new JButton("Visitor");
    JFrame frame = new JFrame("Uncle Bob Farm");

    public ChooseUserView(ChooseUserController controller) {
        Color myGrey =new Color(43,45,48);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 400, 200);

        frame.setBackground(myGrey);


        adminButton.setBounds(90, 20, 100, 20);
        visitorButton.setBounds(210, 20, 100, 20);

        adminButton.addActionListener(e -> controller.adminView());
        visitorButton.addActionListener(e -> controller.visitorView());

        JLabel labelPane = new JLabel();
        labelPane.add(adminButton);
        labelPane.add(visitorButton);

        frame.setContentPane(labelPane);

    }

    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
    }

}
