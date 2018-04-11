package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 10/04/18.
 */

public class AdapterSpinner extends BaseAdapter {

    Activity con;
    ArrayList<University> arrayList;
    boolean selectAll;

    public AdapterSpinner(Activity con, ArrayList<University> arrayList) {
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
        final AdapterSpinner.ViewHolder viewHolder;
        HashMap map = new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new AdapterSpinner.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterSpinner.ViewHolder) view.getTag();
        }

        final University item = arrayList.get(i);
        viewHolder.name.setText(item.getUniversiy_name());


        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name;


    }

    public void toggleSelectAll() {
        this.selectAll = !this.selectAll;
        notifyDataSetInvalidated();
    }

    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog);

        dialog.show();

    }



}
