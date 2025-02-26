package com.sismics.books.core.util.mime;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * A collection of MIME types.
 *
 * @author jtremeaux 
 */
public class MimeType {

    public static final String IMAGE_X_ICON = "image/x-icon";
    
    public static final String IMAGE_PNG = "image/png";
    
    public static final String IMAGE_JPEG = "image/jpeg";
    
    public static final String IMAGE_GIF = "image/gif";
    
    public static final String APPLICATION_ZIP = "application/zip";
    
    public static final String APPLICATION_PDF = "application/pdf";
    
    /**
     * Try to guess the MIME type of a file by its magic number (header).
     * 
     * @param is Stream to inspect
     * @return MIME type
     * @throws Exception
     */
    public static String guessMimeType(InputStream is) throws Exception {
        byte[] headerBytes = new byte[64];
        is.mark(headerBytes.length);
        int readCount = is.read(headerBytes);
        is.reset();
        
        if (readCount <= 0) {
            throw new Exception("Cannot read input file");
        }
        
        return guessMimeType(headerBytes);
    }

    /**
     * Try to guess the MIME type of a file by its magic number (header).
     * 
     * @param headerBytes File header (first bytes)
     * @return MIME type
     * @throws UnsupportedEncodingException
     */
    public static String guessMimeType(byte[] headerBytes) throws UnsupportedEncodingException {
        String header = new String(headerBytes, "US-ASCII");
        
        if (header.startsWith("PK")) {
            return MimeType.APPLICATION_ZIP;
        } else if (header.startsWith("GIF87a") || header.startsWith("GIF89a")) {
            return MimeType.IMAGE_GIF;
        } else if (headerBytes[0] == ((byte) 0xff) && headerBytes[1] == ((byte) 0xd8)) {
            return MimeType.IMAGE_JPEG;
        } else if (headerBytes[0] == ((byte) 0x89) && headerBytes[1] == ((byte) 0x50) && headerBytes[2] == ((byte) 0x4e) && headerBytes[3] == ((byte) 0x47) &&
                headerBytes[4] == ((byte) 0x0d) && headerBytes[5] == ((byte) 0x0a) && headerBytes[6] == ((byte) 0x1a) && headerBytes[7] == ((byte) 0x0a)) {
            return MimeType.IMAGE_PNG;
        } else if (headerBytes[0] == ((byte) 0x00) && headerBytes[1] == ((byte) 0x00) && headerBytes[2] == ((byte) 0x01) && headerBytes[3] == ((byte) 0x00)) {
            return MimeType.IMAGE_X_ICON;
        } else if (headerBytes[0] == ((byte) 0x25) && headerBytes[1] == ((byte) 0x50) && headerBytes[2] == ((byte) 0x44) && headerBytes[3] == ((byte) 0x46)) {
            return MimeType.APPLICATION_PDF;
        }
        
        return null;
    }
}
