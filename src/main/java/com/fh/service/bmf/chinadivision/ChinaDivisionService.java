package com.fh.service.bmf.chinadivision;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
import com.fh.entity.bmf.chinadivision.ChinaDivision;


/** 
 * 类名称：ChinaDivision
 * 创建人：tyj
 * 创建时间：2017-07-23
 */

@Service("chinaDivisionService")
public class ChinaDivisionService extends BaseService<ChinaDivision>{

	protected String getNamespace() {
		return "ChinaDivisionMapper";
	}
	
	@SuppressWarnings("unchecked")
	public List<ChinaDivision> listCountyByProvince(ChinaDivision reqInfo) throws Exception{
		return (List<ChinaDivision>)dao.findForList(getNamespace() + ".listCountyByProvince", reqInfo); 
	}
	

}

