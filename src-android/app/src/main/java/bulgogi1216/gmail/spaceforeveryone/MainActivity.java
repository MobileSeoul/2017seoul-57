package bulgogi1216.gmail.spaceforeveryone;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bulgogi1216.gmail.spaceforeveryone.databinding.ActivityMainBinding;
import bulgogi1216.gmail.spaceforeveryone.membermanagement.Member;
import bulgogi1216.gmail.spaceforeveryone.membermanagement.MemberJoinActivity;
import bulgogi1216.gmail.spaceforeveryone.membermanagement.MemberList;
import bulgogi1216.gmail.spaceforeveryone.membermanagement.MemberListFragment;
import bulgogi1216.gmail.spaceforeveryone.parking.ParkingFragment;
import bulgogi1216.gmail.spaceforeveryone.public_reservation.FacilitiesFragment;
import bulgogi1216.gmail.spaceforeveryone.room_mgmt.Room;
import bulgogi1216.gmail.spaceforeveryone.room_mgmt.RoomFragment;
import bulgogi1216.gmail.spaceforeveryone.room_mgmt.RoomList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ActivityMainBinding mBinding;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mToggle;
    private Boolean mIsLoggedin;
    private Handler mHandler;
    private RoomFragment nowRoomFrag;
    private String menuState;

    private int getId(String type, String name){
        int tempId = getResources().getIdentifier("bulgogi1216.gmail.spaceforeveryone:"+type+"/"+name,null,null);
        return tempId;
    }

    public void createData(){
        RoomList roomList = RoomList.get(this);


        String temp="";


        temp = "office" + (0);
        Room room = new Room("유럽풍의 럭셔리한 빌라 2층", "성북구 삼선동 (Tel.010-4001-3339)", "₩10,000/시간당 (인원 10명)", 1,
                getId("drawable", temp), 37.587236, 127.006841);
        roomList.addRoom(room);

        temp = "office" + (1);
        room = new Room("최고의뷰 초역세권 주차가능한 공간 ", "성북구 돈암동 (Tel.010-4001-3339)", "₩15,000/시간당 (인원 8명)", 1,
                getId("drawable", temp), 37.587236, 127.006841);
        roomList.addRoom(room);


        temp = "office" + (2);
        room = new Room("한강 근처 조용한 주택가", "광진구 자양동 (Tel.010-4001-3339)", "₩12,000/시간당 (인원 4명)", 2,
                getId("drawable", temp), 37.534719, 127.062900);
        roomList.addRoom(room);


        temp = "office" + (3);
        room = new Room("강변역 5분 거리 카페 같은 공간", "광진구 구의동(Tel.010-4001-3339)", "₩13,000/시간당 (인원 5명)", 2,
                getId("drawable", temp), 37.534719, 127.062900);
        roomList.addRoom(room);


        temp = "office" + (4);
        room = new Room("채광 좋고 넓은 공간 ", "서초구 방배동 (Tel.010-4001-3339)", "₩11,000/시간당 (인원 7명)", 3,
                getId("drawable", temp), 37.495359, 126.985542);
        roomList.addRoom(room);


        temp = "office" + (5);
        room = new Room("마당 있는 단독 사용 공간 ", "서초구 반포동 (Tel.010-4001-3339)", "₩7,000/시간당 (인원 6명)", 3,
                getId("drawable", temp), 37.495359, 126.985542);
        roomList.addRoom(room);


        temp = "office" + (6);
        room = new Room("신정역 도보 5분 거리 계단식 복층형", "양천구 신정동 (Tel.010-4001-3339)", "₩12,000/시간당 (인원 8명)", 4,
                getId("drawable", temp), 37.515138, 126.854150);
        roomList.addRoom(room);


        temp = "office" + (7);
        room = new Room("어린이 대공원 근처 조용한 주택가", "광진구 구의동 (Tel.010-4001-3339)", "₩5,000/시간당 (인원 4명)", 2,
                getId("drawable", temp), 37.534719, 127.062900);
        roomList.addRoom(room);


        temp = "office" + (8);
        room = new Room("인테리어 GOOD 깨끗하고 아늑한 곳", "관악구 인헌동 (Tel.010-4001-3339)", "₩18,000/시간당 (인원 10명)", 5,
                getId("drawable", temp), 37.474921, 126.965153);
        roomList.addRoom(room);


        temp = "office" + (9);
        room = new Room("단독 사용 가능한 누구나 원하는 공간", "관악구 신림동 (Tel.010-4001-3339)", "₩14,000/시간당 (인원 6명)", 5,
                getId("drawable", temp), 37.474921, 126.965153);
        roomList.addRoom(room);


        temp = "office" + (10);
        room = new Room("로데오 거리 번화가에 있는 공간", "광진구 자양동 (Tel.010-4001-3339)", "₩8,000/시간당 (인원 4명)", 2,
                getId("drawable", temp), 37.534719, 127.062900);
        roomList.addRoom(room);


        temp = "office" + (11);
        room = new Room("햇살 좋은 최상의 위치 ", "성북구 보문동 (Tel.010-4001-3339)", "₩10,000/시간당 (인원 9명)", 1,
                getId("drawable", temp), 37.587236, 127.006841);
        roomList.addRoom(room);


        temp = "office" + (12);
        room = new Room("성대 정문 근처 신축 리모델링 공간", "성북구 삼선동 (Tel.010-4001-3339)", "₩10,000/시간당 (인원 5명)", 1,
                getId("drawable", temp), 37.587236, 127.006841);
        roomList.addRoom(room);


        temp = "office" + (13);
        room = new Room("넓고 여유 공간 많은 준고급 빌라 5층", "성북구 정릉동 (Tel.010-4001-3339)", "₩9,000/시간당 (인원 7명)", 1,
                getId("drawable", temp), 37.587236, 127.006841);
        roomList.addRoom(room);



        roomList.copyRoom();

    }
    private void createMember(){

                Member m = new Member("010-2322-1211","이정희","여성","jung@naver.com","29");
        MemberList memberList = MemberList.get(this);
        memberList.addMember(m);

        Member m1 = new Member("010-3339-3311","김태훈","남성","hooni@naver.com","32");
        memberList.addMember(m1);
        Member m2 = new Member("010-4411-0011","장우진","남성","jjang@naver.com","28");
        memberList.addMember(m2);
    }


    private void initToolbar() {
        setSupportActionBar(mBinding.toolbarShell.toolbar);
        mActionBar = getSupportActionBar();
        Log.e(TAG, "Actionbar is null");
        assert mActionBar != null;
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setTitle(getResources().getString(R.string.app_name));
    }

    private void initDrawerMenu() {
        mToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout,
                mBinding.toolbarShell.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                //  getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
//                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mBinding.drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                mActionBar.setTitle(item.getTitle());
                Fragment fragment = getFragmentByDrawerTag(item);
                commitFragment(fragment);
                mBinding.drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initBottomNav() {
        mBinding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.bottom_nav_home:
                        menuState = "space";
                        mActionBar.setTitle("공간찾기");
                        invalidateOptionsMenu();
                        List<Room> tempR = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());
                        RoomList.get(MainActivity.this).cleanList();
                        RoomList tempList = RoomList.get(MainActivity.this);
                        for(int i=0;i<tempR.size();i++){
                            tempList.addRoom(tempR.get(i));
                        }

                         fragment = RoomFragment.newInstance(0);
                        commitFragment(fragment);
                      // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottom_nav_room_list:
                        menuState = "empty";
                        invalidateOptionsMenu();
                        RoomList.get(MainActivity.this).cleanList();
                        RoomList.get(MainActivity.this).copyReserve();

                        if(RoomList.get(MainActivity.this).getRooms().size()==0){
                            Toast.makeText(MainActivity.this,"예약중인 공간이 없습니다",Toast.LENGTH_LONG).show();
                        }


                        fragment = RoomFragment.newInstance(1);
                        commitFragment(fragment);
                     //   Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();;
                        return true;
                    case R.id.bottom_nav_reserved_list:

                        menuState = "member";
                        invalidateOptionsMenu();
                        fragment = MemberListFragment.newInstance(2);
                        commitFragment(fragment);
                       // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();;
                        return true;

                    case R.id.bottom_nav_message:
                        menuState = "empty";
                        invalidateOptionsMenu();
                        fragment = ParkingFragment.newInstance(3);
                        commitFragment(fragment);
                       // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();;
                        return true;
                }
                return false;
            }
        });
    }

    private Fragment getFragmentByDrawerTag(MenuItem _item) {
        Fragment fragment;
        String[] drawerTitles = getResources().getStringArray(R.array.drawer_menu_titles);

        if(_item.getTitle().equals(drawerTitles[0])) {
            menuState = "space";
            invalidateOptionsMenu();
            List<Room> tempR = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());
            RoomList.get(MainActivity.this).cleanList();
            RoomList tempList = RoomList.get(MainActivity.this);
            for(int i=0;i<tempR.size();i++){
                tempList.addRoom(tempR.get(i));
            }

            fragment = RoomFragment.newInstance(0);
            //nowRoomFrag = (RoomFragment) fragment;
        } else if(_item.getTitle().equals(drawerTitles[1])) {
            menuState = "empty";
            invalidateOptionsMenu();
            RoomList.get(MainActivity.this).cleanList();
            RoomList.get(MainActivity.this).copyReserve();

            if(RoomList.get(MainActivity.this).getRooms().size()==0){
                Toast.makeText(this,"예약중인 공간이 없습니다",Toast.LENGTH_LONG).show();
            }

            //nowRoomFrag.updateUI();



            fragment = RoomFragment.newInstance(1);
        }else if(_item.getTitle().equals(drawerTitles[2])) {

            menuState = "member";
            invalidateOptionsMenu();
            fragment = MemberListFragment.newInstance(2);
        }
        else if(_item.getTitle().equals(drawerTitles[3])) {

            menuState = "empty";
            invalidateOptionsMenu();
            fragment = ParkingFragment.newInstance(3);
        }
        else if(_item.getTitle().equals(drawerTitles[4])) {

            menuState = "empty";
            invalidateOptionsMenu();
            fragment = FacilitiesFragment.newInstance(4);
        }
        else if(_item.getTitle().equals(drawerTitles[5])) {

            final LinearLayout linear = (LinearLayout)
                    View.inflate(this, R.layout.dialog_daydel, null);

            new android.app.AlertDialog.Builder(this)
                    .setTitle("앱을 종료하시겠습니까?")
                    .setView(linear)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        onBackPressed();


                        }
                    })
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
            menuState = "space";
            invalidateOptionsMenu();
            List<Room> tempR = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());
            RoomList.get(MainActivity.this).cleanList();
            RoomList tempList = RoomList.get(MainActivity.this);
            for(int i=0;i<tempR.size();i++){
                tempList.addRoom(tempR.get(i));
            }

            fragment = RoomFragment.newInstance(0);

        }



        else {
            Log.e(TAG, "fragment variable is null");
            fragment = null;
        }

        return fragment;
    }

    public void commitFragment(Fragment _fragment) {
        // Using Handler class to avoid lagging while committing fragment in same time as closing navigation drawer
        mHandler.post(new CommitFragmentRunnable(_fragment));
    }

    private class CommitFragmentRunnable implements Runnable {

        private Fragment fragment;

        public CommitFragmentRunnable(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public void run() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(mBinding.contentMain.getId(), fragment).commitAllowingStateLoss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,SplashActivity.class);
        startActivity(intent);
        menuState = "space";
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        createData();
        createMember();
        initToolbar();
        initDrawerMenu();
        initBottomNav();

        mHandler = new Handler();

        if(savedInstanceState == null) {
            mIsLoggedin = false;
            Fragment fragment = RoomFragment.newInstance(0);
            nowRoomFrag = (RoomFragment) fragment;
            commitFragment(fragment);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        if(menuState.equals("space")){

            getMenuInflater().inflate(R.menu.main, menu);
        }
        else if(menuState.equals("member")){
            getMenuInflater().inflate(R.menu.menu_member, menu);
        }

        else if(menuState.equals("empty")){
            getMenuInflater().inflate(R.menu.menu_empty, menu);
        }


        return true;
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addmember:

            Intent intent=new Intent(MainActivity.this,MemberJoinActivity.class);
            startActivity(intent);

            break;
            case R.id.search:
                CharSequence info[] = new CharSequence[] {"전체","성북구","광진구","서초구","양천구","관악구","마포구","강남구" };


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("지역구 찾기");

                builder.setItems(info, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {



                        switch(which)

                        {

                            case 0:

                                // 내정보
                                List<Room> tempR = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());
                                RoomList.get(MainActivity.this).cleanList();
                                RoomList tempList = RoomList.get(MainActivity.this);
                                for(int i=0;i<tempR.size();i++){


                                    tempList.addRoom(tempR.get(i));
                                }

                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "전체", Toast.LENGTH_SHORT).show();

                                break;

                            case 1:
                                List<Room> r = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());

                                RoomList.get(MainActivity.this).cleanList();
                                RoomList rList = RoomList.get(MainActivity.this);
                                for(int i=0;i<r.size();i++){

                                    if(r.get(i).getLocalCode()==1){
                                        rList.addRoom(r.get(i));
                                    }
                                }
                                // 로그아웃
                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "성북구", Toast.LENGTH_SHORT).show();

                                break;

                            case 2:
                                List<Room> r2 = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());

                                RoomList.get(MainActivity.this).cleanList();
                                RoomList rList2 = RoomList.get(MainActivity.this);
                                for(int i=0;i<r2.size();i++){

                                    if(r2.get(i).getLocalCode()==2){
                                        rList2.addRoom(r2.get(i));
                                    }
                                }
                                // 로그아웃
                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "광진구", Toast.LENGTH_SHORT).show();

                                break;

                            case 3:
                                List<Room> r3 = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());

                                RoomList.get(MainActivity.this).cleanList();
                                RoomList rList3 = RoomList.get(MainActivity.this);
                                for(int i=0;i<r3.size();i++){

                                    if(r3.get(i).getLocalCode()==3){
                                        rList3.addRoom(r3.get(i));
                                    }
                                }
                                // 로그아웃
                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "서초구", Toast.LENGTH_SHORT).show();

                                break;

                            case 4:
                                List<Room> r4 = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());

                                RoomList.get(MainActivity.this).cleanList();
                                RoomList rList4 = RoomList.get(MainActivity.this);
                                for(int i=0;i<r4.size();i++){

                                    if(r4.get(i).getLocalCode()==4){
                                        rList4.addRoom(r4.get(i));
                                    }
                                }
                                // 로그아웃
                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "양천구", Toast.LENGTH_SHORT).show();

                                break;

                            case 5:
                                List<Room> r5 = new ArrayList<Room>(RoomList.get(MainActivity.this).getPermanentRoom());

                                RoomList.get(MainActivity.this).cleanList();
                                RoomList rList5 = RoomList.get(MainActivity.this);
                                for(int i=0;i<r5.size();i++){

                                    if(r5.get(i).getLocalCode()==5){
                                        rList5.addRoom(r5.get(i));
                                    }
                                }
                                // 로그아웃
                                nowRoomFrag.updateUI();
                                Toast.makeText(MainActivity.this, "관악구", Toast.LENGTH_SHORT).show();

                                break;
                            case 6:

                                Toast.makeText(MainActivity.this, "등록된 공간이 없습니다", Toast.LENGTH_SHORT).show();

                                break;
                            case 7:

                                Toast.makeText(MainActivity.this, "등록된 공간이 없습니다", Toast.LENGTH_SHORT).show();

                                break;

                        }

                        dialog.dismiss();

                    }

                });

                builder.show();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
