package fr.esilv.s8.final_application.adapters;

import android.view.View;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import fr.esilv.s8.final_application.R;
import fr.esilv.s8.final_application.models.VideoResponse;
import fr.esilv.s8.final_application.interfaces.onVideoSelectedListener;
import fr.esilv.s8.final_application.viewholders.VideosViewHolder;

public class VideosAdapter extends RecyclerView.Adapter<VideosViewHolder>{

    private List<VideoResponse.ItemsBean> items;
    private onVideoSelectedListener onVideoSelectedListener;

    public VideosAdapter(List<VideoResponse.ItemsBean> objects){
        this.items=objects;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_video,parent,false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        //return items.size();
        return items != null ? items.size() : 0;
    }
}
