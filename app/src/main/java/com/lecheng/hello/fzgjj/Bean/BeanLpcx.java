package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/19.
 */

public class BeanLpcx {
    /**
     * data : [{"kfname":"闽清深深房地产开发有限公司","lpcode":"lp1600002017000084","lpname":"博仕后家园3#、7#、11#","pwcode":"2016梅房许字第021号"}]
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
         * kfname : 闽清深深房地产开发有限公司
         * lpcode : lp1600002017000084
         * lpname : 博仕后家园3#、7#、11#
         * pwcode : 2016梅房许字第021号
         */

        private String kfname;
        private String lpcode;
        private String lpname;
        private String pwcode;

        public String getKfname() {
            return kfname;
        }

        public void setKfname(String kfname) {
            this.kfname = kfname;
        }

        public String getLpcode() {
            return lpcode;
        }

        public void setLpcode(String lpcode) {
            this.lpcode = lpcode;
        }

        public String getLpname() {
            return lpname;
        }

        public void setLpname(String lpname) {
            this.lpname = lpname;
        }

        public String getPwcode() {
            return pwcode;
        }

        public void setPwcode(String pwcode) {
            this.pwcode = pwcode;
        }
    }
}
