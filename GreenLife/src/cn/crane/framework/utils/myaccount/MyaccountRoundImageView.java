package cn.crane.framework.utils.myaccount;



import cn.crane.application.greenlife.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class  MyaccountRoundImageView  extends ImageView
{
	 private int mBorderThickness = 0;  
	    private Context mContext;  
	    private int defaultColor = 0xFFFFFFFF;  
	    private int mBorderOutsideColor = 0;  
	    private int mBorderInsideColor = 0;  

	    private int defaultWidth = 0;  
	    private int defaultHeight = 0;  
	  
	    public MyaccountRoundImageView(Context context) {  
	        super(context);  
	        mContext = context;  
	    }  
	  
	    public MyaccountRoundImageView(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        mContext = context;  
	        setCustomAttributes(attrs);  
	    }  
	  
	    public MyaccountRoundImageView(Context context, AttributeSet attrs, int defStyle) {  
	        super(context, attrs, defStyle);  
	        mContext = context;  
	        setCustomAttributes(attrs);  
	    }  
	  
	    private void setCustomAttributes(AttributeSet attrs) {  
	        TypedArray a = mContext.obtainStyledAttributes(attrs,  
	                R.styleable.roundedimageview);  
	        mBorderThickness = a.getDimensionPixelSize(  
	                R.styleable.roundedimageview_border_thickness, 0);  
	        mBorderOutsideColor = a  
	                .getColor(R.styleable.roundedimageview_border_outside_color,  
	                        defaultColor);  
	        mBorderInsideColor = a.getColor(  
	                R.styleable.roundedimageview_border_inside_color, defaultColor);  
	    }  
	  
	    @Override  
	    protected void onDraw(Canvas canvas) {  
	        Drawable drawable = getDrawable();  
	        if (drawable == null) {  
	            return;  
	        }  
	  
	        if (getWidth() == 0 || getHeight() == 0) {  
	            return;  
	        }  
	        this.measure(0, 0);  
	        if (drawable.getClass() == NinePatchDrawable.class)  
	            return;  
	        Bitmap b = ((BitmapDrawable) drawable).getBitmap();  
	        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);  
	        if (defaultWidth == 0) {  
	            defaultWidth = getWidth();  
	  
	        }  
	        if (defaultHeight == 0) {  
	            defaultHeight = getHeight();  
	        }   
	        // if (defaultWidth != 0 && defaultHeight != 0) {  
	        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
	        // defaultWidth, defaultHeight);  
	        // setLayoutParams(params);  
	        // }  
	        int radius = 0;  
	        if (mBorderInsideColor != defaultColor  
	                && mBorderOutsideColor != defaultColor) {// ���廭�����߿򣬷ֱ�Ϊ��Բ�߿����Բ�߿�  
	            radius = (defaultWidth < defaultHeight ? defaultWidth  
	                    : defaultHeight) / 2 - 2 * mBorderThickness;  
	            // ����Բ  
	            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
	                    mBorderInsideColor);  
	            // ����Բ  
	            drawCircleBorder(canvas, radius + mBorderThickness  
	                    + mBorderThickness / 2, mBorderOutsideColor);  
	        } else if (mBorderInsideColor != defaultColor  
	                && mBorderOutsideColor == defaultColor) {// ���廭һ���߿�  
	            radius = (defaultWidth < defaultHeight ? defaultWidth  
	                    : defaultHeight) / 2 - mBorderThickness;  
	            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
	                    mBorderInsideColor);  
	        } else if (mBorderInsideColor == defaultColor  
	                && mBorderOutsideColor != defaultColor) {// ���廭һ���߿�  
	            radius = (defaultWidth < defaultHeight ? defaultWidth  
	                    : defaultHeight) / 2 - mBorderThickness;  
	            drawCircleBorder(canvas, radius + mBorderThickness / 2,  
	                    mBorderOutsideColor);  
	        } else {// û�б߿�  
	            radius = (defaultWidth < defaultHeight ? defaultWidth  
	                    : defaultHeight) / 2;  
	        }  
	        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);  
	        canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight  
	                / 2 - radius, null);  
	    }  
	  

	    public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {  
	        Bitmap scaledSrcBmp;  
	        int diameter = radius * 2;  
	  

	        int bmpWidth = bmp.getWidth();  
	        int bmpHeight = bmp.getHeight();  
	        int squareWidth = 0, squareHeight = 0;  
	        int x = 0, y = 0;  
	        Bitmap squareBitmap;  
	        if (bmpHeight > bmpWidth) {// �ߴ��ڿ�  
	            squareWidth = squareHeight = bmpWidth;  
	            x = 0;  
	            y = (bmpHeight - bmpWidth) / 2;  

	            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
	                    squareHeight);  
	        } else if (bmpHeight < bmpWidth) {// ����ڸ�  
	            squareWidth = squareHeight = bmpHeight;  
	            x = (bmpWidth - bmpHeight) / 2;  
	            y = 0;  
	            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,  
	                    squareHeight);  
	        } else {  
	            squareBitmap = bmp;  
	        }  
	  
	        if (squareBitmap.getWidth() != diameter  
	                || squareBitmap.getHeight() != diameter) {  
	            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,  
	                    diameter, true);  
	  
	        } else {  
	            scaledSrcBmp = squareBitmap;  
	        }  
	        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),  
	                scaledSrcBmp.getHeight(), Config.ARGB_8888);  
	        Canvas canvas = new Canvas(output);  
	  
	        Paint paint = new Paint();  
	        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),  
	                scaledSrcBmp.getHeight());  
	  
	        paint.setAntiAlias(true);  
	        paint.setFilterBitmap(true);  
	        paint.setDither(true);  
	        canvas.drawARGB(0, 0, 0, 0);  
	        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,  
	                scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,  
	                paint);  
	        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);  
 
	        // bmp.recycle();  
	        // squareBitmap.recycle();  
	        // scaledSrcBmp.recycle();  
	        bmp = null;  
	        squareBitmap = null;  
	        scaledSrcBmp = null;  
	        return output;  
	    }  
	  

	    private void drawCircleBorder(Canvas canvas, int radius, int color) {  
	        Paint paint = new Paint();  
 
	        paint.setAntiAlias(true);  
	        paint.setFilterBitmap(true);  
	        paint.setDither(true);  
	        paint.setColor(color);  

	        paint.setStyle(Paint.Style.STROKE);  

	        paint.setStrokeWidth(mBorderThickness);  
	        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);  
	    }  
	  
}
