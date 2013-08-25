package st40611.app.mytwitterapp.fragments;

import org.json.JSONArray;

import st40611.app.mytwitterapp.MyTwitterApp;
import st40611.app.restclienttemplate.models.Tweet;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

public class HomeTimelineFragment extends TweetsListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyTwitterApp.getRestClient().getHomeTimeline(25, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	
	private void getMoreTweets(int count) {
		MyTwitterApp.getRestClient().getHomeTimeline(count, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
}
