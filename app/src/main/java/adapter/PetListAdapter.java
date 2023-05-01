package adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.projetandroid.R;

import java.util.List;

import model.Pet;

public class PetListAdapter extends ArrayAdapter<Pet> {
    private final Context context;
    private final List<Pet> petList;

    public PetListAdapter(Context context, List<Pet> petList) {
        super(context, R.layout.activity_main, petList);
        this.context = context;
        this.petList = petList;
    }
/*  //show pet list adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);

        //Get references to the text views in the layout
        TextView petNameTextView = rowView.findViewById(R.id.pet_name_textview);
        TextView petBreedTextView = rowView.findViewById(R.id.pet_breed_textview);
        TextView petIdTextView = rowView.findViewById(R.id.pet_id_textview);

        // Set the text of the text views to the pet's name, breed, and ID
        petNameTextView.setText(petList.get(position).getName());
        petBreedTextView.setText(petList.get(position).getBreed());
        petIdTextView.setText(Integer.toString(petList.get(position).getId()));

        return rowView;
    }
    */

}
