package org.gskbyte.content.update;

import java.net.URL;
import java.util.Date;

import org.gskbyte.content.model.IContentElement;
import org.gskbyte.content.model.IContentManager;

public interface IUpdatableContentManager<ElementClass extends IContentElement>
extends IContentManager<ElementClass>
{
    public ContentUpdater getUpdater();
    public Date getLastUpdate();
    
    public URL getMainDataURL();
    public String getMainDataFilename();
}
