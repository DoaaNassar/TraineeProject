package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import duaa.traineeproject.Model.SpecializationModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class AdapterSpinnerspecialization extends BaseAdapter {

    Activity con;
    ArrayList<SpecializationModel> arrayList;

    public AdapterSpinnerspecialization(Activity con, ArrayList<SpecializationModel> arrayList) {
        this.con = con;
        this.arrayList = arrayList;

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View row;
        final AdapterSpinnerspecialization.ViewHolder viewHolder;
        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new AdapterSpinnerspecialization.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterSpinnerspecialization.ViewHolder) view.getTag();
        }

        final SpecializationModel item = arrayList.get(i);
        viewHolder.name.setText(item.getSpecalization_name());


        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name;


    }

}
