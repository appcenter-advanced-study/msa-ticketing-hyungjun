package com.appcenter.wnt.domain.menu;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
@NoArgsConstructor
@Embeddable
public class MenuName {
    private static final Pattern MENU_NAME_REGEX =
            Pattern.compile("^(?!\\s+$)[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9._\\- ]+$");
    private static final int MAX_LENGTH = 30;

    @Column(name = "menu_name", nullable = false, length = MAX_LENGTH)
    private String menuName;

    public MenuName(String menuName){
        String trimmedStoreName = menuName.trim();
        validateRegex(trimmedStoreName);
        this.menuName = trimmedStoreName;
    }

    private static void validateRegex(String menuName) {
        if (!MENU_NAME_REGEX.matcher(menuName).matches()) {
            throw new RuntimeException("올바르지 않은 메뉴 이름 형식입니다.");
        }
    }
}
