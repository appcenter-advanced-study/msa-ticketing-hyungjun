package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.MenuItemService;
import com.appcenter.wnt.application.dto.request.CreateMenuRequest;
import com.appcenter.wnt.application.dto.response.MenuItemDetailResponse;
import com.appcenter.wnt.application.dto.response.MenuPageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Menu",description = "Menu API")
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    // 메뉴 생성
    @PostMapping
    public ResponseEntity<MenuItemDetailResponse> createMenu(@RequestBody CreateMenuRequest request) {
        MenuItemDetailResponse response = menuItemService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 메뉴 페이지 조회
    @GetMapping("/store/{storeId}")
    public ResponseEntity<MenuPageResponse> getMenuPage(@PathVariable("storeId") Long storeId) {
        return ResponseEntity.status(HttpStatus.OK).body(menuItemService.getMenuPage(storeId));
    }
}
