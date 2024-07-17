package com.eclatsol.mynotemvvm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eclatsol.mynotemvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type", "addNote");
//                startActivityForResult(intent, 1);
                insertResultLauncher.launch(intent);
            }
        });

        noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        noteViewModel.getListLiveData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                rvAdapter.submitList(notes);
            }
        });

        binding.RV.setLayoutManager(new LinearLayoutManager(this));
        binding.RV.setHasFixedSize(true);
        rvAdapter = new RVAdapter();
        binding.RV.setAdapter(rvAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    noteViewModel.delete(rvAdapter.getNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "note deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                    intent.putExtra("type", "update");
                    intent.putExtra("tittle", rvAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("disc", rvAdapter.getNote(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id", rvAdapter.getNote(viewHolder.getAdapterPosition()).getId());
                    updateResultLauncher.launch(intent);
                    Toast.makeText(MainActivity.this, "note updated", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(binding.RV);
    }


    public ActivityResultLauncher<Intent> insertResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Log.e("DataResult", "onActivityResult: " + data.getStringExtra("title"));
                Log.e("DataResult", "onActivityResult: " + data.getStringExtra("disc"));
                String tittle = data.getStringExtra("title");
                String disc = data.getStringExtra("disc");
                Note note = new Note(tittle, disc);
                noteViewModel.insert(note);
                Toast.makeText(MainActivity.this, "Note added", Toast.LENGTH_SHORT).show();
            }
        }
    });

    public ActivityResultLauncher<Intent> updateResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                String tittle = data.getStringExtra("title");
                String description = data.getStringExtra("disc");
                Note note = new Note(tittle, description);
                note.setId(data.getIntExtra("id", 0));
                noteViewModel.update(note);
                Log.e("SecondResult", "onActivityResult: second");
            }
        }
    });
}