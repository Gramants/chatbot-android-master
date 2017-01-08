package com.schibsted.android.chatbot.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.schibsted.android.chatbot.R;
import com.schibsted.android.chatbot.model.Chat;
import com.schibsted.android.chatbot.util.Constant;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends ArrayAdapter {
private Context context;
    public ChatAdapter(final ArrayList<Chat> cities, final Context context) {
        super(context, 0, cities);
        this.context=context;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        final Chat chatitem = (Chat) getItem(position);

            final LayoutInflater layoutInflater = LayoutInflater.from(getContext());

            if (chatitem.getUsername().equals(Constant.CHATOWNER))
            convertView = layoutInflater.inflate(R.layout.item_my_message, null);
            else
            convertView = layoutInflater.inflate(R.layout.item_message, null);

            viewHolder = new ViewHolder();
            if (!chatitem.getUsername().equals(Constant.CHATOWNER))
            {
            viewHolder.chat_bubble_user = (TextView) convertView.findViewById(R.id.chat_bubble_user);
            viewHolder.chat_bubble_profile = (CircleImageView) convertView.findViewById(R.id.chat_bubble_profile);
            }

            viewHolder.chat_bubble_time = (TextView) convertView.findViewById(R.id.chat_bubble_time);
            viewHolder.chat_bubble_text = (TextView) convertView.findViewById(R.id.chat_bubble_text);
            convertView.setTag(viewHolder);


        if (!chatitem.getUsername().equals(Constant.CHATOWNER))
        {
        viewHolder.chat_bubble_user.setText(chatitem.getUsername());

            Picasso.with(context)
                    .load(chatitem.getUserImageUrl())
                    .placeholder(R.drawable.nofoto)
                    .error(R.drawable.nofoto)
                    .resizeDimen(R.dimen.list_detail_image_size, R.dimen.list_detail_image_size)
                    .centerInside()
                    .tag(context)
                    .into(viewHolder.chat_bubble_profile);
        }
        viewHolder.chat_bubble_time.setText(chatitem.getTime());
        viewHolder.chat_bubble_text.setText(chatitem.getContent());
        return convertView;
    }



    private class ViewHolder {
        private TextView chat_bubble_user;
        private TextView chat_bubble_text;
        private TextView chat_bubble_time;
        private CircleImageView chat_bubble_profile;
    }

}
