package com.example.spidertest.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

/**
 * Располагает изображение в Glide сверху
 */
public class CustomCropTransformation extends BitmapTransformation {

    private static final String ID = "com.example.spidertest.view.CustomCropTransformation";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight) {
            return toTransform;
        }
        final float scale;
        final float dx;
        final float dy;
        Matrix m = new Matrix();
        if (toTransform.getWidth() * outHeight > outWidth * toTransform.getHeight()) {
            scale = (float) outHeight / (float) toTransform.getHeight();
            // Принудительно ставим 0, вместо центра картинки
//            dx = (outWidth - toTransform.getWidth() * scale) * 0.5f;
            dx = 0;
            dy = 0;
        } else {
            scale = (float) outWidth / (float) toTransform.getWidth();
            dx = 0;
//            dy = (outHeight - toTransform.getHeight() * scale) * 0.5f;
            dy = 0;
        }

        m.setScale(scale, scale);
        m.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));

        Bitmap result = pool.get(outWidth, outHeight, getNonNullConfig(toTransform));
        TransformationUtils.setAlpha(toTransform, result);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint(TransformationUtils.PAINT_FLAGS);
        canvas.drawBitmap(toTransform, m, paint);

        return result;
    }

    private Bitmap.Config getNonNullConfig(@NonNull Bitmap bitmap) {
        return bitmap.getConfig() != null ? bitmap.getConfig() : Bitmap.Config.ARGB_8888;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterCrop;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
