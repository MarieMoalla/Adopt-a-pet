package adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import model.Pet;
import model.User;

public class UserAdapter extends ArrayAdapter<User> {
    private List<Pet> pets;
    private Context context;

    public UserAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }



}
