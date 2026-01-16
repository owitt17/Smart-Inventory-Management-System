package com.example.inventory_backend.item;

import org.springframework.web.multipart.MultipartFile;
import com.example.inventory_backend.item.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final FileStorageService fileStorageService;
    private final ItemRepository itemRepository;

    // ADMIN can create
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemResponse> create(@Valid @RequestBody ItemCreateRequest req) {
        return ResponseEntity.ok(itemService.create(req));
    }

    // ADMIN and USER can read
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ItemResponse>> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ItemResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getById(id));
    }

    // ADMIN can update
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemResponse> update(@PathVariable Long id, @Valid @RequestBody ItemUpdateRequest req) {
        return ResponseEntity.ok(itemService.update(id, req));
    }

    // ADMIN can delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Search items by name (ADMIN + USER)
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<ItemResponse>> search(@RequestParam String query) {
        return ResponseEntity.ok(itemService.search(query));
    }

    @PostMapping("/{id}/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemResponse> uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws Exception {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));

        String imageUrl = fileStorageService.storeImage(file);
        item.setImageUrl(imageUrl);
        Item saved = itemRepository.save(item);

        return ResponseEntity.ok(new ItemResponse(
                saved.getId(), saved.getName(), saved.getQuantity(),
                saved.getImageUrl(), saved.getCreatedAt(), saved.getUpdatedAt()
        ));
    }

}
