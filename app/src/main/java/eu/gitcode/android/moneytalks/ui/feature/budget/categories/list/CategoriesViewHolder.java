package eu.gitcode.android.moneytalks.ui.feature.budget.categories.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Category;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;

public class CategoriesViewHolder extends BaseViewHolder<Category> {

    @BindView(R.id.title_txt)
    TextView titleTxt;

    private Category category;

    private ExpenseViewHolderListener listener;

    public CategoriesViewHolder(ViewGroup parent, ExpenseViewHolderListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_view_holder, parent, false));
        this.listener = listener;
    }

    @OnClick(R.id.card_view)
    void onCardClick() {
        listener.onCategoryClicked(category);
    }

    @OnLongClick(R.id.card_view)
    boolean onCardLongClick() {
        listener.onCategoryLongClicked(category);
        return true;
    }

    @Override
    public void bind(Category item) {
        this.category = item;
        titleTxt.setText(item.name());
    }

    public interface ExpenseViewHolderListener {
        void onCategoryLongClicked(Category category);

        void onCategoryClicked(Category category);
    }

}
