package controller;


import view.AdminLogInView;
import view.ChooseUserView;
import model.AnimalType;

import java.util.Objects;

public class AdminLogInController {
    AdminLogInView adminLogInView = new AdminLogInView();

    public AdminLogInController() {
        this.adminLogInView.setVisibility(true);
        this.adminLogInView.setAdminController(this);
    }

    public void goBackButtonClicked() {
        adminLogInView.setVisibility(false);
        ChooseUserController chooseUserController = new ChooseUserController();
        ChooseUserView chooseUserView = new ChooseUserView(chooseUserController);
        chooseUserController.setView(chooseUserView);
        chooseUserView.setVisibility(true);

    }

    public void logInButtonClicked() {

        try {
            String username = this.adminLogInView.getUsername();
            String password = this.adminLogInView.getPassword();
            boolean checkPassed = checkUsernameAndPassword(username, password);
            String message = checkPassed ? "Welcome Uncle Bob!" : "Username or Password Incorrect";
            if (checkPassed) this.farmView();
            this.adminLogInView.showMessage(message, 1);

        } catch (Exception e) {
            this.adminLogInView.showMessage("Input error!", 0);
        }

    }

    public boolean checkUsernameAndPassword(String username, String password) {
        return Objects.equals(username, "UncleBob420") && Objects.equals(password, "itAintMuch");
    }

    public void farmView() {
        adminLogInView.setVisibility(false);
        FarmController farmController = new FarmController();
        farmController.animalButtonClicked(AnimalType.ANIMAL);
    }
}
