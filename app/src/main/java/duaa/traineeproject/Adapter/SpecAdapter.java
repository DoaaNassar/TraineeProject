package duaa.traineeproject.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 17/03/18.
 */

public class SpecAdapter  extends RecyclerView.Adapter<SpecAdapter.MyViewHolder> {

    private List<String> specialization;
    CustomItemClickListener listener;
    Context context;
    MyRecyclerViewListener myRecyclerViewListener;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView remove;
        public FontTextViewRegular name;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
            remove  = itemView.findViewById(R.id.remove);
        }


    }

    public SpecAdapter(Context context, List<String> specialization, CustomItemClickListener listener,
                       MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.specialization = specialization;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener;

    }

    public List<String> getItems() {
        return specialization;
    }

    @Override
    public SpecAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spec_layout, parent, false);

        final SpecAdapter.MyViewHolder mViewHolder = new SpecAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(SpecAdapter.MyViewHolder holder, final int position) {
        String item = specialization.get(position);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.RemoveImage(v,position);
            }
        });
        holder.name.setText(item);
    }

    @Override
    public int getItemCount() {
        return specialization.size();
    }

    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
