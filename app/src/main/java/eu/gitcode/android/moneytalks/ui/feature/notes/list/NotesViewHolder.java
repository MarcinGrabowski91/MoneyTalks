package eu.gitcode.android.moneytalks.ui.feature.notes.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Note;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;
import eu.gitcode.android.moneytalks.ui.feature.notes.show.NoteActivity;
import eu.gitcode.android.moneytalks.utils.DateUtils;

public class NotesViewHolder extends BaseViewHolder<Note> {

    @BindView(R.id.title_txt)
    TextView titleTxt;

    @BindView(R.id.content_txt)
    TextView contentTxt;

    @BindView(R.id.date_txt)
    TextView dateTxt;

    private Note note;

    private NoteViewHolderListener listener;

    public NotesViewHolder(ViewGroup parent, NoteViewHolderListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_view_holder, parent, false));
        this.listener = listener;
    }

    @OnClick(R.id.card_view)
    void onCardClick() {
        NoteActivity.startActivity(itemView.getContext(), note);
    }

    @OnLongClick(R.id.card_view)
    boolean onCardLongClick() {
        listener.onNoteLongClicked(note);
        return true;
    }

    @Override
    public void bind(Note item) {
        this.note = item;
        titleTxt.setText(item.title());
        contentTxt.setText(item.content());
        dateTxt.setText(DateUtils.getShortDateStringFromDateTime(item.date()));
    }

    public interface NoteViewHolderListener {
        void onNoteLongClicked(Note note);
    }

}
