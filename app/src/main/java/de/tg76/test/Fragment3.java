package de.tg76.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

public class Fragment3 extends Fragment {

    GoogleMap mMap;
    private static final int ERROR_Dialog_REQUEST = 9001;
    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.activity_fragment3, container, false);


        if(servicesOK()){
            return inflater.inflate(R.layout.activity_map, container, false);

        }else{
            return inflater.inflate(R.layout.activity_fragment3, container, false);
        }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

   /*     if(initMap()){
            Toast.makeText(getActivity(), "Ready to map!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Map not connected!", Toast.LENGTH_SHORT).show();
        }*/

    }

    public boolean servicesOK(){

        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog =
                    GooglePlayServicesUtil.getErrorDialog(isAvailable,getActivity(),ERROR_Dialog_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(getActivity(), "Can't connect to mapping service", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private boolean initMap(){
        if(mMap == null){
         //  SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager.findFragmentById(R.id.map);
           // mMap = mapFragment.getMap();

            SupportMapFragment mapFragment = (SupportMapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();

           /* FragmentManager fragManager = myContext.getSupportFragmentManager();
            mMap = fragManager.getMap();*/
        }
        return (mMap != null);
    }
}