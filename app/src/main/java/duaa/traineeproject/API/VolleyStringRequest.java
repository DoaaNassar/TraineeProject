package duaa.traineeproject.API;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import duaa.traineeproject.Interface.UniversalCallBack;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class VolleyStringRequest extends Request<String> {

    Response.Listener<String> successListener;

    public VolleyStringRequest(int method, String url, Response.Listener<String> successListener,
                               Response.ErrorListener listener) {
        super(method, url, listener);
        this.successListener = successListener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);

        }

        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        try {
            String s = URLEncoder.encode(response, "ISO-8859-1");
            response = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        successListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
            try {
                volleyError = new VolleyError(new String(volleyError.networkResponse.data ,"utf-8"));
                if (volleyError.getMessage()!= null&& volleyError.getMessage().contains("Unauthenticated")) {

//                    new  UserAPI().refresh_token(ApplicationController.getInstance().getUser().getRefresh_token(),
//                            new UniversalCallBack() {
//                        @Override
//                        public void onResponse(Object UniversityListModel) {
//                            ResponseToken responseToken = (ResponseToken) UniversityListModel;
//                            ApplicationController.getInstance().RefreshToken(responseToken);
//
//                        }
//
//                        @Override
//                        public void onFailure(Object UniversityListModel) {
//                            ApplicationController.getInstance().Logout();
//                        }
//
//                        @Override
//                        public void onFinish() {
//
//                        }
//
//                        @Override
//                        public void OnError(String message) {
//                            ApplicationController.getInstance().Logout();
//
//                        }
//                    }

//                    );
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return volleyError;
    }

}
