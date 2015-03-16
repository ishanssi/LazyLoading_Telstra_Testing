package example.cognizant.com.lazyloading2;

/**
 * Created by ishanswarnajith on 13/03/15.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Jsonparserclass {

    public static String Maintitle;

    public List<HashMap<String,Object>> parse(JSONObject jObject){

        JSONArray jCountries = null;
        try {
            // Retrieves all the elements in the 'countries' array
            jCountries = jObject.getJSONArray("rows");

              Maintitle=jObject.getString("title");

            Log.d("ISHAN_JSON",Maintitle);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Invoking getCountries with the array of json object
        // where each json object represent a country
        return getCountries(jCountries);
    }

    private List<HashMap<String, Object>> getCountries(JSONArray RowItems){
        int rowsCount = RowItems.length();
        List<HashMap<String, Object>> countryList = new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> country = null;

        // Taking each country, parses and adds to list object
        for(int i=0; i<rowsCount;i++){
            try {
                // Call getCountry with country JSON object to parse the country
                country = getCountry((JSONObject)RowItems.get(i));
                countryList.add(country);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return countryList;
    }

    // Parsing the Country JSON object
    private HashMap<String, Object> getCountry(JSONObject jCountry){

        HashMap<String, Object> country = new HashMap<String, Object>();
        String title = "";
        String flag="";
        String description = "";


        try {
          title = jCountry.getString("title");
          flag = jCountry.getString("imageHref");
         if (flag.contains("flag_of_canada"))
            {
                flag="http://boredfix.com/wp-content/themes/DeepBlue/DeepBlue/images/blank.png";
            }
//            if (flag.contains("8a327a9Larger"))
//            {
//                flag="http://adaderana.lk/news_images/1122785941malinga.jpg";
//            }

            //flag="http://adaderana.lk/news_images/1122785941malinga.jpg";

            if (title=="null")
            {
                title="Title not available";
            }
            description = jCountry.getString("description");
            if (description=="null")
            {
                description="Description not available";
            }


            country.put("title", title);
            country.put("flag", R.drawable.no_preview);
            country.put("flag_path", flag);
            country.put("description", description);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return country;
    }
}


