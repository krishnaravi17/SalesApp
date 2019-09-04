package com.tech.salesapp.Interface;

import com.tech.salesapp.Entity.BrandList;
import com.tech.salesapp.Entity.CategoriesList;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Entity.Tax;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceForListdata {

    public static List<ProductList> LSTProduct_List = new ArrayList<>();
    public static List<BrandList> LSTBrand_List = new ArrayList<>();
    public static List<CategoriesList> LSTCategoriesList = new ArrayList<>();
    public static List<Tax> LSTTaxList = new ArrayList<>();

}
