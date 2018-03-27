package duaa.traineeproject.API;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;

import duaa.traineeproject.Constants;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.LoginModel;
import duaa.traineeproject.Model.ResponseAddTrainee;
import duaa.traineeproject.Model.UniversityListModel;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class UserAPI {
    public void AddTrainee(final TrainerObject item, final UniversalCallBack callBack) {
        String url = Constants.addTrainee;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseAddTrainee responseObject = gson.fromJson(response.toString(), ResponseAddTrainee.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("trainer_name", item.getTrainer_name());
                params.put("email", item.getEmail());
                params.put("mobile", item.getMobile());
                params.put("specialization", item.getSpecialization());
                params.put("university", item.getUniversity());
                params.put("phone", item.getPhone());
                params.put("collage", item.getCollage()+"");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
//                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }



    public void getAllUniversity(final UniversalCallBack callBack) {
        String url = Constants.getUniversity;
        Log.d("Categories: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Categories: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    UniversityListModel responseObject = gson.fromJson(response.toString(), UniversityListModel.class);
                    callBack.onResponse(responseObject);
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


    public void Login(final String email, final String password, final UniversalCallBack callBack) {
        String url = Constants.login;
        Log.d("Login: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Login: ", response);
                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    LoginModel responseObject = gson.fromJson(response.toString(), LoginModel.class);
                    callBack.onResponse(responseObject);
                    Log.d("duaaa123","ddddd"+responseObject.getMessage());
                } catch (JsonSyntaxException e) {
                    callBack.OnError("Server Connection error try again later");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callBack.onFinish();
                String message = null;
                Log.d("onErrorResponse", error.toString() + "");
                String json = null;
                Log.d("error.getMessage()", error.getMessage() + "");
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    callBack.OnError(message);
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    callBack.OnError(message);
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    callBack.OnError(message);
                } else {
                    try {
                        Gson gson = new Gson();
                        ResponseError ErrorMsg = gson.fromJson(error.getMessage(), ResponseError.class);
                        callBack.onFailure(ErrorMsg);
                    } catch (JsonSyntaxException e) {
                        callBack.OnError("Server Connection error try again later");
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
//                String bearer = "Bearer ".concat(token);
                Map<String, String> headersSys = super.getHeaders();
                Map<String, String> headers = new HashMap<String, String>();
                headersSys.remove("Authorization");
                headers.put("Accept", "application/json");
//                headers.put("Authorization", bearer);
                headers.putAll(headersSys);
                return headers;
            }
        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }



}
