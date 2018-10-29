package com.miracle.sport.community.bean;

/**
 * Created by Michael on 2018/10/27 15:43 (星期六)
 */
public class PostBean {


    /**
     * id : 1
     * title : 我的
     * thumb : null
     * total : 1
     * add_time : 2018-10-29 11:38:11
     */

    private int id;
    private String title;
    private String thumb;
    private int total;
    private String add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
