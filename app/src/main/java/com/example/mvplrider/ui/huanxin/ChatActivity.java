package com.example.mvplrider.ui.huanxin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplrider.R;
import com.example.mvplrider.utils.GlideEngine;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = ChatActivity.class.getCanonicalName();
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.iv_final)
    ImageView ivFinal;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rv_user_text)
    RecyclerView rvUserText;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    private String nameid;
    private List<EMMessage> msgsList;
    ;
    private ChatAdapte adapter;
    private String selfId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        initView();
        initChat();
        initMsgListner();
    }

    private void initChat() {

    }

    private void initMsgListner() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(nameid);
//获取此会话的所有消息
//SDK初始化加载的聊天记录为20条，到顶时需要去DB里获取更多
//获取startMsgId之前的pagesize条消息，此方法获取的messages SDK会自动存入到此会话中，APP中无需再次把获取到的messages添加到会话中
//        List<EMMessage> messages = conversation.loadMoreMsgFromDB(startMsgId, pagesize);
        if(conversation!=null){
            List<EMMessage> messages = conversation.getAllMessages();

            msgsList.addAll(messages);
            adapter.notifyDataSetChanged();
        }
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
//            msgsList.addAll(messages);
//            //收到消息
//            if(nameid.equals(messages.get(0).getFrom())){
//                //好友
//                messages.get(0).getBody();
//                //EMMessageBody messageBody;
//                if(messages.get(0).getType() == EMMessage.Type.TXT){
//                    EMTextMessageBody textMessageBody = (EMTextMessageBody) messages.get(0).getBody();
//                    textMessageBody.getMessage();
//                }else if(messages.get(0).getType() == EMMessage.Type.LOCATION){
//                    //定位销
//                }
//
//            }else if(nameid.equals(messages.get(0).getFrom())){
//                //自己
//            }
//            adapter.notifyDataSetChanged();
            msgsList.addAll(messages);
            rvUserText.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });

            //收到消息
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("nameid")) {
                nameid = intent.getStringExtra("nameid");
                tvUser.setText(nameid);
            }
        } else {
            Log.e(TAG, "initView: " + "intent为空");
        }

        selfId = EMClient.getInstance().getCurrentUser();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUserText.setLayoutManager(linearLayoutManager);
        rvUserText.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        msgsList = new ArrayList<>();
        adapter = new ChatAdapte(msgsList, this);
        rvUserText.setAdapter(adapter);
    }


    @OnClick({R.id.iv_final, R.id.btn_send,R.id.iv_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_final:
                finish();
                break;
            case R.id.btn_send:
                userText();
                adapter.notifyDataSetChanged();
                break;
            case R.id.iv_img:
                openPhoto();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(9)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void userText() {
        String text = etText.getText().toString();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(text, nameid);
//        message.setFrom("abab");
        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: 成功" );
            }

            @Override
            public void onError(int code, String error) {
                Log.e(TAG, "onSuccess: "+error );

            }

            @Override
            public void onProgress(int progress, String status) {
                Log.e(TAG, "onSuccess: "+status );

            }
        });
        //如果是群聊，设置chattype，默认是单聊
//        message.setChatType(EMMessage.ChatType.GroupChat);
        //发送消息


        EMClient.getInstance().chatManager().sendMessage(message);
        msgsList.add(message);
        etText.setText("");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                for(LocalMedia item:selectList){
                    sendMsgByImage(item.getPath());
                }
                break;
            default:
                break;
        }
    }

    private void sendMsgByImage(String path){
        Uri uri = Uri.parse(path);
        EMMessage msg = EMMessage.createImageSendMessage(uri, false, nameid);
        /*EMImageMessageBody body = new EMImageMessageBody(uri);
        msg.addBody(body);*/
        //如果是群聊，设置chattype，默认是单聊
        EMClient.getInstance().chatManager().sendMessage(msg);
        msgsList.add(msg);
        adapter.notifyDataSetChanged();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }

    @OnClick(R.id.iv_img)
    public void onClick() {

    }
}

