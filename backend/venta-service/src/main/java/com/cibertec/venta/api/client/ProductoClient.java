package com.cibertec.venta.api.client;

import com.cibertec.venta.api.dto.ProductoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "producto-service",
        url = "${services.producto.url}"
)
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoResponseDto findById(@PathVariable Integer id);
}
