package org.gskbyte.content.update;

public interface UpdaterListener
{

public void onUpdaterBeginsUpdate(ContentUpdater updater);
public void onUpdaterCompletesMainData(ContentUpdater updater);
public void onUpdaterFailsMainData(ContentUpdater updater);

public void onUpdaterCompletesExtraDatas(ContentUpdater updater, int failed);

}
