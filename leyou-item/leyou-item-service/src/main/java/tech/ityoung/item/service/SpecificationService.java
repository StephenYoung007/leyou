package tech.ityoung.item.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ityoung.item.mapper.SpecGroupMapper;
import tech.ityoung.item.mapper.SpecParamMapper;
import tech.ityoung.item.pojo.SpecGroup;
import tech.ityoung.item.pojo.SpecParam;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        List<SpecGroup> groups = groupMapper.select(record);
        return groups;
    }

    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        if (gid != null) {
            record.setGroupId(gid);
        }
        if (cid != null) {
            record.setCid(cid);
        }
        if (generic != null) {
            record.setGeneric(generic);
        }
        if (searching != null) {
            record.setSearching(searching);
        }
        List<SpecParam> groups = paramMapper.select(record);
        return groups;
    }

    public List<SpecGroup> queryGroupsWithParams(Long cid) {
        List<SpecGroup> groups = queryGroupsByCid(cid);
        groups.forEach(group -> {
            List<SpecParam> specParams = queryParams(group.getId(), null, null, null);
            group.setParams(specParams);
        });
        return groups;
    }
}
