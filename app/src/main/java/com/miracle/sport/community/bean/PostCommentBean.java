package com.miracle.sport.community.bean;

/**
 * Created by Michael on 2018/10/30 22:18 (星期二)
 */
public class PostCommentBean {


    /**
     * comment_id : 4
     * user_id : 12
     * nickname : Michael
     * content : hhghhj
     * add_time : 2018-10-31 11:38:39
     * click_num : 1
     * comment_num : 10
     */

    private String img;
    private int comment_id;
    private int user_id;
    private String nickname;
    private String content;
    private String add_time;
    private int click_num;
    private int comment_num;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getClick_num() {
        return click_num;
    }

    public void setClick_num(int click_num) {
        this.click_num = click_num;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }
}
