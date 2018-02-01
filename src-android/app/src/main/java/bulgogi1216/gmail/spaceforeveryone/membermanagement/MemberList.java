package bulgogi1216.gmail.spaceforeveryone.membermanagement;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 6..
 */

public class MemberList {

    private static MemberList memberList;
    private List<Member> members;


    public static MemberList get(Context context){
        if(memberList == null){
            memberList = new MemberList(context);
        }
        return memberList;
    }

    public void cleanList(){

        members.clear();
    }

    public void deleteMember(UUID id){

        for(Member member:members){
            if(member.getId().equals(id)){
                members.remove(member);
                Log.v("삭제",members.size()+"");
                return;
            }
        }

    }

    private MemberList(Context context){
        members = new ArrayList<>();
//        for(int i=0;i<4;i++){
//            Member member = new Member();
//            member.setName("멤버"+i);
//            members.add(member);
//        }
    }
    public List<Member> getMembers(){
        return members;
    }
    public void addMember(Member m){

        // consult.setTitle(s);
        // consult.setSolved(b);
        members.add(m);
    }


    public Member getMembers(UUID id){
        for(Member member: members){
            if(member.getId().equals(id)){
                return member;
            }
        }
        return null;
    }



}
