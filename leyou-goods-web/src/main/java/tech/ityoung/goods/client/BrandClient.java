package tech.ityoung.goods.client;

import org.springframework.cloud.openfeign.FeignClient;
import tech.ityoung.item.api.BrandApi;

@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi {
}
