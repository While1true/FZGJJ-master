package com.lecheng.hello.fzgjj.Activity.H2;

import java.util.List;

/**
 * Created by vange on 2017/11/1.
 */

public class YuYueBean {


    /**
     * data : [{"bookingtime":"1","number":"10","showtime":"08:30-09:00","yydate":"20171103"},{"bookingtime":"2","number":"10","showtime":"09:00-09:30","yydate":"20171103"},{"bookingtime":"3","number":"10","showtime":"09:30-10:00","yydate":"20171103"},{"bookingtime":"4","number":"10","showtime":"10:00-10:30","yydate":"20171103"},{"bookingtime":"5","number":"10","showtime":"10:30-11:00","yydate":"20171103"},{"bookingtime":"6","number":"10","showtime":"11:00-11:30","yydate":"20171103"},{"bookingtime":"7","number":"10","showtime":"14:30-15:00","yydate":"20171103"},{"bookingtime":"8","number":"10","showtime":"15:00-15:30","yydate":"20171103"},{"bookingtime":"9","number":"10","showtime":"15:30-16:00","yydate":"20171103"},{"bookingtime":"10","number":"10","showtime":"16:00-16:30","yydate":"20171103"}]
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
         * bookingtime : 1
         * number : 10
         * showtime : 08:30-09:00
         * yydate : 20171103
         */

        private String bookingtime;
        private String number;
        private String showtime;
        private String yydate;

        public String getBookingtime() {
            return bookingtime;
        }

        public void setBookingtime(String bookingtime) {
            this.bookingtime = bookingtime;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getShowtime() {
            return showtime;
        }

        public void setShowtime(String showtime) {
            this.showtime = showtime;
        }

        public String getYydate() {
            return yydate;
        }

        public void setYydate(String yydate) {
            this.yydate = yydate;
        }
    }
}
