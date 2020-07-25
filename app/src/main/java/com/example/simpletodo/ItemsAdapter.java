package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Resposnible for displaying data from the model into a row in  the recyvler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListner{
        void onItemLongClicked(int position);
    }
    List<String> items;

    OnLongClickListner longClickListner;

    public ItemsAdapter(List<String> items,OnLongClickListner longClickListner) {
        this.items = items;
        this.longClickListner = longClickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //use layout inflator to inflate a view

       View todoView =  LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        //wrap it inside a view Holder and return it
        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item at the position
            String item = items.get(position);
        //bind the item into the specific view holder
            holder.bind(item);
    }

    //simply tells RV to how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy acess to view that represent each row of the List
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the  view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //notify the listner which position was long pressed.
                        longClickListner.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
