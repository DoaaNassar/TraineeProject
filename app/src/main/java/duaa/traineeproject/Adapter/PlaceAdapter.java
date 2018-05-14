package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.PlaceModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 30/03/18.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private List<PlaceModel> placeList;
    CustomItemClickListener listener;
    Context context;
    PlaceAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular name  ,delete , edit ,address ;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            delete = itemView.findViewById(R.id.delete);
            edit= itemView.findViewById(R.id.edit);
            address = itemView.findViewById(R.id.address);

        }


    }

    public PlaceAdapter(Context context, List<PlaceModel> placeList, CustomItemClickListener listener,
                          PlaceAdapter.MyRecyclerViewListener myRecyclerViewListener) {
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

        holder.name.setText(item.getPlace_name());
        holder.address.setText(item.getAddress());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.RemoveImage(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
