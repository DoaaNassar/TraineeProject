package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.Model.PlaceListModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class AdapterSpinnerPlace extends BaseAdapter {

    Activity con;
    ArrayList<PlaceModel> arrayList;

    public AdapterSpinnerPlace(Activity con, ArrayList<PlaceModel> arrayList) {
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
        final AdapterSpinnerPlace.ViewHolder viewHolder;
        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new AdapterSpinnerPlace.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterSpinnerPlace.ViewHolder) view.getTag();
        }

        final PlaceModel item = arrayList.get(i);
        viewHolder.name.setText(item.getPlace_name());


        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name;


    }

}
