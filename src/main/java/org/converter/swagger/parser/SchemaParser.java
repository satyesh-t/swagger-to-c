package org.converter.swagger.parser;


import io.swagger.oas.models.media.Schema;
import io.swagger.oas.models.media.XML;
import lombok.extern.slf4j.Slf4j;
import org.converter.swagger.model.clang.output.CStructure;
import org.converter.swagger.model.clang.output.CType;
import org.converter.swagger.model.clang.output.StructElement;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class SchemaParser {

    private TypeParser tp=new TypeParser();

    private CStructure parserEach(Schema<Schema> schema,String key)
    {
        CStructure cstruct=new CStructure(key.toLowerCase());
        cstruct.setFullExample(Optional.of(schema.getXml()).orElse(new XML()).toString());
        List<StructElement> structElements=cstruct.getStructElementList();

        schema.getProperties().forEach((propName, value) -> {

            CType type=tp.getCType(value);

            structElements.add(StructElement.builder()
                    .elementName(propName)
                    .type(type)
                    .example(Optional.ofNullable(value.getExample()).orElse("").toString())
                    .build()
            );
        });
        System.out.println(cstruct.createStuctString());

        return  cstruct;
    }

    public List<CStructure> parse(Map<String, Schema> schemaMap)
    {
        List<CStructure> stuctureList=new ArrayList<>();
        schemaMap.forEach((k,v)->{
            stuctureList.add(parserEach(v, k));
        });
        return stuctureList;
    }






}
