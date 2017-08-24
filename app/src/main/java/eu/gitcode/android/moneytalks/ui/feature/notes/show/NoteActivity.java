package eu.gitcode.android.moneytalks.ui.feature.notes.show;

import android.content.Context;
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

import static eu.gitcode.android.moneytalks.ui.feature.notes.addedit.AddEditNoteActivity.ADD_EDIT_NOTE_REQUEST_CODE;

public class NoteActivity extends BaseActivity {

    public static final String NOTE = "NOTE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startActivity(Context context, Note note) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(NOTE, note);
        context.startActivity(intent);
    }

    public static void startActivityForResult(Fragment fragment, Note note) {
        Intent intent = new Intent(fragment.getContext(), NoteActivity.class);
        intent.putExtra(NOTE, note);
        fragment.startActivityForResult(intent, ADD_EDIT_NOTE_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        if (savedInstanceState == null && getIntent().hasExtra(NOTE)) {
            Note note = getIntent().getParcelableExtra(NOTE);
            setUpToolbar(note.name());
            replaceFragment(R.id.fragment_container, NoteFragment.newInstance(note),
                    NoteFragment.TAG).commit();
        } else {
            setUpToolbar(getString(R.string.new_note));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        NoteFragment noteFragment =
                (NoteFragment) getSupportFragmentManager()
                        .findFragmentByTag(NoteFragment.TAG);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.edit_menu_btn:
                if (noteFragment != null) {
                    noteFragment.handleEditNote();
                }
                break;
            case R.id.remove_menu_btn:
                if (noteFragment != null) {
                    noteFragment.handleRemoveNote();
                }
                break;
            default:
                //no-op
                break;
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
