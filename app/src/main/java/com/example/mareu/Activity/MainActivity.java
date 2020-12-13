package com.example.mareu.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mareu.Adapter.MeetingAdapter;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.Di;
import com.example.mareu.Events.DeleteMeetingEvent;
import com.example.mareu.Model.Reunion;
import com.example.mareu.R;
import com.example.mareu.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.view.Menu;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.Utils.Utils.*;

public class MainActivity extends AppCompatActivity {

    private MeetingApiService apiService;
    private List<Reunion> reunions = new ArrayList<>();
    private MeetingAdapter adapter;


     @BindView(R.id.list_meetings)
     RecyclerView recyclerView;

     @BindView(R.id.toolbar)
     Toolbar toolbar;

     @BindView(R.id.fab)
     FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        apiService = Di.getMeetingApiService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete_all) {

            apiService.deleteAllMeetings();
            adapter.notifyDataSetChanged();
            return true;

        } else if(id == R.id.filter_date) {

            return true;
        } else if(id == R.id.filter_room) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void deleteMeeting(DeleteMeetingEvent event) {
        apiService.deleteMeeting(event.reunion);
        initList();
    }

    private void initList() {
        reunions = apiService.getAllMeetings();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingAdapter(reunions);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    void onClick() {
        startActivity(new Intent(this, AddMeetingActivity.class));
    }

}