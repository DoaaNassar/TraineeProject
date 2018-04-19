package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 15/04/18.
 */

public class TypeSpinnerAdapter  extends BaseAdapter {

    Activity con;
    ArrayList<String> arrayList;

    public TypeSpinnerAdapter(Activity con, ArrayList<String> arrayList) {
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
        final TypeSpinnerAdapter.ViewHolder viewHolder;
        HashMap map = new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new TypeSpinnerAdapter.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (TypeSpinnerAdapter.ViewHolder) view.getTag();
        }

        final String item = arrayList.get(i);
        viewHolder.name.setText(item);


        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name;


    }


}
