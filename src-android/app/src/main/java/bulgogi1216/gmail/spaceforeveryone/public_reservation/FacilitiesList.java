package bulgogi1216.gmail.spaceforeveryone.public_reservation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pys on 2017. 10. 26..
 */

public class FacilitiesList {

    private static FacilitiesList parkingList;
    private List<Facilities> parkings;


    public static FacilitiesList get(Context context){
        if(parkingList == null){
            parkingList = new FacilitiesList(context);
        }
        return parkingList;
    }

    public void cleanList(){

        parkings.clear();
    }

    private FacilitiesList(Context context){
        parkings = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Customer customer = new Customer();
//            customer.setName("업무보고"+i);
//            customers.add(customer);
//        }
    }
    public List<Facilities> getParkings(){
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

    public void addParking(Facilities p){

        // consult.setTitle(s);
        // consult.setSolved(b);
        parkings.add(p);
    }


}
