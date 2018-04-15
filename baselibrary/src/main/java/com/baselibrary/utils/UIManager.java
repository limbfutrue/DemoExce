package com.baselibrary.utils;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class UIManager {

	public static void turnToAct(Context ct, Class<?> cls) {
		Intent it = new Intent(ct, cls);
		ct.startActivity(it);
	}

	public static void turnToAct(Context ct, Class<?> cls, Bundle b) {
		Intent it = new Intent(ct, cls);
		if (b != null) {
			it.putExtras(b);
		}
		ct.startActivity(it);
	}

	public static void turnToActForresult(Activity ct, Class<?> cls, int requestCode, Bundle bundle) {
		Intent it = new Intent(ct, cls);
		if (bundle != null) {
			it.putExtras(bundle);
		}
		ct.startActivityForResult(it, requestCode);
	}
	public static void turnToActForresult_Fragment(Fragment fragment, Activity ct, Class<?> cls, int requestCode, Bundle bundle) {
		Intent it = new Intent(ct, cls);
		if (bundle != null) {
			it.putExtras(bundle);
		}
		fragment.startActivityForResult(it, requestCode);
	}

}
