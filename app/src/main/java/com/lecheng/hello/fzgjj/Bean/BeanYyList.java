package com.lecheng.hello.fzgjj.Bean;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.util.Log;

import com.lecheng.hello.fzgjj.Constance;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/28.
 */

public class BeanYyList {
    /**
     * data : [{"bookingtime":"2","date":"2017-06-29","number":"0","xingqi":"4"},{"$ref":"$.data[9]"},{"bookingtime":"2","date":"2017-06-30","number":"0","xingqi":"5"},{"$ref":"$.data[9]"},{"bookingtime":"2","date":"2017-07-03","number":"0","xingqi":"1"},{"$ref":"$.data[9]"},{"bookingtime":"2","date":"2017-07-04","number":"0","xingqi":"2"},{"$ref":"$.data[9]"},{"bookingtime":"2","date":"2017-07-05","number":"1","xingqi":"3"},{"$ref":"$.data[9]"}]
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
         * bookingtime : 2
         * date : 2017-06-29
         * number : 0
         * xingqi : 4
         * $ref : $.data[9]
         */

        private String bookingtime;
        private String date;
        private String number;
        private String xingqi;
        private String worktime;
        private String $ref;

        public String getWorktime() {
            return worktime;
        }

        public void setWorktime(String worktime) {
            this.worktime = worktime;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        private String count;

        public String getBookingtime() {
            return bookingtime;
        }

        public void setBookingtime(String bookingtime) {
            this.bookingtime = bookingtime;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getXingqi() {
            return xingqi;
        }

        public void setXingqi(String xingqi) {
            this.xingqi = xingqi;
        }

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }

        public long getLong() {

            return Long.parseLong(date.replace("-", ""));
        }
    }

}
