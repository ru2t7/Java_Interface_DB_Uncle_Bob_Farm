package model;

import java.util.ArrayList;

public class PackageModel {

    int id;
    String name;
    String description;
    int price;
    int visitorId;

    String[] discountOptions;

    public PackageModel() {

    }

    public PackageModel(int id, String name, String description, int price, int visitor_id, String[] discountOptions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.visitorId = visitor_id;
        this.discountOptions = discountOptions;
    }

    public PackageModel(int id, String name, String description, int price, int visitor_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.visitorId = visitor_id;
    }

    public String convertDiscountIdToOption(int discountId) {
        switch (discountId) {
            case (1):
                return "50% (age<18)";
            case (2):
                return "30% (age>60)";
            case (3):
                return "20% (first visit)";
            case (4):
                return "15% (student age<25)";
            default:
                return "0%";
        }
    }

    public int convertOptionToDiscountId(String option) {
        switch (option) {
            case ("50% (age<18)"):
                return 1;
            case ("30% (age>60)"):
                return 2;
            case ("20% (first visit)"):
                return 3;
            case ("15% (student age<25)"):
                return 4;
            default:
                return 0;
        }
    }

    public int convertOptionToPercentage(String option) {
        switch (option) {
            case ("50% (age<18)"):
                return 50;
            case ("30% (age>60)"):
                return 30;
            case ("20% (first visit)"):
                return 20;
            case ("15% (student age<25)"):
                return 15;
            default:
                return 0;
        }
    }


    public String[] getDiscountOptions() {
        return discountOptions;
    }

    public void setDiscountOptions(String[] discountOptions) {
        this.discountOptions = discountOptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }
}
