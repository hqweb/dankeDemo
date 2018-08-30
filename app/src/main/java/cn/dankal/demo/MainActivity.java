package cn.dankal.demo;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.angmarch.views.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindInt;
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
    @BindView(R.id.shrink_pro)
    ImageView shrinkImg;

    ImageView dotImg;

    private ProblemPresenter presenter = new ProblemPresenter();
    RecyclerViewAdapter adapterProblemCat;
    RecyclerViewAdapter adapterProblemList;
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
        adapterProblemCat = new RecyclerViewAdapter(proCategroyList);
        recyclerViewCategroy.setAdapter(adapterProblemCat);

    }

    /**
     * 显示问题分类
     * @param proListTitle
     */
    @Override
    public void showList(LinkedList<String> proListTitle) {
        recyclerViewProList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterProblemList = new RecyclerViewAdapter(proListTitle);
        recyclerViewProList.setAdapter(adapterProblemList);


    }

    /**
     * 问题列表收缩
     * @param view
     */
    @OnClick(R.id.shrink_pro)
    public void onShrinkClicked(View view) {
        if(recyclerViewCategroy.getVisibility() == View.GONE) {
            recyclerViewCategroy.setVisibility(View.VISIBLE);
            shrinkImg.setImageResource(R.drawable.up_arrow);
            //显示问题列表前面的点
            RecyclerViewAdapter.ImgVisibility(View.GONE);
            adapterProblemList.notifyDataSetChanged();
        }
        else{
            recyclerViewCategroy.setVisibility(View.GONE);
            shrinkImg.setImageResource(R.drawable.down_arrow);
            //隐藏问题列表前面的点
            RecyclerViewAdapter.ImgVisibility(View.VISIBLE);
            adapterProblemList.notifyDataSetChanged();
        }
    }

}
