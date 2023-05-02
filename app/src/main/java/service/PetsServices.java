package service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.List;

import adapter.PetListAdapter;
import model.Pet;

public class PetsServices extends Service {

    private DatabaseHelper databaseHelper;

    public PetsServices(Context context)
    {
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void createPet (Pet pet) {
        try {
            databaseHelper.addPet(pet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pet getPetById (int id)
    {
        return databaseHelper.getpetDetail(id);

    }

    public void displayPets(int id, ListView listView) {
        List<Pet> pets = databaseHelper.getAllPets();
        Log.i("Current logged in user ", String.valueOf(id));
        for (Pet p : pets)
            Log.i("petinfo ",p.getName());
        PetListAdapter adapter = new PetListAdapter(this, pets);
        listView.setAdapter(adapter);
    }

    public void displayMyPets(int id, ListView listView) {
        List<Pet> pets = databaseHelper.getMyPets(id);
        Log.i("Current logged in user ", String.valueOf(id));
        for (Pet p : pets)
            Log.i("petinfo ",p.getName());
        PetListAdapter adapter = new PetListAdapter(this, pets);
        listView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}