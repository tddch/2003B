package com.example.mvplrider.ui.huanxin;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.mvplrider.R;
import com.example.mvplrider.adapter.RecommendAdapter;
import com.example.mvplrider.base.BaseAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import java.util.ArrayList;
import java.util.List;

public class FriendsAdapter extends BaseAdapter {

    public FriendsAdapter(Context context, List list) {
        super(list, context);
    }

    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener<D> {
        void onItemClick(int position, D data);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected void bindData(Object data, VH vh, int position, Context context) {
        EMUserInfo _data = (EMUserInfo) data;
        TextView txtUserName = (TextView) vh.getViewById(R.id.txt_username);
        ImageView ivUserImg = (ImageView) vh.getViewById(R.id.iv_user_img);
        Button btn_user = (Button) vh.getViewById(R.id.btn_user);
        TextView tvChat = (TextView) vh.getViewById(R.id.tv_chat_item);

        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(_data.getUid());
        if (conversation != null) {

            int unreadMsgCount = conversation.getUnreadMsgCount();
            if (unreadMsgCount <= 0) {
                tvChat.setVisibility(View.GONE);
            } else {
                tvChat.setVisibility(View.VISIBLE);
                tvChat.setText(unreadMsgCount + "");
                boolean isClick = true;
                int item = 0;
                if (isClick==true) {
                    isClick = false;
                    if (item == 0 || (item + 1) == unreadMsgCount) {

                        isClick=true;
                        tvChat.post(new Runnable() {
                            @Override
                            public void run() {
                                notifyDataSetChanged();
                            }
                        });
                    }
                    item = unreadMsgCount;

                }


            }

        }


        if (!TextUtils.isEmpty(_data.getNickname())) {
            txtUserName.setText(_data.getNickname());
        } else {
            txtUserName.setText(_data.getUid());
        }
        String header = _data.getHeader();
        if (!TextUtils.isEmpty(header)) {
            Glide.with(ivUserImg).load(header).into(ivUserImg);
        }


        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v.getId(), data);
                }
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_friend_item;
    }

}
