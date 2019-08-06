package com.ning.billing.recurly;

import static com.ning.billing.recurly.TestRecurlyClient.KILLBILL_PAYMENT_RECURLY_API_KEY;
import static com.ning.billing.recurly.TestRecurlyClient.KILLBILL_PAYMENT_RECURLY_SUBDOMAIN;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestKeyAgnosticClient {

    private String apiKey;
    private KeyAgnosticRecurlyClient recurlyClient;

    @BeforeMethod(groups = {"integration", "enterprise"})
    public void setUp() throws Exception {
        apiKey = System.getProperty(KILLBILL_PAYMENT_RECURLY_API_KEY);
        String subDomainTemp = System.getProperty(KILLBILL_PAYMENT_RECURLY_SUBDOMAIN);

        if (apiKey == null) {
            Assert.fail("You need to set your Recurly api key to run integration tests:" +
                        " -Dkillbill.payment.recurly.apiKey=...");
        }

        if (subDomainTemp == null) {
          subDomainTemp = "api";
        }

        final String subDomain = subDomainTemp;

        recurlyClient = new KeyAgnosticRecurlyClient(subDomain);
        recurlyClient.open();
    }

    @AfterMethod(groups = {"integration", "enterprise"})
    public void tearDown() throws Exception {
        recurlyClient.close();
    }

    @Test(groups = "integration")
    public void testUnauthorizedException() throws Exception {
        final String subdomain = System.getProperty(KILLBILL_PAYMENT_RECURLY_SUBDOMAIN);
        // Create a client with a valid api key
        RecurlyClient recurlyClient = new RecurlyClient(apiKey, subdomain);
        // Create a key agnostic client with a client with a valid api key
        KeyAgnosticRecurlyClient keyAgnosticClient = new KeyAgnosticRecurlyClient(recurlyClient);
        keyAgnosticClient.open();

        try {
            // use an invalid api key override
            keyAgnosticClient.getAccounts("invalid-api-key");
            Assert.fail("getAccounts call should not succeed with invalid credentials.");
        } catch (RecurlyAPIException expected) {
            Assert.assertEquals(expected.getRecurlyError().getSymbol(), "unauthorized");
        }
    }

}
