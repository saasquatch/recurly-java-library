package com.ning.billing.recurly;

import static com.ning.billing.recurly.TestRecurlyClient.KILLBILL_PAYMENT_RECURLY_API_KEY;
import static com.ning.billing.recurly.TestRecurlyClient.KILLBILL_PAYMENT_RECURLY_SUBDOMAIN;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ning.billing.recurly.model.Coupon;
import com.ning.billing.recurly.model.Coupons;

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
        RecurlyClient keyRecurlyClient = new RecurlyClient(apiKey, subdomain);
        // Create a key agnostic client with a client with a valid api key
        KeyAgnosticRecurlyClient keyAgnosticClient = new KeyAgnosticRecurlyClient(keyRecurlyClient);
        keyAgnosticClient.open();

        // The following line should not fail
        keyRecurlyClient.getAccounts();

        try {
            // use an invalid api key override
            keyAgnosticClient.getAccounts("invalid-api-key");
            Assert.fail("getAccounts call should not succeed with invalid credentials.");
        } catch (RecurlyAPIException expected) {
            Assert.assertEquals(expected.getRecurlyError().getSymbol(), "unauthorized");
        }
    }

    @Test(groups = "integration")
    public void testCreateCouponWithApiKey() throws Exception {
        final Coupon couponData = TestUtils.createRandomCoupon();

        try {
            // Create the coupon
            Coupon coupon = recurlyClient.createCoupon(couponData, apiKey);
            Assert.assertNotNull(coupon);
            Assert.assertEquals(coupon.getName(), couponData.getName());
            Assert.assertEquals(coupon.getCouponCode(), couponData.getCouponCode());
            Assert.assertEquals(coupon.getDiscountType(), couponData.getDiscountType());
            Assert.assertEquals(coupon.getDiscountPercent(), couponData.getDiscountPercent());

            // Get the coupon
            coupon = recurlyClient.getCoupon(couponData.getCouponCode(), apiKey);
            Assert.assertNotNull(coupon);
            Assert.assertEquals(coupon.getName(), couponData.getName());
            Assert.assertEquals(coupon.getCouponCode(), couponData.getCouponCode());
            Assert.assertEquals(coupon.getDiscountType(), couponData.getDiscountType());
            Assert.assertEquals(coupon.getDiscountPercent(), couponData.getDiscountPercent());

            // Also test getting all coupons
            Coupons coupons = recurlyClient.getCoupons(apiKey);
            Assert.assertNotNull(coupons);

        } finally {
            recurlyClient.deleteCoupon(couponData.getCouponCode(), apiKey);
        }
    }

    @Test(groups = "integration")
    public void testCountsWithApiKey() throws Exception {
        final QueryParams qp = new QueryParams();
        qp.setBeginTime(new DateTime("2017-01-01T00:00:00Z"));

        Integer accountCount = recurlyClient.getAccountsCount(qp, apiKey);
        Assert.assertNotNull(accountCount);
        Integer couponsCount = recurlyClient.getCouponsCount(qp, apiKey);
        Assert.assertNotNull(couponsCount);
        Integer transactionsCount = recurlyClient.getTransactionsCount(qp, apiKey);
        Assert.assertNotNull(transactionsCount);
        Integer plansCount = recurlyClient.getPlansCount(qp, apiKey);
        Assert.assertNotNull(plansCount);
        Integer giftCardsCount = recurlyClient.getGiftCardsCount(qp, apiKey);
        Assert.assertNotNull(giftCardsCount);
    }

}
