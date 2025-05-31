package com.appcenter.wnt.presentation;

import com.appcenter.wnt.application.MenuItemService;
import com.appcenter.wnt.application.dto.request.CreateMenuRequest;
import com.appcenter.wnt.application.dto.response.MenuItemResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Menu",description = "Menu API")
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    // 가게 생성
    @PostMapping
    public ResponseEntity<MenuItemResponse> createMenu(@RequestBody CreateMenuRequest request) {
        MenuItemResponse response = menuItemService.createMenu(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
