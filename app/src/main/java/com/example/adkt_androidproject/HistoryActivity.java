package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageButton ibSearch;
    EditText etSearch;
    ClassForTeacherAdapter classForTeacherAdapter;
    RecyclerView rvHistory;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

//        rvHistory = findViewById(R.id.rvHistory);
//        ibSearch = findViewById(R.id.ibSearch);
//        etSearch = findViewById(R.id.etSearch);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        rvHistory.setLayoutManager(linearLayoutManager);
//        list = new ArrayList<>();
//        addList();
//        classForTeacherAdapter = new ClassForTeacherAdapter(list, new IClickItemListener() {
//            @Override
//            public void onClickItem(String str) {
//                onClickGoToDetail(str);
//            }
//        });
//        rvHistory.setAdapter(classForTeacherAdapter);
//
//        ibSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                search(etSearch.getText().toString());
//                classForTeacherAdapter = new ClassForTeacherAdapter(search(etSearch.getText().toString()), new IClickItemListener() {
//                    @Override
//                    public void onClickItem(String str) {
//                        onClickGoToDetail(str);
//                    }
//                });
//                rvHistory.setAdapter(classForTeacherAdapter);
//            }
//        });
    }

    public void addList() {
        list.add("19DTHA1");
        list.add("19DTHA2");
        list.add("19DTHA3");
        list.add("19DTHA4");
        list.add("19DTHA5");
        list.add("19DTHA6");
        list.add("19DTHA7");
        list.add("19DTHA8");
        list.add("19DTHA9");
        list.add("19DTHA10");
    }

    private void onClickGoToDetail(String str) {
        Intent intent = new Intent(this, DetailHistoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_subject", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<String> search(String keyword) {
        List<String> listTemp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(keyword)) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }
}