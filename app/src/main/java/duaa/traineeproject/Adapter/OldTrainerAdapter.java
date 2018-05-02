package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 13/04/18.
 */

public class OldTrainerAdapter extends RecyclerView.Adapter<OldTrainerAdapter.MyViewHolder> {

    private List<Trainer> placeList;
    CustomItemClickListener listener;
    Context context;
    OldTrainerAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name , email ,enable,edit;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            enable = itemView.findViewById(R.id.enable);
            edit = itemView.findViewById(R.id.edit);

        }


    }

    public OldTrainerAdapter(Context context, List<Trainer> placeList, CustomItemClickListener listener,
                        OldTrainerAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.placeList = placeList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<Trainer> getItems() {
        return placeList;
    }

    @Override
    public OldTrainerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_old_trainer, parent, false);

        final OldTrainerAdapter.MyViewHolder mViewHolder = new OldTrainerAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(OldTrainerAdapter.MyViewHolder holder, final int position) {

        Trainer item = placeList.get(position);

        holder.name.setText(item.getTrainer_name());
        holder.email.setText(item.getEmail());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Edit(v,position);
            }
        });

        holder.enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.Enable(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }


    public interface MyRecyclerViewListener {
        public void Enable(View v, int position);
        public void Edit(View v, int position);


    }

}
