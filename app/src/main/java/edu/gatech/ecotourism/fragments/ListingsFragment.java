package edu.gatech.ecotourism.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.R;
import edu.gatech.ecotourism.adapters.ListingsRecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ListingsFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private List<Listing> listings;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListingsFragment() {
        listings = new ArrayList<>();
        listings.add(new Listing("Riverboat tour of Ganges", "Experience", R.drawable.boat1));
        listings.add(new Listing("Bike Tour of Naneghat Waterfall", "Experience", R.drawable.bike1));
        listings.add(new Listing("Revival Bungalow near GST", "House", R.drawable.home2));
    }

    public static ListingsFragment newInstance() {
        return new ListingsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listings_list, container, false);

        SpeedDialView speedDialView = view.findViewById(R.id.speedDial);
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.action_add_experience, R.drawable.ic_add_white_24dp).
                setLabel(R.string.experience).create());
        speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.action_add_house, R.drawable.ic_add_white_24dp).
                setLabel(R.string.house).create());

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ListingsRecyclerViewAdapter(listings, mListener));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Listing item);
    }
}
