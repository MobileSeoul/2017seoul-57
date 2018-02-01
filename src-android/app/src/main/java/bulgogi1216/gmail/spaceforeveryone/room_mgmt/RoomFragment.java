package bulgogi1216.gmail.spaceforeveryone.room_mgmt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import bulgogi1216.gmail.spaceforeveryone.R;
import bulgogi1216.gmail.spaceforeveryone.tmap.TmapSearchActivity;

/**
 * Created by pys on 2017. 10. 28..
 */

public class RoomFragment extends Fragment {

    private RecyclerView recyclerView;
    private RoomFragment.RoomAdapter adapter;

    private static final String ARG_POSITION = "position";
    private int position;
    private static int pos;
    private int searchCode=0;

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public static RoomFragment newInstance(int position) {
        pos = position;
        RoomFragment f = new RoomFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(){

        RoomList roomList =RoomList.get(getActivity()) ;
        List<Room> rooms =roomList.getRooms();


        if(adapter == null) {
            adapter = new RoomFragment.RoomAdapter(rooms);
            recyclerView.setAdapter(adapter);

        }
        else{

            adapter.notifyDataSetChanged();
        }


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_room,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();





        //        for(int i=0;i<2;i++){
//            Room room = new Room("Title"+i+"","양천구 신정동","30000",i);
//            rooms.add(room);
//        }

        recyclerView = (RecyclerView) view.findViewById(R.id.room_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        updateUI();



        return view;
    }




    public String createComma(String num) {
        int value = Integer.parseInt(num);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result = (String)Commas.format(value);
        return result;
    }

    private class RoomHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView money;
        private TextView address;
        private Room room;
        private TextView reserve;
        private ViewPager pager;
        private LinearLayout itemLinear;
        private int currentItem;
        private ViewPager pager2;

        private TextView navigator;
        private ImageView image;

        private TextView f1;
        private TextView f2;
        private TextView f3;
        private TextView f4;
        private TextView f5;
        private TextView f6;


        public RoomHolder(View itemView) {
            super(itemView);
            itemLinear = (LinearLayout) itemView.findViewById(R.id.itemLinear);
            title = (TextView)itemView.findViewById(R.id.title);
            image = (ImageView)itemView.findViewById(R.id.recyclerImage);
            money = (TextView)itemView.findViewById(R.id.money);
            address = (TextView)itemView.findViewById(R.id.address);
            reserve = (TextView)itemView.findViewById(R.id.reserve);

            f1 = (TextView)itemView.findViewById(R.id.f1);
            f2 = (TextView)itemView.findViewById(R.id.f2);
            f3 = (TextView)itemView.findViewById(R.id.f3);
            f4 = (TextView)itemView.findViewById(R.id.f4);
            f5 = (TextView)itemView.findViewById(R.id.f5);
            f6 = (TextView)itemView.findViewById(R.id.f6);

            itemView.setOnClickListener(this);

        }

        public void bindCrime(Room room){
            this.room = room;
            if(pos==1){
            reserve.setText(room.getDate().toString());
            itemLinear.setVisibility(View.GONE);

            }
            title.setText(room.getTitle().toString());
            image.setImageDrawable(getResources().getDrawable(room.getImageId()));
            money.setText(room.getMoney().toString());
            address.setText(room.getAddress().toString());


              int state = (int) (Math.random() * 2) + 1;
              int viewstate1 = 0;
              if (state == 1) {
                  viewstate1 = View.INVISIBLE;
              } else {
                  viewstate1 = View.VISIBLE;
              }
             state = (int) (Math.random() * 2) + 1;
            int viewstate2 = 0;
            if (state == 1) {
                viewstate2 = View.INVISIBLE;
            } else {
                viewstate2 = View.VISIBLE;
            }
            state = (int) (Math.random() * 2) + 1;
            int viewstate3 = 0;
            if (state == 1) {
                viewstate3 = View.INVISIBLE;
            } else {
                viewstate3 = View.VISIBLE;
            }
            state = (int) (Math.random() * 2) + 1;
            int viewstate4 = 0;
            if (state == 1) {
                viewstate4 = View.INVISIBLE;
            } else {
                viewstate4 = View.VISIBLE;
            }
            state = (int) (Math.random() * 2) + 1;
            int viewstate5 = 0;
            if (state == 1) {
                viewstate5 = View.INVISIBLE;
            } else {
                viewstate5 = View.VISIBLE;
            }
            state = (int) (Math.random() * 2) + 1;
            int viewstate6 = 0;
            if (state == 1) {
                viewstate6 = View.INVISIBLE;
            } else {
                viewstate6 = View.VISIBLE;
            }



            f1.setVisibility(viewstate1);
            f2.setVisibility(viewstate2);
            f3.setVisibility(viewstate3);
            f4.setVisibility(viewstate4);
            f5.setVisibility(viewstate5);
            f6.setVisibility(viewstate6);




        }

        @Override
        public void onClick(View view) {
            if(!(pos==1)) {

                Intent intent = new Intent(getActivity(), TmapSearchActivity.class);
                intent.putExtra("id", room.getId());
                intent.putExtra("x", room.getLatitude());
                intent.putExtra("y", room.getLongitude());
                startActivity(intent);
            }
            else{
                final LinearLayout linear = (LinearLayout)
                        View.inflate(getActivity(), R.layout.dialog_daydel, null);

                new AlertDialog.Builder(getActivity())
                        .setTitle("예약을 취소하시겠습니까?")
                        .setView(linear)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                RoomList roomList = RoomList.get(getActivity());
                                roomList.deleteMember(room.getId());
                                Toast.makeText(getActivity(),"취소 완료",Toast.LENGTH_LONG).show();

                                updateUI();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();
            }
        }
    }


    private class RoomAdapter extends RecyclerView.Adapter<RoomFragment.RoomHolder>{

        private List<Room> rooms;

        public RoomAdapter(List<Room> rooms){
            this.rooms = rooms;
        }

        @Override
        public RoomFragment.RoomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_room,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new RoomFragment.RoomHolder(view);
        }



        @Override
        public void onBindViewHolder(RoomFragment.RoomHolder holder, int position) {
            Room room =rooms.get(position);
            holder.bindCrime(room);

        }

        @Override
        public int getItemCount() {
            return rooms.size();
        }
    }


}
