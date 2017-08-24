package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;

import static android.app.Activity.RESULT_OK;

public class AddEditNoteFragment extends BaseMvpFragment<AddEditNoteContract.View,
        AddEditNoteContract.Presenter> implements AddEditNoteContract.View {
    public static final String TAG = AddEditNoteFragment.class.getSimpleName();

    @BindView(R.id.title_edit)
    TextInputEditText titleEdit;
    @BindView(R.id.content_edit)
    TextInputEditText contentEdit;

    public static AddEditNoteFragment newInstance(Note note) {
        AddEditNoteFragment addEditNoteFragment = new AddEditNoteFragment();
        Bundle args = new Bundle();
        args.putParcelable(AddEditNoteActivity.NOTE, note);
        addEditNoteFragment.setArguments(args);

        return addEditNoteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_edit_note_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(AddEditNoteActivity.NOTE)) {
            Note note = getArguments().getParcelable(AddEditNoteActivity.NOTE);
            if (note != null) {
                getPresenter().handleNoteData(note);
            }
        }
    }

    @Override
    @NonNull
    public AddEditNoteFragmentPresenter createPresenter() {
        AddEditNoteComponent component = App.getAppComponent(getContext())
                .getAddEditNoteComponent();
        component.inject(this);
        return component.getAddEditNotePresenter();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showNoteData(Note note) {
        titleEdit.setText(note.name());
        contentEdit.setText(note.content());
    }

    @Override
    public void showAddOrUpdateSuccessView() {
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

    public void addOrUpdateNote() {
        getPresenter().addOrUpdateNote(titleEdit.getText().toString(),
                contentEdit.getText().toString());
    }

}