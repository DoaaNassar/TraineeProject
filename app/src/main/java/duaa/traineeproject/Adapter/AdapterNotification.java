package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.NotificationModel;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 02/05/18.
 */

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {

    private List<NotificationModel> notificationList;
    CustomItemClickListener listener;
    Context context;
    AdapterNotification.MyRecyclerViewListener myRecyclerViewListener;
    List<NotificationModel> checkList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name;
        CheckBox checkBox;


        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.nameTraineeCard);
            checkBox = itemView.findViewById(R.id.check);
//            number=itemView.findViewById(R.id.idNumber);
//            addNew = itemView.findViewById(R.id.addNew);
//            training = itemView.findViewById(R.id.training);
        }


    }

    public AdapterNotification(Context context, List<NotificationModel> notificationList,List<NotificationModel>checkList, CustomItemClickListener listener
            , MyRecyclerViewListener myRecyclerViewListener ) {
        this.context = context;
        this.notificationList = notificationList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener;
        this.checkList = checkList;

    }

    public List<NotificationModel> getItems() {
        return notificationList;
    }

    @Override
    public AdapterNotification.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);

        final AdapterNotification.MyViewHolder mViewHolder = new AdapterNotification.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(final AdapterNotification.MyViewHolder holder, final int position) {

        final NotificationModel item = notificationList.get(position);
        if (checkList.contains(item))
            holder.checkBox.setChecked(true);
        else
            holder.checkBox.setChecked(false);
        
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                myRecyclerViewListener.check(item, position);

            }
        });

//        holder.name.setText(item.getTrainee_name());
//        holder.number.setText(item.getMobile());

//        holder.addNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRecyclerViewListener.AddNew(v,position);
//
//            }
//        });
//
//        holder.training.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRecyclerViewListener.Training(v,position);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    public interface MyRecyclerViewListener {
        public void check(NotificationModel v, int position);

    }


}
