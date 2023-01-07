package org.converter.swagger.model.clang.output;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StructElement {
    private CType type;
    private String elementName;
    private String example;

    public String getElementString() {
        StringBuffer structString = new StringBuffer("");
        if (!example.isBlank()) {
            structString.append("\t// eg: ").append(example).append("\n");
        }
        boolean isArray = type.isArray();
        String finalType = isArray ? type.getTypeName() + " *" : type.getTypeName();
        structString.append("\t").append(finalType).append(" ").append(elementName).append(";\n");
        if (isArray) {
            structString.append("\t" + "// size of array ").append(elementName).append(";\n");
            structString.append("\t" + "int " + elementName + "Size" + ";\n");
        }

        return structString.toString();
    }


}
