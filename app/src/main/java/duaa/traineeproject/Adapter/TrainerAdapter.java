package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.TrainerModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;

/**
 * Created by AL-Qema on 28/03/18.
 */

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {

    private List<TrainerModel> trainerList;
    CustomItemClickListener listener;
    Context context;
    UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontEditTextViewRegular remove,edit;


        public MyViewHolder(View view) {
            super(view);
//            edit = itemView.findViewById(R.id.edit);
//            remove=itemView.findViewById(R.id.delete);
        }


    }

    public TrainerAdapter(Context context, List<TrainerModel> trainerList, CustomItemClickListener listener,
                             UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.trainerList = trainerList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<TrainerModel> getItems() {
        return trainerList;
    }

    @Override
    public TrainerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);

        final TrainerAdapter.MyViewHolder mViewHolder = new TrainerAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(TrainerAdapter.MyViewHolder holder, final int position) {
//        TrainerObject item = specification.get(position);

//        holder.remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRecyclerViewListener.RemoveImage(v,position);
//            }
//        });
//        holder.name.setText(item.getUniversiy_name());
    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
