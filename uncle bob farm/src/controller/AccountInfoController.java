package controller;

import repository.AccountInfoRepository;
import view.AccountInfoView;
import model.VisitorModel;
import view.ChooseUserView;

public class AccountInfoController {

    AccountInfoRepository accountInfoRepository;
    AccountInfoView accountInfoView = new AccountInfoView();
    VisitorModel visitorModel;

    public AccountInfoController(VisitorModel visitorModel) {
        this.accountInfoRepository = new AccountInfoRepository();
        this.accountInfoView.setVisibility(true);
        this.accountInfoView.setAccountInfoController(this);
        this.visitorModel = visitorModel;
    }

    public void updateButtonClicked() {
        visitorModel.setUsername(accountInfoView.getUsername());
        visitorModel.setPassword(accountInfoView.getPassword());
        visitorModel.setName(accountInfoView.getName());
        visitorModel.setAge(Integer.parseInt(accountInfoView.getAge()));

        String message = accountInfoRepository.updateVisitor(visitorModel);
        accountInfoView.showMessage(message, 1);
        accountInfoView.setAccountData(visitorModel);
    }


}
