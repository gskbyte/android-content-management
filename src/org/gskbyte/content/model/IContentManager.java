package org.gskbyte.content.model;

import java.util.List;
import java.util.Map;

import android.content.Context;

public interface IContentManager<ElementClass extends IContentElement>
extends IContentElement
{

public enum Status
{
    Empty,
    Ready
}

public Status getStatus();
public Context getContext();
public Map<String, ExtraData> getExtraDatasRecursive();

public int countElements();
public List<ElementClass> getElements();
public Map<String, ElementClass> getElementsMap();

}
