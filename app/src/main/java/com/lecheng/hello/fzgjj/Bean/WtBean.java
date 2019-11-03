package com.lecheng.hello.fzgjj.Bean;

/**
 * Created by vange on 2017/11/16.
 */

public class WtBean {

    /**
     * data : 0
     * msg : 系统正在维护升级中,请稍候。
     * status : 0
     */

    private int booking_type;
    private String end;
    private String wait;
    private String update_time;

    public int getBooking_type() {
        return booking_type;
    }

    public void setBooking_type(int booking_type) {
        this.booking_type = booking_type;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
