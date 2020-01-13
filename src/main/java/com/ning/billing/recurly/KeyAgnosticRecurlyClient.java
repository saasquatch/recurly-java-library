/*
 * Copyright 2019 ReferralSaaSquatch.com Inc.
 *
 * The Billing Project licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.recurly;

import java.io.InputStream;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Preconditions;
import com.ning.billing.recurly.RecurlyClient.ApiKeyOverrideCloseable;
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
import com.ning.billing.recurly.model.Item;
import com.ning.billing.recurly.model.Items;
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

/**
 * A wrapper for {@link RecurlyClient} where you can pass in an API key in every method.
 */
public class KeyAgnosticRecurlyClient {

    // Just a placeholder that will not be used
    private static final String FAKE_API_KEY = KeyAgnosticRecurlyClient.class.getName();

    private final RecurlyClient recurlyClient;

    public KeyAgnosticRecurlyClient(@Nonnull RecurlyClient recurlyClient) {
        this.recurlyClient = Preconditions.checkNotNull(recurlyClient);
    }

    public KeyAgnosticRecurlyClient() {
        this.recurlyClient = new RecurlyClient(FAKE_API_KEY);
    }

    public KeyAgnosticRecurlyClient(final String subDomain) {
        this.recurlyClient = new RecurlyClient(FAKE_API_KEY, subDomain);
    }

    public KeyAgnosticRecurlyClient(final String host, final int port, final String version) {
        this.recurlyClient = new RecurlyClient(FAKE_API_KEY, host, port, version);
    }

    public KeyAgnosticRecurlyClient(final String scheme, final String host, final int port, final String version) {
        this.recurlyClient = new RecurlyClient(FAKE_API_KEY, scheme, host, port, version);
    }

    public RecurlyClient getRecurlyClient() {
        return recurlyClient;
    }

    public void open() throws KeyManagementException, NoSuchAlgorithmException {
        recurlyClient.open();
    }

    public void close() {
        recurlyClient.close();
    }

    public Account createAccount(final Account account, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createAccount(account);
        } finally {
            overrideCloseable.close();
        }
    }

    public Accounts getAccounts(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccounts();
        } finally {
            overrideCloseable.close();
        }
    }

    public Accounts getAccounts(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccounts(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getAccountsCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountsCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupons getCoupons(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCoupons();
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupons getCoupons(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCoupons(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getCouponsCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponsCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Account getAccount(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccount(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Account updateAccount(final String accountCode, final Account account, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateAccount(accountCode, account);
        } finally {
            overrideCloseable.close();
        }
    }

    public AccountBalance getAccountBalance(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountBalance(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public void closeAccount(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.closeAccount(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Account reopenAccount(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.reopenAccount(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Accounts getChildAccounts(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getChildAccounts(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountAdjustments(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountAdjustments(accountCode, type);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, final Adjustments.AdjustmentState state, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountAdjustments(accountCode, type, state);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustments getAccountAdjustments(final String accountCode, final Adjustments.AdjustmentType type, final Adjustments.AdjustmentState state, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountAdjustments(accountCode, type, state, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustment getAdjustment(final String adjustmentUuid, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAdjustment(adjustmentUuid);
        } finally {
            overrideCloseable.close();
        }
    }

    public Adjustment createAccountAdjustment(final String accountCode, final Adjustment adjustment, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createAccountAdjustment(accountCode, adjustment);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteAccountAdjustment(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteAccountAdjustment(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteAdjustment(final String adjustmentUuid, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteAdjustment(adjustmentUuid);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription createSubscription(final Subscription subscription, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createSubscription(subscription);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription previewSubscription(final Subscription subscription, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.previewSubscription(subscription);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription getSubscription(final String uuid, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getSubscription(uuid);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription cancelSubscription(final Subscription subscription, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.cancelSubscription(subscription);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription cancelSubscription(final String subscriptionUuid, final SubscriptionUpdate.Timeframe timeframe, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.cancelSubscription(subscriptionUuid, timeframe);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription pauseSubscription(final String subscriptionUuid, final int remainingPauseCycles, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.pauseSubscription(subscriptionUuid, remainingPauseCycles);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription resumeSubscription(final String subscriptionUuid, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.resumeSubscription(subscriptionUuid);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription postponeSubscription(final Subscription subscription, final DateTime renewaldate, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.postponeSubscription(subscription, renewaldate);
        } finally {
            overrideCloseable.close();
        }
    }

    public void terminateSubscription(final Subscription subscription, final RefundOption refund, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.terminateSubscription(subscription, refund);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription reactivateSubscription(final Subscription subscription, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.reactivateSubscription(subscription);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription updateSubscription(final String uuid, final SubscriptionUpdate subscriptionUpdate, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateSubscription(uuid, subscriptionUpdate);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription updateSubscriptionPreview(final String uuid, final SubscriptionUpdate subscriptionUpdate, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateSubscriptionPreview(uuid, subscriptionUpdate);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription updateSubscriptionNotes(final String uuid, final SubscriptionNotes subscriptionNotes, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateSubscriptionNotes(uuid, subscriptionNotes);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getAccountSubscriptions(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountSubscriptions(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getSubscriptions(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getSubscriptions();
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getSubscriptions(final SubscriptionState state, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getSubscriptions(state, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getSubscriptionsCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getSubscriptionsCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getAccountSubscriptions(final String accountCode, final SubscriptionState state, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountSubscriptions(accountCode, state, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getInvoiceSubscriptions(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoiceSubscriptions(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getInvoiceSubscriptions(final String invoiceId, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoiceSubscriptions(invoiceId, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Usage postSubscriptionUsage(final String subscriptionCode, final String addOnCode, final Usage usage, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.postSubscriptionUsage(subscriptionCode, addOnCode, usage);
        } finally {
            overrideCloseable.close();
        }
    }

    public Usages getSubscriptionUsages(final String subscriptionCode, final String addOnCode, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getSubscriptionUsages(subscriptionCode, addOnCode, params);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Subscriptions getAccountSubscriptions(String accountCode, String status, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountSubscriptions(accountCode, status);
        } finally {
            overrideCloseable.close();
        }
    }

    public BillingInfo createOrUpdateBillingInfo(final String accountCode, final BillingInfo billingInfo, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createOrUpdateBillingInfo(accountCode, billingInfo);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public BillingInfo createOrUpdateBillingInfo(final BillingInfo billingInfo, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createOrUpdateBillingInfo(billingInfo);
        } finally {
            overrideCloseable.close();
        }
    }

    public BillingInfo getBillingInfo(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getBillingInfo(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public void clearBillingInfo(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.clearBillingInfo(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AccountNotes getAccountNotes(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountNotes(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transactions getAccountTransactions(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountTransactions(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transactions getAccountTransactions(final String accountCode, final TransactionState state, final TransactionType type, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountTransactions(accountCode, state, type, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transactions getTransactions(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getTransactions();
        } finally {
            overrideCloseable.close();
        }
    }

    public Transactions getTransactions(final TransactionState state, final TransactionType type, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getTransactions(state, type, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getTransactionsCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getTransactionsCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transaction getTransaction(final String transactionId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getTransaction(transactionId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transaction createTransaction(final Transaction trans, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createTransaction(trans);
        } finally {
            overrideCloseable.close();
        }
    }

    public void refundTransaction(final String transactionId, @Nullable final BigDecimal amount, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.refundTransaction(transactionId, amount);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscriptions getTransactionSubscriptions(final String transactionId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getTransactionSubscriptions(transactionId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice getInvoice(final Integer invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice getInvoice(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice updateInvoice(final String invoiceId, final Invoice invoice, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateInvoice(invoiceId, invoice);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public InputStream getInvoicePdf(final Integer invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoicePdf(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public InputStream getInvoicePdf(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoicePdf(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoices getInvoices(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoices();
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoices getInvoices(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoices(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public int getInvoicesCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoicesCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transactions getInvoiceTransactions(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getInvoiceTransactions(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoices getAccountInvoices(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountInvoices(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoices getOriginalInvoices(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getOriginalInvoices(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice refundInvoice(final String invoiceId, final Integer amountInCents, final RefundMethod method, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.refundInvoice(invoiceId, amountInCents, method);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice refundInvoice(final String invoiceId, List<AdjustmentRefund> lineItems, final RefundMethod method, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.refundInvoice(invoiceId, lineItems, method);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice refundInvoice(final String invoiceId, final InvoiceRefund refundOptions, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.refundInvoice(invoiceId, refundOptions);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingAddresses getAccountShippingAddresses(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountShippingAddresses(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingAddress getShippingAddress(final String accountCode, final long shippingAddressId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getShippingAddress(accountCode, shippingAddressId);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingAddress createShippingAddress(final String accountCode, final ShippingAddress shippingAddress, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createShippingAddress(accountCode, shippingAddress);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingAddress updateShippingAddress(final String accountCode, final long shippingAddressId, ShippingAddress shippingAddress, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateShippingAddress(accountCode, shippingAddressId, shippingAddress);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteShippingAddress(final String accountCode, final long shippingAddressId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteShippingAddress(accountCode, shippingAddressId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoices getAccountInvoices(final String accountCode, final InvoiceState state, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountInvoices(accountCode, state, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection postAccountInvoice(final String accountCode, final Invoice invoice, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.postAccountInvoice(accountCode, invoice);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Invoice markInvoiceSuccessful(final Integer invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.markInvoiceSuccessful(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice markInvoiceSuccessful(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.markInvoiceSuccessful(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public InvoiceCollection markInvoiceFailed(final Integer invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.markInvoiceFailed(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection markInvoiceFailed(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.markInvoiceFailed(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice forceCollectInvoice(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.forceCollectInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice forceCollectInvoice(final String invoiceId, final String transactionType, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.forceCollectInvoice(invoiceId, transactionType);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice voidInvoice(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.voidInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Transaction enterOfflinePayment(final Integer invoiceId, final Transaction payment, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.enterOfflinePayment(invoiceId, payment);
        } finally {
            overrideCloseable.close();
        }
    }

    public Transaction enterOfflinePayment(final String invoiceId, final Transaction payment, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.enterOfflinePayment(invoiceId, payment);
        } finally {
            overrideCloseable.close();
        }
    }

    public Item createItem(final Item item, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createItem(item);
        } finally {
            overrideCloseable.close();
        }
    }

    public Item updateItem(final String itemCode, final Item item, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateItem(itemCode, item);
        } finally {
            overrideCloseable.close();
        }
    }

    public Item getItem(final String itemCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getItem(itemCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Items getItems(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getItems();
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteItem(final String itemCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteItem(itemCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Item reactivateItem(final String itemCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.reactivateItem(itemCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Plan createPlan(final Plan plan, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createPlan(plan);
        } finally {
            overrideCloseable.close();
        }
    }

    public Plan updatePlan(final Plan plan, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updatePlan(plan);
        } finally {
            overrideCloseable.close();
        }
    }

    public Plan getPlan(final String planCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getPlan(planCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Plans getPlans(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getPlans();
        } finally {
            overrideCloseable.close();
        }
    }

    public Plans getPlans(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getPlans(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getPlansCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getPlansCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deletePlan(final String planCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deletePlan(planCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AddOn createPlanAddOn(final String planCode, final AddOn addOn, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createPlanAddOn(planCode, addOn);
        } finally {
            overrideCloseable.close();
        }
    }

    public AddOn getAddOn(final String planCode, final String addOnCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAddOn(planCode, addOnCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AddOns getAddOns(final String planCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAddOns(planCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AddOns getAddOns(final String planCode, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAddOns(planCode, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteAddOn(final String planCode, final String addOnCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteAddOn(planCode, addOnCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AddOn updateAddOn(final String planCode, final String addOnCode, final AddOn addOn, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateAddOn(planCode, addOnCode, addOn);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupon createCoupon(final Coupon coupon, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createCoupon(coupon);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupon getCoupon(final String couponCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCoupon(couponCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteCoupon(final String couponCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteCoupon(couponCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupon restoreCoupon(final String couponCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.restoreCoupon(couponCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemption redeemCoupon(final String couponCode, final Redemption redemption, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.redeemCoupon(couponCode, redemption);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemption getCouponRedemptionByAccount(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionByAccount(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByAccount(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByAccount(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByAccount(final String accountCode, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByAccount(accountCode, params);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Redemption getCouponRedemptionByInvoice(final Integer invoiceNumber, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionByInvoice(invoiceNumber);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemption getCouponRedemptionByInvoice(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionByInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Redemptions getCouponRedemptionsByInvoice(final Integer invoiceNumber, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByInvoice(invoiceNumber);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByInvoice(final String invoiceId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByInvoice(invoiceId);
        } finally {
            overrideCloseable.close();
        }
    }

    @Deprecated
    public Redemptions getCouponRedemptionsByInvoice(final Integer invoiceNumber, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByInvoice(invoiceNumber, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsByInvoice(final String invoiceId, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsByInvoice(invoiceId, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Redemptions getCouponRedemptionsBySubscription(final String subscriptionUuid, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCouponRedemptionsBySubscription(subscriptionUuid, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteCouponRedemption(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteCouponRedemption(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteCouponRedemption(final String accountCode, final String redemptionUuid, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteCouponRedemption(accountCode, redemptionUuid);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupons generateUniqueCodes(final String couponCode, final Coupon coupon, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.generateUniqueCodes(couponCode, coupon);
        } finally {
            overrideCloseable.close();
        }
    }

    public Coupons getUniqueCouponCodes(final String couponCode, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getUniqueCouponCodes(couponCode, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public Subscription fetchSubscription(final String recurlyToken, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.fetchSubscription(recurlyToken);
        } finally {
            overrideCloseable.close();
        }
    }

    public BillingInfo fetchBillingInfo(final String recurlyToken, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.fetchBillingInfo(recurlyToken);
        } finally {
            overrideCloseable.close();
        }
    }

    public Invoice fetchInvoice(final String recurlyToken, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.fetchInvoice(recurlyToken);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCards getGiftCards(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getGiftCards(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCards getGiftCards(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getGiftCards();
        } finally {
            overrideCloseable.close();
        }
    }

    public Integer getGiftCardsCount(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getGiftCardsCount(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCard getGiftCard(final Long giftCardId, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getGiftCard(giftCardId);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCard redeemGiftCard(final String redemptionCode, final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.redeemGiftCard(redemptionCode, accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCard purchaseGiftCard(final GiftCard giftCard, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.purchaseGiftCard(giftCard);
        } finally {
            overrideCloseable.close();
        }
    }

    public GiftCard previewGiftCard(final GiftCard giftCard, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.previewGiftCard(giftCard);
        } finally {
            overrideCloseable.close();
        }
    }

    public MeasuredUnits getMeasuredUnits(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getMeasuredUnits();
        } finally {
            overrideCloseable.close();
        }
    }

    public MeasuredUnit createMeasuredUnit(final MeasuredUnit measuredUnit, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createMeasuredUnit(measuredUnit);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection purchase(final Purchase purchase, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.purchase(purchase);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection previewPurchase(final Purchase purchase, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.previewPurchase(purchase);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection authorizePurchase(final Purchase purchase, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.authorizePurchase(purchase);
        } finally {
            overrideCloseable.close();
        }
    }

    public InvoiceCollection pendingPurchase(final Purchase purchase, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.pendingPurchase(purchase);
        } finally {
            overrideCloseable.close();
        }
    }

    public AccountAcquisition createAccountAcquisition(final String accountCode, final AccountAcquisition acquisition, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.createAccountAcquisition(accountCode, acquisition);
        } finally {
            overrideCloseable.close();
        }
    }

    public AccountAcquisition getAccountAcquisition(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getAccountAcquisition(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public AccountAcquisition updateAccountAcquisition(final String accountCode, final AccountAcquisition acquisition, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.updateAccountAcquisition(accountCode, acquisition);
        } finally {
            overrideCloseable.close();
        }
    }

    public void deleteAccountAcquisition(final String accountCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            recurlyClient.deleteAccountAcquisition(accountCode);
        } finally {
            overrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCreditPayments();
        } finally {
            overrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCreditPayments(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public CreditPayments getCreditPayments(final String accountCode, final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getCreditPayments(accountCode, params);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingMethods getShippingMethods(final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getShippingMethods();
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingMethods getShippingMethods(final QueryParams params, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getShippingMethods(params);
        } finally {
            overrideCloseable.close();
        }
    }

    public ShippingMethod getShippingMethod(final String shippingMethodCode, final String apiKey) {
        final ApiKeyOverrideCloseable overrideCloseable = recurlyClient.overrideApiKey(apiKey);
        try {
            return recurlyClient.getShippingMethod(shippingMethodCode);
        } finally {
            overrideCloseable.close();
        }
    }

}
