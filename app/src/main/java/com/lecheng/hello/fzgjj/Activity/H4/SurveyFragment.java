package com.lecheng.hello.fzgjj.Activity.H4;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import RxWeb.BaseFragment;
import butterknife.ButterKnife;

/**
 * Created by vange on 2017/9/18.
 */

public class SurveyFragment extends BaseFragment {
    int type;//1:单选2:多选

    private ListView listView;
    TextView title;
    List<String> checked = new ArrayList<>(5);
    UnityAdapter<String> adapter = null;
    List<String> datas=new ArrayList<>(0);

    public SurveyFragment setTitle(String titlestr) {
        title.setText(titlestr);
        return this;
    }
    public SurveyFragment setType(int type) {
        this.type = type;
        return this;
    }

    public SurveyFragment reset(List<String> checked, List<String> datas) {
        if (checked == null)
            this.checked.clear();
        else
            this.checked = checked;
        this.datas = datas;
        adapter.setList(datas);
        adapter.notifyDataSetChanged();
        return this;
    }

    @Override
    protected void initView( Bundle savedInstanceState) {
        listView = (ListView) view.findViewById(R.id.listview);
        title = (TextView) view.findViewById(R.id.title);

        adapter = new UnityAdapter<String>(getContext(), datas, R.layout.list_survey_item) {
            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setCbtext(R.id.cb, item);
                final CheckBox cb = helper.getView(R.id.cb);
                final String positions = position + "";
                if (checked.contains(positions))
                    cb.setChecked(true);
                else
                    cb.setChecked(false);

                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cb.isChecked()) {
                            if (type == 1) {
                                checked.clear();
                                checked.add(positions);
                            } else {
                                if (!checked.contains(positions))
                                    checked.add(positions);
                            }
                        } else {
                            if (type == 1) {
                                checked.clear();
                            } else {
                                if (checked.contains(positions))
                                    checked.remove(positions);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        listView.setAdapter(adapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.survey_fragment;
    }

    public List<String> getData() {
        return checked;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
