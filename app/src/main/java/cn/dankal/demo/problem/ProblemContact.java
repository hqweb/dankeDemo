package cn.dankal.demo.problem;

import java.util.LinkedList;
import java.util.List;

import cn.dankal.basic_lib.base.BasePresenter;
import cn.dankal.basic_lib.base.BaseView;
import cn.dankal.basic_lib.pojo.ProblemCategory;
import cn.dankal.basic_lib.pojo.ProblemList;

/**
 * Created by root on 18-8-29.
 */

public interface ProblemContact {

    interface ProblemView extends BaseView{
        void showCategroy(LinkedList<String> proCategroyName);

        void showList(LinkedList<String> proListTitle);
    }

    interface ProblemPresenter extends BasePresenter<ProblemView>{
        void loadList();
    }
}
