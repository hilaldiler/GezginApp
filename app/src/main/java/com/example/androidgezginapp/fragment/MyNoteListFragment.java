package com.example.androidgezginapp.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidgezginapp.R;
import com.example.androidgezginapp.adapter.NoteAdapter;
import com.example.androidgezginapp.model.Notes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyNoteListFragment extends Fragment {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ArrayList<Notes> notes;
    RecyclerView recycler_note;

    public MyNoteListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Notes");
        getData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_notes, container, false);
        recycler_note=view.findViewById(R.id.recycler_notes);

        return view;
    }
    public void getData() {
    notes = new ArrayList<>();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange (@NonNull DataSnapshot dataSnapshot){
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                for (DataSnapshot _snapshot : snapshot.getChildren()) {
                    String uid = _snapshot.getKey();
                    String title = _snapshot.getValue().toString();
                    Notes note = new Notes(uid, title);

                    notes.add(note);
                }
            }
            NoteAdapter adapter = new NoteAdapter(notes, getActivity());
            recycler_note.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager =
                    new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation
                    (LinearLayoutManager.VERTICAL);
            recycler_note.setLayoutManager
                    (linearLayoutManager);
        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

