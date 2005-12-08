/*
 * Carrot2 Project
 * Copyright (C) 2002-2005, Stanislaw Osinski, Dawid Weiss
 * Portions (C) Contributors listed in carrot2.CONTRIBUTORS file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the CVS checkout or at:
 * http://www.cs.put.poznan.pl/dweiss/carrot2.LICENSE
 */
package carrot2.demo.settings;

import java.util.*;

import javax.swing.*;

import carrot2.demo.*;

/**
 * Base class for process settings.
 *  
 * @author Stanislaw Osinski
 */
public abstract class ProcessSettingsBase implements ProcessSettings
{
    private boolean liveUpdate = true;

    protected HashMap params;
    protected Vector listeners = new Vector();

    public boolean hasSettings() {
        return false;
    }

    public abstract JComponent getSettingsComponent();

    public abstract boolean isConfigured();

    public HashMap getRequestParams() {
        synchronized (this) {
            return new HashMap(params);
        }
    }

    public abstract ProcessSettings createClone();

    protected void fireParamsUpdated() {
        for (Iterator i = listeners.iterator(); i.hasNext();)
        {
            ((ProcessSettingsListener) i.next()).settingsChanged(this);
        }
    }

    public void addListener(ProcessSettingsListener listener) {
        this.listeners.add(listener);
    }

    public void setRequestParams(HashMap params) {
        synchronized (this) {
            this.params = params;
            if (liveUpdate) {
                fireParamsUpdated();
            }
        }
    }

    public boolean isLiveUpdate() {
        return liveUpdate;
    }

    public void setLiveUpdate(boolean liveUpdate) {
        this.liveUpdate = liveUpdate;
        if (liveUpdate) {
            fireParamsUpdated();
        }
    }
}