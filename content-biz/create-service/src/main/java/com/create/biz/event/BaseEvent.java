package com.create.biz.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author xmy
 * @date 2021/2/6 14:34
 */
@Getter
@Setter
public abstract class BaseEvent<T> extends ApplicationEvent {

    protected T eventData;

    BaseEvent(Object source, T eventData) {
        super(source);
        this.eventData = eventData;
    }

}
