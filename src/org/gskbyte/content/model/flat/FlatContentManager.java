package org.gskbyte.content.model.flat;

import java.util.List;
import java.util.Map;

import org.gskbyte.collection.ListHashMap;
import org.gskbyte.content.model.ContentManager;

import com.google.common.collect.ImmutableMap;

import android.content.Context;

public abstract class FlatContentManager<ElementClass extends FlatContentElement<ElementClass, ManagerClass>,
                                         ManagerClass extends FlatContentManager<ElementClass, ManagerClass>>
extends ContentManager< ElementClass, ManagerClass >
{
 
private final ListHashMap<String, ElementClass> elements = new ListHashMap<String, ElementClass>() {
    private static final long serialVersionUID = -301743790574462247L;

    @Override
    public String keyForValue(ElementClass element)
    { return element.getKey(); }
};

public FlatContentManager(Context context, String uniqueId)
{
    super(context, uniqueId);
}

public final boolean containsElementWithKey(String elementKey)
{ return elements.containsKey(elementKey); }

public final int countElements()
{ return elements.size(); }

public final List<ElementClass> getElements()
{ return elements.valuesList(); }

public final Map<String, ElementClass> getElementsMap()
{ return ImmutableMap.copyOf(elements); }

public final ElementClass getElementAt(int position)
{ return elements.get(position); }

public final ElementClass getElementWithKey(String elementId)
{ return elements.get(elementId); }

/**
 * Returns the previous element, if any
 * */
protected final ElementClass appendElement(ElementClass element)
{
    return elements.put(element.getKey(), element);
}

public final ElementClass removeElement(String elementId)
{ return elements.remove(elementId); }

public final ElementClass removeElementAt(int position)
{ return elements.remove(position); }

}
