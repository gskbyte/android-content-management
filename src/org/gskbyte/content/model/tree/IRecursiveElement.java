package org.gskbyte.content.model.tree;

import java.util.List;
import java.util.Map;

// this interface contains read-only methods
public interface IRecursiveElement<ParentElementClass, ChildElementClass>
{
    public ParentElementClass getParent();
    public boolean hasDefinedParent();

    public boolean isRoot();
    public int getDepth();
    
    public boolean isLeaf();
    
    // read-only methods
    public int countChildren();
    public boolean containsChildWithKey(String elementKey);
    public List<ChildElementClass> getChildrenList();
    public Map<String, ChildElementClass> getChildrenMap();
    public ChildElementClass getChildAt(int position);
    public ChildElementClass getChildWithKey(String key);
    
    // recursive methods
    public int countChildrenRecursive();
    public boolean containsChildWithKeyRecursive(String elementKey);
    public List<ChildElementClass> getChildrenListRecursive();
    public Map<String, ChildElementClass> getChildrenMapRecursive();
    
    // modification methods
    public ChildElementClass addChild(ChildElementClass child);
    public ChildElementClass removeChildWithKey(String childKey);
    public ChildElementClass removeChildAt(int position);
    
}
