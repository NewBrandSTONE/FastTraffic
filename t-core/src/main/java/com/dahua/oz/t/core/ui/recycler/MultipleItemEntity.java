package com.dahua.oz.t.core.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

/**
 * 给RecyclerView的Item实体类
 *
 * @author O.z Young
 * @version 2018/4/18
 */

public class MultipleItemEntity implements MultiItemEntity {

    /**
     * 当数据过多的时候会导致内存溢出，所以这里使用ReferenceQueue
     * <p>
     * LinkedHashMap用来处理每一次加进来的数据，一个key对应一个值
     */
    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULTIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object, Object>> FILEDS_REFERENCE
            = new SoftReference<>(MULTIPLE_FIELDS, ITEM_QUEUE);

    MultipleItemEntity(LinkedHashMap<Object, Object> fields) {
        FILEDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder() {
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        // 这里FILEDS_FEREFENCE.get()实际上得到的是我们传入的MULTIPLE_FILDS
        return (int) FILEDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getField(Object key) {
        return (T) FILEDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?, ?> getFields() {
        return FILEDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key, Object value) {
        FILEDS_REFERENCE.get().put(key, value);
        return this;
    }
}
