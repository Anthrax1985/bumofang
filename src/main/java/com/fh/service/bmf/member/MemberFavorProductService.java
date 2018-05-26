package com.fh.service.bmf.member;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.member.MemberFavorProduct;


/** 
 * 类名称：MemberFavorProduct
 * 创建人：tyj
 * 创建时间：2017-07-21
 */

@Service("memberFavorProductService")
public class MemberFavorProductService extends BaseService<MemberFavorProduct>{

	protected String getNamespace() {
		return "MemberFavorProductMapper";
	}
	
}

