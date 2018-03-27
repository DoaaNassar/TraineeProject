package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
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
 * Created by مركز الخبراء on 02/12/2018.
 */

public class ListTraineeAdapter extends BaseAdapter {

    Activity con;
    ArrayList<TrainerObject> arrayList;
    boolean selectAll ;

    public ListTraineeAdapter(Activity con, ArrayList<TrainerObject> arrayList) {
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
        final ViewHolder viewHolder;
        HashMap map =new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.card_view_layout, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.name= row.findViewById(R.id.nameTraineeCard);
            viewHolder.idNumber=row.findViewById(R.id.idNumber);
            viewHolder.edit = row.findViewById(R.id.edit);
            viewHolder.delete = row.findViewById(R.id.delete);
            viewHolder.checkBox=row.findViewById(R.id.check);
            viewHolder.addNew =row.findViewById(R.id.addNew);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (ViewHolder) view.getTag();
        }

        final TrainerObject item = arrayList.get(i);
        viewHolder.name.setText(item.getTrainer_name());

        viewHolder.checkBox.setChecked(selectAll);


//        else {
//            if (map.containsKey(item.getTrainer_name())) {
//                viewHolder.checkBox.setChecked(false);
//            } else {
//                viewHolder.checkBox.setChecked(true);
//
//            }
//        }
        viewHolder.edit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Fragment fragment =new EditTraineeFragment();
                Bundle args =new Bundle();
                args.putSerializable("namee", item);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction =  con.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerTrainee, fragment);
                fragmentTransaction.commit();

            }
        });


        viewHolder.delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(con);
                builder.setMessage("هل أنت متاكد ؟");
                builder.setCancelable(false);
                builder.setPositiveButton("نعم",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                arrayList.remove(arrayList.get(i));
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("لا",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



            }
        });

        //  viewHolder.date.setText(item.getDate());

//        if (item.getIsIncoming()==0){
//            map.put(item.getTime(),item.getTime());
//
//        }else {
//            map.remove(item.getTime());
//        }
//
//        if (map.containsKey(item.getTime())){
//            viewHolder.image.setImageResource(R.drawable.ic_call_made_black_24dp);
//        }else{
//            viewHolder.image.setImageResource(R.drawable.ic_call_received_black_24dp);
//        }

        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name,idNumber,edit,delete,addNew;
        CheckBox checkBox ;


    }

    public void toggleSelectAll() {
        this.selectAll = !this.selectAll;
        notifyDataSetInvalidated();
    }
}



