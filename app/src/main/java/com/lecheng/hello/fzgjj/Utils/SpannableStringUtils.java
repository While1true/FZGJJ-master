package com.lecheng.hello.fzgjj.Utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;

import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.R;

/**
 * Created by vange on 2017/9/8.
 */

public class SpannableStringUtils {
    private static SpannableString getSpannableString(Context context, String part1, String part2, int style1, int style2) {
        SpannableString string = new SpannableString(part1 + part2);
        if (style1 != 0)
            string.setSpan(new TextAppearanceSpan(context, style1), 0, part1.length(), SpannedString.SPAN_INCLUSIVE_EXCLUSIVE);
        if (style2 != 0)
            string.setSpan(new TextAppearanceSpan(context, style2), part1.length(), string.length(), SpannedString.SPAN_INCLUSIVE_EXCLUSIVE);

        return string;
    }

    public static SpannableString getLoanNotice(String content) {
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG, "getLoanNotice: "+content+""+content.indexOf(":"));
        return getSpannableString(MyApplication.getContext(), content.indexOf(":")==-1?"":content.substring(0, content.indexOf(":")+1), content.substring(content.indexOf(":")==-1?0:content.indexOf(":")+1),0, R.style.Color0056ac );
    }
}
