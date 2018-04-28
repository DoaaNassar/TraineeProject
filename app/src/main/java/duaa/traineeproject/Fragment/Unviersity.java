package duaa.traineeproject.Fragment;

import android.app.NativeActivity;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.engine.Resource;
import com.tapadoo.alerter.Alerter;

import java.util.ArrayList;

import duaa.traineeproject.API.ResponseError;
import duaa.traineeproject.API.UserAPI;
import duaa.traineeproject.Activity.MainActivity;
import duaa.traineeproject.Adapter.ShowUniversityAdapter;
import duaa.traineeproject.Adapter.UniversityAdapter;
import duaa.traineeproject.Interface.CustomItemClickListener;
import duaa.traineeproject.Interface.UniversalCallBack;
import duaa.traineeproject.Model.University;
import duaa.traineeproject.Model.UniversityListModel;
import duaa.traineeproject.R;
import duaa.traineeproject.view.FontTextViewRegular;

import static duaa.traineeproject.Constants.FONTS_APP;


public class Unviersity extends Fragment {
    RecyclerView universityRecyclerView;
    View view;
    UniversityAdapter universityAdapter;
    ArrayList<University> universityList;
    UniversityAdapter showUniversityAdapter;
    LinearLayout loading, noInternet;
    FontTextViewRegular title, universityTab;
    ImageView search;


    Typeface face;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        universityList = new ArrayList<>();
        face = Typeface.createFromAsset(getActivity().getAssets(), FONTS_APP);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_unviersity, container, false);
        bindView();
        title.setText(getResources().getString(R.string.universityPart));
        university();
        setHasOptionsMenu(true);
        search.setVisibility(View.VISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    public void bindView() {
        universityRecyclerView = view.findViewById(R.id.listUniversityShow);
        loading = view.findViewById(R.id.loading);
        title = getActivity().findViewById(R.id.title);
        universityTab = getActivity().findViewById(R.id.university);
        search = getActivity().findViewById(R.id.search);


    }

    public void university() {
        universityTab.setEnabled(false);
        new UserAPI().getAllUniversity(new UniversalCallBack() {
            @Override
            public void onResponse(Object result) {

                UniversityListModel responseCategories = (UniversityListModel) result;

                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);


//                if (responseCategories.isStatus()) {
                universityList.clear();
                universityList.addAll(responseCategories.getResult());
                universityRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                showUniversityAdapter = new UniversityAdapter(getActivity(), universityList,
                        new CustomItemClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                            }
                        }, new UniversityAdapter.MyRecyclerViewListener() {
                    @Override
                    public void RemoveImage(View v, int position) {

                    }
                });

                universityRecyclerView.setAdapter(showUniversityAdapter);
                showUniversityAdapter.notifyDataSetChanged();


            }
//            }

            @Override
            public void onFailure(Object result) {
                if (result != null) {
                    ResponseError responseError = (ResponseError) result;
                    loading.setVisibility(View.GONE);
                    universityTab.setEnabled(true);

                    if (getActivity() != null)
                        Alarm(getResources().getString(R.string.noAdd));
                }
            }

            @Override
            public void onFinish() {
                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);


            }

            @Override
            public void OnError(String message) {
                loading.setVisibility(View.GONE);
                universityTab.setEnabled(true);
                if (getActivity() != null)
                    Alarm(getResources().getString(R.string.noInternet));
            }
        });


    }

    public void Alarm(String message) {
        Alerter.create(getActivity())
                .setText(message)
                .hideIcon()
                .setContentGravity(GravityCompat.END)
                .setTextTypeface(face)
                .setBackgroundColorRes(R.color.cardview_dark_background)
                .show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.search_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//        final MenuItem item = menu.findItem(R.id.action_search);
////        searchView.setMenuItem(item);
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = new SearchView(((NativeActivity) getActivity()).getSupportActionBar().getThemedContext());
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        searchView.setOnClickListener(new View.OnClickListener() {
//                                          @Override
//                                          public void onClick(View v) {
//
//                                          }
//                                      }
//        );
//    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.search_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
//        MenuItemCompat.setActionView(item, searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //Do search code here
//                return true;
//            }
//        });
//
//    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:

                //       onCall();   //your logic

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
