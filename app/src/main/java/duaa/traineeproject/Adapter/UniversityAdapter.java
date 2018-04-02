package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class UniversityAdapter  extends RecyclerView.Adapter<UniversityAdapter.MyViewHolder> {

    private List<University> specification;
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

    public UniversityAdapter(Context context, List<University> specification, CustomItemClickListener listener,
                             UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.specification = specification;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<University> getItems() {
        return specification;
    }

    @Override
    public UniversityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.university_item, parent, false);

        final UniversityAdapter.MyViewHolder mViewHolder = new UniversityAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(UniversityAdapter.MyViewHolder holder, final int position) {
        University item = specification.get(position);

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
        return specification.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
