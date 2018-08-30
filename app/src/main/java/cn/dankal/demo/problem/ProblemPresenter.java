package cn.dankal.demo.problem;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;

import cn.dankal.basic_lib.base.BaseApi;
import cn.dankal.basic_lib.base.BaseView;
import cn.dankal.basic_lib.base.PreHandlerDialogSubscriber;
import cn.dankal.basic_lib.exception.ExceptionHandle;
import cn.dankal.basic_lib.pojo.ProblemCategory;
import cn.dankal.basic_lib.pojo.ProblemList;
import cn.dankal.demo.user.UserService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by root on 18-8-29.
 */

public class ProblemPresenter implements ProblemContact.ProblemPresenter {

    private ProblemContact.ProblemView problemView;
    //问题分类和问题列表的name,title
    private LinkedList<String> problemCategoryNmae = new LinkedList<>();
    private LinkedList<String> problemListTitle = new LinkedList<>();
    @Override
    public void loadList() {
        BaseApi.getRetrofit().create(ProblemService.class)
                .getProblemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .onErrorResumeNext(t -> {
                    return Observable.error(ExceptionHandle.handleException(t));
                })
                .subscribe(new PreHandlerDialogSubscriber<String>(problemView) {
                    @Override
                    public void onNext(String prolemList) {
                        JSONObject jsonResult = JSONObject.parseObject(prolemList).getJSONObject("result");

                        JSONArray jsonCateGory = jsonResult.getJSONArray("categories");
                        JSONArray jsonList = jsonResult.getJSONArray("list");

                        List<ProblemCategory> proCategroyList = JSONArray.parseArray(jsonCateGory.toJSONString(),ProblemCategory.class);
                        List<ProblemList> proListList = JSONArray.parseArray(jsonList.toJSONString(),ProblemList.class);

                        getProblemNmae(proCategroyList, proListList);

                        problemView.showList(problemListTitle);
                        problemView.showCategroy(problemCategoryNmae);

                    }

                });

    }

    /**
     * 取问题分类和问题列表的name,title
     */
    void getProblemNmae(List<ProblemCategory> proCategroyList, List<ProblemList> proListList){

        for(ProblemCategory proCa:proCategroyList){
            problemCategoryNmae.add(proCa.getName());
        }
        for(ProblemList ProLi:proListList){
            problemListTitle.add(ProLi.getTitle());
        }
    }


    @Override
    public void attachView(@NonNull ProblemContact.ProblemView view) {
        problemView = view;
    }

    @Override
    public void detachView() {
        problemView = null;
    }

}
