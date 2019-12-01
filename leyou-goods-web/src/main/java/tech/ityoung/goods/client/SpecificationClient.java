package tech.ityoung.goods.client;

import org.springframework.cloud.openfeign.FeignClient;
import tech.ityoung.item.api.SpecificationApi;


@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi {
}
