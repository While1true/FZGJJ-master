package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/6/29.
 */

public class BeanHkcx {
    /**
     * data : [{"ACURTERM_RTN_PRIN":"455","SCURTERM_RTN_AMT":"5000"}]
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
         * ACURTERM_RTN_PRIN : 455
         * SCURTERM_RTN_AMT : 5000
         */

        private String ACURTERM_RTN_PRIN;
        private String SCURTERM_RTN_AMT;

        public String getACURTERM_RTN_PRIN() {
            return ACURTERM_RTN_PRIN;
        }

        public void setACURTERM_RTN_PRIN(String ACURTERM_RTN_PRIN) {
            this.ACURTERM_RTN_PRIN = ACURTERM_RTN_PRIN;
        }

        public String getSCURTERM_RTN_AMT() {
            return SCURTERM_RTN_AMT;
        }

        public void setSCURTERM_RTN_AMT(String SCURTERM_RTN_AMT) {
            this.SCURTERM_RTN_AMT = SCURTERM_RTN_AMT;
        }
    }
}
