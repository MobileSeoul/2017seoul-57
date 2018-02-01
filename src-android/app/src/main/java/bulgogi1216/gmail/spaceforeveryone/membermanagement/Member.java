package bulgogi1216.gmail.spaceforeveryone.membermanagement;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 6..
 */

public class Member {

    private UUID id;
    private String userId="";
    private String name="";
    private String grade="";
    private String title="";
    private String email="";
    private String dept="";

    public Member(){
        id = UUID.randomUUID();
    }

    public Member(String userid, String name, String grade, String email, String dept){
        this.id = UUID.randomUUID();
        this.userId = userid;
        this.name = name;
        this.grade = grade;
        this.email = email;
        this.dept = dept;

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
