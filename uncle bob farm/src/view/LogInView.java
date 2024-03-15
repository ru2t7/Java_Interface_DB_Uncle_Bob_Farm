package view;

import javax.swing.*;

public class LogInView {

    JLabel labelPane = new JLabel();
    JLabel labelUsername = new JLabel("Username: ");
    JTextField textFieldUsername = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();
    JButton goBackButton = new JButton("Go Back");
    JButton logInButton = new JButton("Log In");


    public String getUsername() {
        return textFieldUsername.getText();
    }

    public String getPassword() {
        return textFieldPassword.getText();
    }

}
