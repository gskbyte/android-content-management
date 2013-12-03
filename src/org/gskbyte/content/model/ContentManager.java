package org.gskbyte.content.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import android.content.Context;

public abstract class ContentManager<ElementClass extends ContentElement<ElementClass, ManagerClass>,
                                     ManagerClass extends ContentManager<ElementClass, ManagerClass>>
extends AbstractContent< ContentManager<ElementClass, ManagerClass> >
{

protected final Context context;
@Getter
protected final String uniqueId;

private final List<ElementClass> elements = new ArrayList<ElementClass>();
private final Map<String, ElementClass> elementsMap = new LinkedHashMap<String, ElementClass>();

public ContentManager(Context context, String uniqueId)
{
    this.context = context;
    this.uniqueId = uniqueId;
}

@Override
public final Context getContext()
{ return context; }

public final Map<String, ExtraData> getExtraDatasRecursive()
{
    Map<String, ExtraData> allExtraDatas = new HashMap<String, ExtraData>();
    if( hasExtraDatas() )
        allExtraDatas.putAll( getExtraDatas() );
    
    for(ElementClass element : getElements()) {
        Map<String, ExtraData> extraDatas = element.getExtraDatas();
        if(extraDatas!=null)
            allExtraDatas.putAll(extraDatas);
    }
    
    return allExtraDatas;
}

public abstract boolean loadData();

public final boolean containsElement(String elementId)
{ return elementsMap.containsKey(elementId); }

public final int countElements()
{ return elements.size(); }

public final List<ElementClass> getElements()
{ return Collections.unmodifiableList(elements); }

public final ElementClass getElementAt(int position)
{ return elements.get(position); }

public final ElementClass getElement(String elementId)
{ return elementsMap.get(elementId); }

/**
 * Returns the previous element, if any
 * */
protected final ElementClass addElement(ElementClass element)
{
    ElementClass old = elementsMap.get(element.uniqueId);
    
    elementsMap.put(element.uniqueId, element);
    if( old != null) {
        elements.remove(old);
    }
    elements.add(element);
    return old;
}

public final ElementClass removeElement(String elementId)
{
    ElementClass old = elementsMap.remove(elementId);
    if(old != null) {
        elements.remove(old);
    }
    return old;
}

public final ElementClass removeElementAt(int position)
{
    ElementClass old = elements.remove(position);
    if(old != null) {
        elements.remove(old.uniqueId);
    }
    return old;
}


}
