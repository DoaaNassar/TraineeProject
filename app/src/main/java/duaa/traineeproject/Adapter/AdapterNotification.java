package duaa.traineeproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.NotificationModel;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 02/05/18.
 */

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.MyViewHolder> {

    private List<TraineeModel> notificationList;
    CustomItemClickListener listener;
    Context context;
    AdapterNotification.MyRecyclerViewListener myRecyclerViewListener;
    Map<TraineeModel, TraineeModel> checkList = new HashMap<>();


    public class MyViewHolder extends RecyclerView.ViewHolder {

        FontTextViewRegular name, collage, date, place, approve, disapprove;
        CheckBox checkBox;
        CircleImageView universityImage;


        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            checkBox = itemView.findViewById(R.id.check);
            approve = itemView.findViewById(R.id.approve);
            disapprove = itemView.findViewById(R.id.disapprove);
            collage = itemView.findViewById(R.id.collage);
            date = itemView.findViewById(R.id.date);
            place = itemView.findViewById(R.id.place);
            universityImage = itemView.findViewById(R.id.image);

        }


    }

    public AdapterNotification(Context context, List<TraineeModel> notificationList, CustomItemClickListener listener
            , MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.notificationList = notificationList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener;

    }

    public List<TraineeModel> getItems() {
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

        final TraineeModel item = notificationList.get(position);
        holder.name.setText(item.getTrainee_name());
        if (checkList.containsKey(item)) {
            holder.checkBox.setChecked(true);
        } else
            holder.checkBox.setChecked(false);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkList.containsKey(item)) {
                    checkList.remove(item);
                    holder.checkBox.setChecked(false);
                    notifyDataSetChanged();
                } else {
                    checkList.put(item, item);
                    holder.checkBox.setChecked(true);
                    notifyDataSetChanged();

                }
            }
        });

        holder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Approve(item,position);
            }
        });

        holder.disapprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Disapprove(item,position);
            }
        });

//
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    public interface MyRecyclerViewListener {
        public void Approve(TraineeModel v,  int position);
        public void Disapprove(TraineeModel v,  int position);


    }

    public Map getData() {
        notifyDataSetChanged();
        return checkList;

    }

    public void selectAll(){
        checkList.clear();
        for (TraineeModel traineeModel : notificationList)
        checkList.put(traineeModel,traineeModel);
        notifyDataSetChanged();
    }


}
