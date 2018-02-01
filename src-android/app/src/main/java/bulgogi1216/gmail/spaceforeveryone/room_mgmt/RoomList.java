package bulgogi1216.gmail.spaceforeveryone.room_mgmt;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 28..
 */

public class RoomList {

    private static RoomList roomList;
    private List<Room> permanentRoom;
    private List<Room> reserveRoom;
    private List<Room> rooms;


    public static RoomList get(Context context){
        if(roomList == null){
            roomList = new RoomList(context);
        }
        return roomList;
    }

    public void cleanList(){

        rooms.clear();
    }
    public void deleteMember(UUID id){

        for(Room room:rooms){
            if(room.getId().equals(id)){
                reserveRoom.remove(room);
                rooms.remove(room);
                Log.v("삭제",rooms.size()+"");
                return;
            }
        }

    }

    private RoomList(Context context){
        rooms = new ArrayList<>();
        reserveRoom = new ArrayList<>();


//        for(int i=0;i<2;i++){
//            Room room = new Room("Title"+i+"","양천구 신정동","30000",i);
//            rooms.add(room);
//        }
    }
    public List<Room> getRooms(){
        return rooms;
    }
    public List<Room> getPermanentRoom(){
        return permanentRoom;
    }

    public void copyRoom(){
        permanentRoom = new ArrayList<>(rooms);
    }
    public void copyReserve(){
        rooms = new ArrayList<>(reserveRoom);
    }

    public Room getRooms(UUID id){
        for(Room room: rooms){
            if(room.getId().equals(id)){
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room r){

        rooms.add(r);
    }

    public void addReserve(Room r){

        reserveRoom.add(r);
    }
}
