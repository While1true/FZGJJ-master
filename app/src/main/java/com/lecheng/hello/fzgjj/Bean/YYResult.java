package com.lecheng.hello.fzgjj.Bean;

import java.io.Serializable;

import RxWeb.GsonUtil;

/**
 * Created by vange on 2017/11/3.
 */

public class YYResult {

    /**
     * data : {"bookingDate":"2017-11-07","msg":"办理提取业务","success":"1","worktime":"08:30-11:00"}
     * status : 1
     */

    private DataBean data;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean implements Serializable {
        /**
         * bookingDate : 2017-11-07
         * msg : 办理提取业务
         * success : 1
         * worktime : 08:30-11:00
         */

        private String bookingDate;
        private String msg;
        private String success;
        private String worktime;
        private String username;
        private String mobile;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getMsg() {
            return msg;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getWorktime() {
            return worktime;
        }

        public void setWorktime(String worktime) {
            this.worktime = worktime;
        }

        @Override
        public String toString() {
            return GsonUtil.GsonString(this);
        }
    }
}
