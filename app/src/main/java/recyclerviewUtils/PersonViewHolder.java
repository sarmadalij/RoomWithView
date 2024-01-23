package recyclerviewUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarmadali.roomwithview.R;

public class PersonViewHolder extends RecyclerView.ViewHolder {

    private final TextView personName;
    private final TextView personProfession;


    public PersonViewHolder(@NonNull View itemView) {
        super(itemView);

        personName = itemView.findViewById(R.id.name);
        personProfession = itemView.findViewById(R.id.profession);
    }

    public void bind(String name, String profession){
        personName.setText(name);
        personProfession.setText(profession);
    }


    static PersonViewHolder create (ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PersonViewHolder(view);
    }
}
