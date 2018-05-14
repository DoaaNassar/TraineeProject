package duaa.traineeproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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

    private List<University> specialization;
    CustomItemClickListener listener;
    Context context;
    UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular remove,name ,email ,phone ,address,mobile,edit;
        CircleImageView image ;



        public MyViewHolder(View view) {
            super(view);
            edit = itemView.findViewById(R.id.edit);
            remove=itemView.findViewById(R.id.delete);
            email= view.findViewById(R.id.email);
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address);
            mobile= view.findViewById(R.id.mobileNumber);
            phone = view.findViewById(R.id.idNumber);
            image = view.findViewById(R.id.circleView);
        }


    }

    public UniversityAdapter(Context context, List<University> specialization, CustomItemClickListener listener,
                             UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.specialization = specialization;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<University> getItems() {
        return specialization;
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
        University item = specialization.get(position);
        holder.name.setText(item.getUniversiy_name());
//        holder.email.setText(item.getAddress());
        holder.address.setText(item.getAddress());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholdertest);
        requestOptions.error(R.drawable.placeholdertest);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(holder.image.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.PHOTO_PATH+item.getLogo()).thumbnail(.1f).into(holder.image);


        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewListener.RemoveImage(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return specialization.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
