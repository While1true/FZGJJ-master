package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/8/23.
 */

public class BeanBgxzList {
    /**
     * data : [{"createdate":"2017-05-09 09:26:30.0","filename":"2017年05月03日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beace731c0009\\"},{"createdate":"2017-05-09 09:46:43.0","filename":"2017年05月02日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beae0f69f0010\\"},{"createdate":"2017-05-09 08:36:53.0","fileurl":"drowFlies\\20170509\\8a80ee155beaa06e015beaa0f10b0009\\"},{"createdate":"2017-05-09 09:14:21.0","filename":"2017年05月03日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beac353620008\\"},{"createdate":"2017-05-09 09:26:30.0","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beace731c0009\\"},{"createdate":"2017-05-09 09:46:43.0","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beae0f69f0010\\"},{"createdate":"2017-05-09 08:36:53.0","filename":"2017年05月08日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beaa06e015beaa0f10b0009\\"},{"createdate":"2017-05-09 09:14:21.0","filename":"2017年05月05日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beac353620008\\"},{"createdate":"2017-05-09 09:14:21.0","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beac353620008\\"},{"createdate":"2017-05-09 09:26:30.0","filename":"2017年05月04日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beace731c0009\\"},{"createdate":"2017-05-09 09:26:30.0","filename":"2017年05月03日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beace731c0009\\"},{"createdate":"2017-05-09 09:46:43.0","filename":"2017年05月05日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beae0f69f0010\\"},{"createdate":"2017-05-09 09:46:43.0","filename":"desktop.ini","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beae0f69f0010\\"},{"createdate":"2017-05-09 08:36:53.0","filename":"2017年05月04日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beaa06e015beaa0f10b0009\\"},{"createdate":"2017-05-09 09:14:21.0","filename":"2017年05月03日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beac353620008\\"},{"createdate":"2017-05-09 09:26:30.0","filename":"2017年05月08日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beace731c0009\\"},{"createdate":"2017-05-09 09:46:43.0","filename":"2017年05月08日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beae0f69f0010\\"},{"createdate":"2017-05-09 08:36:53.0","filename":"2017年05月02日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beaa06e015beaa0f10b0009\\"},{"createdate":"2017-05-09 09:14:21.0","filename":"2017年05月03日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beac244015beac353620008\\"},{"createdate":"2017-05-09 08:36:53.0","filename":"2017年05月02日工作报告_林珑.xls","fileurl":"drowFlies\\20170509\\8a80ee155beaa06e015beaa0f10b0009\\"}]
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
         * createdate : 2017-05-09 09:26:30.0
         * filename : 2017年05月03日工作报告_林珑.xls
         * fileurl : drowFlies\20170509\8a80ee155beac244015beace731c0009\
         */

        private String createdate;
        private String filename;
        private String fileurl;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getFileurl() {
            return fileurl;
        }

        public void setFileurl(String fileurl) {
            this.fileurl = fileurl;
        }
    }
}
