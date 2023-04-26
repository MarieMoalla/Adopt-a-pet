package model;

public class AdoptionRequest {
    //region attributes
    private int id;
    private boolean accepted = false;
    private int ownerId;
    private int adapterId;
    //foreign key
    private int petId;
    //endregion

    //region constructor
    public AdoptionRequest(){}
    public AdoptionRequest(int id, boolean accepted, int ownerId, int adapterId, int petId) {
        this.id = id;
        this.accepted = accepted;
        this.ownerId = ownerId;
        this.adapterId = adapterId;
        this.petId = petId;
    }
    //endregion

    //region getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean getAccepted() {
        return accepted;
    }
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getAdapterId() {
        return adapterId;
    }
    public void setAdapterId(int adapterId) {
        this.adapterId = adapterId;
    }

    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {this.petId = petId;}
    //endregion
}
