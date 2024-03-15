package controller;


import view.ChooseUserView;

public class ChooseUserController {

    private ChooseUserView view;

    public ChooseUserView getView() {
        return view;
    }

    public void setView(ChooseUserView view) {
        this.view = view;
    }

    public void adminView() {
        view.setVisibility(false);
        AdminLogInController adminLogInController = new AdminLogInController();
    }

    public void visitorView() {
        view.setVisibility(false);
        VisitorLogInController visitorLogInController = new VisitorLogInController();
    }
}
