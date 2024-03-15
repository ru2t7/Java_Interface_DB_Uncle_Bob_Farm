package main;

import controller.ChooseUserController;
import view.ChooseUserView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        ChooseUserController chooseUserController = new ChooseUserController();
        ChooseUserView chooseUserView = new ChooseUserView(chooseUserController);
        chooseUserController.setView(chooseUserView);
        chooseUserView.setVisibility(true);
    }
}































