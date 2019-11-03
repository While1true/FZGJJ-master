package com.lecheng.hello.fzgjj.TbsFileReader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.FileUtils;

import java.io.File;


public class FileDisplayActivity extends AppCompatActivity {


    private String TAG = "FileDisplayActivity";
    SuperFileView2 mSuperFileView;

    String filePath;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_display);
        init();
    }


    public void init() {
        mSuperFileView = (SuperFileView2) findViewById(R.id.mSuperFileView);
        mSuperFileView.setOnGetFilePathListener(new SuperFileView2.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView2 mSuperFileView2) {
                getFilePathAndShowFile();
            }
        });

        Intent intent = this.getIntent();
        String path = (String) intent.getSerializableExtra("path");

        if (!TextUtils.isEmpty(path)) {
            TLog.d(TAG, "文件path:" + path);
            setFilePath(path);
        }
        mSuperFileView.show();

    }


    private void getFilePathAndShowFile() {
            mSuperFileView.displayFile(new File(getFilePath()));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        TLog.d("FileDisplayActivity-->onDestroy");
        if (mSuperFileView != null) {
            mSuperFileView.onStopDisplay();
        }
    }


    public static boolean show(Context context, String url) {
        if(FileUtils.isDocFile(new File(url))) {
            Intent intent = new Intent(context, FileDisplayActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("path", url);
            intent.putExtras(bundle);
            context.startActivity(intent);
            return true;
        }
        return false;

    }

    public void setFilePath(String fileUrl) {
        this.filePath = fileUrl;
    }

    private String getFilePath() {
        return filePath;
    }

}
