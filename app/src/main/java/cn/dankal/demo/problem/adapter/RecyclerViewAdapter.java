package cn.dankal.demo.problem.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import cn.dankal.demo.R;

/**
 * Created by root on 18-8-29.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> list;
    public static int ImgVisibi = View.GONE;
    public RecyclerViewAdapter(List<String> data) {
        this.list = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_problem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // Log.v("aaaa","bindvie");
        holder.mText.setText(list.get(position));
        if(ImgVisibi == View.VISIBLE)
            holder.dotImg.setVisibility(View.VISIBLE);
        else{
            holder.dotImg.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mText;
        ImageView dotImg;
        public ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.item_pro);
            dotImg = itemView.findViewById(R.id.dot_img);
        }
    }
    public static void ImgVisibility(int ImgVis){
        ImgVisibi = ImgVis;
    }
}
