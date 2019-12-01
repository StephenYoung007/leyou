package tech.ityoung.search.client;

import org.springframework.cloud.openfeign.FeignClient;
import tech.ityoung.item.api.CategoryApi;


@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
