package com.cibertec.venta.api.client;

import com.cibertec.venta.api.dto.ProductoResponseDto;
import com.cibertec.venta.api.dto.StockRequestDto;
import com.cibertec.venta.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "producto-service",
        url = "${services.producto.url}",
        configuration = FeignConfig.class
)
public interface ProductoClient {

    @GetMapping("/api/v1/productos/{id}")
    ProductoResponseDto findById(@PathVariable Integer id);

    @PutMapping("/api/v1/productos/{id}/descontar-stock")
    void descontarStock(
            @PathVariable Integer id,
            @RequestBody StockRequestDto dto
    );
}
