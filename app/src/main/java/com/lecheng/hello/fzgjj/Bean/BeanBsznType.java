package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/4.
 */

public class BeanBsznType {
    /*{"data":[{"code":"GJJKH","id":"7","sort":"1","title":"公积金开户"},{"code":"ZFGJJJC","id":"8","sort":"2","title":"住房公积金缴存"},{"code":"ZFGJJZQ","id":"9","sort":"3","title":"住房公积金支取"},{"code":"ZFGJJDK","id":"10","sort":"4","title":"住房公积金贷款"},{"code":"ZFGJJBG","id":"33","sort":"5","title":"住房公积金变更"},{"code":"ZFGJJJSHD","id":"34","sort":"6","title":"住房公积金基数核定"},{"code":"ZFGJJCHD","id":"35","sort":"7","title":"住房公积金冲还贷"},{"code":"ZFGJJZHCX","id":"36","sort":"8","title":"住房公积金账户查询"},{"code":"DWTJYHJ","id":"37","sort":"9","title":"单位停缴与缓缴"},{"code":"GJJZY","id":"38","sort":"10","title":"公积金转移"}],"status":"1"}*/
    /**
     * data : [{"code":"GJJKH","id":"7","sort":"1","title":"公积金开户"},{"code":"ZFGJJJC","id":"8","sort":"2","title":"住房公积金缴存"},{"code":"ZFGJJZQ","id":"9","sort":"3","title":"住房公积金支取"},{"code":"ZFGJJDK","id":"10","sort":"4","title":"住房公积金贷款"},{"code":"ZFGJJBG","id":"33","sort":"5","title":"住房公积金变更"},{"code":"ZFGJJJSHD","id":"34","sort":"6","title":"住房公积金基数核定"},{"code":"ZFGJJCHD","id":"35","sort":"7","title":"住房公积金冲还贷"},{"code":"ZFGJJZHCX","id":"36","sort":"8","title":"住房公积金账户查询"},{"code":"DWTJYHJ","id":"37","sort":"9","title":"单位停缴与缓缴"},{"code":"GJJZY","id":"38","sort":"10","title":"公积金转移"}]
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
         * code : GJJKH
         * id : 7
         * sort : 1
         * title : 公积金开户
         */

        private String code;
        private String id;
        private String sort;
        private String title;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
