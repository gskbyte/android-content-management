package org.gskbyte.content.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.gskbyte.download.DiskDownload;
import org.gskbyte.util.IOUtils;

import android.content.Context;
import android.webkit.MimeTypeMap;

public final class ExtraData
implements Serializable
{
private static final long serialVersionUID = -967079383896305298L;


@Getter
public final AbstractContent<?> parent;
@Getter
public final URL url;
@Getter
public final int location;
@Getter
public final String filepath;
@Getter @Setter
public boolean forceDownload = false;

public ExtraData(AbstractContent<?> parent, URL url)
{
    this(parent, url, SecureFilenameFromURL(url));
}

public ExtraData(AbstractContent<?> parent, URL url, String filepath)
{
    this.parent = parent;
    this.url = url;
    this.location = parent.getExtraDataLocation();
    this.filepath = filepath;
}

public ExtraData(AbstractContent<?> parent, int location, String filepath)
{
    this.parent = parent;
    this.url = null;
    this.location = location;
    this.filepath = filepath;
}


public final Context getContext()
{ return parent.getContext(); }

public boolean existsFile()
{ return IOUtils.ExistsFile(getLocation(), filepath, getContext()); }

public boolean deleteFile()
{ return IOUtils.DeleteFile(getLocation(), filepath, getContext()); }

public boolean isDownloadable()
{ return url != null; }

public DiskDownload.Request getDownloadRequest()
{
    if( !isDownloadable() )
        throw new IllegalStateException("getDownloadRequest called for non-downloadable ExtraData");
    return new DiskDownload.Request(url, getContext(), getLocation(), filepath);
}

@Override
public int hashCode()
{ return filepath.hashCode(); }

private String getMimeExtensionFromString(String s)
{
    String extension = MimeTypeMap.getFileExtensionFromUrl(s);
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
}

public String guessedMimeContentType()
{
    String type = getMimeExtensionFromString(filepath);
    if(type == null) {
        type = URLConnection.guessContentTypeFromName(url.getFile());
    }
    return type;
}

public static String SecureFilename(String path)
{
    try {
        path = URLDecoder.decode(path, "UTF-8");
    } catch (UnsupportedEncodingException e) {
        // won't happen
    }
    path = StringUtils.deleteWhitespace(path);
    
    path = StringUtils.stripAccents(path);
    path = path.replace(' ', '_');
    
    return path;
}

public static String SecureFilenameFromURL(String urlString)
{
    return SecureFilename( IOUtils.LastPathComponent(urlString) );
}

public static String SecureFilenameFromURL(URL url)
{
    return SecureFilenameFromURL(url.getPath());
}

}
