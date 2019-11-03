package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/12.
 */

public class BeanGrgjjtqInfocx {
    /**
     * data : [{"accounttype":"公积金","approver":"乌冬面","bal":"999.9","name":"林思晗","psn_acct":"lsh","real_draw_int":"222","real_draw_prin":"111","sjtqrq":"2017-05-01","sjtqzje":"333","zqfs":"现金支取","zqywlx":"批量支取","zqyy":"还商业性贷款"}]
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
         * accounttype : 公积金
         * approver : 乌冬面
         * bal : 999.9
         * name : 林思晗
         * psn_acct : lsh
         * real_draw_int : 222
         * real_draw_prin : 111
         * sjtqrq : 2017-05-01
         * sjtqzje : 333
         * zqfs : 现金支取
         * zqywlx : 批量支取
         * zqyy : 还商业性贷款
         */

        private String accounttype;
        private String approver;
        private String bal;
        private String name;
        private String psn_acct;
        private String real_draw_int;
        private String real_draw_prin;
        private String sjtqrq;
        private String sjtqzje;
        private String zqfs;
        private String zqywlx;
        private String zqyy;

        public String getAccounttype() {
            return accounttype;
        }

        public void setAccounttype(String accounttype) {
            this.accounttype = accounttype;
        }

        public String getApprover() {
            return approver;
        }

        public void setApprover(String approver) {
            this.approver = approver;
        }

        public String getBal() {
            return bal;
        }

        public void setBal(String bal) {
            this.bal = bal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPsn_acct() {
            return psn_acct;
        }

        public void setPsn_acct(String psn_acct) {
            this.psn_acct = psn_acct;
        }

        public String getReal_draw_int() {
            return real_draw_int;
        }

        public void setReal_draw_int(String real_draw_int) {
            this.real_draw_int = real_draw_int;
        }

        public String getReal_draw_prin() {
            return real_draw_prin;
        }

        public void setReal_draw_prin(String real_draw_prin) {
            this.real_draw_prin = real_draw_prin;
        }

        public String getSjtqrq() {
            return sjtqrq;
        }

        public void setSjtqrq(String sjtqrq) {
            this.sjtqrq = sjtqrq;
        }

        public String getSjtqzje() {
            return sjtqzje;
        }

        public void setSjtqzje(String sjtqzje) {
            this.sjtqzje = sjtqzje;
        }

        public String getZqfs() {
            return zqfs;
        }

        public void setZqfs(String zqfs) {
            this.zqfs = zqfs;
        }

        public String getZqywlx() {
            return zqywlx;
        }

        public void setZqywlx(String zqywlx) {
            this.zqywlx = zqywlx;
        }

        public String getZqyy() {
            return zqyy;
        }

        public void setZqyy(String zqyy) {
            this.zqyy = zqyy;
        }
    }
}
