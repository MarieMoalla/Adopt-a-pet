package model;

public class Pet {
    //region attributes
    private int id;
    private String name;
    private String breed;
    private String gender="male";
    private float weight=1;
    private int age;
    private int hasReport=0;
    private int forAdaption =0;
    //foreign key
    private int ownerId;
    //endregion

    //region constructor
    public Pet(){}
    public Pet(String name, String breed, String gender, float weight, int age) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.age=age;
    }
    public Pet(int id, String name, String breed, String gender, float weight, int age, int  hasReport, int forAdaption,int ownerId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.hasReport = hasReport;
        this.forAdaption = forAdaption;
        this.age=age;
        this.ownerId = ownerId;
    }
    //endregion

    //region getters & setters
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

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge () {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getHasReport() {
        return hasReport;
    }
    public void setHasReport(int hasReport) {
        this.hasReport = hasReport;
    }

    public int getForAdaption() {
        return forAdaption;
    }
    public void setForAdaption(int forAdaption) {
        this.forAdaption = forAdaption;
    }

    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    //endregion
}
