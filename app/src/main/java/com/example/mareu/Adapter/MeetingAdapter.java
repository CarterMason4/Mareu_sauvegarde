package com.example.mareu.Adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.Di;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private MeetingApiService apiService;

    private List<Meeting> meetings = new ArrayList<>();

    public MeetingAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
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
        Meeting meeting = meetings.get(position);

        Glide.with(holder.meetingColor.getContext())
                .load(meeting.getColor())
                .fitCenter()
                .into(holder.meetingColor);

        holder.entrants.setText(meeting.getEntrants());
        // TODO Il va falloir construire le chaîne de caractères qui constituera le nom du meeting.
    }

    @Override
    public int getItemCount() {
        return meetings.size();
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
}
