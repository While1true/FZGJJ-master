package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/4.
 */

public class BeanBgt {

    /**
     * data : [{"id":"106","title":"曝光台2","twolevel":"BGT","updatedate":"2017-05-03"},{"id":"105","title":"曝光台1","twolevel":"BGT","updatedate":"2017-05-01"}]
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
         * id : 106
         * title : 曝光台2
         * twolevel : BGT
         * updatedate : 2017-05-03
         */

        private String id;
        private String title;
        private String twolevel;
        private String updatedate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTwolevel() {
            return twolevel;
        }

        public void setTwolevel(String twolevel) {
            this.twolevel = twolevel;
        }

        public String getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(String updatedate) {
            this.updatedate = updatedate;
        }
    }
}
