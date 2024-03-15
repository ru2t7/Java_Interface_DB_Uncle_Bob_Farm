package view;

import controller.CreateAccountController;

import javax.swing.*;
import java.awt.*;

public class CreateAccountView extends AccountView {

    JFrame frame = new JFrame("Create Account");
    JButton createButton = new JButton("Create Account");
    JButton goBackButton = new JButton("Go Back");
    CreateAccountController createAccountController;

    public CreateAccountView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 400, 400);

        Color myGrey =new Color(43,45,48);
        frame.setBackground(myGrey);
        labelUsername.setForeground(Color.WHITE);
        labelPassword.setForeground(Color.WHITE);
        labelAge.setForeground(Color.WHITE);
        labelName.setForeground(Color.WHITE);

        createButton.addActionListener(e -> createAccountController.createButtonClicked());
        goBackButton.addActionListener(e -> createAccountController.goBackButtonClicked());


        labelUsername.setBounds(100, 30, 100, 20);
        textFieldUsername.setBounds(200, 30, 100, 20);
        labelPassword.setBounds(100, 55, 100, 20);
        textFieldPassword.setBounds(200, 55, 100, 20);
        labelName.setBounds(100, 80, 100, 20);
        textFieldName.setBounds(200, 80, 100, 20);
        labelAge.setBounds(100, 105, 100, 20);
        textFieldAge.setBounds(200, 105, 100, 20);
        createButton.setBounds(100, 140, 200, 20);
        goBackButton.setBounds(150, 165, 100, 20);


        JLabel label = new JLabel();
        label.setBounds(0, 0, 400, 400);

        label.add(labelUsername);
        label.add(textFieldUsername);
        label.add(labelPassword);
        label.add(textFieldPassword);
        label.add(labelName);
        label.add(textFieldName);
        label.add(labelAge);
        label.add(textFieldAge);
        label.add(createButton);
        label.add(goBackButton);

        frame.setContentPane(label);
    }


    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public CreateAccountController createAccountController() {
        return createAccountController;
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setCreateAccountController(CreateAccountController createAccountController) {
        this.createAccountController = createAccountController;
    }

}