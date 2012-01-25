package org.nvc.ui.common.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

public class StringResponseHandler implements ResponseHandler<String>
{
    public String handleResponse(HttpResponse response)
            throws ClientProtocolException, IOException
    {
        HttpEntity entity = response.getEntity();
        if (entity != null)
        {
            return EntityUtils.toString(entity);
        }
        else
        {
            return null;
        }
    }
}
