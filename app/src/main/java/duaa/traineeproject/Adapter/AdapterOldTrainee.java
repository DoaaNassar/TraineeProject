package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.TraineeModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 21/04/18.
 */

public class AdapterOldTrainee extends RecyclerView.Adapter<AdapterOldTrainee.MyViewHolder> {

    private List<TraineeModel> traineeList;
    CustomItemClickListener listener;
    Context context;
    AdapterOldTrainee.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name , number , addNew ,training ;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.nameTraineeCard);
            number=itemView.findViewById(R.id.idNumber);
            addNew = itemView.findViewById(R.id.addNew);
            training = itemView.findViewById(R.id.training);
        }


    }

    public AdapterOldTrainee(Context context, List<TraineeModel> traineeList, CustomItemClickListener listener,
                             AdapterOldTrainee.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.traineeList = traineeList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<TraineeModel> getItems() {
        return traineeList;
    }

    @Override
    public AdapterOldTrainee.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.old_trainee_item, parent, false);

        final AdapterOldTrainee.MyViewHolder mViewHolder = new AdapterOldTrainee.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(AdapterOldTrainee.MyViewHolder holder, final int position) {

        TraineeModel item = traineeList.get(position);

        holder.name.setText(item.getName());
        holder.number.setText(item.getMobileNumber());

        holder.addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.AddNew(v,position);

            }
        });

        holder.training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Training(v,position);

            }
        });
    }

    @Override
    public int getItemCount() {
        return traineeList.size();
    }


    public interface MyRecyclerViewListener {
        public void AddNew(View v, int position);
        public void Training(View v, int position);

    }

}
