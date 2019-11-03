package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/19.
 */

public class BeanZlcx {
    /**
     * data : [{"credit_code":"35010219921111581x","mobile":"18705011735","name":"林思晗"}]
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
         * credit_code : 35010219921111581x
         * mobile : 18705011735
         * name : 林思晗
         */

        private String credit_code;

        public String getCredit_type() {
            return credit_type;
        }

        public void setCredit_type(String credit_type) {
            this.credit_type = credit_type;
        }

        private String credit_type;
        private String mobile;
        private String name;

        public String getCredit_code() {
            return credit_code;
        }

        public void setCredit_code(String credit_code) {
            this.credit_code = credit_code;
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
    }
}
