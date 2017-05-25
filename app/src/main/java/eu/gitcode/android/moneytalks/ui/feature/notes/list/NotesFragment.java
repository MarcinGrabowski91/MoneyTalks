package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.enumeration.ItemActionChooserEnum;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteActivity;

public class NotesFragment extends BaseMvpFragment<NotesAdapterContract.View,
        NotesAdapterContract.Presenter> implements NotesAdapterContract.View {
    public static final String TAG = NotesFragment.class.getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    NotesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.notes_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        getPresenter().handleNotesData();
    }

    @Override
    @NonNull
    public NotesFragmentPresenter createPresenter() {
        NotesComponent component = App.getAppComponent(getContext()).getNotesComponent();
        component.inject(this);
        return component.getNotesPresenter();
    }

    @OnClick(R.id.floating_btn)
    void onFloatingBtnClick() {
        AddEditNoteActivity.startActivityForResult(this);
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showNotesData() {
        //TODO load notes data from the server
        List<Note> notesList = new ArrayList<>();
        notesList.add(Note.builder().title("Lubie liczyc pieniadze")
                .content("Musze pamietac o kasie")
                .date(DateTime.now().minusMonths(2)).build());
        notesList.add(Note.builder().title("Dawid ukradl worek drobnych")
                .content("Trzeba zabrac drobne, bo jak nie zabiore, to bedzie zle i drobne " +
                        "zostana nieodzyskane, czego bardzo nie chcemy")
                .date(DateTime.now())
                .build());
        notesList.add(notesList.get(0));
        notesList.add(notesList.get(1));
        notesList.add(notesList.get(0));
        notesList.add(notesList.get(1));
        notesList.add(notesList.get(0));
        notesList.add(notesList.get(1));
        notesList.add(notesList.get(0));
        notesList.add(notesList.get(1));
        adapter.setNotes(notesList);
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().handleNotesData();
    }

    private void setupRecyclerView() {
        NotesViewHolder.NoteViewHolderListener listener = note ->
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        AddEditNoteActivity.startActivityForResult(this, note);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveNoteDialog(note);
                                    }
                                }).show();
        adapter = new NotesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void showRemoveNoteDialog(Note note) {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_note)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveNote(note))
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}