package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.projetandroid.R;

import java.util.List;

import model.AdoptionRequest;

public class RequestAdapter extends ArrayAdapter<AdoptionRequest> {
    private List<AdoptionRequest> reqs;
    private Context context;

    public RequestAdapter(Context context, List<AdoptionRequest> reqss) {
        super(context, 0, reqss);
        this.context = context;
        this.reqs = reqss;
    }

    //show pet list adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_request, parent, false);

        //Get references to the text views in the layout
        TextView reqId = rowView.findViewById(R.id.req_id_textview);
        TextView reqPetId = rowView.findViewById(R.id.req_pet_id_textview);
        TextView petOwnerId = rowView.findViewById(R.id.req_owner_id_textview);

        // Set the text of the text views to the pet's name, breed, and ID
        reqId.setText(Integer.toString(reqs.get(position).getId()));
        reqPetId.setText(Integer.toString(reqs.get(position).getPetId()));
        petOwnerId.setText(Integer.toString(reqs.get(position).getOwnerId()));

        return rowView;
    }
}