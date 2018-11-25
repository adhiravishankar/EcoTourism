package edu.gatech.ecotourism.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.gatech.ecotourism.Contact;
import edu.gatech.ecotourism.R;
import edu.gatech.ecotourism.adapters.ContactsAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooveit.library.Fakeit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactsFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private List<Contact> contactList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ContactsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ContactsAdapter(contactList, mListener));
        }
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

        contactList = new ArrayList<>();
        Random random = new Random();
        int[] pictures = {R.drawable.person_amy, R.drawable.person_callie, R.drawable.person_emory,
                R.drawable.person_richard, R.drawable.person_rosalie, R.drawable.person_shreya,
                R.drawable.person_toler, R.drawable.person_will};
        for (int i = 0; i < 20; i++) {
            int index = random.nextInt(pictures.length);
            contactList.add(new Contact(Fakeit.name().name(), Fakeit.phone().formats(),
                    Fakeit.name().firstName().toLowerCase() + "@" + Fakeit.internet().freeEmail(), pictures[index]));
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
        void onListFragmentInteraction(Contact item);
    }
}
