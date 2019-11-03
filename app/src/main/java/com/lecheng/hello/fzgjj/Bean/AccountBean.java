package com.lecheng.hello.fzgjj.Bean;

import org.litepal.crud.DataSupport;

/**
 * Created by vange on 2017/9/15.
 */

public class AccountBean extends DataSupport {


    /**
     * credit_code : 350103197102050065
     * credit_type : 身份证件
     * email :
     * family_address :
     * gjjaccount : 001187252
     * homephone :
     * mobile : 13365910795
     * name : 黄春
     * unitsphone :
     * workunits : 福建医科大学附属协和医院
     */

    private String credit_code;
    private String credit_type;
    private String email;
    private String family_address;
    private String gjjaccount;
    private String homephone;
    private String mobile;
    private String name;
    private String unitsphone;
    private String workunits;

    public String getCredit_code() {
        return credit_code;
    }

    public void setCredit_code(String credit_code) {
        this.credit_code = credit_code;
    }

    public String getCredit_type() {
        return credit_type;
    }

    public void setCredit_type(String credit_type) {
        this.credit_type = credit_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamily_address() {
        return family_address;
    }

    public void setFamily_address(String family_address) {
        this.family_address = family_address;
    }

    public String getGjjaccount() {
        return gjjaccount;
    }

    public void setGjjaccount(String gjjaccount) {
        this.gjjaccount = gjjaccount;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitsphone() {
        return unitsphone;
    }

    public void setUnitsphone(String unitsphone) {
        this.unitsphone = unitsphone;
    }

    public String getWorkunits() {
        return workunits;
    }

    public void setWorkunits(String workunits) {
        this.workunits = workunits;
    }
}
