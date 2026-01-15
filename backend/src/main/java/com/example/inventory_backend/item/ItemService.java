package com.example.inventory_backend.item;

import com.example.inventory_backend.item.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemResponse create(ItemCreateRequest req) {
        Item item = Item.builder()
                .name(req.name())
                .quantity(req.quantity())
                .imageUrl(null) // will be set by upload endpoint later
                .build();

        Item saved = itemRepository.save(item);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> getAll() {
        return itemRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ItemResponse getById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));
        return toResponse(item);
    }

    @Transactional
    public ItemResponse update(Long id, ItemUpdateRequest req) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + id));

        if (req.name() != null && !req.name().isBlank()) item.setName(req.name());
        if (req.quantity() != null) item.setQuantity(req.quantity());

        return toResponse(item);
    }

    @Transactional
    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new IllegalArgumentException("Item not found: " + id);
        }
        itemRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> search(String query) {
        if (query == null || query.isBlank()) return getAll();
        return itemRepository.findByNameContainingIgnoreCase(query).stream().map(this::toResponse).toList();
    }

    private ItemResponse toResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getImageUrl(),
                item.getCreatedAt(),
                item.getUpdatedAt()
        );
    }
}
