package edu.gatech.ecotourism.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.gatech.ecotourism.Listing;
import edu.gatech.ecotourism.fragments.ListingsFragment.OnListFragmentInteractionListener;
import edu.gatech.ecotourism.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Listing} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class ListingsRecyclerViewAdapter extends RecyclerView.Adapter<ListingsRecyclerViewAdapter.ViewHolder> {

    private final List<Listing> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListingsRecyclerViewAdapter(List<Listing> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_listings, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.imageView.setImageResource(mValues.get(position).getImage());
        holder.mIdView.setText(mValues.get(position).getTitle());
        holder.mTypeView.setText(mValues.get(position).getType());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        final ImageView imageView;
        final TextView mIdView;
        final TextView mTypeView;
        Listing mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.banner_slider1);
            mIdView = view.findViewById(R.id.item_number);
            mTypeView = view.findViewById(R.id.type);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mTypeView.getText() + "'";
        }
    }
}
