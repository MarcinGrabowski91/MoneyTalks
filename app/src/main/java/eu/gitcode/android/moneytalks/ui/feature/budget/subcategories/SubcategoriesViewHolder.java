package eu.gitcode.android.moneytalks.ui.feature.budget.subcategories;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import eu.gitcode.android.moneytalks.R;
import eu.gitcode.android.moneytalks.models.ui.Subcategory;
import eu.gitcode.android.moneytalks.ui.common.base.BaseViewHolder;

public class SubcategoriesViewHolder extends BaseViewHolder<Subcategory> {

    @BindView(R.id.title_txt)
    TextView titleTxt;

    private Subcategory subcategory;

    private SubcategoryViewHolderListener listener;

    public SubcategoriesViewHolder(ViewGroup parent, SubcategoryViewHolderListener listener) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subcategory_view_holder, parent, false));
        this.listener = listener;
    }

    @OnClick(R.id.card_view)
    void onCardClick() {
        listener.onSubcategoryClicked(subcategory);
    }

    @OnLongClick(R.id.card_view)
    boolean onCardLongClick() {
        listener.onSubcategoryLongClicked(subcategory);
        return true;
    }

    @Override
    public void bind(Subcategory item) {
        this.subcategory = item;
        titleTxt.setText(item.name());
    }

    public interface SubcategoryViewHolderListener {
        void onSubcategoryLongClicked(Subcategory subcategory);

        void onSubcategoryClicked(Subcategory subcategory);
    }

}
