package view;

import controller.AdminLogInController;

import javax.swing.*;
import java.awt.*;

public class AdminLogInView extends LogInView {
    JFrame frame = new JFrame("Admin Check");
    JButton skipButton = new JButton("Skip Log In");


    AdminLogInController adminLogInController;

    public AdminLogInView() {


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

        logInButton.addActionListener(e -> adminLogInController.logInButtonClicked());
        skipButton.addActionListener(e -> adminLogInController.farmView());
        goBackButton.addActionListener(e -> adminLogInController.goBackButtonClicked());

        labelPane.add(labelUsername);
        labelPane.add(textFieldUsername);
        labelPane.add(labelPassword);
        labelPane.add(textFieldPassword);
        labelPane.add(logInButton);
        labelPane.add(goBackButton);


        frame.setContentPane(labelPane);

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

    public AdminLogInController getAdminController() {
        return adminLogInController;
    }

    public void setAdminController(AdminLogInController adminLogInController) {
        this.adminLogInController = adminLogInController;
    }
}
