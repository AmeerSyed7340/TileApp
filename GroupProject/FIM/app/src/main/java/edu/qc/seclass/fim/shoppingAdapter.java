package edu.qc.seclass.fim;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class shoppingAdapter extends RecyclerView.Adapter<shoppingAdapter.MyViewHolder>{
    public Context context;
    public ArrayList<ShoppingCart> listofItems;

    public shoppingAdapter(Context context, ArrayList<ShoppingCart>listofItems){
        this.context= context;
        this.listofItems= listofItems;
    }

    @NonNull
    @Override
    public shoppingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.individualitem,parent,false);
        return new shoppingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shoppingAdapter.MyViewHolder holder, int position) {
        ShoppingCart scart= listofItems.get(position);
        holder.bind(scart);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ModifyShoppingCart.class);
                intent.putExtra("LIST_PRODUCTS",listofItems);
                view.getContext().startActivity(intent);
            }
        });

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView prod, quant;
        EditText inputquan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prod= itemView.findViewById(R.id.product1);
            quant= itemView.findViewById(R.id.quantity1);
            inputquan = itemView.findViewById(R.id.quatityinput2);

        }
        public void bind(ShoppingCart sch){
            String para= sch.getProId();
            prod.setText(para);
            quant.setText( String.valueOf(sch.getQuan(para)));
            inputquan.setText(String.valueOf(sch.getQuan(para)));

        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void clear(){
        listofItems.clear();
        notifyDataSetChanged();
    }
}