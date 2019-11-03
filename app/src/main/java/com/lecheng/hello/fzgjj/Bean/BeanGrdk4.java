package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/26.
 */

public class BeanGrdk4 {

    /**
     * data : [{"ACCT_TYPE":"1","BAL":5000,"DRAWMONEY":700,"DRAW_NO":"6666666d666","DRAW_REA":"还公积金贷款","PSN_ACCT":"lsh","REAL_DRAW_DATE":"2017-05-20"},{"ACCT_TYPE":"1","BAL":999.9,"DRAWMONEY":333,"DRAW_NO":"66666667","DRAW_REA":"还商业性贷款","PSN_ACCT":"lsh","REAL_DRAW_DATE":"2017-05-01"},{"ACCT_TYPE":"1","BAL":5000,"DRAWMONEY":700,"DRAW_NO":"6666666d666","DRAW_REA":"还公积金贷款","PSN_ACCT":"lsh","REAL_DRAW_DATE":"2017-05-20"},{"ACCT_TYPE":"1","BAL":999.9,"DRAWMONEY":333,"DRAW_NO":"66666667","DRAW_REA":"还商业性贷款","PSN_ACCT":"lsh","REAL_DRAW_DATE":"2017-05-01"}]
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
         * ACCT_TYPE : 1
         * BAL : 5000
         * DRAWMONEY : 700
         * DRAW_NO : 6666666d666
         * DRAW_REA : 还公积金贷款
         * PSN_ACCT : lsh
         * REAL_DRAW_DATE : 2017-05-20
         */

        private String ACCT_TYPE;
        private double BAL;
        private double DRAWMONEY;
        private String DRAW_NO;
        private String DRAW_REA;
        private String PSN_ACCT;
        private String REAL_DRAW_DATE;

        public String getACCT_TYPE() {
            return ACCT_TYPE;
        }

        public void setACCT_TYPE(String ACCT_TYPE) {
            this.ACCT_TYPE = ACCT_TYPE;
        }

        public double getBAL() {
            return BAL;
        }

        public void setBAL(double BAL) {
            this.BAL = BAL;
        }

        public double getDRAWMONEY() {
            return DRAWMONEY;
        }

        public void setDRAWMONEY(double DRAWMONEY) {
            this.DRAWMONEY = DRAWMONEY;
        }

        public String getDRAW_NO() {
            return DRAW_NO;
        }

        public void setDRAW_NO(String DRAW_NO) {
            this.DRAW_NO = DRAW_NO;
        }

        public String getDRAW_REA() {
            return DRAW_REA;
        }

        public void setDRAW_REA(String DRAW_REA) {
            this.DRAW_REA = DRAW_REA;
        }

        public String getPSN_ACCT() {
            return PSN_ACCT;
        }

        public void setPSN_ACCT(String PSN_ACCT) {
            this.PSN_ACCT = PSN_ACCT;
        }

        public String getREAL_DRAW_DATE() {
            return REAL_DRAW_DATE;
        }

        public void setREAL_DRAW_DATE(String REAL_DRAW_DATE) {
            this.REAL_DRAW_DATE = REAL_DRAW_DATE;
        }
    }
}
