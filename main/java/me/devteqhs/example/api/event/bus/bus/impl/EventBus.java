package me.devteqhs.example.api.event.bus.bus.impl;

import me.devteqhs.example.api.event.bus.Listener;
import me.devteqhs.example.api.event.bus.annotations.Target;
import me.devteqhs.example.api.event.bus.bus.Bus;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public final class EventBus<Event> implements Bus<Event> {

    private final Map<Type, List<CallSite<Event>>> callSiteMap;
    private final Map<Type, List<Listener<Event>>> listenerCache;

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    public EventBus() {
        callSiteMap = new HashMap<>();
        listenerCache = new HashMap<>();
    }

    @Override
    public void subscribe(final Object subscriber) {
        for (final Field field : subscriber.getClass().getDeclaredFields()) {
            final Target annotation = field.getAnnotation(Target.class);
            if (annotation != null) {
                final Type eventType = ((ParameterizedType) (field.getGenericType())).getActualTypeArguments()[0];

                if (!field.isAccessible())
                    field.setAccessible(true);
                try {
                    final Listener<Event> listener = (Listener<Event>) LOOKUP.unreflectGetter(field)
                            .invokeWithArguments(subscriber);

                    final byte priority = annotation.value();

                    final List<CallSite<Event>> callSites;
                    final CallSite<Event> callSite = new CallSite<>(subscriber, listener, priority);

                    if (this.callSiteMap.containsKey(eventType)) {
                        callSites = this.callSiteMap.get(eventType);
                        callSites.add(callSite);
                        callSites.sort(Comparator.comparingInt(o -> o.priority));
                    } else {
                        callSites = new ArrayList<>(1);
                        callSites.add(callSite);
                        this.callSiteMap.put(eventType, callSites);
                    }
                } catch (Throwable ignored) {
                }
            }
        }

        this.populateListenerCache();
    }

    private void populateListenerCache() {
        final Map<Type, List<CallSite<Event>>> callSiteMap = this.callSiteMap;
        final Map<Type, List<Listener<Event>>> listenerCache = this.listenerCache;

        for (final Type type : callSiteMap.keySet()) {
            final List<CallSite<Event>> callSites = callSiteMap.get(type);
            final int size = callSites.size();
            final List<Listener<Event>> listeners = new ArrayList<>(size);

            for (int i = 0; i < size; i++)
                listeners.add(callSites.get(i).listener);

            listenerCache.put(type, listeners);
        }
    }

    @Override
    public void unsubscribe(final Object subscriber) {
        for (List<CallSite<Event>> callSites : this.callSiteMap.values()) {
            callSites.removeIf(eventCallSite -> eventCallSite.owner == subscriber);
        }

        this.populateListenerCache();
    }

    public void post(Event event) {
        final List<Listener<Event>> listeners = this.listenerCache.get(event.getClass());

        if (listeners == null)
            return;

        int listenersSize = listeners.size();

        try {
            while (listenersSize > 0) {
                listeners.get(--listenersSize).call(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CallSite<Event> {
        private final Object owner;
        private final Listener<Event> listener;
        private final byte priority;

        public CallSite(Object owner, Listener<Event> listener, byte priority) {
            this.owner = owner;
            this.listener = listener;
            this.priority = priority;
        }
    }
}