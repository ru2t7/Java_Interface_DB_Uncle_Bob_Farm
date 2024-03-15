package controller;

import model.PackageModel;
import model.ReservationModel;
import model.VisitorModel;
import repository.VisitorRepository;
import view.VisitorView;
import view.ChooseUserView;

import java.util.List;

public class VisitorController {

    VisitorView visitorView = new VisitorView();
    VisitorRepository visitorRepository;
    VisitorModel visitorModel;
    PackageModel packageModel;

    public VisitorController(VisitorModel visitor) {
        this.visitorRepository = new VisitorRepository();
        this.visitorModel = visitor;
        this.packageModel = new PackageModel();
        this.visitorView.setVisitorController(this);
        this.visitorView.setVisibility(true);
    }

    public void displayData() {
        visitorView.displayPackagesData(visitorRepository.getPackagesData(visitorModel));
        visitorView.displayReservationsData(visitorRepository.getReservationsData(visitorModel));
    }

    public void accountInfoItemClicked() {
        AccountInfoController accountInfoController = new AccountInfoController(visitorModel);
        accountInfoController.accountInfoView.setAccountData(visitorModel);

    }

    public void logOutItemClicked() {
        this.visitorView.setVisibility(false);
        ChooseUserController chooseUserController = new ChooseUserController();
        ChooseUserView chooseUserView = new ChooseUserView(chooseUserController);
        chooseUserController.setView(chooseUserView);
        chooseUserView.setVisibility(true);
    }

    public void deleteItemClicked() {

        int rowsAffected = visitorRepository.deleteVisitor(visitorModel);
        if (rowsAffected == 0)
            visitorView.showMessage("Account NOT Deleted!", 0);
        else {
            visitorView.showMessage("Account Deleted Successfully!", 1);
            logOutItemClicked();
        }

    }

    public void makeReservationButtonClicked() {

        int id = visitorRepository.getNextIndex();
        int packageId = visitorRepository.getPackage(visitorView.getName()).getId();
        String date = visitorView.getDate();
        int disountId = packageModel.convertOptionToDiscountId(visitorView.getDiscount());
        int finalPrice = Integer.parseInt(visitorView.getPrice());
        ReservationModel reservation = new ReservationModel(id, visitorModel.getId(), date, disountId, packageId, finalPrice);
        int rowsAffected = visitorRepository.createReservation(reservation);
        if (rowsAffected == 0)
            visitorView.showMessage("Reservation NOT MADE!", 0);
        else {
            visitorView.showMessage("Reservation Done!", 1);
            visitorView.displayReservationsData(visitorRepository.getReservationsData(visitorModel));
        }


    }


    public PackageModel getPackage(String name) {
        PackageModel newPackage = visitorRepository.getPackage(name);
        return newPackage;
    }

}