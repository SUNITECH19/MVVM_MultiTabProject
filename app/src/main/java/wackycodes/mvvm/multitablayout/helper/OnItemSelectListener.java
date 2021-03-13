package wackycodes.mvvm.multitablayout.helper;

import androidx.annotation.Nullable;

import wackycodes.mvvm.multitablayout.model.ModelListItem;

public interface OnItemSelectListener {

    void onItemSelected(@Nullable ModelListItem item);
    void onNoDataMatched( @Nullable String message );

}
