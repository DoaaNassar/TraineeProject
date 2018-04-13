package duaa.traineeproject.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Model.Trainer;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontEditTextViewRegular;
import duaa.traineeproject.view.FontTextViewRegular;

/**
 * Created by AL-Qema on 28/03/18.
 */

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {

    private List<Trainer> trainerList;
    CustomItemClickListener listener;
    Context context;
    UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        FontTextViewRegular remove,edit,name,email;


        public MyViewHolder(View view) {
            super(view);
            edit = itemView.findViewById(R.id.edit);
            remove=itemView.findViewById(R.id.delete);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }


    }

    public TrainerAdapter(Context context, List<Trainer> trainerList, CustomItemClickListener listener,
                          UniversityAdapter.MyRecyclerViewListener myRecyclerViewListener) {
        this.context = context;
        this.trainerList = trainerList;
        this.listener = listener;
        this.myRecyclerViewListener = myRecyclerViewListener ;

    }

    public List<Trainer> getItems() {
        return trainerList;
    }

    @Override
    public TrainerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_new_trainer, parent, false);

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
        Trainer item = trainerList.get(position);
        holder.email.setText(item.getEmail());
        holder.name.setText(item.getName());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("هل أنت متاكد ؟");
                builder.setCancelable(false);
                builder.setPositiveButton("نعم",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                trainerList.remove(trainerList.get(position));
                                notifyDataSetChanged();
                            }
                        });
                
                builder.setNegativeButton("لا",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return trainerList.size();
    }


    public interface MyRecyclerViewListener {
        public void RemoveImage(View v, int position);
    }

}
