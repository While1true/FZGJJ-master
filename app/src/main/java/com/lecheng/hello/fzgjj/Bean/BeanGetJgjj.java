package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/4.
 */

public class BeanGetJgjj {
    /**
     * data : [{"content":"<p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<span style=\"font-family: tahoma, 宋体; font-size: 21.3333px; text-indent: 49.0667px; background-color: rgb(255, 255, 255);\">福建省直单位住房公积金管理中心于1997年1月成立，是不以营利为目的的参照公务员法管理的正处级事业法人单位，隶属福建省机关事务管理局，负责省直和中央驻榕3000多家单位26万多干部职工的住房公积金管理工作。<\/span><\/p>","fbt":"中心简介的标题","id":"101","title":"中心简介的标题","twolevel":"ZXJJ","typeName":"中心简介"},{"content":"<p>陈岩石，老红军，17岁背炸药包。<\/p>","fbt":"领导","id":"102","title":"中心领导标题","twolevel":"ZXLD","typeName":"中心领导"},{"content":"<p>机构设置如图<\/p>","fbt":"机构设置标题","id":"103","title":"机构设置标题","twolevel":"JGSZ","typeName":"机构设置"},{"content":"<p>部门职能如下，程序员<\/p>","fbt":"部门职能","id":"104","title":"部门职能标题","twolevel":"BMZN","typeName":"部门职能"}]
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
         * content : <p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<span style="font-family: tahoma, 宋体; font-size: 21.3333px; text-indent: 49.0667px; background-color: rgb(255, 255, 255);">福建省直单位住房公积金管理中心于1997年1月成立，是不以营利为目的的参照公务员法管理的正处级事业法人单位，隶属福建省机关事务管理局，负责省直和中央驻榕3000多家单位26万多干部职工的住房公积金管理工作。</span></p>
         * fbt : 中心简介的标题
         * id : 101
         * title : 中心简介的标题
         * twolevel : ZXJJ
         * typeName : 中心简介
         */

        private String content;
        private String fbt;
        private String id;
        private String title;
        private String twolevel;
        private String typeName;

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

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }
    }
    //机构简介

}
