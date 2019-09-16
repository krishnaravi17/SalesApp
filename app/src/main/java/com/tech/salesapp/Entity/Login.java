package com.tech.salesapp.Entity;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Login {

    public static List<Login> getListDataFromJson(String serverJson){
        Gson gson=new Gson();
        Type type=new TypeToken<List<Login>>(){}.getType();
        return gson.fromJson(serverJson,type);
    }

    public static Login getjsonObject(String serverjson){
        Gson gson=new Gson();
        Type type=new TypeToken<Login>(){}.getType();
        return gson.fromJson(serverjson,type);
    }

    String ID,USERNAME,NAME,CITY;
    int PASSWORD;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public int getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(int PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("last_ip_address")
    @Expose
    private String lastIpAddress;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("salt")
    @Expose
    private Object salt;
    @SerializedName("email")
    @Expose
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastIpAddress() {
        return lastIpAddress;
    }

    public void setLastIpAddress(String lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getSalt() {
        return salt;
    }

    public void setSalt(Object salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(Object activationCode) {
        this.activationCode = activationCode;
    }

    public Object getForgottenPasswordCode() {
        return forgottenPasswordCode;
    }

    public void setForgottenPasswordCode(Object forgottenPasswordCode) {
        this.forgottenPasswordCode = forgottenPasswordCode;
    }

    public Object getForgottenPasswordTime() {
        return forgottenPasswordTime;
    }

    public void setForgottenPasswordTime(Object forgottenPasswordTime) {
        this.forgottenPasswordTime = forgottenPasswordTime;
    }

    public String getRememberCode() {
        return rememberCode;
    }

    public void setRememberCode(String rememberCode) {
        this.rememberCode = rememberCode;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Object getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Object warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Object getBillerId() {
        return billerId;
    }

    public void setBillerId(Object billerId) {
        this.billerId = billerId;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public String getShowCost() {
        return showCost;
    }

    public void setShowCost(String showCost) {
        this.showCost = showCost;
    }

    public String getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(String showPrice) {
        this.showPrice = showPrice;
    }

    public String getAwardPoints() {
        return awardPoints;
    }

    public void setAwardPoints(String awardPoints) {
        this.awardPoints = awardPoints;
    }

    public String getViewRight() {
        return viewRight;
    }

    public void setViewRight(String viewRight) {
        this.viewRight = viewRight;
    }

    public String getEditRight() {
        return editRight;
    }

    public void setEditRight(String editRight) {
        this.editRight = editRight;
    }

    public String getAllowDiscount() {
        return allowDiscount;
    }

    public void setAllowDiscount(String allowDiscount) {
        this.allowDiscount = allowDiscount;
    }

    @SerializedName("activation_code")
    @Expose
    private Object activationCode;
    @SerializedName("forgotten_password_code")
    @Expose
    private Object forgottenPasswordCode;
    @SerializedName("forgotten_password_time")
    @Expose
    private Object forgottenPasswordTime;
    @SerializedName("remember_code")
    @Expose
    private String rememberCode;
    @SerializedName("created_on")
    @Expose
    private String createdOn;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("active")
    @Expose
    private String active;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("warehouse_id")
    @Expose
    private Object warehouseId;
    @SerializedName("biller_id")
    @Expose
    private Object billerId;
    @SerializedName("company_id")
    @Expose
    private Object companyId;
    @SerializedName("show_cost")
    @Expose
    private String showCost;
    @SerializedName("show_price")
    @Expose
    private String showPrice;
    @SerializedName("award_points")
    @Expose
    private String awardPoints;
    @SerializedName("view_right")
    @Expose
    private String viewRight;
    @SerializedName("edit_right")
    @Expose
    private String editRight;
    @SerializedName("allow_discount")
    @Expose
    private String allowDiscount;
}
