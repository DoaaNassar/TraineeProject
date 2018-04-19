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
import duaa.traineeproject.Model.ResponseSuccess;
import duaa.traineeproject.Model.ResponseTrue;
import duaa.traineeproject.Model.TraineeListModel;
import duaa.traineeproject.Model.TrainerListModel;
import duaa.traineeproject.Model.UniversityListModel;

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
                params.put("name", item.getName());
                params.put("email", item.getEmail());
                params.put("mobile", item.getMobileNumber());
                params.put("phone", item.getPhoneNumber());
                params.put("gender", item.getGender());
                params.put("id_num", item.getId_num());
                params.put("university", item.getUniversity());
                params.put("collage", item.getCollage());
                params.put("specialization", item.getSpecialization());
                params.put("trainee_type", item.getTrainee_type());
                params.put("university_number", item.getUniversity_number());
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
                params.put("place-name", item.getName());
                int i = 0;
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
                params.put("trainee-id", item.getId()+"");
                params.put("type-training", item.getType()+"");
                params.put("id-university", item.getIdUniversity()+"");
                params.put("id-faculty", item.getIdFaculty()+"");
                params.put("id-specification", item.getIdSpecification()+"");
                params.put("hour-number", item.getHourNumber());

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
                int i = 0;
                for (int id : item.getList()) {

                    params.put("trainee-id[" + (i++) + "]", id+"");

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






    public void getAllFaculty(final String university_id , final UniversalCallBack callBack) {
        String url = Constants.GET_FACULTY;
        Log.d("faculty: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
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
                params.put("id-university", university_id);

                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


    public void getPartPlace(final String placeID , final UniversalCallBack callBack) {
        String url = Constants.GET_PART_PLACE;
        Log.d("faculty: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
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
                params.put("place_id", placeID);

                return params;
            }

        };

        VolleySingleton.getInstance().addToRequestQueue(stringRequest, "");

    }


    public void getAllNowTrainer(final UniversalCallBack callBack) {
        String url = Constants.GET_now_TRAINER;
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


    public void getAllNowTrainee(final UniversalCallBack callBack) {
        String url = Constants.GET_NOW_TRAINEE;
        Log.d("trainee: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
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

    public void getAllOldTrainee(final UniversalCallBack callBack) {
        String url = Constants.GET_OLD_TRAINEE;
        Log.d("trainee: ", url);
        VolleyStringRequest stringRequest = new VolleyStringRequest(Request.Method.GET, url,
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
                for (String name : item.getSpecifications()) {

                    params.put("specalization_name[" + (i++) + "]", name+"");

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




}
