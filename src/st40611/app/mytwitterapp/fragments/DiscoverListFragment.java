package st40611.app.mytwitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import st40611.app.mytwitterapp.MyTwitterApp;
import st40611.app.mytwitterapp.ProfileActivity;
import st40611.app.mytwitterapp.R;
import st40611.app.mytwitterapp.TweetsAdapter;
import st40611.app.restclienttemplate.models.Tweet;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.loopj.android.http.JsonHttpResponseHandler;

public class DiscoverListFragment extends Fragment {

	private ArrayList<Tweet> tweets;
	private TweetsAdapter adapter;
	private EditText etQuery;
	ListView lvTweets;

	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		return inf.inflate(R.layout.fragments_discover_list, parent, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		etQuery = (EditText) getActivity().findViewById(R.id.etQuery);
		tweets = new ArrayList<Tweet>();
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
		etQuery.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				discoverTweets(arg0);
				return true;
			}
			
		});
		lvTweets.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long id) {
				Intent i = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
				Tweet tweet = tweets.get(position);
				i.putExtra("screenname", tweet.getUser().getScreenName());
				startActivity(i);
			}
		});
	}

	public void discoverTweets(View v) {
		String query = etQuery.getText().toString();
		MyTwitterApp.getRestClient().query(query, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				try {
					JSONArray statuses = (JSONArray) response.get("statuses");
					adapter.addAll(Tweet.fromJson(statuses));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TweetsAdapter getAdapter() {
		return adapter;
	}
}
