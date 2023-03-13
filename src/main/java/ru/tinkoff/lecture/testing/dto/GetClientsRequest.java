package ru.tinkoff.lecture.testing.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public record GetClientsRequest(int limit, int offset, String sort, String lastName) {

    private static final String DESC_ORDER = "desc";

    public Pageable page() {
        return page(this);
    }

    private static Pageable page(GetClientsRequest request) {
        return PageRequest.of(request.limit(), request.offset(), sort(request));
    }

    private static Sort sort(GetClientsRequest request) {
        var sortingParams = request.sort().split(",");

        var field = sortingParams[0];
        if (sortingParams.length > 1) {
            var order = DESC_ORDER.equals(sortingParams[1])
                    ? Sort.Order.asc(field)
                    : Sort.Order.desc(field);
            return Sort.by(order);
        }
        return Sort.by(field);
    }

}
