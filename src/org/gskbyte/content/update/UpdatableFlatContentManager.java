package org.gskbyte.content.update;

import java.net.URL;
import java.util.Date;

import org.gskbyte.content.model.flat.FlatContentElement;
import org.gskbyte.content.model.flat.FlatContentManager;

import android.content.Context;

public class UpdatableFlatContentManager<ElementClass extends FlatContentElement<ElementClass, ManagerClass>,
                                         ManagerClass extends UpdatableFlatContentManager<ElementClass, ManagerClass>>
extends FlatContentManager<ElementClass, ManagerClass>
implements IUpdatableContentManager<ElementClass>
{

private final UpdatableManagerMixin mixin;

public UpdatableFlatContentManager(Context context, String uniqueId, URL mainDataUrl, String mainDataFilename)
{
    super(context, uniqueId);
    this.mixin = new UpdatableManagerMixin(this, mainDataUrl, mainDataFilename);
}

@Override
public org.gskbyte.content.model.IContentManager.Status getStatus()
{
    // TODO Auto-generated method stub
    return null;
}

@Override
public ContentUpdater getUpdater()
{ return this.mixin.getUpdater(); }

@Override
public Date getLastUpdate()
{ return this.mixin.getLastUpdate(); }

@Override
public URL getMainDataURL()
{ return this.mixin.getMainDataURL(); }

@Override
public String getMainDataFilename()
{ return this.mixin.getMainDataFilename(); }

@Override
public boolean loadData()
{
    // TODO Auto-generated method stub
    return false;
}

@Override
public int getExtraDataLocation()
{
    // TODO Auto-generated method stub
    return 0;
}

}
