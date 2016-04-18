package com.temenos.interaction.example;

import static org.junit.Assert.*;

import org.junit.Test;

import com.temenos.useragent.generic.DefaultInteractionSession;
import com.temenos.useragent.generic.InteractionSession;
import com.temenos.useragent.generic.mediatype.AtomPayloadHandler;

public class UserAgentExample {

	@Test
	public void simpleGetWithCheckingStatusCode() {
		// create an interaction session
		InteractionSession session = DefaultInteractionSession.newSession();

		// execute a GET on resource 'enqCurrencyLists'
		session.basicAuth("INPUTT", "123456")
				.url()
				.baseuri("http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/")
				.path("enqCurrencyLists()").get();

		// verify the response
		assertEquals(200, session.result().code());
	}

	@Test
	public void simpleGetWithConfiguredMediaTypeAtomXml() {
		// create an interaction session
		InteractionSession session = DefaultInteractionSession.newSession();

		// execute a GET on resource 'enqCurrencyLists'
		session.registerHandler("application/atom+xml",
				AtomPayloadHandler.class)
				.header("Content-Type", "application/atom+xml")
				.header("Accept", "application/atom+xml")
				.basicAuth("INPUTT", "123456")
				.url()
				.baseuri("http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/")
				.path("enqCurrencyLists()").get();

		// verify the response
		assertEquals(200, session.result().code());
	}

	@Test
	public void simpleGetWithAccessingEntityContentById() {
		// create an interaction session
		InteractionSession session = DefaultInteractionSession.newSession();

		// execute a GET on resource 'enqCurrencyLists'
		session.basicAuth("INPUTT", "123456")
				.url()
				.baseuri("http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/")
				.path("enqCurrencyLists()").get();

		// verify the response
		assertEquals(200, session.result().code());
		assertTrue(session.entities().isCollection());
		assertEquals("Pound Sterling",
				session.entities().byId("GBP")
						.get("enqCurrencyList_CcyNameMvGroup(0)/CcyName"));
	}

	@Test
	public void postWithAnEmbeddedEntityInResponse() {
		// create an interaction session
		InteractionSession session = DefaultInteractionSession.newSession();

		// execute a GET on resource "verCurrencys"
		session.basicAuth("INPUTT", "123456")
				.url()
				.baseuri("http://localhost:9089/t24interactiontests-iris/t24interactiontests.svc/GB0010001/")
				.path("verCurrencys").get();

		// verify the response
		assertEquals(200, session.result().code());
		assertTrue(session.entities().isCollection());

		// execute a GET for resource "verCurrencys('USD')"
		session.entities().byId("USD").links().byRel("self").url().get();

		// verify the response
		assertEquals(200, session.result().code());
		assertTrue(session.entities().isItem());

		// execute a POST with an invalid property value
		session.reuse().header("Content-Type", "application/atom+xml")
				.header("If-Match", session.header("ETag")).set("Rank", "foo")
				.entities().item().links().byTitle("input.*").url().post();

		// verify the response
		assertEquals(400, session.result().code());
		assertEquals("INPUT NOT NUMERIC", session.entities().item().links()
				.byRel("http://temenostech.temenos.com/rels/errors").embedded()
				.entity().get("Errors_ErrorsMvGroup(0)/Code"));
	}
}
