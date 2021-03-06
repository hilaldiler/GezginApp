package com.example.androidgezginapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidgezginapp.R;
import com.example.androidgezginapp.model.Notes;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    public List<Notes> notes;
    LayoutInflater inflater;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView post_description;
        public TextView post_title;

        public MyViewHolder(View v) {
            super(v);
            post_description = (TextView) itemView.findViewById(R.id.tvNotlar1);
            post_title = (TextView) itemView.findViewById(R.id.tvNotlar);

        }
    }

    public NoteAdapter(List<Notes> notes, Context _context) {
        this.notes = notes;
        context = _context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.note_list, parent, false);
        NoteAdapter.MyViewHolder vh = new NoteAdapter.MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final NoteAdapter.MyViewHolder holder, final int position) {
        holder.post_description.setText(notes.get(position).getNoteID());
        holder.post_title.setText(String.valueOf(notes.get(position).getNoteTitle()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}