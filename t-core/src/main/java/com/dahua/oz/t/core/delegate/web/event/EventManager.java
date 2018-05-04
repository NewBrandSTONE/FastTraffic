package com.dahua.oz.t.core.delegate.web.event;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * 与JavaScript交互的事件管理
 *
 * @author O.z Young
 * @version 2018/5/3
 */

public class EventManager {

    private static final HashMap<String, AbstractEvent> EVENTS = new HashMap<>();

    private EventManager() {

    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    public EventManager addEvent(@NonNull String name, AbstractEvent event) {
        EVENTS.put(name, event);
        return this;
    }

    public AbstractEvent createEvent(@NonNull String action) {
        final AbstractEvent event = EVENTS.get(action);
        if (event == null) {
            return new UndefinedEvent();
        }
        return event;
    }
}
