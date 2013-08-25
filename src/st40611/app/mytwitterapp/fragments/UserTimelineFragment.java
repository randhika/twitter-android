package st40611.app.mytwitterapp.fragments;

import org.json.JSONArray;

import st40611.app.mytwitterapp.MyTwitterApp;
import st40611.app.restclienttemplate.models.Tweet;
import android.os.Bundle;

import com.loopj.android.http.JsonHttpResponseHandler;


public class UserTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String screenname = (String) getActivity().getIntent().getSerializableExtra("screenname");
		if (screenname == null) {
			getOwnerTimeline();
		} else {
			getUserTimeline(screenname);
		}
	}
	
	public void getOwnerTimeline() {
		MyTwitterApp.getRestClient().getUserTimeline(null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	
	public void getUserTimeline(String screenname) {
		MyTwitterApp.getRestClient().getUserTimeline(screenname, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
}
