package com.lecheng.hello.fzgjj.Bean;

import org.litepal.crud.DataSupport;

/**
 * Created by vange on 2017/11/14.
 */

public class TQBean extends DataSupport {
    String bookingDate;
    String msg;
    String username;
    String bookingTime;
    String appcode;
    int type = 1;

    public TQBean(String bookingDate, String msg, String username, String bookingTime, String appcode, int type) {
        this.bookingDate = bookingDate;
        this.msg = msg;
        this.username = username;
        this.bookingTime = bookingTime;
        this.appcode = appcode;
        this.type = type;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
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

    public void setMsg(String appcodename) {
        this.msg = appcodename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppcode() {
        return appcode;
    }

    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TQBean{" +
                "bookingDate='" + bookingDate + '\'' +
                ", msg='" + msg + '\'' +
                ", username='" + username + '\'' +
                ", bookingTime='" + bookingTime + '\'' +
                ", appcode='" + appcode + '\'' +
                ", type=" + type +
                '}';
    }
}
