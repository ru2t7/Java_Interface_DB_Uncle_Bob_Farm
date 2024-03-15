package model;

public class ReservationModel {
    int id;
    int visitorId;
    String date;
    int discountId;
    int packageId;
    String packageName;
    int finalPrice;

    public ReservationModel(int id, int visitorId, String date, int discountId, int packageId, String packageName, int finalPrice) {
        this.id = id;
        this.visitorId = visitorId;
        this.date = date;
        this.discountId = discountId;
        this.packageId = packageId;
        this.packageName = packageName;
        this.finalPrice = finalPrice;
    }

    public ReservationModel(int id, int visitorId, String date, int discountId, int packageId, int finalPrice) {
        this.id = id;
        this.visitorId = visitorId;
        this.date = date;
        this.discountId = discountId;
        this.packageId = packageId;
        this.finalPrice = finalPrice;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
