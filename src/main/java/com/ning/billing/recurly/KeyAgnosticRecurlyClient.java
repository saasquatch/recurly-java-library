package com.ning.billing.recurly;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.ning.billing.recurly.RecurlyClient.RecurlyKeyOverrideCloseable;
import com.ning.billing.recurly.model.Account;
import com.ning.billing.recurly.model.AccountAcquisition;
import com.ning.billing.recurly.model.AccountBalance;
import com.ning.billing.recurly.model.AccountNotes;
import com.ning.billing.recurly.model.Accounts;
import com.ning.billing.recurly.model.AddOn;
import com.ning.billing.recurly.model.AddOns;
import com.ning.billing.recurly.model.Adjustment;
import com.ning.billing.recurly.model.AdjustmentRefund;
import com.ning.billing.recurly.model.Adjustments;
import com.ning.billing.recurly.model.BillingInfo;
import com.ning.billing.recurly.model.Coupon;
import com.ning.billing.recurly.model.Coupons;
import com.ning.billing.recurly.model.CreditPayments;
import com.ning.billing.recurly.model.GiftCard;
import com.ning.billing.recurly.model.GiftCards;
import com.ning.billing.recurly.model.Invoice;
import com.ning.billing.recurly.model.InvoiceCollection;
import com.ning.billing.recurly.model.InvoiceRefund;
import com.ning.billing.recurly.model.InvoiceState;
import com.ning.billing.recurly.model.Invoices;
import com.ning.billing.recurly.model.MeasuredUnit;
import com.ning.billing.recurly.model.MeasuredUnits;
import com.ning.billing.recurly.model.Plan;
import com.ning.billing.recurly.model.Plans;
import com.ning.billing.recurly.model.Purchase;
import com.ning.billing.recurly.model.Redemption;
import com.ning.billing.recurly.model.Redemptions;
import com.ning.billing.recurly.model.RefundMethod;
import com.ning.billing.recurly.model.RefundOption;
import com.ning.billing.recurly.model.ShippingAddress;
import com.ning.billing.recurly.model.ShippingAddresses;
import com.ning.billing.recurly.model.ShippingMethod;
import com.ning.billing.recurly.model.ShippingMethods;
import com.ning.billing.recurly.model.Subscription;
import com.ning.billing.recurly.model.SubscriptionNotes;
import com.ning.billing.recurly.model.SubscriptionState;
import com.ning.billing.recurly.model.SubscriptionUpdate;
import com.ning.billing.recurly.model.Subscriptions;
import com.ning.billing.recurly.model.Transaction;
import com.ning.billing.recurly.model.TransactionState;
import com.ning.billing.recurly.model.TransactionType;
import com.ning.billing.recurly.model.Transactions;
import com.ning.billing.recurly.model.Usage;
import com.ning.billing.recurly.model.Usages;

public class KeyAgnosticRecurlyClient {

    // Just a placeholder that will not be used
    private static final String FAKE_API_KEY = KeyAgnosticRecurlyClient.class.getName();

    private final RecurlyClient client;

    public KeyAgnosticRecurlyClient() {
        this.client = new RecurlyClient(FAKE_API_KEY);
    }

    public KeyAgnosticRecurlyClient(final String subDomain) {
        this.client = new RecurlyClient(FAKE_API_KEY, subDomain);
    }

    public KeyAgnosticRecurlyClient(final String host, final int port, final String version) {
        this.client = new RecurlyClient(FAKE_API_KEY, host, port, version);
    }

    public KeyAgnosticRecurlyClient(final String scheme, final String host, final int port, final String version) {
        this.client = new RecurlyClient(FAKE_API_KEY, scheme, host, port, version);
    }

    public RecurlyClient getRecurlyClient() {
		return client;
	}

    public Account createAccount(final Account account, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.createAccount(account);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Accounts getAccounts(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getAccounts();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Accounts getAccounts(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getAccounts(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Integer getAccountsCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountsCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Coupons getCoupons(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCoupons();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Coupons getCoupons(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCoupons(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Integer getCouponsCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponsCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Account getAccount(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccount(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Account updateAccount(final String accountCode, final Account account, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateAccount(accountCode, account);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AccountBalance getAccountBalance(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountBalance(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void closeAccount(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.closeAccount(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Account reopenAccount(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.reopenAccount(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Accounts getChildAccounts(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getChildAccounts(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getAccountAdjustments(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountAdjustments(accountCode, type);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, final Adjustments.AdjustmentState state, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountAdjustments(accountCode, type, state);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, final Adjustments.AdjustmentState state, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountAdjustments(accountCode, type, state, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustment getAdjustment(final String adjustmentUuid, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAdjustment(adjustmentUuid);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Adjustment createAccountAdjustment(final String accountCode, final Adjustment adjustment, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createAccountAdjustment(accountCode, adjustment);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteAccountAdjustment(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteAccountAdjustment(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteAdjustment(final String adjustmentUuid, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteAdjustment(adjustmentUuid);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription createSubscription(final Subscription subscription, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createSubscription(subscription);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription previewSubscription(final Subscription subscription, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.previewSubscription(subscription);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription getSubscription(final String uuid, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getSubscription(uuid);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Subscription cancelSubscription(final Subscription subscription, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.cancelSubscription(subscription);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription pauseSubscription(final String subscriptionUuid, final int remainingPauseCycles, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.pauseSubscription(subscriptionUuid, remainingPauseCycles);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription resumeSubscription(final String subscriptionUuid, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.resumeSubscription(subscriptionUuid);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription postponeSubscription(final Subscription subscription, final DateTime renewaldate, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.postponeSubscription(subscription, renewaldate);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void terminateSubscription(final Subscription subscription, final RefundOption refund, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.terminateSubscription(subscription, refund);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription reactivateSubscription(final Subscription subscription, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.reactivateSubscription(subscription);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Subscription updateSubscription(final String uuid, final SubscriptionUpdate subscriptionUpdate, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateSubscription(uuid, subscriptionUpdate);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription updateSubscriptionPreview(final String uuid, final SubscriptionUpdate subscriptionUpdate, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateSubscriptionPreview(uuid, subscriptionUpdate);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscription updateSubscriptionNotes(final String uuid, final SubscriptionNotes subscriptionNotes, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateSubscriptionNotes(uuid, subscriptionNotes);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Subscriptions getAccountSubscriptionsWithApiKey(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getAccountSubscriptions(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscriptions getSubscriptions(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getSubscriptions();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscriptions getSubscriptions(final SubscriptionState state, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getSubscriptions(state, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Integer getSubscriptionsCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getSubscriptionsCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Subscriptions getAccountSubscriptions(final String accountCode, final SubscriptionState state, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountSubscriptions(accountCode, state, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Usage postSubscriptionUsage(final String subscriptionCode, final String addOnCode, final Usage usage, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.postSubscriptionUsage(subscriptionCode, addOnCode, usage);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Usages getSubscriptionUsages(final String subscriptionCode, final String addOnCode, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getSubscriptionUsages(subscriptionCode, addOnCode, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
	public Subscriptions getAccountSubscriptionsWithApiKey(String accountCode, String status, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountSubscriptions(accountCode, status);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public BillingInfo createOrUpdateBillingInfo(final String accountCode, final BillingInfo billingInfo, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createOrUpdateBillingInfo(accountCode, billingInfo);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	@Deprecated
	public BillingInfo createOrUpdateBillingInfo(final BillingInfo billingInfo, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createOrUpdateBillingInfo(billingInfo);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public BillingInfo getBillingInfo(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getBillingInfo(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public void clearBillingInfo(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.clearBillingInfo(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
	}

    public AccountNotes getAccountNotes(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountNotes(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Transactions getAccountTransactions(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getAccountTransactions(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transactions getAccountTransactions(final String accountCode, final TransactionState state, final TransactionType type, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountTransactions(accountCode, state, type, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transactions getTransactions(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getTransactions();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transactions getTransactions(final TransactionState state, final TransactionType type, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getTransactions(state, type, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Integer getTransactionsCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
            return client.getTransactionsCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transaction getTransaction(final String transactionId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getTransaction(transactionId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Transaction createTransaction(final Transaction trans, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createTransaction(trans);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void refundTransaction(final String transactionId, @Nullable final BigDecimal amount, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.refundTransaction(transactionId, amount);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice getInvoice(final Integer invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice getInvoice(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Invoice updateInvoice(final String invoiceId, final Invoice invoice, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateInvoice(invoiceId, invoice);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	@Deprecated
    public InputStream getInvoicePdf(final Integer invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoicePdf(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public InputStream getInvoicePdf(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoicePdf(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoices getInvoices(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoices();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoices getInvoices(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoices(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public int getInvoicesCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoicesCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transactions getInvoiceTransactions(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getInvoiceTransactions(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Invoices getAccountInvoices(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountInvoices(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Invoices getOriginalInvoices(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getOriginalInvoices(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	@Deprecated
    public Invoice refundInvoice(final String invoiceId, final Integer amountInCents, final RefundMethod method, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.refundInvoice(invoiceId, amountInCents, method);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	@Deprecated
    public Invoice refundInvoice(final String invoiceId, List<AdjustmentRefund> lineItems, final RefundMethod method, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.refundInvoice(invoiceId, lineItems, method);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice refundInvoice(final String invoiceId, final InvoiceRefund refundOptions, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.refundInvoice(invoiceId, refundOptions);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingAddresses getAccountShippingAddresses(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountShippingAddresses(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingAddress getShippingAddress(final String accountCode, final long shippingAddressId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getShippingAddress(accountCode, shippingAddressId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingAddress createShippingAddress(final String accountCode, final ShippingAddress shippingAddress, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createShippingAddress(accountCode, shippingAddress);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingAddress updateShippingAddress(final String accountCode, final long shippingAddressId, ShippingAddress shippingAddress, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateShippingAddress(accountCode, shippingAddressId, shippingAddress);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteShippingAddress(final String accountCode, final long shippingAddressId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteShippingAddress(accountCode, shippingAddressId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoices getAccountInvoices(final String accountCode, final InvoiceState state, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountInvoices(accountCode, state, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public InvoiceCollection postAccountInvoice(final String accountCode, final Invoice invoice, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.postAccountInvoice(accountCode, invoice);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice markInvoiceSuccessful(final Integer invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.markInvoiceSuccessful(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice markInvoiceSuccessful(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.markInvoiceSuccessful(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public InvoiceCollection markInvoiceFailed(final Integer invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.markInvoiceFailed(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public InvoiceCollection markInvoiceFailed(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.markInvoiceFailed(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice forceCollectInvoice(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.forceCollectInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice voidInvoice(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.voidInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Transaction enterOfflinePayment(final Integer invoiceId, final Transaction payment, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.enterOfflinePayment(invoiceId, payment);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Transaction enterOfflinePayment(final String invoiceId, final Transaction payment, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.enterOfflinePayment(invoiceId, payment);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Plan createPlan(final Plan plan, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createPlan(plan);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Plan updatePlan(final Plan plan, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updatePlan(plan);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Plan getPlan(final String planCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getPlan(planCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Plans getPlans(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getPlans();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Plans getPlans(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getPlans(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Integer getPlansCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getPlansCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deletePlan(final String planCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deletePlan(planCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AddOn createPlanAddOn(final String planCode, final AddOn addOn, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createPlanAddOn(planCode, addOn);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AddOn getAddOn(final String planCode, final String addOnCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAddOn(planCode, addOnCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AddOns getAddOns(final String planCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAddOns(planCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AddOns getAddOns(final String planCode, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAddOns(planCode, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteAddOn(final String planCode, final String addOnCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteAddOn(planCode, addOnCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AddOn updateAddOn(final String planCode, final String addOnCode, final AddOn addOn, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateAddOn(planCode, addOnCode, addOn);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Coupon createCoupon(final Coupon coupon, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createCoupon(coupon);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Coupon getCoupon(final String couponCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCoupon(couponCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteCoupon(final String couponCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteCoupon(couponCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemption redeemCoupon(final String couponCode, final Redemption redemption, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.redeemCoupon(couponCode, redemption);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemption getCouponRedemptionByAccount(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionByAccount(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByAccount(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByAccount(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByAccount(final String accountCode, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByAccount(accountCode, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Redemption getCouponRedemptionByInvoice(final Integer invoiceNumber, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionByInvoice(invoiceNumber);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemption getCouponRedemptionByInvoice(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionByInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Redemptions getCouponRedemptionsByInvoice(final Integer invoiceNumber, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByInvoice(invoiceNumber);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByInvoice(final String invoiceId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByInvoice(invoiceId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    @Deprecated
    public Redemptions getCouponRedemptionsByInvoice(final Integer invoiceNumber, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByInvoice(invoiceNumber, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByInvoice(final String invoiceId, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsByInvoice(invoiceId, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsBySubscription(final String subscriptionUuid, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCouponRedemptionsBySubscription(subscriptionUuid, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public void deleteCouponRedemptionWithApiKey(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteCouponRedemption(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteCouponRedemption(final String accountCode, final String redemptionUuid, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteCouponRedemption(accountCode, redemptionUuid);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void generateUniqueCodes(final String couponCode, final Coupon coupon, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.generateUniqueCodes(couponCode, coupon);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Coupons getUniqueCouponCodes(final String couponCode, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getUniqueCouponCodes(couponCode, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public Subscription fetchSubscription(final String recurlyToken, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.fetchSubscription(recurlyToken);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public BillingInfo fetchBillingInfo(final String recurlyToken, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.fetchBillingInfo(recurlyToken);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Invoice fetchInvoice(final String recurlyToken, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.fetchInvoice(recurlyToken);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCards getGiftCards(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getGiftCards(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCards getGiftCards(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getGiftCards();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public Integer getGiftCardsCount(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getGiftCardsCount(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCard getGiftCard(final Long giftCardId, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getGiftCard(giftCardId);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCard redeemGiftCard(final String redemptionCode, final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.redeemGiftCard(redemptionCode, accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCard purchaseGiftCard(final GiftCard giftCard, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.purchaseGiftCard(giftCard);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public GiftCard previewGiftCard(final GiftCard giftCard, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.previewGiftCard(giftCard);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public MeasuredUnits getMeasuredUnits(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getMeasuredUnits();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public MeasuredUnit createMeasuredUnit(final MeasuredUnit measuredUnit, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createMeasuredUnit(measuredUnit);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public InvoiceCollection purchase(final Purchase purchase, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.purchase(purchase);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public InvoiceCollection previewPurchase(final Purchase purchase, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.previewPurchase(purchase);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public InvoiceCollection authorizePurchase(final Purchase purchase, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.authorizePurchase(purchase);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public InvoiceCollection pendingPurchase(final Purchase purchase, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.pendingPurchase(purchase);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AccountAcquisition createAccountAcquisition(final String accountCode, final AccountAcquisition acquisition, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.createAccountAcquisition(accountCode, acquisition);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public AccountAcquisition getAccountAcquisition(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getAccountAcquisition(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

	public AccountAcquisition updateAccountAcquisition(final String accountCode, final AccountAcquisition acquisition, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.updateAccountAcquisition(accountCode, acquisition);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public void deleteAccountAcquisition(final String accountCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	client.deleteAccountAcquisition(accountCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCreditPayments();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCreditPayments(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(final String accountCode, final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getCreditPayments(accountCode, params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingMethods getShippingMethods(String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getShippingMethods();
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingMethods getShippingMethods(final QueryParams params, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getShippingMethods(params);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

    public ShippingMethod getShippingMethod(final String shippingMethodCode, String apiKey) {
        final RecurlyKeyOverrideCloseable keyOverrideCloseable = client.overrideKey(apiKey);
        try {
        	return client.getShippingMethod(shippingMethodCode);
        } finally {
        	keyOverrideCloseable.close();
        }
    }

}
