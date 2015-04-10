<%@include file="/html/init.jsp" %>

<portlet:actionURL name="reindex" var="reindexURL" windowState="normal" />
<aui:form action="<%= reindexURL %>" method="POST" name="fm">
	<aui:fieldset label="select.items">
	<%
		List<Indexer> indexers = IndexerRegistryUtil.getIndexers();
		Set<String> names = new HashSet<String>();
		for (Indexer indexer : indexers){
			String indexName = indexer.getClass().getName();
			if (indexName.contains("com.liferay") && !names.contains(indexName)){
				names.add(indexer.getClass().getName());
				%>
					<aui:input type="checkbox" name="<%=indexName%>"/>
				<%
			}
		}
	%>
	</aui:fieldset>
	
	<aui:button-row>
		<aui:button type="submit" value="reindex"/>
	</aui:button-row>
</aui:form>

