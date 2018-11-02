package com.miracle.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.miracle.base.bean.UserInfoBean;
import com.miracle.base.im.ui.ChatActivity;
import com.miracle.base.switcher.WelcomeActivity;
import com.miracle.base.util.CommonUtils;
import com.miracle.base.util.ContextHolder;
import com.miracle.base.util.ToastUtil;
import com.miracle.michael.common.activity.AboutUsActivity;
import com.miracle.michael.common.activity.CircleTurntableActivity;
import com.miracle.michael.common.activity.CustomerServiceActivity;
import com.miracle.michael.common.activity.LoginActivity;
import com.miracle.michael.common.activity.MeInfoActivity;
import com.miracle.michael.common.activity.ModifyPasswordActivity;
import com.miracle.michael.common.activity.RegisterActivity;
import com.miracle.michael.common.activity.SettingActivity;
import com.miracle.michael.common.activity.TestActivity;
import com.miracle.michael.football.activity.FootballMeActivity;
import com.miracle.michael.football.activity.FootballMyCollectionsActivity;
import com.miracle.michael.football.activity.FootballSaiShiFenXiActivity;
import com.miracle.michael.lottery.activity.LotteryMyCollectionsActivity;

public class GOTO {
    public static void WelcomeActivity(Context context) {
        context.startActivity(new Intent(context, WelcomeActivity.class));
    }

    public static void MainActivity(Context context) {
        App.getApp().finishAllActivity();
        context.startActivity(new Intent(context, AppConfig.mainClass));
    }

    public static void RegisterActivity(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    public static void TestActivity(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    public static void LoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void LotteryMyCollectionsActivity(Context context) {
        context.startActivity(new Intent(context, LotteryMyCollectionsActivity.class));
    }

    public static void FootballMyCollectionsActivity(Context context) {
        context.startActivity(new Intent(context, FootballMyCollectionsActivity.class));
    }

    public static void SettingActivity(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    public static void CustomerServiceActivity(Context context) {
        context.startActivity(new Intent(context, CustomerServiceActivity.class));
    }

    public static void CircleTurntableActivity(Context context) {
        context.startActivity(new Intent(context, CircleTurntableActivity.class));
    }

    public static void MeInfoActivity(Context context,UserInfoBean userInfo) {
        context.startActivity(new Intent(context, MeInfoActivity.class).putExtra(Constant.USER_INFO, userInfo));
    }

    public static void FootballMeActivity(Context context,UserInfoBean userInfo) {
        context.startActivity(new Intent(context, FootballMeActivity.class).putExtra(Constant.USER_INFO, userInfo));
    }

    public static void ModifyPasswordActivity(Context context) {
        context.startActivity(new Intent(context, ModifyPasswordActivity.class));
    }

    public static void AboutUsActivity(Context context) {
        context.startActivity(new Intent(context, AboutUsActivity.class));
    }

    public static void FootballSaiShiFenXiActivity(Context context) {
        context.startActivity(new Intent(context, FootballSaiShiFenXiActivity.class));
    }

    public static void ChatActivity(Context context) {
        if (CommonUtils.getUser() == null) {
            LoginActivity(context);
        } else if (TextUtils.isEmpty(AppConfig.groupId)) {
            ToastUtil.toast("聊天室登录中,请稍后再试");
        } else {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("chatType", com.miracle.base.im.Constant.CHATTYPE_GROUP);
            intent.putExtra("userId", AppConfig.groupId);
            context.startActivity(intent);
        }

    }

}
