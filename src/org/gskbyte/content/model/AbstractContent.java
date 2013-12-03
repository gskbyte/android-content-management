package org.gskbyte.content.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.gskbyte.util.Logger;

import android.content.Context;

public abstract class AbstractContent<T extends AbstractContent<T>>
{

private Map<String, ExtraData> extraDatas = null;

public abstract int getExtraDataLocation();
public abstract Context getContext();

@SuppressWarnings("unchecked")
public final T self()
{ return (T) this; }

private final void initializeExtraDatas()
{ extraDatas = new HashMap<String, ExtraData>(); }

public final boolean hasExtraDatas()
{
    if(extraDatas != null)
        return !extraDatas.isEmpty();
    else
        return false;
}

public final int countExtraDatas()
{
    if(extraDatas != null)
        return extraDatas.size();
    else
        return 0;
}

public final Map<String, ExtraData> getExtraDatas()
{
    if(extraDatas != null)
        return Collections.unmodifiableMap(extraDatas);
    else
        return null;
}

protected final boolean containsExtraData(String filename)
{
    if(extraDatas != null)
        return extraDatas.containsKey(filename);
    else
        return false;
}

protected final ExtraData getExtraData(String filename)
{
    if(extraDatas != null)
        return extraDatas.get(filename);
    else
        return null;
}

protected final ExtraData addExtraData(int location, String filename)
{
    if(extraDatas == null)
        initializeExtraDatas();
    
    ExtraData data = new ExtraData(this, location, filename);
    if( extraDatas.put(data.filepath, data) != null)
        Logger.error(getClass(), "An ExtraData with the path '"+data.filepath+"' already exists");
    return data;
}

protected final ExtraData addExtraData(String urlString, String filename)
{
    if(extraDatas == null)
        initializeExtraDatas();
    
    ExtraData data = null;
    try {
        URL url = new URL(urlString.replaceAll("\\s", ""));
        data = new ExtraData(this, url, filename);
        if( extraDatas.put(data.filepath, data) != null)
            Logger.error(getClass(), "An ExtraData with the path '"+data.filepath+"' already exists");
        return data;

    } catch (MalformedURLException e) {
        Logger.error(getClass(), "Invalid extra data url: '"+urlString+"'");
    }
    return data;
}

protected final ExtraData removeExtraData(String filename)
{
    if(extraDatas != null)
        return extraDatas.remove(filename);
    else
        return null;
}

protected void clearExtraDatas()
{ extraDatas = null; }



}
