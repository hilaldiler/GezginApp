package com.example.androidgezginapp.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidgezginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class NoteFragment extends Fragment implements View.OnClickListener {


    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    Button btnAddNote, btnNoteList;
    EditText edtNote;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("Notes");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        btnAddNote = view.findViewById(R.id.btn_add_note);
        btnNoteList = view.findViewById(R.id.btn_list_note);
        edtNote = view.findViewById(R.id.edit_notes);
        btnNoteList.setOnClickListener(this);
        btnAddNote.setOnClickListener(this);
        return view;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_note:
                addNote();
                break;
            case R.id.btn_list_note:
                goToMyNotesFragment();
                break;
        }
    }

    private void goToMyNotesFragment() {
        loadFragment(new MyNoteListFragment());
    }

    private void addNote() {
        if (!edtNote.getText().toString().isEmpty()) {
            mDatabaseReference.child(UUID.randomUUID().toString())
                    .child(UUID.randomUUID()
                            .toString()).setValue(edtNote.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                edtNote.setText("");
                                Toast.makeText(getActivity(), "Not Kaydedildi", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
        }
    }
}


