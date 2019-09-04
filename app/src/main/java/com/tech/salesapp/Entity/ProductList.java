package com.tech.salesapp.Entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductList {

    public static int count;

    @SerializedName("quantity")
    @Expose
    public int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        ProductList.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ProductList)) {
            return false;
        }
        // typecast o to ProductList so that we can compare data members
        ProductList product = (ProductList) o;
        // Compare the data members and return accordingly
        return id.equals(product.id);
    }

    public static String listToJson(List<ProductList> productList) {
        return new Gson().toJson(productList);

    }

    public static List<ProductList> getListDataFromJson(String serverJson) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductList>>() {
        }.getType();
        return gson.fromJson(serverJson, type);
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("merchant_id")
    @Expose
    private String merchantId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("cost")
    @Expose
    private String cost;

    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("alert_quantity")
    @Expose
    private String alertQuantity;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("subcategory_id")
    @Expose
    private Object subcategoryId;
    @SerializedName("cf1")
    @Expose
    private String cf1;
    @SerializedName("cf2")
    @Expose
    private String cf2;
    @SerializedName("cf3")
    @Expose
    private String cf3;
    @SerializedName("cf4")
    @Expose
    private String cf4;
    @SerializedName("cf5")
    @Expose
    private String cf5;
    @SerializedName("cf6")
    @Expose
    private String cf6;


    @SerializedName("tax_rate")
    @Expose
    private String taxRate;
    @SerializedName("track_quantity")
    @Expose
    private String trackQuantity;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("warehouse")
    @Expose
    private Object warehouse;
    @SerializedName("barcode_symbology")
    @Expose
    private String barcodeSymbology;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("product_details")
    @Expose
    private String productDetails;
    @SerializedName("tax_method")
    @Expose
    private String taxMethod;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("supplier1")
    @Expose
    private String supplier1;
    @SerializedName("supplier1price")
    @Expose
    private Object supplier1price;
    @SerializedName("supplier2")
    @Expose
    private Object supplier2;
    @SerializedName("supplier2price")
    @Expose
    private Object supplier2price;
    @SerializedName("supplier3")
    @Expose
    private Object supplier3;
    @SerializedName("supplier3price")
    @Expose
    private Object supplier3price;
    @SerializedName("supplier4")
    @Expose
    private Object supplier4;
    @SerializedName("supplier4price")
    @Expose
    private Object supplier4price;
    @SerializedName("supplier5")
    @Expose
    private Object supplier5;
    @SerializedName("supplier5price")
    @Expose
    private Object supplier5price;
    @SerializedName("promotion")
    @Expose
    private Object promotion;
    @SerializedName("promo_price")
    @Expose
    private Object promoPrice;
    @SerializedName("start_date")
    @Expose
    private Object startDate;
    @SerializedName("end_date")
    @Expose
    private Object endDate;
    @SerializedName("supplier1_part_no")
    @Expose
    private String supplier1PartNo;
    @SerializedName("supplier2_part_no")
    @Expose
    private Object supplier2PartNo;
    @SerializedName("supplier3_part_no")
    @Expose
    private Object supplier3PartNo;
    @SerializedName("supplier4_part_no")
    @Expose
    private Object supplier4PartNo;
    @SerializedName("supplier5_part_no")
    @Expose
    private Object supplier5PartNo;
    @SerializedName("sale_unit")
    @Expose
    private String saleUnit;
    @SerializedName("purchase_unit")
    @Expose
    private String purchaseUnit;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("featured")
    @Expose
    private Object featured;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("hsn_code")
    @Expose
    private Object hsnCode;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("hide")
    @Expose
    private String hide;
    @SerializedName("second_name")
    @Expose
    private String secondName;
    @SerializedName("hide_pos")
    @Expose
    private String hidePos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAlertQuantity() {
        return alertQuantity;
    }

    public void setAlertQuantity(String alertQuantity) {
        this.alertQuantity = alertQuantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Object getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Object subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    public String getCf1() {
        return cf1;
    }

    public void setCf1(String cf1) {
        this.cf1 = cf1;
    }

    public String getCf2() {
        return cf2;
    }

    public void setCf2(String cf2) {
        this.cf2 = cf2;
    }

    public String getCf3() {
        return cf3;
    }

    public void setCf3(String cf3) {
        this.cf3 = cf3;
    }

    public String getCf4() {
        return cf4;
    }

    public void setCf4(String cf4) {
        this.cf4 = cf4;
    }

    public String getCf5() {
        return cf5;
    }

    public void setCf5(String cf5) {
        this.cf5 = cf5;
    }

    public String getCf6() {
        return cf6;
    }

    public void setCf6(String cf6) {
        this.cf6 = cf6;
    }


    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTrackQuantity() {
        return trackQuantity;
    }

    public void setTrackQuantity(String trackQuantity) {
        this.trackQuantity = trackQuantity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Object warehouse) {
        this.warehouse = warehouse;
    }

    public String getBarcodeSymbology() {
        return barcodeSymbology;
    }

    public void setBarcodeSymbology(String barcodeSymbology) {
        this.barcodeSymbology = barcodeSymbology;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getTaxMethod() {
        return taxMethod;
    }

    public void setTaxMethod(String taxMethod) {
        this.taxMethod = taxMethod;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier1() {
        return supplier1;
    }

    public void setSupplier1(String supplier1) {
        this.supplier1 = supplier1;
    }

    public Object getSupplier1price() {
        return supplier1price;
    }

    public void setSupplier1price(Object supplier1price) {
        this.supplier1price = supplier1price;
    }

    public Object getSupplier2() {
        return supplier2;
    }

    public void setSupplier2(Object supplier2) {
        this.supplier2 = supplier2;
    }

    public Object getSupplier2price() {
        return supplier2price;
    }

    public void setSupplier2price(Object supplier2price) {
        this.supplier2price = supplier2price;
    }

    public Object getSupplier3() {
        return supplier3;
    }

    public void setSupplier3(Object supplier3) {
        this.supplier3 = supplier3;
    }

    public Object getSupplier3price() {
        return supplier3price;
    }

    public void setSupplier3price(Object supplier3price) {
        this.supplier3price = supplier3price;
    }

    public Object getSupplier4() {
        return supplier4;
    }

    public void setSupplier4(Object supplier4) {
        this.supplier4 = supplier4;
    }

    public Object getSupplier4price() {
        return supplier4price;
    }

    public void setSupplier4price(Object supplier4price) {
        this.supplier4price = supplier4price;
    }

    public Object getSupplier5() {
        return supplier5;
    }

    public void setSupplier5(Object supplier5) {
        this.supplier5 = supplier5;
    }

    public Object getSupplier5price() {
        return supplier5price;
    }

    public void setSupplier5price(Object supplier5price) {
        this.supplier5price = supplier5price;
    }

    public Object getPromotion() {
        return promotion;
    }

    public void setPromotion(Object promotion) {
        this.promotion = promotion;
    }

    public Object getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Object promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getSupplier1PartNo() {
        return supplier1PartNo;
    }

    public void setSupplier1PartNo(String supplier1PartNo) {
        this.supplier1PartNo = supplier1PartNo;
    }

    public Object getSupplier2PartNo() {
        return supplier2PartNo;
    }

    public void setSupplier2PartNo(Object supplier2PartNo) {
        this.supplier2PartNo = supplier2PartNo;
    }

    public Object getSupplier3PartNo() {
        return supplier3PartNo;
    }

    public void setSupplier3PartNo(Object supplier3PartNo) {
        this.supplier3PartNo = supplier3PartNo;
    }

    public Object getSupplier4PartNo() {
        return supplier4PartNo;
    }

    public void setSupplier4PartNo(Object supplier4PartNo) {
        this.supplier4PartNo = supplier4PartNo;
    }

    public Object getSupplier5PartNo() {
        return supplier5PartNo;
    }

    public void setSupplier5PartNo(Object supplier5PartNo) {
        this.supplier5PartNo = supplier5PartNo;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Object getFeatured() {
        return featured;
    }

    public void setFeatured(Object featured) {
        this.featured = featured;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Object getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(Object hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getHidePos() {
        return hidePos;
    }

    public void setHidePos(String hidePos) {
        this.hidePos = hidePos;
    }
}
