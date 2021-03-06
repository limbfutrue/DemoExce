package com.baselibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baselibrary.base.MyApplication;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 工具类
 */
public class CommonUtils {
    //随机数数组
    private static final char[] CHARS = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static <T> boolean isListNull(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> boolean isListEmpty(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> boolean isListNotNull(Collection<T> collection) {
        return !isListNull(collection);
    }

    public static <T> boolean isListNotNull(Map map) {
        return !isListNull(map);
    }

    public static <T> boolean isListNull(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getFileName(String extraStr, boolean isNeedTime, boolean isShow) {
        return getFileName(extraStr, isNeedTime, isShow, ".jpg");
    }

    /**
     * @param extraStr   文件名
     * @param isNeedTime 是否需要时间
     * @param isShow     是否需要后缀名
     * @param type       后缀名
     * @return
     */
    public static String getFileName(String extraStr, boolean isNeedTime, boolean isShow, String type) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        if (isNeedTime) {
            if (isShow) {
                return format.format(date) + "_" + extraStr + type;
            } else {
                return format.format(date) + "_" + extraStr;
            }
        } else {
            if (isShow) {
                return extraStr + type;
            } else {
                return extraStr;
            }
        }
    }


    // 获取系统版本号显示
    public static String getSystemVersionName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getApplicationInfo().packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return true=全是中文
     */
    public static boolean checkNameChese(String name) {
        boolean res = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++) {
            if (!isChinese(cTemp[i])) {
                res = false;
                break;
            }
        }
        return res;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 防止按钮重复被点击
     */
    public static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastClick(int clickTime) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < clickTime) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 防止按钮重复被点击
     */
    private static int lastClickId = 0;

    /**
     * 防止按钮重复被点击
     *
     * @param clickId 被点击view的id
     * @return
     */
    public static boolean isFastDoubleClick(int clickId) {
        if (clickId == lastClickId) {
            return isFastDoubleClick();
        }

        lastClickTime = System.currentTimeMillis();
        lastClickId = clickId;
        return false;
    }

    /**
     * 防止按钮重复被点击
     */
    private static View lastClickView = null;

    /**
     * 防止按钮重复被点击
     *
     * @param view 被点击view
     * @return
     */
    public static boolean isFastDoubleClick(View view) {
        if (view == lastClickView) {
            return isFastDoubleClick();
        }

        lastClickTime = System.currentTimeMillis();
        lastClickView = view;
        return false;
    }


    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return false;
        }

        cardId = cardId.replaceAll(" ", "");
        String nonCheckCodeCardId = cardId.substring(0, cardId.length() - 1);
        if (!nonCheckCodeCardId.matches("\\d+")) {
            return false;
        }

        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
            return false;
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return ((((luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0')) == (cardId.charAt(cardId.length() - 1))) ? true : false);
    }

    public static void openSoftKeyBoard(Context context) {
        if (context != null) {
            // Configuration initLayoutConfig = context.getResources().getConfiguration();
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean isKeyBoardVisiable(Activity activity) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        return params.softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;
    }

    public static void closeSoftKeyBoard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && activity.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static void closeSoftKeyBoard(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static void closeSoftKeyBoard(Window window, Context context) {
        if (window != null && context != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null && window.getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(window.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    public static Pattern getUrlPattern() {
        return Pattern.compile("^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$", Pattern.CASE_INSENSITIVE);
    }

    /**
     * 小数点两位
     *
     * @param str
     * @return
     */
    public static String castDoubleTwo(String str) {
        if (StringUtils.isNull(str))
            str = "0.00";
        String encStr = "";
        try {
            encStr = new DecimalFormat("0.00").format(Double.parseDouble(str));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return encStr;
    }

    public static String getTimeStr(String s) {
        return StringUtils.isNotNull(s) ? DateUtil.getyyyyMMddHHmm(Long.parseLong(s)) : "";
    }

    public static String getSex(String s) {
        String sex = "必填";
        if ("0".equals(s))
            sex = "男";
        else if ("1".equals(s))
            sex = "女";

        return sex;
    }


    public static String getAppVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String versionCode = "";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        int versionCode = 1;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @SuppressLint("MissingPermission")
    public static String getImei(Context context, String imei) {
        String ret = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            ret = telephonyManager.getDeviceId();
        } catch (Exception e) {
            Log.d("error_code", "getImei: " + e.getMessage());
        }
        if (StringUtils.isNotNull(ret)) {
            return ret;
        } else {
            return imei;
        }
    }

    public static String getAppVersionName() {
        PackageManager packageManager = MyApplication.instance.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        String versionCode = "";
        try {
            packInfo = packageManager.getPackageInfo(MyApplication.instance.getPackageName(), 0);
            versionCode = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 添加换行符
     *
     * @param industryStr
     * @param inds
     * @return
     */
    public static String formatStr(String industryStr, String[] inds) {
        for (int i = 0; i < inds.length; i++) {
            if (inds.length == 1) {
                industryStr += inds[i];
            } else if (inds.length == 2) {
                if (i != 1) {
                    industryStr += inds[i] + "\n";
                } else {
                    industryStr += inds[i];
                }
            } else {
                if (i != 2) {
                    industryStr += inds[i] + "\n";
                } else {
                    industryStr += inds[i];
                }
            }
        }
        return industryStr;
    }

    /**
     * 获取wlan芯片的mac地址，是另一个唯一ID。
     * 但是你需要为你的工程加入android.permission.ACCESS_WIFI_STATE 权限，否则这个地址会为null。
     *
     * @param context
     * @return
     */
    public String getWlanId(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(MyApplication.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        return m_szWLANMAC;
    }

    /**
     * 注意：在双卡双待的手机上imei不是唯一，因为有2个还有getDeviceId在电信卡上得到的是meid，和imei完全不一样。
     *
     * @param context
     * @return
     */
    public String getImei(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    public static String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    /**
     * 设置监听 并设置文本渐变颜色
     * 水平渐变
     * @param editText
     */
    public static void setEditTextLinearGradient(final EditText editText, final int startColor, final int endColor){
        LinearGradient mLinearGradient = new LinearGradient(0, 0, editText.getHint().length()*editText.getTextSize(), 0, startColor,endColor, Shader.TileMode.CLAMP);
        editText.getPaint().setShader(mLinearGradient);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LinearGradient mLinearGradient = new LinearGradient(0, 0, s.length()*editText.getTextSize(), 0, startColor, endColor, Shader.TileMode.CLAMP);
                editText.getPaint().setShader(mLinearGradient);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 设置监听 并设置文本渐变颜色
     * 水平渐变
     * @param textView
     */
    public static void setTextViewLinearGradient(TextView textView, final int startColor, final int endColor){
        LinearGradient mLinearGradient = new LinearGradient(0, 0, textView.getText().length()*textView.getTextSize(), 0, startColor,endColor, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(mLinearGradient);
    }

    /**
     * DrawerLayout设置滑动边距
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage
     */
    public static void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);
            DisplayMetrics dm = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (dm.widthPixels * displayWidthPercentage)));
        } catch (Exception e) {
        }
    }

    /**
     * 获取随机字符
     * @return
     */
    public static String getRandomStr(){
        String code = "";
        for (int i = 0; i < 4; i++) {
            //随机获取CHARS的下标
            int rand = (int) ((Math.random()*10 + 1)*5);
            //如果随机下标大于CHARS数组长度，就默认+A，否则顺序拼接
            if (rand<CHARS.length) {
                //拼接获取的随机数据
                code = code + CHARS[rand];
            }else {
                code = code+"A";
            }
        }

        return code;
    }

    /**
     * 关闭软键盘
     * @param editText
     */
    public static void closeSoftKeyBroad(final EditText editText){
        Timer timer = new Timer();
        timer.schedule(new TimerTask()   {
            public void run() {
                InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 300);
    }

    /**
     * 设置字数限制监听
     * @param context    上下文对象
     * @param et         编辑框的控件对象
     * @param fontLimit  限制字数
     * @param showText   字数显示的文本控件
     */
    public static void setTextChangeListener(Context context, final EditText et, final int fontLimit, final TextView showText){
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(et.getText().toString()) && et.getText().toString().length() > fontLimit) {
                    ToastUtils.showShortToast("最多输入"+fontLimit+"个字符");
                } else {
                    showText.setText(et.getText().toString().length()+"");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
