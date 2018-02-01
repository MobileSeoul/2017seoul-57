package bulgogi1216.gmail.spaceforeveryone.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bulgogi1216.gmail.spaceforeveryone.R;
import bulgogi1216.gmail.spaceforeveryone.databinding.FragmentHomeBinding;


/**
 * Created by bulgo on 2017-10-27.
 */

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;
    private Context mContext;

    private void initComponent() {
    }

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mContext = mBinding.getRoot().getContext();

        initComponent();

        return mBinding.getRoot();
    }
}
