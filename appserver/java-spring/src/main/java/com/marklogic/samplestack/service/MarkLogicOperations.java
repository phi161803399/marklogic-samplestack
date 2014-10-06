/*
 * Copyright 2012-2014 MarkLogic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marklogic.samplestack.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marklogic.client.Transaction;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.samplestack.domain.ClientRole;

/**
 * Encapsulates interaction with MarkLogic.
 */
public interface MarkLogicOperations {

	/**
	 * Returns a JSON document, as a Jackson JSONNode from the database by URI
	 * @param role The client role to use in accessing the database.
	 * @param documentUri the URI of the document in MarkLogic server.
	 * @return A JsonNode containing the document.
	 */
	public JsonNode getJsonDocument(ClientRole role, String documentUri);
	
	/**
	 * Delete an entire directory/class of objects.
	 * @param role the caller's role
	 * @param type The directory to delete
	 */	
	public void deleteDirectory(ClientRole role, String directory);
	
	/**
	 * Gets a JSONDocumentManager with the caller's role for lower-level document
	 * access to MarkLogic.
	 * @param role the caller's role.
	 */
	public JSONDocumentManager newJSONDocumentManager(ClientRole role);

	/**
	 * Wraps a call to the MarkLogic suggest capability
	 * @param role Role to search with
	 * @param suggestPattern
	 * @return An array of Strings matching the suggest pattern.
	 */
	public String[] suggestTags(ClientRole role, String suggestPattern);

	
	/**
	 * Start a transaction
	 * @param role Role to search with
	 * @return A transaction to use in subsequent calls to MarkLogic 
	 */
	public Transaction start(ClientRole role);

	/**
	 * Get a string query definition from the underlying QueryManager
	 * @param optionsName options name passed to queryManager
	 * @return a new StringQueryDefinition
	 */
	public StringQueryDefinition newStringQueryDefinition(String optionsName);

	/**
	 * Wraps a call to REST API /v1/values to get back tag values and frequencies
	 * @param role Role to search with
	 * @param combinedQuery a JSON node containing the options definition for this query.
	 * @param start the first index to retrieve.
	 * @return A values response in a JSON structure.
	 */
	public ObjectNode tagValues(ClientRole role, JsonNode combinedQuery, long start);

}
