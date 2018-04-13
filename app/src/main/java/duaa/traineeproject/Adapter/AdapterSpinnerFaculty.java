package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 12/04/18.
 */

public class AdapterSpinnerFaculty extends BaseAdapter {

    Activity con;
    ArrayList<Faculty> arrayList;
    boolean selectAll;

    public AdapterSpinnerFaculty(Activity con, ArrayList<Faculty> arrayList) {
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
        final AdapterSpinnerFaculty.ViewHolder viewHolder;
        HashMap map = new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.item_spinner, viewGroup, false);
            viewHolder = new AdapterSpinnerFaculty.ViewHolder();

            viewHolder.name = row.findViewById(R.id.name);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (AdapterSpinnerFaculty.ViewHolder) view.getTag();
        }

        final Faculty item = arrayList.get(i);
        viewHolder.name.setText(item.getFaculty_name());


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
