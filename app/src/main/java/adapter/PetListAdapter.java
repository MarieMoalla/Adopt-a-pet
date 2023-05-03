package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projetandroid.R;

import java.util.List;

import model.Pet;

public class PetListAdapter extends ArrayAdapter<Pet> {
    private List<Pet> pets;
    private Context context;

    public PetListAdapter(Context context, List<Pet> pets) {
        super(context, 0, pets);
        this.context = context;
        this.pets = pets;
    }
 //show pet list adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_adoption, parent, false);

        //Get references to the text views in the layout
        TextView petNameTextView = rowView.findViewById(R.id.pet_name_textview);
        TextView petBreedTextView = rowView.findViewById(R.id.pet_id_textview);
        TextView petIdTextView = rowView.findViewById(R.id.pet_breed_textview);

        // Set the text of the text views to the pet's name, breed, and ID
        petNameTextView.setText(pets.get(position).getName());
        petBreedTextView.setText(pets.get(position).getBreed());
        petIdTextView.setText(Integer.toString(pets.get(position).getId()));

        return rowView;
    }


}
