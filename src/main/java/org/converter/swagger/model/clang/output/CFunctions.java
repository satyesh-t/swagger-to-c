package org.converter.swagger.model.clang.output;

import lombok.Data;

import java.util.List;

@Data
public class CFunctions {

    class FunctionArgs
    {
        String argName;
        CType argType;
    }

    private String returnType;
    private String functionName;
    private List<FunctionArgs> functionArgsList;
    private List<String> functionProtoType;
}
