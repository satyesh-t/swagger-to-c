package org.converter.swagger.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CStructure {
    String structName;
    List<StructElement> structElementList;
    String typeDef;

    public CStructure(String stuctName)
    {
        this.structName = stuctName;
        structElementList=new ArrayList<>();
    }
}
