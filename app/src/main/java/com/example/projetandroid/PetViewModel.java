package com.example.projetandroid;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import model.Pet;

public class PetViewModel extends ViewModel {
    public  MutableLiveData<String> petName = new MutableLiveData<>();
    public  MutableLiveData<String> petBreed = new MutableLiveData<>();
    public  MutableLiveData<String> petGender = new MutableLiveData<>();
    public  MutableLiveData<String> petAge = new MutableLiveData<>();
    public  MutableLiveData<String> petWeight = new MutableLiveData<>();
    public  MutableLiveData<String> petHasReport = new MutableLiveData<>();
    public  MutableLiveData<String> petForAdaption = new MutableLiveData<>();
    public  MutableLiveData<String> petOwnerId = new MutableLiveData<>();

    public  MutableLiveData<Pet> petLiveData = new MutableLiveData<>();



    public PetViewModel ()
    {
        petName.setValue("");
        petBreed.setValue("");
        petGender.setValue("");
        petWeight.setValue("0f");
        petAge.setValue("0");
        petHasReport.setValue("0");
        petForAdaption.setValue("0");
        petOwnerId.setValue("0");
    }
    public PetViewModel (String name,String breed, String gender, String weight, String age, String hasreport, String foradaption, String ownerid )
    {
        petName.setValue(name);
        petBreed.setValue(breed);
        petGender.setValue(gender);
        petWeight.setValue(String.valueOf(weight));
        petAge.setValue(String.valueOf(age));
        petHasReport.setValue(String.valueOf(hasreport));
        petForAdaption.setValue(String.valueOf(foradaption));
        petOwnerId.setValue(String.valueOf(ownerid));
    }

    public MutableLiveData<String> getPetName()
    {
        return petName;
    }
    public void setPetName(String name)
    {
        this.petName.setValue(name);
    }

    public void setPet (Pet p)
    {
        this.petName.setValue(p.getName());
        this.petGender.setValue(p.getGender());
        this.petBreed.setValue(p.getBreed());
        this.petWeight.setValue(String.valueOf(p.getWeight()));
        this.petAge.setValue(String.valueOf(p.getAge()));
        this.petLiveData.setValue(p);
    }
    public Pet getPet ()
    {
        return new Pet (0,this.petName.getValue(),this.petBreed.getValue(),this.petGender.getValue(),Float.parseFloat(this.petWeight.getValue()),Integer.parseInt(this.petAge.getValue()),Integer.parseInt(this.petHasReport.getValue()),Integer.parseInt(this.petForAdaption.getValue()),Integer.parseInt(this.petOwnerId.getValue()));
    }

    public MutableLiveData<Pet> getPetLiveData() {
        return petLiveData;
    }
}
