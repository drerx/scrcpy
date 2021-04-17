package com.genymobile.scrcpy.wrappers;

import com.genymobile.scrcpy.Ln;

import android.os.IInterface;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StatusBarManager {

    private final IInterface manager;
    private Method expandNotificationsPanelMethod;
    private Method expandSettingsPanelMethod;
    private Object[] expandSettingsPanelArgs;
    private Method collapsePanelsMethod;

    public StatusBarManager(IInterface manager) {
        this.manager = manager;
    }

    private Method getExpandNotificationsPanelMethod() throws NoSuchMethodException {
        if (expandNotificationsPanelMethod == null) {
            expandNotificationsPanelMethod = manager.getClass().getMethod("expandNotificationsPanel");
        }
        return expandNotificationsPanelMethod;
    }

    private Method getExpandSettingsPanel() throws NoSuchMethodException {
        if (expandSettingsPanelMethod == null) {
            try{
                expandSettingsPanelMethod = manager.getClass().getMethod("expandSettingsPanel");
                expandSettingsPanelArgs = new Object[] {};
            } catch (NoSuchMethodException e){
                try{
                    expandSettingsPanelMethod = manager.getClass().getMethod("expandSettingsPanel", String.class);
                    expandSettingsPanelArgs = new Object[] {null};
                } catch (NoSuchMethodException f){
                    f.initCause(e);
                    throw f;
                }
            }

        }
        return expandSettingsPanelMethod;
    }

    private Method getCollapsePanelsMethod() throws NoSuchMethodException {
        if (collapsePanelsMethod == null) {
            collapsePanelsMethod = manager.getClass().getMethod("collapsePanels");
        }
        return collapsePanelsMethod;
    }

    public void expandNotificationsPanel() {
        try {
            Method method = getExpandNotificationsPanelMethod();
            method.invoke(manager);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            Ln.e("Could not invoke method", e);
        }
    }
    public void expandSettingsPanel() {
        try {
            Method method = getExpandSettingsPanel();
            method.invoke(manager, expandSettingsPanelArgs);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            Ln.e("Could not invoke method", e);
        }
    }

    public void collapsePanels() {
        try {
            Method method = getCollapsePanelsMethod();
            method.invoke(manager);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            Ln.e("Could not invoke method", e);
        }
    }
}
