package com.example.listycity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button addButton;
    Button deleteButton;
    Button confirmButton;
    EditText editText;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addButton = findViewById(R.id.button1);
        deleteButton = findViewById(R.id.button2);
        confirmButton = findViewById(R.id.button3);
        editText = findViewById(R.id.text_input);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        addButton.setOnClickListener(view -> {
            editText.setVisibility(View.VISIBLE);
            confirmButton.setVisibility(View.VISIBLE);
        });

        confirmButton.setOnClickListener(view -> {
            String text = editText.getText().toString();
            dataList.add(text);
            cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
            cityList.setAdapter(cityAdapter);
            editText.setText("");
            editText.setVisibility(View.INVISIBLE);
            confirmButton.setVisibility(View.INVISIBLE);
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
            }
        });

        deleteButton.setOnClickListener(view -> {
            dataList.remove(selectedItem);
            cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
            cityList.setAdapter(cityAdapter);
        });
    }
}