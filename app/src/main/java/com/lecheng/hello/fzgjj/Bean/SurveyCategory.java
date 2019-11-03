package com.lecheng.hello.fzgjj.Bean;

/**
 * Created by vange on 2017/9/21.
 */

public class SurveyCategory {

    /**
     * id : 23
     * onlines : 0
     * term : 2017-07-11 -2017-08-02
     * title : 国际经济1
     */

    private String id;
    private int onlines;
    private String term;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOnlines() {
        return onlines;
    }

    public void setOnlines(int onlines) {
        this.onlines = onlines;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "SurveyCategory{" +
                "id='" + id + '\'' +
                ", onlines=" + onlines +
                ", term='" + term + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
