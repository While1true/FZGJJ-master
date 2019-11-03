package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/11.
 */

public class BeanGrjcmxcx {

    /**
     * data : [{"BAL":123122.56,"BANK_DATE":"2017-05-10","BUSI_BRIEF":"汇缴201705","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":120924.56,"BANK_DATE":"2017-04-07","BUSI_BRIEF":"汇缴201704","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":118726.56,"BANK_DATE":"2017-03-07","BUSI_BRIEF":"汇缴201703","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":116528.56,"BANK_DATE":"2017-02-08","BUSI_BRIEF":"汇缴201702","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":114330.56,"BANK_DATE":"2017-01-06","BUSI_BRIEF":"汇缴201701","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":112132.56,"BANK_DATE":"2016-12-07","BUSI_BRIEF":"汇缴201612","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":109934.56,"BANK_DATE":"2016-11-08","BUSI_BRIEF":"汇缴201611","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":107736.56,"BANK_DATE":"2016-10-10","BUSI_BRIEF":"汇缴201610","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":105538.56,"BANK_DATE":"2016-09-08","BUSI_BRIEF":"汇缴201609","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":103340.56,"BANK_DATE":"2016-08-08","BUSI_BRIEF":"汇缴201608","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":101142.56,"BANK_DATE":"2016-07-13","BUSI_BRIEF":"汇缴201607","CR_AMT":2198,"PSN_ACCT":"001194654"},{"BAL":98944.56,"BANK_DATE":"2016-07-01","BUSI_BRIEF":"年度结息","CR_AMT":1304.83,"PSN_ACCT":"001194654"},{"BAL":97639.73,"BANK_DATE":"2016-06-07","BUSI_BRIEF":"汇缴1606","CR_AMT":2118,"PSN_ACCT":"001194654"},{"BAL":95521.73,"BANK_DATE":"2016-05-04","BUSI_BRIEF":"汇缴1605","CR_AMT":2118,"PSN_ACCT":"001194654"},{"BAL":93403.73,"BANK_DATE":"2016-04-06","BUSI_BRIEF":"汇缴1604","CR_AMT":2118,"PSN_ACCT":"001194654"},{"BAL":91285.73,"BANK_DATE":"2016-03-08","BUSI_BRIEF":"汇缴1603","CR_AMT":2118,"PSN_ACCT":"001194654"},{"BAL":89167.73,"BANK_DATE":"2016-02-02","BUSI_BRIEF":"汇缴1602","CR_AMT":2118,"PSN_ACCT":"001194654"},{"BAL":87049.73,"BANK_DATE":"2016-01-11","BUSI_BRIEF":"汇缴1601","CR_AMT":2118,"PSN_ACCT":"001194654"}]
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
         * BAL : 123122.56
         * BANK_DATE : 2017-05-10
         * BUSI_BRIEF : 汇缴201705
         * CR_AMT : 2198
         * PSN_ACCT : 001194654
         */

        private double BAL;
        private String BANK_DATE;
        private String BUSI_BRIEF;
        private double CR_AMT;
        private String PSN_ACCT;

        public double getBAL() {
            return BAL;
        }

        public void setBAL(double BAL) {
            this.BAL = BAL;
        }

        public String getBANK_DATE() {
            return BANK_DATE;
        }

        public void setBANK_DATE(String BANK_DATE) {
            this.BANK_DATE = BANK_DATE;
        }

        public String getBUSI_BRIEF() {
            return BUSI_BRIEF;
        }

        public void setBUSI_BRIEF(String BUSI_BRIEF) {
            this.BUSI_BRIEF = BUSI_BRIEF;
        }

        public double getCR_AMT() {
            return CR_AMT;
        }

        public void setCR_AMT(double CR_AMT) {
            this.CR_AMT = CR_AMT;
        }

        public String getPSN_ACCT() {
            return PSN_ACCT;
        }

        public void setPSN_ACCT(String PSN_ACCT) {
            this.PSN_ACCT = PSN_ACCT;
        }
    }
}
