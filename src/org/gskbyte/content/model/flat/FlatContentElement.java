package org.gskbyte.content.model.flat;

import org.gskbyte.content.model.ContentElement;

public class FlatContentElement<ElementClass extends FlatContentElement<ElementClass, ManagerClass>,
                                ManagerClass extends FlatContentManager<ElementClass, ManagerClass>>
extends ContentElement< ElementClass, ManagerClass >
{

public FlatContentElement(ManagerClass manager, String uniqueId)
{
    super(manager, uniqueId);
}

}
