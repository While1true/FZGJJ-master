package RxWeb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vange on 2017/9/12.
 */

public abstract class BaseFragment extends Fragment {
    protected Toolbar toolbar;
    protected View view;
    protected boolean isfirst=true;
    boolean isvisable=false;
    protected boolean isInit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container,false);
        return view;
    }


    protected abstract void initView(Bundle savedInstanceState);

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedInstanceState);
        isInit=true;
        if(isvisable&&isfirst) {
            isfirst=false;
            loadLazy();
        }
    }

    /**
     * 内容布局id
     * @return
     */
    protected abstract int getLayoutId();


    protected void loadLazy(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxLifeUtils.getInstance().remove(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isvisable=isVisibleToUser;
        if(isvisable&&isfirst&&isInit){
            isfirst=false;
            loadLazy();
        }
    }
}
