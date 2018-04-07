package duaa.traineeproject.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import duaa.traineeproject.Constants;

/**
 * Created by AL-Qema on 07/04/18.
 */

public class FontRadioButton extends AppCompatRadioButton {


    public FontRadioButton(Context context) {
        super(context);
        Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
        this.setTypeface(face);
    }

    public FontRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
        this.setTypeface(face);
    }

    public FontRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face= Typeface.createFromAsset(context.getAssets(), Constants.FONTS_APP);
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }

}