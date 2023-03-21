package edu.qc.seclass.fim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FloorsAdapter extends RecyclerView.Adapter<FloorsAdapter.ViewHolder>{
    Context context;
    List<Floors> floorsList;
    RecyclerView rvPrograms;
    boolean isEmployee;
    final View.OnClickListener onClickListener = new MyOnClickListener();

    public FloorsAdapter(ProductDisplayActivity context, List<Floors> floorsList, RecyclerView rvPrograms, boolean isEmployee) {
        this.context = context;
        this.floorsList = floorsList;
        this.rvPrograms = rvPrograms;
        this.isEmployee = isEmployee;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowID;
        TextView rowCategory;
        TextView rowType;
        TextView rowSize;
        TextView rowPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowID = itemView.findViewById(R.id.item_id);
            rowCategory = itemView.findViewById(R.id.catText);
            rowType = itemView.findViewById(R.id.typeText);
            rowSize = itemView.findViewById(R.id.sizeText);
            rowPrice = itemView.findViewById(R.id.priceText);
        }
    }

    public FloorsAdapter(Context context, List<Floors> floorsList, RecyclerView rvPrograms){
        this.context = context;
        this.floorsList = floorsList;
        this.rvPrograms = rvPrograms;
    }

    @NonNull
    @Override
    public FloorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item, viewGroup, false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FloorsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        Floors floor = floorsList.get(position);
        viewHolder.rowID.setText(""+floor.getId());
        viewHolder.rowCategory.setText(""+floor.getCategory());
        viewHolder.rowType.setText(" "+floor.getType());
        viewHolder.rowSize.setText(" "+floor.getSize());
        viewHolder.rowPrice.setText(" "+floor.getPrice());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Floors clicked = floorsList.get(position);
                Intent intent = new Intent(view.getContext(), ProductInfoActivity.class);
                intent.putExtra("id", clicked.getId());
                intent.putExtra("isEmployee", isEmployee);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return floorsList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvPrograms.getChildLayoutPosition(v);
            int id = floorsList.get(itemPosition).getId();
            ProductDisplayActivity pda = new ProductDisplayActivity();
            pda.openProductInfo(v, id);
        }
    }
}
