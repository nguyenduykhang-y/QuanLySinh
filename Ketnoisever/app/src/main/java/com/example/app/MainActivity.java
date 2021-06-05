package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app.Adapter.StudentAdapter;
import com.example.app.DAO.StudentDAO;
import com.example.app.Model.Students;

import java.util.ArrayList;


public class MainActivity extends Activity {

    Button btnThem, btnSua;
    public EditText txtId, txtName, txtEmail, txtSex, txtAddress;
    public String _id;
    ListView lv;
    Students sv;
    StudentDAO svDao;
    ArrayList<Students> students;
    public StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=findViewById(R.id.lv1);
        txtId=findViewById(R.id.edtId);
        txtName=findViewById(R.id.edtTen);
        txtEmail=findViewById(R.id.edtEmail);
        txtSex = findViewById(R.id.edtsex);
        txtAddress=findViewById(R.id.edtaddress);
        btnThem=findViewById(R.id.btnThem);
        btnSua=findViewById(R.id.btnSua);

        svDao = new StudentDAO(this);

        capnhatLV();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv = new Students();

                sv.id=txtId.getText().toString();
                sv.name=txtName.getText().toString();
                sv.email=txtEmail.getText().toString();
                sv.sex=txtSex.getText().toString();
                sv.address=txtAddress.getText().toString();

                if(!sv.id.isEmpty() && !sv.name.isEmpty()){
                    //them student
                    svDao.insert(sv);
                    txtId.setText("");
                    txtName.setText("");
                    txtEmail.setText("");
                    txtSex.setText("");
                    txtAddress.setText("");



                }else{
                    Toast.makeText(MainActivity.this, "Vui long nhap du thong tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sv = new Students();
                sv._id = _id;
                sv.id=txtId.getText().toString();
                sv.name=txtName.getText().toString();
                sv.email=txtEmail.getText().toString();
                sv.sex=txtSex.getText().toString();
                sv.address=txtAddress.getText().toString();

                svDao.update(sv);

                txtId.setText("");
                txtName.setText("");
                txtEmail.setText("");
                txtSex.setText("");
                txtAddress.setText("");
            }
        });

    }

    public void xoaStudent(String id){

        svDao.delete(id);

        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtSex.setText("");
        txtAddress.setText("");


    }

    public void capnhatLV(){

        // getAll Student
        students = (ArrayList<Students>) svDao.getAll();

        // gan adapter

        adapter = new StudentAdapter(this, students);

        lv.setAdapter(adapter);
    }
}
