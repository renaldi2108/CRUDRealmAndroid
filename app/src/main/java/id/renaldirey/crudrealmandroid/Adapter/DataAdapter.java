package id.renaldirey.crudrealmandroid.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.renaldirey.crudrealmandroid.Model.DataModel;
import id.renaldirey.crudrealmandroid.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private ArrayList<DataModel> data;

    public DataAdapter(ArrayList<DataModel> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtnama)
        TextView nama;
        @BindView(R.id.txtemail)
        TextView email;
        @BindView(R.id.txtid)
        TextView id;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final DataModel datamodel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(datamodel);
                }
            });
        }
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.id.setText(Integer.toString(data.get(position).getId()));
        holder.nama.setText(data.get(position).getNama());
        holder.email.setText(data.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(DataModel item);
    }
}
