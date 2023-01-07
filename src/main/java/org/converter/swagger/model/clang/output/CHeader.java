package org.converter.swagger.model.clang.output;

import lombok.Data;

import java.util.List;

@Data
public class CHeader {

    private String headerName;
    private List<CStructure> structureList;
    private List<String> functionPrototype;

    private StringBuffer headerString = new StringBuffer("");


    private String intHeader() {
        String pragmaName = "_" + headerName.toUpperCase() + "_H\n";
        String pragma = "#ifndef " + pragmaName + "\n";
        return pragma + "#define " + pragma + "\n";

    }

    private String endHeader() {
        return "#endif";
    }

    private String commonEnums() {
        String commonEnum = "\t#ifndef _COMMON_ENUM\n\t\t#define _COMMON_ENUM\n"
                + "\t\ttypedef char * str;\n"
                + "\t\ttypedef enum boolean {NO,YES} bool;\n"
                + "\t#endif\n";
        return commonEnum;

    }

    public String toHeaderString() {
        StringBuffer structBuffer = new StringBuffer("");
        structureList.forEach(e -> structBuffer.append(e.getStructString()).append("\n"));
        return headerString.append(intHeader())
                .append(commonEnums())
                .append("\n")
                .append(structBuffer)
                .append(endHeader())
                .toString();

    }
}
