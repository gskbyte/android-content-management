package org.gskbyte.content.model;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import lombok.Getter;
import android.content.Context;

public abstract class ContentManager<ElementClass extends ContentElement<ElementClass, ManagerClass>,
                                     ManagerClass extends ContentManager<ElementClass, ManagerClass>>
extends AbstractContent< ContentManager<ElementClass, ManagerClass> >
implements IContentManager<ElementClass>
{

protected final Context context;
@Getter
public final String key;

public ContentManager(Context context, String key)
{
    this.context = context;
    this.key = key;
}

@Override
public final Context getContext()
{ return context; }

public final Map<String, ExtraData> getExtraDatasRecursive()
{
    final Map<String, ExtraData> allExtraDatas = new HashMap<String, ExtraData>();
    if( hasExtraDatas() )
        allExtraDatas.putAll( getExtraDatas() );
    
    for(ElementClass element : getElements()) {
        Map<String, ExtraData> extraDatas = element.getExtraDatas();
        if(extraDatas!=null)
            allExtraDatas.putAll(extraDatas);
    }
    
    return ImmutableMap.copyOf(allExtraDatas);
}

public abstract boolean loadData();

public abstract boolean containsElementWithKey(String elementKey);

}
