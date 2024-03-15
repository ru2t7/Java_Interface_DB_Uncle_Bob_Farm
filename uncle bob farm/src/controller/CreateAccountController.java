package controller;


import model.VisitorModel;
import view.CreateAccountView;
import repository.CreateAccountRepository;

public class CreateAccountController {

    CreateAccountRepository createAccountRepository;
    CreateAccountView createAccountView = new CreateAccountView();

    public CreateAccountController() {
        this.createAccountRepository = new CreateAccountRepository();
        this.createAccountView.setVisibility(true);
        this.createAccountView.setCreateAccountController(this);
    }

    public void createButtonClicked() {

        try {
            boolean animalAdded = false;
            Integer id = this.createAccountRepository.getNextIndex();
            Integer age = Integer.valueOf(this.createAccountView.getAge());
            String password = this.createAccountView.getPassword();
            String name = this.createAccountView.getName();
            String username = this.createAccountView.getUsername();
            VisitorModel newVisitor = new VisitorModel(id, age, password, name, username);
            String message = createAccountRepository.createAccount(newVisitor);
            this.createAccountView.showMessage(message, 1);
            createAccountView.emptyAccountFields();


        } catch (Exception e) {
            this.createAccountView.showMessage("Input error!", 0);
        }
    }

    public void goBackButtonClicked() {
        createAccountView.setVisibility(false);
        VisitorLogInController visitorLogInController = new VisitorLogInController();
    }
}
