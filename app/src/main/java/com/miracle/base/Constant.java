package com.miracle.base;

/**
 * Created by Administrator on 2018/3/1.
 */

public interface Constant {

    /**已选中频道的json*/
    public static final String SELECTED_CHANNEL_JSON = "selectedChannelJson";
    /**w未选频道的json*/
    public static final String UNSELECTED_CHANNEL_JSON = "unselectChannelJson";

    /**频道对应的请求参数*/
    public static final String CHANNEL_CODE = "channelCode";
    public static final String IS_VIDEO_LIST = "isVideoList";

    public static final String DATA_SELECTED = "dataSelected";
    public static final String DATA_UNSELECTED = "dataUnselected";

    int QQLOGIN = 1;
    int LINKENDINLOGIN = 2;
    int WECHATLOGIN = 3;


    int REQUSET_CODE_MEDIA = 0;
    int REQUSET_CODE_CAMERA = 1;
    int REQUSET_CODE_MEFRAGMENT_ACCOUNTCENTER = 2;


    int FROM_ProductDetailsAct = 0;
    int FROM_MeFragment = 1;


    String USER_ID = "";
    String USER_INFO = "user_info";
    String APPID = "";
    String TITLE = "title";
    String REQKEY = "reqKey";
    String CONTENT = "content";

    String testName = "18888888888";
    String testPassword = "888888";

    //QQ聊天
    String qqUrl = "mqq://im/chat?chat_type=crm&uin=%1$s&version=1&src_type=web";
    //微信聊天
    String wxUrl = "vnd.android.cursor.item/vnd.com.tencent.mm.chatting.profile";
}
