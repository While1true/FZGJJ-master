package com.lecheng.hello.fzgjj.Bean;


import java.util.List;

/**
 * Created by vange on 2017/9/14.
 */

public class CalculateBean {

    /**
     * gjjdebj : {"dkys":"12（月）","hkze":"0","sqfk":"0","yhje":"0元","zflxk":"0"}
     * gjjdebx : {"dkys":"12（月）","hkze":"0","sqfk":"0","yhje":"0元","zflxk":"0"}
     * sydebj : {"dkys":"12（月）","hkze":"10235.62","sqfk":"869.58","yhje":"12月,836.35(元) ","zflxk":"235.62"}
     * sydebx : {"dkys":"12（月）","hkze":"10237.19","sqfk":"853.10","yhje":"853.10（元）","zflxk":"237.19"}
     */

    private Data gjjdebj;
    private Data gjjdebx;
    private Data sydebj;
    private Data sydebx;

    public Data getGjjdebj() {
        return gjjdebj;
    }

    public void setGjjdebj(Data gjjdebj) {
        this.gjjdebj = gjjdebj;
    }

    public Data getGjjdebx() {
        return gjjdebx;
    }

    public void setGjjdebx(Data gjjdebx) {
        this.gjjdebx = gjjdebx;
    }

    public Data getSydebj() {
        return sydebj;
    }

    public void setSydebj(Data sydebj) {
        this.sydebj = sydebj;
    }

    public Data getSydebx() {
        return sydebx;
    }

    public void setSydebx(Data sydebx) {
        this.sydebx = sydebx;
    }
    public  static class Data{

        /**
         * dkys : 12（月）
         * hkze : 10235.62
         * sqfk : 869.58
         * yhje : ["12月,836.35(元) "]
         * zflxk : 235.62
         */

        private String dkys;
        private String hkze;
        private String sqfk;
        private String zflxk;
        private List<String> yhje;

        public String getDkys() {
            return dkys;
        }

        public void setDkys(String dkys) {
            this.dkys = dkys;
        }

        public String getHkze() {
            return hkze;
        }

        public void setHkze(String hkze) {
            this.hkze = hkze;
        }

        public String getSqfk() {
            return sqfk;
        }

        public void setSqfk(String sqfk) {
            this.sqfk = sqfk;
        }

        public String getZflxk() {
            return zflxk;
        }

        public void setZflxk(String zflxk) {
            this.zflxk = zflxk;
        }

        public List<String> getYhje() {
            return yhje;
        }

        public void setYhje(List<String> yhje) {
            this.yhje = yhje;
        }
    }
}
