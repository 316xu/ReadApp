package hust.xujifa.readapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hust.xujifa.readapp.R;
import hust.xujifa.readapp.activity.BookinfoActivity;
import hust.xujifa.readapp.module.BookSimple;

/**
 * Created by xujifa on 2016/1/23.
 */

public class BooklistAdapter extends RecyclerView.Adapter<BooklistAdapter.BooklistViewHolder> {

    private List<BookSimple>booklists;
    private Context context;
    public BooklistAdapter(List<BookSimple> booklists,Context context){
        this.context=context;
        this.booklists=booklists;
    }


    @Override
    public BooklistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_simplebook,
                parent,false);

        return new BooklistViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BooklistViewHolder holder, final int position) {
        holder.text_title.setText(booklists.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, BookinfoActivity.class);
                intent.putExtra("bookcode",booklists.get(position).getBookcode());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booklists.size();
    }

    public class BooklistViewHolder extends RecyclerView.ViewHolder{
        private TextView text_title;
        private View itemView;
        public BooklistViewHolder(View itemView) {

            super(itemView);
            this.itemView=itemView;
            text_title= (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
