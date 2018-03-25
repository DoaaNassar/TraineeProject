package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.JavaObject.Specification;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class ShowUniversityAdapter extends RecyclerView.Adapter<ShowUniversityAdapter.MyViewHolder> {

    private List<University> specification;
    CustomItemClickListener listener;
    Context context;
    ShowUniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox name;



        public MyViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.name);
        }


    }

    public ShowUniversityAdapter(Context context, List<University> specification, CustomItemClickListener listener,
                                 ShowUniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.specification = specification;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener;

    }

    public List<University> getItems() {
        return specification;
    }

    @Override
    public ShowUniversityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.show_university, parent, false);

        final ShowUniversityAdapter.MyViewHolder mViewHolder = new ShowUniversityAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(ShowUniversityAdapter.MyViewHolder holder, final int position) {
        University item = specification.get(position);

//        holder.remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRecyclerViewListener.RemoveImage(v,position);
//            }
//        });
        holder.name.setText(item.getUniversiy_name());
    }

    @Override
    public int getItemCount() {
        return specification.size();
    }

    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }


}
