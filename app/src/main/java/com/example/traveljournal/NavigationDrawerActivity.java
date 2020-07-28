package com.example.traveljournal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.traveljournal.Fragments.AboutUsFragment;
import com.example.traveljournal.Fragments.ContactFragment;
import com.example.traveljournal.Fragments.HomeFragment;
import com.example.traveljournal.Fragments.ShareFragment;
import com.example.traveljournal.Trip.AddTripActivity;
import com.example.traveljournal.Trip.Trip;
import com.example.traveljournal.Trip.TripViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    public static final int NEW_TRIP_ACTIVITY_REQUEST_CODE = 1;
    private TextView username;
    private TextView email;
    private TripViewModel mTripViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);


        /*username = findViewById(R.id.usernameLabel);
        email = findViewById(R.id.emailLabel);

        username.setText(getIntent().getStringExtra("User"));
        email.setText(username.getText().toString().toLowerCase().trim() + "@gmail.com");*/

        openFragment(new HomeFragment());
        mTripViewModel = new ViewModelProvider(this).get(TripViewModel.class);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationDrawerActivity.this, AddTripActivity.class);
                startActivityForResult(intent, NEW_TRIP_ACTIVITY_REQUEST_CODE);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // fab.show();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String str = data.getStringExtra(AddTripActivity.EXTRA_REPLY);
            String[] splited = str.split("-");
            Trip trip = new Trip(""+splited[0], ""+splited[1], ""+splited[2], Double.parseDouble(splited[3])+0, ""+splited[4], ""+splited[5], 0+Double.parseDouble(splited[6]));
            mTripViewModel.insert(trip);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            openFragment(new HomeFragment());
            fab.show();
        } else if (id == R.id.nav_aboutus) {
            openFragment(new AboutUsFragment());
            fab.hide();
        } else if (id == R.id.nav_contact) {
            openFragment(new ContactFragment());
            fab.hide();
        } else if (id == R.id.nav_share) {
            openFragment(new ShareFragment());
            fab.hide();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openFragment(Fragment fragment) {
        // 4 steps to add dynamically a fragment inside of an activity
        // step 1: create an instance of FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // step 2: create an instance of FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // step 3: replace container content with the fragment content
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        // step 4: commit transaction
        fragmentTransaction.commit();
    }

    public void callOnClick(View view) {


        Intent callImpliciIntent = new Intent(Intent.ACTION_DIAL);
        callImpliciIntent.setData(Uri.parse("tel:0754713281"));
        startActivity(callImpliciIntent);

    }

    public void mailOnClick(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "balmaudragos@gmail.com" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Bug - Report");
        intent.putExtra(Intent.EXTRA_TEXT, "I found a bug");
        startActivity(Intent.createChooser(intent, ""));

    }

    public void locationOnClick(View view) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=Universitatea din Bucuresti"));
        startActivity(intent);

    }

}
