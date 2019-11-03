package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/12.
 */

public class BeanGrdk3 {


    /**
     * data : [{"amt":"1000","bank_date":"2017-05-23","brief":"解除劳动关系且购房销户本金","loan_bal":"500","pay_hst_int":"2","pay_int":"20","pay_ovd_int":"1","pay_ovd_prin":"10","pay_prin":"500","pre_pay_prin":"12"}]
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
        /**
         * amt : 1000
         * bank_date : 2017-05-23
         * brief : 解除劳动关系且购房销户本金
         * loan_bal : 500
         * pay_hst_int : 2
         * pay_int : 20
         * pay_ovd_int : 1
         * pay_ovd_prin : 10
         * pay_prin : 500
         * pre_pay_prin : 12
         */

        private String amt;
        private String bank_date;
        private String brief;
        private String loan_bal;
        private String pay_hst_int;
        private String pay_int;
        private String pay_ovd_int;
        private String pay_ovd_prin;
        private String pay_prin;
        private String pre_pay_prin;

        public String getAmt() {
            return amt;
        }

        public void setAmt(String amt) {
            this.amt = amt;
        }

        public String getBank_date() {
            return bank_date;
        }

        public void setBank_date(String bank_date) {
            this.bank_date = bank_date;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getLoan_bal() {
            return loan_bal;
        }

        public void setLoan_bal(String loan_bal) {
            this.loan_bal = loan_bal;
        }

        public String getPay_hst_int() {
            return pay_hst_int;
        }

        public void setPay_hst_int(String pay_hst_int) {
            this.pay_hst_int = pay_hst_int;
        }

        public String getPay_int() {
            return pay_int;
        }

        public void setPay_int(String pay_int) {
            this.pay_int = pay_int;
        }

        public String getPay_ovd_int() {
            return pay_ovd_int;
        }

        public void setPay_ovd_int(String pay_ovd_int) {
            this.pay_ovd_int = pay_ovd_int;
        }

        public String getPay_ovd_prin() {
            return pay_ovd_prin;
        }

        public void setPay_ovd_prin(String pay_ovd_prin) {
            this.pay_ovd_prin = pay_ovd_prin;
        }

        public String getPay_prin() {
            return pay_prin;
        }

        public void setPay_prin(String pay_prin) {
            this.pay_prin = pay_prin;
        }

        public String getPre_pay_prin() {
            return pre_pay_prin;
        }

        public void setPre_pay_prin(String pre_pay_prin) {
            this.pre_pay_prin = pre_pay_prin;
        }
    }
}
