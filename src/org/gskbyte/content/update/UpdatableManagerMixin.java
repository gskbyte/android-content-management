package org.gskbyte.content.update;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import lombok.Getter;

public class UpdatableManagerMixin
{

private final WeakReference< IUpdatableContentManager<?> > manager;
@Getter
private final String mainDataFilename;
@Getter
private final URL mainDataURL;
@Getter
private Date lastUpdate;
@Getter
private final ContentUpdater updater;

public UpdatableManagerMixin(IUpdatableContentManager<?> manager, URL url, String filename)
{
    this.manager = new WeakReference<IUpdatableContentManager<?>>(manager);
    this.mainDataURL = url;
    this.mainDataFilename = filename;
    this.updater = new ContentUpdater(manager);
    
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    long updateLong =  prefs.getLong(getLastUpdateKey(), 0);
    this.lastUpdate = new Date(updateLong);
}

public final void setLastUpdate(Date date)
{
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
    prefs.edit().putLong(getLastUpdateKey(), date.getTime()).commit();
    lastUpdate = date;
}

public final IUpdatableContentManager<?> getManager()
{ return manager.get(); }

public final Context getContext()
{ return getManager().getContext(); }

private final String getLastUpdateKey()
{ return "last_update_manager_" + getManager().getKey(); }

}
