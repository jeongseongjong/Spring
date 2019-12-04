package com.biz.iolist.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.domain.ProductDTO;

public interface ProductDao {

	public List<ProductDTO> selectAll();
	public List<ProductDTO> findAll();
	public String getPCode();
	public ProductDTO findByPCode(String p_code);
	
	public int insert(ProductDTO ProductDTO);
	public int delete(String p_code);
	public int update(ProductDTO ProductDTO);
	public List<ProductDTO> findByName(@Param("p_name")String strText);
}
