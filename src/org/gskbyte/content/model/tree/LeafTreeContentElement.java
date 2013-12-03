package org.gskbyte.content.model.tree;

public abstract class LeafTreeContentElement<TreeElementClass extends TreeContentElement<TreeElementClass, TreeManagerClass>,
                                             TreeManagerClass extends TreeContentManager<TreeElementClass, TreeManagerClass>>
extends TreeContentElement<TreeElementClass, TreeManagerClass>
{

public LeafTreeContentElement(TreeManagerClass manager, String uniqueId)
{
    super(manager, uniqueId);
}

public LeafTreeContentElement(TreeManagerClass manager, String uniqueId, TreeElementClass parent)
{
    super(manager, uniqueId, parent);
}

@Override
public final boolean isRoot()
{ return false; }

@Override
public final boolean isLeaf()
{ return true; }

public final TreeElementClass addChild(TreeElementClass child)
{
    throw new UnsupportedOperationException( getModificationErrorMessage() );
}

public final TreeElementClass removeChildWithKey(String childKey)
{
    throw new UnsupportedOperationException( getModificationErrorMessage() );
}

public final TreeElementClass removeChildAt(int position)
{
    throw new UnsupportedOperationException( getModificationErrorMessage() );
}

private final String getModificationErrorMessage()
{ return "Leaf elements can't have children"; }

}
