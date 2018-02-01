package bulgogi1216.gmail.spaceforeveryone.public_reservation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class FacilitiesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FacilitiesFragment.ParkingAdapter adapter;
    private EditText parkingEdit;
    private static final String ARG_POSITION = "position";
    private int position;
    private FacilitiesList parkingList;
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

    public static FacilitiesFragment newInstance(int position) {
        FacilitiesFragment f = new FacilitiesFragment();
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

    // 시설 리스트 콜백
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


                parkingList = FacilitiesList.get(getActivity());
                parkingList.cleanList();
                JSONObject datas = jsonbody.getJSONObject("ListPublicReservationSport");
                    JSONArray datas2 = datas.getJSONArray("row");

                    int size = datas2.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
               //     memberList = MemberList.get(LeaderMainActivity.this);
                   // memberList.cleanList();


                    for (int i = 0; i < size; i++) {

                        Facilities p = new Facilities(datas2.getJSONObject(i).getString("MINCLASSNM"),
                                datas2.getJSONObject(i).getString("PAYATNM"),
                                datas2.getJSONObject(i).getString("PLACENM"),
                                datas2.getJSONObject(i).getString("AREANM"),
                                datas2.getJSONObject(i).getString("DTLCONT"),
                                datas2.getJSONObject(i).getString("TELNO"),
                                datas2.getJSONObject(i).getString("X"),
                                datas2.getJSONObject(i).getString("Y"),
                                datas2.getJSONObject(i).getString("IMGURL")

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


        FacilitiesList parkingList = FacilitiesList.get(getActivity());
        List<Facilities> parkings = parkingList.getParkings();


        if(adapter == null) {
            adapter = new FacilitiesFragment.ParkingAdapter(parkings);
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


        View view = inflater.inflate(R.layout.fragment_public,container,false);
        setHasOptionsMenu(true);
        getActivity().getActionBar();
        recyclerView = (RecyclerView) view.findViewById(R.id.parking_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));   // getActivty() 프래그먼트 호스팅하고 있는 액티비티 가져옴
        parkingEdit = (EditText)view.findViewById(R.id.parkingedit);
        view.findViewById(R.id.parkingsearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpConnector httpcon = new HttpConnector();
                httpcon.accessPublic(parkingEdit.getText().toString(),parkingselect);
            }
        });
      //  updateUI();


        return view;
    }

    private class ParkingHolder extends RecyclerView.ViewHolder  implements Button.OnClickListener{


        private TextView parkname;
        private TextView parknum;
        private TextView parktime;
        private TextView parkpay;
        private TextView parkingaddr;
        private TextView dictation;

        private Facilities parking;

        public ParkingHolder(View itemView) {
            super(itemView);
            parkname = (TextView)itemView.findViewById(R.id.parkname);
            parknum = (TextView)itemView.findViewById(R.id.parknum);
            parktime = (TextView)itemView.findViewById(R.id.parktime);
            parkpay = (TextView)itemView.findViewById(R.id.parkpay);
            parkingaddr = (TextView) itemView.findViewById(R.id.parkingaddr);
            dictation = (TextView) itemView.findViewById(R.id.dictation);
            itemView.findViewById(R.id.btnpos).setOnClickListener(this);
            itemView.findViewById(R.id.btnfac).setOnClickListener(this);
            itemView.findViewById(R.id.btndictation).setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        public void bindCrime(Facilities parking){
            this.parking = parking;
            parkname.setText(parking.getName().toString());
            dictation.setText(parking.getCode().toString());

           // double temp = Double.parseDouble(parking.getTime());
           // int temp2 = (int)temp;
            parktime.setText(parking.getTime().toString());

           // temp = Double.parseDouble(parking.getPay());
          //  temp2 = (int)temp;
            parkpay.setText(parking.getPay().toString());

           // temp = Double.parseDouble(parking.getNum());
           // temp2 = (int)temp;
            parknum.setText(parking.getNum().toString());

            parkingaddr.setText(parking.getAddr().toString());

        }


        @Override
        public void onClick(View v) {




            switch (v.getId()){
                case R.id.btnfac:
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_imageview);
                    String img = parking.getImg();
                    mImage = (ImageView) dialog.findViewById(R.id.image);
                    (new DownThread(img)).start();


                    dialog.show();
                    break;
                case R.id.btnpos:


                    if((!parking.getPosx().equals("")) &&(!parking.getPosy().equals(""))){
                        double temp1 = Double.parseDouble(parking.getPosx());
                        double temp2 = Double.parseDouble(parking.getPosy());
                        Intent intent = new Intent(getActivity(), TmapSearchActivity.class);
                    intent.putExtra("x", temp1);
                    intent.putExtra("y", temp2);
                    startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(),"위치가 등록되어 있지 않습니다",Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btndictation:
                    Dialog dialog2 = new Dialog(getActivity());
                    dialog2.setContentView(R.layout.dialog_html);
                    String msg = parking.getCode();
                    TextView tv = (TextView) dialog2.findViewById(R.id.htmlview);
                    tv.setText(Html.fromHtml(msg));


                    dialog2.show();


            }



            //   Toast.makeText(getActivity(),crime.getTitle()+"",Toast.LENGTH_LONG).show();

            //   Intent intent = CrimeActivity.newIntent(getActivity(), crime.getId());
            // startActivity(intent);





        }
    }


    private class ParkingAdapter extends RecyclerView.Adapter<FacilitiesFragment.ParkingHolder>{

        private List<Facilities> parkings;

        public ParkingAdapter(List<Facilities> parkings){
            this.parkings = parkings;
        }

        @Override
        public FacilitiesFragment.ParkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_public,parent,false);   // 인자1.프래그먼트뷰 2.뷰의 부모 3. false는 호스팅 액티비티에서 뷰를 추가한다는뜻


            return new FacilitiesFragment.ParkingHolder(view);
        }



        @Override
        public void onBindViewHolder(FacilitiesFragment.ParkingHolder holder, int position) {
            Facilities parking =parkings.get(position);
            holder.bindCrime(parking);

        }

        @Override
        public int getItemCount() {
            return parkings.size();
        }
    }

}
