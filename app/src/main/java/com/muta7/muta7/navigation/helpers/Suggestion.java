package com.muta7.muta7.navigation.helpers;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.muta7.muta7.database.models.Space;

import java.util.ArrayList;

/**
 * Created by Kareem Waleed on 3/21/2017.
 */

public class Suggestion implements SearchSuggestion {

    Space body;

    public Suggestion(Space body){
        this.body = body;
    }
    @Override
    public String getBody() {
        return body.getGeneralInfo().getSpaceName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static ArrayList<Suggestion> parseStringArrayList(ArrayList<Space> dataSet){
        if(dataSet == null)
            return null;
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        for(int i=0; i < dataSet.size(); i++){
            suggestions.add(new Suggestion(dataSet.get(i)));
        }
        return suggestions;
    }

    public Space getData(){
        return body;
    }
}
