package com.miracle.base;

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
    public static void WelcomeActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), WelcomeActivity.class));
    }

    public static void MainActivity() {
        App.getApp().finishAllActivity();
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), AppConfig.mainClass));
    }

    public static void RegisterActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), RegisterActivity.class));
    }

    public static void TestActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), TestActivity.class));
    }

    public static void LoginActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), LoginActivity.class));
    }

    public static void LotteryMyCollectionsActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), LotteryMyCollectionsActivity.class));
    }

    public static void FootballMyCollectionsActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), FootballMyCollectionsActivity.class));
    }

    public static void SettingActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), SettingActivity.class));
    }

    public static void CustomerServiceActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), CustomerServiceActivity.class));
    }

    public static void CircleTurntableActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), CircleTurntableActivity.class));
    }

    public static void MeInfoActivity(UserInfoBean userInfo) {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), MeInfoActivity.class).putExtra(Constant.USER_INFO, userInfo));
    }

    public static void FootballMeActivity(UserInfoBean userInfo) {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), FootballMeActivity.class).putExtra(Constant.USER_INFO, userInfo));
    }

    public static void ModifyPasswordActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), ModifyPasswordActivity.class));
    }

    public static void AboutUsActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), AboutUsActivity.class));
    }

    public static void FootballSaiShiFenXiActivity() {
        ContextHolder.getContext().startActivity(new Intent(ContextHolder.getContext(), FootballSaiShiFenXiActivity.class));
    }

    public static void ChatActivity() {
        if (CommonUtils.getUser() == null) {
            LoginActivity();
        } else if (TextUtils.isEmpty(AppConfig.groupId)) {
            ToastUtil.toast("聊天室登录中,请稍后再试");
        } else {
            Intent intent = new Intent(ContextHolder.getContext(), ChatActivity.class);
            intent.putExtra("chatType", com.miracle.base.im.Constant.CHATTYPE_GROUP);
            intent.putExtra("userId", AppConfig.groupId);
            ContextHolder.getContext().startActivity(intent);
        }

    }
}
