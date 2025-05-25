package com.appcenter.wnt.domain.menu;

import com.appcenter.wnt.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="menu_items")
public class MenuItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Embedded
    private MenuName menuName;

    @Embedded
    private Price price;

    @Column(name = "is_representative", nullable = false)
    private boolean representative;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "menu_item_details",
            joinColumns = @JoinColumn(name = "menu_item_id")
    )
    @Column(name = "detail", nullable = false, length = 50)
    private Set<String> details;

    @Embedded
    private MenuImage menuImage;

    @Builder
    private MenuItem(Store store, String menuName, int price, boolean representative, Set<String> details, MenuImage menuImage) {
        this.store = store;
        this.menuName = new MenuName(menuName);
        this.price = new Price(price);
        this.representative = representative;
        this.details = details != null ? new HashSet<>(details) : new HashSet<>();
        this.menuImage = menuImage;
    }

    public static MenuItem of(Store store, String menuName, int price, boolean representative, Set<String> details, MenuImage menuImage) {
        return MenuItem.builder().store(store).menuName(menuName).price(price).representative(representative).details(details).menuImage(menuImage).build();
    }
}

