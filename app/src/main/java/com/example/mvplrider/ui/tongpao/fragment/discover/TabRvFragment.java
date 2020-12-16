package com.example.mvplrider.ui.tongpao.fragment.discover;

import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplrider.R;
import com.example.mvplrider.base.BaseFragment;
import com.example.mvplrider.interfaces.tongpao.discover.IDiscoverTab;
import com.example.mvplrider.model.tongpaodata.DiscoverTabRvBean;
import com.example.mvplrider.presenter.tongpao.discover.DiscoverTabPresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *
 */
public class TabRvFragment extends BaseFragment<DiscoverTabPresenter> implements IDiscoverTab.View {
    private int type;
    @BindView(R.id.rv_tabrv)
    RecyclerView rvTabrv;
    private ArrayList<DiscoverTabRvBean.DataBean.ListBean> listBeans;
    private TabRvAdapter adapter;

    public TabRvFragment(int type) {
        this.type = type;
    }


    @Override
    public int getLayout() {
        return R.layout.layout_disciver_tabrv;
    }

    @Override
    public void initView() {
        rvTabrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTabrv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        listBeans = new ArrayList<>();
        adapter = new TabRvAdapter(listBeans, getActivity());
        rvTabrv.setAdapter(adapter);
    }

    @Override
    public DiscoverTabPresenter createPresenter() {
        return new DiscoverTabPresenter(this);
    }

    @Override
    public void initData() {
        presenter.getTabRv(type);
    }

    @Override
    public void getTabRvBean(DiscoverTabRvBean tabRvBean) {
        Log.e("TAG", "getTabRvBean: "+tabRvBean );
        listBeans.addAll(tabRvBean.getData().getList());
        adapter.notifyDataSetChanged();

    }
}
