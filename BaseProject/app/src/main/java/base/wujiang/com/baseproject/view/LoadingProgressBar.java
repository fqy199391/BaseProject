package base.wujiang.com.baseproject.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.dinus.com.loadingdrawable.render.LoadingDrawable;
import app.dinus.com.loadingdrawable.render.shapechange.CoolWaitLoadingRenderer;
import base.wujiang.com.baseproject.R;


/**
 *
 */
public class LoadingProgressBar extends Dialog {

    private LoadingDrawable mElectricFanDrawable;
    private Context context;
    public LoadingProgressBar(Context context) {
//        super(context,R.style.ShareDialog);
//        super(context, R.style.NormalDialogStyle);

        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_progressbar);
        ImageView imageView = (ImageView) findViewById(R.id.loading_imgv);
        CoolWaitLoadingRenderer coolWaitLoadingRenderer = new CoolWaitLoadingRenderer.Builder(context).build();
        mElectricFanDrawable = new LoadingDrawable(coolWaitLoadingRenderer);
        imageView.setImageDrawable(mElectricFanDrawable);
        this.getWindow().setBackgroundDrawableResource(R.color.transparent);
    }

    public void hide(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= 0x00001000;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                getWindow().getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        hide();
        mElectricFanDrawable.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mElectricFanDrawable.stop();
    }

}
