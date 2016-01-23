package hust.xujifa.readapp.presenter;

import hust.xujifa.readapp.action.MainAction;

/**
 * Created by xujifa on 2016/1/23.
 */
public class MainPresenterImp  implements MainPresenter {
    MainAction action;

    public MainPresenterImp(MainAction action){
        this.action=action;
    }
}
