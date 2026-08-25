package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.AccountCampaignPointAmounts;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetAccountCampaignPointAmounts} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class GetAccountCampaignPointAmounts extends BankRequest {
    @NonNull
    public String accountId;
    @NonNull
    public String campaignId;

    public GetAccountCampaignPointAmounts(@NonNull String accountId, @NonNull String campaignId) {
        this.accountId = accountId;
        this.campaignId = campaignId;
    }

    protected final String path() {
        return "/accounts/" + accountId + "/campaigns/" + campaignId +"/point-amounts";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final AccountCampaignPointAmounts send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(AccountCampaignPointAmounts.class, accessToken);
    }
}