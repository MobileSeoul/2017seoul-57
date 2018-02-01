package bulgogi1216.gmail.spaceforeveryone.parking;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 26..
 */

public class Parking {

    private String name;
    private String num;
    private String time;
    private String pay;
    private String code;
    private String addr;
    private UUID id;

    public Parking(){


    }
    public Parking(String name, String num, String time, String pay, String code, String addr ){

        this.name = name;
        this.num = num;
        this.time = time;
        this.pay = pay;
        this.code = code;
        this.addr = addr;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
