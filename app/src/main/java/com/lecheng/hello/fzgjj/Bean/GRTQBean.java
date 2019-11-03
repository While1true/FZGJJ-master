package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by vange on 2017/11/6.
 */

public class GRTQBean {

    /**
     * data : [{"ACCOUNTBALANCE":"10044.97","PSN_NAME":"罗烽 ","YEAR_DRAW_AMT":"0"}]
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
         * ACCOUNTBALANCE : 10044.97
         * PSN_NAME : 罗烽
         * YEAR_DRAW_AMT : 0
         */

        private String draw_no;
        private String draw_prin;
        private String sreal_draw_date;
        private String draw_mode;
        private String draw_rea;

        public String getDraw_no() {
            return draw_no;
        }

        public void setDraw_no(String draw_no) {
            this.draw_no = draw_no;
        }

        public String getDraw_prin() {
            return draw_prin;
        }

        public void setDraw_prin(String draw_prin) {
            this.draw_prin = draw_prin;
        }

        public String getSreal_draw_date() {
            return sreal_draw_date;
        }

        public void setSreal_draw_date(String sreal_draw_date) {
            this.sreal_draw_date = sreal_draw_date;
        }

        public String getDraw_mode() {
            return draw_mode;
        }

        public void setDraw_mode(String draw_mode) {
            this.draw_mode = draw_mode;
        }

        public String getDraw_rea() {
            return draw_rea;
        }

        public void setDraw_rea(String draw_rea) {
            this.draw_rea = draw_rea;
        }
    }
}
