package wackycodes.mvvm.multitablayout.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import wackycodes.mvvm.multitablayout.R;
import wackycodes.mvvm.multitablayout.adaptor.AdaptorListSearchable;
import wackycodes.mvvm.multitablayout.databinding.FragmentTabPlaceholderBinding;
import wackycodes.mvvm.multitablayout.helper.OnItemSelectListener;
import wackycodes.mvvm.multitablayout.model.ModelListItem;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentTabPlaceholder extends Fragment implements OnItemSelectListener {

    private static final String KEY_FRAGMENT = "KEY_FRAGMENT";
    private static final String KEY_BG_COLOR = "KEY_BG_COLOR";


    public static FragmentTabPlaceholder newInstance(int index, int bgColor) {
        FragmentTabPlaceholder fragment = new FragmentTabPlaceholder();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_FRAGMENT, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            fragmentNum = getArguments().getInt( KEY_FRAGMENT );
        }
    }

    private int fragmentNum;
    private int bgColor;

    // ------------------
    private boolean isDataLoaded = false;
    private FragmentTabPlaceholderBinding fragmentTabPlaceholderBinding;
    private TextView textViewNoData;

    private AdaptorListSearchable adaptorListSearchable;
    private List<ModelListItem> listItems = new ArrayList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        fragmentTabPlaceholderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_placeholder, container, false);

        textViewNoData = (TextView) fragmentTabPlaceholderBinding.textViewNoDataFound;

        // This List for testing Purpose...
        listItems.add( new ModelListItem( "Sukhdev", 1 ));
        listItems.add( new ModelListItem( "Bhagat Singh", 2 ));
        listItems.add( new ModelListItem( "Gadhi Ji", 3 ));
        listItems.add( new ModelListItem( "Jawahar lal nehru", 4 ));
        listItems.add( new ModelListItem( "Bal Gangadhar", 5 ));
        listItems.add( new ModelListItem( "Bhim Ambedkar", 6 ));
        listItems.add( new ModelListItem( "Rani Laxmibai", 7 ));
        listItems.add( new ModelListItem( "VipinChandra Pal", 8 ));
        listItems.add( new ModelListItem( "Rani AvantiBai", 9 ));

        setListItems();

        Log.e( "FRAGMENT", "Num : " + fragmentNum + " Code :" );

        return fragmentTabPlaceholderBinding.getRoot();
    }

    private void setListItems(){
        adaptorListSearchable = new AdaptorListSearchable( getContext(), listItems, this );
        fragmentTabPlaceholderBinding.lisView.setAdapter( adaptorListSearchable );
        adaptorListSearchable.notifyDataSetChanged();
    }

    public void setUIData( ){
        // TODO : Set Data | Query To Load Data |
        Toast.makeText(getContext(), "Num : " + fragmentNum + " Code :" + bgColor, Toast.LENGTH_SHORT).show();
        int bgColor ;
        switch ( fragmentNum ){
            case 2:
                bgColor = getContext().getResources().getColor(R.color.colorBackBabyPink);
                break;
            case 3:
                bgColor = getContext().getResources().getColor(R.color.colorBackBabyBlue);
                break;
            case 4:
                bgColor = getContext().getResources().getColor(R.color.colorGreen);
                break;
            case 5:
                bgColor = getResources().getColor(R.color.colorAccent);
                break;
            case 6:
                bgColor = getResources().getColor(R.color.colorRed);
                break;
            case 7:
            default:
                bgColor = getResources().getColor(R.color.colorBackKhakiLav);
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragmentTabPlaceholderBinding.getRoot().findViewById(R.id.constraintLayout).setBackgroundColor( bgColor );
        }

        if (listItems.size() == 0 && !isDataLoaded){
            // TODO : Query To Load Data...
            // Show Dialog....
            isDataLoaded = true;

        }else{
            if (listItems.size() == 0){
                onNoDataMatched( null );
            }else{
                textViewNoData.setVisibility(View.GONE);
            }
        }

    }

    public void onSearchQuery( String query ){
        if (listItems.size()>0){
            adaptorListSearchable.filter( query );
        }
    }

    @Override
    public void onItemSelected(@Nullable ModelListItem item) {

    }

    @Override
    public void onNoDataMatched( @Nullable String message ) {
        textViewNoData.setVisibility( View.VISIBLE );
        textViewNoData.setText( message != null ? message : getString( R.string.data_not_found ));
    }




}