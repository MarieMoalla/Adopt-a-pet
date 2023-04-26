package model;

public class Pet {
    //region attributes
    private int id;
    private String name;
    private String breed;
    private String gender;
    private float weight;
    private int age;
    private boolean hasReport;
    private boolean forAdaption = false;
    //foreign key
    private int ownerId;
    //endregion

    //region constructor
    public Pet(){}
    public Pet(int id, String name, String breed, String gender, float weight, int age, boolean  hasReport, boolean forAdaption,int ownerId) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.weight = weight;
        this.hasReport = hasReport;
        this.forAdaption = forAdaption;
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

    public boolean getHasReport() {
        return hasReport;
    }
    public void setHasReport(boolean hasReport) {
        this.hasReport = hasReport;
    }

    public boolean getForAdaption() {
        return forAdaption;
    }
    public void setForAdaption(boolean forAdaption) {
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
