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

public class AdapterNewTrainee extends RecyclerView.Adapter<AdapterNewTrainee.MyViewHolder> {

    private List<TraineeModel> traineeList;
    CustomItemClickListener listener;
    Context context;
    AdapterNewTrainee.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name , number , end;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.nameTraineeCard);
            number=itemView.findViewById(R.id.idNumber);
            end = itemView.findViewById(R.id.delete);
        }


    }

    public AdapterNewTrainee(Context context, List<TraineeModel> traineeList, CustomItemClickListener listener,
                             AdapterNewTrainee.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.traineeList = traineeList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<TraineeModel> getItems() {
        return traineeList;
    }

    @Override
    public AdapterNewTrainee.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_trainee_item, parent, false);

        final AdapterNewTrainee.MyViewHolder mViewHolder = new AdapterNewTrainee.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });

        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(AdapterNewTrainee.MyViewHolder holder, final int position) {

        TraineeModel item = traineeList.get(position);

        holder.name.setText(item.getName());
        holder.number.setText(item.getMobileNumber());

        holder.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Delete(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return traineeList.size();
    }


    public interface MyRecyclerViewListener {
        public void Delete(View v, int position);
        public void Edit (View v, int position);
    }

}
