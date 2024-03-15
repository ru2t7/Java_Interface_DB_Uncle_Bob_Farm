package view;

import javax.swing.*;

public class AccountView {

    //JFrame frame = new JFrame("Create Account");

    JLabel labelUsername = new JLabel("Username: ");
    JTextField textFieldUsername = new JTextField();
    JLabel labelPassword = new JLabel("Password: ");
    JTextField textFieldPassword = new JTextField();

    JLabel labelName = new JLabel("Name: ");
    JTextField textFieldName = new JTextField();
    JLabel labelAge = new JLabel("Age: ");
    JTextField textFieldAge = new JTextField();

    // JButton createButton = new JButton("Create Account");


    public String getUsername() {
        return textFieldUsername.getText();
    }

    public String getPassword() {
        return textFieldPassword.getText();
    }

    public String getName() {
        return textFieldName.getText();
    }

    public String getAge() {
        return textFieldAge.getText();
    }


    public void emptyAccountFields() {
        textFieldUsername.setText("");
        textFieldPassword.setText("");
        textFieldName.setText("");
        textFieldAge.setText("");
    }


//    public void setVisibility(boolean isVisible){
//        frame.setVisible(isVisible);
//    }
//    public CreateAccountController createAccountController() {
//        return createAccountController;
//    }
//    public void showMessage(String message, int option){
//        if( option == 0) {
//            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
//        }
//        if(option ==1){
//            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//    public void setCreateAccountController(CreateAccountController createAccountController) {
//        this.createAccountController = createAccountController;
//    }

}