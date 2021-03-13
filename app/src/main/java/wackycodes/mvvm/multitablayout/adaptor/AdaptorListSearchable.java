package wackycodes.mvvm.multitablayout.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import wackycodes.mvvm.multitablayout.R;
import wackycodes.mvvm.multitablayout.helper.OnItemSelectListener;
import wackycodes.mvvm.multitablayout.model.ModelListItem;

public class AdaptorListSearchable extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<ModelListItem> listShorted;
    private final List<ModelListItem> listAllItems;
    private OnItemSelectListener onItemSelectListener;

    public AdaptorListSearchable(Context context, List<ModelListItem> listShorted, OnItemSelectListener onItemSelectListener) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.onItemSelectListener = onItemSelectListener;
        this.listShorted = listShorted;
        this.listAllItems = new ArrayList<ModelListItem>();
        this.listAllItems.addAll(listShorted);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return listShorted.size();
    }

    @Override
    public ModelListItem getItem(int position) {
        return listShorted.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_simple_spinner, null);
            holder.name = (TextView) view.findViewById(R.id.text_view_spinner_item);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(listShorted.get(position).getItemName());
        // On Item Selected...!
        view.setOnClickListener( v -> onItemSelectListener.onItemSelected( listShorted.get( position ) ));
        return view;
    }

//    ------------------ Searching Code ----------------------------------------------------
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listShorted.clear();
        if (charText.length() == 0) {
            listShorted.addAll(listAllItems);
        } else {
            for (ModelListItem modelListItem : listAllItems) {
                if (modelListItem.getItemName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listShorted.add(modelListItem);
                }
            }
        }
        notifyDataSetChanged();
        if (listShorted.size() == 0){
            // : Show Data not Matched!
            onItemSelectListener.onNoDataMatched( mContext.getString( R.string.no_data_matched ) );
        }

    }


}
