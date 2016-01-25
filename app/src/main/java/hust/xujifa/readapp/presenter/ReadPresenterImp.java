package hust.xujifa.readapp.presenter;
import android.util.Log;
import hust.xujifa.readapp.action.ReadAction;
public class ReadPresenterImp implements ReadPresenter{
private static final String TAG=ReadPresenterImp.class.getSimpleName();
private ReadAction action;
public ReadPresenterImp(ReadAction action){
this.action=action;
}
}
