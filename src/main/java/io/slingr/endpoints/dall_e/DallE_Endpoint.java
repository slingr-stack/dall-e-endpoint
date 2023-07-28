package io.slingr.endpoints.dall_e;

import io.slingr.endpoints.HttpEndpoint;
import io.slingr.endpoints.exceptions.EndpointException;
import io.slingr.endpoints.framework.annotations.ApplicationLogger;
import io.slingr.endpoints.framework.annotations.EndpointFunction;
import io.slingr.endpoints.framework.annotations.EndpointProperty;
import io.slingr.endpoints.framework.annotations.SlingrEndpoint;
import io.slingr.endpoints.services.AppLogs;
import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.ws.exchange.FunctionRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

/**
 * DALL-E endpoint
 * Created by pasaperez on 6/6/23.
 */
@SlingrEndpoint(name = "dalle", functionPrefix = "_")
public class DallE_Endpoint extends HttpEndpoint {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DallE_Endpoint.class);

    private static final String OPENAI_BASE_URL = "https://api.openai.com/";

    @ApplicationLogger
    private AppLogs appLogger;

    @EndpointProperty
    private String apiToken;

    @EndpointProperty
    private String organizationId;

    @Override
    public String getApiUri() { return OPENAI_BASE_URL; }

    @Override
    public void endpointStarted() {
        logger.error("Endpoint started");
        appLogger.error("Endpoint started");
        httpService().setAllowExternalUrl(true);
    }

    @EndpointFunction(name = "_get")
    public Json get(FunctionRequest request) {
        try {
            setRequestHeaders(request);
            return defaultGetRequest(request);
        } catch (EndpointException restException) {
            if (handleErrorCodes(restException)) {
                appLogger.info("Retrying request");
                return defaultGetRequest(request);
            } else {
                throw restException;
            }
        }
    }

    @EndpointFunction(name = "_post")
    public Json post(FunctionRequest request) {
        try {
            setRequestHeaders(request);
            return defaultPostRequest(request);
        } catch (EndpointException restException) {
            if (handleErrorCodes(restException)) {
                appLogger.info("Retrying request");
                return defaultPostRequest(request);
            } else {
                throw restException;
            }
        }
    }

    private void setRequestHeaders(FunctionRequest request) {
        Json body = request.getJsonParams();
        Json headers = body.json("headers");
        if (headers == null) {
            headers = Json.map();
        }
        if (StringUtils.isNotBlank(apiToken)) headers.set("Authorization", "Bearer " + apiToken);
        if (StringUtils.isNotBlank(organizationId)) headers.set("OpenAI-Organization", organizationId);
        body.set("headers", headers);
    }

    private boolean handleErrorCodes(EndpointException restException) {
        if (restException.getHttpStatusCode() == 401) {
            appLogger.error("401 - Invalid Authentication or 401 - Incorrect API key provided or 401 - You must be a member of an organization to use the API.");
            return false;
        }
        if (restException.getHttpStatusCode() == 429) {
            appLogger.error("429 - Rate limit reached for requests or 429 - You exceeded your current quota, please check your plan and billing details or 429 - The engine is currently overloaded, please try again later.");
            return true;
        }
        if (restException.getHttpStatusCode() == 500) {
            appLogger.error("500 - The server had an error while processing your request.");
            return true;
        }
        return false;
    }
}