package duaa.traineeproject.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import duaa.traineeproject.Fragment.EditTraineeFragment;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class OldTraineeAdapter
//               extends RecyclerView.Adapter<OldTraineeAdapter.MyViewHolder> {
//
//    private List<TraineeModel> traineeList;
//    CustomItemClickListener listener;
//    Context context;
//    OldTraineeAdapter.MyRecyclerViewListener myRecyclerViewListener;
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        FontTextViewRegular name , number;
//
//
//
//        public MyViewHolder(View view) {
//            super(view);
//            name = itemView.findViewById(R.id.nameTraineeCard);
//            number=itemView.findViewById(R.id.idNumber);
//        }
//
//
//    }
//
//    public OldTraineeAdapter(Context context, List<TraineeModel> traineeList, CustomItemClickListener listener,
//                             OldTraineeAdapter.MyRecyclerViewListener myRecyclerViewListener) {
//        this.context = context;
//        this.traineeList = traineeList;
//        this.listener = listener;
//        this.myRecyclerViewListener = myRecyclerViewListener ;
//
//    }
//
//    public List<TraineeModel> getItems() {
//        return traineeList;
//    }
//
//    @Override
//    public OldTraineeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.new_trainee_item, parent, false);
//
//        final OldTraineeAdapter.MyViewHolder mViewHolder = new OldTraineeAdapter.MyViewHolder(itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(v, mViewHolder.getAdapterPosition());
//            }
//        });
//
//
//        return mViewHolder;
//    }
//
//
//    @Override
//    public void onBindViewHolder(OldTraineeAdapter.MyViewHolder holder, final int position) {
//
//        TraineeModel item = traineeList.get(position);
//
//        holder.name.setText(item.getName());
//        holder.number.setText(item.getMobileNumber());
//
////        holder.remove.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                myRecyclerViewListener.Check(v,position);
////            }
////        });
////        holder.name.setText(item.getUniversiy_name());
//    }
//
//    @Override
//    public int getItemCount() {
//        return traineeList.size();
//    }
//
//
//    public interface MyRecyclerViewListener {
//        public void Check(View v, int position);
//    }
//
//}

        extends BaseAdapter {

    Activity con;
    ArrayList<TrainerObject> arrayList;
    boolean selectAll;

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
        HashMap map = new HashMap();

        if (view == null) {

            row = con.getLayoutInflater().inflate(R.layout.old_trainee_item, viewGroup, false);
            viewHolder = new OldTraineeAdapter.ViewHolder();

            viewHolder.name = row.findViewById(R.id.nameTraineeCard);
            viewHolder.idNumber = row.findViewById(R.id.idNumber);
            viewHolder.addNew = row.findViewById(R.id.addNew);

            row.setTag(viewHolder);

        } else {
            row = view;
            viewHolder = (OldTraineeAdapter.ViewHolder) view.getTag();
        }

        final TrainerObject item = arrayList.get(i);
        viewHolder.name.setText(item.getTrainer_name());


        viewHolder.addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(con);
            }
        });

        return row;
    }

    public class ViewHolder {
        FontTextViewRegular name, idNumber, addNew;


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
