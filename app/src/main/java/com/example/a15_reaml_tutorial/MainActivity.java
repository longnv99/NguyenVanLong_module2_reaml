package com.example.a15_reaml_tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public
class MainActivity extends AppCompatActivity {

    private Realm realm;
    private MyHelper helper;
    private
    RealmChangeListener realmChangeListener;
    private List<User> list;
    private UserAdapter adapter;
    private
    EditText edMSV, edName, edClass, edToan, edLy, edHoa;
    private
    RadioButton rdMale, rdFemale;
    private
    RecyclerView recyclerView;
    private
    Button btnAdd;
    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edMSV=findViewById(R.id.msv);
        edName=findViewById(R.id.name);
        edClass=findViewById(R.id.lop);
        edToan=findViewById(R.id.diemToan);
        edLy=findViewById(R.id.diemLy);
        edHoa=findViewById(R.id.diemHoa);
        rdMale=findViewById(R.id.rdMale);
        rdFemale=findViewById(R.id.rdFemale);
        recyclerView=findViewById(R.id.recycleView);
        btnAdd=findViewById(R.id.btnAdd);

        realm = Realm.getDefaultInstance();
        helper = new MyHelper(realm);
        helper.selectFromDB();
        list = helper.convertList();

        adapter = new UserAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setData(list);
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public
            void onClick(View v) {
                insertUser();
            }
        });

        reFresh();

    }

    private
    void insertUser() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public
            void execute(Realm realm) {
                Number maxId = realm.where(User.class).max("id");
                long currentId = (maxId == null) ? 1 : maxId.intValue()+1;
                User u = realm.createObject(User.class, currentId);
                u.setMaSinhVien(edMSV.getText().toString());
                u.setHoTen(edName.getText().toString());
                u.setLop(edClass.getText().toString());
                if(rdMale.isChecked())
                    u.setGioiTinh("Nam");
                else if (rdFemale.isChecked())
                    u.setGioiTinh("Nu");
                u.setDiemToan(edToan.getText().toString());
                u.setDiemLy(edLy.getText().toString());
                u.setDiemHoa(edHoa.getText().toString());
            }
        });

        Toast.makeText(this,"Add successfull",Toast.LENGTH_LONG).show();
    }

    private void reFresh(){
        realmChangeListener = new RealmChangeListener() {
            @Override
            public
            void onChange(Object o) {
                UserAdapter adapter = new UserAdapter(MainActivity.this);
                adapter.setData(helper.convertList());
                recyclerView.setAdapter(adapter);
            }
        };
        realm.addChangeListener(realmChangeListener);
    }

    @Override
    protected
    void onDestroy() {
        super.onDestroy();
        realm.removeChangeListener(realmChangeListener);
        realm.close();
    }
}