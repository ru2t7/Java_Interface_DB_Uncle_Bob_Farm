package model;

import javax.swing.*;

import static model.AnimalType.*;

public class FarmModel {

    public String convertIdToType(int id) {
        switch (id) {
            case 1:
                return "sheep";
            case 2:
                return "chicken";
            case 3:
                return "goose";
            case 4:
                return "pig";
            case 5:
                return "goat";
            case 6:
                return "horse";
            case 7:
                return "duck";
            case 8:
                return "pony";
            case 9:
                return "cow";
            default:
                return "animal";
        }
    }

    public int convertTypeToId(AnimalType animalType) {
        switch (animalType) {
            case SHEEP:
                return 1;
            case CHICKEN:
                return 2;
            case GOOSE:
                return 3;
            case PIG:
                return 4;
            case GOAT:
                return 5;
            case HORSE:
                return 6;
            case DUCK:
                return 7;
            case PONY:
                return 8;
            case COW:
                return 9;
            default:
                return 0;
        }
    }

    public AnimalType convertTextToType(String animalType) {
        switch (animalType) {
            case "sheep":
                return SHEEP;
            case "chicken":
                return CHICKEN;
            case "goose":
                return GOOSE;
            case "pig":
                return PIG;
            case "goat":
                return GOAT;
            case "horse":
                return HORSE;
            case "duck":
                return DUCK;
            case "pony":
                return PONY;
            case "cow":
                return COW;
            default:
                return ANIMAL;
        }
    }
    public String convertTypeToText(AnimalType animalType) {
        switch (animalType) {
            case SHEEP:
                return "sheep";
            case CHICKEN:
                return "chicken";
            case GOOSE:
                return "goose";
            case PIG:
                return "pig";
            case GOAT:
                return "goat";
            case HORSE:
                return "horse";
            case DUCK:
                return "duck";
            case PONY:
                return "pony";
            case COW:
                return "cow";
            default:
                return "animal";
        }
    }

    public static class MyRowFilter extends RowFilter {

        private final String searchText;

        public MyRowFilter(String searchText) {
            this.searchText = searchText;
        }

        @Override
        public boolean include(Entry entry) {
            return entry.getStringValue(1).indexOf(searchText) >= 0;

        }
    }
}