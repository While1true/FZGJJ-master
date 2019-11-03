package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/27.
 */

public class BeanPtss {
    /**
     * data : [{"lmdadd":"/","title":"单位申请住房公积金账户开户办事指南","twolevel":"GJJKH","updatedate":"2017-01-01 09:55:18.0","content":"内容"},{"lmdadd":"/","title":"省直住房公积金管理中心","twolevel":"BMZN","updatedate":"2017-05-24 17:11:49.0"},{"fbt":"公积金办事","gjz":"公积金办事","lmdadd":"/","source":"公积金办事","title":"调离本省提取公积金办事指南","twolevel":"ZXLD","updatedate":"2017-06-03 18:12:09.0","zhaiyao":"公积金办事"},{"lmdadd":"/","title":"与单位终止劳动关系未再就业提取公积金办事指南","twolevel":"请选择","updatedate":"2017-06-03 18:13:08.0"},{"fbt":"公积金支取","gjz":"支取","lmdadd":"/","source":"网络","title":"公积金支取相关问题","twolevel":"CJWT","updatedate":"2017-05-11 00:00:00.0","zhaiyao":"支取"},{"lmdadd":"/","title":"出境出国定居提取住房公积金办事指南","twolevel":"ZFGJJZQ","updatedate":"2017-01-01 21:17:56.0"},{"lmdadd":"/","title":"无房支付房租提取住房公积金办事指南","twolevel":"ZFGJJZQ","updatedate":"2017-01-01 21:14:56.0"},{"lmdadd":"/","title":"异地缴存公积金职工购买二手房公积金(组合、贴息)贷款办事指南(201703)","twolevel":"ZFGJJDK","updatedate":"2017-03-03 10:42:53.0"},{"lmdadd":"/","title":"异地缴存公积金职工购买商品住房公积金(组合、贴息)贷款办事指南(201703)","twolevel":"ZFGJJDK","updatedate":"2017-03-03 10:41:53.0"},{"lmdadd":"/","title":"省直缴存公积金职工购买商品住房公积金(组合、贴息)贷款办事指南(201703)","twolevel":"ZFGJJDK","updatedate":"2017-03-03 10:39:53.0"},{"lmdadd":"/","title":"省直缴存公积金职工购买二手房公积金(组合、贴息)贷款办事指南(201703)","twolevel":"ZFGJJDK","updatedate":"2017-03-03 10:38:53.0"},{"lmdadd":"/","title":"关于印发省直住房公积金贷款业务办事指南的通知","twolevel":"ZFGJJDK","updatedate":"2017-03-03 10:37:53.0"},{"lmdadd":"/","title":"关于公布住房公积金贷款流动性调节系数的公告","twolevel":"TZGG","updatedate":"2016-06-03 17:40:59.0"},{"fbt":"公积金志愿者","gjz":"公积金志愿者","lmdadd":"/","title":"文明交通劝导，公积金志愿者在行动","twolevel":"DJGZ","updatedate":"2017-06-03 21:57:00.0"},{"fbt":"服务进厦航","gjz":"厦航","lmdadd":"ueditor/jsp/upload/image/20170603/1496491463856091575.bmp","source":"公积金中心","title":"省直公积金中心携手工行公积金贷款服务进厦航","twolevel":"TPXW","updatedate":"2017-06-25 10:36:36.0","zhaiyao":"厦航"},{"fbt":"公积金中心","gjz":"公积金中心","lmdadd":"ueditor/jsp/upload/image/20170603/1496462074434000478.bmp","source":"公积金中心","title":"公积金中心","twolevel":"TPXW","updatedate":"2017-06-03 18:54:34.0","zhaiyao":"公积金中心"},{"fbt":"省直公积金中心开通跨行冲还贷业务","lmdadd":"/","source":"福建省直单位住房公积金管理中心","title":"省直公积金中心开通跨行冲还贷业务","twolevel":"GZDT","updatedate":"2017-01-26 10:05:55.0"},{"lmdadd":"/","title":"公积金宣传走进社区","twolevel":"JGFC","updatedate":"2017-06-05 09:49:22.0"},{"fbt":"1","lmdadd":"/","title":"公积金缴存","twolevel":"ZFGJJJC","updatedate":"2017-06-05 11:25:35.0","zhaiyao":"1"},{"lmdadd":"/","title":"职工与单位解除劳动关系且未重新就业提取住房公积金办事指南","twolevel":"ZFGJJZQ","updatedate":"2017-01-01 21:24:56.0"}]
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
         * lmdadd : /
         * title : 单位申请住房公积金账户开户办事指南
         * twolevel : GJJKH
         * updatedate : 2017-01-01 09:55:18.0
         * content : 内容
         * fbt : 公积金办事
         * gjz : 公积金办事
         * source : 公积金办事
         * zhaiyao : 公积金办事
         */

        private String lmdadd;
        private String title;
        private String twolevel;
        private String updatedate;
        private String content;
        private String fbt;
        private String gjz;
        private String source;
        private String zhaiyao;

        public String getLmdadd() {
            return lmdadd;
        }

        public void setLmdadd(String lmdadd) {
            this.lmdadd = lmdadd;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFbt() {
            return fbt;
        }

        public void setFbt(String fbt) {
            this.fbt = fbt;
        }

        public String getGjz() {
            return gjz;
        }

        public void setGjz(String gjz) {
            this.gjz = gjz;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getZhaiyao() {
            return zhaiyao;
        }

        public void setZhaiyao(String zhaiyao) {
            this.zhaiyao = zhaiyao;
        }
    }
}
