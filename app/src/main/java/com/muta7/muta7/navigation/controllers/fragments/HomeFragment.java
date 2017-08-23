package com.muta7.muta7.navigation.controllers.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.muta7.muta7.R;
import com.muta7.muta7.create_space.controllers.activities.CreateSpaceActivity;
import com.muta7.muta7.database.models.GeneralInfo;
import com.muta7.muta7.database.models.Location;
import com.muta7.muta7.database.models.Space;
import com.muta7.muta7.navigation.helpers.SearchTrieTree;
import com.muta7.muta7.navigation.helpers.Suggestion;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private FloatingSearchView searchBar;
    private Button findSpaceButton;
    private Button createSpaceButton;
    private ConstraintLayout homeFragmentMainLayout;
    private SearchTrieTree searchTree;
    private String lastQuery;
    private SearchBarFocus activitySearchBarFocusListener;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = (FloatingSearchView) view.findViewById(R.id.fsv_search_bar);
        findSpaceButton = (Button) view.findViewById(R.id.b_find_space);
        createSpaceButton = (Button) view.findViewById(R.id.b_create_space);
        homeFragmentMainLayout = (ConstraintLayout) view.findViewById(R.id.home_fragment_main_layout);
    }

    @Override
    public void onResume() {
        super.onResume();
        initiateSearchTree();
        lastQuery = "";
        homeFragmentMainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                searchBar.setSearchFocused(false);
                return false;
            }
        });

        searchBar.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                searchBar.showProgress();
                lastQuery = newQuery;
                ArrayList<Suggestion> suggestions = Suggestion.parseStringArrayList(searchTree.search(newQuery));
                if (suggestions == null)
                    suggestions = new ArrayList<>();
                searchBar.swapSuggestions(suggestions);
                searchBar.hideProgress();
            }
        });

        searchBar.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                //TODO: Implement suggestion clicks handler
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });

        searchBar.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.search_bar_action_search:
                        //TODO: Impelement search button clicks handler
                        break;
                }
            }
        });

        searchBar.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                activitySearchBarFocusListener.searchBarFocusListener(true);
                searchBar.showProgress();
                ArrayList<Suggestion> suggestions = Suggestion.parseStringArrayList(searchTree.search(lastQuery));
                if (suggestions == null)
                    suggestions = new ArrayList<>();
                searchBar.swapSuggestions(suggestions);
                searchBar.hideProgress();
            }

            @Override
            public void onFocusCleared() {
                activitySearchBarFocusListener.searchBarFocusListener(false);
            }
        });

        createSpaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateSpaceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initiateSearchTree() {

        //TODO: initiate with real data
        ArrayList<Space> tempSpaces = new ArrayList<>();
        Space space = new Space(new GeneralInfo("Creativo", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Makan", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Medan", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Y Circles", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Makanana", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Mauzaa", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Mkan", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Mokan", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);
        space = new Space(new GeneralInfo("Mukan", "dshgjhga", "01454545", "jadhfkjahsf@gmail.com", "skjhfkjsf.com"
                , "facebook", "twiter", "instagram", "youtube"), new Location("Cairo", "Egypt", 21.22, 33.55, "", null));

        tempSpaces.add(space);

        searchTree = new SearchTrieTree(tempSpaces);
    }

    public interface SearchBarFocus{
        void searchBarFocusListener(boolean isFocused);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activitySearchBarFocusListener = (SearchBarFocus) context;
    }

    public void clearSearchBarFocus(){
        searchBar.clearFocus();
    }
}
