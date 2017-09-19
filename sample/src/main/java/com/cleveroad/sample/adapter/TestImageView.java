package com.cleveroad.sample.adapter;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cleveroad.adaptivetablelayout.ViewHolderImpl;
import com.cleveroad.sample.R;
import com.cleveroad.sample.datasource.DetailsViewHolder;
import com.cleveroad.sample.datasource.TableDataSource;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TestImageView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TestImageView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestImageView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private TableDataSource<String, String, String, String> mTableDataSource;
    private ViewHolderImpl viewHolder;
    private int dataRow;
    private int dataColumn;
    private DetailsViewHolder detailsViewHolder;
    private ViewGroup viewGroup;

    // TODO: Rename and change types of parameters
    private String mParam1;


    private OnFragmentInteractionListener mListener;

    public TestImageView() {
        // Required empty public constructor
        mTableDataSource = null;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TestImageView.
     */
    // TODO: Rename and change types and number of parameters
    public static TestImageView newInstance(int param1) {
        TestImageView fragment = new TestImageView();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    public TableDataSource<String, String, String, String> getmTableDataSource(TableDataSource<String, String, String, String> mTableDataSource) {
        return mTableDataSource;
    }

    public void setmTableDataSource(com.cleveroad.sample.datasource.TableDataSource<String, String, String, String> mTableDataSource) {
        this.mTableDataSource = mTableDataSource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.test_image_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);

        final ImageView imageView = (ImageView) view.findViewById(R.id.ivImage_test);
        String itemData = mTableDataSource.getItemData(getDataRow(), getDataColumn());

        if (TextUtils.isEmpty(itemData)) {
            itemData = "";
        }

        itemData = itemData.trim();
        //imageView.setVisibility(View.VISIBLE);

        Bundle args = getArguments();
        int itemNumber = args.containsKey(ARG_PARAM1) ? args.getInt(ARG_PARAM1) : 1;

        switch (itemNumber) {
            case 1:
                Glide.with(this)
                        .load(itemData)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                startPostponedEnterTransition();
                                imageView.setVisibility(View.INVISIBLE);

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                startPostponedEnterTransition();
                                imageView.setVisibility(View.VISIBLE);
                                //imageView.setImageResource(imageView.getDrawable().hashCode());

                                return false;
                            }
                        })
                        .into(imageView);
                break;
        }
    }

    public void setDataRow(int row) {
        dataRow = row;
    }

    public void setDataColumn(int column) {
        dataColumn = column;
    }

    public void setViewHolder(ViewHolderImpl viewHolder1) {
        viewHolder = viewHolder1;
    }

    public void setViewGroup(ViewGroup viewGroup1) {
        viewGroup = viewGroup1;
    }

    public void setDetailsViewHolder(DetailsViewHolder detailsViewHolder1) { detailsViewHolder = detailsViewHolder1;}

    public int getDataRow() {
        return dataRow;
    }

    public int getDataColumn() {
        return dataColumn;
    }

    public ViewGroup getViewGroup() {return viewGroup;}

    public ViewHolderImpl getViewHolder() {
        return viewHolder;
    }

    public DetailsViewHolder getDetailsViewHolder() {return detailsViewHolder;}

    public class TestViewHolderImage extends ViewHolderImpl {
        ImageView itemImage;

        public TestViewHolderImage(@NonNull View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }




















    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
