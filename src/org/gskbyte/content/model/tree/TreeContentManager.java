package org.gskbyte.content.model.tree;

import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.gskbyte.content.model.ContentManager;

import android.content.Context;

public abstract class TreeContentManager<TreeElementClass extends TreeContentElement<TreeElementClass, TreeManagerClass>,
                                TreeManagerClass extends TreeContentManager<TreeElementClass, TreeManagerClass>>
extends ContentManager<TreeElementClass, TreeManagerClass>
{

@Getter
private TreeElementClass rootElement;

public TreeContentManager(Context context, String uniqueId)
{
    super(context, uniqueId);
}

@Override
public boolean loadData()
{
    // TODO Auto-generated method stub
    return false;
}

@Override
public boolean containsElementWithKey(String elementKey)
{ return rootElement.containsChildWithKeyRecursive(elementKey); }

@Override
public int countElements()
{ return rootElement.countChildrenRecursive(); }

@Override
public List<TreeElementClass> getElements()
{ return rootElement.getChildrenListRecursive(); }

@Override
public Map<String, TreeElementClass> getElementsMap()
{ return rootElement.getChildrenMapRecursive(); }

@Override
public int getExtraDataLocation()
{
    // TODO REMOVE AND MAKE ABSTRACT
    return 0;
}


}
