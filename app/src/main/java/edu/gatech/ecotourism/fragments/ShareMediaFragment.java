package edu.gatech.ecotourism.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.gatech.ecotourism.Glide4Engine;
import edu.gatech.ecotourism.R;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareMediaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShareMediaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareMediaFragment extends Fragment implements PermissionListener {

    private OnFragmentInteractionListener mListener;
    private TextView choosePhoto;
    private int REQUEST_CODE_CHOOSE = 8001;

    public ShareMediaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShareMediaFragment.
     */
    private static ShareMediaFragment newInstance() {
        return new ShareMediaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_share_media, container, false);
        choosePhoto = parentView.findViewById(R.id.choose_photo);

        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(getActivity())
                        .choose(MimeType.ofImage())
                        .countable(true)
                        .maxSelectable(9)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new Glide4Engine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
        return parentView;
    }

    public void onButtonPressed(String instagram) {
        if (mListener != null) {
            mListener.onShareMediaFragmentInteraction(instagram);
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        Dexter.withActivity((Activity) context).withPermission(Manifest.permission.CAMERA)
                .withListener(this).check();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPermissionGranted(PermissionGrantedResponse response) {

    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse response) {

    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onShareMediaFragmentInteraction(String instagram);
    }
}
