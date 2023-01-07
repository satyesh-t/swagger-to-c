package org.converter.swagger.model.clang.output;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Data
@Slf4j
public class CStructure {
    private CType typeDef;
    private String structName;
    private String fullExample;
    private List<StructElement> structElementList;
    private StringBuffer structString=new StringBuffer();
    private boolean includesOnlyBasicType;

    public CStructure(@NotNull final String stuctName) {
        this.structName = stuctName;
        structElementList=new ArrayList<>();
    }

    public void createStuctString( )
    {
        structString.append("typedef struct ");
        structString.append(this.getStructName()).append("_st {\n");
        structElementList.forEach(element->structString.append(element.getElementString()));
        structString.append("}").append(this.getStructName()).append("_t ;\n");
    }
}
