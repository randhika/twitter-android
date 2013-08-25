package st40611.app.mytwitterapp.fragments;

import java.util.ArrayList;

import st40611.app.mytwitterapp.ProfileActivity;
import st40611.app.mytwitterapp.R;
import st40611.app.mytwitterapp.TweetsAdapter;
import st40611.app.restclienttemplate.models.Tweet;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TweetsListFragment extends Fragment {

	private ArrayList<Tweet> tweets;
	private TweetsAdapter adapter;
	ListView lvTweets;
	private int count = 25;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		return inf.inflate(R.layout.fragments_tweets_list, parent, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		tweets = new ArrayList<Tweet>();
		//ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
		lvTweets.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position, long id) {
				Intent i = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
				Tweet tweet = tweets.get(position);
				i.putExtra("screenname", tweet.getUser().getScreenName());
				startActivity(i);
			}
		});
		lvTweets.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView lw, final int firstVisibleItem,
					final int visibleItemCount, final int totalItemCount) {
				final int lastItem = firstVisibleItem + visibleItemCount;
				if(lastItem == totalItemCount) {
					// Last item is fully visible.
					count += 25;
					getMoreTweets(count);
				}
			}

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	private void getMoreTweets(int count) {
		//Sub-classes should implement this
	}

	public TweetsAdapter getAdapter() {
		return adapter;
	}
}
