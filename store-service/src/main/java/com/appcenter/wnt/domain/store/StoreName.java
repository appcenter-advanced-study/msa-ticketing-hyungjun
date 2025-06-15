package com.appcenter.wnt.domain.store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class StoreName {

    private static final Pattern STORE_NAME_REGEX = Pattern.compile("^(?!\\s+$)[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._\\- ]+$");
    private static final int MAX_LENGTH = 30;

    @Column(name = "store_name", nullable = false, length = MAX_LENGTH)
    private String storeName;

    public StoreName(String storeName) {
        String trimmedStoreName = storeName.trim();
        validateRegex(trimmedStoreName);
        this.storeName = trimmedStoreName;
    }

    private static void validateRegex(String storeName) {
        if (!STORE_NAME_REGEX.matcher(storeName).matches()) {
            throw new RuntimeException("올바르지 않은 가게 이름 형식입니다.");
        }
    }
}
