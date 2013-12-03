package org.gskbyte.content.model.tree;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gskbyte.collection.ListHashMap;
import org.gskbyte.content.model.ContentElement;

import com.google.common.collect.ImmutableMap;

public class TreeContentElement<TreeElementClass extends TreeContentElement<TreeElementClass, TreeManagerClass>,
                                TreeManagerClass extends TreeContentManager<TreeElementClass, TreeManagerClass>>
extends ContentElement<TreeElementClass, TreeManagerClass>
implements IRecursiveElement<TreeElementClass, TreeElementClass>
{

private WeakReference<TreeElementClass> parent;
private ListHashMap<String, TreeElementClass> children;

public TreeContentElement(TreeManagerClass manager, String uniqueId)
{
    super(manager, uniqueId);
    this.parent = null;
}

public TreeContentElement(TreeManagerClass manager, String uniqueId, TreeElementClass parent)
{
    super(manager, uniqueId);
    this.parent = new WeakReference<TreeElementClass>(parent);
}

// stuff with parent

@Override
public final TreeElementClass getParent()
{ return parent.get(); }

public final boolean hasDefinedParent()
{ return parent != null; }

@Override
public boolean isRoot()
{ return hasDefinedParent() && getParent() == null; }

@Override
public int getDepth()
{
    if( isRoot() ){
        return 0;
    } else {
        return getParent().getDepth()+1;
    }
}

// stuff with children

@Override
public boolean isLeaf()
{ return children.size() == 0; }

@Override
public final int countChildren()
{ return children.size(); }

@Override
public final boolean containsChildWithKey(String elementKey)
{ return children.containsKey(elementKey); }

@Override
public final List<TreeElementClass> getChildrenList()
{ return children.valuesList(); }

@Override
public final Map<String, TreeElementClass> getChildrenMap()
{ return ImmutableMap.copyOf(children); }

@Override
public final TreeElementClass getChildAt(int position)
{ return children.getAt(position); }

@Override
public final TreeElementClass getChildWithKey(String key)
{ return children.get(key); }

@Override
public final int countChildrenRecursive()
{
    int count = countChildren();
    for(TreeElementClass e : getChildrenList()) {
        count += e.countChildrenRecursive();
    }
    return count;
}

@Override
public final boolean containsChildWithKeyRecursive(String elementKey)
{
    if( containsChildWithKey(elementKey) ) {
        return true;
    } else {
        for(TreeElementClass e : getChildrenList()) {
            if( e.containsChildWithKeyRecursive(elementKey) )
                return true;
        }
        return false;
    }
}

@Override
public final List<TreeElementClass> getChildrenListRecursive()
{
    ArrayList<TreeElementClass> list = new ArrayList<TreeElementClass>();
    for(TreeElementClass e : getChildrenList()) {
        list.add(e);
        list.addAll( e.getChildrenListRecursive() );
    }
    
    return list;
}

@Override
public final Map<String, TreeElementClass> getChildrenMapRecursive()
{
    HashMap<String, TreeElementClass> map = new HashMap<String, TreeElementClass>( children );
    for(TreeElementClass e : getChildrenList()) {
        map.putAll( e.getChildrenMapRecursive() );
    }
    return map;
}

public TreeElementClass addChild(TreeElementClass child)
{
    throw new IllegalStateException();
}

public TreeElementClass removeChildWithKey(String childKey)
{
    throw new IllegalStateException();
}

public TreeElementClass removeChildAt(int position)
{
    throw new IllegalStateException();
}
}
