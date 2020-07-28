package com.example.traveljournal.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.R;
import com.example.traveljournal.Trip.Trip;
import com.example.traveljournal.Trip.TripListAdapter;
import com.example.traveljournal.Trip.TripViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TripViewModel mTripViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_triplist, container, false);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        final TripListAdapter adapter = new TripListAdapter(this);
        recyclerView.setAdapter(adapter);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.

        mTripViewModel.getAllTrips().observe(getViewLifecycleOwner(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable final List<Trip> trips) {
                // Update the cached copy of the words in the adapter.
                adapter.setTrips(trips);
            }
        });

        return root;

    }

}

