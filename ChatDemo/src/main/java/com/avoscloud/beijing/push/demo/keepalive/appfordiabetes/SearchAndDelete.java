package com.avoscloud.beijing.push.demo.keepalive.appfordiabetes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

/**
 * Created by linpan1 on 15/4/7.
 */
public class SearchAndDelete extends Activity {
    private Button search;
    private EditText searchtheName;
    private EditText searchItem;
    private Button delete;
    private ListView searchList;
    private ArrayAdapter<String> listAdapter;
    private String result;
    private int count =0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchanddelete);

        search = (Button)findViewById(R.id.search);
        delete =(Button)findViewById(R.id.delete);
        searchtheName = (EditText)findViewById(R.id.searchName);
        searchItem = (EditText)findViewById(R.id.searchItem);
        searchList = (ListView)findViewById(R.id.list);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        searchList.setAdapter(listAdapter);
        listAdapter.add("此处显示查询到的数据");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVQuery<AVObject> query = new AVQuery<AVObject>(searchItem.getText().toString().trim());
                query.whereEqualTo("P_name", searchtheName.getText().toString().trim());
                query.findInBackground(new FindCallback<AVObject>() {
                    public void done(List<AVObject> avObjects, AVException e) {
                        if (e == null) {
                            Log.d("成功", "查询到" + avObjects.size() + " 条符合条件的数据");

                            result=avObjects.get(count).getString("P_diet");
                            listAdapter.add(result);
                            count++;

                        } else {
                            Log.d("失败", "查询错误: " + e.getMessage());
                        }
                    }
                });
            }
        });




    }

}
