package com.example.mareu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.Di;
import com.example.mareu.Events.DeleteMeetingEvent;
import com.example.mareu.Model.Reunion;
import com.example.mareu.R;
import com.example.mareu.Utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> implements Filterable {

    private MeetingApiService apiService;

    private List<Reunion> reunions = new ArrayList<>();
    private List<Reunion> copie;

    public MeetingAdapter(List<Reunion> reunions) {
        this.reunions = reunions;
        copie = new ArrayList<>(reunions);
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View meetingView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_item_list, parent, false);

            apiService = Di.getMeetingApiService();


        return new MeetingViewHolder(meetingView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Reunion reunion = reunions.get(position);

        Glide.with(holder.meetingColor.getContext())
                .load(reunion.getCouleur())
                .fitCenter()
                .into(holder.meetingColor);

        holder.meetingName.setText(reunion.getName());

        holder.entrants.setText(reunion.getEntrants());

        holder.deleteButton.setOnClickListener(view ->
                EventBus.getDefault().post(
                new DeleteMeetingEvent(reunion)));
    }

    @Override
    public int getItemCount() {
        return reunions.size();
    }


    class MeetingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.meeting_color)
        ImageView meetingColor;

        @BindView(R.id.meeting_name)
        TextView meetingName;

        @BindView(R.id.entrants)
        TextView entrants;

        @BindView(R.id.deleteButton)
        ImageButton deleteButton;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence filtre) {

            List<Reunion> listeFiltree = new ArrayList<>();

            if(filtre == null || filtre.length() == 0) {

                listeFiltree.addAll(copie);

            } else {

                String filterPattern = filtre.toString().toLowerCase();

                for(Reunion reunion : reunions) {
                    if(Character.isAlphabetic(filterPattern.charAt(0))) {
                        if(reunion.getRoom().toLowerCase().contains(filterPattern)) {
                            listeFiltree.add(reunion);
                        }
                    } else if(Character.isDigit(filterPattern.charAt(0))) {
                        if(reunion.getTime().toLowerCase().contains(filterPattern)) {
                            listeFiltree.add(reunion);
                        }
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = listeFiltree;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            reunions.clear();
            reunions.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
