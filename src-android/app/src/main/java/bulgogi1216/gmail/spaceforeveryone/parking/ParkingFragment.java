package bulgogi1216.gmail.spaceforeveryone.parking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import bulgogi1216.gmail.spaceforeveryone.HttpConnector;
import bulgogi1216.gmail.spaceforeveryone.JsonCallback;
import bulgogi1216.gmail.spaceforeveryone.R;
import bulgogi1216.gmail.spaceforeveryone.tmap.TmapSearchActivity;

/**
 * Created by pys on 2017. 10. 26..
 */

public class ParkingFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParkingFragment.ParkingAdapter adapter;
    private EditText parkingEdit;
    private static final String ARG_POSITION = "position";
    private int position;
    private ParkingList parkingList;
    private ImageView mImage;
    class DownThread extends Thread {
        String mAddr;

        DownThread(String addr) {
            mAddr = addr;
        }

        public void run() {
            try {
                InputStream is = new URL(mAddr).openStream();
                Bitmap bit = BitmapFactory.decodeStream(is);
                is.close();
                Message message = mAfterDown.obtainMessage();
                message.obj = bit;
                mAfterDown.sendMessage(message);
            } catch (Exception e) {;}
        }
    }
    Handler mAfterDown = new Handler() {
        public void handleMessage(Message msg) {
            Bitmap bit = (Bitmap)msg.obj;
            if (bit == null) {
                Toast.makeText(getActivity(), "이미지가 없습니다", Toast.LENGTH_LONG).show();
            } else {
                mImage.setImageBitmap(bit);
            }
        }
    };


    public static ParkingFragment newInstance(int position) {
        ParkingFragment f = new ParkingFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void showPos(double x,double y){
        Intent intent = new Intent(getActivity(),TmapSearchActivity.class);
        intent.putExtra("x",x);
        intent.putExtra("y",y);
        startActivity(intent);


    }
    // 주차장 코드 콜백
    JsonCallback codeselect = new JsonCallback() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);


                parkingList = ParkingList.get(getActivity());
                parkingList.cleanList();
                JSONObject datas = jsonbody.getJSONObject("GetParkInfoRealtime");
                JSONArray datas2 = datas.getJSONArray("row");

                int size = datas2.length();
                Log.v("size", String.valueOf(size));
                Log.v("sizecontent", datas+"");

                showPos(datas2.getJSONObject(0).getDouble("LAT"),datas2.getJSONObject(0).getDouble("LNG"));
                //     memberList = MemberList.get(LeaderMainActivity.this);
                // memberList.cleanList();


                    // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));



                //  nowMemberFragment.updateUI();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };

    // 주차장 리스트 콜백
    JsonCallback parkingselect = new JsonCallback() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);


                parkingList = ParkingList.get(getActivity());
                parkingList.cleanList();
                JSONObject datas = jsonbody.getJSONObject("GetParkInfo");
                    JSONArray datas2 = datas.getJSONArray("row");

                    int size = datas2.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
               //     memberList = MemberList.get(LeaderMainActivity.this);
                   // memberList.cleanList();

                    for (int i = 0; i < size; i++) {

                        Parking p = new Parking(datas2.getJSONObject(i).getString("PARKING_NAME"),
                                datas2.getJSONObject(i).getString("CAPACITY"),
                                datas2.getJSONObject(i).getString("TIME_RATE"),
                                datas2.getJSONObject(i).getString("RATES"),
                                datas2.getJSONObject(i).getString("PARKING_CODE"),
                                datas2.getJSONObject(i).getString("ADDR")

                        );

                        parkingList.addParking(p);

                        // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
                updateUI();

                //  nowMemberFragment.updateUI();


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };



    public void updateUI(){


        ParkingList parkingList = ParkingList.get(getActivity());
        List<Parking> parkings = parkingList.getParkings();


        if(adapter == null) {
            adapter = new ParkingFragment.ParkingAdapter(parkings);
            recyclerView.setAdapter(adapter);
            Log.v("널실행이다","프래그");
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


        View view = inflater.inflate(R.layout.fragment_parking,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.parking_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        parkingEdit = (EditText)view.findViewById(R.id.parkingedit);
        view.findViewById(R.id.parkingsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpConnector httpcon = new HttpConnector();
                httpcon.accessParking(parkingEdit.getText().toString(),parkingselect);
            }
        });
      //  updateUI();


        return view;
    }

    private class ParkingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView parkname;
        private TextView parknum;
        private TextView parktime;
        private TextView parkpay;
        private TextView parkingaddr;

        private Parking parking;

        public ParkingHolder(View itemView) {
            super(itemView);
            parkname = (TextView)itemView.findViewById(R.id.parkname);
            parknum = (TextView)itemView.findViewById(R.id.parknum);
            parktime = (TextView)itemView.findViewById(R.id.parktime);
            parkpay = (TextView)itemView.findViewById(R.id.parkpay);
            parkingaddr = (TextView) itemView.findViewById(R.id.parkingaddr);

            itemView.setOnClickListener(this);

        }

        public void bindCrime(Parking parking){
            this.parking = parking;
            parkname.setText(parking.getName().toString());


            double temp = Double.parseDouble(parking.getTime());
            int temp2 = (int)temp;
            parktime.setText(temp2+"");

            temp = Double.parseDouble(parking.getPay());
            temp2 = (int)temp;
            parkpay.setText(temp2+"");

            temp = Double.parseDouble(parking.getNum());
            temp2 = (int)temp;
            parknum.setText(temp2+"");

            parkingaddr.setText(parking.getAddr().toString());

        }


        @Override
        public void onClick(View v) {



        /*    Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.dialog_imageview);


            mImage = (ImageView) dialog.findViewById(R.id.image);
            (new DownThread("https://yeyak.seoul.go.kr/fileDownload.web?p=/TB_SVCIMG/2014/01/25/S140125140023317380&n=eDzX1dv62vt6b1nTcM9d2S8oGLc78z&on=사본 -S110418155514225375[1].jpg")).start();


            dialog.show();
*/
            HttpConnector httpcon = new HttpConnector();
            httpcon.accessParkingPos(parking.getCode().toString(),codeselect);
        }
    }


    private class ParkingAdapter extends RecyclerView.Adapter<ParkingFragment.ParkingHolder>{

        private List<Parking> parkings;

        public ParkingAdapter(List<Parking> parkings){
            this.parkings = parkings;
        }

        @Override
        public ParkingFragment.ParkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_parking,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new ParkingFragment.ParkingHolder(view);
        }



        @Override
        public void onBindViewHolder(ParkingFragment.ParkingHolder holder, int position) {
            Parking parking =parkings.get(position);
            holder.bindCrime(parking);

        }

        @Override
        public int getItemCount() {
            return parkings.size();
        }
    }

}
