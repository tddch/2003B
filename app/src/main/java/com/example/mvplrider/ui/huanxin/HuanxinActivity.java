package com.example.mvplrider.ui.huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvplrider.R;
import com.example.mvplrider.utils.SpUtils;
import com.example.mvplrider.utils.UserInfoManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.exceptions.HyphenateException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuanxinActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_userpsw)
    EditText etUserpsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    private ArrayList<EMUserInfo> userInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huanxin);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        userInfos = new ArrayList<>();
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        login();
    }

    private void login() {
        String name = etUsername.getText().toString();
        String psw = etUserpsw.getText().toString();
        if(!name.isEmpty()&&!psw.isEmpty()){
            EMClient.getInstance().login(name, psw, new EMCallBack() {//回调

                @Override
                public void onSuccess() {
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//                startActivity(new Intent(HuanxinActivity.this,UserListActivity.class));
//
                    try {
                        List<String> friends = EMClient.getInstance().contactManager().getAllContactsFromServer();
                        ArrayList<EMUserInfo> list = new ArrayList<>();
                        for(String item:friends){
                            EMUserInfo user = new EMUserInfo();
                            user.setUid(item);
                            String header = SpUtils.getInstance().getString(item);
                            if(!TextUtils.isEmpty(header)){
                                user.setHeader(header);
                            }
                            list.add(user);
                        }
                        UserInfoManager.getInstance().addUsers(list);
                        Log.e("TAG", "onSuccess: "+friends);
                        Intent intent = new Intent(HuanxinActivity.this, UserListActivity.class);
                        intent.putExtra("userList",list);
                        startActivity(intent);
                        finish();
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    Log.e("main", "onSuccess:登录成功！");

                }

                @Override
                public void onProgress(int progress, String status) {
                    Log.e("main", "onProgress: " + status);
                }

                @Override
                public void onError(int code, String message) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(HuanxinActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e("main", "onError:登录失败！" + message);
                }
            });
        }else{
            Toast.makeText(this, "账号或密码不能为 ", Toast.LENGTH_SHORT).show();
        }

    }
}