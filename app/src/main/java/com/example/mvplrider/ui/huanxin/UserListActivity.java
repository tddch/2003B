package com.example.mvplrider.ui.huanxin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseAdapter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListActivity extends AppCompatActivity {

    @BindView(R.id.rv_user_list)
    RecyclerView rvUserList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ArrayList<EMUserInfo> userList;
    private FriendsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        initView();
        initData();
        initChat();
        adapter.notifyDataSetChanged();
//        initOss();
    }

    private void initChat() {

    }


    private void initData() {
        Intent intent = getIntent();
        ArrayList<EMUserInfo> userList = (ArrayList<EMUserInfo>) intent.getSerializableExtra("userList");
        this.userList.addAll(userList);

//        if(userList!=null){
//            EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userList.get(0).getUid());
//            int unreadMsgCount = conversation.getUnreadMsgCount();
//            Log.e("TAG", "initChat: "+unreadMsgCount );
//        }

        adapter.notifyDataSetChanged();
    }

    private void initView() {
        toolbar.setTitle("好友列表");
        setSupportActionBar(toolbar);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        rvUserList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        userList = new ArrayList<>();
        adapter = new FriendsAdapter(this, userList);
        rvUserList.setAdapter(adapter);


        adapter.setClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int position) {
                Intent intent = new Intent(UserListActivity.this, ChatActivity.class);
                intent.putExtra("nameid", userList.get(position).getUid());
                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(userList.get(position).getUid());
//指定会话消息未读数清零
                conversation.markAllMessagesAsRead();
//把一条消息置为已读
                conversation.markMessageAsRead(userList.get(position).getUid());
//所有未读消息数清零
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new FriendsAdapter.OnItemClickListener<EMUserInfo>() {
            @Override
            public void onItemClick(int position, EMUserInfo data) {
                Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
                intent.putExtra("username", data);
                startActivity(intent);
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "退出登录");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UserListActivity.this, "退出登录", Toast.LENGTH_SHORT).show();

                    }
                });
                finish();
            }

            @Override
            public void onError(int code, String error) {
                Log.e("TAG", "onError: " + error);
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e("TAG", "onProgress: " + status);
            }
        });

    }
}