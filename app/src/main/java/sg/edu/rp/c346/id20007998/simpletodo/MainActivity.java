package sg.edu.rp.c346.id20007998.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etThings;
    ListView lsThings;
    Button btnAdd;
    Button btnClear;
    Button btnDel;
    ArrayList<String> altoDoList;
    ArrayAdapter<String> aatoDoList;
    Spinner spnAddDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etThings=findViewById(R.id.etThings);
        lsThings=findViewById(R.id.toDoList);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        btnDel=findViewById(R.id.buttonDel);
        spnAddDel=findViewById(R.id.spnAddDel);

        altoDoList=new ArrayList<>();

        aatoDoList=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,altoDoList);

        lsThings.setAdapter(aatoDoList);

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                btnDel.setEnabled(true);
                btnAdd.setEnabled(true);
                switch (position) {
                    case 0:
                        btnDel.setEnabled(false);
                        etThings.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        etThings.setHint("Type in the index of the task to be removed");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?>parent){

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String colour=etThings.getText().toString();
                altoDoList.add(colour);
                aatoDoList.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(altoDoList.size()!=0){
                    if(etThings.getText().toString().trim().length() > 0){
                        int index=Integer.parseInt(etThings.getText().toString());
                        if (index<=altoDoList.size()-1){
                            altoDoList.remove(index);
                            aatoDoList.notifyDataSetChanged();
                        }else{
                            Toast.makeText(MainActivity.this," Wrong index number",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this," You need to input a number",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MainActivity.this," You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                altoDoList.clear();
                aatoDoList.notifyDataSetChanged();
            }
        });
    }
}