package eu.gitcode.android.moneytalks.ui.feature.notes.addedit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseActivity;

public class AddEditNoteActivity extends BaseActivity {

    public static final String NOTE = "NOTE";

    public static final int ADD_EDIT_NOTE_REQUEST_CODE = 1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivityForResult(Fragment fragment, Note note) {
        Intent intent = new Intent(fragment.getContext(), AddEditNoteActivity.class);
        intent.putExtra(NOTE, note);
        fragment.startActivityForResult(intent, ADD_EDIT_NOTE_REQUEST_CODE);
    }

    public static void startActivityForResult(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), AddEditNoteActivity.class);
        fragment.startActivityForResult(intent, ADD_EDIT_NOTE_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        if (savedInstanceState == null) {
            AddEditNoteFragment addEditNoteFragment;
            if (getIntent().hasExtra(NOTE)) {
                Note note = getIntent().getParcelableExtra(NOTE);
                addEditNoteFragment = AddEditNoteFragment.newInstance(note);
                setUpToolbar(note.title());
            } else {
                addEditNoteFragment = new AddEditNoteFragment();
                setUpToolbar(getString(R.string.new_note));
            }

            replaceFragment(R.id.fragment_container, addEditNoteFragment,
                    AddEditNoteFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.done_menu_btn) {
            AddEditNoteFragment clientProfileFragment =
                    (AddEditNoteFragment) getSupportFragmentManager()
                            .findFragmentByTag(AddEditNoteFragment.TAG);
            if (clientProfileFragment != null) {
                clientProfileFragment.addOrUpdateNote();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar(String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
