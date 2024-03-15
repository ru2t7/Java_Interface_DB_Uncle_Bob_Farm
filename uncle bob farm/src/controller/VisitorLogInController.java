package controller;


import model.VisitorModel;
import repository.VisitorLogInRepository;
import view.ChooseUserView;
import view.VisitorLogInView;


public class VisitorLogInController {


    VisitorLogInView visitorLogInView = new VisitorLogInView();
    VisitorLogInRepository visitorLogInRepository;

    public VisitorLogInController() {
        this.visitorLogInRepository = new VisitorLogInRepository();
        this.visitorLogInView.setVisibility(true);
        this.visitorLogInView.setVisitorController(this);
    }

    public void logInButtonClicked() {

        try {
            String username = this.visitorLogInView.getUsername();
            String password = this.visitorLogInView.getPassword();
            int visitorId = visitorLogInRepository.checkUsername(username);
            if (visitorId != 0) {
                boolean passwordCorrect = visitorLogInRepository.checkPassword(username, password);
                if (passwordCorrect) {
                    this.visitorLogInView.showMessage("Welcome " + username + " !", 1);
                    VisitorModel visitor = visitorLogInRepository.getVisitorData(username);
                    this.visitorView(visitor);
                } else this.visitorLogInView.showMessage("Password Incorrect!", 1);
            } else this.visitorLogInView.showMessage("Username Does Not Exist!", 1);

        } catch (Exception e) {
            this.visitorLogInView.showMessage("Input error!", 0);
        }

    }

    public void createAccountView() {
        visitorLogInView.setVisibility(false);
        CreateAccountController createAccountController = new CreateAccountController();

    }

    public void choseUserView() {
        visitorLogInView.setVisibility(false);
        ChooseUserController chooseUserController = new ChooseUserController();
        ChooseUserView chooseUserView = new ChooseUserView(chooseUserController);
        chooseUserController.setView(chooseUserView);
        chooseUserView.setVisibility(true);

    }

    public void visitorView(VisitorModel visitor) {
        visitorLogInView.setVisibility(false);
        VisitorController visitorController = new VisitorController(visitor);
        visitorController.displayData();
    }
}