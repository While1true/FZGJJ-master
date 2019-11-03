package com.lecheng.hello.fzgjj.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vange on 2017/10/9.
 */

public class YYCode {

    /**
     * data : [{"bookingdate":"2017-11-20","bookingtime":"08:30-09:30","bookingtype":"2"}]
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

    public static class DataBean implements Serializable{
        /**
         * bookingdate : 2017-11-20
         * bookingtime : 08:30-09:30
         * bookingtype : 2
         */

        private String bookingdate;
        private String bookingtime;
        private int bookingtype;

        public String getBookingdate() {
            return bookingdate;
        }

        public void setBookingdate(String bookingdate) {
            this.bookingdate = bookingdate;
        }

        public String getBookingtime() {
            return bookingtime;
        }

        public void setBookingtime(String bookingtime) {
            this.bookingtime = bookingtime;
        }

        public int getBookingtype() {
            return bookingtype;
        }

        public void setBookingtype(int bookingtype) {
            this.bookingtype = bookingtype;
        }
    }
}
