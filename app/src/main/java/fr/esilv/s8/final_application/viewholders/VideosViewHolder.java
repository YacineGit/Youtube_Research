package fr.esilv.s8.final_application.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.esilv.s8.final_application.R;
import fr.esilv.s8.final_application.interfaces.onVideoSelectedListener;
import fr.esilv.s8.final_application.models.VideoResponse;

public class VideosViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView video_name;
    private ImageView image;

    private onVideoSelectedListener onVideoSelectedListener;

    public VideosViewHolder(View itemView){

        super(itemView);
        name = (TextView) itemView.findViewById(R.id.title);
        video_name = (TextView) itemView.findViewById(R.id.description);
        image = (ImageView) itemView.findViewById(R.id.imageVideo);
    }

    public void bind (final VideoResponse.ItemsBean video) {
        name.setText(video.getSnippet().getTitle());
        video_name.setText(video.getSnippet().getDescription());
        Picasso.with(itemView.getContext()).load(video.getSnippet().getThumbnails().getMedium().getUrl()).into(image);

    }
}
