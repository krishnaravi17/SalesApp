package com.tech.salesapp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tech.salesapp.Adapter.RecyclerviewGridCategories;
import com.tech.salesapp.R;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTCategoriesList;


public class FragmentForCategories extends Fragment {
    RecyclerView recyclerView;

    public FragmentForCategories() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_for_categories, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_categories);

        if (LSTCategoriesList != null && LSTCategoriesList.size() > 0) {

            FragmentManager fragmentManager = getFragmentManager();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
            //  call the constructor of CustomAdapter to send the reference and data to Adapter
            RecyclerviewGridCategories customAdapter = new RecyclerviewGridCategories(getActivity(), LSTCategoriesList, fragmentManager);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        } else {

            Toast.makeText(getActivity(), "Data Not Found!", Toast.LENGTH_SHORT).show();

        }


        return view;
    }


}
