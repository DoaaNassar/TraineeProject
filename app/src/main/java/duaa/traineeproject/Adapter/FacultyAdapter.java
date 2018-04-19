package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import duaa.traineeproject.Constants;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.Faculty;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 16/04/18.
 */

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.MyViewHolder> {

    private List<Faculty> faculty;
    CustomItemClickListener listener;
    Context context;
    FacultyAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular remove,name ,email ,phone ;
        CircleImageView image ;



        public MyViewHolder(View view) {
            super(view);
//            edit = itemView.findViewById(R.id.edit);
//            remove=itemView.findViewById(R.id.delete);
            name = view.findViewById(R.id.name);

        }


    }

    public FacultyAdapter(Context context, List<Faculty> faculty, CustomItemClickListener listener,
                             FacultyAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.faculty = faculty;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<Faculty> getItems() {
        return faculty;
    }

    @Override
    public FacultyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.faculty_item, parent, false);

        final FacultyAdapter.MyViewHolder mViewHolder = new FacultyAdapter.MyViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());
            }
        });


        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(FacultyAdapter.MyViewHolder holder, final int position) {
        Faculty item = faculty.get(position);
        holder.name.setText(item.getCollage_name());
//        holder.email.setText(item.ge());

    }

    @Override
    public int getItemCount() {
        return faculty.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
