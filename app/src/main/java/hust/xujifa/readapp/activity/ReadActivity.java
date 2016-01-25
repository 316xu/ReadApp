package hust.xujifa.readapp.activity;
import android.util.Log;
import hust.xujifa.readapp.R;
import hust.xujifa.readapp.action.ReadAction;
import hust.xujifa.readapp.presenter.ReadPresenter;
import hust.xujifa.readapp.presenter.ReadPresenterImp;
public class ReadActivity extends BaseActivity implements ReadAction{
private static final String TAG=ReadActivity.class.getSimpleName();
ReadPresenter presenter;
@Override
public void init(){
//TODO: 
//setContentView();
presenter=new ReadPresenterImp(this);
}
}
