package com.eclatsol.mynotemvvm;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.eclatsol.mynotemvvm.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
//            setTitle("update");
            binding.tittle.setText(getIntent().getStringExtra("tittle"));
            binding.discription.setText(getIntent().getStringExtra("disc"));
            binding.add.setText("Update Note");
            int id = getIntent().getIntExtra("id",0);
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // setResult no matlab ek thay che jyathi intent ne call karelu che tya setResult ne set kari do
                    //DataInsertActivity ne call karish main activity thi
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.tittle.getText().toString().trim());
                    intent.putExtra("disc",binding.discription.getText().toString().trim());
                    intent.putExtra("id",id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }else {
//        setTitle("Add note");
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // setResult no matlab ek thay che jyathi intent ne call karelu che tya setResult ne set kari do
                    //DataInsertActivity ne call karish main activity thi
                    Intent intent = new Intent();
                    intent.putExtra("title",binding.tittle.getText().toString().trim());
                    intent.putExtra("disc",binding.discription.getText().toString().trim());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });

        }


        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
            }
        });


    }

}