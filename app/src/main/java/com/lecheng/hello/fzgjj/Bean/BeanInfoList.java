package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/2.
 */

public class BeanInfoList {
    /**
     * data : [{"content":"<p>叶成伟大傻逼<\/p>","fbt":"法律法规副标题","gjz":"法律法规","lmdadd":"/","source":"网络","title":"法律法规标题","twolevel":"FLFG","updatedate":"2017-05-04 00:00:00.0","zhaiyao":"法律法规"}]
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

    @Override
    public String toString() {
        return "BeanInfoList{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "content='" + content + '\'' +
                    ", fbt='" + fbt + '\'' +
                    ", gjz='" + gjz + '\'' +
                    ", lmdadd='" + lmdadd + '\'' +
                    ", source='" + source + '\'' +
                    ", title='" + title + '\'' +
                    ", twolevel='" + twolevel + '\'' +
                    ", updatedate='" + updatedate + '\'' +
                    ", zhaiyao='" + zhaiyao + '\'' +
                    '}';
        }

        /**
         * content : <p>叶成伟大傻逼</p>
         * fbt : 法律法规副标题
         * gjz : 法律法规
         * lmdadd : /
         * source : 网络
         * title : 法律法规标题
         * twolevel : FLFG
         * updatedate : 2017-05-04 00:00:00.0
         * zhaiyao : 法律法规
         */

        private String content;
        private String fbt;
        private String gjz;
        private String lmdadd;
        private String source;
        private String title;
        private String twolevel;
        private String updatedate;
        private String zhaiyao;

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

        public String getLmdadd() {
            return lmdadd;
        }

        public void setLmdadd(String lmdadd) {
            this.lmdadd = lmdadd;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
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

        public String getZhaiyao() {
            return zhaiyao;
        }

        public void setZhaiyao(String zhaiyao) {
            this.zhaiyao = zhaiyao;
        }
    }
}
