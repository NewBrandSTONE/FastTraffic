package com.dahua.oz.t.traffic.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.dahua.oz.t.core.ui.recycler.RgbValue;
import com.dahua.oz.t.traffic.R;

/**
 * 与Index的CoordinateLayout配合
 * 这个泛型是表示与Toolbar进行滑动配合
 *
 * @author O.z Young
 * @version 2018/4/21
 */
@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<android.support.v7.widget.Toolbar> {

    /**
     * 顶部距离
     */
    private int mDistanceY = 0;
    /**
     * 颜色变化速率
     */
    private static final int SHOW_SPEED = 3;

    /**
     * 定义颜色
     */
    private final RgbValue RGB_VALUE = RgbValue.create(255, 124, 2);

    public TranslucentBehavior() {
    }

    /**
     * 必须有一个两个参数的构造方法，否则会报错
     * 因为在Behavior里面，是通过反射获取TranslucentBehavior类的
     *
     * @param context
     * @param attrs
     */
    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 这里需要依赖Index的RecyclerView
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency.getId() == R.id.rv_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        mDistanceY += dy;
        // toolbar的高度
        final int targetheight = child.getBottom();
        // 当滑动的时候，并且距离小于toolbar的高度时，调整渐变色
        if (mDistanceY > 0 && mDistanceY <= targetheight) {
            final float scale = (float) mDistanceY / targetheight;
            final float alpha = scale * 255;
            child.setBackgroundColor(Color.argb((int) alpha, RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        } else if (mDistanceY > targetheight) {
            child.setBackgroundColor(Color.rgb(RGB_VALUE.red(), RGB_VALUE.green(), RGB_VALUE.blue()));
        }
    }
}
