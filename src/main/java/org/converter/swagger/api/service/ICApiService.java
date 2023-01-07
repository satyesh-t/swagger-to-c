package org.converter.swagger.api.service;

import org.converter.swagger.model.clang.output.CHeader;

public interface ICApiService {


    public CHeader createHeader(String yaml);


}
