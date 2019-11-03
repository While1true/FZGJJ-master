package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/12.
 */

public class BeanGrdk2 {


    /**
     * data : [{"acct_state":"正常","acurterm_rtn_inte":"12","acurterm_rtn_prin":"455","cont_due_date":"2025-05-15","cur_term":"13","loan_amt":"500000","loan_bal":"6000","loan_bgn_date":"2014-05-06","loan_end_date":"2019-05-23","loan_irate":".03","loan_terms":"15","loan_type":"公积金贷","mon_pay_amt":"1500","remain_terms":"50","rtn_type":"等额本息法","scurterm_rtn_inte":"122","scurterm_rtn_prin":"2450"}]
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
         * acct_state : 正常
         * acurterm_rtn_inte : 12
         * acurterm_rtn_prin : 455
         * cont_due_date : 2025-05-15
         * cur_term : 13
         * loan_amt : 500000
         * loan_bal : 6000
         * loan_bgn_date : 2014-05-06
         * loan_end_date : 2019-05-23
         * loan_irate : .03
         * loan_terms : 15
         * loan_type : 公积金贷
         * mon_pay_amt : 1500
         * remain_terms : 50
         * rtn_type : 等额本息法
         * scurterm_rtn_inte : 122
         * scurterm_rtn_prin : 2450
         */

        private String acct_state;
        private String acurterm_rtn_inte;
        private String acurterm_rtn_prin;
        private String cont_due_date;
        private String cur_term;
        private String loan_amt;
        private String loan_bal;
        private String loan_bgn_date;
        private String loan_end_date;
        private String loan_irate;
        private String loan_terms;
        private String loan_type;
        private String mon_pay_amt;
        private String remain_terms;
        private String rtn_type;
        private String scurterm_rtn_inte;
        private String scurterm_rtn_prin;

        public String getAcct_state() {
            return acct_state;
        }

        public void setAcct_state(String acct_state) {
            this.acct_state = acct_state;
        }

        public String getAcurterm_rtn_inte() {
            return acurterm_rtn_inte;
        }

        public void setAcurterm_rtn_inte(String acurterm_rtn_inte) {
            this.acurterm_rtn_inte = acurterm_rtn_inte;
        }

        public String getAcurterm_rtn_prin() {
            return acurterm_rtn_prin;
        }

        public void setAcurterm_rtn_prin(String acurterm_rtn_prin) {
            this.acurterm_rtn_prin = acurterm_rtn_prin;
        }

        public String getCont_due_date() {
            return cont_due_date;
        }

        public void setCont_due_date(String cont_due_date) {
            this.cont_due_date = cont_due_date;
        }

        public String getCur_term() {
            return cur_term;
        }

        public void setCur_term(String cur_term) {
            this.cur_term = cur_term;
        }

        public String getLoan_amt() {
            return loan_amt;
        }

        public void setLoan_amt(String loan_amt) {
            this.loan_amt = loan_amt;
        }

        public String getLoan_bal() {
            return loan_bal;
        }

        public void setLoan_bal(String loan_bal) {
            this.loan_bal = loan_bal;
        }

        public String getLoan_bgn_date() {
            return loan_bgn_date;
        }

        public void setLoan_bgn_date(String loan_bgn_date) {
            this.loan_bgn_date = loan_bgn_date;
        }

        public String getLoan_end_date() {
            return loan_end_date;
        }

        public void setLoan_end_date(String loan_end_date) {
            this.loan_end_date = loan_end_date;
        }

        public String getLoan_irate() {
            return loan_irate;
        }

        public void setLoan_irate(String loan_irate) {
            this.loan_irate = loan_irate;
        }

        public String getLoan_terms() {
            return loan_terms;
        }

        public void setLoan_terms(String loan_terms) {
            this.loan_terms = loan_terms;
        }

        public String getLoan_type() {
            return loan_type;
        }

        public void setLoan_type(String loan_type) {
            this.loan_type = loan_type;
        }

        public String getMon_pay_amt() {
            return mon_pay_amt;
        }

        public void setMon_pay_amt(String mon_pay_amt) {
            this.mon_pay_amt = mon_pay_amt;
        }

        public String getRemain_terms() {
            return remain_terms;
        }

        public void setRemain_terms(String remain_terms) {
            this.remain_terms = remain_terms;
        }

        public String getRtn_type() {
            return rtn_type;
        }

        public void setRtn_type(String rtn_type) {
            this.rtn_type = rtn_type;
        }

        public String getScurterm_rtn_inte() {
            return scurterm_rtn_inte;
        }

        public void setScurterm_rtn_inte(String scurterm_rtn_inte) {
            this.scurterm_rtn_inte = scurterm_rtn_inte;
        }

        public String getScurterm_rtn_prin() {
            return scurterm_rtn_prin;
        }

        public void setScurterm_rtn_prin(String scurterm_rtn_prin) {
            this.scurterm_rtn_prin = scurterm_rtn_prin;
        }
    }
}
