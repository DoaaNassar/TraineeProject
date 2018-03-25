//package duaa.traineeproject.view;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.util.AttributeSet;
//
//import com.flyco.tablayout.CommonTabLayout;
//
//import static duaa.traineeproject.Constants.FONTS_APP;
//
//
//public class FontCommonTabLayout extends CommonTabLayout {
//
//    private Typeface mTypeface;
//
//    public FontCommonTabLayout(Context context) {
//        super(context);
//        init();
//    }
//
//    public FontCommonTabLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public FontCommonTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        mTypeface = Typeface.createFromAsset(getContext().getAssets(), FONTS_APP);
//    }
//
//    public void setmTypeface(Typeface mTypeface) {
//        this.mTypeface = mTypeface;
//    }
//
//
//
//    //    @Override
////    public void addTab(TabLayout.Tab tab) {
////        super.addTab(tab);
////
////        ViewGroup mainView = (ViewGroup) getChildAt(0);
////        ViewGroup tabView = (ViewGroup) mainView.getChildAt(tab.getPosition());
////
////        int tabChildCount = tabView.getChildCount();
////        for (int i = 0; i < tabChildCount; i++) {
////            View tabViewChild = tabView.getChildAt(i);
////            if (tabViewChild instanceof TextView) {
////                ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
////            }
////        }
////    }
//}
