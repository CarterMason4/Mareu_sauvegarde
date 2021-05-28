package com.example.mareu.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mareu.Adapter.MeetingAdapter;
import com.example.mareu.Api.MeetingApiService;
import com.example.mareu.DI.DI;
import com.example.mareu.Events.DeleteMeetingEvent;
import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class MainActivity extends AppCompatActivity {

    private MeetingApiService apiService;
    private List<Meeting> meetings = new ArrayList<>();
    private MeetingAdapter adapter;

    // TODO Remplacer le MultiCompleteTextView par un EdiText avec petit FAB
    // TODO La personne ajoute l'intertevenant en appuyant sur le FAB
    // TODO Avec un petit RecyclerView qui montre les emails ajoutés.


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

        apiService = DI.getMeetingApiService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.filtrer);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilter(newText);
                return false;
            }
        });

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                fab.setEnabled(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                fab.setEnabled(true);
                return true;
            }
        });

        return true;
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
        apiService.deleteMeeting(event.meeting);
        initList();
        Utils.makeToast(getApplicationContext(), getString(R.string.reunion_supprimee));
    }

    public void test() {
        System.out.print("Lol tu me fais rire !");
    }


    @OnClick(R.id.fab)
    public void onClick() {
        startActivity(new Intent(this, AddMeetingActivity.class));
    }


    private void applyFilter(String text) {
        List<Meeting> searchResult = apiService.filterMeetings(text);
        adapter.updateMeetings(searchResult);
    }

    private void initList() {
        meetings = apiService.getAllMeetings();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MeetingAdapter(meetings);
        recyclerView.setAdapter(adapter);
    }
}