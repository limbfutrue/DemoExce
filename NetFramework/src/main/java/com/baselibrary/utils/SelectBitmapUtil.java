package com.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SelectBitmapUtil {

    public static Bitmap revitionImageSize(String path) throws IOException {
        Bitmap bitmap = null;
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;

        LogUtil.log("options.outWidth:" + options.outWidth);
        LogUtil.log("options.outHeight:" + options.outHeight);

//        while (true) {
//            if ((options.outWidth >> i <= 2000)
//                    && (options.outHeight >> i <= 2000)) {
//                in = new BufferedInputStream(new FileInputStream(new File(path)));
//                options.inSampleSize = (int) Math.pow(1.9D, i);
//
//                LogUtil.log("options.inSampleSize:" + options.inSampleSize);
//                options.inJustDecodeBounds = false;
//                bitmap = BitmapFactory.decodeStream(in, null, options);
//                break;
//            }
//            i += 1;
//        }

        if ((options.outWidth > 1440)) {
            float scaleSize = options.outWidth * 1.0f / 1440;

            if (1.0f < scaleSize && scaleSize <= 1.6f) {
                scaleSize = 1.0f;
            } else if (1.6f < scaleSize && scaleSize <= 2.0f) {
                scaleSize = 2.0f;
            }
            LogUtil.log("scaleSize:" + scaleSize);
            options.inSampleSize = (int) (scaleSize);
        } else {
            options.inSampleSize = 1;
        }
        in = new BufferedInputStream(new FileInputStream(new File(path)));
        LogUtil.log("options.inSampleSize:" + options.inSampleSize);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeStream(in, null, options);

        return bitmap;
    }

    ///////////////////////
    public static void compressBmpToFile(String path) {
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(path)));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(in, null, options);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int option = 80;
            bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos);
            while (baos.toByteArray().length / 1024 > 250) {
                baos.reset();
                option -= 10;
                bitmap.compress(Bitmap.CompressFormat.JPEG, option, baos);
            }

            //FileUtils.saveBitmap(bitmap, "2.jpg");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }

}
