package com.dahua.oz.t.core.delegate;

/**
 * 后续正式使用的Delegate
 *
 * @author gaohuang_yangzi@dahuatech.com
 * @version 2018/4/3
 */

public abstract class TrafficDelegate extends PermissionCheckDelegate {

    /**
     * 获取父类的Delegate
     *
     * @return 父类delegate
     */
    @SuppressWarnings("unchecked")
    public <T extends TrafficDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}
