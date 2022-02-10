package com.example.bai6_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lsMonHoc;
    EditText editText;
    Button btnAdd, btnUpdate;
    ArrayList listMonHoc;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        index = -1;
        lsMonHoc = (ListView) findViewById(R.id.listMonHoc);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        editText = (EditText) findViewById(R.id.txtCourse);

        listMonHoc = new ArrayList();
        listMonHoc.add("JAVA");
        listMonHoc.add("PHP");
        listMonHoc.add("C#");
        listMonHoc.add("RUBY");
        listMonHoc.add("JAVASCRIPT");

        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listMonHoc);
        lsMonHoc.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String course = editText.getText().toString();
                if(course.length()>0){
                    listMonHoc.add(course);
                    arrayAdapter.notifyDataSetChanged();
                    editText.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "Empty Field!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index < 0){
                    Toast.makeText(MainActivity.this, "You Have To Choose One", Toast.LENGTH_LONG).show();
                }else{
                    if(editText.getText().toString().length()>0){
                        listMonHoc.set(index, editText.getText().toString());
                        arrayAdapter.notifyDataSetChanged();
                        editText.setText("");
                        index = -1;
                    }else{
                        Toast.makeText(MainActivity.this, "Empty Field!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        lsMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(listMonHoc.get(i).toString());
                index = i;
            }
        });

        lsMonHoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                listMonHoc.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}