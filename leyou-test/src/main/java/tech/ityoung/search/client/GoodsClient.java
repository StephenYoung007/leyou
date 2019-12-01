package tech.ityoung.search.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import tech.ityoung.item.api.GoodsApi;
import tech.ityoung.item.pojo.SpuDetail;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {

}
