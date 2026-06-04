package io.github.williamledo.icompras.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "io.github.williamledo.icompras.pedidos.client")
public class ClientsConfig {

}
