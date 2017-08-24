package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.enumeration.ItemActionChooserEnum;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteActivity;
import eu.gitcode.android.moneytalks.ui.feature.notes.show.NoteActivity;
import onactivityresult.ActivityResult;
import onactivityresult.OnActivityResult;

import static eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteActivity.ADD_EDIT_NOTE_REQUEST_CODE;

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
    public void showNotesData(List<Note> notesList) {
        adapter.setNotes(notesList);
    }

    @Override
    public void showRemoveSuccessView() {
        getPresenter().handleNotesData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityResult.onResult(requestCode, resultCode, data).into(this);
    }

    @OnActivityResult(requestCode = ADD_EDIT_NOTE_REQUEST_CODE, resultCodes = {Activity.RESULT_OK})
    void onAddEditResult() {
        getPresenter().handleNotesData();
    }

    private void setupRecyclerView() {
        NotesViewHolder.NoteViewHolderListener listener = new NotesViewHolder.NoteViewHolderListener() {
            @Override
            public void onNoteLongClicked(Note note) {
                new AlertDialog.Builder(getContext())
                        .setItems(ItemActionChooserEnum.getNamesResArray(getResources()),
                                (dialog, which) -> {
                                    if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.EDIT)) {
                                        startAddEditNoteActivity(note);
                                    } else if (ItemActionChooserEnum.values()[which]
                                            .equals(ItemActionChooserEnum.REMOVE)) {
                                        showRemoveNoteDialog(note);
                                    }
                                }).show();
            }

            @Override
            public void onNoteClicked(Note note) {
                startNoteActivity(note);
            }
        };
        adapter = new NotesAdapter(listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void startAddEditNoteActivity(Note note) {
        AddEditNoteActivity.startActivityForResult(this, note);
    }

    private void startNoteActivity(Note note) {
        NoteActivity.startActivityForResult(this, note);
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