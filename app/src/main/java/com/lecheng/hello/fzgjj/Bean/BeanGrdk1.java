package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/12.
 */

public class BeanGrdk1 {
    /**
     * data : [{"apply_amt":"60000","auth_stat":"待处理","bank_loan_period":"10年","bank_loan_prin":"100000.0","bor_cert_code":"35010219921111581X","cur_step":"受理","ent_date":"2017-05-10","ent_oper":"赵瑞龙","montincome":"66","name":"林思晗"}]
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
         * apply_amt : 60000
         * auth_stat : 待处理
         * bank_loan_period : 10年
         * bank_loan_prin : 100000.0
         * bor_cert_code : 35010219921111581X
         * cur_step : 受理
         * ent_date : 2017-05-10
         * ent_oper : 赵瑞龙
         * montincome : 66
         * name : 林思晗
         */

        private String apply_amt;
        private String auth_stat;
        private String bank_loan_period;
        private String bank_loan_prin;
        private String bor_cert_code;
        private String cur_step;
        private String ent_date;
        private String ent_oper;
        private String montincome;
        private String apply_period;
        private String name;

        public String getApply_period() {
            return apply_period;
        }

        public void setApply_period(String apply_period) {
            this.apply_period = apply_period;
        }

        public String getApply_amt() {
            return apply_amt;
        }

        public void setApply_amt(String apply_amt) {
            this.apply_amt = apply_amt;
        }

        public String getAuth_stat() {
            return auth_stat;
        }

        public void setAuth_stat(String auth_stat) {
            this.auth_stat = auth_stat;
        }

        public String getBank_loan_period() {
            return bank_loan_period;
        }

        public void setBank_loan_period(String bank_loan_period) {
            this.bank_loan_period = bank_loan_period;
        }

        public String getBank_loan_prin() {
            return bank_loan_prin;
        }

        public void setBank_loan_prin(String bank_loan_prin) {
            this.bank_loan_prin = bank_loan_prin;
        }

        public String getBor_cert_code() {
            return bor_cert_code;
        }

        public void setBor_cert_code(String bor_cert_code) {
            this.bor_cert_code = bor_cert_code;
        }

        public String getCur_step() {
            return cur_step;
        }

        public void setCur_step(String cur_step) {
            this.cur_step = cur_step;
        }

        public String getEnt_date() {
            return ent_date;
        }

        public void setEnt_date(String ent_date) {
            this.ent_date = ent_date;
        }

        public String getEnt_oper() {
            return ent_oper;
        }

        public void setEnt_oper(String ent_oper) {
            this.ent_oper = ent_oper;
        }

        public String getMontincome() {
            return montincome;
        }

        public void setMontincome(String montincome) {
            this.montincome = montincome;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
