package org.gskbyte.content.model;

import java.lang.ref.WeakReference;

import android.content.Context;
import lombok.Getter;

public abstract class ContentElement<ElementClass extends ContentElement<ElementClass, ManagerClass>,
                                     ManagerClass extends ContentManager<ElementClass, ManagerClass>>
extends AbstractContent< ContentElement<ElementClass, ManagerClass> >
implements IContentElement
{

@Getter
protected final WeakReference< ManagerClass > managerRef;
@Getter
private final String key;

public ContentElement(ManagerClass manager, String key)
{
    if(manager == null) {
        throw new IllegalArgumentException("manager can't be null");
    } else if ( manager.containsElementWithKey(key) ) {
        throw new IllegalArgumentException("uniqueId must be unique: " + key);
    }
    
    this.managerRef = new WeakReference<ManagerClass>(manager);
    this.key = key;
}

@Override
public final Context getContext()
{ return getManager().getContext(); }

public final ManagerClass getManager()
{ return managerRef.get(); }

@Override
public int getExtraDataLocation()
{ return getManager().getExtraDataLocation(); }


}
