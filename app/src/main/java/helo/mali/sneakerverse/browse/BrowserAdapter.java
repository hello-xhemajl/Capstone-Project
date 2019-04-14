package helo.mali.sneakerverse.browse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.sneakers.Sneakers;

public class BrowserAdapter extends RecyclerView.Adapter<BrowserAdapter.SneakersVh> {
    private List<Sneakers> sneakers;

    SneakersVh.OnSneakersEventsListener onSneakersEventsListener;

    public BrowserAdapter(SneakersVh.OnSneakersEventsListener onSneakersEventsListener) {
        this.onSneakersEventsListener = onSneakersEventsListener;
    }

    @NonNull
    @Override
    public SneakersVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View sneakersView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sneakers, viewGroup, false);
        return new SneakersVh(sneakersView, onSneakersEventsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SneakersVh vh, int position) {
        Sneakers sneakers = this.sneakers.get(position);
        vh.sneakersImageView.setContentDescription(sneakers.getImageContentDescription());
        Picasso.get()
                .load(sneakers.getImageUri())
                .into(vh.sneakersImageView);
    }

    @Override
    public int getItemCount() {
        return sneakers != null ? sneakers.size(): 0;
    }

    public void setSneakers(List<Sneakers> sneakers){
        this.sneakers = sneakers;
        notifyDataSetChanged();
    }

    public static class SneakersVh extends RecyclerView.ViewHolder{

        @BindView(R.id.sneakers_image_view)
        ImageView sneakersImageView;

        OnSneakersEventsListener listener;

        public SneakersVh(@NonNull View itemView, OnSneakersEventsListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSneakersClicked(getAdapterPosition());
                }
            });
        }

        public interface OnSneakersEventsListener {
            void onSneakersClicked(int position);
        }
    }



}
