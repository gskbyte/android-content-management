package org.gskbyte.content.update;

import org.gskbyte.download.Download;
import org.gskbyte.download.DownloadManager;

import lombok.Getter;

public class ContentUpdater
implements DownloadManager.Listener
{

protected static final int DL_TAG_MAINDATA = 1;

public static enum Status
{
    Ready,
    DownloadingMainData,
    DownloadingExtraData
}

@Getter
private final IUpdatableContentManager<?> manager;
@Getter
private Status status;
private DownloadManager downloadManager;

public ContentUpdater(IUpdatableContentManager<?> manager)
{
    this.manager = manager;
    this.status = Status.Ready;
    
    this.downloadManager = new DownloadManager();
    this.downloadManager.addListener(this);
}

public boolean begin()
{
    if( getStatus() == Status.Ready ) {
        
        return true;
    } else {
        return false;
    }
}

@Override
public void onDownloadManagerCompleted(DownloadManager manager)
{
    
}

@Override
public void onDownloadManagerRate(DownloadManager manager, float rate)
{
    // TODO Auto-generated method stub
    
}

@Override
public void onDownloadStartedInManager(Download download, DownloadManager manager)
{
    
}

@Override
public void onDownloadCompletedInManager(Download download, DownloadManager manager)
{
    if( download.getTag() == DL_TAG_MAINDATA ) {
        
    } else {
        
    }
}

@Override
public void onDownloadFailedInManager(Download download, DownloadManager manager)
{
    if( download.getTag() == DL_TAG_MAINDATA ) {
        
    } else {
        
    }
}

@Override
public void onDownloadRateInManager(Download download, float rate, DownloadManager manager)
{
    
}




}
