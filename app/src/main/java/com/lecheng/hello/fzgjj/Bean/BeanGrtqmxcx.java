package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/11.
 */

public class BeanGrtqmxcx {


    /**
     * data : [{"ACCOUNTBALANCE":"123122.56","PSN_NAME":"陈永福 ","YEAR_DRAW_AMT":"0"}]
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
         * 支取编号
         */
        private String DRAW_NO;
        /**
         * 支取日期
         */
        private String REAL_DRAW_DATE;

        /**
         * 支取原因
         */
        private String DRAW_REA;
        /**
         * 提取额
         */
        private String DRAWMONEY	;
        /**
         * 账户余额
         */
        private String BAL	;
        /**
         * 人民币
         */
        private String PSN_ACCT	;
        /**
         * 账户类型 1-公积金   3-补贴
         */
        private int ACCT_TYPE	;

        public String getDRAW_NO() {
            return DRAW_NO;
        }

        public void setDRAW_NO(String DRAW_NO) {
            this.DRAW_NO = DRAW_NO;
        }

        public String getREAL_DRAW_DATE() {
            return REAL_DRAW_DATE;
        }

        public void setREAL_DRAW_DATE(String REAL_DRAW_DATE) {
            this.REAL_DRAW_DATE = REAL_DRAW_DATE;
        }

        public String getDRAW_REA() {
            return DRAW_REA;
        }

        public void setDRAW_REA(String DRAW_REA) {
            this.DRAW_REA = DRAW_REA;
        }

        public String getDRAWMONEY() {
            return DRAWMONEY;
        }

        public void setDRAWMONEY(String DRAWMONEY) {
            this.DRAWMONEY = DRAWMONEY;
        }

        public String getBAL() {
            return BAL;
        }

        public void setBAL(String BAL) {
            this.BAL = BAL;
        }

        public String getPSN_ACCT() {
            return PSN_ACCT;
        }

        public void setPSN_ACCT(String PSN_ACCT) {
            this.PSN_ACCT = PSN_ACCT;
        }

        public int getACCT_TYPE() {
            return ACCT_TYPE;
        }

        public void setACCT_TYPE(int ACCT_TYPE) {
            this.ACCT_TYPE = ACCT_TYPE;
        }
    }
}
