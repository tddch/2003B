package com.example.mvplrider.ui.huanxin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplrider.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class ChatAdapter extends RecyclerView.Adapter {
    private List<EMMessage> strings;
    private Context context;
    private int TYPE_VIEW_ONE = 1;
    private int TYPE_VIEW_TWO = 2;
    private String selfUid;
    private final LayoutInflater inflater;

    public ChatAdapter(List<EMMessage> strings, Context context) {
        this.strings = strings;
        this.context = context;
        selfUid = EMClient.getInstance().getCurrentUser();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        EMMessage msg = (EMMessage) strings.get(position);
        if (selfUid.equals(msg.getFrom())) {
            return TYPE_VIEW_ONE;
        } else {
            return TYPE_VIEW_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_VIEW_ONE) {
            View view = inflater.inflate(R.layout.layout_chat_item_rt, parent, false);
            return new ViewHolder(view);

        } else {
            View view = inflater.inflate(R.layout.layout_chat_item_lt, parent, false);
            return new ViewHolderRt(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            EMMessage emMessage = strings.get(position);
            EMTextMessageBody body = (EMTextMessageBody) emMessage.getBody();
            String message = body.getMessage();
            ((ViewHolder) holder).tvUserText.setText(message);
        }else if(holder instanceof ViewHolderRt){
            EMMessage emMessage1 = strings.get(position);
            EMTextMessageBody body1 = (EMTextMessageBody) emMessage1.getBody();
            String message = body1.getMessage();
            ((ViewHolderRt) holder).tvUserText.setText(message);
        }
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }



    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_text)
        TextView tvUserText;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    static
    class ViewHolderRt extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_user_text)
        TextView tvUserText;

        ViewHolderRt(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
