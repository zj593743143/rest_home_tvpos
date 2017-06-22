package com.yonyou.framework.library.http.parser;

import com.yonyou.framework.library.common.log.Elog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 作者：addison on 15/12/15 19:05
 * 邮箱：lsf@yonyou.com
 */
public abstract class FileCacheableParser<T> extends DataParser<T> {
    protected File file;
    public FileCacheableParser() {
    }
    public FileCacheableParser(File saveToFile) {
        this.file = saveToFile;
    }

    public final T readFromDiskCache(File file) throws IOException {
        data = parseDiskCache(file);
        return data;
    }

    /**
     * parse local file
     */
    protected abstract T parseDiskCache(File file) throws IOException;

    @Override
    public boolean isMemCacheSupport() {
        return false;
    }

    protected final File streamToFile(InputStream is, long len) throws IOException {
        File file = request.getCachedFile();
        FileOutputStream fos = null;
        try {
            File pFile = file.getParentFile();
            if (!pFile.exists()) {
                boolean mk = pFile.mkdirs();
                Elog.i(TAG, "keep cache mkdirs result: " + mk + "  path:" + pFile.getAbsolutePath());
            }
            fos = new FileOutputStream(file);
            byte[] tmp = new byte[buffSize];
            int l;
            while (!request.isCancelledOrInterrupted() && (l = is.read(tmp)) != -1) {
                tmp = translateBytes(tmp);
                fos.write(tmp, 0, l);
                readLength += l;
                notifyReading(len, readLength);
            }
            if (file != null) {
                Elog.i("FileParser", "file len: " + file.length());
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return file;
    }

}

