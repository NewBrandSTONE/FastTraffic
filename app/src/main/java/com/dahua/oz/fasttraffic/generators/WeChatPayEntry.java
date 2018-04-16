package com.dahua.oz.fasttraffic.generators;

import com.dahua.oz.t.core.wechat.templates.AppRegisterTemplate;
import com.dahua.oz.t_annotations.PayEntryGenerator;

/**
 * <一句话简述功能>
 * <功能详细描述>
 *
 * @author O.z Young
 * @version 2018/4/16
 */
@PayEntryGenerator(packageName = "com.dahua.oz.fasttraffic",
        payTemplete = AppRegisterTemplate.class)
public interface WeChatPayEntry {
}
