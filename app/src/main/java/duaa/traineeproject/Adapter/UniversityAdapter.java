package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import duaa.traineeproject.Model.University;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 18/03/18.
 */

public class UniversityAdapter  extends RecyclerView.Adapter<UniversityAdapter.MyViewHolder> {

    private List<University> specification;
    CustomItemClickListener listener;
    Context context;
    UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular remove,name ,email ,phone ;
        CircleImageView image ;



        public MyViewHolder(View view) {
            super(view);
//            edit = itemView.findViewById(R.id.edit);
//            remove=itemView.findViewById(R.id.delete);
            email= view.findViewById(R.id.emailTraineeCard);
            name = view.findViewById(R.id.nameTraineeCard);
            phone = view.findViewById(R.id.idNumber);
            image = view.findViewById(R.id.circleView);
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
        holder.name.setText(item.getUniversiy_name());
        holder.email.setText(item.getAddress());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.error_image);
        requestOptions.error(R.drawable.error_image);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(holder.image.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.PHOTO_PATH+item.getLogo()).thumbnail(.1f).into(holder.image);

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
