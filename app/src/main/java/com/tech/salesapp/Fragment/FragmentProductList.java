package com.tech.salesapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.salesapp.Adapter.RecyclerviewForCartDetails;
import com.tech.salesapp.R;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTProduct_List;

public class FragmentProductList extends Fragment {

    RecyclerView rv_cartdetails;
    LinearLayout ll_empty,ll_activity;
    Context context;
    TextView tv_price;

    public FragmentProductList() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_cart, container, false);

        rv_cartdetails = v.findViewById(R.id.rv_cartdetails);
        ll_activity=v.findViewById(R.id.ll_empty);
        ll_empty=v.findViewById(R.id.ll_empty);
        tv_price = v.findViewById(R.id.tv_price);


        if (LSTProduct_List != null && LSTProduct_List.size() > 0){

            FragmentManager fragmentManager = getFragmentManager();
            RecyclerviewForCartDetails adapter = new RecyclerviewForCartDetails(getActivity(), fragmentManager, LSTProduct_List);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv_cartdetails.setLayoutManager(layoutManager);
            rv_cartdetails.setAdapter(adapter);

        }else{

            Toast.makeText(context, getResources().getString(R.string.datanotfound), Toast.LENGTH_SHORT).show();

        }



        return v;
    }




}
