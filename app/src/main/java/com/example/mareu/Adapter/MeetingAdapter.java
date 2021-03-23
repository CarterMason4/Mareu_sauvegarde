package com.example.mareu.Adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mareu.Events.DeleteMeetingEvent;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private List<Meeting> meetings;
    private List<Meeting> copie;

    public MeetingAdapter(List<Meeting> meetings) {
        this.meetings = meetings;
        this.copie = new ArrayList<>(meetings);
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View meetingView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_item_list, parent, false);
        return new MeetingViewHolder(meetingView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);

        Glide.with(holder.meetingColor.getContext())
                .load(meeting.getCouleur())
                .fitCenter()
                .into(holder.meetingColor);

        holder.meetingName.setText(meeting.getName());

        holder.entrants.setText(meeting.getEntrants());

        holder.deleteButton.setOnClickListener(view ->
                EventBus.getDefault().post(
                new DeleteMeetingEvent(meeting)));
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

    public void updateMeetings(List<Meeting> meetings) {
        if(meetings != null && !meetings.isEmpty()) {
            this.meetings.clear();
            this.meetings.addAll(meetings);
            notifyDataSetChanged();
        }
    }
}