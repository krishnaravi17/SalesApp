package com.tech.salesapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.salesapp.Adapter.RecyclerviewForMyProducts;
import com.tech.salesapp.R;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTBrand_List;


public class FragmentForSeeMyProducts extends Fragment {
    RecyclerView recyclerView;

    public FragmentForSeeMyProducts() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment_for_see_all_deals, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rv_categories);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        RecyclerviewForMyProducts customAdapter = new RecyclerviewForMyProducts(getActivity(), LSTBrand_List);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


        return v;
    }

}
