package tech.ityoung.goods.client;


import org.springframework.cloud.openfeign.FeignClient;
import tech.ityoung.item.api.GoodsApi;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {

}
