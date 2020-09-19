package com.dayu.mapper;

import com.dayu.model.domain.InvoiceManagerFile;

public interface InvoiceManagerFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvoiceManagerFile record);

    int insertSelective(InvoiceManagerFile record);

    InvoiceManagerFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvoiceManagerFile record);

    int updateByPrimaryKey(InvoiceManagerFile record);
}