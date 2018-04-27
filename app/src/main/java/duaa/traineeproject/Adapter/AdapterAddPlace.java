package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.JavaObject.PartObject;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 24/04/18.
 */

public class AdapterAddPlace  extends RecyclerView.Adapter<AdapterAddPlace.MyViewHolder> {

    private List<PartObject> place;
    CustomItemClickListener listener;
    Context context;
    AdapterAddPlace.MyRecyclerViewListener myRecyclerViewListener;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView remove;
        public FontTextViewRegular name;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            remove  = itemView.findViewById(R.id.remove);
        }


    }

    public AdapterAddPlace(Context context, List<PartObject> place, CustomItemClickListener listener,
                           AdapterAddPlace.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.place = place;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener;

    }

    public List<PartObject> getItems() {
        return place;
    }

    @Override
    public AdapterAddPlace.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spec_layout, parent, false);

        final AdapterAddPlace.MyViewHolder mViewHolder = new AdapterAddPlace.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(AdapterAddPlace.MyViewHolder holder, final int position) {
        PartObject item = place.get(position);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.RemoveImage(v,position);
            }
        });
        holder.name.setText(item.getPartment_name());
    }

    @Override
    public int getItemCount() {
        return place.size();
    }

    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
