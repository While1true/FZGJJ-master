package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/11.
 */

public class BeanGrgjjxxcx {
    /**
     * data : [{"accountbalance":"50000","accountcode":"111111111","accountstatus":"正常","accounttype":"公积金","income":"3000","monthpay":"720","opendate":"2015-05-05","openeddate":"2015-05-05","paytodate":"2017-05","peracct":"lsh","personpay":"600","personpayrates":"12","unitpay":"600","unitpayrates":"12","workingdate":"2015-05-05"}]
     * status : 1
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        String name;
        String peracct;
        int accountstatus;
        int accounttype;

        String bkcard_no;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPeracct() {
            return peracct;
        }

        public void setPeracct(String peracct) {
            this.peracct = peracct;
        }

        public int getAccountstatus() {
            return accountstatus;
        }

        public void setAccountstatus(int accountstatus) {
            this.accountstatus = accountstatus;
        }

        public int getAccounttype() {
            return accounttype;
        }

        public void setAccounttype(int accounttype) {
            this.accounttype = accounttype;
        }

        public String getBkcard_no() {
            return bkcard_no;
        }

        public void setBkcard_no(String bkcard_no) {
            this.bkcard_no = bkcard_no;
        }

        public String getOpen_date() {
            return open_date;
        }

        public void setOpen_date(String open_date) {
            this.open_date = open_date;
        }

        public String getYear_d_amt() {
            return year_d_amt;
        }

        public void setYear_d_amt(String year_d_amt) {
            this.year_d_amt = year_d_amt;
        }

        public String getLst_year_bal() {
            return lst_year_bal;
        }

        public void setLst_year_bal(String lst_year_bal) {
            this.lst_year_bal = lst_year_bal;
        }

        public String getYear_c_amt() {
            return year_c_amt;
        }

        public void setYear_c_amt(String year_c_amt) {
            this.year_c_amt = year_c_amt;
        }

        public String getYears_d_amt() {
            return years_d_amt;
        }

        public void setYears_d_amt(String years_d_amt) {
            this.years_d_amt = years_d_amt;
        }

        public String getYears_c_amt() {
            return years_c_amt;
        }

        public void setYears_c_amt(String years_c_amt) {
            this.years_c_amt = years_c_amt;
        }

        public String getAccountbalance() {
            return accountbalance;
        }

        public void setAccountbalance(String accountbalance) {
            this.accountbalance = accountbalance;
        }

        public String getPayto_date() {
            return payto_date;
        }

        public void setPayto_date(String payto_date) {
            this.payto_date = payto_date;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getUnitpayrates() {
            return unitpayrates;
        }

        public void setUnitpayrates(String unitpayrates) {
            this.unitpayrates = unitpayrates;
        }

        public String getPersonpayrates() {
            return personpayrates;
        }

        public void setPersonpayrates(String personpayrates) {
            this.personpayrates = personpayrates;
        }

        public String getUnitpay() {
            return unitpay;
        }

        public void setUnitpay(String unitpay) {
            this.unitpay = unitpay;
        }

        public String getPersonpay() {
            return personpay;
        }

        public void setPersonpay(String personpay) {
            this.personpay = personpay;
        }

        public String getMonthpay() {
            return monthpay;
        }

        public void setMonthpay(String monthpay) {
            this.monthpay = monthpay;
        }

        public int getFrozenmarks() {
            return frozenmarks;
        }

        public void setFrozenmarks(int frozenmarks) {
            this.frozenmarks = frozenmarks;
        }

        public String getComp_name() {
            return comp_name;
        }

        public void setComp_name(String comp_name) {
            this.comp_name = comp_name;
        }

        String open_date;
        String year_d_amt;
        String lst_year_bal;

        String year_c_amt;
        String years_d_amt;
        String years_c_amt;
        String accountbalance;
        String payto_date;
        String income;
        String unitpayrates;
        String personpayrates;
        String unitpay;
        String personpay;
        String monthpay;
        int frozenmarks;

        String comp_name;
    }
}
