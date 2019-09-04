package com.tech.salesapp.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tech.salesapp.Adapter.RecyclerviewForBestSellers;
import com.tech.salesapp.Adapter.RecyclerviewForSearch;
import com.tech.salesapp.Adapter.RecyclerviewGridProducts;
import com.tech.salesapp.Entity.BrandList;
import com.tech.salesapp.Entity.CategoriesList;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Entity.RetroPhoto;
import com.tech.salesapp.Entity.Tax;
import com.tech.salesapp.Interface.ApiService;
import com.tech.salesapp.Network.ApiClient;
import com.tech.salesapp.R;
import com.tech.salesapp.Utils.AndroidUtils;
import com.tech.salesapp.Utils.AppProgress;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTBrand_List;
import static com.tech.salesapp.Interface.InterfaceForListdata.LSTCategoriesList;
import static com.tech.salesapp.Interface.InterfaceForListdata.LSTProduct_List;
import static com.tech.salesapp.Interface.InterfaceForListdata.LSTTaxList;


public class FragmentDashboard extends Fragment {
    EditText et_search;
    RecyclerView recyclerView, rv_forBestSellers, rv_search;
    TextView seeAllCategories, seeAllDealsHeader, seemore;
    LinearLayout ll_dashboard, ll_for_fragments;
    ImageView iv_dealoftheday;
    RetroPhoto retroPhoto = new RetroPhoto();
    List<RetroPhoto> lstretro = new ArrayList<>();
    List<ProductList> newlstforrv = new ArrayList<>();
    public static int countData = 0;

    public FragmentDashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_dashboard, container, false);
        findViewById(v);

        if (countData == 0) {
            getCategoriesList();//fetchinf data of catogies
            getTaxList();//Fetching Tax data
            countData++;
        }


        FragmentManager fragmentManager = getFragmentManager();//categories
        //recyclerView for features--categories
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        RecyclerviewGridProducts customAdapter = new RecyclerviewGridProducts(getActivity(), LSTProduct_List, fragmentManager);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        //recyclerView for bestSellers
        RecyclerviewForBestSellers adapter = new RecyclerviewForBestSellers(getActivity(), fragmentManager, LSTProduct_List);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_forBestSellers.setLayoutManager(layoutManager);
        rv_forBestSellers.setAdapter(adapter);

        //testing

        retroPhoto.setName("Ravi Krishna");
        lstretro.add(retroPhoto);
        retroPhoto.setName("Ravi Kumar");
        lstretro.add(retroPhoto);

        seeAllDealsHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getBrandList();

                FragmentProductList fragmentProductList = new FragmentProductList();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentProductList);
                transaction.commit();

            }
        });


        seeAllCategories.setOnClickListener(new View.OnClickListener() {//categories
            @Override
            public void onClick(View v) {


                //getCategoriesList();

                FragmentForCategories fragmentProductListCart = new FragmentForCategories();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentProductListCart);
                transaction.commit();


            }
        });
        iv_dealoftheday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LSTProduct_List.size() > 0) {
                    ProductList productList = LSTProduct_List.get(0);

                    ProductDetailsFragment fragmentCart = new ProductDetailsFragment(productList);
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.ll_for_fragments, fragmentCart);
                    transaction.commit();
                }


                //Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                // startActivity(intent);
            }
        });


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
                newlstforrv.clear();
                rv_search.setVisibility(View.GONE);
                RecyclerviewForSearch adapter1 = new RecyclerviewForSearch(getActivity(), newlstforrv);
                RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
                rv_search.setLayoutManager(layoutManager1);
                rv_search.setAdapter(adapter1);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

                if (s.length() > 0) {
                    rv_search.setVisibility(View.VISIBLE);

                    for (ProductList retroPhoto : LSTProduct_List) {

                        if (retroPhoto.getName().toLowerCase().contains(s)) {
                            newlstforrv.add(retroPhoto);
                        }
                        RecyclerviewForSearch adapter1 = new RecyclerviewForSearch(getActivity(), newlstforrv);
                        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
                        rv_search.setLayoutManager(layoutManager1);
                        rv_search.setAdapter(adapter1);
                    }

                } else {
                    rv_search.setVisibility(View.GONE);
                    newlstforrv.clear();
                    RecyclerviewForSearch adapter1 = new RecyclerviewForSearch(getActivity(), newlstforrv);
                    RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
                    rv_search.setLayoutManager(layoutManager1);
                    rv_search.setAdapter(adapter1);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();

            }
        });

        seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBrandList();
            }
        });


        return v;
    }

    private void getCategoriesList() {

        if (AndroidUtils.checkYourMobileDataConnection(getActivity())) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(getActivity());

            Call<JsonObject> call = apiService.CategoriesData("2");//merchant id

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null) {
                        LSTCategoriesList.clear();

                        List<CategoriesList> productLists = CategoriesList.getListDataFromJson(response.body().get("data").toString());

                        for (CategoriesList productList : productLists) {

                            LSTCategoriesList.add(productList);

                        }


                       /* FragmentForCategories fragmentProductListCart = new FragmentForCategories();
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.ll_for_fragments, fragmentProductListCart);
                        transaction.commit();*/


                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    AppProgress.hideProgress(dialog);
                }
            });


        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }


    }

    private void getTaxList() {

        if (AndroidUtils.checkYourMobileDataConnection(getActivity())) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(getActivity());

            Call<JsonObject> call = apiService.TaxData("1");//merchant id

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null) {
                      //  LSTCategoriesList.clear();

                        List<Tax> taxLists = Tax.getListDataFromJson(response.body().get("data").toString());

                        for (Tax tax : taxLists) {

                            LSTTaxList.add(tax);

                        }


                       /* FragmentForCategories fragmentProductListCart = new FragmentForCategories();
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.ll_for_fragments, fragmentProductListCart);
                        transaction.commit();*/


                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    AppProgress.hideProgress(dialog);
                }
            });


        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }


    }

    private void getBrandList() {

        if (AndroidUtils.checkYourMobileDataConnection(getActivity())) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(getActivity());

            Call<JsonObject> call = apiService.BrandData("2");//merchant id

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null) {
                        LSTBrand_List.clear();

                        List<BrandList> productLists = BrandList.getListDataFromJson(response.body().get("data").toString());

                        for (BrandList productList : productLists) {

                            LSTBrand_List.add(productList);

                        }


                        FragmentForSeeMyProducts fragmentCart = new FragmentForSeeMyProducts();
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.ll_for_fragments, fragmentCart);
                        transaction.commit();


                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    AppProgress.hideProgress(dialog);
                }
            });


        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }


    }

    private void findViewById(View v) {
        rv_forBestSellers = v.findViewById(R.id.rv_forBestSellers);
        et_search = (EditText) v.findViewById(R.id.et_search);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_products);
        seeAllCategories = (TextView) v.findViewById(R.id.seeAllCategories);
        ll_dashboard = (LinearLayout) v.findViewById(R.id.ll_dashboard);
        ll_for_fragments = (LinearLayout) v.findViewById(R.id.ll_for_fragments);
        iv_dealoftheday = (ImageView) v.findViewById(R.id.iv_dealoftheday);
        rv_search = (RecyclerView) v.findViewById(R.id.rv_search);
        rv_search = (RecyclerView) v.findViewById(R.id.rv_search);
        seeAllDealsHeader = (TextView) v.findViewById(R.id.seeAllDealsHeader);
        seemore = (TextView) v.findViewById(R.id.seemore);
    }
}
