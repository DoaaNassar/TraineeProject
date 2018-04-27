package duaa.traineeproject.API;

import android.text.TextUtils;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import duaa.traineeproject.Constants;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.JavaObject.AntherTraining;
import duaa.traineeproject.JavaObject.EndTraining;
import duaa.traineeproject.JavaObject.PartObject;
import duaa.traineeproject.JavaObject.Place;
import duaa.traineeproject.JavaObject.Trainee;
import duaa.traineeproject.JavaObject.TrainerObject;
import duaa.traineeproject.Model.AddFacultyModel;
import duaa.traineeproject.Model.AddUniversityObject;
import duaa.traineeproject.Model.FacultyListModel;
import duaa.traineeproject.Model.LoginModel;
import duaa.traineeproject.Model.PartPlaceListModel;
import duaa.traineeproject.Model.PlaceListModel;
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.Model.SpecializationListModel;
import duaa.traineeproject.Model.TraineeAddModel;
import duaa.traineeproject.Model.TraineeListModel;
import duaa.traineeproject.Model.TrainerListModel;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.Model.UserDataResponse;

/**
 * Created by AL-Qema on 08/03/18.
 */

public class UserAPI {
    public void AddTrainer(final TrainerObject item, final UniversalCallBack callBack) {
        String url = Constants.ADD_TRAINER;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("collage", item.getCollage() + "");

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


    public void AddTrainee(final Trainee item, final UniversalCallBack callBack) {
        String url = Constants.ADD_TRAINEE;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    TraineeAddModel responseObject = gson.fromJson(response.toString(), TraineeAddModel.class);
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
                params.put("name", item.getName());
                params.put("email", item.getEmail());
                params.put("mobile", item.getMobileNumber());
                params.put("phone", item.getPhoneNumber());
                params.put("gender", item.getGender());
                params.put("id_num", item.getId_num());
                if (TextUtils.isEmpty(item.getUniversity())) {
                    params.put("university", 0 + "");
                    params.put("collage", 0 + "");
                    params.put("specialization", 0 + "");
                    params.put("university_number", 0 + "");
                } else {
                    params.put("university", item.getUniversity());
                    params.put("collage", item.getCollage());
                    params.put("specialization", item.getSpecialization());
                    params.put("university_number", item.getUniversity_number());
                }

                params.put("trainee_type", item.getTrainee_type());
                params.put("hour_number", item.getHour_number());
                params.put("trainee_place", item.getTrainee_place());
                params.put("place_partment", item.getPlace_partment());
//                params.put("ext", item.getName());
//                params.put("dir", item.getName());
//                params.put("source", item.getName());
                params.put("role_id", item.getRole_id());
                params.put("user_id", item.getUser_id());

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


    public void AddPlace(final Place item, final UniversalCallBack callBack) {
        String url = Constants.ADD_PLACE;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("place_name", item.getName());
                params.put("email",item.getEmail());
                params.put("full_address",item.getFull_address());
                params.put("mobile",item.getMobile());
                params.put("phone",item.getPhone());

//                int i = 0;
//                for (PartObject id : item.getPart()) {
//                    params.put("slider[]", new PartObject("image.jpg", 1));
//
//                }


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


    public void AddAntherTrainingTrainee(final AntherTraining item, final UniversalCallBack callBack) {
        String url = Constants.ADD_ANTHER_TRAINING_TRAINEE;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("trainee-id", item.getTrainee_id());
                params.put("type-training", item.getTrainee_type());
                params.put("id-university", item.getUniversity());
                params.put("id-faculty", item.getCollage());
                params.put("id-specialization", item.getSpecialization());
                params.put("hour-number", item.getHour_number());

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

    public void EndTrainingForTrainee(final EndTraining item, final UniversalCallBack callBack) {
        String url = Constants.END_TRAINING_TRAINEE;
        Log.d("endTraining: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("endTraining: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                int i = 0;
                for (int id : item.getList()) {

                    params.put("trainee-id[" + (i++) + "]", id + "");

                }

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


    public void DeletePlace(final String id_place, final UniversalCallBack callBack) {
        String url = Constants.DELETE_PLACE;
        Log.d("DeletePlace: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DeletePlace: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("place_id", id_place);


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

    public void DeleteTrainee(final String id_training, final UniversalCallBack callBack) {
        String url = Constants.DELETE_TRAINEE;
        Log.d("DeleteTrainee: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("DeleteTrainee: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("training_id", id_training);


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


    public void getAllFaculty(final String university_id, final UniversalCallBack callBack) {
        String url = Constants.GET_FACULTY;
        Log.d("faculty: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("faculty: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            FacultyListModel responseObject = gson.fromJson(response.toString(), FacultyListModel.class);
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
                params.put("university_id", university_id);


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


    public void getAllSpecialization(final String collage_id, final UniversalCallBack callBack) {
        String url = Constants.GET_SPECIALIZATION;
        Log.d("specii: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("specii: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            SpecializationListModel responseObject = gson.fromJson(response.toString(), SpecializationListModel.class);
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
                params.put("collage_id", collage_id);


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

    public void getPartPlace(final String placeID, final UniversalCallBack callBack) {
        String url = Constants.GET_PART_PLACE;
        Log.d("getPartPlace: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getPartPlace: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            PartPlaceListModel responseObject = gson.fromJson(response.toString(), PartPlaceListModel.class);
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
                params.put("place_id", placeID);

                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }

    public void getPlace(final UniversalCallBack callBack) {
        String url = Constants.GET_PLACE;
        Log.d("getPlace: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("getPlace: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            PlaceListModel responseObject = gson.fromJson(response.toString(), PlaceListModel.class);
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


    public void getAllNowTrainer(final String old , final UniversalCallBack callBack) {
        String url = Constants.GET_now_TRAINER;
        Log.d("trainer: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("trainer: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            TrainerListModel responseObject = gson.fromJson(response.toString(), TrainerListModel.class);
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
                params.put("old", old);


                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }

    public void getAllOldTrainer(final UniversalCallBack callBack) {
        String url = Constants.GET_OLD_TRAINER;
        Log.d("trainer: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("trainer: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            TrainerListModel responseObject = gson.fromJson(response.toString(), TrainerListModel.class);
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


    public void getAllNowTrainee(String userRole, final UniversalCallBack callBack) {
        String url = Constants.GET_NOW_TRAINEE;
        Log.d("trainee: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("trainee: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            TraineeListModel responseObject = gson.fromJson(response.toString(), TraineeListModel.class);
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

    public void getTrainee(final String user_id , final String roleId , final String approve , final String deleted , final UniversalCallBack callBack) {
        String url = Constants.GET_OLD_TRAINEE;
        Log.d("trainee: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("trainee: ", response);

                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            TraineeListModel responseObject = gson.fromJson(response.toString(), TraineeListModel.class);
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
                params.put("user_id", user_id);
                params.put("role_id", roleId);
                params.put("approve", approve);
                params.put("deleted", deleted);
                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }

    public void Login(final String email, final String password, final UniversalCallBack callBack) {
        String url = Constants.LOGIN;
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
                    Log.d("duaaa123", "ddddd" + responseObject.getMessage());
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

    public void AddUniversity(final AddUniversityObject item, final UniversalCallBack callBack) {
        String url = Constants.ADD_UNIVERSITY;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseTrue responseObject = gson.fromJson(response.toString(), ResponseTrue.class);
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

                params.put("university_name", item.getUniversity_name());
                params.put("email", item.getEmail());
                params.put("mobile", item.getMobile());
                params.put("phone", item.getPhone());
                params.put("full_address", item.getFull_address());

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

    public void AddFaculty(final AddFacultyModel item, final UniversalCallBack callBack) {
        String url = Constants.ADD_COLLAGE;
        Log.d("AddItem: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AddItem: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseTrue responseObject = gson.fromJson(response.toString(), ResponseTrue.class);
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

                params.put("collage_name", item.getName());

                int i = 0;
                for (String name : item.getspecializations()) {

                    params.put("specalization_name[" + (i++) + "]", name + "");

                }
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


    public void UpdateProfile(final UserDataResponse item, final UniversalCallBack callBack) {
        String url = Constants.UPDATE_PROFILE;
        Log.d("UpdateProfile: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("UpdateProfile: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
//                params.put("trainee-id", item.getId()+"");
//                params.put("type-training", item.getType()+"");
//                params.put("id-university", item.getIdUniversity()+"");
//                params.put("id-faculty", item.getIdFaculty()+"");
//                params.put("id-specialization", item.getIdspecialization()+"");
//                params.put("hour-number", item.getHourNumber());

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

    public void UpdatePassword(final String oldPassword, final String newPassword ,final  String confirm , final UniversalCallBack callBack) {
        String url = Constants.CHANGE_PASSWORD;
        Log.d("UpdatePassword: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("UpdatePassword: ", response);

                try {
                    callBack.onFinish();
                    Gson gson = new Gson();
                    ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                params.put("oldPassword", oldPassword);
                params.put("newPassword", newPassword);
                params.put("confirmPassword", confirm);


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


    public void UploadUserImage(final String token, final byte[] photo, final String check, final UniversalCallBack callBack) {
        String url = Constants.UPLOAD;
        Log.d("UploadImage: ", url);


        VolleyMultipartRequest multipartRequest =
                new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("UploadImage: ", response.toString());
                        try {
                            callBack.onFinish();
                            Gson gson = new Gson();
                            ResponseSuccess responseObject = gson.fromJson(response.toString(), ResponseSuccess.class);
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
                        Log.d("onErrorResponse", error.getMessage() + "");
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
                        if (photo != null) {
                            if (TextUtils.isEmpty(check)) {
                                params.put("key", "university_logo");
                            } else {
                                params.put("key", "book_images");
                                params.put("trainee_data_id", check);
                            }
                        }

                        return params;
                    }

                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        if (photo != null) {
                            params.put("image_upload", new DataPart("image.jpg", photo, "image/jpeg"));
                        }
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        String bearer = "Bearer ".concat(token);
                        Map<String, String> headersSys = super.getHeaders();
                        Map<String, String> headers = new HashMap<String, String>();
                        headersSys.remove("Authorization");
                        headers.put("Authorization", bearer);
                        headers.putAll(headersSys);
                        return headers;
                    }
                };

        VolleySingleton.getInstance().addToRequestQueue(multipartRequest, "");

    }
}
