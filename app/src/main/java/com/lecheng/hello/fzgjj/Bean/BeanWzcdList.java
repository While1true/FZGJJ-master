package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/2.
 */

public class BeanWzcdList {
    /**
     * data : [{"code":"ZFGJJBG","levels":"2","pcode":"BSZN","title":"住房公积金变更"},{"code":"ZFGJJJSHD","levels":"2","pcode":"BSZN","title":"住房公积金基数核定"},{"code":"ZFGJJCHD","levels":"2","pcode":"BSZN","title":"住房公积金冲还贷"},{"code":"ZFGJJZHCX","levels":"2","pcode":"BSZN","title":"住房公积金账户查询"},{"code":"DWTJYHJ","levels":"2","pcode":"BSZN","title":"单位停缴与缓缴"},{"code":"GJJZY","levels":"2","pcode":"BSZN","title":"公积金转移"},{"code":"GJJKH","levels":"2","pcode":"BSZN","title":"公积金开户"},{"code":"ZFGJJJC","levels":"2","pcode":"BSZN","title":"住房公积金缴存"},{"code":"ZFGJJZQ","levels":"2","pcode":"BSZN","title":"住房公积金支取"},{"code":"ZFGJJDK","levels":"2","pcode":"BSZN","title":"住房公积金贷款"}]
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
         * code : ZFGJJBG
         * levels : 2
         * pcode : BSZN
         * title : 住房公积金变更
         */

        private String code;
        private String levels;
        private String pcode;
        private String title;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLevels() {
            return levels;
        }

        public void setLevels(String levels) {
            this.levels = levels;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
