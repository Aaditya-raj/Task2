package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mylist;
    SearchView searchView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        mylist = findViewById(R.id.mylist);
        List<ApplicationInfo> listApplicationInfo = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        String[] stringslist = new String[listApplicationInfo.size()];
        int i=0;

        for(ApplicationInfo applicationInfo: listApplicationInfo){
            stringslist[i]=applicationInfo.packageName;
            i++;
        }

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,stringslist);
        mylist.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}