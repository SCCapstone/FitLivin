package edu.sc.FitLivin;

/**
 * Created by Owner on 2/22/2016.
 */
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;


public class RoundImage extends Drawable {
    private final Bitmap map;
    private final Paint paint;
    private final RectF r;
    private final int Width;
    private final int Height;

    public RoundImage(Bitmap bitmap) {
        map = bitmap;
        r = new RectF();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        final BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        Height = map.getHeight();
        Width = map.getWidth();

    }
    public void draw(Canvas canvas) {
        canvas.drawOval(r, paint);
    }
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        r.set(bounds);
    }
    public void setAlpha(int alpha) {
        if (paint.getAlpha() != alpha) {
            paint.setAlpha(alpha);
            invalidateSelf();
        }
    }
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
    public int getIntrinsicWidth() {
        return Width;
    }

    public int getIntrinsicHeight() {
        return Height;   }

    public void setFilterBitmap(boolean filter) {
        paint.setFilterBitmap(filter);
        invalidateSelf();    }


    public void setDither(boolean dither) {
        paint.setDither(dither);
        invalidateSelf();
    }



}
