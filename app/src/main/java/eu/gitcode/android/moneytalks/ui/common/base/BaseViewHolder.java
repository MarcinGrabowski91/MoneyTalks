package eu.gitcode.android.moneytalks.ui.common.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    abstract public void bind(T item);
}
