package com.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Maps_Screen extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProvideClient;
    private static final float DEFAULT_ZOOM = 15f;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private boolean mLocationPermisionGranded = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps__screen);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String[] permisions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

        if(checkLocationPermission())
        {
            mLocationPermisionGranded = true;
            getDeviceLocation();

            mMap.setMyLocationEnabled(true);
        }

         Intent receiveIntent = this.getIntent();
            boolean b = receiveIntent.getBooleanExtra("All ok",false);  //from Problem_report_screen
            if(b)
                Toast.makeText(Maps_Screen.this, "Problem send" , Toast.LENGTH_LONG).show();


            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    MarkerOptions options = new MarkerOptions();
                    options.position(latLng);
                    mMap.clear();
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.addMarker(options);



                    Intent intent = new Intent(Maps_Screen.this , Problem_report_screen.class);
                    intent.putExtra("Latitude",latLng.latitude);
                    intent.putExtra("Longitude",latLng.longitude);
                    startActivity(intent);




            }
            });
        }
        




    private void getDeviceLocation()
    {
        mFusedLocationProvideClient = LocationServices.getFusedLocationProviderClient(Maps_Screen.this);

        try
        {
            Task location = mFusedLocationProvideClient.getLastLocation();

            location.addOnCompleteListener(new OnCompleteListener()
            {
                @Override


                public void onComplete(@NonNull Task task) {




                      if( checkLocationPermission())
                      {
                        Location currentLocation = (Location) task.getResult();
                        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);

                    }


                }
            });
        }

        catch (SecurityException e)
        {
                e.printStackTrace();

        }
    }


    private void moveCamera(LatLng latLng,float zoom)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
        MarkerOptions options = new MarkerOptions().position(latLng);
        mMap.addMarker(options);

    }






    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

            return false;
        }

        else

            {
            return true;
        }
    }



}