package com.example.traveljournal.Trip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.traveljournal.Fragments.HomeFragment;
import com.example.traveljournal.R;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

   class TripViewHolder extends RecyclerView.ViewHolder {
       private final TextView tripName;
       private final TextView tripDestination;
       private final TextView tripPrice;

       private TripViewHolder(View itemView) {
           super(itemView);
           tripName = itemView.findViewById(R.id.tripName);
           tripDestination = itemView.findViewById(R.id.tripDestination);
           tripPrice = itemView.findViewById(R.id.tripPrice);
       }
   }

   private final LayoutInflater mInflater;
   private List<Trip> mTrips; // Cached copy of trips

   public TripListAdapter(HomeFragment context) { mInflater = LayoutInflater.from(context.getContext()); }

   @Override
   public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
       return new TripViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(TripViewHolder holder, int position) {
       if (mTrips != null) {
           Trip current = mTrips.get(position);
           holder.tripName.setText(current.getTrip());
           holder.tripDestination.setText(current.getDestination());
           holder.tripPrice.setText(current.getPrice() + "$");
       } else {
           // Covers the case of data not being ready yet.
           holder.tripName.setText("No Trip");
       }
   }

   public void setTrips(List<Trip> trips){
       mTrips = trips;
       notifyDataSetChanged();
   }

   // getItemCount() is called many times, and when it is first called,
   // mWords has not been updated (means initially, it's null, and we can't return null).
   @Override
   public int getItemCount() {
       if (mTrips != null)
           return mTrips.size();
       else return 0;
   }
}