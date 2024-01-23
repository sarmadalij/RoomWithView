package recyclerviewUtils;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import DAO.Person;

public class PersonListAdapter extends ListAdapter<Person, PersonViewHolder> {

    public PersonListAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallBack){
        super(diffCallBack);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PersonViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        Person current  = getItem(position);
        holder.bind(current.getPersonName(), current.getPersonProfession());
    }

    public static class PersonDIff extends DiffUtil.ItemCallback<Person> {

        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.getPersonName().equals(newItem.getPersonName()) &&
                    oldItem.getPersonProfession().equals(newItem.getPersonProfession());
        }
    }
}
