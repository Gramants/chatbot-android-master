package com.schibsted.android.chatbot.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.schibsted.android.chatbot.App;
import com.schibsted.android.chatbot.R;
import com.schibsted.android.chatbot.adapter.ChatAdapter;
import com.schibsted.android.chatbot.model.Chat;
import com.schibsted.android.chatbot.persistence.PersistentStorageProxy;
import com.schibsted.android.chatbot.presenter.ChatPresenterContract;
import com.schibsted.android.chatbot.presenter.ChatViewContract;
import com.schibsted.android.chatbot.presenter.impl.ChatActivityPresenter;
import com.schibsted.android.chatbot.util.Constant;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.ButterKnife;
import javax.inject.Inject;
import butterknife.Bind;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChatActivity extends AppCompatActivity implements ChatViewContract {

    @Inject
    PersistentStorageProxy mStorageProxy;

    @Bind(R.id.lvMessages)
    ListView listView;

    @Bind(R.id.send_button)
    ImageView button;

    @Bind(R.id.message_text)
    EditText mymessage;

    @Bind(R.id.footer_section)
    LinearLayout footer;


    private MaterialDialog mProgress;
    private ArrayAdapter mAdapter;
    private ChatPresenterContract mChatActivityPresenter;
    private ArrayList<Chat> mWebmessages;
    private ArrayList<Chat> mMymessages;
    private SimpleDateFormat mSdf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        ((App) getApplication()).getAppComponent().inject(this);
        init();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setTitle(getResources().getString(R.string.chat_label)+" " + mStorageProxy.getUserName());
    }

    private void init() {
        mChatActivityPresenter= new ChatActivityPresenter(this);
        mMymessages=new ArrayList<Chat>();
    }

    @Override
    public void onPause(){
        super.onPause();

        if (mMymessages!=null)
        {
        mStorageProxy.setMyMessages(mMymessages);
        mWebmessages.clear();
        }
        mAdapter.notifyDataSetChanged();
        footer.setVisibility(View.GONE);
    }


    @Override
    public void onResume(){
        super.onResume();

        mChatActivityPresenter.CurrentMessages();
        getSupportActionBar().setTitle(getResources().getString(R.string.chat_label)+" " + mStorageProxy.getUserName());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!mymessage.getText().toString().equals(""))
                {
                mSdf = new SimpleDateFormat("HH:mm");
                String timenow = mSdf.format(new Date());

                Chat chat=new Chat();
                chat.setContent(mymessage.getText().toString());
                chat.setUsername(Constant.CHATOWNER);
                chat.setTime(timenow);

                mWebmessages.add(chat);

                if (mMymessages==null)
                    mMymessages=new ArrayList<Chat>();

                mMymessages.add(chat);

                updateListview(mWebmessages);
                mymessage.setText("");
            }
            }
        });
        
    }

    private void updateListview(ArrayList tempnew) {
        mAdapter = new ChatAdapter(tempnew,this);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
            default:
                break;
        }
        return true;
    }

    private void logout() {
        mStorageProxy.onLogout();
        mWebmessages.clear();

        if (mMymessages!=null)
        {mMymessages.clear();}

        mAdapter.notifyDataSetChanged();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void startLoadMessages() {
        footer.setVisibility(View.GONE);
        mProgress=new MaterialDialog.Builder(this)
                .title(getResources().getString(R.string.loading_label))
                .content(mStorageProxy.getUserName()+", "+getResources().getString(R.string.wait_label))
                .progress(true, 0)
                .show();
    }
    
    public void finishLoadMessages() {
        mProgress.dismiss();
        footer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showChatMessages(List<Chat> messageList) {
        mWebmessages=new ArrayList<Chat>(messageList);
        updateListview(mWebmessages);
        mMymessages=mStorageProxy.getMyMessages();
        
        if (mMymessages!=null)
        {
            for(int i=0;i<mMymessages.size();i++)
            {mWebmessages.add((Chat) mMymessages.get(i));}
        }
        
        updateListview(mWebmessages);
        finishLoadMessages();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
