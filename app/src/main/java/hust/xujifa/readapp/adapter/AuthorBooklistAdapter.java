package hust.xujifa.readapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import hust.xujifa.readapp.R;

/**
 * Created by xujifa on 2016/1/25.
 */
public class AuthorBooklistAdapter extends RecyclerView.Adapter<AuthorBooklistAdapter.MyHolderView> {


    Context context;
    List<String>books;
    public AuthorBooklistAdapter(List<String>books, Context context){
        this.books=books;
        this.context=context;
    }


    @Override
    public MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=  LayoutInflater.from(context).inflate(R.layout.listitem_simplebook,parent,false);
        return new MyHolderView(v);
    }

    @Override
    public void onBindViewHolder(MyHolderView holder, int position) {
        holder.title.setText(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder{
        TextView title;
        public MyHolderView(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.text_title);
        }
    }
}
