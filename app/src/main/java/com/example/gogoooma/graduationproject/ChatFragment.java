package com.example.gogoooma.graduationproject;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    View v;
    ListView listView;//asdf
    DBRoomHelper dbRoomHelper;
    List<Talk> talkList;
    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_chat, container, false);
        dbRoomHelper = new DBRoomHelper(getContext(), "TALKLIST", null, 1);
        talkList = dbRoomHelper.getAllTalk();
        listView = (ListView) v.findViewById(R.id.chat_listview);
        ChatListAdapter adapter = new ChatListAdapter(getContext(), R.layout.chatlist_row,
                talkList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Friend friend = new Friend(talkList.get(i).getFriendName(),
                        talkList.get(i).getDbName(), null);
                Intent intent = new Intent(getContext(), ChatActivity.class);
                intent.putExtra("friend", friend);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        dbRoomHelper = new DBRoomHelper(getContext(), "TALKLIST", null, 1);
        talkList.clear();
        talkList = dbRoomHelper.getAllTalk();
        listView = (ListView) v.findViewById(R.id.chat_listview);
        ChatListAdapter adapter = new ChatListAdapter(getContext(), R.layout.chatlist_row,
                talkList);
        listView.setAdapter(adapter);
    }

    //    tvData = (TextView)v.findViewById(R.id.textView);
//    Button btn = (Button)v.findViewById(R.id.httpTest);
//    //버튼이 클릭되면 여기 리스너로 옴
//        btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            new JSONTask().execute("http://192.168.22.170:3000/post");//AsyncTask 시작시킴
//        }
//    });

//    public class JSONTask extends AsyncTask<String, String, String> {
//            @Override
//            protected String doInBackground(String... urls) {
//                try {
//                    //JSONObject를 만들고 key value 형식으로 값을 저장해준다.
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.accumulate("user_id", "androidTest");
//                    jsonObject.accumulate("name", "songkyeohee");
//                    HttpURLConnection con = null;
//                    BufferedReader reader = null;
//
//                    try {
//                        //URL url = new URL("http://192.168.25.16:3000/users");
//                        URL url = new URL(urls[0]);
//                        //연결을 함
//                        con = (HttpURLConnection) url.openConnection();
//                        con.setRequestMethod("POST");//POST방식으로 보냄
//                        con.setRequestProperty("Cache-Control", "no-cache");//캐시 설정
//                        con.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
//                        con.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
//                        con.setDoOutput(true);//Outstream으로 post 데이터를 넘겨주겠다는 의미
//                        con.setDoInput(true);//Inputstream으로 서버로부터 응답을 받겠다는 의미
//                        con.connect();
//                        //서버로 보내기위해서 스트림 만듬
//                        OutputStream outStream = con.getOutputStream();
//                        //버퍼를 생성하고 넣음
//                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outStream));
//                        writer.write(jsonObject.toString());
//                        writer.flush();
//                        writer.close();//버퍼를 받아줌
//                        //서버로 부터 데이터를 받음
//                        InputStream stream = con.getInputStream();
//                        reader = new BufferedReader(new InputStreamReader(stream));
//                        StringBuffer buffer = new StringBuffer();
//                        String line = "";
//                        while ((line = reader.readLine()) != null) {
//                            buffer.append(line);
//                        }
//                        return buffer.toString();//서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (con != null) {
//                            con.disconnect();
//                        }
//                        try {
//                            if (reader != null) {
//                                reader.close();//버퍼를 닫아줌
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                tvData.setText(result);//서버로 부터 받은 값을 출력해주는 부
//            }
//    }
}
