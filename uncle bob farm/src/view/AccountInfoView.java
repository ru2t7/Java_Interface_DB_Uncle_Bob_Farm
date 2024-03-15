package view;

import controller.AccountInfoController;
import model.VisitorModel;

import javax.swing.*;
import java.awt.*;

public class AccountInfoView extends AccountView {
    JFrame frame = new JFrame("Account Info");
    JButton updateButton = new JButton("Update");
    AccountInfoController accountInfoController;

    public AccountInfoView() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);

        Color myGrey =new Color(43,45,48);
        frame.setBackground(myGrey);
        labelUsername.setForeground(Color.WHITE);
        labelName.setForeground(Color.WHITE);
        labelAge.setForeground(Color.WHITE);
        labelPassword.setForeground(Color.WHITE);

        updateButton.addActionListener(e -> accountInfoController.updateButtonClicked());


        labelUsername.setBounds(100, 30, 100, 20);
        textFieldUsername.setBounds(200, 30, 100, 20);
        labelPassword.setBounds(100, 55, 100, 20);
        textFieldPassword.setBounds(200, 55, 100, 20);
        labelName.setBounds(100, 80, 100, 20);
        textFieldName.setBounds(200, 80, 100, 20);
        labelAge.setBounds(100, 105, 100, 20);
        textFieldAge.setBounds(200, 105, 100, 20);

        updateButton.setBounds(125, 140, 120, 20);


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
        label.add(updateButton);


        frame.setContentPane(label);
    }


    public void setVisibility(boolean isVisible) {
        frame.setVisible(isVisible);
    }

    public AccountInfoController accountInfoController() {
        return accountInfoController;
    }

    public void showMessage(String message, int option) {
        if (option == 0) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }
        if (option == 1) {
            JOptionPane.showMessageDialog(frame, message, "Swing Tester", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setAccountInfoController(AccountInfoController accountInfoController) {
        this.accountInfoController = accountInfoController;
    }

    public void setAccountData(VisitorModel visitorModel) {
        textFieldUsername.setText(visitorModel.getUsername());
        textFieldPassword.setText(visitorModel.getPassword());
        textFieldName.setText(visitorModel.getName());
        textFieldAge.setText(String.valueOf(visitorModel.getAge()));
    }
}
