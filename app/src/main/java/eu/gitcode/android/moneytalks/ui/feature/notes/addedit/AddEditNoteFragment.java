package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.OnClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.application.App;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseMvpFragment;
import eu.gitcode.android.moneytalks.utils.DateUtils;

import static android.app.Activity.RESULT_OK;

public class AddEditNoteFragment extends BaseMvpFragment<AddEditNoteContract.View,
        AddEditNoteContract.Presenter> implements AddEditNoteContract.View,
        DatePickerDialog.OnDateSetListener {
    public static final String TAG = AddEditNoteFragment.class.getSimpleName();

    @BindView(R.id.title_edit)
    TextInputEditText titleEdit;
    @BindView(R.id.date_edit)
    TextInputEditText dateEdit;
    @BindView(R.id.content_edit)
    TextInputEditText contentEdit;

    DatePickerDialog datePickerDialog;

    DateTime dateTime = DateTime.now();

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
                dateTime = note.date();
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

    @OnClick(R.id.date_edit)
    void onDateClick() {
        showDatePickerDialog();
    }

    @Override
    public void showViewOnError(String text) {
        showSnackbar(text);
    }

    @Override
    public void showNoteData(Note note) {
        titleEdit.setText(note.title());
        dateEdit.setText(DateUtils.getLongDateStringFromDateTime(note.date()));
        contentEdit.setText(note.content());
    }

    @Override
    public void showAddOrUpdateSuccessView() {
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

    public void addOrUpdateNote() {
        getPresenter().addOrUpdateNote();
    }


    private void showDatePickerDialog() {
        datePickerDialog = new DatePickerDialog(
                getContext(), this, dateTime.getYear(), dateTime.getMonthOfYear() - 1,
                dateTime.getDayOfMonth());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateTime = new DateTime(year, month, dayOfMonth, 0, 0);
        dateEdit.setText(DateUtils.getLongDateStringFromDateTime(dateTime));
    }
}