package com.liferay.gs.util;

import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * Portlet implementation class ReindexerPortlet
 */
public class ReindexerPortlet extends MVCPortlet {
	
	public void reindex(ActionRequest request, ActionResponse response){
		String companyId = CompanyThreadLocal.getCompanyId() + "";
		
		for (String param : request.getParameterMap().keySet()){
			try{
				if (ParamUtil.getBoolean(request, param)){
					Indexer indexer = IndexerRegistryUtil.getIndexer(param);
					if (indexer != null)
						indexer.reindex(new String[] {companyId});
				}
			}
			catch (Exception e){
				continue;
			}
		}
	}
}
