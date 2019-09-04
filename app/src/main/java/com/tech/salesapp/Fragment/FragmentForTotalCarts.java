package com.tech.salesapp.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.salesapp.Adapter.RecyclerviewForCartDetails;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.tech.salesapp.Interface.InterfaceForListdata.LSTTaxList;


public class FragmentForTotalCarts extends Fragment {

    RecyclerView rv_cartdetails;
    List<ProductList> list;
    TextView subtotal, tax, total;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_for_total_carts, container, false);
        rv_cartdetails = v.findViewById(R.id.rv_cartdetails);
        subtotal = v.findViewById(R.id.subtotal);
        tax = v.findViewById(R.id.tax);
        total = v.findViewById(R.id.total);

        FragmentManager fragmentManager = getFragmentManager();

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        String productsList = prefs.getString("Products", null);//taking data from sharedPrefs

        List<ProductList> data = ProductList.getListDataFromJson(productsList);

        if (data != null && data.size() > 0) {

            RecyclerviewForCartDetails adapter = new RecyclerviewForCartDetails(getActivity(), fragmentManager, data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rv_cartdetails.setLayoutManager(layoutManager);
            rv_cartdetails.setAdapter(adapter);

            int subtotalnum = 0;
            for (int i = 0; i < data.size(); i++) {
                subtotalnum = subtotalnum + data.get(i).getPrice();
            }

            subtotal.setText(String.valueOf(subtotalnum));
            if (LSTTaxList.size() > 0 && LSTTaxList.get(0).getRate() != null) {
                int Tax = 0;
                try {
                    Tax = Integer.parseInt(LSTTaxList.get(0).getRate());
                } catch (Exception e) {
                    Tax = 0;
                }


                int tot = subtotalnum + Tax;

                tax.setText(String.valueOf(Tax));
                total.setText(String.valueOf(tot));

            } else {
                Toast.makeText(getActivity(), "No Tax Data found", Toast.LENGTH_SHORT).show();
            }


        } else {

            Toast.makeText(getActivity(), "Data Not Found!!", Toast.LENGTH_SHORT).show();

        }


        return v;
    }

}
