package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import duaa.traineeproject.Model.PartPlaceModel;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 20/04/18.
 */

public class AdapterSpinnerPartPlace extends BaseAdapter {

    Activity con;
    ArrayList<PartPlaceModel> arrayList;

    public AdapterSpinnerPartPlace(Activity con, ArrayList<PartPlaceModel> arrayList) {
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
        final AdapterSpinnerPartPlace.ViewHolder viewHolder;
        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new AdapterSpinnerPartPlace.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterSpinnerPartPlace.ViewHolder) view.getTag();
        }

        final PartPlaceModel item = arrayList.get(i);
        Log.d("ddddd",item.getPartment_name());

        viewHolder.name.setText(item.getPartment_name());

        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name;


    }

}
