package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.avos.avoscloud.R;

/**
 * Created by linpan1 on 15/4/24.
 */
public class Chat extends Activity {
    private ListView listView;
    private EditText input;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        listView = (ListView)findViewById(R.id.listView);
        input = (EditText)findViewById(R.id.input);
        send = (Button)findViewById(R.id.send);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(input.getText().toString().trim());
            }
        });

//        AVIMClient imClient = AVIMClient.getInstance("Tom");
//        imClient.open(new AVIMClientCallback(){
//            @Override
//            public void done(AVIMClient client, AVException e) {
//                if (null != e) {
//                    // 出错了，可能是网络问题无法连接 LeanCloud 云端，请检查网络之后重试。
//                    // 此时聊天服务不可用。
//                    e.printStackTrace();
//                } else {
//                    // 成功登录，可以开始进行聊天了（假设为 MainActivity）。
//                    Intent intent = new Intent(Chat.this, Login.class);
//                    startActivity(intent);
//                };
//            }
//        });
//
//        List<String> clientIds = new ArrayList<String>();
//        clientIds.add("Tom");
//        clientIds.add("Bob");

// 我们给对话增加一个自定义属性 type，表示单聊还是群聊
// 常量定义：
// int ConversationType_OneOne = 0; // 两个人之间的单聊
// int ConversationType_Group = 1;  // 多人之间的群聊
//        Map<String, Object> attr = new HashMap<String, Object>();
////        attr.put("type", ConversationType_OneOne);
//
//        imClient.createConversation(clientIds, attr, new AVIMConversationCreatedCallback() {
//            @Override
//            public void done(AVIMConversation conversation, AVException e) {
//                if (null != conversation) {
//                    // 成功了，这时候可以显示对话的 Activity 页面（假定为 ChatActivity）了。
//                    Intent intent = new Intent(Chat.this, Login.class);
////                    Intent.putExtra("conversation", conversation);
//                    startActivity(intent);
//                }
//            }
//        });
    }



}
