package cn.dankal.demo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.dankal.basic_lib.base.BaseActivity;
import cn.dankal.demo.problem.ProblemContact;
import cn.dankal.demo.problem.ProblemPresenter;
import cn.dankal.demo.problem.adapter.RecyclerViewAdapter;

public class MainActivity extends BaseActivity implements ProblemContact.ProblemView{
    @BindView(R.id.problem_list)
    RecyclerView recyclerViewProList;
    @BindView(R.id.problem_categroy)
    RecyclerView recyclerViewCategroy;
    private ProblemPresenter presenter = new ProblemPresenter();
    RecyclerViewAdapter adapterProblem;
    @Override
    protected int contentViewLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponents() {
        presenter.attachView(this);
        presenter.loadList();
    }


    /**
     *显示问题列表
     * @param proCategroyList
     */
    public void showCategroy(LinkedList<String> proCategroyList)
    {
        recyclerViewCategroy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterProblem = new RecyclerViewAdapter(proCategroyList);
        recyclerViewCategroy.setAdapter(adapterProblem);

    }

    /**
     * 显示问题分类
     * @param proListTitle
     */
    @Override
    public void showList(LinkedList<String> proListTitle) {
        recyclerViewProList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterProblem = new RecyclerViewAdapter(proListTitle);
        recyclerViewProList.setAdapter(adapterProblem);

    }

    /**
     * 问题列表收缩
     * @param view
     */
    @OnClick(R.id.shrink_pro)
    public void onShrinkClicked(View view) {
        if(recyclerViewCategroy.getVisibility() == View.GONE)
            recyclerViewCategroy.setVisibility(View.VISIBLE);
        else{
            recyclerViewCategroy.setVisibility(View.GONE);
        }
    }

}
