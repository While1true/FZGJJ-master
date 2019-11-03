package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.RefreshViews.HeadWrap.DefaultRefreshWrap;
import com.ck.hello.nestrefreshlib.View.RefreshViews.SRecyclerView;
import com.lecheng.hello.fzgjj.Bean.MessageBean;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.SpannableStringUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vange on 2017/9/7.
 */

public class PushMessageActivity extends AppCompatActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.srecyclerview)
    SRecyclerView srecyclerview;
    private int type = -1;
    List<MessageBean> messages = new ArrayList<>(0);
    private CommonAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pushmessage_activity);
        ButterKnife.bind(this);
        if (type == -1)
            type = getIntent().getIntExtra("type", 0);

        init();

        quiry();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        quiry();
    }

    private void quiry() {
        adapter.getDatas().clear();

        Observable.just(type)
                .observeOn(Schedulers.io())
                .map(new Function<Integer, List<MessageBean>>() {
                    @Override
                    public List<MessageBean> apply(@NonNull Integer integer) throws Exception {
                        List<MessageBean> mess = null;
                        switch (type) {
                            //扣款还款提示
                            case 1:
                                mess = DataSupport.where(" type = 1 ").order(" id desc ").find(MessageBean.class);
                                break;
                            case 2:
                                mess = DataSupport.where(" type = 2 ").order(" id desc ").find(MessageBean.class);
                                break;
                        }
                        return mess;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserve<List<MessageBean>>() {
                    @Override
                    public void onNext(List<MessageBean> messageBeen) {
                        adapter.getDatas().addAll(0, messageBeen);
                        adapter.notifyDataSetChanged();
                        srecyclerview.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                srecyclerview.notifyRefreshComplete();
                            }
                        }, 1000);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (adapter.getDatas().size() == 0)
                            new MyToast(PushMessageActivity.this, "没有相关信息", 0);
                        srecyclerview.notifyRefreshComplete();
                        e.printStackTrace();

                    }
                });

    }

    private void init() {

        adapter = new CommonAdapter<MessageBean>(this, R.layout.message_item, messages) {

            @Override
            protected void convert(ViewHolder holder, MessageBean message, int position) {

                setText((TextView) holder.getView(R.id.name), message.getName());
                setText((TextView) holder.getView(R.id.time), message.getToday());
                setText((TextView) holder.getView(R.id.period), SpannableStringUtils.getLoanNotice(message.getQs()));
                setText((TextView) holder.getView(R.id.title), message.getTitle());
                setText((TextView) holder.getView(R.id.capital), SpannableStringUtils.getLoanNotice(message.getYhbj()));
                setText((TextView) holder.getView(R.id.interests), SpannableStringUtils.getLoanNotice(message.getYhlx()));
                setText((TextView) holder.getView(R.id.pay), SpannableStringUtils.getLoanNotice(message.getYhje()));
                setText((TextView) holder.getView(R.id.date), SpannableStringUtils.getLoanNotice(message.getDate()));
                setText((TextView) holder.getView(R.id.info), SpannableStringUtils.getLoanNotice(message.getRemark()));
            }
        };
        srecyclerview
                .addHeader(new DefaultRefreshWrap(srecyclerview, true))
                .setRefreshMode(true, true, false, false)
                .setAdapter(new LinearLayoutManager(this), adapter)
                .setRefreshingListener(new SRecyclerView.OnRefreshListener() {
                    @Override
                    public void Refreshing() {

                    }

                    @Override
                    public void Loading() {
                        super.Loading();

                    }
                })
                .setRefreshing();

    }

    private void setText(TextView tv, CharSequence charSequence) {
        tv.setText(charSequence);
    }

    @OnClick({R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }


}
