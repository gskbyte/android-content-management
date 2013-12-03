package org.gskbyte.content.model;

import java.lang.ref.WeakReference;

import android.content.Context;
import lombok.Getter;

public abstract class ContentElement<ElementClass extends ContentElement<ElementClass, ManagerClass>,
                                     ManagerClass extends ContentManager<ElementClass, ManagerClass>>
extends AbstractContent< ContentElement<ElementClass, ManagerClass> >
{

@Getter
protected final WeakReference< ManagerClass > managerRef;
protected final String uniqueId;

public ContentElement(ManagerClass manager, String uniqueId)
{
    if(manager == null)
        throw new IllegalArgumentException("manager can't be null");
    
    this.managerRef = new WeakReference<ManagerClass>(manager);
    this.uniqueId = uniqueId;
}

@Override
public final Context getContext()
{ return getManager().getContext(); }

public ManagerClass getManager()
{ return managerRef.get(); }


}
