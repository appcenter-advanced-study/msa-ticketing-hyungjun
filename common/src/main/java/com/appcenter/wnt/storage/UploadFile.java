package com.appcenter.wnt.storage;

import java.io.InputStream;

public record UploadFile(
        InputStream stream,
        String originalName
) {
}
