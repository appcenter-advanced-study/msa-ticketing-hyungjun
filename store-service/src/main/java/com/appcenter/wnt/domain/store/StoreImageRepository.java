package com.appcenter.wnt.domain.store;

import java.util.List;

public interface StoreImageRepository {
    List<StoreImage> saveAll(List<StoreImage> images);
}
