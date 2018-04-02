package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.Model.TrainerModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private List<PlaceModel> placeList;
    CustomItemClickListener listener;
    Context context;
    UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name , number;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
        }


    }

    public PlaceAdapter(Context context, List<PlaceModel> placeList, CustomItemClickListener listener,
                          UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.placeList = placeList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<PlaceModel> getItems() {
        return placeList;
    }

    @Override
    public PlaceAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);

        final PlaceAdapter.MyViewHolder mViewHolder = new PlaceAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(PlaceAdapter.MyViewHolder holder, final int position) {

        PlaceModel item = placeList.get(position);

        holder.name.setText(item.getName());
        holder.number.setText(item.getNumber());

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
        return placeList.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
