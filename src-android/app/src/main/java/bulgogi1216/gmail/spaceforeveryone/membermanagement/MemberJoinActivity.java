package bulgogi1216.gmail.spaceforeveryone.membermanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bulgogi1216.gmail.spaceforeveryone.R;

/**
 * Created by pys on 2017. 10. 31..
 */

public class MemberJoinActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_join );
        getSupportActionBar().setTitle("팀원등록");



    }

    public void BtnSubmit(View view) {

        String name = ((EditText) findViewById(R.id.joinname)).getText().toString();
        String age =((EditText) findViewById(R.id.joinage)).getText().toString();
        String gen = ((EditText)findViewById(R.id.joingender)).getText().toString();
        String tel = ((EditText)findViewById(R.id.joinphone)).getText().toString();
        String email = ((EditText)findViewById(R.id.joinemail)).getText().toString();

        MemberList.get(this).addMember(new Member(name,age,gen,tel,email));
        Toast.makeText(this,"등록 완료",Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}
