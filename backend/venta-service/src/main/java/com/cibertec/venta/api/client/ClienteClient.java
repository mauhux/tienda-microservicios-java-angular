package com.cibertec.venta.api.client;

import com.cibertec.venta.api.dto.ClienteResponseDto;
import com.cibertec.venta.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "cliente-service",
        url = "${services.cliente.url}",
        configuration = FeignConfig.class
)
public interface ClienteClient {

    @GetMapping("/api/v1/clientes/{id}")
    ClienteResponseDto findById(@PathVariable Integer id);
}
