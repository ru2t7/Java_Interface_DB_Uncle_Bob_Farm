package controller;

import model.AnimalModel;
import model.AnimalType;
import repository.FarmRepository;
import model.FarmModel;
import view.FarmView;
import view.ChooseUserView;

import java.util.List;

public class FarmController {

    FarmView farmView = new FarmView();

    FarmRepository farmRepository;
    FarmModel farmModel;

    public FarmController() {
        this.farmRepository = new FarmRepository();
        this.farmModel = new FarmModel();
        this.farmView.setFarmController(this);
        this.farmView.setVisibility(true);

    }

    public void animalButtonClicked(AnimalType animalType) {
        try {
            List<AnimalModel> animals = farmRepository.getData(farmModel.convertTypeToId(animalType));
            farmView.displayData(animals);
        } catch (Exception e) {

            this.farmView.showMessage("Input error!", 0);

        }
    }

    public void searchButtonClicked() {
        try {
            List<AnimalModel> animals = farmRepository.getData(farmModel.convertTypeToId(farmModel.convertTextToType(farmView.getAnimalType())));
            farmView.displayData(animals, farmView.getSearchText());
        } catch (Exception e) {

            this.farmView.showMessage("Input error!", 0);

        }
    }

    public void addButtonClicked() {

        try {
            boolean animalAdded = false;
            int animalTypeId = this.farmModel.convertTypeToId(farmModel.convertTextToType(farmView.getAddAnimalType()));
            List<Integer> penIds = farmRepository.getPenIds(animalTypeId);
            for (int i = 0; i < penIds.size(); i++) {
                int penId = penIds.get(i);
                if (farmRepository.isThereSpace(penId)) {
                    animalAdded = true;
                    String name = this.farmView.getAddName();
                    int age = Integer.parseInt(this.farmView.getAddAge());
                    int nextIndex = farmRepository.getNextIndex();
                    AnimalModel animal=new AnimalModel(nextIndex,animalTypeId,farmView.getAddAnimalType(), name, age, penId);
                    String message = farmRepository.createAnimal(animal);
                    this.farmView.showMessage(message, 1);
                    animalButtonClicked(farmModel.convertTextToType(farmView.getAnimalType()));
                    break;
                }
            }
            if (!animalAdded) this.farmView.showMessage("There is no space", 1);
        } catch (Exception e) {
            this.farmView.showMessage("Input error!", 0);
        }
    }

    public void deleteButtonClicked() {

        try {
            String name = this.farmView.getDeleteName();
            String message = farmRepository.deleteAnimal(name);
            this.farmView.showMessage(message, 1);
            //this.filterView.emptyUpdateFields();
            this.farmRepository.showAllAnimals();
            animalButtonClicked(farmModel.convertTextToType(farmView.getAnimalType()));

        } catch (Exception e) {
            this.farmView.showMessage("Input error!", 0);
        }
    }

    public void updateButtonClicked() {
        try {
            if (!farmView.getId().isBlank()) {
                AnimalModel animal = new AnimalModel();
                animal.setAnimalId(Integer.valueOf(farmView.getId()));
                animal.setName(farmView.getUpdateName());
                animal.setAnimalTypeId(farmModel.convertTypeToId(farmModel.convertTextToType(farmView.getUpdateAnimalType())));
                animal.setAnimalType(farmView.getUpdateAnimalType());
                animal.setAge(Integer.valueOf(farmView.getUpdateAge()));
                animal.setPen(Integer.valueOf(farmView.getPen()));
                int rowsAffected = this.farmRepository.updateAnimal(animal);
                if (rowsAffected == 0) {
                    this.farmView.showMessage("Animal NOT Updated!", 1);
                } else {
                    this.farmView.showMessage("Animal Updated!", 1);
                    this.farmView.emptyUpdateFields();
                    animalButtonClicked(farmModel.convertTextToType(farmView.getAnimalType()));
                }

            } else
                this.farmView.showMessage("First Select An Animal Then Update It!", 1);
        } catch (Exception e) {
            this.farmView.showMessage("Input error!", 0);
        }

    }

    public void goBackButtonClicked() {
        farmView.setVisibility(false);
        ChooseUserController chooseUserController = new ChooseUserController();
        ChooseUserView chooseUserView = new ChooseUserView(chooseUserController);
        chooseUserController.setView(chooseUserView);
        chooseUserView.setVisibility(true);
    }


}
