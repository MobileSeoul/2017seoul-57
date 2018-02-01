package bulgogi1216.gmail.spaceforeveryone.parking;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pys on 2017. 10. 26..
 */

public class ParkingList {

    private static ParkingList parkingList;
    private List<Parking> parkings;


    public static ParkingList get(Context context){
        if(parkingList == null){
            parkingList = new ParkingList(context);
        }
        return parkingList;
    }

    public void cleanList(){

        parkings.clear();
    }

    private ParkingList(Context context){
        parkings = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Customer customer = new Customer();
//            customer.setName("업무보고"+i);
//            customers.add(customer);
//        }
    }
    public List<Parking> getParkings(){
        return parkings;
    }



//    public Parking getParkings(UUID id){
//        for(Customer customer: customers){
//            if(customer.getId().equals(id)){
//                return customer;
//            }
//        }
//        return null;
//    }

    public void addParking(Parking p){

        // consult.setTitle(s);
        // consult.setSolved(b);
        parkings.add(p);
    }


}
