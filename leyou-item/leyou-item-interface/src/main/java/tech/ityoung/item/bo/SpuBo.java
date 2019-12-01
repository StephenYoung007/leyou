package tech.ityoung.item.bo;

import lombok.Data;
import tech.ityoung.item.pojo.Sku;
import tech.ityoung.item.pojo.Spu;
import tech.ityoung.item.pojo.SpuDetail;

import java.util.List;

@Data
public class SpuBo extends Spu {

    private String cname;
    private String bname;

    private List<Sku> skus;

    private SpuDetail spuDetail;

}
