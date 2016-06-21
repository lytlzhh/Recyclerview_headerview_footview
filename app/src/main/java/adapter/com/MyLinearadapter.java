package adapter.com;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.llw.myapplication.Bean;
import com.example.llw.myapplication.MainActivity;
import com.example.llw.myapplication.R;

import java.util.List;

/**
 * Created by llw on 2016/6/21.
 */
public class MyLinearadapter extends RecyclerView.Adapter<MyLinearadapter.ViewHolder> implements View.OnClickListener {
    private static final int TYPE_HEADER = 0, TYPE_ITEM = 1, TYPE_FOOT = 2;
    public List<Bean> list = null;
    public Context context = null;
    public String[] data = null;

    public View headerview = null;
    public View footview = null;

    public int headersize = 0;
    public int footsize = 0;


    public boolean isaddheader = false;
    public boolean isaddfoot = false;

    public interface OnRecyclerviewListener {
        public void Onitem(View view);
    }

    public OnRecyclerviewListener onrecyclerviewlistener = null;

    public void getrecyclerviewlistener(OnRecyclerviewListener onrecyclerviewlistener) {
        this.onrecyclerviewlistener = onrecyclerviewlistener;
    }

    public MyLinearadapter(MainActivity mainActivity, List<Bean> list) {
        this.list = list;
        this.context = mainActivity;
    }

    public void addheaderview(View view) {
        this.headerview = view;
        headersize = 1;
        isaddheader = true;
    }

    public void addfootview(View view) {
        this.footview = view;
        footsize = 1;
        isaddfoot = true;

    }

    @Override
    public MyLinearadapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int type = 0;
        View view = null;
        switch (viewType) {
            case TYPE_HEADER:
                view = headerview;
                break;
            case TYPE_ITEM:
                view = LayoutInflater.from(context).inflate(R.layout.body_item, parent, false);
                break;
            case TYPE_FOOT:
                view = footview;
                break;
        }
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyLinearadapter.ViewHolder holder, int position) {
        if (position > 0 && position < getItemCount() - 1) {
            holder.textViewone.setText(list.get(position).getSrt());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = TYPE_ITEM;
        if (headersize == 1 && position == 0) {
            type = TYPE_HEADER;
        } else if (footsize == 1 && position == getItemCount() - 1) {
            //最后一个位置
            type = TYPE_FOOT;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onClick(View v) {
        onrecyclerviewlistener.Onitem(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewone;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewone = (TextView) itemView.findViewById(R.id.bodyitem_id);
        }
    }
}
