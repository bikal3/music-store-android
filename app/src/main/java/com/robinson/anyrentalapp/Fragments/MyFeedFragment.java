package com.robinson.anyrentalapp.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.robinson.anyrentalapp.Adapter.FeedAdapter;
import com.robinson.anyrentalapp.BLL.FeedBLL;
import com.robinson.anyrentalapp.Helper.StrictMode;
import com.robinson.anyrentalapp.Model.Feed;
import com.robinson.anyrentalapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFeedFragment extends Fragment {
    private List<Feed> feedList;
    private FeedBLL feedBLL;
    private RecyclerView feedRecycler;


    public MyFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedBLL = new FeedBLL();
        feedList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_feed, container, false);
        feedRecycler = view.findViewById(R.id.feedrecycler);

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);
        feedRecycler.setLayoutAnimation(controller);
        feedRecycler.scheduleLayoutAnimation();
        feedRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    public static MyFeedFragment newInstance() {
        MyFeedFragment myFeedFragment = new MyFeedFragment();
        return myFeedFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        FeedList();
    }

    private void FeedList() {
        StrictMode.StrictMode();
        feedList = feedBLL.getFeed();
        FeedAdapter feedAdapter = new FeedAdapter(getActivity(), feedList);
        feedRecycler.setAdapter(feedAdapter);
    }
}
