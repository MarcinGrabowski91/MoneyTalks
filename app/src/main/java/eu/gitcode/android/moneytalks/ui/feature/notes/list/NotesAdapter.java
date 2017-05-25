package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eu.gitcode.android.moneytalks.models.ui.Note;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    private NotesViewHolder.NoteViewHolderListener listener;
    private List<Note> notesList = new ArrayList<>();

    public NotesAdapter(NotesViewHolder.NoteViewHolderListener listener) {
        this.listener = listener;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent,
                                              int viewType) {
        return new NotesViewHolder(parent, listener);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotes(List<Note> notesList) {
        this.notesList = notesList;
        notifyDataSetChanged();
    }
}