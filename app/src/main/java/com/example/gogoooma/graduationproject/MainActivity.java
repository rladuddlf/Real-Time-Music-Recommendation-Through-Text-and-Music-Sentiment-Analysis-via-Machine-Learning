package com.example.gogoooma.graduationproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {


//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            FragmentManager manager = getSupportFragmentManager();
//            switch (item.getItemId()) {
//                case R.id.navigation_man:
//                    manager.beginTransaction().replace(R.id.content_main, new ManFragment()).commit();
//                    return true;
//                case R.id.navigation_emotion:
//                    manager.beginTransaction().replace(R.id.content_main, new Emotion_Fragment()).commit();
//                    return true;
//                case R.id.navigation_chat:
//                    manager.beginTransaction().replace(R.id.content_main, new ChatFragment()).commit();
//                    return true;
//                case R.id.navigation_music:
//                    manager.beginTransaction().replace(R.id.content_main, new MusicFragment()).commit();
//                    return true;
//                case R.id.navigation_setting:
//                    manager.beginTransaction().replace(R.id.content_main, new SettingFragment()).commit();
//                    return true;
//            }
//            return false;
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main, new ManFragment()).commit();

        final SpaceNavigationView spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("home", R.drawable.menu_man));
        spaceNavigationView.addSpaceItem(new SpaceItem("emotion", R.drawable.menu_emotion));
        spaceNavigationView.addSpaceItem(new SpaceItem("music", R.drawable.menu_music));
        spaceNavigationView.addSpaceItem(new SpaceItem("setting", R.drawable.menu_setting));
        spaceNavigationView.showIconOnly();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.content_main, new ChatFragment()).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                clickMenu(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                clickMenu(itemIndex);
            }
        });
    }

    public void clickMenu(int itemIndex){
        FragmentManager manager = getSupportFragmentManager();
        switch (itemIndex) {
            case 0:
                manager.beginTransaction().replace(R.id.content_main, new ManFragment()).commit();
                break;
            case 1:
                manager.beginTransaction().replace(R.id.content_main, new Emotion_Fragment()).commit();
                break;
            case 2:
                manager.beginTransaction().replace(R.id.content_main, new MusicFragment()).commit();
                break;
            case 3:
                manager.beginTransaction().replace(R.id.content_main, new SettingFragment()).commit();
                break;
        }
    }

    private long backKeyPressedTime = 0;
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            finish();
        }
    }
}
