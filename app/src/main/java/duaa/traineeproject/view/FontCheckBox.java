package duaa.traineeproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import duaa.traineeproject.Constants;


public class FontCheckBox extends AppCompatCheckBox {


    public FontCheckBox(Context context) {
      super(context);
      Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
      this.setTypeface(face);
    }

    public FontCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
     Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
  this.setTypeface(face);
    }

    public FontCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
     Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
  this.setTypeface(face); 
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
        
       
    }

}