package com.sinjinsong.minirest.core.io.impl;

import com.sinjinsong.minirest.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author sinjinsong
 * @date 2018/3/4
 */
public class UrlResource implements Resource {
    
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }
    @Override
    public URL getURL() throws IOException {
        return url;
    }

    @Override
    public URI getURI() throws IOException {
        return null;
    }

    @Override
    public File getFile() throws IOException {
        return null;
    }

    @Override
    public long contentLength() throws IOException {
        return 0;
    }

    @Override
    public long lastModified() throws IOException {
        return 0;
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
