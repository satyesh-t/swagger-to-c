package org.converter.swagger.model.clang.output;

import lombok.Data;
import org.converter.swagger.model.clang.output.StructElement;

import java.util.ArrayList;
import java.util.List;
@Data
public class CStructure {
    CType typeDef;
    String structName;
    String fullExample;
    List<StructElement> structElementList;
    StringBuffer structString=new StringBuffer();
    public CStructure(String stuctName) {
        this.structName = stuctName;
        structElementList=new ArrayList<>();
    }

    public String createStuctString( )
    {
        structString.append("typedef struct ");
        structString.append(this.getStructName()+"_st {\n");
        for (StructElement structElement : this.getStructElementList()) {
            String example=structElement.getExample();
            if(!example.isBlank()) {
                structString.append("\t// eg: "+example +"\n");
            }
            structString.append("\t"+structElement.getType().getTypeName()+" "+structElement.getElementName());
            structString.append(structElement.getType().isArray()?"[];\n":";\n");
        }
        structString.append("}"+this.getStructName()+"_t ;\n");
        return structString.toString();
    }
}
