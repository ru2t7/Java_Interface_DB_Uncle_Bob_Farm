package view;

import controller.VisitorLogInController;

import javax.swing.*;
import java.awt.*;

public class VisitorLogInView extends LogInView {


    JFrame frame = new JFrame("Visitor");

    JButton createAccountButton = new JButton("Create New Account");


    VisitorLogInController visitorLogInController;

    public VisitorLogInView() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 400, 200);

        Color myGrey =new Color(43,45,48);
        frame.setBackground(myGrey);
        labelUsername.setForeground(Color.WHITE);
        labelPassword.setForeground(Color.WHITE);

        labelUsername.setBounds(10, 10, 100, 20);
        textFieldUsername.setBounds(115, 10, 100, 20);
        labelPassword.setBounds(10, 35, 100, 20);
        textFieldPassword.setBounds(115, 35, 100, 20);
        logInButton.setBounds(10, 60, 100, 20);
        goBackButton.setBounds(115, 60, 100, 20);
        createAccountButton.setBounds(10, 85, 205, 20);

        logInButton.addActionListener(e -> visitorLogInController.logInButtonClicked());
        createAccountButton.addActionListener(e -> visitorLogInController.createAccountView());
        goBackButton.addActionListener(e -> visitorLogInController.choseUserView());

        labelPane.add(labelUsername);
        labelPane.add(textFieldUsername);
        labelPane.add(labelPassword);
        labelPane.add(textFieldPassword);
        labelPane.add(logInButton);
        labelPane.add(createAccountButton);
        labelPane.add(goBackButton);

        frame.setContentPane(labelPane);
    }


    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public VisitorLogInController getVisitorController() {
        return visitorLogInController;
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setVisitorController(VisitorLogInController visitorLogInController) {
        this.visitorLogInController = visitorLogInController;
    }
}
