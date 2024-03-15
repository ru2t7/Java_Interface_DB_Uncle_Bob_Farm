package model;

public class AnimalModel {
    int animalId;
    int animalTypeId;
    String animalType;
    String name;
    int age;
    int pen;

    public AnimalModel() {
    }

    public AnimalModel(int animalId, int animalTypeId, String animalType, String name, int age, int pen) {
        this.animalId = animalId;
        this.animalTypeId = animalTypeId;
        this.animalType = animalType;
        this.name = name;
        this.age = age;
        this.pen = pen;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(int animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPen() {
        return pen;
    }

    public void setPen(int pen) {
        this.pen = pen;
    }
}
