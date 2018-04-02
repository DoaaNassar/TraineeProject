package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;

import duaa.traineeproject.Fragment.EditTraineeFragment;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class OldTraineeAdapter extends BaseAdapter {

    Activity con;
    ArrayList<TrainerObject> arrayList;
    boolean selectAll ;

    public OldTraineeAdapter(Activity con, ArrayList<TrainerObject> arrayList) {
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
        final OldTraineeAdapter.ViewHolder viewHolder;
        HashMap map =new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.old_trainee_item, viewGroup, false);
            viewHolder = new OldTraineeAdapter.ViewHolder();

            viewHolder.name= row.findViewById(R.id.nameTraineeCard);
            viewHolder.idNumber=row.findViewById(R.id.idNumber);
            viewHolder.checkBox=row.findViewById(R.id.check);
            viewHolder.addNew =row.findViewById(R.id.addNew);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (OldTraineeAdapter.ViewHolder) view.getTag();
        }

        final TrainerObject item = arrayList.get(i);
        viewHolder.name.setText(item.getTrainer_name());

        viewHolder.checkBox.setChecked(selectAll);

        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name,idNumber,addNew;
        CheckBox checkBox ;


    }

    public void toggleSelectAll() {
        this.selectAll = !this.selectAll;
        notifyDataSetInvalidated();
    }
}
