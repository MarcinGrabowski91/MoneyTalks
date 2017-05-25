package eu.gitcode.android.moneytalks.ui.feature.notes.show;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class NoteFragment extends BaseMvpFragment<NoteContract.View,
        NoteContract.Presenter> implements NoteContract.View {
    public static final String TAG = NoteFragment.class.getSimpleName();

    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.date_txt)
    TextView dateTxt;
    @BindView(R.id.content_txt)
    TextView contentTxt;

    public static NoteFragment newInstance(Note note) {
        NoteFragment noteFragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(NoteActivity.NOTE, note);
        noteFragment.setArguments(args);

        return noteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            getPresenter().handleNoteData(getArguments().getParcelable(NoteActivity.NOTE));
        }
    }

    @Override
    @NonNull
    public NoteFragmentPresenter createPresenter() {
        NoteComponent component = App.getAppComponent(getContext()).getNoteComponent();
        component.inject(this);
        return component.getNotePresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showNoteData(Note note) {
        titleTxt.setText(note.title());
        dateTxt.setText(DateUtils.getLongDateStringFromDateTime(note.date()));
        contentTxt.setText(note.content());
    }

    @Override
    public void showRemoveSuccessView() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    public void handleEditNote() {
        AddEditNoteActivity.startActivityForResult(this, getPresenter().getNote());
    }

    public void handleRemoveNote() {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.remove_note)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        getPresenter().handleRemoveNote())
                .setNegativeButton(R.string.no, (dialog, which) -> { // no-op
                }).create().show();
    }
}