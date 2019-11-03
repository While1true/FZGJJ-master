package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/25.
 */

public class BeanZjdc {
    /**
     * data : [{"choosea":"fasdf","chooseb":"fasdf","choosec":"adfadf","choosed":"afdfad","starttime":"2017-04-26 14:16:01.0","title":"dfadfadf","type":"1"},{"choosea":"sdasd","chooseb":"sdasd","choosec":"asdfa","choosed":"dfadfad","starttime":"2017-04-26 14:17:04.0","title":"dfadfadf","type":"1"},{"choosea":"adfad","chooseb":"adfad","choosec":"fad","choosed":"fadfadf","starttime":"2017-04-26 14:17:51.0","title":"dfadfad","type":"2"},{"choosea":"2323","chooseb":"2323","choosec":"23","choosed":"23","starttime":"2017-04-26 17:38:00.0","title":"关于住房公积金缴存","type":"1"},{"choosea":"0只","chooseb":"0只","choosec":"9只","choosed":"10只","starttime":"2017-04-13 09:28:17.0","title":"树上10只鸟，被猎人打死一只一只，请问还剩几只？","type":"1"},{"choosea":"32","chooseb":"32","choosec":"123","choosed":"23","starttime":"2017-04-13 09:42:14.0","title":"脑筋急转弯3","type":"2"},{"choosea":"dfadf","chooseb":"dfadf","choosec":"adf","choosed":"adfad","starttime":"2017-04-26 14:14:37.0","title":"ddfadf","type":"1"},{"choosea":"afadfadf","chooseb":"afadfadf","choosec":"asdfasdf","choosed":"asdfasdf","starttime":"2017-04-26 14:19:01.0","title":"ddfadf233","type":"2"},{"choosea":"1","chooseb":"1","choosec":"3","choosed":"4","starttime":"2017-04-13 09:41:08.0","title":"脑筋急转弯","type":"1"},{"choosea":"2","chooseb":"2","choosec":"21","choosed":"3","starttime":"2017-04-13 09:41:54.0","title":"脑筋急转弯1","type":"2"},{"choosea":"adfad","chooseb":"adfad","choosec":"adf","choosed":"adfadf","starttime":"2017-04-26 14:16:34.0","title":"ddfadffadfadf","type":"2"}]
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
         * choosea : fasdf
         * chooseb : fasdf
         * choosec : adfadf
         * choosed : afdfad
         * starttime : 2017-04-26 14:16:01.0
         * title : dfadfadf
         * type : 1
         */

        private String choosea;
        private String chooseb;
        private String choosec;
        private String choosed;
        private String starttime;
        private String title;
        private String type;

        public String getChoosea() {
            return choosea;
        }

        public void setChoosea(String choosea) {
            this.choosea = choosea;
        }

        public String getChooseb() {
            return chooseb;
        }

        public void setChooseb(String chooseb) {
            this.chooseb = chooseb;
        }

        public String getChoosec() {
            return choosec;
        }

        public void setChoosec(String choosec) {
            this.choosec = choosec;
        }

        public String getChoosed() {
            return choosed;
        }

        public void setChoosed(String choosed) {
            this.choosed = choosed;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
    /*{"data":[{"choosea":"fasdf","chooseb":"fasdf","choosec":"adfadf","choosed":"afdfad","starttime":"2017-04-26 14:16:01.0","title":"dfadfadf","type":"1"},{"choosea":"sdasd","chooseb":"sdasd","choosec":"asdfa","choosed":"dfadfad","starttime":"2017-04-26 14:17:04.0","title":"dfadfadf","type":"1"},{"choosea":"adfad","chooseb":"adfad","choosec":"fad","choosed":"fadfadf","starttime":"2017-04-26 14:17:51.0","title":"dfadfad","type":"2"},{"choosea":"2323","chooseb":"2323","choosec":"23","choosed":"23","starttime":"2017-04-26 17:38:00.0","title":"关于住房公积金缴存","type":"1"},{"choosea":"0只","chooseb":"0只","choosec":"9只","choosed":"10只","starttime":"2017-04-13 09:28:17.0","title":"树上10只鸟，被猎人打死一只一只，请问还剩几只？","type":"1"},{"choosea":"32","chooseb":"32","choosec":"123","choosed":"23","starttime":"2017-04-13 09:42:14.0","title":"脑筋急转弯3","type":"2"},{"choosea":"dfadf","chooseb":"dfadf","choosec":"adf","choosed":"adfad","starttime":"2017-04-26 14:14:37.0","title":"ddfadf","type":"1"},{"choosea":"afadfadf","chooseb":"afadfadf","choosec":"asdfasdf","choosed":"asdfasdf","starttime":"2017-04-26 14:19:01.0","title":"ddfadf233","type":"2"},{"choosea":"1","chooseb":"1","choosec":"3","choosed":"4","starttime":"2017-04-13 09:41:08.0","title":"脑筋急转弯","type":"1"},{"choosea":"2","chooseb":"2","choosec":"21","choosed":"3","starttime":"2017-04-13 09:41:54.0","title":"脑筋急转弯1","type":"2"},{"choosea":"adfad","chooseb":"adfad","choosec":"adf","choosed":"adfadf","starttime":"2017-04-26 14:16:34.0","title":"ddfadffadfadf","type":"2"}],"status":"1"}*/

}
