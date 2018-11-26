package edu.gatech.ecotourism.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.gatech.ecotourism.Contact;
import edu.gatech.ecotourism.fragments.ContactsFragment.OnContactsFragmentInteractionListener;
import edu.gatech.ecotourism.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Contact} and makes a call to the
 * specified {@link OnContactsFragmentInteractionListener}.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private final List<Contact> mValues;
    private final OnContactsFragmentInteractionListener mListener;

    public ContactsAdapter(List<Contact> items, OnContactsFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNameView.setText(mValues.get(position).getName());
        holder.mEmailView.setText(mValues.get(position).getEmail());
        holder.mPhoneView.setText(mValues.get(position).getPhone());
        holder.mImageView.setImageResource(mValues.get(position).getPicture());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onContactsFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mNameView;
        final TextView mEmailView;
        final TextView mPhoneView;
        final CircleImageView mImageView;
        Contact mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.tv_name);
            mEmailView = view.findViewById(R.id.tv_email);
            mPhoneView = view.findViewById(R.id.tv_phone);
            mImageView = view.findViewById(R.id.iv_person);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mEmailView.getText() + "'";
        }
    }
}
